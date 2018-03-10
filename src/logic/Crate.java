package logic;

public class Crate extends Thing{
	
	public boolean HitBy(Thing t,Direction d,Thing o)
	{
		return true;
	}
	
	public void OnEndTile(EndTile t)
	{
		
	}
	
	public boolean UpdateOwner(Thing t)
	{
		return true;
	}

}
