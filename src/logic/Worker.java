package logic;

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
	public boolean HitBy(Worker w,Direction d,Worker o)
	{
		return false;
	}

	public void Destroy()
	{
		
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
