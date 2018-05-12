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
public class GraphicWorker extends AbstractGraphic {
	private static final Logger LOGGER = Logger.getLogger( GraphicWorker.class.getName() );
	private Worker worker;
	/**
	 * 
	 */
	public GraphicWorker() {
		super(IconCollection.getInstance().getWorkerBlue());
		LOGGER.log(Level.FINE,"GraphicWorker created");
	}

	/**
	 * @param _img
	 */
	public GraphicWorker(ImageIcon _img) {
		super(_img);
		LOGGER.log(Level.FINE,"GraphicWorker created");
	}
	
	/**
	 * @param _img
	 */
	public GraphicWorker(ImageIcon _img, Worker _worker) {
		super(_img);
		LOGGER.log(Level.FINE,"GraphicWorker created");
		worker=_worker;
	}
	
	/**
	 * @param _img
	 */
	public GraphicWorker(Worker _worker) {
		super(IconCollection.getInstance().getWorkerBlue());
		LOGGER.log(Level.FINE,"GraphicWorker created");
		worker=_worker;
	}

	/* (non-Javadoc)
	 * @see gui.Drawable#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

}
