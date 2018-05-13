package gui;

import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;


import logic.Crate;

public class GraphicCrate extends AbstractGraphic {
	private static final Logger LOGGER = Logger.getLogger( GraphicCrate.class.getName() );
	private Crate crate;
	
	public GraphicCrate() {
		super(IconCollection.getInstance().getBox());
		LOGGER.log(Level.FINE,"GraphicCrate created");
	}
	
	public GraphicCrate(Crate ocrate) {
		super(IconCollection.getInstance().getBox());
		LOGGER.log(Level.FINE,"GraphicCrate created");
		setCrate(ocrate);
	}

	@Override
	public void draw(Graphics g) {
		// Auto-generated method stub

	}

	/**
	 * @return the crate
	 */
	public Crate getCrate() {
		return crate;
	}

	/**
	 * @param crate the crate to set
	 */
	public void setCrate(Crate crate) {
		this.crate = crate;
	}

}
