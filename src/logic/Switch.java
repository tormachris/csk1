package logic;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Represents the Switch which can toggle a Hole linked to it. 
 * @author tdani
 *
 */
public class Switch extends Tile {

	private static final long serialVersionUID = 8468456817549292315L;
	private static final Logger LOGGER = Logger.getLogger(Switch.class.getName());
	private Hole hole;

	/**
	 * Default constructor
	 */
	public Switch() {
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);

	}

	/**
	 * removes the thing from the switch, toggles if it was open before
	 * @param t the thing
	 */
	@Override
	public void remove(Thing t) {
		// checking if the Thing is on this Tile
		if (t.equals(super.getThing()))
			hole.setOpen(false);
		super.remove(t);
	}

	/**
	 * Acts like a normal Tile, however if the Thing gets accepted it will call the
	 * OnSwitch function of the Thing
	 * 
	 * @param t
	 *            The Thing that moves onto the Switch
	 * @return Shows whether the Thing has been accepted
	 */
	@Override
	public boolean accept(Thing t) {
		// calls the same function of its superclass
		Boolean accepted = Boolean.valueOf(super.accept(t));
		// if the Thing is allowed to move onto the Tile the Switch will trigger its
		// OnSwitch()
		// function in order to inform the Thing that it has moved onto a Switch
		if (accepted) {
			t.onSwitch(this);
			if (!t.getClass().equals(Crate.class))
				hole.setOpen(false);
		}
		LOGGER.log(Level.FINE, "Switch accepted:{0}", accepted);
		return accepted.booleanValue();
	}

	/**
	 * This function should be called when a Crate moves onto a switch. If this
	 * happens the Switch will toggle the state of the Hole linked to it.
	 * 
	 * @param c
	 *            The Crate that has moved onto the Switch.
	 */
	public void activate() {
		LOGGER.log(Level.FINE, "Switch toggling hole");
		hole.toggleOpen();
	}

	/**
	 * Setter of the attribute Hole.
	 * 
	 * @param newvalue
	 *            The new hole
	 */
	public void setHole(Hole newvalue) {
		if (newvalue != null) { // Checking for valid value
			hole = newvalue;
			LOGGER.log(Level.FINE, "New value for hole set");
		} else
			throw new NullPointerException("newvalue is null");
	}

	/**
	 * Gets the current hole.
	 */
	public Hole getHole() {
		return hole;
	}

	/**
	 * Constructor. Sets the hole.
	 */
	public Switch(Hole h) {
		this.setHole(h);
	}
}
