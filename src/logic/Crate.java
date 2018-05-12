package logic;

import java.util.logging.*;

/** Represents an Thing that cannot move on it's own and can activae switches.
 * They disappear on EndTiles.
 * @author Kristof
 * @version 1.0
 * @since 1.0
*/
public class Crate extends Thing{
	private static final Logger LOGGER = Logger.getLogger( Crate.class.getName() );
	/**
	 * Constructor. Calls the super class' constructor.
	*/
	public Crate(Integer w) {
		super(w);
		LOGGER.log(Level.FINE, "Crate created");
	}
	
	
	/**
	 * Called by an EndTile, when a crate occupies it.
	 * @param t The EndTile the Crate moves onto.
	*/
	@Override
	public void onEndTile(EndTile t)
	{	
		Worker w = (Worker)this.getOwner(); 
		w.setPoints(w.getPoints() + 1); //Destroying the Crate
		LOGGER.log(Level.FINE, "Crate is on end tile");
		this.destroy();
	}
	
	/**
	 * Called when this object is destroyed. (NOT A DESTRUCTOR)
	*/
	@Override
	public void destroy() {
		LOGGER.log(Level.FINE, "Crate is ded");
		Game.getInstance().getCurrentmap().removeCrate(this);
	}
	
	/**
	 * This function is called by a switch this Thing is on.
	 * @param s The Switch onto which the Thing has moved.
	 */
	@Override
	public void onSwitch(Switch s)
	{
		LOGGER.log(Level.FINE, "Crate is on switch");
		s.activate();	//Activate the switch tile that contains the Crate
	}

}
