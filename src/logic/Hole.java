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
		System.out.println("!New Hole instance created");
		this.setOpen(isOpen);
	}
	
	/**
	 * Default constructor
	 */
	public Hole() {
		System.out.println("!New Hole instance created");
		this.setOpen(Boolean.valueOf(false));
	}
	
	/**
	 * @return the open
	 */
	public Boolean getOpen() {
		System.out.println("<[:Hole].getOpen(): " + open.toString());
		return open;
	}

	/**
	 * @param open the open to set
	 */
	public void setOpen(Boolean open) {
		this.open = open;
		System.out.println("<[:Hole].setOpen(open):void");
	}

	/**
	 * This function is called when a Thing moves onto a Hole.
	 * @param t The Thing that moves.
	 * @return Shows if the Thing has been accepted.
	 */
	@Override
	public  boolean accept(Thing t){
		System.out.println("!This is a Hole");
		Boolean accepted;
		//if the hole is open it destroys every thing that moves onto it
		if(open.booleanValue())
		{
			System.out.println(">[:Thing].destroy()");
			t.destroy();
			accepted = Boolean.TRUE;
			System.out.println("#accepted= "+ accepted.toString());
		}
		//if it is not open it will act just like a normal tile
		else {
			accepted = Boolean.valueOf(super.accept(t));
			System.out.println("#accepted= "+ accepted.toString());
		}
		System.out.println("<[:Hole].accept(t): " + accepted.toString());
		return accepted;
	}
		
	/**
	 * Changes the state of the hole.
	 */
	public void toggleOpen()
	{
		System.out.println("#open=" + open.toString());
		open = !open;
		System.out.println("<[:Hole].toggleOpen():void");
	}
}
