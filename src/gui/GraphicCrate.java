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
	
	public GraphicCrate(Crate _crate) {
		super(IconCollection.getInstance().getBox());
		LOGGER.log(Level.FINE,"GraphicCrate created");
		crate=_crate;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

}
