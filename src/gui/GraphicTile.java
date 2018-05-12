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
public class GraphicTile extends AbstractGraphic {
	private static final Logger LOGGER = Logger.getLogger( Crate.class.getName() );
	/**
	 * 
	 */
	public GraphicTile() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param _img
	 */
	public GraphicTile(ImageIcon _img) {
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
