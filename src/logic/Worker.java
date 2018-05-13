package logic;

import java.io.Serializable;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Represents a worker controlled by the player.
 */
public class Worker extends Thing implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6880043466619175109L;

	private static final Logger LOGGER = Logger.getLogger( Worker.class.getName() );
	
	private Integer points;
	private Double force;
	private Double initialforce;
	private Boolean alive;
	
	/**
	 * This function is called whenever a Worker gets hit by a Crate. The Crate will take the
	 * worker's tile anyway so this will return true value however the worker might get 
	 * squashed.
	 * @param t The Thing that hits the Worker
	 * @param d The Direction towards the Worker is hit
	 * @param o The owner of the Thing
	 * @return true as there should be movement
	 */
	

	public Worker(Integer w) {
		super(w);
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		
		force = w*2.5;
		initialforce=w*2.5;
		points = 0;
		alive=true;
		this.updateOwner(this);
	}

	@Override
	public boolean move(Direction d) {
		if (alive) {
			force=initialforce;
			Double temp = this.getForce();
			boolean toReturn = super.move(d);
			this.setForce(temp);
			return toReturn;
		}else return false;
	}
	@Override
	public boolean hitBy(Thing t,Direction d,Thing o)
	{
		if(t.getClass()==Worker.class) {
			return false;
		}
		else {
		this.updateOwner(o); 	//updating the owner for the action
		if(this.getOwner()==null) 
			throw new IllegalArgumentException("Null ptr in owner.");
		else
		{
		Worker w=(Worker)this.getOwner();
		w.setForce(w.getForce()-this.getWeight()*this.getTile().getFrictionMod().getFriction());
		Double d2=Double.valueOf(0);
		if(w.getForce().compareTo(d2) > 0) {

			if(Boolean.valueOf(this.move(d))) //trying to move..)
				this.updateOwner(this);  //we need to reset the owner			
				
			else {
				this.destroy(); //if the Worker couldn't move he will get squashed
				}
		LOGGER.log( Level.FINE, "Worker hitby another thing: true");
		return true; //gives space for the Crate incoming
		}
		return false;
			}
		}
	}
	
	/**
	 * This function represents the death of a worker.
	 */
	@Override
	public void destroy()
	{
		LOGGER.log( Level.FINE, "Worker ded");
		super.getTile().remove(this);
		alive=false;
		Game.getInstance().getCurrentmap().removeWorker(this);
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
	
	public void dropOil() {
		LOGGER.log( Level.FINE, "Dropping oil");
		if(alive)this.getTile().setFrictionMod(new Oil());
	}
	public void dropHoney() {
		LOGGER.log( Level.FINE, "Dropping honey");
		if(alive)this.getTile().setFrictionMod(new Honey());
	}


	/**
	 * @return the force
	 */
	public Double getForce() {
		return force;
	}


	/**
	 * @param force the force to set
	 */
	public void setForce(Double f) {
		this.force = f;
	}
	
}
