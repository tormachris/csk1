package logic;

import java.io.Serializable;
import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Represents a map that has things on it.
 * The Map can also step.
 */
public class Map implements Steppable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6905345017425347502L;
	private static final Logger LOGGER = Logger.getLogger( Map.class.getName() );
	private Integer ticksRemain;
	private Set<Crate> crates; 
	private Set<Worker> workers; 
	private Set<Tile> tiles; 

	private static final int DEFAULTTICKSREMAIN=1000;
	
	/**
	 * @return the defaultticksremain
	 */
	public static int getDefaultticksremain() {
		return DEFAULTTICKSREMAIN;
	}

	/**
	 * Default constructor
	 * Initialises the sets and Integers.
	 */
	public Map() {
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		
		this.setCrates(new HashSet<Crate>());
		this.setWorkers(new HashSet<Worker>());
		this.setTiles(new HashSet<Tile>());
		this.resetTimer();
	}
	
	/**
	 * Starts the map.
	 */
	public void startMap()
	{
		LOGGER.log( Level.FINE, "Map started");
		this.resetTimer();
		Timer.getInstance().addSteppable(this);
		Game.getInstance().setCurrentmap(this);
	}
	
	/**
	 * Called when the map ends.
	 */
	public void endMap()
	{
		LOGGER.log( Level.FINE, "Map ended");
		ticksRemain=-1;
		Timer.getInstance().removeSteppable(this);
	}
	
	/**
	 * Resets the remaining time to the default time of a game.
	 */
	public void resetTimer()
	{
		//reseting the time
		this.setTicksRemain(DEFAULTTICKSREMAIN);
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
		if(crates.isEmpty() || workers.size() < 2 || ticksRemain.intValue()<=0)
			endMap();
		else
			//shortening the remaining time by 1 tick
			ticksRemain--;
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
	
	/**
	 * @param w the worker to add
	 */	
	public void addWorker(Worker w) {
		if(w==null)
			throw new IllegalArgumentException("Cannot add null to workers collection.");	 //Checking for valid input 
		else {
			this.workers.add(w);
		}
	}
	
	/**
	 * @param worker the Worker to remove
	 */
	public void removeWorker(Worker w) {
		if(this.workers.contains(w))	//Checking that the worker is on the map.
			this.workers.remove(w);
	}
	/**
	 * @param c the Crate to add
	 */
	public void addCrate(Crate c) {
		if(c==null)
			throw new IllegalArgumentException("Cannot add null to crates collection.");	//Checking for valid input 
		else
			this.crates.add(c);
	}	
	/**
	 * @param c the Crate to remove
	 */
	public void removeCrate(Crate c) {
		if(this.crates.contains(c))	
			this.crates.remove(c);			//Checking that the Crate is in the crates collection.
	}
	/**
	 * @param t the Tile to add
	 */
	public void addTile(Tile t) {
		if(t==null)
			throw new IllegalArgumentException("Cannot add null to tiles collection.");	//Checking for valid input.
		else
			this.tiles.add(t);
	}
	/**
	 * @param t the Tile to remove
	 */
	public void removeTile(Tile t) {
		if(this.tiles.contains(t))	//Checking that the Tile is in the tiles collection.
			this.tiles.remove(t);
	}
}
