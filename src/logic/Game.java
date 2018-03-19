package logic;

import java.util.*;

/** Represents the entire game logic.
 * @author Kristof
 * @version 1.0
 * @since 1.0
*/
public class Game {
	
	private Set<Map> maps; //We don't want to accidentally store the same map twice, do we?
	private Map currentmap;	//We are playing on that map,that is the one stepping.
	private static Game instance = null;
	/**
	 * Default constructor, initialises the maps Set with a super-duper fast HashSet <3
	 */
	public Game() {
		System.out.println("!New Game instance created");
		this.setMaps(new HashSet<Map>());
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
		}
		else throw new NullPointerException("m cannot be null"); 
		System.out.println("<[:Game].start(m):void");
	}
	
	/**
	 * Called when the game is over.
	 */
	public void end()
	{
		System.out.println("<[:Game].end():void");
		return;
	}

	/**
	 * @param maps the maps to set
	 */
	private void setMaps(Set<Map> maps) {
		System.out.println("#maps got a new value");
		this.maps = maps;	
		System.out.println("<[:Game].setMaps(maps): void");
	}
	
	/**
	 * @param m the map to add
	 */
	public void addMap(Map m) {
		if (m!=null) maps.add(m);
		else throw new IllegalArgumentException("Cannot add null to maps collection.");
		System.out.println("<[:Game].addMap(m):void");
	}
	
	/**
	 * @param m the map to remove
	 */
	public void removeMap(Map m) {
		if (m==null) throw new IllegalArgumentException("Cannot remove null to maps collection.");
		else if (maps.contains(m)) maps.remove(m);
		System.out.println("<[:Game].addMap(m):void");
	}
	
	/**
	 * This method realizes Game being a singleton in Java.
	 */
	public static Game getInstance() {
		System.out.println("!Getting instance of Game");
		if(instance == null) {
			instance = new Game();
		}
		System.out.println("<[:Game].getInstance(): instance");
		return instance;
	}

	/**
	 * @return the currentmap
	 */
	public Map getCurrentmap() {
		System.out.println("<[:Game].getCurrentmap(): currentmap");
		return currentmap;
	}

	/**
	 * @param currentmap the currentmap to set
	 */
	public void setCurrentmap(Map currentmap) {
		if(!(maps.contains(currentmap))) throw new IllegalArgumentException("Invalid Current map"); //Checking that the map is in the collection
		else {
			System.out.println("#currentmap got a new value");
			this.currentmap = currentmap;	
		}
		System.out.println("<[:Game].setCurrentmap(currentmap): void");
	}
}
