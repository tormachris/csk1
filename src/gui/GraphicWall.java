/**
 * 
 */
package gui;

import java.awt.Graphics;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import logic.*;

/**
 * @author krist
 *
 */
public class GraphicWall extends AbstractGraphic {
	private static final Logger LOGGER = Logger.getLogger( GraphicWall.class.getName() );
	private Wall wall;
	/**
	 * 
	 */
	public GraphicWall() {
		super(IconCollection.getInstance().getWall());
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		
		LOGGER.log(Level.FINE,"GraphicWall created");
	}

	/**
	 * 
	 */
	public GraphicWall(Wall owall) {
		super(IconCollection.getInstance().getWall());
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		
		LOGGER.log(Level.FINE,"GraphicWall created");
		setWall(owall);
	}


	/* (non-Javadoc)
	 * @see gui.Drawable#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		// Auto-generated method stub

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
