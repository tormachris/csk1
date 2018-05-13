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
public class GraphicSwitch extends AbstractGraphic {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5925419797962477106L;
	private static final Logger LOGGER = Logger.getLogger( GraphicSwitch.class.getName() );
	private Switch switcho;
	/**
	 * 
	 */
	public GraphicSwitch() {
		super(IconCollection.getInstance().getButton());
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		
		LOGGER.log(Level.FINE,"GraphicSwitch created");
	}

	public GraphicSwitch(Switch oswitch) {
		super(IconCollection.getInstance().getButton());
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		
		LOGGER.log(Level.FINE,"GraphicSwitch created");
		setSwitcho(oswitch); //SO FUNKY
	}

	/* (non-Javadoc)
	 * @see gui.Drawable#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(JLabel label) {
		// Auto-generated method stub

	}

	/**
	 * @return the switcho
	 */
	public Switch getSwitcho() {
		return switcho;
	}

	/**
	 * @param switcho the switcho to set
	 */
	public void setSwitcho(Switch switcho) {
		this.switcho = switcho;
	}

}
