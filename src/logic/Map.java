package logic;

import java.util.*;

/**
 * Represents a map that has things on it.
 * The Map can also step.
 */
public class Map implements Steppable {
	
	private Integer ticksRemain;
	private Set<Crate> crates; //TODO: ADDER FUNCTION
	private Set<Worker> workers; //TODO: ADDER
	private Set<Tile> tiles; //TODO: ADDER
	
	private static final int DEFAULTTICKSREMAIN=100000000;
	
	/**
	 * Default constructor
	 * Initialises the sets and Integers.
	 */
	public Map() {
		System.out.println("!New instance of Map created.");
		System.out.println(">[:Map].setCrates(new HashSet<Crate>())");
		this.setCrates(new HashSet<Crate>());
		System.out.println(">[:Map].setWorkers(new HashSet<Workers>())");
		this.setWorkers(new HashSet<Worker>());
		System.out.println(">[:Map].setTiles(new HashSet<Tile>())");
		this.setTiles(new HashSet<Tile>());
		System.out.println(">[:Map].resetTimer()");
		this.resetTimer();
	}
	
	/**
	 * Starts the map.
	 */
	public void startMap()
	{
		System.out.println(">[:Map].resetTimer()");
		this.resetTimer();
		System.out.println(">Timer.getInstance().addSteppable(this)");
		Timer.getInstance().addSteppable(this);
		System.out.println("<[:Map].startMap()");
	}
	
	/**
	 * Called when the map ends.
	 */
	public void endMap()
	{
		System.out.println(">Timer.getInstance().removeSteppable(this)");
		Timer.getInstance().removeSteppable(this);
		System.out.println("<[:Map].endMap()");
	}
	
	/**
	 * Resets the remaining time to the default time of a game.
	 */
	public void resetTimer()
	{
		//reseting the time
		System.out.println("#ticksRemain=DEFAULTTICKSREMAIN");
		this.setTicksRemain(DEFAULTTICKSREMAIN);
		System.out.println("<[:Map].resetTimer()");
	}
	
	/**
	 * Called by the Timer at every Tick. If there are no crates, only one worker or no more
	 * time left it will end the map. Else it will shorten the remaining time by one second.
	 * 
	 * Not going to log Step() functions, to not spam the STDout.
	 */
	public void step()
	{
		//checking if there is any reason to end the map
		if(crates.isEmpty() || workers.size() <= 2 || ticksRemain == 0)
			endMap();
		else
			//shortening the remaining time by 1 tick
			ticksRemain--;
		
		return;
	}

	/**
	 * @return the tiles
	 */
	public Set<Tile> getTiles() {
		System.out.println("<[:Map].getTiles(): tiles");
		return tiles;
	}

	/**
	 * @param tiles the tiles to set
	 */
	public void setTiles(Set<Tile> tiles) {
		System.out.println("#tiles=newtiles");
		this.tiles = tiles;
		System.out.println("<[:Map].setTiles(tiles): void");
	}
	/**
	 * @return the ticksRemain
	 */
	public Integer getTicksRemain() {
		System.out.println("<[:Map].getTicksRemain(): " + ticksRemain.toString());
		return ticksRemain;
	}

	/**
	 * @param ticksRemain the ticksRemain to set
	 */
	public void setTicksRemain(Integer ticksRemain) {
		System.out.println("#ticksRemain=" + ticksRemain.toString());
		this.ticksRemain = ticksRemain;
		System.out.println("<[:Map].setTicksRemain(ticksRemain): void");
	}

	/**
	 * @return the crates
	 */
	public Set<Crate> getCrates() {
		System.out.println("<[:Map].getCrates(): crates");
		return crates;
	}

	/**
	 * @param crates the crates to set
	 */
	public void setCrates(Set<Crate> crates) {
		System.out.println("#crates=newcrates");
		this.crates = crates;
		System.out.println("<[:Map].setCrates(crates): void");
	}

	/**
	 * @return the workers
	 */
	public Set<Worker> getWorkers() {
		System.out.println("<[:Map].getWorkers(): workers");
		return workers;
	}

	/**
	 * @param workers the workers to set
	 */
	public void setWorkers(Set<Worker> workers) {
		System.out.println("#workers=newworkers");
		this.workers = workers;
		System.out.println("<[:Map].setWorkers(workers): void");
	}
}
