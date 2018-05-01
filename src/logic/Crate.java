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
	public Crate(Integer w) {
		super(w);
	}
	
	
	/**
	 * Called by an EndTile, when a crate occupies it.
	 * @param t The EndTile the Crate moves onto.
	*/
	@Override
	public void onEndTile(EndTile t)
	{	
		Worker w = (Worker)this.getOwner(); 
		w.setPoints(w.getPoints() + 1); //Destroying the Crate
		int id = Commander.getInstance().getID(this);
		if(id != -1)
			System.out.println("Crate " + id + " : onendtile");
		this.destroy();
	}
	
	/**
	 * Called when this object is destroyed. (NOT A DESTRUCTOR)
	*/
	@Override
	public void destroy() {
		int id = Commander.getInstance().getID(this);
		if(id != -1)
			System.out.println("Crate " + id + " : died");
		Game.getInstance().getCurrentmap().removeCrate(this);
	}
	
	/**
	 * This function is called by a switch this Thing is on.
	 * @param s The Switch onto which the Thing has moved.
	 */
	@Override
	public void onSwitch(Switch s)
	{
		s.activate();	//Activate the switch tile that contains the Crate
	}

}
