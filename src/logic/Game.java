 package logic;

import java.io.Serializable;
import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/** Represents the entire game logic.
 * @author Kristof
 * @version 1.0
 * @since 1.0
*/
public class Game implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5999992904426996002L;
	private static final Logger LOGGER = Logger.getLogger( Game.class.getName() );
	private Set<Map> maps; //We don't want to accidentally store the same map twice, do we?
	private Map currentmap;	//We are playing on that map,that is the one stepping.
	private static Game instance = null;
	/**
	 * Default constructor, initialises the maps Set with a super-duper fast HashSet <3
	 */
	private Game() {
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		
		this.setMaps(new HashSet<Map>());
		LOGGER.log( Level.FINE, "Game created");
	}
	
	/**
	 * Called when a map starts.
	 * Think of this as a 2nd phase of the initialisation of a map.
	 * (The first being its constructor)
	 * @param m: Map to start
	 */
	public void start(Map m)
	{
		if(m!=null) {
			if(!(maps.contains(m))) throw new IllegalArgumentException("Invalid Map Passed"); 	//Checking that the map actually exists.
			this.setCurrentmap(m); 
			m.startMap();	//Chosen map being started
			LOGGER.log( Level.FINE, "New map started");
		}
		else throw new NullPointerException("m cannot be null"); 
	}
	
	/**
	 * Called when the game is over.
	 */
	public void end()
	{
		LOGGER.log( Level.FINE, "Game ended");
		return;
	}

	/**
	 * @param maps the maps to set
	 */
	private void setMaps(Set<Map> maps) {
		this.maps = maps;	
	}
	
	/**
	 * @param m the map to add
	 */
	public void addMap(Map m) {
		if (m!=null) maps.add(m);
		else throw new IllegalArgumentException("Cannot add null to maps collection.");
	}
	
	/**
	 * @param m the map to remove
	 */
	public void removeMap(Map m) {
		if (m==null) throw new IllegalArgumentException("Cannot remove null to maps collection.");
		else if (maps.contains(m)) maps.remove(m);
	}
	
	/**
	 * This method realizes Game being a singleton in Java.
	 */
	public static Game getInstance() {
		if(instance == null) {
			instance = new Game();
		}
		return instance;
	}

	/**
	 * @return the currentmap
	 */
	public Map getCurrentmap() {
		return currentmap;
	}

	/**
	 * @param currentmap the currentmap to set
	 */
	public void setCurrentmap(Map currentmap) {
		if(!(maps.contains(currentmap))) throw new IllegalArgumentException("Invalid Current map"); //Checking that the map is in the collection
		else {
			LOGGER.log( Level.FINE, "Setting current map");
			this.currentmap = currentmap;	
			Timer.getInstance().addSteppable(currentmap);
		}
	}
	
	/**
	 * @return the number of maps
	 */
	public int getNumofMaps() {
		return maps.size();
	}
}
