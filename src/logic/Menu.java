/**
 * 
 */
package logic;

import java.io.IOException;

/**
 * @author krist
 * This class is the skeleton's menu system.
 * There is going to be only one instance in the Main class!
 */
public class Menu {

	/**
	 * @author krist
	 * This method shows the menu. It is the entry point of the menu.
	 */
	public void show() {
		Integer input;
		java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
		String rawinput=new String("");
		while(rawinput=="") {
		System.out.println("1. Worker Moves\n"
				+ "2. Tile-re worker erkezne\n"
				+ "3. A Tile-re dobozt tolnának\n"
				+ "4. Egy Játékossal doboz ütközik\n"
				+ "5. Egy lyukra dobozt tolnak\n"
				+ "6.Egy lyukra játékos akar lépnin\n"
				+ "7.Egy kapcsolóra dobozt tolnak\n"
				+ "0.Kilépés a játékból");

		try {
			rawinput=reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		input=Integer.valueOf(rawinput.charAt(0));
		
		switch (input.intValue()) {
		
		case(1):
			first();
		case(2):
			second();
		case(3):
			third();
		case(4):
			fourth();
		case(5):
			fifth();
		case(6):
			sixth();
		case(7):
			seventh();
		case(8):
			eight();
		case(9):
			nineth();
		default:
			{}
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
		
	}
	
	private void eight() {
		
	}
	
	private void nineth() {
		
	}
}
