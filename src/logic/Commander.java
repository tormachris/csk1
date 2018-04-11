/**
 * 
 */
package logic;

import java.io.*;
import java.util.*;

/** Interprets commands and executes them.
 * Just like a command line program :)
 * @author Kristof
 * @version 1.0
 * @since 1.0
*/
public final class Commander {
	
	private HashMap<Integer, Tile> tiles = new HashMap<Integer, Tile>();
	private HashMap<Integer, Thing> things = new HashMap<Integer, Thing>();

	public String scan() {
		String toreturn="";
		
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        try {
			toreturn = stdin.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return toreturn.toLowerCase();	//we are not case sensitive
	}
	
	public void interpreter() {
		//First, we break up the raw input into command and arguments.
		String rawin=scan();
		String[] input=rawin.split(" ");
		while (true) { //Let's process a bunch of inputs.
			switch (input[0]) {
		
			case "newmap":
				break;
			case "newtile":
				newTile(input[1].toLowerCase());
				break;
			case "connecttiles":
				connectTiles(input[1], input[2].toLowerCase(), input[3]);
				break;
			case "newthing":
				newThing(input[1].toLowerCase(), input[2]);
				break;
			case "toggletimer":
				toggleTimer();
				break;
			case "gettimerstate":
				break;
			case "putfrictionmodifierontile":
				putFrictionModifieronTile(input[1].toLowerCase(), input[2]);
			break;
			case "gettilestate":
				break;
			case "moveworker":
				break;
			case "step":
			break;
			case "getgamestate":
				break;
			case "xmlprepare":
				xmlinterpreter();	//Change to XML processer state.
				break;
			case "xmlover"://This command is invalid in this state, but let's leave this here for accounting purposes.
				break;
			default:
				break;
			}
		}
	}
	
	private void putFrictionModifieronTile(String type, String tileid) {
	if(tiles.get(Integer.valueOf(tileid)) != null)
		if(type.equals("honey"))
			tiles.get(Integer.valueOf(tileid)).setFrictionMod(new Honey());
		else
			tiles.get(Integer.valueOf(tileid)).setFrictionMod(new Oil());
		
	}

	private void toggleTimer() {
		//is this right?
		if(Timer.getInstance().getState() == Thread.State.RUNNABLE)
			Timer.getInstance().interrupt();
		else 
			Timer.getInstance().start();
	}

	private void newThing(String type, String force) {
		switch(type) {
			case "worker" : 
				if(force != null)
					things.put(things.size(), new Worker(Integer.valueOf(force)));
				else
					things.put(things.size(), new Worker(100)); //what's the default weight?
				break;
			default: 
				things.put(things.size(), new Crate(100));
				break;
		}
		
	}

	private void connectTiles(String t1, String d, String t2) {
		Tile tile1 = tiles.get(Integer.valueOf(t1));
		Tile tile2 = tiles.get(Integer.valueOf(t2));
		if(tile1 == null || tile2 == null)
		{
			System.out.println("Tiles with the given IDs do not exist");
			return;
		}
		else 
			switch(d) 
			{
			case "north": 
				tile1.setNeighbour(Direction.NORTH, tile2);
				tile2.setNeighbour(Direction.SOUTH, tile1);
				break;
			case "south":
				tile1.setNeighbour(Direction.SOUTH, tile2);
				tile2.setNeighbour(Direction.NORTH, tile1);
				break;
			case "east": 
				tile1.setNeighbour(Direction.EAST, tile2);
				tile2.setNeighbour(Direction.WEST, tile1);
				break;
			case "west": 
				tile1.setNeighbour(Direction.WEST, tile2);
				tile2.setNeighbour(Direction.EAST, tile1);
				break;
			default:
				System.out.println("No such direction");
				break;
			}
		
	}

	private void newTile(String type) {
		switch(type) {
			case "endtile": 
				tiles.put(tiles.size(), new EndTile());
				break;
			case "wall":
				tiles.put(tiles.size(), new Wall());
				break;
			case "hole":
				tiles.put(tiles.size(), new Hole());
				break;
			default: 
				System.out.println("Type does not exists");
				return;
		}
		System.out.println(tiles.size() - 1);
	}

	public void xmlinterpreter() {
		
	}
}
