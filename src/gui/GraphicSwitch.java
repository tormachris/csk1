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
	}

	public GraphicSwitch(Switch oswitch) {
		super(IconCollection.getInstance().getButton());
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		setSwitcho(oswitch); //SO FUNKY
	}

	/* (non-Javadoc)
	 * @see gui.Drawable#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(JLabel label) {

		if(switcho.getThing() == null)
		{
			if(switcho.getFrictionMod() == null)
			{
				label.setIcon(IconCollection.getInstance().getButton());
				return;
			}
			if(switcho.getFrictionMod().getClass().equals(Oil.class))
				label.setIcon(IconCollection.getInstance().getButtonoil());
			if(switcho.getFrictionMod().getClass().equals(Honey.class))
				label.setIcon(IconCollection.getInstance().getButtonhoney());
		return;
		
		}
		if(switcho.getThing().getClass().equals(Crate.class))
			label.setIcon(IconCollection.getInstance().getBox());
		if(switcho.getThing().getClass().equals(Worker.class))
			if(SokobanGui.getInstance().getWorker(true).equals(switcho.getThing()))
				label.setIcon(IconCollection.getInstance().getRedontile());
			else
				label.setIcon(IconCollection.getInstance().getBlueontile());
	

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
