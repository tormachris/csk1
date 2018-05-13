package logic;

import java.io.Serializable;

/**
 *  Represents anything that does something at every tick of the timer.
 */
public interface Steppable extends Serializable{
	
	/**
	 *  Called by the Timer at every Tick.
	 */
	public void step();

}
