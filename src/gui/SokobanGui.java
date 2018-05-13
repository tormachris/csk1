package gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.*;
import java.util.logging.*;
import javax.swing.border.*;

import ansiliary.*;
import logic.*;
import logic.Map;
import logic.Timer;

public class SokobanGui extends JFrame implements Steppable {
	/**
	 * Generated serialization UID
	 */
	private static final long serialVersionUID = 2897342800007758713L;
	private static final Logger LOGGER = Logger.getLogger(SokobanGui.class.getName());
	private static final short GRIDSIZE = 11;
	private static SokobanGui instance = null;
	private ArrayList<JPanel> gameGrid;
	private ArrayList<Drawable> drawables;
	private static GraphicWorker blueWorker;
	private static GraphicWorker redWorker;
	private JLabel lblScoreBlue;
	private JLabel lblScoreRed;
	private JLabel lblTimer;
	private File chosenFile;

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

		gameGrid = new ArrayList<>();
		drawables = new ArrayList<>();

		initialize();

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
		/* Initializing Frame */
		setResizable(false);
		this.setTitle("KILLER SOKOBAN!!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 792, 75 + 792); // shrug
		this.setIconImage(IconCollection.getInstance().getCsupor().getImage());
		/* Upper menu and menu items  */

		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewGame = new JMenuItem("New Game");
		mntmNewGame.addActionListener((java.awt.event.ActionEvent evt) -> initializeLevel());
		mnFile.add(mntmNewGame);

		JMenuItem mntmLoadGame = new JMenuItem("Load Game");
		mnFile.add(mntmLoadGame);
		mntmLoadGame.addActionListener((java.awt.event.ActionEvent evt) -> {
			JFileChooser fc=new JFileChooser();
			int returnVal = fc.showOpenDialog(this);

	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	        	chosenFile = fc.getSelectedFile();
	        	initializeLevel();
	        }
		});
		this.getContentPane().setLayout(new BorderLayout(0, 0));

		/* Upper Panel (Time and points) */

		Font scoreFont = new Font("Tahoma", Font.PLAIN, 20);

