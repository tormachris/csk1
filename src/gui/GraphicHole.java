/**
 * 
 */
package gui;

import java.awt.*;
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
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		
		LOGGER.log(Level.FINE,"GraphicHole created");
	}

	public GraphicHole(Hole ohole) {
		super(IconCollection.getInstance().getHole());
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		
		LOGGER.log(Level.FINE,"GraphicHole created");
		setHole(ohole);
	}

	/* (non-Javadoc)
	 * @see gui.Drawable#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		// Auto-generated method stub

	}

	/**
	 * @return the hole
	 */
	public Hole getHole() {
		return hole;
	}

	/**
	 * @param hole the hole to set
	 */
	public void setHole(Hole hole) {
		this.hole = hole;
	}

}
