/**
 * 
 */
package gui;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.*;
import logic.*;

/**
 * @author krist
 *Wall to be drawn to the viewers eyes
 */
public class GraphicWall extends AbstractGraphic {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 5452874926648909735L;
	private static final Logger LOGGER = Logger.getLogger( GraphicWall.class.getName() );
	private Wall wall;
	/**
	 * Constructor
	 */
	public GraphicWall() {
		super(IconCollection.getInstance().getWall());
		//Logger
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		
		LOGGER.log(Level.FINE,"GraphicWall created");
	}

	/**
	 * Other constructor
	 */
	public GraphicWall(Wall owall) {
		super(IconCollection.getInstance().getWall());
		//Logger
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		setWall(owall);//Wall
	}

	/**
	 * Drawing the wall.
	 */
	@Override
	public ImageIcon draw() {
		return IconCollection.getInstance().getWall(); //It's a wall. What can I say?
	}

	/**
	 * @return the wall
	 */
	public Wall getWall() {
		return wall;
	}

	/**
	 * @param wall the wall to set
	 */
	public void setWall(Wall wall) {
		this.wall = wall;
	}

}
