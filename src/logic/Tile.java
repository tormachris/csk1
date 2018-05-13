package logic;
import java.io.Serializable;
import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Represents a general Tile that is on the Map.
 */
public class Tile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -604892624799118263L;
	private static final Logger LOGGER = Logger.getLogger( Tile.class.getName() );
	private Thing thing;
	private EnumMap<Direction, Tile> nexttiles;
	private FrictionModifier frictionmodifier;
	
	
	/**
	 * Whenever a Thing tries to move onto the Tile this function is called. 
	 * @param t the Thing that tries to move
	 * @return Indicates whether the Thing has been accepted
	 */
	public boolean accept(Thing t)
	{
		Boolean accepted;
		//if the Tile is empty it will accept
		if(thing == null) {
			LOGGER.log(Level.FINEST, "Tile was empty, accepting automagically");
			accepted = Boolean.TRUE;
		}
		//if it is not empty it will make the moving Thing collide with the Thing that is
		//currently on the Tile 
		else {
			LOGGER.log(Level.FINEST, "Tile was not empty, colliding it with the thing I contain.");
			accepted = Boolean.valueOf(t.collideWith(thing));
		}
		
		//if it has been accepted then it refreshes the thing attribute
		if(accepted.booleanValue())
			thing = t;
		//returns with the acceptance anyway
		LOGGER.log(Level.FINE, "Tile accepted thing:{0}",accepted);
		return accepted.booleanValue();
	}
	/**
	 * Default constructor, initializes the Map and the "thing" variable.
	 */
	public Tile() {
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		
		nexttiles=new EnumMap<>(Direction.class);
		thing=null;
		frictionmodifier = new FrictionModifier();
		LOGGER.log(Level.FINEST, "New Tile created");
	}	
	/**
	 * Removes the Thing from the tile.
	 * @param t The Thing that is wanted to be removed.
	 */
	public void remove(Thing t)
	{
		//checking if the Thing is on this Tile
		if(t.equals(thing)) 
			//if that's true the Thing will be removed
			thing = null;
		LOGGER.log(Level.FINE, "Thing removed from tile");
	}
	
	/**
	 * Returns the Tile that is in specified Direction from this Tile.
	 * @param d The Direction in which the neighbour Tile should be returned.
	 * @return The neighbour Tile in the given Direction
	 */
	public Tile getNeighbour(Direction d)
	{
		return nexttiles.get(d);
	}
	
	/**
	 * Sets a Tile as a neighbour of this Tile.
	 * @param d Shows in which Direction the other Tile is from this Tile 
	 * @param t The other Tile that should be placed as a neighbour.
	 */
	public void setNeighbour(Direction d, Tile t)
	{
		//putting the tile in the "nexttiles" Map if there is a valid d passed.
		if(d==null) {
			LOGGER.log(Level.FINEST, "Passed direction to Tile is null");
			throw new NullPointerException("d cannot be null");
			}
		nexttiles.put(d, t);
	}
	/**
	 * @return the thing
	 */
	public Thing getThing() {
		return thing;
	}
	/**
	 * @param thing the thing to set
	 */
	public void setThing(Thing thing) {
		this.thing = thing;
		if (thing!=null)thing.setTile(this); //We need this for the init, so the Thing will easily know his Tile.
		else {
			LOGGER.log(Level.FINEST, "Passed thing is null (logic.Tile)");
		}
	}
	/**
	 * Getter of the frictionmodifier
	 * @return the frictionmodifier
	 */
	public FrictionModifier getFrictionMod() {
		return frictionmodifier;
	}
	/**
	 * Setter of the frictionmodifier
	 * @param frictionmod the frictionmodifier to set
	 */
	public void setFrictionMod(FrictionModifier frictionmod) {
		this.frictionmodifier = frictionmod;
	}

}
