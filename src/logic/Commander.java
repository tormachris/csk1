/**
 * 
 */
package logic;


import java.util.*;


/**
 * Interprets commands and executes them. Just like a command line program :)
 * 
 * @author Kristof
 * @version 1.0
 * @since 1.0
 */
public final class Commander {

	private HashMap<Integer, Tile> tiles = new HashMap<>();
	private HashMap<Integer, Thing> things = new HashMap<>();
	private static Commander instance;
	private Boolean verboseMode;
	private Boolean XMLMode;
	
	Scanner stdin = null;

	private Commander() {
		verboseMode = Boolean.valueOf(false);
		XMLMode = Boolean.valueOf(false);
	}

	public static Commander getInstance() {
		if (instance == null) {
			instance = new Commander();
		}
		return instance;
	}
	public int getID(Thing t) {
		int i;
		for(i=0; i<things.size();i++) {
			if(t.equals(this.things.get(i)))
				return i;
		}
		return -1;
	}
	public int getTileID(Tile t) {
		int i;
		for(i=0; i<tiles.size();i++) {
			if(t.equals(this.tiles.get(i)))
				return i;
		}
		return -1;
	}


	public void interpreter() {
		stdin=new Scanner(System.in);
		// First, we break up the raw input into command and arguments.
	while(stdin.hasNextLine()) {
		String rawin = stdin.nextLine().toLowerCase();
		String[] input = rawin.split(" ");
		if (input[0] != "forceexit") 
			switch (input[0]) {
			case "newmap":
				newMap();
				break;
			case "newtile":
				newTile(input[1].toLowerCase());
				break;
			case "connecttiles":
				if(input.length>=4)
					connectTiles(input[1], input[2].toLowerCase(), input[3]);
				break;
			case "newthing":
				if(input.length>=4)
					newThing(input[1].toLowerCase(), input[2], input[3]);
				break;
			case "toggletimer":
				toggleTimer();
				break;
			case "gettimerstate":
				getTimerState();
				break;
			case "putfrictionmodifierontile":
				if(input.length>=3)
				putFrictionModifieronTile(input[1].toLowerCase(), input[2]);
				break;
			case "gettilestate":
				if(input.length>=2)
				getTileState(input[1]);
				break;
			case "moveworker":
				if(input.length>=3)
				moveWorker(input[1], input[2].toLowerCase());
				break;
			case "step":
				step();
				break;
			case "getgamestate":
				getGameState();
				break;
			case "xmlprepare":
				xmlinterpreter(); // Change to XML processor state.
				break;
			case "xmlover":// This command is invalid in this state, but let's leave this here for
							// accounting purposes.
				break;
			case "verbosemode":
				verboseMode = Boolean.valueOf(true);
				break;
			case "setholestate":
				if(input.length>=3)
					setholestate(input[1],input[2]);
				break;
			case "connectswitchto":
				if(input.length>=3)
					connectSwitchTo(input[1],input[2]);
				break;
			default:
				break;
			}
		else
			break;
		} stdin.close();
		return;
	}

	private void newMap() {
		Map m = new Map();
		Game.getInstance().addMap(m);
		Game.getInstance().start(m);
	}

	private void step() {
		Timer.getInstance().tick();
	}

	private void getTimerState() {
		System.out.println(Timer.getInstance().GetRunning());
	}

	public boolean getVerboseMode() {
		return verboseMode.booleanValue();
	}
	private void getGameState() {
		StringBuilder sb=new StringBuilder();
		sb.append("Workers\n");
		Thing t=null;
		for(Integer i=0;i<things.size();i++) {
			t=things.get(i);
			if(t instanceof Worker && Game.getInstance().getCurrentmap().getWorkers().contains(t)) {
				Double d=((Worker)t).getForce();
				int x= d.intValue();
				sb.append(i.toString() + " " + x + "\n");}
		}
		sb.append("Crates\n");
		for(Integer i=0;i<things.size();i++) {
			t=things.get(i);
			if(t instanceof Crate && Game.getInstance().getCurrentmap().getCrates().contains(t)) 
				sb.append(i.toString() + "\n");
		}
		sb.append("Maps\n");
		sb.append(Game.getInstance().getNumofMaps() + "\n");
		System.out.print(sb.toString());
	}
	private void setholestate(String id, String state) {
		Tile tile=tiles.get(Integer.valueOf(id));
		Boolean boolstate=Boolean.valueOf(state.compareTo("open")==0 || state.compareTo("1")==0 || state.compareTo("true")==0);
		if(tile instanceof Hole){
			Hole hTile=(Hole)tile;
			hTile.setOpen(boolstate);
		}
	}
	private void connectSwitchTo(String id, String id2) {
		Tile tile=tiles.get(Integer.valueOf(id));
		Tile tile2=tiles.get(Integer.valueOf(id2));
		Hole hTile=null;
		Switch sTile=null;
		if(tile instanceof Switch)
			sTile=(Switch)tile;
		if(tile2 instanceof Hole)
			hTile=(Hole)tile2;
		if(hTile!=null && sTile!=null)
			sTile.setHole(hTile);
	}
	private void moveWorker(String workerid, String d) {
		if (things.get(Integer.valueOf(workerid)) == null
				|| things.get(Integer.valueOf(workerid)).getClass() != Worker.class) {
			return;
		}
		Boolean moved = false;
		moved = things.get(Integer.valueOf(workerid)).move(Direction.valueOf(d.toUpperCase(Locale.ENGLISH)));
		System.out.println(moved.toString());
	}

