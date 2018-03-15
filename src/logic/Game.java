package logic;

import java.util.*;


public class Game {
	
	private Set<Map> maps; //We don't want to accidentally store the same map twice, do we?
	
	public Game() {
		setMaps(new HashSet<Map>());
	}
	
	public void Start(Map m)
	{
		
	}
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
