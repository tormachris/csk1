package gui;

import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.EndTile;

public class GraphicEndTile extends AbstractGraphic {
	private static final Logger LOGGER = Logger.getLogger( GraphicEndTile.class.getName() );
	private EndTile endtile;
	public GraphicEndTile() {
		super(IconCollection.getInstance().getTarget());
		LOGGER.log(Level.FINE,"GraphicEndTle created");
	}

	public GraphicEndTile(EndTile _endtile) {
		super(IconCollection.getInstance().getTarget());
		LOGGER.log(Level.FINE,"GraphicEndTle created");
		endtile=_endtile;
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

}
