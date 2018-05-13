package gui;

import java.awt.*;
import java.util.logging.*;

import logic.EndTile;

public class GraphicEndTile extends AbstractGraphic {
	private static final Logger LOGGER = Logger.getLogger( GraphicEndTile.class.getName() );
	private EndTile endtile;
	public GraphicEndTile() {
		super(IconCollection.getInstance().getTarget());
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		
		LOGGER.log(Level.FINE,"GraphicEndTle created");
	}

	public GraphicEndTile(EndTile oendtile) {
		super(IconCollection.getInstance().getTarget());
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		
		LOGGER.log(Level.FINE,"GraphicEndTle created");
		setEndtile(oendtile);
	}

	@Override
	public void draw(Graphics g) {
		// Auto-generated method stub

	}

	/**
	 * @return the endtile
	 */
	public EndTile getEndtile() {
		return endtile;
	}

	/**
	 * @param endtile the endtile to set
	 */
	public void setEndtile(EndTile endtile) {
		this.endtile = endtile;
	}

}
