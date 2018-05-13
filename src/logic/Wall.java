package logic;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Represents a wall on the map that will never be occupied.
 */
public class Wall extends Tile {
	private static final Logger LOGGER = Logger.getLogger( Wall.class.getName() );
	public Wall() {
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		
	}
	/**
	 * This function gets called when a Thing tries to move onto a Wall. As this cannot happen
	 * the function will return false.
	 * @param t The Thing that tries to move.
	 * @return False as a Wall will not accept anything.
	 */
	@Override
	public boolean accept(Thing t)
	{
		LOGGER.log( Level.FINE, "Thing hit a wall");
		return false;
	}

}
