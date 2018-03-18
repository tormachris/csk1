/**
 * 
 */
package logic;

import java.io.*;
import java.util.*;

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
	}
	
	/**
	 * @author krist
	 * This method shows the menu. It is the entry point of the menu.
	 */
	public void show() {
		Integer input;
		String rawinput=new String("");
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
			input=Integer.valueOf(rawinput.charAt(0));
		
			switch (input.intValue()) {
			
			case(1):
				first();
			break;
			case(2):
				second();
			break;
			case(3):
				third();
			break;
			case(4):
				fourth();
			break;
			case(5):
				fifth();
			break;
			case(6):
				sixth();
			break;
			case(7):
				seventh();
			break;
			case(8):
				eight();
			break;
			case(9):
				nineth();
			break;
			default:
				break;
		}
		}
		return;
	}
	
	private void first() {
	}
	
	private void second() {
		
	}
	
	private void third(){
		
	}
	
	private void fourth() {
		
	}
	
	private void fifth() {
		
	}
	
	private void sixth() {
	
	}
	
	private void seventh() {
		Hole h=new Hole();
		Switch s=new Switch(h);
		String rawinput=new String("");
		System.out.print("\t *7.1Van valami a kapcsolon tartozo lyukon? y/N \n ?");
		try {
			rawinput=reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(rawinput.toLowerCase().charAt(0)=='y') {
			System.out.print("\t \t *7.1Doboz van a lyukon? y/N \n ?");
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
	}
	
	private void eight() {
		
	}
	
	private void nineth() {
		
	}
}
