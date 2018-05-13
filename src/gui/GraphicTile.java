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
public class GraphicTile extends AbstractGraphic {
	private static final Logger LOGGER = Logger.getLogger( GraphicTile.class.getName() );
	private Tile tile;
	/**
	 * 
	 */
	public GraphicTile() {
		super(IconCollection.getInstance().getFloor());
		LOGGER.log(Level.FINE,"GraphicTile created");
	}

	
	public GraphicTile(Tile otile) {
		super(IconCollection.getInstance().getFloor());
		LOGGER.log(Level.FINE,"GraphicTile created");
		setTile(otile);
	}

	/* (non-Javadoc)
	 * @see gui.Drawable#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		// Auto-generated method stub

	}


	/**
	 * @return the tile
	 */
	public Tile getTile() {
		return tile;
	}


	/**
	 * @param tile the tile to set
	 */
	public void setTile(Tile tile) {
		this.tile = tile;
	}

}
