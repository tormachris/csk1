package logic;

/** Represents an Thing that cannot move on it's own and can activae switches.
 * They disappear on EndTiles.
 * @author Kristof
 * @version 1.0
 * @since 1.0
*/
public class Crate extends Thing{
	
	/**
	 * Constructor. Calls the super class' constructor.
	*/
	public Crate() {
		super();
		System.out.println("!New Crate created.");
	}
	
	/**
	 * Called when a Crate is hit by some thing.
	 * @param t The Thing that hits the Crate.
	 * @param d The Direction towards the Crate is hit
	 * @param o The owner of the Thing
	 * @return
	*/
	@Override
	public boolean hitBy(Thing t,Direction d,Thing o)
	{
		System.out.println(">[:Crate].updateOwner(o)");
		this.updateOwner(o);
		
		System.out.println(">[:Crate].move(d)");
		Boolean moved=Boolean.valueOf(this.move(d));
		System.out.println("<[:Crate].hitBy(t,d,o): " + moved.toString());
		return moved.booleanValue();
	}
	
	/**
	 * Called by an EndTile, when a crate occupies it.
	 * @param t The EndTile the Crate moves onto.
	*/
	@Override
	public void onEndTile(EndTile t)
	{	//Incrementing the owner's points by 1
		System.out.println(">[:Crate].getOwner()");
		Worker w = (Worker)this.getOwner();
		System.out.println("!Worker's points increased.");
		w.setPoints(w.getPoints() + 1);
		//Destroying the Crate
		System.out.println(">[:Crate].destroy()");
		this.destroy();
	}
	
	/**
	 * Called when this object is destroyed. (NOT A DESTRUCTOR)
	*/
	@Override
	public void destroy() {
		
		Game.getInstance().getCurrentmap().removeCrate(this);
		
		System.out.println("<[:Crate].destroy():void");
	}
	
	/**
	 * This function is called by a switch this Thing is on.
	 * @param s The Switch onto which the Thing has moved.
	 */
	@Override
	public void onSwitch(Switch s)
	{	
		System.out.println("!Check what type of Thing this is!");
		//tries to activate the switch
		System.out.println(">[:Switch].activate(this)");
		s.activate();
		System.out.println("<[:Thing].onSwitch(t): void");
	}

}
