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
/**
 * Our window into the world of the Killer Sokoban
 */
public class SokobanGui extends JFrame implements Steppable {
	/**
	 * Generated serialization UID
	 */
	private static final long serialVersionUID = 2897342800007758713L;
	private static final Logger LOGGER = Logger.getLogger(SokobanGui.class.getName());//Logger
	private static final short GRIDSIZE = 11;//Constant size of the grid
	private static SokobanGui instance = null;//Instance, since this is a singleton
	private ArrayList<JPanel> gameGrid;//GameGrid in gui elements
	private ArrayList<Drawable> drawables;//GameGrid in gui-to-model elements
	private static GraphicWorker blueWorker;//BATMAN
	private static GraphicWorker redWorker;//Joker
	private JLabel lblScoreBlue;//Score of batman
	private JLabel lblScoreRed;//Score of joker
	private JLabel lblTimer;//TIME
	private File chosenFile;//A file. Spoilers.

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
		//Init the collections
		gameGrid = new ArrayList<>();
		drawables = new ArrayList<>();

		//Init the GUI
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
		setResizable(false);//MAGIC
		this.setTitle("KILLER SOKOBAN!!");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//We will have a single frame
		this.setBounds(100, 100, 792, 75 + 792); // shrug
		this.setIconImage(IconCollection.getInstance().getCsupor().getImage());//Favicon.ico
		/* Upper menu and menu items  */

		JMenuBar menuBar = new JMenuBar();//The menubar on top
		this.setJMenuBar(menuBar);//Setting the menubar

		JMenu mnFile = new JMenu("File");//File menu
		menuBar.add(mnFile);
		
		JMenu mnInfo = new JMenu("Info");//Info menu
		menuBar.add(mnInfo);
		
		JMenuItem mntmHelp = new JMenuItem("Help");//Some help with the controls
		mntmHelp.addActionListener((java.awt.event.ActionEvent evt) -> {
			String infoMessage=
					"Moving the Batman:\n" +
					"WASD\n" +
					"Dropping Oil and Honey with Batman:\n" +
					"Q and E\n" +
					"Moving Joker:\n" +
					"Arrow keys\n" +
					"Dropping Oil and Honey with Joker:\n" +
					"NUMPAD 1 and NUMPAD 2";
			JOptionPane.showMessageDialog(null, infoMessage, "Controls", JOptionPane.INFORMATION_MESSAGE);
		});
		mnInfo.add(mntmHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");//About Us
		mntmAbout.addActionListener((java.awt.event.ActionEvent evt) -> {
			String infoMessage=
					"CSK-1 Szoftver Projekt Labor\n" +
					"Konzulens: Rácz Gergely\n" +
					"Csapattagok\n" +
					"Torma Kristóf\n" +
					"Tóth Vince\n" +
					"Tóth Dániel Péter\n" +
					"Pünkösd Marcell\n" +
					"Veres Csaba Miklós";
			JOptionPane.showMessageDialog(null, infoMessage, "About KILLER SOKOBAN", JOptionPane.INFORMATION_MESSAGE);
		});
		mnInfo.add(mntmAbout);
		
		JMenuItem mntmNewGame = new JMenuItem("New Game");//New game. Resets everything
		mntmNewGame.addActionListener((java.awt.event.ActionEvent evt) -> initializeLevel());
		mnFile.add(mntmNewGame);

		JMenuItem mntmLoadGame = new JMenuItem("Load Game");//Load a map from file
		mnFile.add(mntmLoadGame);
		mntmLoadGame.addActionListener((java.awt.event.ActionEvent evt) -> {
			JFileChooser fc=new JFileChooser();
			int returnVal = fc.showOpenDialog(this);

	        if (returnVal == JFileChooser.APPROVE_OPTION) {
	        	Timer.getInstance().setRunning(false);
	        	chosenFile = fc.getSelectedFile();
	        	initializeLevel();
	        }
		});
		this.getContentPane().setLayout(new BorderLayout(0, 0));//BorderLayout with 0,0 parameters

		/* Upper Panel (Time and points) */

		Font scoreFont = new Font("Tahoma", Font.PLAIN, 20);//Font used to display scores

		JPanel panelTop = new JPanel();//The top half of the screen with the scores and all that
		this.getContentPane().add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new BorderLayout(0, 0));
		panelTop.setPreferredSize(new Dimension(792, 75));

		JPanel panelScoreBlue = new JPanel(); //BATMAN
		panelTop.add(panelScoreBlue, BorderLayout.WEST);
		panelScoreBlue.setBorder(new EmptyBorder(0, 5, 10, 5));
		lblScoreBlue = new JLabel("---");
		panelScoreBlue.add(lblScoreBlue);
		//Grafx
		lblScoreBlue.setFont(scoreFont);
		lblScoreBlue.setForeground(Color.BLUE);
		lblScoreBlue.setHorizontalAlignment(SwingConstants.LEFT);

