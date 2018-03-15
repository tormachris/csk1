package logic;

public class Hole extends Tile {
	
	private boolean open;
	
	/**
	 * This function is called when a Thing moves onto a Hole.
	 * @param t The Thing that moves.
	 * @return Shows if the Thing has been accepted.
	 */
	public  boolean Accept(Thing t)
	{	boolean accepted;
		//if the hole is open it destroys every thing that moves onto it
		if(open)
		{
			t.Destroy();
			accepted = true;
		}
		//if it is not open it will act just like a normal tile
		else
			accepted = super.Accept(t);
		
		return accepted;
	}
		
	/**
	 * Changes the state of the hole.
	 */
	public void ToggleOpen()
	{
		open = !open;
	}
	

}
