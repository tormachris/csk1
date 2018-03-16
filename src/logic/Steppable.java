package logic;

/**
 *  Represents anything that does something at every tick of the timer.
 */
public interface Steppable {
	
	/**
	 *  Called by the Timer at every Tick.
	 */
	public void Step();

}
