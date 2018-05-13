package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Queue;
import java.util.logging.*;
import javax.swing.border.*;

import ansiliary.*;
import logic.*;
import logic.Map;

public class SokobanGui extends JFrame implements KeyListener {
	/**
	 * Generated serialization UID
	 */
	private static final long serialVersionUID = 2897342800007758713L;
	private static final Logger LOGGER = Logger.getLogger(SokobanGui.class.getName());
	private static final int GRIDSIZE = 11;
	private static SokobanGui instance = null;
	private ArrayList<JPanel> gameGrid;
	private ArrayList<Drawable> drawables;
	private GraphicWorker blueWorker;
	private GraphicWorker redWorker;
	private Boolean keydownBlue;
	private Boolean keydownRed;

	/**
	 * Create the application.
	 */
	private SokobanGui() {
		super();
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);

		blueWorker = new GraphicWorker(IconCollection.getInstance().getWorkerBlue());
		redWorker = new GraphicWorker(IconCollection.getInstance().getWorkerRed());

		gameGrid = new ArrayList<>();
		drawables = new ArrayList<>();

		initialize();

		this.addKeyListener(this);

		keydownRed = false;
		keydownBlue = false;
		LOGGER.log(Level.FINE, "GUI Created with default constructor");
	}

	/**
	 * This method realizes SokobanGui being a singleton in Java.
	 */
	public static SokobanGui getInstance() {
		if (instance == null) {
			instance = new SokobanGui();
		}
		return instance;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setTitle("KILLER SOKOBAN!!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 550, 75 + 550); // shrug

		/* Felso menu geci */

		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmNewGame = new JMenuItem("New Game");
		mnFile.add(mntmNewGame);

		JMenuItem mntmSaveGame = new JMenuItem("Save Game");
		mnFile.add(mntmSaveGame);

		JMenuItem mntmLoadGame = new JMenuItem("Load Game");
		mnFile.add(mntmLoadGame);
		this.getContentPane().setLayout(new BorderLayout(0, 0));

		/* Felso panel (ido es pontok) */

		Font scoreFont = new Font("Tahoma", Font.PLAIN, 20);

		JPanel panelTop = new JPanel();
		this.getContentPane().add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new BorderLayout(0, 0));
		panelTop.setPreferredSize(new Dimension(550, 75));

		JPanel panelScoreBlue = new JPanel();
		panelTop.add(panelScoreBlue, BorderLayout.WEST);
		panelScoreBlue.setBorder(new EmptyBorder(0, 5, 10, 5));

		JLabel lblScoreBlue = new JLabel("---");
		panelScoreBlue.add(lblScoreBlue);
		lblScoreBlue.setFont(scoreFont);
		lblScoreBlue.setForeground(Color.BLUE);
		lblScoreBlue.setHorizontalAlignment(SwingConstants.LEFT);

		JPanel panelScoreRed = new JPanel();
		panelTop.add(panelScoreRed, BorderLayout.EAST);
		panelScoreRed.setBorder(new EmptyBorder(0, 5, 10, 5));

		JLabel lblScoreRed = new JLabel("---");
		panelScoreRed.add(lblScoreRed);
		lblScoreRed.setFont(scoreFont);
		lblScoreRed.setForeground(Color.RED);
		lblScoreRed.setHorizontalAlignment(SwingConstants.RIGHT);

		JPanel panelTimerContainer = new JPanel();
		panelTop.add(panelTimerContainer, BorderLayout.NORTH);

		JLabel lblTimer = new JLabel("--:--");
		panelTimerContainer.add(lblTimer);
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimer.setFont(new Font("Tahoma", Font.BOLD, 28));

		/* Also panel (maga a game) */

		JPanel panelMain = new JPanel();
		this.getContentPane().add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE, 0, 0));
		panelMain.setPreferredSize(new Dimension(550, 550));

		int i;
		boolean grey = false;
		for (i = 0; i < (GRIDSIZE * GRIDSIZE); i++) {
			JPanel p = new JPanel();

			if (grey)
				p.setBackground(Color.LIGHT_GRAY);
			else
				p.setBackground(Color.WHITE);

			grey = !grey;
			gameGrid.add(p);
			panelMain.add(p);
		}
		LevelLoader ll=new LevelLoader();
		Queue<LevelElements> map = ll.getLevel(LevelStorage.DEMOLEVEL);
		Map m = new Map();
		Game.getInstance().addMap(m);
		LinkedList<GraphicHole> lastholes = new LinkedList<>();
		for (int j = 0; j < map.size(); ++j) {
			switch (map.remove()) {
			case WALL:
				LOGGER.log(Level.FINE, "WALL Detected");
				drawables.add(new GraphicWall(new Wall()));
				m.addTile(cast(drawables.get(drawables.size() - 1)));
				break;
			case TILE:
				LOGGER.log(Level.FINE, "TILE Detected");
				drawables.add(new GraphicTile(new Tile()));
				m.addTile(cast(drawables.get(drawables.size() - 1)));
				break;
			case ENDTILE:
				LOGGER.log(Level.FINE, "ENDTILE Detected");
				drawables.add(new GraphicEndTile(new EndTile()));
				m.addTile(cast(drawables.get(drawables.size() - 1)));
				break;
			case TRAP:
				LOGGER.log(Level.FINE, "TRAP Detected");
				lastholes.add(new GraphicHole(new Hole()));
				drawables.add(lastholes.element());
				m.addTile(cast(drawables.get(drawables.size() - 1)));
				break;
			case HOLE:
				LOGGER.log(Level.FINE, "HOLE Detected");
				drawables.add(new GraphicHole(new Hole()));
				m.addTile(cast(drawables.get(drawables.size() - 1)));
				break;
			case SWITCH:
				LOGGER.log(Level.FINE, "SWITCH Detected");
				drawables.add(new GraphicSwitch(new Switch(lastholes.remove().getHole())));
				m.addTile(cast(drawables.get(drawables.size() - 1)));
				break;
			case RED:
				LOGGER.log(Level.FINE, "RED Detected");
				drawables.add(new GraphicTile(new Tile()));
				redWorker = new GraphicWorker(new Worker(1));
				((GraphicTile) drawables.get(drawables.size() - 1)).getTile().setThing(redWorker.getWorker());
				m.addTile(cast(drawables.get(drawables.size() - 1)));
				m.addWorker(redWorker.getWorker());
				break;
			case BLUE:
				LOGGER.log(Level.FINE, "BLUE Detected");
				drawables.add(new GraphicTile(new Tile()));
				blueWorker = new GraphicWorker(new Worker(1));
				((GraphicTile) drawables.get(drawables.size() - 1)).getTile().setThing(blueWorker.getWorker());
				m.addTile(cast(drawables.get(drawables.size() - 1)));
				m.addWorker(blueWorker.getWorker());
				break;
			case CRATE:
				LOGGER.log(Level.FINE, "CRATE Detected");
				drawables.add(new GraphicTile(new Tile()));
				((GraphicTile) drawables.get(drawables.size() - 1)).getTile().setThing(new Crate(1));
				m.addTile(cast(drawables.get(drawables.size() - 1)));
				m.addCrate((Crate) ((GraphicTile) drawables.get(drawables.size() - 1)).getTile().getThing());
				break;
			default:
				LOGGER.log(Level.FINE, "DEFAULT Detected");
				break;
			}
		}
		setUpNeighbors();

		LOGGER.log(Level.FINE, "GUI Initialized");
	}

	private void setUpNeighbors() {

		Tile t = null;
		for (int i = 0; i < drawables.size(); ++i) {
			t = cast(drawables.get(i));
			if (i > 10 && t!=null)
				t.setNeighbour(Direction.NORTH, cast(drawables.get(i - 11)));
			if (i % 11 != 10 && t!=null)
				t.setNeighbour(Direction.EAST, cast(drawables.get(i + 1)));
			if (i % 11 != 0 && t!=null)
				t.setNeighbour(Direction.WEST, cast(drawables.get(i - 1)));
			if (i < 110 && t!=null)
				t.setNeighbour(Direction.SOUTH, cast(drawables.get(i + 11)));

		}

	}

	private Tile cast(Drawable d) {
		if (d.getClass().equals(GraphicTile.class))
			return ((GraphicTile) d).getTile();
		if (d.getClass().equals(GraphicEndTile.class))
			return ((GraphicEndTile) d).getEndtile();
		if (d.getClass().equals(GraphicHole.class))
			return ((GraphicHole) d).getHole();
		if (d.getClass().equals(GraphicSwitch.class))
			return ((GraphicSwitch) d).getSwitcho();
		if (d.getClass().equals(GraphicWall.class))
			return ((GraphicWall) d).getWall();
		return null;
	}

	/**
	 * @deprecated (since="1.0", forRemoval=false)
	 */
	@Deprecated
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
			dir = Direction.WEST;
			break;
		case (KeyEvent.VK_DOWN):
			dir = Direction.SOUTH;
			break;
		case (KeyEvent.VK_LEFT):
			dir = Direction.EAST;
			break;
		case (KeyEvent.VK_Q):
			if (!keydownBlue) {
				LOGGER.fine("Blue Worker put down honey");
				keydownBlue = true;
				blueWorker.getWorker().dropHoney();
			}
			return;
		case (KeyEvent.VK_E):
			if (!keydownBlue) {
				LOGGER.fine("Blue Worker put down oil");
				keydownBlue = true;
				blueWorker.getWorker().dropOil();
			}
			return;
		case (KeyEvent.VK_NUMPAD1):
			if (!keydownRed) {
				LOGGER.fine("Red Worker put down honey");
				keydownBlue = true;
				redWorker.getWorker().dropHoney();
			}
			return;
		case (KeyEvent.VK_NUMPAD2):
			if (!keydownRed) {
				LOGGER.fine("Red Worker put down oil");
				keydownBlue = true;
				redWorker.getWorker().dropOil();
			}
			return;
		default:
			break;
		}
		if (blue) {
			LOGGER.fine("Blue Worker is going to move");
			if (!keydownBlue) {
				LOGGER.fine("Blue Worker is about to move");
				keydownBlue = true;
				blueWorker.getWorker().move(dir);// Make blue worker move
			}
		} else {
			LOGGER.fine(() -> "Blue worker did not move");
			if (!keydownRed) {
				LOGGER.fine("Red Worker is going to move");
				keydownRed = true;
				redWorker.getWorker().move(dir);// Make red worker move
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_S
				|| e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_Q
				|| e.getKeyCode() == KeyEvent.VK_E) {
			keydownBlue = false;
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_LEFT
				|| e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_NUMPAD1
				|| e.getKeyCode() == KeyEvent.VK_NUMPAD2)
			keydownRed = false;
	}

}
