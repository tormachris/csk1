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
			instance = new Menu();
		}
		System.out.println("<[:Menu].getInstance(): instance");
		return instance;
	}
	/**
	 * @author krist
	 * Default constructor.
	 */
	public Menu() {
		reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		this.show();
	}
	
	/**
	 * @author krist
	 * This method shows the menu. It is the entry point of the menu.
	 */
	public void show() {
		String rawinput=new String("a");
		while(rawinput.compareTo("")!=0) {
			System.out.println("1. Worker Moves\n"
					+ "2. Tile-re worker erkezne\n"
					+ "3. A Tile-re dobozt tolnának\n"
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
			switch (rawinput.charAt(0)) {
			
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
			case('8'):{
				eight();
				break;
			}
			case('9'):{
				nineth();
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
		Tile t1 = new Tile();
		Worker w = new Worker();
		
		t1.setThing(w);
		
		String rawinput=new String("");
		System.out.print("\t *1.1Mi van a worker elott (Wall vagy EndTile) ? W/E \n ?");
		try {
			rawinput=reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		rawinput = rawinput.toLowerCase();
		switch (rawinput) {
		
		case "w":
			Wall wall = new Wall();
			t1.setNeighbour(Direction.NORTH, wall);
			wall.setNeighbour(Direction.SOUTH, t1);
			break;
		
		case "e":
			EndTile eT = new EndTile();
			t1.setNeighbour(Direction.NORTH, eT);
			eT.setNeighbour(Direction.SOUTH, t1);
			break;
		default:
			break;
		}
		w.move(Direction.NORTH);

	}
	
	private void second() {
		
		Tile t1 = new Tile();
		Tile t2 = new Tile();
		Tile t3 = new Tile();
		Worker w = new Worker();

		t1.setNeighbour(Direction.NORTH, t2);
		t1.setThing(w);
		t2.setNeighbour(Direction.NORTH, t3);
		t2.setNeighbour(Direction.SOUTH, t1);
		t3.setNeighbour(Direction.SOUTH, t2);


		String rawinput=new String("");
		System.out.print("\t *2.1Van valami a worker elotti Tile-on? y/N \n ?");
		try {
			rawinput=reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(rawinput.toLowerCase().charAt(0)=='y') {
			System.out.print("\t \t *2.1Doboz van a Tile-on? y/N \n ?");
			try {
			rawinput=reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(rawinput.toLowerCase().charAt(0)=='y') {
				Crate c=new Crate();
				t2.setThing(c);
			}
			else {
				Worker w2=new Worker();
				t2.setThing(w2);
			}
		}
		if(rawinput.toLowerCase().charAt(0)=='n') {
		t3.setThing(null);
		}
		System.out.println("!Setup done, doing operation.");
		
		w.move(Direction.NORTH);

		
	}
	
	private void third(){
		Tile t1 = new Tile();
		Tile t2 = new Tile();
		Tile t3 = new Tile();
		Worker w = new Worker();
		Crate c = new Crate();
		
		t1.setNeighbour(Direction.NORTH, t2);
		t1.setThing(w);
		t2.setNeighbour(Direction.NORTH, t3);
		t2.setNeighbour(Direction.SOUTH, t1);
		t2.setThing(c);
		t3.setNeighbour(Direction.SOUTH, t2);

		String rawinput=new String("");
		System.out.print("\t *2.1Van valami a doboz mogotti Tile-on? y/N \n ?");
		try {
			rawinput=reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(rawinput.toLowerCase().charAt(0)=='y') {
			System.out.print("\t \t *3.1Doboz van a Tile-on? y/N \n ?");
			try {
			rawinput=reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(rawinput.toLowerCase().charAt(0)=='y') {
				Crate c2=new Crate();
				t3.setThing(c2);
			}
			else {
				Worker w2=new Worker();
				t3.setThing(w2);
			}
		}
		if(rawinput.toLowerCase().charAt(0)=='n') {
		t3.setThing(null);
		}
		System.out.println("!Setup done, doing operation.");
		w.move(Direction.NORTH);
		
	}
	
	private void fourth() {
		System.out.println("!Setting up operation.");
		//Setup
		String rawinput=new String("");
		Tile t0=new Tile();
		Tile t1=new Tile();
		Tile t2=new Tile();
		System.out.print("\t *4.1 Van hova csusznia a jatekosnak? y/N \n ?");
		try {
			rawinput=reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Tile t3=new Tile();
		Wall w3=new Wall();
		if (rawinput.toLowerCase().charAt(0)=='y') t2.setNeighbour(Direction.NORTH, t3);
		else t2.setNeighbour(Direction.NORTH, w3);
		t0.setNeighbour(Direction.NORTH, t1);
		t1.setNeighbour(Direction.NORTH, t2);
		Worker w2=new Worker();
		t2.setThing(w2);
		Crate c1=new Crate();
		t1.setThing(c1);
		Worker w0=new Worker();
		t0.setThing(w0);
		//Moving
		System.out.println("!Setup done, doing operation.");
		w0.move(Direction.NORTH);
	}
	
	private void fifth() {
		String rawinput=new String("");
		System.out.print("\t *5.1 Nyitva van a lyuk? y/N \n ?");
		try {
			rawinput=reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Setting up
		Hole h=new Hole(rawinput.toLowerCase().charAt(0)=='y');
		Tile t=new Tile();
		t.setNeighbour(Direction.NORTH, h);
		Worker w=new Worker();
		Crate c= new Crate();
		t.setThing(c);
		Tile t2=new Tile();
		t2.setNeighbour(Direction.NORTH, t);
		t2.setThing(w);
		//Moving
		System.out.println("!Setup done, doing operation.");
		w.move(Direction.NORTH);
	}
	
	private void sixth() {
		String rawinput=new String("");
		System.out.print("\t *6.1 Nyitva van a lyuk? y/N \n ?");
		try {
			rawinput=reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Setting up
		Hole h=new Hole(rawinput.toLowerCase().charAt(0)=='y');
		Tile t=new Tile();
		t.setNeighbour(Direction.NORTH, h);
		Worker w=new Worker();
		t.setThing(w);
		//Moving
		System.out.println("!Setup done, doing operation.");
		w.move(Direction.NORTH);
	}
	
	private void seventh() {
		Hole h=new Hole();
		Switch s=new Switch(h);//They don't have to be neighbours
		String rawinput=new String("");
		System.out.print("\t *7.1 Van valami a kapcsolon tartozo lyukon? y/N \n ?");
		try {
			rawinput=reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(rawinput.toLowerCase().charAt(0)=='y') {
			System.out.print("\t \t *7.1 Doboz van a lyukon? y/N \n ?");
			try {
				rawinput=reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(rawinput.toLowerCase().charAt(0)=='y') {
				Crate c=new Crate();
				h.setThing(c);
			}
			else {
				Worker w=new Worker();
				h.setThing(w);
			}
		}
		Crate c2=new Crate();
		Worker w2=new Worker();
		Tile t1=new Tile();
		t1.setThing(c2);
		t1.setNeighbour(Direction.NORTH, s);
		Tile t2=new Tile();
		t2.setThing(w2);
		t2.setNeighbour(Direction.NORTH, t1);
		System.out.println("!Setup done, doing operation.");
		w2.move(Direction.NORTH);
	}
	
	private void eight() {
		
	}
	
	private void nineth() {
		
	}
}
