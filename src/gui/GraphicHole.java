/**
 * 
 */
package gui;

import java.awt.Graphics;
import java.util.logging.*;

import logic.*;

/**
 * @author krist
 *
 */
public class GraphicHole extends AbstractGraphic {
	private static final Logger LOGGER = Logger.getLogger( GraphicHole.class.getName() );
	private Hole hole;
	/**
	 * 
	 */
	public GraphicHole() {
		super(IconCollection.getInstance().getHole());
		LOGGER.log(Level.FINE,"GraphicHole created");
	}

	public GraphicHole(Hole _hole) {
		super(IconCollection.getInstance().getHole());
		LOGGER.log(Level.FINE,"GraphicHole created");
		hole=_hole;
	}

	/* (non-Javadoc)
	 * @see gui.Drawable#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

}
