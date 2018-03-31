package logic;

/** Represents a Tile, that (if active) will kill any Thing that is on it.
 * Activated by the Map (Static) or a Switch (Dynamic)
 * @author Kristof
 * @version 1.0
 * @since 1.0
*/
public class Hole extends Tile {
	
	private Boolean open;
	
	/**
	 * With this constructor one can make a permanently open Hole.
	 * (By not attaching an instance to any switch and making isOpen true
	 * @param isOpen: default state
	 */
	public Hole(Boolean isOpen) {
		this.setOpen(isOpen);
	}
	
	/**
	 * Default constructor
	 */
	public Hole() {
		this.setOpen(Boolean.valueOf(false));
	}
	
	/**
	 * @return the open
	 */
	public Boolean getOpen() {
		return open;
	}

	/**
	 * @param open the open to set
	 */
	public void setOpen(Boolean open) {
		this.open = open;
	}

	/**
	 * This function is called when a Thing moves onto a Hole.
	 * @param t The Thing that moves.
	 * @return Shows if the Thing has been accepted.
	 */
	@Override
	public  boolean accept(Thing t){
		Boolean accepted;
		//if the hole is open it destroys every thing that moves onto it.
		if(open.booleanValue())
		{
			t.destroy();	//Destroying the Thing on the Hole.
			accepted = Boolean.TRUE;
		}
		//if it is not open it will act just like a normal tile.
		else {
			accepted = Boolean.valueOf(super.accept(t));
		}
		return accepted;
	}
		
	/**
	 * Changes the state of the hole.
	 */
	public void toggleOpen()
	{
		open = !open;	//Changing to the opposite state
		if(open.booleanValue() && this.getThing()!=null)
		{
			this.getThing().destroy();	//Destroying the Thing standing on the Hole when it is activated.
		}
	}
}
