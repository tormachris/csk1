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
		drawables = new ArrayList<Drawable>();

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
		Queue<LevelElements> map = LevelLoader.getLevel(LevelStorage.DEMOLEVEL);
		LinkedList<GraphicHole> lastholes = new LinkedList<GraphicHole>();
		for(int j = 0; j < map.size(); ++j)
			switch(map.remove()) {
				case WALL : drawables.add(new GraphicWall(new Wall()));
							break;
				case TILE : drawables.add(new GraphicTile(new Tile()));
							break;
				case ENDTILE : drawables.add(new GraphicEndTile(new EndTile()));
							break;
				case TRAP : lastholes.add(new GraphicHole(new Hole()));
							drawables.add(lastholes.element());
							break;
				case HOLE : drawables.add(new GraphicHole(new Hole()));
							break;
				case SWITCH : drawables.add(new GraphicSwitch(new Switch(lastholes.remove().getHole())));
								break;
				case RED : drawables.add(new GraphicTile(new Tile()));
							redWorker = new GraphicWorker(new Worker(1));
							((GraphicTile) drawables.get(drawables.size() - 1)).getTile().setThing(redWorker.getWorker());
							break;
				case BLUE : drawables.add(new GraphicTile(new Tile()));
							blueWorker = new GraphicWorker(new Worker(1));
							((GraphicTile) drawables.get(drawables.size() - 1)).getTile().setThing(blueWorker.getWorker());
							break;
				case CRATE : drawables.add(new GraphicTile(new Tile()));
							((GraphicTile) drawables.get(drawables.size() - 1)).getTile().setThing(new Crate(1));
							break;
				default: break;
				}			
		setUpNeighbors();
		
		LOGGER.log(Level.FINE, "GUI Initialized");
	}

	private void setUpNeighbors() {
		
		Tile t = null;
		for(int i = 0; i < drawables.size(); ++i)
		{
			t = cast(drawables.get(i));
			if(i > 10)
				t.setNeighbour(Direction.NORTH, cast(drawables.get(i - 11)));
			if(i % 11 != 10)
				t.setNeighbour(Direction.EAST, cast(drawables.get(i + 1)));
			if(i % 11 != 0)
				t.setNeighbour(Direction.WEST, cast(drawables.get(i - 1)));
			if(i < 110)
				t.setNeighbour(Direction.SOUTH, cast(drawables.get(i + 11)));
			
			
			}
			
		}
	
	private Tile cast(Drawable d) {
		if(d.getClass().equals(GraphicTile.class))
			return ((GraphicTile)d).getTile();
		if(d.getClass().equals(GraphicEndTile.class))
			return ((GraphicEndTile)d).getEndtile();
		if(d.getClass().equals(GraphicHole.class))
			return ((GraphicHole)d).getHole();
		if(d.getClass().equals(GraphicSwitch.class))
			return ((GraphicSwitch)d).getSwitcho();	
		if(d.getClass().equals(GraphicWall.class))
			return ((GraphicWall)d).getWall();	
		return null;
	}

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
		// Processing keyboard input for BLUE Worker
		if (e.getKeyCode() == KeyEvent.VK_W)
			dir = Direction.NORTH;
		if (e.getKeyCode() == KeyEvent.VK_A)
			dir = Direction.WEST;
		if (e.getKeyCode() == KeyEvent.VK_S)
			dir = Direction.SOUTH;
		if (e.getKeyCode() == KeyEvent.VK_D)
			dir = Direction.EAST;

		if (dir != null) {
			LOGGER.fine("Blue Worker is going to move");
			if (!keydownBlue) {
				LOGGER.fine("Blue Worker is about to move");
				keydownBlue = true;
				blueWorker.getWorker().move(dir);// Make blue worker move
			}
			return; // Exit, so we don't move the other worker
		}
		LOGGER.fine(() -> "Blue worker did not move");
		// Processing keyboard input for RED Worker
		if (e.getKeyCode() == KeyEvent.VK_UP)
			dir = Direction.NORTH;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			dir = Direction.WEST;
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			dir = Direction.SOUTH;
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			dir = Direction.EAST;
		if (!keydownRed) {
			LOGGER.fine("Red Worker is going to move");
			keydownRed = true;
			redWorker.getWorker().move(dir);// Make red worker move
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_S
				|| e.getKeyCode() == KeyEvent.VK_D) {
			keydownBlue = false;
			return;
		}
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_LEFT
				|| e.getKeyCode() == KeyEvent.VK_RIGHT)
			keydownRed = false;
	}

}
