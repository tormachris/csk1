package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.ImageIcon;

import logic.Direction;
/**
 * The literal controller of the program
 */
public class Controller implements KeyListener {
	private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());//Logger
	//States
	private Boolean keydownBlue;
	private Boolean keydownRed;
	private GraphicWorker blueWorker;
	private GraphicWorker redWorker;
	private static boolean acceptinput;

	public Controller(GraphicWorker bw, GraphicWorker rw) {
		//Logger setup
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		//Class seup
		blueWorker = bw;
		redWorker = rw;
		//States
		acceptinput = true;
		keydownBlue = false;
		keydownRed = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Auto-generated method stub
		// We don't want to use this, since this only fires for characters that can be
		// typed. (No arrow keys)
		// And it fires every time the character is typed, not pressed physically.
		return;
	}
	
	/**
	 * Overwriting the keyPressed method to manage user inputs.
	 * @param  e the keyEvent used to determine the input.
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		if (acceptinput) {//If the game is over, we don't care about the keyboard
			LOGGER.fine(() -> "Key pressed, key is {}" + e.getKeyCode());
			Direction dir = null;
			Boolean blue = false;
			switch (e.getKeyCode()) {//Process the key
			case (KeyEvent.VK_W):
				dir = Direction.NORTH;
				blue = true;
				break;
			case (KeyEvent.VK_A):
				dir = Direction.WEST;
				blue = true;//This makes our guys look in the direction they are heading
				IconCollection.getInstance().setBlueontile(new ImageIcon("assets/csk1_graf/EasterEggBlueLeft.png"));
				break;
			case (KeyEvent.VK_S):
				dir = Direction.SOUTH;
				blue = true;
				break;
			case (KeyEvent.VK_D):
				dir = Direction.EAST;
				blue = true;
				IconCollection.getInstance().setBlueontile(new ImageIcon("assets/csk1_graf/EasterEggBlueRight.png"));
				break;
			case (KeyEvent.VK_UP):
				dir = Direction.NORTH;
				break;
			case (KeyEvent.VK_RIGHT):
				dir = Direction.EAST;
				IconCollection.getInstance().setRedontile(new ImageIcon("assets/csk1_graf/EasterEggRedRight.png"));
				break;
			case (KeyEvent.VK_DOWN):
				dir = Direction.SOUTH;
				break;
			case (KeyEvent.VK_LEFT):
				dir = Direction.WEST;
				IconCollection.getInstance().setRedontile(new ImageIcon("assets/csk1_graf/EasterEggRedLeft.png"));
				break;
			case (KeyEvent.VK_Q):
				if (!keydownBlue) {
					keydownBlue = true;
					blueWorker.getWorker().dropHoney();
				}
				SokobanGui.getInstance().drawAll();
				return;
			case (KeyEvent.VK_E):
				if (!keydownBlue) {
					keydownBlue = true;
					blueWorker.getWorker().dropOil();
				}
				SokobanGui.getInstance().drawAll();
				return;
			case (KeyEvent.VK_NUMPAD1):
				if (!keydownRed) {
					keydownBlue = true;
					redWorker.getWorker().dropHoney();
				}
				SokobanGui.getInstance().drawAll();
				return;
			case (KeyEvent.VK_NUMPAD2):
				if (!keydownRed) {
					keydownBlue = true;
					redWorker.getWorker().dropOil();
				}
				SokobanGui.getInstance().drawAll();
				return;
			default:
				break;
			}
			if (blue) {
				if (!keydownBlue) {
					keydownBlue = true;
					blueWorker.getWorker().move(dir);// Make blue worker move
				}
			} else {
				if (!keydownRed) {
					keydownRed = true;
					redWorker.getWorker().move(dir);// Make red worker move
				}
			}
			SokobanGui.getInstance().drawAll();//Invalidate the screen
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// We don't want to process keys being held down
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_S
				|| e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_Q
				|| e.getKeyCode() == KeyEvent.VK_E) {
			keydownBlue = false;
			SokobanGui.getInstance().drawAll();
			return;
		}
		//So we throw that input away by setting these states
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_LEFT
				|| e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_NUMPAD1
				|| e.getKeyCode() == KeyEvent.VK_NUMPAD2)
			keydownRed = false;
		SokobanGui.getInstance().drawAll();//Invalidate the screen
	}

	/**
	 * @return the acceptinput
	 */
	public static boolean isAcceptinput() {
		return acceptinput;
	}

	/**
	 * @param acceptinput the acceptinput to set
	 */
	public static void setAcceptinput(boolean acceptinput) {
		Controller.acceptinput = acceptinput;
	}

}
