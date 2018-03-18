package logic;

import java.util.*;

/**
 * Represents a map that has things on it.
 * The Map can also step.
 */
public class Map implements Steppable {
	
	private Integer ticksRemain;
	private Integer defaultTime;
	private Set<Crate> crates;
	private Set<Worker> workers;
	private Set<Tile> tiles;
	
	private static final int DEFAULTTICKSREMAIN=100000000;
	
	/**
	 * Default constructor
	 * Initialises the sets and Integers.
	 */
	public Map() {
		setCrates(new HashSet<Crate>());
		setWorkers(new HashSet<Worker>());
		setTiles(new HashSet<Tile>());
		ticksRemain=Integer.valueOf(DEFAULTTICKSREMAIN);
	}
	
	/**
	 * Starts the map.
	 */
	public void startMap()
	{
		this.resetTimer();
		Timer.getInstance().addSteppable(this);
	}
	
	/**
	 * Called when the map ends.
	 */
	public void endMap()
	{
		Timer.getInstance().removeSteppable(this);
	}
	
	/**
	 * Resets the remaining time to the default time of a game.
	 */
	public void resetTimer()
	{
		//reseting the time
		setTicksRemain(defaultTime);
		return;
	}
	
	/**
	 * Called by the Timer at every Tick. If there are no crates, only one worker or no more
	 * time left it will end the map. Else it will shorten the remaining time by one second.
	 */
	public void step()
	{
		//checking if there is any reason to end the map
		if(crates.isEmpty() || workers.size() != 2 || ticksRemain == 0)
			endMap();
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
