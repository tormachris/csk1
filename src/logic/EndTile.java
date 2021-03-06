package logic;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/** Represents an Tile that "Consumes" Crates.
 * @author Kristof
 * @version 1.0
 * @since 1.0
*/
public class EndTile extends Tile {
	private static final long serialVersionUID = 2560652795235672539L;//Serialization UID
	private static final Logger LOGGER = Logger.getLogger( EndTile.class.getName() );//Logger to log stuff
	
	/**
	 * Constructor of the EndTile
	 */
	public EndTile() {
		//Logger stuff
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
	}
	/**
	 * Acts like a normal Tile, however if the Thing gets accepted it will call the OnEndTile 
	 * function of the Thing 
	 * @param t The Thing that moves onto the EndTile
	 * @return Shows whether the Thing has been accepted
	 */
	@Override
	public boolean accept(Thing t)
	{
		boolean accepted = super.accept(t);//We call onto the supa to accept the thing
		//if the Thing is allowed to move onto the Tile the EndTile will trigger its OnEndTile()
		//function in order to inform the Thing that it has moved onto an EndTile.
		if(accepted)
			t.onEndTile(this);
		LOGGER.log(Level.FINE, "Thing accepted by EndTile: {0}",accepted);
		return accepted;
	}

}
