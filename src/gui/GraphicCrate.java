package gui;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.*;

import logic.Crate;

public class GraphicCrate extends AbstractGraphic implements Disappearable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2521107572210618401L;
	private static final Logger LOGGER = Logger.getLogger( GraphicCrate.class.getName() );
	private Crate crate;
	
	public GraphicCrate() {
		super(IconCollection.getInstance().getBox());
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
	}
	
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
	public void draw(JLabel label) {
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

	@Override
	public void disappear() {
		// Auto-generated method stub
		
	}

}