	private void getTileState(String id) {
		if (tiles.get(Integer.valueOf(id)) == null) {
			return;
		}
		System.out.println(id + " " + tiles.get(Integer.valueOf(id)).getClass().toString() + " "
				+ tiles.get(Integer.valueOf(id)).getFrictionMod().getClass().toString());
	}

	private void putFrictionModifieronTile(String type, String tileid) {
		if (tiles.get(Integer.valueOf(tileid)) != null)
			if (type.equals("honey"))
				tiles.get(Integer.valueOf(tileid)).setFrictionMod(new Honey());
			else
				tiles.get(Integer.valueOf(tileid)).setFrictionMod(new Oil());

	}

	private void toggleTimer() {
		if(Timer.getInstance().GetRunning()) {
			Timer.getInstance().SetRunning(false);
		}
		else
			Timer.getInstance().SetRunning(true);
	}

	private void newThing(String type, String tileid, String force) {
		if (!tiles.containsKey(Integer.valueOf(tileid))) {
			return;
		}
		if (type.compareTo("worker")==0)
		{
			if (force != null)
				things.put(things.size(), new Worker(Integer.valueOf(force)));
			else
				things.put(things.size(), new Worker(2)); // what's the default weight?
			Game.getInstance().getCurrentmap().addWorker((Worker) things.get(things.size() - 1));
		}
		else
		{
			things.put(things.size(), new Crate(1));
			Game.getInstance().getCurrentmap().addCrate((Crate) things.get(things.size() - 1));
		}
		tiles.get(Integer.valueOf(tileid)).accept(things.get(things.size() - 1));
		things.get(things.size() - 1).setTile(tiles.get(Integer.valueOf(tileid)));
		if(!XMLMode)
		System.out.println(things.size() - 1);

	}

	private void connectTiles(String t1, String d, String t2) {
		Tile tile1 = tiles.get(Integer.valueOf(t1));
		Tile tile2 = tiles.get(Integer.valueOf(t2));
		if (tile1 == null || tile2 == null) {
			return;
		} else {
			tile1.setNeighbour(Direction.valueOf(d.toUpperCase(Locale.ENGLISH)) ,tile2);
			tile2.setNeighbour(Direction.getOpposite(Direction.valueOf(d.toUpperCase(Locale.ENGLISH))), tile1);
		}
	}

	private void newTile(String type) {
		switch (type) {
		case "endtile":
			tiles.put(tiles.size(), new EndTile());
			break;
		case "wall":
			tiles.put(tiles.size(), new Wall());
			break;
		case "hole":
			tiles.put(tiles.size(), new Hole());
			break;
		case "switch":
			tiles.put(tiles.size(), new Switch(new Hole()));
		default:
			tiles.put(tiles.size(), new Tile());
			break;
		}
		Game.getInstance().getCurrentmap().addTile(tiles.get(tiles.size() - 1));
		if(!XMLMode)
		System.out.println(tiles.size() - 1);
	}

	public void xmlinterpreter() {
		  XMLMode = Boolean.valueOf(true);
		  StringBuilder sb = new StringBuilder();
		  String inline = "";
		  String[] params;
		  while ( stdin.hasNext()) {			  
				  switch (stdin.nextLine()) {
				  case "<!ELEMENT map>": 
					  System.out.println("mapot gyartok");
					  this.tiles = new HashMap<>();
					  this.things = new HashMap<>();
					  newMap();
					  newTile(" ");
					  break;
				  case  "<!ELEMENT tile(id, type, connectsto)>":
					  for(int i = 0; i < 3; ++i)
					  {
						  inline = stdin.nextLine();
						  sb.append(inline);
					  }
					  params = sb.toString().split("[(]|[)]");
					  newTile(params[3]);
					  connectTiles(params[1], "south", params[5]);
					  break;
				  case "<!ELEMENT thing(type, tileid, force)>":
					  for(int i = 0; i < 3; ++i)
					  {
						  inline = stdin.nextLine();
						  sb.append(inline);
					  }
					  params = sb.toString().split("[(]|[)]");
					  newThing(params[1], params[3], params[5]);
					  break;
				  case "<!ELEMENT frictionmoifier (tileid, type)>":
					  for(int i = 0; i < 2; ++i)
					  {
						  inline = stdin.nextLine();
						  sb.append(inline);
					  }
					  params = sb.toString().split("[(]|[)]");
					  putFrictionModifieronTile(params[3],params[1]);
					  break;
				  case "XMLOver":	  
					  XMLMode = Boolean.valueOf(false);
					  return;
				  default:
					  break;
				  }
			  sb = new StringBuilder("");
		  }	
	} 
}
