package logic;

import java.util.*;

/**
 * Represents Timer that tics every once in a while.
 */
public class Timer extends Thread{
	
	private Set<Steppable> steppables; //We don't want to step something twice, do we?
	
	private static final int TIMETOWAIT=1000000; //Modify interval here, pls.
	
	private static Timer instance = null;
	/**
	 * @return the TIMETOWAIT
	 */
	public static int getMilisecstowait() {
		return TIMETOWAIT;
	}
	
	/**
	 * Constructor. Initialises the set of steppables.
	 */
	public Timer() {
		steppables = new HashSet<>();
		this.start(); //Start itself automagically, so you don't have to!
	}
	
	/**
	 * This method realizes Timer being a singleton in Java.
	 */
	public static Timer getInstance() {
		if(instance == null) {
			instance = new Timer();
		}
		return instance;
	}
	
	/**
	 * This function is called when the thread of the Timer is started.
	 * It Ticks the timer every once in a while.
	 * 
	 * Not adding Logs to this or any Step() functions, to not spam the STDout.
	 */
	@Override
	public void run() {
		while(true) { //I don't know if this is how to do it, but this works!
			this.tick(); //Let's Tick
			try {
				Thread.sleep(TIMETOWAIT);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	/**
	 * This method is the tick of the timer.
	 * Calls the Step function of every registered Steppable class.
	 * 
	 * Not going to log this either.
	 */
	public void tick()
	{
	    for(Steppable s:steppables) {
	    	s.step();
	    }
	}
	
	/**
	 * Register a Steppable class.
	 * @param s: Steppable to register.
	 */
	public void addSteppable(Steppable s)
	{
		if(s==null) throw new NullPointerException("Cannot add null to our set of Steppables."); //Checking for valid value.
		if(this.steppables.contains(s)) throw new IllegalArgumentException("Item already in collection.");
		this.steppables.add(s);
	}
	
	/**
	 * De-register a Steppable class.
	 * @param s: Steppable to de-register.
	 */
	public void removeSteppable(Steppable s)
	{
		if(s==null) throw new NullPointerException("Cannot remove null from our set of Steppables.");
		if (this.steppables.isEmpty()) throw new IllegalArgumentException("This collection is empty.");
		if (!(this.steppables.contains(s))) throw new IllegalArgumentException("Item not in collection.");
		this.steppables.remove(s);
	}
	

}
