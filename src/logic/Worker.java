package logic;

/**
 * Represents a worker controlled by the player.
 */
public class Worker extends Thing{
	
	
	private Integer points;
	private Integer strength;
	
	/**
	 * This function is called whenever a Worker gets hit by a Crate. The Crate will take the
	 * worker's tile anyway so this will return true value however the worker might get 
	 * squashed.
	 * @param t The Thing that hits the Worker
	 * @param d The Direction towards the Worker is hit
	 * @param o The owner of the Thing
	 * @return true as there should be movement
	 */
	
	public Worker(Integer w,Integer s) {
		super(w);
		strength = s;
		System.out.println("!New Worker created.");
	}

	
	@Override
	public boolean hitBy(Thing t,Direction d,Thing o)
	{
		if(t.getClass()==Worker.class) {
			System.out.println("<[:Worker].hitBy(w,d,o):false");
			System.out.println("!I am not going to do anything, bc I was hit by a Worker.");
			return false;
		}
		else {
		System.out.println(">[:Worker].updateOwner(o)");
		this.updateOwner(o); 	//updating the owner for the action
		
		System.out.println(">[:Worker].updateOwner(o)");
		Boolean moved = Boolean.valueOf(this.move(d)); //trying to move..
		System.out.println("#moved= "+ moved.toString());
		if(moved.booleanValue()) {
			System.out.println(">[:Worker].updateOwner(this)");
			this.updateOwner(this);  //we need to reset the owner
		}
		else {
			System.out.println(">[:Worker].destroy()");
			this.destroy(); //if the Worker couldn't move he will get squashed
		}
		System.out.println("<[:Worker].hitBy(t,d,o): true");
		return true; //gives space for the Crate incoming
		}
	}
	
	/**
	 * This function represents the death of a worker.
	 */
	@Override
	public void destroy()
	{
		System.out.println("<[:Worker].destroy():void");
		System.out.println(">[:Game].getInstance().getCurrentmap().removeWorker(this)");
		Game.getInstance().getCurrentmap().removeWorker(this);
		System.out.println("<[:Worker].destroy():void");
	}

	/**
	 * @return the points
	 */
	public Integer getPoints() {
		System.out.println("<[:Worker].getPoints(): " + points.toString());
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(Integer points) {
		System.out.println("#points= points");
		this.points = points;
		System.out.println("<[:Worker].setPoints(points): void");
	}
	
	public void dropOil() {
		this.getTile().setFriction(0.3);
	}
	public void dropHoney() {
		this.getTile().setFriction(0.7);
	}


	/**
	 * @return the strength
	 */
	public Integer getStrength() {
		return strength;
	}


	/**
	 * @param strength the strength to set
	 */
	public void setStrength(Integer strength) {
		this.strength = strength;
	}
	
}