		JPanel panelTop = new JPanel();
		this.getContentPane().add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new BorderLayout(0, 0));
		panelTop.setPreferredSize(new Dimension(792, 75));

		JPanel panelScoreBlue = new JPanel();
		panelTop.add(panelScoreBlue, BorderLayout.WEST);
		panelScoreBlue.setBorder(new EmptyBorder(0, 5, 10, 5));
		lblScoreBlue = new JLabel("---");
		panelScoreBlue.add(lblScoreBlue);
		lblScoreBlue.setFont(scoreFont);
		lblScoreBlue.setForeground(Color.BLUE);
		lblScoreBlue.setHorizontalAlignment(SwingConstants.LEFT);

		JPanel panelScoreRed = new JPanel();
		panelTop.add(panelScoreRed, BorderLayout.EAST);
		panelScoreRed.setBorder(new EmptyBorder(0, 5, 10, 5));

		lblScoreRed = new JLabel("---");
		panelScoreRed.add(lblScoreRed);
		lblScoreRed.setFont(scoreFont);
		lblScoreRed.setForeground(Color.RED);
		lblScoreRed.setHorizontalAlignment(SwingConstants.RIGHT);

		JPanel panelTimerContainer = new JPanel();
		panelTop.add(panelTimerContainer, BorderLayout.NORTH);

		lblTimer = new JLabel("--:--");
		panelTimerContainer.add(lblTimer);
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimer.setFont(new Font("Tahoma", Font.BOLD, 28));

		/* Also panel (maga a game) */

		JPanel panelMain = new JPanel();
		this.getContentPane().add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE, 0, 0));
		panelMain.setPreferredSize(new Dimension(792, 792));

		/*Creating the grid for the map */
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
		
		initializeLevel();

		LOGGER.log(Level.FINE, "GUI Initialized");
	}
	/**
	 * Initializing the level based on the level loader
	 */
	private void initializeLevel() {
		LevelLoader ll = new LevelLoader();
		Queue<LevelElements> map;
		if(chosenFile==null)map=ll.getLevel(LevelStorage.DEMOLEVEL);
		else map=ll.getLevelFromFile(chosenFile);
		chosenFile=null;
		Map m = new Map();
		Game.getInstance().clearMaps();
		Game.getInstance().addMap(m);
		Game.getInstance().setCurrentmap(m);
		LinkedList<GraphicHole> lastholes = new LinkedList<>();
		drawables.clear();
		Controller.setAcceptinput(true);
		for (int j = 0; j < GRIDSIZE * GRIDSIZE; ++j) {
			switch (map.remove()) {
			case WALL:
				drawables.add(new GraphicWall(new Wall()));
				m.addTile(cast(drawables.get(drawables.size() - 1)));
				break;
			case TILE:
				drawables.add(new GraphicTile(new Tile()));
				m.addTile(cast(drawables.get(drawables.size() - 1)));
				break;
			case ENDTILE:
				drawables.add(new GraphicEndTile(new EndTile()));
				m.addTile(cast(drawables.get(drawables.size() - 1)));
				break;
			case TRAP:
				lastholes.add(new GraphicHole(new Hole()));
				drawables.add(lastholes.element());
				m.addTile(cast(drawables.get(drawables.size() - 1)));
				break;
			case HOLE:
				drawables.add(new GraphicHole(new Hole()));
				m.addTile(cast(drawables.get(drawables.size() - 1)));
				break;
			case SWITCH:
				drawables.add(new GraphicSwitch(new Switch(lastholes.remove().getHole())));
				m.addTile(cast(drawables.get(drawables.size() - 1)));
				break;
			case RED:
				drawables.add(new GraphicTile(new Tile()));
				redWorker = new GraphicWorker(new Worker(1));
				((GraphicTile) drawables.get(drawables.size() - 1)).getTile().setThing(redWorker.getWorker());
				m.addTile(cast(drawables.get(drawables.size() - 1)));
				m.addWorker(redWorker.getWorker());
				break;
			case BLUE:
				drawables.add(new GraphicTile(new Tile()));
				blueWorker = new GraphicWorker(new Worker(1));
				((GraphicTile) drawables.get(drawables.size() - 1)).getTile().setThing(blueWorker.getWorker());
				m.addTile(cast(drawables.get(drawables.size() - 1)));
				m.addWorker(blueWorker.getWorker());
				break;
			case CRATE:
				drawables.add(new GraphicTile(new Tile()));
				((GraphicTile) drawables.get(drawables.size() - 1)).getTile().setThing(new Crate(1));
				m.addTile(cast(drawables.get(drawables.size() - 1)));
				m.addCrate((Crate) ((GraphicTile) drawables.get(drawables.size() - 1)).getTile().getThing());
				break;
			default:
				break;
			}
		}
		
		this.addKeyListener(new Controller(blueWorker, redWorker));
		
		setUpNeighbors();

		drawAll();
		
		Timer.getInstance().setRunning(true);
		try{
			Timer.getInstance().addSteppable(this);
		}catch(Exception ex) {
			LOGGER.log(Level.FINE, ex.getMessage(), ex);
		}
	}
	
	/**
	 * Draws every drawable on the map
	 */
	public void drawAll() {
		lblScoreRed.setText(redWorker.getWorker().getPoints().toString());
		lblScoreRed.revalidate();
		lblScoreBlue.setText(blueWorker.getWorker().getPoints().toString());
		lblScoreBlue.revalidate();
		/* Putting labels on the grid on which the icons for the drawables are drawed */
		for (int j = 0; j < gameGrid.size(); j++) {
			JPanel panel = gameGrid.get(j);
			panel.setLayout(new GridLayout(1, 1));
			JLabel label = new JLabel(drawables.get(j).draw());

			panel.removeAll();
			panel.add(label);
			panel.revalidate();
		}
		this.revalidate();
	}

	/**
	 * Setting up the neighbors of the tiles on the grid
	 */
	private void setUpNeighbors() {
		LOGGER.log(Level.FINE, "Setting up neighbors");
		Tile t = null;
		for (int i = 0; i < GRIDSIZE * GRIDSIZE; ++i) {
			t = cast(drawables.get(i));
			if (i > GRIDSIZE - 1 && t != null)
				t.setNeighbour(Direction.NORTH, cast(drawables.get(i - GRIDSIZE)));
			if (i % GRIDSIZE != GRIDSIZE - 1 && t != null)
				t.setNeighbour(Direction.EAST, cast(drawables.get(i + 1)));
			if (i % GRIDSIZE != 0 && t != null)
				t.setNeighbour(Direction.WEST, cast(drawables.get(i - 1)));
			if (i < GRIDSIZE * GRIDSIZE - GRIDSIZE && t != null)
				t.setNeighbour(Direction.SOUTH, cast(drawables.get(i + GRIDSIZE)));

		}
	}

	/**
	 * Getting the represented tile of a drawabletile
	 * @param d the drawable	
	 * @return the tile
	 */
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
	 * getter of the workers
	 * @param red true if the red worker is asked for
	 * @return the worker
	 */
	public static Worker getWorker(boolean red) {
		if (red)
			return redWorker.getWorker();
		else
			return blueWorker.getWorker();
	}

	/**
	 * this method is needed so that the gui implements the steppable interface
	 */
	@Override
	public void step() {
		if (Game.getInstance().getCurrentmap() != null) {
			int timeinticks = Game.getInstance().getCurrentmap().getTicksRemain();
			if (Game.getInstance().getCurrentmap().getTicksRemain().compareTo(-1) == 0) {
				Timer.getInstance().removeSteppable(this);
				Controller.setAcceptinput(false);
			}
			int timeinsecs = timeinticks / (1000 / Timer.getMilisecstowait());
			if (Game.getInstance().getCurrentmap().getTicksRemain().compareTo(-1) == 0)
				lblTimer.setText("GAME OVER");
			else
				lblTimer.setText(timeinsecs / 60 + ":" + timeinsecs % 60);
		}

	}
}
