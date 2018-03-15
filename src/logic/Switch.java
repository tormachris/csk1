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
	 * @param t
	 */
	public void Activate(Thing t)
	{
		
	}
	
	/**
	 * This function should be called when a Crate moves onto a switch. If this happens the
	 * Switch will toggle the state of the Hole linked to it.
	 * @param c The Crate that has moved onto the Switch.
	 */
	public void Activate(Crate c)
	{
		hole.ToggleOpen();
	}
	
	/**
	 * Setter of the attribute Hole.
	 * @param newvalue The new hole
	 */
	public void SetHole(Hole newvalue)
	{
		hole = newvalue;
	}
}
