package logic;

public class Switch extends Tile {
	
	private Hole hole;
	
	/**
	 * Acts like a normal Tile, however if the Thing gets accepted it will call the OnSwitch 
	 * function of the Thing 
	 * @param t The Thing that moves onto the Switch
	 * @return Shows whether the Thing has been accepted
	 */
	@Override
	public boolean accept(Thing t)
	{
		//calls the same function of its superclass
		Boolean accepted = Boolean.valueOf(super.accept(t));
		//if the Thing is allowed to move onto the Tile the Switch will trigger its OnSwitch()
		//function in order to inform the Thing that it has moved onto a Switch
		if(accepted)
			t.onSwitch(this);
		return accepted.booleanValue();
	}
	
	/**
	 * This function should be called when a Crate moves onto a switch. If this happens the
	 * Switch will toggle the state of the Hole linked to it.
	 * @param c The Crate that has moved onto the Switch.
	 */
	public void activate()
	{
		hole.toggleOpen();
	}
	
	/**
	 * Setter of the attribute Hole.
	 * @param newvalue The new hole
	 */
	public void setHole(Hole newvalue)
	{
		if(newvalue!=null) {		//Checking for valid value
			hole=newvalue;	
		}
		else throw new NullPointerException("newvalue is null");
	}
	
	/**
	 * Gets the current hole.
	 */
	public Hole getHole() {
		return hole;
	}
	
	/**
	 * Constructor. Sets the hole.
	 */
	Switch(Hole h){
		this.setHole(h);
	}
}
