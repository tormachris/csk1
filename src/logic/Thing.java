package logic;

import java.util.logging.*;

/**
 * Represents a general thing that moves on the map. Abstract.
 */
public abstract class Thing {
	private static final Logger LOGGER = Logger.getLogger(Thing.class.getName());
	private Thing owner;
	private Direction moving;
	private Tile tile;
	private Integer weight;

	/**
	 * Default Constructor
	 * 
	 * @param None
	 */
	public Thing(Integer w) {
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);

		// Let this be a warning, if a thing's owner is null, it is brand new!
		owner = null;
		tile = null; // Hanging in the aether.
		weight = w;
		LOGGER.log(Level.FINE, "Thing created");
	}

	/**
	 * @return the weight
	 */
	public Integer getWeight() {
		LOGGER.fine(() -> "Thing's weight gotten, it was: {}" + weight);
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
		LOGGER.fine(() -> "Thing's weight set to {}" + weight);
	}

	/**
	 * Collides the Thing with another Thing
	 * 
	 * @param t
	 *            The other Thing
	 * @return Indicates whether there has been movement due to the collision.
	 */
	public boolean collideWith(Thing t) {
		Boolean collided;
		// Hitting the other Thing with this towards this one's moving direction
		collided = Boolean.valueOf(t.hitBy(this, moving, this.owner));
		LOGGER.log(Level.FINE, "Thing collided with another: {0}", collided);
		return collided;

	}

	/**
	 * This function tries to move the Thing towards a given direction.
	 * 
	 * @param d
	 *            The given direction.
	 * @return The return value indicates if the moving towards d direction was
	 *         possible.
	 */
	public boolean move(Direction d) {
		LOGGER.log(Level.FINE, "Thing starting to move");
		// getting the neighbor of the current Tile and setting the moving attribute
		Tile newTile;
		newTile = tile.getNeighbour(d);
		moving = d;

		// checking whether the new Tile accepts the Thing
		Boolean moved = newTile.accept(this);
		if (moved) {
			LOGGER.log(Level.FINE, "Thing accepted by tile, performing the move");
			// if the Thing was accepted by the new Tile the Thing moves to it.
			tile.remove(this);
			tile = newTile;
			if (this.getClass().equals(Crate.class)) {
				LOGGER.log(Level.FINE, "Thing is a crate!");
			}
		}
		LOGGER.log(Level.FINE, "Thing moved:{0}", moved);
		return moved.booleanValue();
	}

	/**
	 * Called when hit by another thing.
	 */
	public boolean hitBy(Thing t, Direction d, Thing o) {
		if (t.equals(this)) {
			LOGGER.log(Level.SEVERE, "Somebody wants a Thing to hit itself");
			throw new IllegalArgumentException("Thing cannot hit itself");
		}
		Boolean hit;
		this.updateOwner(o); // While it is pushed by another Thing, it has to be the property of the Thing.
		if (this.getOwner() == null) {
			LOGGER.log(Level.FINE, "Owner of this thing is nullpointer! WUT?");
			throw new IllegalArgumentException("Null ptr in owner.");
		} else {
			Worker w = (Worker) this.getOwner();
			w.setForce(w.getForce() - this.getWeight() * this.getTile().getFrictionMod().getFriction());
			LOGGER.fine(() -> "Owner's new force value:{}" + w.getForce());
			Double d2 = 0.0;
			if (w.getForce().compareTo(d2) >= 0) {
				LOGGER.log(Level.FINE, "Owned has enough force to move this thing");
				hit = Boolean.valueOf(this.move(d)); // The direction is set, and the Thing is pushed into this
														// direction.
			} else {
				LOGGER.log(Level.FINE, "Owner does not have enough force to move this thing");
				hit = Boolean.FALSE;
			}
		}
		LOGGER.log(Level.FINE, "Thing hitBy:{0}", hit);
		return hit.booleanValue();
	}

	/**
	 * Updates the owner of the Thing
	 * 
	 * @param t
	 *            The new owner
	 * @return if the update was successful
	 */
	public final boolean updateOwner(Thing t) {
		this.owner = t;
		LOGGER.log(Level.FINER, "Thing got new owner");
		return true;
	}

	/**
	 * This function is called by a switch this Thing is on.
	 * 
	 * @param s
	 *            The Switch onto which the Thing has moved.
	 */
	public void onSwitch(Switch s) {
		LOGGER.log(Level.FINER, "Thing is onswitch, ignoring");
		return;
	}

	/**
	 * Called when a worker is on an EndTile. Does nothing, unless overridden.
	 * 
	 * @param t
	 *            EndTile the worker is on
	 */
	public void onEndTile(EndTile t) {
		LOGGER.log(Level.FINER, "Thing is onendtile, ignoring");
		return;
	}

	/**
	 * Getter of the attribute owner.
	 * 
	 * @return The owner of the Thing
	 */
	public final Thing getOwner() {
		return owner;
	}

	/**
	 * Getter of the Tile on which the Thing is at the given moment.
	 * 
	 * @return The tile
	 */
	public final Tile getTile() {
		return tile;
	}

	/**
	 * Setter of the Tile the Thing is on.
	 * 
	 * @param t
	 *            The tile
	 */
	public final void setTile(Tile t) {
		if (t != null)
			tile = t;
	}

	public void destroy() {
		LOGGER.log(Level.FINE, "Thing ded");
		return;
	}
}