		JPanel panelScoreRed = new JPanel();//Joker
		panelTop.add(panelScoreRed, BorderLayout.EAST);
		panelScoreRed.setBorder(new EmptyBorder(0, 5, 10, 5));
		//Grafx
		lblScoreRed = new JLabel("---");
		panelScoreRed.add(lblScoreRed);
		lblScoreRed.setFont(scoreFont);
		lblScoreRed.setForeground(Color.RED);
		lblScoreRed.setHorizontalAlignment(SwingConstants.RIGHT);

		JPanel panelTimerContainer = new JPanel();//Middle section
		panelTop.add(panelTimerContainer, BorderLayout.NORTH);
		//Grafx
		lblTimer = new JLabel("--:--");
		panelTimerContainer.add(lblTimer);
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimer.setFont(new Font("Tahoma", Font.BOLD, 28));

		/* Also panel (maga a game) */

		JPanel panelMain = new JPanel();//The game, my dude
		this.getContentPane().add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(new GridLayout(GRIDSIZE, GRIDSIZE, 0, 0));
		panelMain.setPreferredSize(new Dimension(792, 792));

		/*Creating the grid for the map */
		int i;
		boolean grey = false;
		for (i = 0; i < (GRIDSIZE * GRIDSIZE); i++) {
			JPanel p = new JPanel();
			//Setting up the panels to be ready to display dank memery
			if (grey)
				p.setBackground(Color.LIGHT_GRAY);
			else
				p.setBackground(Color.WHITE);
			//Grey-nogrey. Mid-life crisis.
			grey = !grey;
			gameGrid.add(p);
			panelMain.add(p);
		}
		
		initializeLevel();//Initialize the level itself

		LOGGER.log(Level.FINE, "GUI Initialized");
	}
	/**
	 * Initializing the level based on the level loader
	 */
	private void initializeLevel() {
		LevelLoader ll = new LevelLoader();//Initializing my level loader
		Queue<LevelElements> map;//This is where our map will be
		if(chosenFile==null)map=ll.getLevel(LevelStorage.PRESENTLEVEL);//Load the default level
		else map=ll.getLevelFromFile(chosenFile);//If we load from file, load it from a file
		chosenFile=null;//Next time, we will not be loading from a file. Unless we will be.
		if(map.size()<GRIDSIZE*GRIDSIZE) return;
		Map m = new Map();//Do all this on a new map.
		Game.getInstance().clearMaps();//With a blank slate.
		Game.getInstance().addMap(m);//Add it to the game
		Game.getInstance().setCurrentmap(m);//Start it up.
		LinkedList<GraphicHole> lastholes = new LinkedList<>();//Consumer
		drawables.clear();//Clear it, just in case
		Controller.setAcceptinput(true);//Reset the controller
		for (int j = 0; j < GRIDSIZE * GRIDSIZE; ++j) {
			switch (map.remove()) {
			//Iterate through the map and process the items
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
		
		//New workers, new controller
		this.addKeyListener(new Controller(blueWorker, redWorker));
		
		setUpNeighbors(); //MAGIC OF BASIC MATHS

		drawAll();//Draw all this crazyness
		
		//Reset the timer and start it
		Timer.getInstance().setRunning(true);
		try{
			Timer.getInstance().addSteppable(this);//Register our map as a steppable
		}catch(Exception ex) {
			LOGGER.log(Level.FINE, ex.getMessage(), ex);
		}
	}
	
	/**
	 * Draws every drawable on the map
	 */
	public void drawAll() {
		//Write the scores to screen
		lblScoreRed.setText(redWorker.getWorker().getPoints().toString());
		lblScoreRed.revalidate();
		lblScoreBlue.setText(blueWorker.getWorker().getPoints().toString());
		lblScoreBlue.revalidate();
		/* Putting labels on the grid on which the icons for the drawables are drawed */
		for (int j = 0; j < gameGrid.size(); j++) {
			JPanel panel = gameGrid.get(j);
			panel.setLayout(new GridLayout(1, 1));
			JLabel label = new JLabel(drawables.get(j).draw());
			//We clear each panel and then put the new "picture" on it.
			//Cool, ha?
			panel.removeAll();
			panel.add(label);
			panel.revalidate();
		}
		//Invalidate it, so it gets redrawn
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
			//This is who our neighbors are if we know how big the playingfield is
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
		//Some casting memes
		//NOT a casting couch
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
		if (Game.getInstance().getCurrentmap() != null) {//If there is no current map, this would be bad
			int timeinticks = Game.getInstance().getCurrentmap().getTicksRemain();//How much time is left
			if (Game.getInstance().getCurrentmap().getTicksRemain().compareTo(-1) == 0) {//If it's all over
				Timer.getInstance().removeSteppable(this);//We remove us from steppiness
				Controller.setAcceptinput(false);//And stop accepting input from the keyboard, since the game is over
			}
			int timeinsecs = timeinticks / (1000 / Timer.getMilisecstowait());//How much time is left, in seconds
			if (Game.getInstance().getCurrentmap().getTicksRemain().compareTo(-1) == 0)//Print game over
				lblTimer.setText("GAME OVER");
			else
				lblTimer.setText(timeinsecs / 60 + ":" + timeinsecs % 60);//Format it and print it.
		}

	}
}
