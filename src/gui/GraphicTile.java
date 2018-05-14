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
 * A general tile on the screen. So boring.
 * @author krist
 *
 */
public class GraphicTile extends AbstractGraphic {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 2696615332414413283L;
	private static final Logger LOGGER = Logger.getLogger(GraphicTile.class.getName());
	private Tile tile;

	/**
	 * Constructor
	 */
	public GraphicTile() {
		super(IconCollection.getInstance().getFloor());
		//The meme
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
	}
	/**
	 * Constructor
	 */
	public GraphicTile(Tile otile) {
		super(IconCollection.getInstance().getFloor());
		//Real deal
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		setTile(otile);
	}

	/*
	 *
	 * 
	 * @see gui.Drawable#draw(java.awt.Graphics)
	 */
	@Override
	public ImageIcon draw() {
		if (tile.getThing() != null) {
			return drawWithThing();

		} else {
			//Just to put things into perspective:
			//It is now half pas two in the morning
			//And I am commenting this god forsaken project all alone
			//If you are reading this, have mercy on my soul and be kind
			if (tile.getFrictionMod().getClass().equals(Oil.class))
				return IconCollection.getInstance().getOil();
			if (tile.getFrictionMod().getClass().equals(Honey.class))
				return IconCollection.getInstance().getHoney();
			if (tile.getFrictionMod().getClass().equals(FrictionModifier.class))
				return IconCollection.getInstance().getFloor();
		}
		return null;
	}
	/*
	 *Drawing a thing that is on this tile
	 */
	private ImageIcon drawWithThing() {
		//Things on tiles are generally regarded as dangerous animals
		//I have lost the plot so fast
		//I am amazed
		if (tile.getThing().getClass().equals(Crate.class))
			return IconCollection.getInstance().getBox();
		if (tile.getThing().getClass().equals(Worker.class))
			if (SokobanGui.getWorker(true).equals(tile.getThing()))
				return IconCollection.getInstance().getRedontile();
			else {
				return IconCollection.getInstance().getBlueontile();
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
	 * @param tile
	 *            the tile to set
	 */
	public void setTile(Tile tile) {
		this.tile = tile;
	}

}
