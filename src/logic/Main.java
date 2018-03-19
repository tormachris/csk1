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
				+ "!They help you to identify problems, but might not be part of the "
				+ "sequence diagram you should construct from the test.\n"
				+ "You might as well disregard them in most cases.\n"
				+ "!PLEASE READ EVERYTHING CAREFULLY\n");
		Timer.getInstance(); //Init Timer
		Game.getInstance();	//Init Game
		Menu.getInstance();	//Init Menu
		Menu.getInstance().show(); //Show Menu
	}

}
