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
 * Switch to grid
 * @author krist
 *
 */
public class GraphicSwitch extends AbstractGraphic {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5925419797962477106L;//UID
	private static final Logger LOGGER = Logger.getLogger(GraphicSwitch.class.getName());//Logger
	private Switch switcho;

	/**
	 * Constructor
	 */
	public GraphicSwitch() {
		super(IconCollection.getInstance().getButton());
		//Setup
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
	}
	/**
	 * Constructor
	 */
	public GraphicSwitch(Switch oswitch) {
		super(IconCollection.getInstance().getButton());
		//Setup
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		setSwitcho(oswitch); // SO FUNKY
	}

	
	/**
	 * Drawing the switch
	 */
	@Override
	public ImageIcon draw() {
		//Checks if the Thing on the Switch is null or not, if not it draws it.
		if (switcho.getThing() != null) {
			return drawWithThing();
		//Draws the FrictionModifiers on the Switch.
		} else {
			if (switcho.getFrictionMod().getClass().equals(Oil.class))
				return IconCollection.getInstance().getButtonoil();
			if (switcho.getFrictionMod().getClass().equals(Honey.class))
				return IconCollection.getInstance().getButtonhoney();
			if (switcho.getFrictionMod().getClass().equals(FrictionModifier.class))
				return IconCollection.getInstance().getButton();
		}
		return null;

	}
	/**
	*Draws a worker or crate depending on the Thing's class.
	*/
	private ImageIcon drawWithThing() {
		//Things should be drawn like this
		if (switcho.getThing().getClass().equals(Crate.class))
			return IconCollection.getInstance().getBox();
		if (switcho.getThing().getClass().equals(Worker.class))
			if (SokobanGui.getWorker(true).equals(switcho.getThing()))
				return IconCollection.getInstance().getRedontile();
			else
				return IconCollection.getInstance().getBlueontile();
		return null;
	}

	/**
	 * @return the switcho
	 */
	public Switch getSwitcho() {
		return switcho;
	}

	/**
	 * @param switcho
	 *            the switcho to set
	 */
	public void setSwitcho(Switch switcho) {
		this.switcho = switcho;
	}

}
