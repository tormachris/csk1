package logic;

/**
 * Represents a general thing that moves on the map.
 * Abstract.
 */
public abstract class Thing {
	
	private Thing owner;
	private Direction moving;
	private Tile tile;
	
	/**
	 * Default Constructor
	 * @param None
	 */
	public Thing() {
		//Let this be a warning, if a thing's owner is null, it is brand new!
		owner=null;
		tile=null; //Hanging in the aether.
	}
	
	/**
	 * Collides the Thing with another Thing
	 * @param t The other Thing
	 * @return Indicates whether there has been movement due to the collision.
	 */
	public boolean collideWith(Thing t)
	{
		boolean collided;
		//Hitting the other Thing with this towards this one's moving direction
		collided = t.hitBy(this, moving , this.owner);
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
		boolean moved = newTile.accept(this);
		if(moved)
		{
			//if the Thing was accepted by the new Tile the Thing moves to it.
			tile.remove(this);
			tile = newTile;
		}
		
		return moved;
	}
	/**
	 * Called when hit by another thing.
	 */
	public boolean hitBy(Thing t,Direction d, Thing o)
	{
		
		this.updateOwner(o);
		
		boolean moved = this.move(d);
		
		return moved;
	}
	
	/**
	 * Updates the owner f the Thing
	 * @param t The new owner
	 * @return if the update was successful
	 */
	public final boolean updateOwner(Thing t)
	{
		this.owner = t;
		return true;	//valami ellenőrzés kéne ide hogy legyen értelme a visszatérési értéknek de nemtom
	}
	
	/**
	 * This function should be called whenever a Thing moves onto a Switch.
	 * @param s The Switch onto which the Thing has moved.
	 */
	public void onSwitch(Switch s)
	{	
		//tries to activate the switch
		s.activate(this);
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
	
	public void destroy()
	{
		
	}
}
