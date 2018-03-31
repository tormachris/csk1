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
	 * Called when a Crate is hit by some thing.
	 * @param t The Thing that hits the Crate.
	 * @param d The Direction towards the Crate is hit
	 * @param o The owner of the Thing
	 * @return
	*/
	@Override
	public boolean hitBy(Thing t,Direction d,Thing o)
	{	
		
		this.updateOwner(o);// Updating the owner in case of scoring
		if(this.getOwner()==null) 
			throw new IllegalArgumentException("Null ptr in owner.");
		else
		{
		Worker w=(Worker)this.getOwner();
		w.setForce(w.getForce()-this.getWeight()*this.getTile().getFriction());
		Double d2=Double.valueOf(0);
		if(w.getForce().compareTo(d2)>=0) 
		{
		Boolean moved=Boolean.valueOf(this.move(d));
		return moved.booleanValue();
		}
		else
			return false;
		}
		
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
		this.destroy();
	}
	
	/**
	 * Called when this object is destroyed. (NOT A DESTRUCTOR)
	*/
	@Override
	public void destroy() {
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
