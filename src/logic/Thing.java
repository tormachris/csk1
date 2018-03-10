package logic;

public class Thing {
	
	private Thing owner;
	private Direction moving;
	private Tile tile;
	
	public void CollideWith(Thing t)
	{
		
	}
	
	public boolean Move(Direction d)
	{
		return true;
	}
	
	public boolean HitBy(Thing t,Direction d, Thing o)
	{
		return true;
	}
	
	public boolean UpdateOwner(Thing t)
	{
	return true;	
	}
	public void Destroy()
	{
		
	}
	
	public void OnSwitch(Switch s)
	{
		
	}
	
	public void OnEndTile(EndTile t)
	{
		
	}
	

}
