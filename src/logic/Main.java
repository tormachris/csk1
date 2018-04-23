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
		Timer.getInstance(); //Init Timer
		Game.getInstance();	//Init Game 
		Commander.getInstance().interpreter();
	
		
	}
}
