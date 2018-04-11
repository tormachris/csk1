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
				connectTiles(input[1], input[2], input[3]);
				break;
			case "newthing":
				newThing(input[1]);
				break;
			case "toggletimer":
				toggleTimer();
				break;
			case "gettimerstate":
				break;
			case "putfrictionmodifierontile":
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
	
	private void toggleTimer() {
		// TODO Auto-generated method stub
		
	}

	private void newThing(String string) {
		// TODO Auto-generated method stub
		
	}

	private void connectTiles(String string, String string2, String string3) {
		// TODO Auto-generated method stub
		
	}

	private void newTile(String type) {
		switch(type) {
		case "endtile" : 
			tiles.put(tiles.size(), new EndTile());
			break;
		case "wall" :
			tiles.put(tiles.size(), new Wall());
			break;
		case "hole" :
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
