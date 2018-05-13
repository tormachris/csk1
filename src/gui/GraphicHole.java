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
	public void draw(JLabel label) {
		if(hole.getOpen())
		{
			label.setIcon(IconCollection.getInstance().getHole());
			return;
		}

		if(hole.getThing() == null)
		{
			if(hole.getFrictionMod() == null)
			{
				label.setIcon(IconCollection.getInstance().getFloor());
				return;
			}
			if(hole.getFrictionMod().getClass().equals(Oil.class))
				label.setIcon(IconCollection.getInstance().getOil());
			if(hole.getFrictionMod().getClass().equals(Honey.class))
				label.setIcon(IconCollection.getInstance().getHoney());
		return;
		
		}
		if(hole.getThing().getClass().equals(Crate.class))
			label.setIcon(IconCollection.getInstance().getBox());
		if(hole.getThing().getClass().equals(Worker.class))
			if(SokobanGui.getInstance().getWorker(true).equals(hole.getThing()))
				label.setIcon(IconCollection.getInstance().getRedontile());
			else
				label.setIcon(IconCollection.getInstance().getBlueontile());
	
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
