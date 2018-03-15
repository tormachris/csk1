package logic;

import java.util.*;

public class Timer {
	
	private Set<Steppable> steppables; //We don't want to step something twice, do we?
	
	public Timer() {
		steppables = new HashSet<Steppable>();
	}
	
	public void Tick()
	{
	    for(Steppable s:steppables) {
	    	s.Step();
	    }
	}
	
	public void AddSteppable(Steppable s) throws Exception
	{
		if(this.steppables.contains(s)) throw new Exception("Item already in collection.");
		this.steppables.add(s);
		
	}
	
	public void RemoveSteppable(Steppable s) throws Exception
	{
		if (this.steppables.isEmpty()) throw new Exception("This collection is empty.");
		if (!(this.steppables.contains(s))) throw new Exception("Item not in collection.");
		this.steppables.remove(s);
	}
	

}
