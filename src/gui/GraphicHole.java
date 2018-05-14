/**
 * 
 */
package gui;

import java.util.logging.*;

import javax.swing.*;

import logic.*;

/**
 * Hole to screen class
 * @author krist
 *
 */
public class GraphicHole extends AbstractGraphic {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 2218415094782645305L;
	private static final Logger LOGGER = Logger.getLogger(GraphicHole.class.getName());
	private Hole hole;

	/**
	 * A constructor
	 */
	public GraphicHole() {
		super(IconCollection.getInstance().getHole());

		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
	}
	/**
	 * A constructor
	 */
	public GraphicHole(Hole ohole) {
		super(IconCollection.getInstance().getHole());

		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		setHole(ohole);
	}

	/**
	 * A constructor
	 */
	@Override
	public ImageIcon draw() {
		//Checking if the Hole is open, if it is, it draws the Hole.
		if (hole.getOpen()) {
			return IconCollection.getInstance().getHole();//If a hole is open, it can only be a hole

		}
		//Checking if there is any Thing on the Hole.
		if (hole.getThing() != null) {
			return drawWithThing();//If it is closed, it acts as a regular tile
		//Draws the FrictionModifiers on the closed Hole.
		} else {
			if (hole.getFrictionMod().getClass().equals(Oil.class))
				return IconCollection.getInstance().getOil();
			if (hole.getFrictionMod().getClass().equals(Honey.class))
				return IconCollection.getInstance().getHoney();
			if (hole.getFrictionMod().getClass().equals(FrictionModifier.class))
				return IconCollection.getInstance().getFloor();

		}
		return null;

	}
	/**
	 * Draws a worker or crate depending on the Thing's class.
	 */
	private ImageIcon drawWithThing() {
		if (hole.getThing().getClass().equals(Crate.class))//Draw different
			return IconCollection.getInstance().getBox();//Types of
		if (hole.getThing().getClass().equals(Worker.class))//Things
			if (SokobanGui.getWorker(true).equals(hole.getThing()))
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
	 * @param hole
	 *            the hole to set
	 */
	public void setHole(Hole hole) {
		this.hole = hole;
	}

}
