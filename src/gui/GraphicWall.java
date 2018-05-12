/**
 * 
 */
package gui;

import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

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
		LOGGER.log(Level.FINE,"GraphicWall created");
	}

	/**
	 * 
	 */
	public GraphicWall(Wall _wall) {
		super(IconCollection.getInstance().getWall());
		LOGGER.log(Level.FINE,"GraphicWall created");
		wall=_wall;
	}


	/* (non-Javadoc)
	 * @see gui.Drawable#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

}
