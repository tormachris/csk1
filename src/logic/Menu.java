/**
 * 
 */
package logic;

import java.io.*;

/**
 * @author krist
 * This class is the skeleton's menu system.
 * There is going to be only one instance in the Main class!
 */
public class Menu {
	java.io.BufferedReader reader;
	private static Menu instance = null;
	/**
	 * This method realizes Menu being a singleton in Java.
	 */
	public static Menu getInstance() {
		System.out.println("!Getting instance of Menu");
		if(instance == null) {
			instance = new Menu();		//Creating the menu.
		}
		System.out.println("<[:Menu].getInstance(): instance");
		return instance;
	}
	/**
	 * @author krist
	 * Default constructor.
	 */
	public Menu() {
		reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));	//Making the console for controlling the menu.
	}
	
	/**
	 * @author krist
	 * This method shows the menu. It is the entry point of the menu.
	 */
	public void show() {
		String rawinput=new String("a");
		while(rawinput.compareTo("")!=0) {		//The view of the menu.
			System.out.println("\n\n");
			System.out.println("1. Worker Moves\n"
					+ "2. Tile-re worker erkezne\n"
					+ "3. A Tile-re dobozt tolnanak\n"
					+ "4. Egy Jatekossal doboz utkozik\n"
					+ "5. Egy lyukra dobozt tolnak\n"
					+ "6.Egy lyukra jatakos akar lepni\n"
					+ "7.Egy kapcsolora dobozt tolnak\n"
					+ "0.Kilépés a játékból");
			System.out.print('?');
			try {
				rawinput=reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			switch (rawinput.charAt(0)) {				//Choosing test type
			

				case('0'):{
					zeroth();
					break;
				}
				case('1'):{
					first();
					break;
				}
				case('2'):{
					second();
					break;
				}
				case('3'):{
					third();
					break;
				}
				case('4'):{
					fourth();
					break;
				}
				case('5'):{
					fifth();
					break;
				}
				case('6'):{
					sixth();
					break;
				}
				case('7'):{
					seventh();
					break;
				}
				default:
					break;

			}
		}
		return;
	}
	
	private void first() {
		System.out.print("!1. Worker Moves");
		Map m=new Map();						//Setting up the test map
		Game.getInstance().addMap(m);			
		m.startMap();							//Starting the test map
		Tile t1 = new Tile();						
		Worker w = new Worker();				//Worker added for tests
		
		t1.setThing(w);
		
		String rawinput=new String("");
		System.out.print("\t *1.1Mi van a worker elott (Wall vagy EndTile) ? W/E \n ?");	//Choosing test case
		try {
			rawinput=reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		rawinput = rawinput.toLowerCase();
		switch (rawinput) {
		
		case "w":
			Wall wall = new Wall();
			m.addTile(wall);							//Checking interaction with a Wall.
			t1.setNeighbour(Direction.NORTH, wall);		//Putting the wall onto the map
			wall.setNeighbour(Direction.SOUTH, t1);		
			break;
		
		case "e":
			EndTile eT = new EndTile();
			m.addTile(eT);								//Checking  interaction with EndTile
			t1.setNeighbour(Direction.NORTH, eT);		//Putting the EndTile onto the map
			eT.setNeighbour(Direction.SOUTH, t1);
			break;
		default:
			break;
		}
		m.addTile(t1);
		m.addWorker(w);
		System.out.println("\n\n");
		System.out.println("!Setup done, doing operation.");
		System.out.println(">[:Worker].move(Direction.NORTH)");		//Test map is ready with contributors
		w.move(Direction.NORTH);									//Trying to move the Worker 
		System.out.println("\n\n");
		System.out.println("!Operation done, shutting down test env.");
		Game.getInstance().removeMap(m);
		m.endMap();							
		System.out.println("!Test Done");
	}
	
	private void second() {
		System.out.print("!2. Tile-re worker erkezne");
		Map m=new Map();								//Setting up the test map 
		Game.getInstance().addMap(m);					
		m.startMap();									//Starting the test map
		Tile t1 = new Tile();
		m.addTile(t1);								
		Tile t2 = new Tile();
		m.addTile(t2);
		Tile t3 = new Tile();
		m.addTile(t3);									//Building the tiles of the map
		Worker w = new Worker();
		m.addWorker(w);									//Worker added for tests

		t1.setNeighbour(Direction.NORTH, t2);
		t1.setThing(w);
		t2.setNeighbour(Direction.NORTH, t3);
		t2.setNeighbour(Direction.SOUTH, t1);
		t3.setNeighbour(Direction.SOUTH, t2);


		String rawinput=new String("");
		System.out.print("\t *2.1Van valami a worker elotti Tile-on? y/N \n ?"); //Choosing test coreography.
		try {
			rawinput=reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(rawinput.toLowerCase().charAt(0)=='y') {
			System.out.print("\t \t *2.1Doboz van a Tile-on? y/N \n ?");	//Choosing test coreography.
			try {
			rawinput=reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(rawinput.toLowerCase().charAt(0)=='y') {
				Crate c=new Crate();
				m.addCrate(c);				//Adding Crate to the test map.
				t2.setThing(c);
			}
			else {
				Worker w2=new Worker();
				m.addWorker(w2);				//Adding Second Worker to the test map.
				t2.setThing(w2);
			}
		}
		if(rawinput.toLowerCase().charAt(0)=='n') {
			t3.setThing(null);							//Clearing Tile for the test case.
		}
		System.out.println("\n\n");
		System.out.println("!Setup done, doing operation.");	//Test map is ready with contributors.
		System.out.println(">[:Worker].move(Direction.NORTH)");
		w.move(Direction.NORTH);								//Trying to move the Worker.
		System.out.println("\n\n");
		System.out.println("!Operation done, shutting down test env.");	
		Game.getInstance().removeMap(m);
		m.endMap();
		System.out.println("!Test Done");
	}
	
	private void third(){
		System.out.print("!3. Tile-re worker erkezne");	//Setting up the test map.
		Map m=new Map();
		Game.getInstance().addMap(m);						
		m.startMap();				//Starting test map.
		Tile t1 = new Tile();
		m.addTile(t1);
		Tile t2 = new Tile();						
		m.addTile(t2);
		Tile t3 = new Tile();
		m.addTile(t3);
		Tile t4 = new Tile();		//Building up the Tiles of the map.
		m.addTile(t4);
		Worker w = new Worker();
		m.addWorker(w);				//Worker added to the test.
		Crate c=new Crate();											
		m.addCrate(c);				//Crate added to the test.
		
		t1.setNeighbour(Direction.NORTH, t2);
		t1.setThing(w);
		t2.setNeighbour(Direction.NORTH, t3);
		t2.setNeighbour(Direction.SOUTH, t1);
		t2.setThing(c);
		t3.setNeighbour(Direction.SOUTH, t2);
		
		t3.setNeighbour(Direction.NORTH, t4);
		t4.setNeighbour(Direction.SOUTH, t3);

		String rawinput=new String("");
		System.out.print("\t *2.1Van valami a doboz mogotti Tile-on? y/N \n ?");	//Choosing test coreography. 
		try {
			rawinput=reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(rawinput.toLowerCase().charAt(0)=='y') {
			System.out.print("\t \t *3.1Doboz van a Tile-on? y/N \n ?");	//Choosing test coreography.
			try {	
			rawinput=reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(rawinput.toLowerCase().charAt(0)=='y') {
				Crate c2=new Crate();		//Adding Crate to the test map.
				m.addCrate(c2);
				t3.setThing(c2);
			}
			else {
				Worker w2=new Worker();		//Addign Worker to the test map.
				m.addWorker(w2);									
				t3.setThing(w2);
			}
		}
		if(rawinput.toLowerCase().charAt(0)=='n') {
			t3.setThing(null);
		}
		System.out.println("\n\n");
		System.out.println("!Setup done, doing operation.");
		System.out.println(">[:Worker].move(Direction.NORTH)");		//Test map is ready with contributors.
		w.move(Direction.NORTH);				//Trying to move worker.
		System.out.println("\n\n");			
		System.out.println("!Operation done, shutting down test env.");
		Game.getInstance().removeMap(m);
		m.endMap();
		System.out.println("!Test Done");
	}
	
	private void fourth() {
		System.out.println("!4. Egy Jatekossal doboz utkozik");			
		Map m=new Map();											//Setting up the test map.
		Game.getInstance().addMap(m);
		m.startMap();
		String rawinput=new String("");
		Tile t0 = new Tile();
		m.addTile(t0);
		Tile t1 = new Tile();			
		m.addTile(t1);
		Tile t2 = new Tile();						//Building up the Tiles of the map.
		m.addTile(t2);
		System.out.print("\t *4.1 Van hova csusznia a jatekosnak? y/N \n ?");		//Choosing test coreography.
		try {
			rawinput=reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Tile t3 = new Tile();
		m.addTile(t3);					//Adding a Wall to the test.
		Wall w3=new Wall();
		m.addTile(w3);
		if (rawinput.toLowerCase().charAt(0)=='y') t2.setNeighbour(Direction.NORTH, t3);
		else t2.setNeighbour(Direction.NORTH, w3);
		t0.setNeighbour(Direction.NORTH, t1);
		t1.setNeighbour(Direction.NORTH, t2);
		Worker w2=new Worker();
		m.addWorker(w2);
		t2.setThing(w2);		//Adding Worker to the test.
		Crate c1=new Crate();
		m.addCrate(c1);			//Adding a Crate to the test.
		t1.setThing(c1);
		Worker w0=new Worker();
		m.addWorker(w0);		//Adding second Worker to the test.
		t0.setThing(w0);
		System.out.println("\n\n");
		System.out.println("!Setup done, doing operation.");
		System.out.println(">[:Worker].move(Direction.NORTH)");
		w0.move(Direction.NORTH);	//Moving the first Worker.
		System.out.println("\n\n");
		System.out.println("!Operation done, shutting down test env.");
		Game.getInstance().removeMap(m);
		m.endMap();
		System.out.println("!Test Done");
	}
	
	private void fifth() {
		System.out.println("!5. Egy lyukra dobozt tolnak");		
		Map m=new Map();					//Setting up the test map.
		Game.getInstance().addMap(m);
		m.startMap();					
		String rawinput=new String("");
		System.out.print("\t *5.1 Nyitva van a lyuk? y/N \n ?");	//Choosing test coreography.
		try {
			rawinput=reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Hole h=new Hole(rawinput.toLowerCase().charAt(0)=='y');		//Adding a Hole to the map.
		m.addTile(h);
		Tile t=new Tile();	//Building up the tiles of the map.
		m.addTile(t);
		t.setNeighbour(Direction.NORTH, h);
		Worker w=new Worker();		//Adding a Worker to the test.
		m.addWorker(w);
		Crate c= new Crate();		//Adding a Crate to the test.
		m.addCrate(c);
		t.setThing(c);
		Tile t2=new Tile();
		m.addTile(t2);				
		t2.setNeighbour(Direction.NORTH, t);
		t2.setThing(w);
		//Moving
		System.out.println("\n\n");
		System.out.println("!Setup done, doing operation.");	//Test map is ready with contributors.
		System.out.println(">[:Worker].move(Direction.NORTH)");
		w.move(Direction.NORTH);		//Moving the Worker and pushing a Crate into the Hole.
		System.out.println("\n\n");	
		System.out.println("!Operation done, shutting down test env.");
		Game.getInstance().removeMap(m);
		m.endMap();
		System.out.println("!Test Done");
	}
	
	private void sixth() {
		System.out.println("!6.Egy lyukra jatakos akar lepni");
		Map m=new Map();					//Setting up the test map.
		Game.getInstance().addMap(m);
		m.startMap();
		String rawinput=new String("");
		System.out.print("\t *6.1 Nyitva van a lyuk? y/N \n ?");		//Choosing test coreography.
		try {
			rawinput=reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Hole h=new Hole(rawinput.toLowerCase().charAt(0)=='y');		//Adding a Hole to the test map.
		m.addTile(h);
		Tile t=new Tile();
		m.addTile(t);							//Building up the tiles of the map.
		t.setNeighbour(Direction.NORTH, h);
		Worker w=new Worker();				//Adding a Worker to the test.
		m.addWorker(w);
		t.setThing(w);
		System.out.println("\n\n");
		System.out.println("!Setup done, doing operation.");
		System.out.println(">[:Worker].move(Direction.NORTH)");	//Test map is ready with contributors.
		w.move(Direction.NORTH);				//Worker interacted with the Hole tile.
		System.out.println("\n\n");
		System.out.println("!Operation done, shutting down test env.");
		Game.getInstance().removeMap(m);
		m.endMap();
		System.out.println("!Test Done");
	}
	
	private void seventh() {
		System.out.println("!7.Egy kapcsolora dobozt tolnak");
		Map m=new Map();		//Setting up the test map.
		Game.getInstance().addMap(m);
		m.startMap();
		Hole h=new Hole();
		m.addTile(h);
		Switch s=new Switch(h);	//They don't have to be neighbours.
		m.addTile(s);
		String rawinput=new String("");
		System.out.print("\t *7.1 Van valami a kapcsolon tartozo lyukon? y/N \n ?");	//Choosing test coreography.
		try {
			rawinput=reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(rawinput.toLowerCase().charAt(0)=='y') {
			System.out.print("\t \t *7.1 Doboz van a lyukon? y/N \n ?");	//Choosing test coreography.
			try {
				rawinput=reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(rawinput.toLowerCase().charAt(0)=='y') {
				Crate c=new Crate();	//Adding a Crate to the test.
				m.addCrate(c);
				h.setThing(c);
			}
			else {
				Worker w=new Worker();
				m.addWorker(w);		//Adding a Worker to the test.
				h.setThing(w);
			}
		}
		Crate c2=new Crate();	//Adding second Crate to the test.
		m.addCrate(c2);
		Worker w2=new Worker();	//Adding second Worker to the test.
		m.addWorker(w2);
		Tile t1=new Tile();
		m.addTile(t1);		//Building up the tiles of the map.
		t1.setThing(c2);
		t1.setNeighbour(Direction.NORTH, s);
		Tile t2=new Tile();
		m.addTile(t2);
		t2.setThing(w2);
		t2.setNeighbour(Direction.NORTH, t1);
		System.out.println("\n\n");
		System.out.println("!Setup done, doing operation.");
		System.out.println(">[:Worker].move(Direction.NORTH)");
		w2.move(Direction.NORTH);	//Crate and Switch are interacting.
		System.out.println("\n\n");
		System.out.println("!Operation done, shutting down test env.");
		Game.getInstance().removeMap(m);
		m.endMap();
		System.out.println("!Test Done");
	}
	
	private void zeroth() {
		System.out.print("\t *9.1 A jatek valamelyik jatekos gyozelmevel, vagy felhasznalio megszakitassal zarult? 1/2/q \n ?");
		String rawinput=new String("");
		while(rawinput.toLowerCase().charAt(0)!='1' ||
				rawinput.toLowerCase().charAt(0)!='2' ||		//Leaving the menu and the application.
				rawinput.toLowerCase().charAt(0)!='q') {
			try {
				rawinput=reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (rawinput.toLowerCase().charAt(0)!='q') Game.getInstance().end();
			else {
				System.out.println("The application will now close!");
				System.exit(0);
			}
		}
	}
}
