package logic;

/** Represents an Thing that cannot move on it's own and can activae switches.
 * They disappear on EndTiles.
 * @author Kristof
 * @version 1.0
 * @since 1.0
*/
public class Crate extends Thing{
	
	/**
	 * Constructor. Calles the super class' constructor.
	*/
	public Crate() {
		super();
	}
	
	/**
	 * Called when a Crate is hit by some thing.
	*/
	@Override
	public boolean hitBy(Thing t,Direction d,Thing o)
	{
		return true;
	}
	
	/**
	 * Called by an EndTile, when a crate occupies it.
	*/
	@Override
	public void onEndTile(EndTile t)
	{
		//Please implement this
	}

}
