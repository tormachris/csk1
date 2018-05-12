/**
 * 
 */
package gui;

import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

import logic.*;

/**
 * @author krist
 *
 */
public class GraphicSwitch extends AbstractGraphic {
	private static final Logger LOGGER = Logger.getLogger( GraphicSwitch.class.getName() );
	private Switch switch_;
	/**
	 * 
	 */
	public GraphicSwitch() {
		super(IconCollection.getInstance().getButton());
		LOGGER.log(Level.FINE,"GraphicSwitch created");
	}

	public GraphicSwitch(Switch _switch) {
		super(IconCollection.getInstance().getButton());
		LOGGER.log(Level.FINE,"GraphicSwitch created");
		switch_=_switch; //SO FUNKY
	}

	/* (non-Javadoc)
	 * @see gui.Drawable#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

}
