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
		System.out.println("!This is a Switch.");
		//calls the same function of its superclass
		Boolean accepted = Boolean.valueOf(super.accept(t));
		//if the Thing is allowed to move onto the Tile the Switch will trigger its OnSwitch()
		//function in order to inform the Thing that it has moved onto a Switch
		if(accepted)
		{
			System.out.println(">[:Thing].onSwitch(this)");
			t.onSwitch(this);
		}
		System.out.println("<[:Switch].accept(t): " + accepted.toString());
		return accepted.booleanValue();
	}
	
	/**
	 * 
	 * Does not activate if a random Thing is on it.
	 * @param t General Thing. (Like a Worker)
	 */
	public void activate(Thing t)
	{
		System.out.println("<[:Thing].activate(t): void");
		return;
	}
	
	/**
	 * This function should be called when a Crate moves onto a switch. If this happens the
	 * Switch will toggle the state of the Hole linked to it.
	 * @param c The Crate that has moved onto the Switch.
	 */
	public void activate(Crate c)
	{
		if(hole!=null) {
			System.out.println(">[:Hole].toggleOpen()");
			hole.toggleOpen();
		}
		else throw new NullPointerException("hole is null");
	}
	
	/**
	 * Setter of the attribute Hole.
	 * @param newvalue The new hole
	 */
	public void setHole(Hole newvalue)
	{
		if(newvalue!=null) {
			System.out.println("#hole=newvalue");
			hole=newvalue;
		}
		else throw new NullPointerException("newvalue is null");
	}
	
	/**
	 * Gets the current hole.
	 */
	public Hole getHole() {
		System.out.println("<[:Hole].getHole(): hole");
		return hole;
	}
	
	/**
	 * Constructor. Sets the hole.
	 */
	Switch(Hole h){
		System.out.println(">[:Hole].setHole(h)");
		this.setHole(h);
	}
	
	/**
	 * Default constructor. Makes the switch essentially useless, unless one manually sets the hole property.
	 */
	Switch(){
		hole=null;
		System.out.println("!!!SET THE VALUE OF hole");
	}
}
