package logic;

public class Switch extends Tile {
	
	private Hole hole;
	
	/**
	 * Acts like a normal Tile, however if the Thing gets accepted it will call the OnSwitch 
	 * function of the Thing 
	 * @param t The Thing that moves onto the Switch
	 * @return Shows whether the Thing has been accepted
	 */
	public boolean Accept(Thing t)
	{
		//calls the same function of its superclass
		boolean accepted = super.Accept(t);
		//if the Thing is allowed to move onto the Tile the Switch will trigger its OnSwitch()
		//function in order to inform the Thing that it has moved onto a Switch
		if(accepted)
			t.OnSwitch(this);
		return accepted;
	}
	
	/**
	 * 
	 * Does not activate if a random Thing is on it.
	 * @param t General Thing. (Like a Worker)
	 */
	public void Activate(Thing t)
	{
		return;
	}
	
	/**
	 * This function should be called when a Crate moves onto a switch. If this happens the
	 * Switch will toggle the state of the Hole linked to it.
	 * @param c The Crate that has moved onto the Switch.
	 */
	public void Activate(Crate c)
	{
		if(hole!=null)
			hole.ToggleOpen();
		else throw new NullPointerException("hole is null");
	}
	
	/**
	 * Setter of the attribute Hole.
	 * @param newvalue The new hole
	 */
	public void setHole(Hole newvalue)
	{
		if(newvalue!=null)
			hole=newvalue;
		else throw new NullPointerException("newvalue is null");
	}
}
