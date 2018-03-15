package logic;

import java.util.ArrayList;
import java.util.List;

public class Map implements Steppable {
	
	private int ticksRemain;
	private int defaultTime;
	private List<Crate> crates = new ArrayList<Crate>();
	private List<Worker> workers = new ArrayList<Worker>();
	private List<Tile> tiles = new ArrayList<Tile>();
	
	public void StartMap()
	{
		
		
	}
	
	public void EndMap()
	{
		
	}
	
	/**
	 * Resets the remaining time to the default time of a game.
	 */
	public void ResetTimer()
	{
		//reseting the time
		ticksRemain = defaultTime;
		return;
	}
	
	/**
	 * Function which is called at every tick. If there are no crates, only one worker or no more
	 * time left it will end the map. Else it will shorten the remaining time by one second.
	 */
	public void Step()
	{
		//checking if there is any reason to end the map
		if(crates.size() == 0 || workers.size() != 2 || ticksRemain == 0)
			EndMap();
		else
			//shortening the remaining time by 1s
			ticksRemain--;
		
		return;
	}

}
