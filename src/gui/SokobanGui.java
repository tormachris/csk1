package gui;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.border.*;

public class SokobanGui {
	private static final Logger LOGGER = Logger.getLogger( SokobanGui.class.getName() );
	private JFrame frmKillerSokoban;
	private final int gridSize;

	/**
	 * Create the application.
	 */
	public SokobanGui(int ogridSize) {
		gridSize = ogridSize;
		initialize();
		LOGGER.log( Level.FINE, "GUI Created");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmKillerSokoban = new JFrame();
		frmKillerSokoban.setTitle("KILLER SOKOBAN!!");
		frmKillerSokoban.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmKillerSokoban.setBounds(100, 100, 550, 75+550); //shrug
		
		
		/* Felso menu geci*/
		
		JMenuBar menuBar = new JMenuBar();
		frmKillerSokoban.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewGame = new JMenuItem("New Game");
		mnFile.add(mntmNewGame);
		
		JMenuItem mntmSaveGame = new JMenuItem("Save Game");
		mnFile.add(mntmSaveGame);
		
		JMenuItem mntmLoadGame = new JMenuItem("Load Game");
		mnFile.add(mntmLoadGame);
		frmKillerSokoban.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		
		/* Felso panel (ido es pontok) */
		
		Font scoreFont =  new Font("Tahoma", Font.PLAIN, 20);
		
		JPanel panelTop = new JPanel();
		frmKillerSokoban.getContentPane().add(panelTop, BorderLayout.NORTH);
		panelTop.setLayout(new BorderLayout(0, 0));
		panelTop.setPreferredSize(new Dimension(550,75));
		
		JPanel panelScoreBlue = new JPanel();
		panelTop.add(panelScoreBlue, BorderLayout.WEST);
		panelScoreBlue.setBorder(new EmptyBorder(0,5,10,5));
		
		JLabel lblScoreBlue = new JLabel("---");
		panelScoreBlue.add(lblScoreBlue);
		lblScoreBlue.setFont(scoreFont);
		lblScoreBlue.setForeground(Color.BLUE);
		lblScoreBlue.setHorizontalAlignment(SwingConstants.LEFT);
		
		JPanel panelScoreRed = new JPanel();
		panelTop.add(panelScoreRed, BorderLayout.EAST);
		panelScoreRed.setBorder(new EmptyBorder(0,5,10,5));
		
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
		frmKillerSokoban.getContentPane().add(panelMain, BorderLayout.CENTER);
		panelMain.setLayout(new GridLayout(gridSize, gridSize, 0, 0));
		panelMain.setPreferredSize(new Dimension(550,550));
		
		int i;
		boolean grey = false;
		for(i = 0; i < (gridSize*gridSize); i++) {
			JPanel p = new JPanel();
			
			if (grey)
				p.setBackground(Color.LIGHT_GRAY);
			else
				p.setBackground(Color.WHITE);
			
			grey = !grey;
			panelMain.add(p);
		}
		LOGGER.log( Level.FINE, "GUI Initialized");
	}
	
	public void show() {
		LOGGER.log( Level.FINE, "Showing GUI");
		frmKillerSokoban.setVisible(true);
	}
}
