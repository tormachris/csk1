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
	public ImageIcon draw() { 

		if(switcho.getThing() != null)
		{
			if(switcho.getThing().getClass().equals(Crate.class))
				return IconCollection.getInstance().getBox();
			if(switcho.getThing().getClass().equals(Worker.class))
				if(SokobanGui.getInstance().getWorker(true).equals(switcho.getThing()))
					return IconCollection.getInstance().getBlueontile();
				else
					return IconCollection.getInstance().getBlueontile();
				
		}
		else
		{
			if(switcho.getFrictionMod() != null)
			{
				if(switcho.getFrictionMod().getFriction() < 1)
					return IconCollection.getInstance().getButtonoil();
				else
					return IconCollection.getInstance().getButtonhoney();
			}
			return IconCollection.getInstance().getButton();
		}	
		return null;
	

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
