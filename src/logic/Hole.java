package logic;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/** Represents a Tile, that (if active) will kill any Thing that is on it.
 * Activated by the Map (Static) or a Switch (Dynamic)
 * @author Kristof
 * @version 1.0
 * @since 1.0
*/
public class Hole extends Tile {
	private static final Logger LOGGER = Logger.getLogger( Hole.class.getName() );
	private Boolean open;
	
	/**
	 * With this constructor one can make a permanently open Hole.
	 * (By not attaching an instance to any switch and making isOpen true
	 * @param isOpen: default state
	 */
	public Hole(Boolean isOpen) {
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		
		this.setOpen(isOpen);
		LOGGER.log( Level.FINE, "New Hole created with attribute:{0}",isOpen);
	}
	
	/**
	 * Default constructor
	 */
	public Hole() {
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		
		LOGGER.log( Level.FINE, "New Hole created");
		this.setOpen(Boolean.valueOf(false));
	}
	
	/**
	 * @return the open
	 */
	public Boolean getOpen() {
		return open;
	}

	/**
	 * @param open the open to set
	 */
	public void setOpen(Boolean open) {
		LOGGER.log( Level.FINE, "Hole set to state:{0}",open);
		this.open = open;
	}

	/**
	 * This function is called when a Thing moves onto a Hole.
	 * @param t The Thing that moves.
	 * @return Shows if the Thing has been accepted.
	 */
	@Override
	public  boolean accept(Thing t){
		Boolean accepted;
		//if the hole is open it destroys every thing that moves onto it.
		if(open.booleanValue())
		{
			LOGGER.log( Level.FINE, "Hole killed thing");
			t.destroy();	//Destroying the Thing on the Hole.
			accepted = Boolean.TRUE;
		}
		//if it is not open it will act just like a normal tile.
		else {
			LOGGER.log( Level.FINE, "Hole closed, accepting thing as if it was a regular tile");
			accepted = Boolean.valueOf(super.accept(t));
		}
		LOGGER.log( Level.FINE, "Hole accepted:{0}",accepted);
		return accepted.booleanValue();
	}
		
	/**
	 * Changes the state of the hole.
	 */
	public void toggleOpen()
	{
		setOpen(!open);	//Changing to the opposite state
		if(open.booleanValue() && this.getThing()!=null)
		{
			LOGGER.log( Level.FINE, "Hole killed thing");
			this.getThing().destroy();	//Destroying the Thing standing on the Hole when it is activated.
		}
	}
}
