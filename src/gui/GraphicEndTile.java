package gui;

import java.util.logging.*;

import javax.swing.*;

import logic.*;
/**
 * EndTile on the screen
 */
public class GraphicEndTile extends AbstractGraphic {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 1454880402469371046L;//UID
	private static final Logger LOGGER = Logger.getLogger( GraphicEndTile.class.getName() );//Logger
	private EndTile endtile;//The endtile we want to display
	public GraphicEndTile() {
		//Setup the superclass
		super(IconCollection.getInstance().getTarget());
		//Setup the logger to log to console
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
	}

	public GraphicEndTile(EndTile oendtile) {
		super(IconCollection.getInstance().getTarget());
		//Log to console
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		setEndtile(oendtile);
	}

	@Override
	public ImageIcon draw() { 
		if(endtile.getThing() != null)
			return drawWithThing();//If it has a thing on it, we draw that
		else
			return drawWithoutThing(); //If it does not, we still have some work left
	}

	/**
	 * Draws the EndTile with a Thing on it
	 * @return the ImageIcon to draw
	 */
	private ImageIcon drawWithThing() {
		if(endtile.getThing().getClass().equals(Crate.class))//A crate covers the entire thing
			return IconCollection.getInstance().getBox();
		else if(endtile.getThing().getClass().equals(Worker.class))//We have two types of workers
			if(SokobanGui.getWorker(true).equals(endtile.getThing()))
				return IconCollection.getInstance().getRedontile();
			else
				return IconCollection.getInstance().getBlueontile();
		return null;
	}
	/**
	 * Draws the EndTile without any Thing on it
	 * @return the ImageIcon to draw
	 */
	private ImageIcon drawWithoutThing() {
		if(endtile.getFrictionMod().getClass().equals(Oil.class))//We can put frictionmodifiers on the thing
			return IconCollection.getInstance().getEndoil();
		else if(endtile.getFrictionMod().getClass().equals(Honey.class))
			return IconCollection.getInstance().getEndhoney();
		else if(endtile.getFrictionMod().getClass().equals(FrictionModifier.class))
			return IconCollection.getInstance().getTarget();
		return null;
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
