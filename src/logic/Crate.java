package logic;

public class Crate extends Thing{
	
	public Crate() {
		super();
	}
	
	public boolean HitBy(Thing t,Direction d,Thing o)
	{
		return true;
	}
	
	public void OnEndTile(EndTile t)
	{
		
	}

}
