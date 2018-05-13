/**
 * 
 */
package gui;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.*;

import logic.*;

/**
 * @author krist
 *
 */
public class GraphicTile extends AbstractGraphic {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2696615332414413283L;
	private static final Logger LOGGER = Logger.getLogger( GraphicTile.class.getName() );
	private Tile tile;
	/**
	 * 
	 */
	public GraphicTile() {
		super(IconCollection.getInstance().getFloor());
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
	}

	
	public GraphicTile(Tile otile) {
		super(IconCollection.getInstance().getFloor());
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		setTile(otile);
	}

	/* (non-Javadoc)
	 * @see gui.Drawable#draw(java.awt.Graphics)
	 */
	@Override
	public ImageIcon draw() { 
 /*
		if(tile.getThing() == null)
		{
			if(tile.getFrictionMod() == null)
			{
				return IconCollection.getInstance().getFloor();
			}
			if(tile.getFrictionMod().getClass().equals(Oil.class))
				return IconCollection.getInstance().getOil();
			if(tile.getFrictionMod().getClass().equals(Honey.class))
				return IconCollection.getInstance().getHoney();
		
		}
		if(tile.getThing().getClass().equals(Crate.class))
			return IconCollection.getInstance().getBox();
		if(tile.getThing().getClass().equals(Worker.class))
			if(SokobanGui.getInstance().getWorker(true).equals(tile.getThing()))
				return IconCollection.getInstance().getRedontile();
			else
				return IconCollection.getInstance().getBlueontile();
		return null;
	
*/
		if(tile.getThing() != null)
		{
			if(tile.getThing().getClass().equals(Crate.class))
				return IconCollection.getInstance().getBox();
			if(tile.getThing().getClass().equals(Worker.class))
				if(SokobanGui.getWorker(true).equals(tile.getThing()))
					return IconCollection.getInstance().getRedontile();
				else
				{
					return IconCollection.getInstance().getBlueontile();
				}
				
		}
		else
		{
			
				if(tile.getFrictionMod().getClass().equals(Oil.class))
					return IconCollection.getInstance().getOil();
				if(tile.getFrictionMod().getClass().equals(Honey.class))
					return IconCollection.getInstance().getHoney();
				if(tile.getFrictionMod().getClass().equals(FrictionModifier.class))
					return IconCollection.getInstance().getFloor();
		}	
		return null;
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
