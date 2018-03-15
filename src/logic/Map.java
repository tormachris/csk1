package logic;

import java.util.*;

public class Map implements Steppable {
	
	private Integer ticksRemain;
	private Integer defaultTime;
	private Set<Crate> crates;
	private Set<Worker> workers;
	private Set<Tile> tiles;
	
	/**
	 * Default constructor
	 */
	public Map() {
		setCrates(new HashSet<Crate>());
		setWorkers(new HashSet<Worker>());
		setTiles(new HashSet<Tile>());
	}
	
	public void StartMap()
	{
		
		
	}
	
	public void EndMap()
	{
		
	}
	
	/**
	 * Resets the remaining time to the default time of a game.
	 */
	public void ResetTimer()
	{
		//reseting the time
		setTicksRemain(defaultTime);
		return;
	}
	
	/**
	 * Function which is called at every tick. If there are no crates, only one worker or no more
	 * time left it will end the map. Else it will shorten the remaining time by one second.
	 */
	public void Step()
	{
		//checking if there is any reason to end the map
		if(crates.size() == 0 || workers.size() != 2 || ticksRemain == 0)
			EndMap();
		else
			//shortening the remaining time by 1s
			ticksRemain--;
		
		return;
	}

	/**
	 * @return the tiles
	 */
	public Set<Tile> getTiles() {
		return tiles;
	}

	/**
	 * @param tiles the tiles to set
	 */
	public void setTiles(Set<Tile> tiles) {
		this.tiles = tiles;
	}
	/**
	 * @return the ticksRemain
	 */
	public Integer getTicksRemain() {
		return ticksRemain;
	}

	/**
	 * @param ticksRemain the ticksRemain to set
	 */
	public void setTicksRemain(Integer ticksRemain) {
		this.ticksRemain = ticksRemain;
	}

	/**
	 * @return the defaultTime
	 */
	public Integer getDefaultTime() {
		return defaultTime;
	}

	/**
	 * @param defaultTime the defaultTime to set
	 */
	public void setDefaultTime(Integer defaultTime) {
		this.defaultTime = defaultTime;
	}

	/**
	 * @return the crates
	 */
	public Set<Crate> getCrates() {
		return crates;
	}

	/**
	 * @param crates the crates to set
	 */
	public void setCrates(Set<Crate> crates) {
		this.crates = crates;
	}

	/**
	 * @return the workers
	 */
	public Set<Worker> getWorkers() {
		return workers;
	}

	/**
	 * @param workers the workers to set
	 */
	public void setWorkers(Set<Worker> workers) {
		this.workers = workers;
	}
}
