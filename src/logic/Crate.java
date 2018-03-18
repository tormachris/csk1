package logic;

/** Represents an Thing that cannot move on it's own and can activae switches.
 * They disappear on EndTiles.
 * @author Kristof
 * @version 1.0
 * @since 1.0
*/
public class Crate extends Thing{
	
	/**
	 * Constructor. Calls the super class' constructor.
	*/
	public Crate() {
		super();
	}
	
	/**
	 * Called when a Crate is hit by some thing.
	 * @param t The Thing that hits the Crate.
	 * @param d The Direction towards the Crate is hit
	 * @param o The owner of the Thing
	 * @return
	*/
	@Override
	public boolean hitBy(Thing t,Direction d,Thing o)
	{
		this.updateOwner(o);
		
		boolean moved = this.move(d);
		
		return moved;
	}
	
	/**
	 * Called by an EndTile, when a crate occupies it.
	 * @param t The EndTile the Crate moves onto.
	*/
	@Override
	public void onEndTile(EndTile t)
	{	//Incrementing the owner's points by 1
		Worker w = (Worker)this.getOwner();
		w.setPoints(w.getPoints() + 1);
		//Destroying the Crate
		destroy();
	}


}
