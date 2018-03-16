package logic;

import java.util.*;

/**
 * Represents Timer that tics every once in a while.
 */
public class Timer extends Thread{
	
	private Set<Steppable> steppables; //We don't want to step something twice, do we?
	private static final int milisecstowait=100; //Modify interval here, pls.
	
	/**
	 * Constructor. Initialises the set of steppables.
	 */
	public Timer() {
		steppables = new HashSet<Steppable>();
		this.start(); //Start itself automagically, so you don't have to!
	}
	
	public void run() {
		while(true) { //I don't know if this is how to do it, but this works!
			this.Tick(); //Let's Tick
			try {
				Thread.sleep(milisecstowait);
			} catch(InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	/**
	 * This method is the tick of the timer.
	 * Calls the Step function of every registered Steppable class.
	 */
	public void Tick()
	{
	    for(Steppable s:steppables) {
	    	s.Step();
	    }
	}
	
	/**
	 * Register a Steppable class.
	 * @param s: Steppable to register.
	 */
	public void addSteppable(Steppable s) throws Exception
	{
		if(s==null) throw new NullPointerException("Cannot add null to our set of Steppables.");
		if(this.steppables.contains(s)) throw new Exception("Item already in collection.");
		this.steppables.add(s);
		
	}
	
	/**
	 * De-register a Steppable class.
	 * @param s: Steppable to de-register.
	 */
	public void RemoveSteppable(Steppable s) throws Exception
	{
		if(s==null) throw new NullPointerException("Cannot remove null from our set of Steppables.");
		if (this.steppables.isEmpty()) throw new Exception("This collection is empty.");
		if (!(this.steppables.contains(s))) throw new Exception("Item not in collection.");
		this.steppables.remove(s);
	}
	

}
