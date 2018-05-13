/**
 * 
 */
package gui;

import java.util.logging.*;

import javax.swing.*;

import logic.*;

/**
 * @author krist
 *
 */
public class GraphicHole extends AbstractGraphic {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2218415094782645305L;
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
	}

	public GraphicHole(Hole ohole) {
		super(IconCollection.getInstance().getHole());
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		setHole(ohole);
	}

	/* (non-Javadoc)
	 * @see gui.Drawable#draw(java.awt.Graphics)
	 */
	@Override
	public ImageIcon draw() {
		if(hole.getOpen())
		{
			return IconCollection.getInstance().getHole();
			
		}

		if(hole.getThing() == null)
		{
			if(hole.getFrictionMod() == null)
			{
				return IconCollection.getInstance().getFloor();
				
			}
			if(hole.getFrictionMod().getClass().equals(Oil.class))
				return IconCollection.getInstance().getOil();
			if(hole.getFrictionMod().getClass().equals(Honey.class))
				return IconCollection.getInstance().getHoney();
		
		}
		if(hole.getThing().getClass().equals(Crate.class))
			return IconCollection.getInstance().getBox();
		if(hole.getThing().getClass().equals(Worker.class))
			if(SokobanGui.getInstance().getWorker(true).equals(hole.getThing()))
				return IconCollection.getInstance().getRedontile();
			else
				return IconCollection.getInstance().getBlueontile();
		return null; 
	
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
