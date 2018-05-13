/**
 * 
 */
package gui;

import java.awt.Graphics;
import java.io.Serializable;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.ImageIcon;

import logic.*;

/**
 * @author krist
 *
 */
public class GraphicWorker extends AbstractGraphic implements Disappearable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3588418911330272336L;
	private static final Logger LOGGER = Logger.getLogger( GraphicWorker.class.getName() );
	private Worker worker;
	/**
	 * 
	 */
	public GraphicWorker() {
		super(IconCollection.getInstance().getWorkerBlue());
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		
		LOGGER.log(Level.FINE,"GraphicWorker created with default constructor");
		worker=new Worker(100);
	}

	/**
	 * @param _img
	 */
	public GraphicWorker(ImageIcon oimg) {
		super(oimg);
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		
		LOGGER.log(Level.FINE,"GraphicWorker created with oimg constructor");
		worker=new Worker(100);
	}
	
	/**
	 * @param _img
	 */
	public GraphicWorker(ImageIcon oimg, Worker oworker) {
		super(oimg);
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		
		LOGGER.log(Level.FINE,"GraphicWorker created with all parameters");
		setWorker(oworker);
	}
	
	/**
	 * @param _img
	 */
	public GraphicWorker(Worker oworker) {
		super(IconCollection.getInstance().getWorkerBlue());
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		
		LOGGER.log(Level.FINE,"GraphicWorker created");
		setWorker(oworker);
	}

	/* (non-Javadoc)
	 * @see gui.Drawable#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g) {
		// Auto-generated method stub

	}

	/**
	 * @return the worker
	 */
	public Worker getWorker() {
		return worker;
	}

	/**
	 * @param worker the worker to set
	 */
	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	@Override
	public void disappear() {
		// Auto-generated method stub
		
	}

}
