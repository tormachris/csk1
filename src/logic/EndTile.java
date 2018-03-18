package logic;

/** Represents an Tile that "Consumes" Crates.
 * @author Kristof
 * @version 1.0
 * @since 1.0
*/
public class EndTile extends Tile {
	
	/**
	 * Acts like a normal Tile, however if the Thing gets accepted it will call the OnEndTile 
	 * function of the Thing 
	 * @param t The Thing that moves onto the EndTile
	 * @return Shows whether the Thing has been accepted
	 */
	@Override
	public boolean accept(Thing t)
	{
		System.out.println("!This is an EndTile");
		//calls the same function of its superclass
		boolean accepted = super.accept(t);
		//if the Thing is allowed to move onto the Tile the EndTile will trigger its OnEndTile()
		//function in order to inform the Thing that it has moved onto a Switch
		if(accepted)
			t.onEndTile(this);
		System.out.println("<[:EndTile].accept(t): " + accepted);
		return accepted;
	}

}
