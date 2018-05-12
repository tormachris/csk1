package logic;

import java.util.logging.Logger;

/** Represents an Tile that "Consumes" Crates.
 * @author Kristof
 * @version 1.0
 * @since 1.0
*/
public class EndTile extends Tile {
	private static final Logger LOGGER = Logger.getLogger( EndTile.class.getName() );
	/**
	 * Acts like a normal Tile, however if the Thing gets accepted it will call the OnEndTile 
	 * function of the Thing 
	 * @param t The Thing that moves onto the EndTile
	 * @return Shows whether the Thing has been accepted
	 */
	@Override
	public boolean accept(Thing t)
	{
		boolean accepted = super.accept(t);
		//if the Thing is allowed to move onto the Tile the EndTile will trigger its OnEndTile()
		//function in order to inform the Thing that it has moved onto an EndTile.
		if(accepted)
			t.onEndTile(this);
		return accepted;
	}

}
