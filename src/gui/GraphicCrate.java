package gui;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.*;

import logic.Crate;
/**
 * Crate as Drawable
 */
public class GraphicCrate extends AbstractGraphic implements Disappearable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2521107572210618401L;//UID
	private static final Logger LOGGER = Logger.getLogger( GraphicCrate.class.getName() );//LOG with this
	private Crate crate;
	
	public GraphicCrate() {
		super(IconCollection.getInstance().getBox());//Setup the super class
		//Setup the logger
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
	}
	//Other constructors are the same
	public GraphicCrate(Crate ocrate) {
		super(IconCollection.getInstance().getBox());
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		setCrate(ocrate);
	}

	@Override
	public ImageIcon draw() {
		return IconCollection.getInstance().getBox();
		//A box covers the entire portion of the grid it sits on.
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

	@Override
	public void disappear() {
		// Auto-generated method stub
		
	}

}
