package logic;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Switch extends Tile {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8468456817549292315L;
	private static final Logger LOGGER = Logger.getLogger( Switch.class.getName() );
	private Hole hole;
	public Switch() {
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		
	}
	/**
	 * Acts like a normal Tile, however if the Thing gets accepted it will call the OnSwitch 
	 * function of the Thing 
	 * @param t The Thing that moves onto the Switch
	 * @return Shows whether the Thing has been accepted
	 */
	@Override
	public boolean accept(Thing t)
	{
		//calls the same function of its superclass
		Boolean accepted = Boolean.valueOf(super.accept(t));
		//if the Thing is allowed to move onto the Tile the Switch will trigger its OnSwitch()
		//function in order to inform the Thing that it has moved onto a Switch
		if(accepted)
			t.onSwitch(this);
		LOGGER.log( Level.FINE, "Switch accepted:{0}",accepted);
		return accepted.booleanValue();
	}
	
	/**
	 * This function should be called when a Crate moves onto a switch. If this happens the
	 * Switch will toggle the state of the Hole linked to it.
	 * @param c The Crate that has moved onto the Switch.
	 */
	public void activate()
	{
		LOGGER.log( Level.FINE, "Switch toggling hole");
		hole.toggleOpen();
	}
	
	/**
	 * Setter of the attribute Hole.
	 * @param newvalue The new hole
	 */
	public void setHole(Hole newvalue)
	{
		if(newvalue!=null) {		//Checking for valid value
			hole=newvalue;
			LOGGER.log( Level.FINE, "New value for hole set");
		}
		else throw new NullPointerException("newvalue is null");
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
	Switch(Hole h){
		this.setHole(h);
	}
}
