/**
 * 
 */
package gui;

import java.awt.Graphics;
import java.util.logging.Logger;

import javax.swing.ImageIcon;

import logic.Crate;

/**
 * @author krist
 *
 */
public class GraphicWall extends AbstractGraphic {
	private static final Logger LOGGER = Logger.getLogger( Crate.class.getName() );
	/**
	 * 
	 */
	public GraphicWall() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param _img
	 */
	public GraphicWall(ImageIcon _img) {
		super(_img);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see gui.Drawable#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

}
