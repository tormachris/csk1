package logic;

/**
 * Represents a general thing that moves on the map.
 * Abstract.
 */
public abstract class Thing {
	
	private Thing owner; 
	private Direction moving;
	private Tile tile;
	private Integer weight;
	
	
	/**
	 * Default Constructor
	 * @param None
	 */
	public Thing(Integer w) {
		//Let this be a warning, if a thing's owner is null, it is brand new!
		owner=null;
		tile=null; //Hanging in the aether.
		weight = w;
	}
	
	/**
	 * @return the weight
	 */
	public Integer getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}


	/**
	 * Collides the Thing with another Thing
	 * @param t The other Thing
	 * @return Indicates whether there has been movement due to the collision.
	 */
	public boolean collideWith(Thing t)
	{
		Boolean collided;
		//Hitting the other Thing with this towards this one's moving direction
		collided = Boolean.valueOf(t.hitBy(this, moving , this.owner));
		return collided;
		
	}
	
	/**
	 * This function tries to move the Thing towards a given direction. 
	 * @param d The given direction.
	 * @return The return value indicates if the moving towards d direction was possible.
	 */
	public boolean move(Direction d)
	{	
		//getting the neighbour of the current Tile and setting the moving attribute
		Tile newTile;
		newTile = tile.getNeighbour(d);
		moving = d;
		
		//checking whether the new Tile accepts the Thing
		Boolean moved = newTile.accept(this);
		if(moved)
		{
			//if the Thing was accepted by the new Tile the Thing moves to it.
			tile.remove(this);
			tile = newTile;
		}
		
		return moved.booleanValue();
	}
	/**
	 * Called when hit by another thing.
	 */
	public boolean hitBy(Thing t,Direction d, Thing o)
	{
		Boolean hit;
		this.updateOwner(o);								//While it is pushed by another Thing, it has to be the property of the Thing.	
		if(this.getOwner()==null) 
			throw new IllegalArgumentException("Null ptr in owner.");
		else
		{
		Worker w=(Worker)this.getOwner();
		w.setForce(w.getForce()-this.getWeight()*this.getTile().getFriction());
		Double d2=0.0;
		if(w.getForce().compareTo(d2)>=0) 
		{
			hit = Boolean.valueOf(this.move(d));		//The direction is set, and the Thing is pushed into this direction.
		}
		else
			hit=Boolean.FALSE;
		}
		return hit.booleanValue();
	}
	
	/**
	 * Updates the owner of the Thing
	 * @param t The new owner
	 * @return if the update was successful
	 */
	public final boolean updateOwner(Thing t)
	{
		this.owner = t;
		return true;	
	}
	
	/**
	 * This function is called by a switch this Thing is on.
	 * @param s The Switch onto which the Thing has moved.
	 */
	public void onSwitch(Switch s)
	{
		return;
	}
	
	/**
	 * Called when a worker is on an EndTile. Does nothing, unless overridden.
	 * @param t	EndTile the worker is on
	 */
	public void onEndTile(EndTile t)
	{
		return;
	}
	
	/**
	 *Getter of the attribute owner.
	 * @return The owner of the Thing
	 */
	public final Thing getOwner()
	{
		return owner;
	}
	
	/**
	 * Getter of the Tile on which the Thing is at the given moment.
	 * @return The tile
	 */
	public final Tile getTile()
	{
		return tile;
	}
	
	/**
	 * Setter of the Tile the Thing is on.
	 * @param t The tile
	 */
	public final void setTile(Tile t) {
		if(t!=null) tile=t;
	}
	
	 public void destroy()
	{
		return;
	}
}
