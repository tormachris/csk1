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
	//Logger of this class
	private static final Logger LOGGER = Logger.getLogger( Worker.class.getName() );
	
	private Integer points;//Points of the worker
	private Double force;//The sum force of the worker
	private Double initialforce;//This remains constant throughout the worker's life.
	private Boolean alive;//Indicates whether the worker can perform actions.
	
	/**
	 * The Constructor.
	 * Sets up the logger of this class and it's parameters.
	 */
	public Worker(Integer w) {
		super(w);//SUPA CALL
		//Logger setup
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		//Modify this part
		force = w*2.5;
		initialforce=w*2.5;
		points = 0;
		alive=true;
		this.updateOwner(this);
	}
	/**
	 * This function realizes the specialties of the Worker's movements.
	 * @param d The Direction where the worker is going
	 * @return true as there should be movement
	 */
	@Override
	public boolean move(Direction d) {
		if (alive) {//A dead worker cannot move, obviously
			force=initialforce;//We reset it's force, so it does creep down
			Double temp = this.getForce();//Calculate the force
			boolean toReturn = super.move(d);
			this.setForce(temp);
			return toReturn;//Return boi
		}else return false;
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
		if(t.getClass()==Worker.class) {
			return false;//Workers cannot hit each other
		}
		else {
		this.updateOwner(o); 	//updating the owner for the action
		if(this.getOwner()==null) //Null guard
			throw new IllegalArgumentException("Null ptr in owner.");
		else
		{
		Worker w=(Worker)this.getOwner();//Only workers can be owners, so this is safe
		w.setForce(w.getForce()-this.getWeight()*this.getTile().getFrictionMod().getFriction());//We calculate the new force of the owner.
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
		super.getTile().remove(this);//We remove the worker from the tile
		alive=false;//It is dead. Duh
		Game.getInstance().getCurrentmap().removeWorker(this);//We tell the map that this worker is no longer in play
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
		if(alive)this.getTile().setFrictionMod(new Oil());//drop the base
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
