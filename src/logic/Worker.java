package logic;

/**
 * Represents a worker controlled by the player.
 */
public class Worker extends Thing{
	
	private Integer points;
	
	/**
	 * This function is called whenever a Worker gets hit by another Worker. As a worker can't 
	 * move other workers by himself this Worker won't move.
	 * @param w  The other worker
	 * @param d  The direction towards the other worker is moving
	 * @param o	 The owner of the other worker.
	 * @return	 False because workers can't be moved by other workers.
	 */
	public boolean hitBy(Worker w,Direction d,Thing o)
	{
		return false;
	}
	
	/**
	 * This function is called whenever a Worker gets hit by a Crate. The Crate will take the
	 * worker's tile anyway so this will return true value however the worker might get 
	 * squashed.
	 * @param t The Thing that hits the Worker
	 * @param d The Direction towards the Worker is hit
	 * @param o The owner of the Thing
	 * @return true as there should be movement
	 */
	@Override
	public boolean hitBy(Thing t,Direction d,Thing o)
	{
		
		this.updateOwner(o); 	//updating the owner for the action
		
		boolean moved = this.move(d); //trying to move..
		
		if(moved)
			this.updateOwner(this);  //we need to reset the owner
		else 
			this.destroy(); //if hthe Worker couldn't move he will get squashed
		
		return true; //gives space for the Crate incoming
		
	}
	
	/**
	 * This function represents the death of a worker.
	 */
	@Override
	public void destroy()
	{
		//Please implement
	}

	/**
	 * @return the points
	 */
	public Integer getPoints() {
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(Integer points) {
		this.points = points;
	}
	
}
