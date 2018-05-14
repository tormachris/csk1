package logic;

import java.util.logging.*;

/** Represents an Thing that cannot move on it's own and can activate switches.
 * They disappear on EndTiles.
 * @author Kristof
 * @version 1.0
 * @since 1.0
*/
public class Crate extends Thing{
	/**
	 * Serial UID of this class
	 */
	private static final long serialVersionUID = -3311379866858796414L;
	private static final Logger LOGGER = Logger.getLogger( Crate.class.getName() );//Logger of this class
	/**
	 * Constructor. Calls the super class' constructor.
	*/
	public Crate(Integer w) {
		super(w);//Calling the supa
		//Logger setup
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
	}
	
	
	/**
	 * Called by an EndTile, when a crate occupies it.
	 * @param t The EndTile the Crate moves onto.
	*/
	@Override
	public void onEndTile(EndTile t)
	{	//We are on endtile
		Worker w = (Worker)this.getOwner(); //We want to talk to the owner
		w.setPoints(w.getPoints() + 1); //Destroying the Crate
		t.remove(this);//We disappear
		LOGGER.log(Level.FINE, "Crate is on end tile");
		this.destroy();//And then we die
	}
	
	/**
	 * Called when this object is destroyed. (NOT A DESTRUCTOR)
	*/
	@Override
	public void destroy() {
		LOGGER.log(Level.FINE, "Crate is ded");
		getTile().remove(this);//Just to make sure, we disappear again
		Game.getInstance().getCurrentmap().removeCrate(this);//We tell the map the we are history
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
