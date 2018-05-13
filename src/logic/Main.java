package logic;

import java.io.Serializable;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Starting point of the program
 * @author kristof
 *
 */
public class Main implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1942436123313288694L;
	private static final Logger LOGGER = Logger.getLogger( Map.class.getName() );
	/**
	 * Entry point of the program
	 * @param args
	 */
	public static void main(String[] args) {
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		
		LOGGER.log(Level.FINE, "Initialising timer");
		Timer.getInstance().setRunning(false);
		LOGGER.log(Level.FINE, "Initialising Game");
		Game.getInstance();	//Init Game 
		
		LOGGER.log(Level.FINE, "Initialising GUI and making is visible");
		gui.SokobanGui.getInstance().setVisible(true); //Show the GUI
	}
}
