package logic;

public class Worker extends Thing{
	
	private int points;
	
	public boolean HitBy(Thing t,Direction d,Thing o)
	{
		return true;
	}

	public void Destroy()
	{
		
	}
	
	public void OnEndTile(EndTile t)
	{
		
	}
	
	public boolean UpdateOwner(Thing t)
	{
		return true;
	}
}
