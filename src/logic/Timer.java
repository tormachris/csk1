package logic;

import java.util.*;

/**
 * Represents Timer that tics every once in a while.
 */
public class Timer { //Please make the Timer work. Reminder: Java has shit that will execute periodically.
					 //Make this a child of that and make this class ready for work.
	
	private Set<Steppable> steppables; //We don't want to step something twice, do we?
	
	/**
	 * Constructor. Initialises the set of steppables.
	 */
	public Timer() {
		steppables = new HashSet<Steppable>();
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
