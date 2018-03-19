/**
 * This is our one and single package for this skeleton.
 */
package logic;

/**
 * Starting point of the program
 * @author kristof
 *
 */
public class Main {

	/**
	 * Entry point of the program
	 * @param args
	 */
	public static void main(String[] args) {
		//Some advice.
		System.out.println("!During the setup of every test, "
				+ "you will see a lot of printouts.\n"
				+ "!They help you to identify problems, but are not part of the "
				+ "sequence diagram you should construct from the test. "
				+ "You might as well disregard them. \n"
				+ "!PLEASE READ EVERYTHING CAREFULLY");
		Timer.getInstance(); //Init Timer
		Game.getInstance();	//Init Game
		Menu.getInstance();	//Init Menu
		Menu.getInstance().show(); //Show Menu
	}

}
