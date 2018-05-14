/**
 * 
 */
package gui;

import java.io.Serializable;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.swing.ImageIcon;


import logic.*;

/**
 * @author krist
 *The most useless class in this project. This does nothing
 */
public class GraphicWorker extends AbstractGraphic implements Disappearable, Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = -3588418911330272336L;
	private static final Logger LOGGER = Logger.getLogger( GraphicWorker.class.getName() );//Logger
	private Worker worker;
	/**
	 * Constructor
	 */
	public GraphicWorker() {
		super(IconCollection.getInstance().getBlueontile());
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		worker=new Worker(100);
	}

	/**
	 * Other constructor
	 * @param _img
	 */
	public GraphicWorker(ImageIcon oimg) {
		super(oimg);
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		worker=new Worker(100);
	}
	
	/**
	 * Yet another constructor
	 * @param _img
	 */
	public GraphicWorker(ImageIcon oimg, Worker oworker) {
		super(oimg);
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		setWorker(oworker);
	}
	
	/**
	 * Last constructor
	 * @param _img
	 */
	public GraphicWorker(Worker oworker) {
		super(IconCollection.getInstance().getBlueontile());
		
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
		setWorker(oworker);
	}

	/**
	 * Drawing the worker
	 * We have nothing to do, the tile takes care of this
	 */
	@Override
	public ImageIcon draw() {
		return null;

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
