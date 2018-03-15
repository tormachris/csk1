package logic;

public class Thing {
	
	private Thing owner;
	private Direction moving;
	private Tile tile;
	
	/**
	 * Collides the Thing with another Thing
	 * @param t The other Thing
	 * @return Indicates whether there has been movement due to the collision.
	 */
	public boolean CollideWith(Thing t)
	{
		boolean collided;
		//Hitting the other Thing with this towards this one's moving direction
		collided = t.HitBy(this, moving , this.owner);
		return collided;
		
	}
	
	/**
	 * This function tries to move the Thing towards a given direction. 
	 * @param d The given direction.
	 * @return The return value indicates if the moving towards d direction was possible.
	 */
	public boolean Move(Direction d)
	{	
		//getting the neighbor of the current Tile and setting the moving attribute
		Tile newTile;
		newTile = tile.GetNeighbor(d);
		moving = d;
		
		//checking whether the new Tile accepts the Thing
		boolean moved = newTile.Accept(this);
		if(moved)
		{
			//if the Thing was accepted by the new Tile the Thing moves to it.
			tile.Remove(this);
			tile = newTile;
		}
		
		return moved;
	}
	
	public boolean HitBy(Thing t,Direction d, Thing o)
	{
		return true;
	}
	
	/**
	 * 
	 * @param t
	 * @return
	 */
	public final boolean UpdateOwner(Thing t)
	{
		this.owner = t;
	return true;	//valami ellenőrzés kéne ide hogy legyen értelme a visszatérési értéknek de nemtom
	}
	public void Destroy()
	{
		
	}
	
	/**
	 * This function should be called whenever a Thing moves onto a Switch.
	 * @param s The Switch onto which the Thing has moved.
	 */
	public void OnSwitch(Switch s)
	{	
		//tries to activate the switch
		s.Activate(this);
	}
	
	public void OnEndTile(EndTile t)
	{
		
	}
	
	/**
	 *Getter of the attribute owner.
	 * @return The owner of the Thing
	 */
	public final Thing GetOwner()
	{
		return owner;
	}
	
	/**
	 * Getter of the Tile on which the Thing is at the given moment.
	 * @return The tile
	 */
	public final Tile GetTile()
	{
		return tile;
	}
}
