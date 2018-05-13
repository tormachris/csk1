package gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import logic.Direction;

public class Controller implements KeyListener {
	private static final Logger LOGGER = Logger.getLogger(Controller.class.getName());
	private Boolean keydownBlue;
	private Boolean keydownRed;
	private GraphicWorker blueWorker;
	private GraphicWorker redWorker;
	public Controller(GraphicWorker bw, GraphicWorker rw) {
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		blueWorker=bw;
		redWorker=rw;
		keydownBlue=false;
		keydownRed=false;
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// Auto-generated method stub
		// We don't want to use this, since this only fires for characters that can be
		// typed. (No arrow keys)
		// And it fires every time the character is typed, not pressed physically.
		return;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		LOGGER.fine(() -> "Key pressed, key is {}" + e.getKeyCode());
		Direction dir = null;
		Boolean blue = false;
		switch (e.getKeyCode()) {
		case (KeyEvent.VK_W):
			dir = Direction.NORTH;
			blue = true;
			break;
		case (KeyEvent.VK_A):
			dir = Direction.WEST;
			blue = true;
			break;
		case (KeyEvent.VK_S):
			dir = Direction.SOUTH;
			blue = true;
			break;
		case (KeyEvent.VK_D):
			dir = Direction.EAST;
			blue = true;
			break;
		case (KeyEvent.VK_UP):
			dir = Direction.NORTH;
			break;
		case (KeyEvent.VK_RIGHT):
			dir = Direction.EAST;
			break;
		case (KeyEvent.VK_DOWN):
			dir = Direction.SOUTH;
			break;
		case (KeyEvent.VK_LEFT):
			dir = Direction.WEST;
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
		SokobanGui.getInstance().drawAll();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_S
				|| e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_Q
				|| e.getKeyCode() == KeyEvent.VK_E) {
			keydownBlue = false;
			SokobanGui.getInstance().drawAll();
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_LEFT
				|| e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_NUMPAD1
				|| e.getKeyCode() == KeyEvent.VK_NUMPAD2)
			keydownRed = false;
		SokobanGui.getInstance().drawAll();
	}

}
