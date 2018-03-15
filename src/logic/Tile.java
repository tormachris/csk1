package logic;

import java.util.*;

public class Tile {
	
	private Thing thing;
	private AbstractMap<Direction, Tile> nexttiles;
	
	/**
	 * Whenever a Thing tries to move onto the Tile this function is called. 
	 * @param t the Thing that tries to move
	 * @return Indicates whether the Thing has been accepted
	 */
	public boolean Accept(Thing t)
	{
		boolean accepted;
		//if the Tile is empty it will accept
		if(thing == null)
			accepted = true;
		
		//if it is not empty it will make the moving Thing collide with the Thing that is
		//currently on the Tile 
		else
			accepted = t.CollideWith(thing);
		
		//if it has been accepted then it refreshes the thing attribute
		if(accepted)
			thing = t;
		
		//returns with the acceptence anyway
		return accepted;
	}
	public Tile() {
		nexttiles=new HashMap<Direction, Tile>();
	}
	/**
	 * Removes the Thing from the tile.
	 * @param t The Thing that is wanted to be removed.
	 */
	public void Remove(Thing t)
	{
		//checking if the Thing is on this Tile
		if(t.equals(thing))
			//if that's true the Thing will be removed
			thing = null;
	}
	
	/**
	 * Returns the Tile that is in specified Direction from this Tile.
	 * @param d The Direction in which the neighbor Tile should be returned.
	 * @return The neighbor Tile in the given Direction
	 */
	public Tile GetNeighbor(Direction d)
	{
		return nexttiles.get(d);
	}
	
	/**
	 * Sets a Tile as a neighbor of this Tile.
	 * @param d Shows in which Direction the other Tile is from this Tile 
	 * @param t The other Tile that should be placed as a neighbor.
	 */
	public void SetNeighbor(Direction d, Tile t)
	{
		//putting the tile in the nexttiles AbstractMap
		nexttiles.put(d, t);
	}

}
