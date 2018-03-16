package logic;

import java.util.*;

/** Represents the entire game logic.
 * @author Kristof
 * @version 1.0
 * @since 1.0
*/
public class Game {
	
	private Set<Map> maps; //We don't want to accidentally store the same map twice, do we?
	
	/**
	 * Default contructor, initializes the maps Set with a super-duper fast HashSet <3
	 */
	public Game() {
		setMaps(new HashSet<Map>());
	}
	
	/**
	 * Called when a map stars.
	 * Think of this as a 2nd phase of the initialisation of a map.
	 * (The first being its constructor)
	 * @param m: Map to start (WHY?)
	 */
	public void Start(Map m)
	{
		if(m!=null)
			m.StartMap();
		else throw new NullPointerException("m cannot be null");
		
	}
	
	/**
	 * Called when the game is over.
	 */
	public void End()
	{
		
	}

	/**
	 * @return the maps
	 */
	public Set<Map> getMaps() {
		return maps;
	}

	/**
	 * @param maps the maps to set
	 */
	public void setMaps(Set<Map> maps) {
		this.maps = maps;
	}

}
