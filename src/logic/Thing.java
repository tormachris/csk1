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
		System.out.println("!New instance of Thing created."); //LOLOLOL WILL NEVER HAPPEN
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
		Boolean collided;
		//Hitting the other Thing with this towards this one's moving direction
		collided = Boolean.valueOf(t.hitBy(this, moving , this.owner));
		System.out.println("<[:Thing].collideWith(t): "+ collided.toString());
		System.out.println("!Check what type of Thing this is!");
		return collided;
		
	}
	
	/**
	 * This function tries to move the Thing towards a given direction. 
	 * @param d The given direction.
	 * @return The return value indicates if the moving towards d direction was possible.
	 */
	public boolean move(Direction d)
	{	
		System.out.println("!Check what type of Thing this is!");
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
		System.out.println("!Check what type of Thing this is!");
		
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
		System.out.println("!Check what type of Thing this is!");
		this.owner = t;
		return true;	//valami ellenorzes kene ide, hogy legyen ertelme, vagy nem tudom
	}
	
	/**
	 * This function should be called whenever a Thing moves onto a Switch.
	 * @param s The Switch onto which the Thing has moved.
	 */
	public void onSwitch(Switch s)
	{	
		System.out.println("!Check what type of Thing this is!");
		//tries to activate the switch
		s.activate(this);
	}
	
	/**
	 * Called when a worker is on an EndTile. Does nothing, unless overridden.
	 * @param t	EndTile the worker is on
	 */
	public void onEndTile(EndTile t)
	{
		System.out.println("!Check what type of Thing this is!");
		return;
	}
	
	/**
	 *Getter of the attribute owner.
	 * @return The owner of the Thing
	 */
	public final Thing getOwner()
	{
		System.out.println("!Check what type of Thing this is!");
		return owner;
	}
	
	/**
	 * Getter of the Tile on which the Thing is at the given moment.
	 * @return The tile
	 */
	public final Tile getTile()
	{
		System.out.println("!Check what type of Thing this is!");
		return tile;
	}
	
	public void destroy()
	{
		System.out.println("!Check what type of Thing this is!");
		//Please implement
	}
}
