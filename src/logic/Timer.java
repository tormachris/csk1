package logic;

import java.io.Serializable;
import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Represents Timer that tics every once in a while.
 */
public class Timer extends Thread implements Serializable {
	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = -777532793085920701L;
	private static final Logger LOGGER = Logger.getLogger(Timer.class.getName());//Logger of this class
	private transient Set<Steppable> steppables; // We don't want to step something twice, do we?

	private static final int TIMETOWAIT = 100; // Modify interval here, pls.

	private Boolean running; // state of the timer.

	private static Timer instance = null;//Singleton stuff

	/**
	 * @return the TIMETOWAIT
	 */
	public static int getMilisecstowait() {
		return TIMETOWAIT;
	}

	/**
	 * Constructor. Initialises the set of steppables.
	 */
	private Timer() {
		//Logger setup
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);

		steppables = new HashSet<>();//Steppables setup
		running = true;//It is going to start
		this.start(); // Start itself automagically, so you don't have to!
		LOGGER.log(Level.FINE, "Timer created");
	}

	/**
	 * This method realizes Timer being a singleton in Java.
	 */
	public static Timer getInstance() {
		if (instance == null) {
			instance = new Timer();
		}
		return instance;
	}

	/**
	 * This function is called when the thread of the Timer is started. It Ticks the
	 * timer every once in a while.
	 * 
	 * Not adding Logs to this or any Step() functions, to not spam the STDout.
	 */
	@Override
	public void run() {
		while (true) {//We want this timer to run continuously
			if (running) {//Perform actions only if it is running
				this.tick(); // Let's Tick
				try {
					Thread.sleep(TIMETOWAIT);//Let's not rape the CPU
				} catch (InterruptedException ex) {
					LOGGER.log(Level.FINE, ex.toString(), ex);
					Thread.currentThread().interrupt();
				}
			} else
				try {
					Thread.sleep(TIMETOWAIT*(long)2);//Let's be gentle with the cpu
				} catch (InterruptedException ex) {
					LOGGER.log(Level.FINE, ex.toString(), ex);
					Thread.currentThread().interrupt();
				}
		}
	}

	/**
	 * This method is the tick of the timer. Calls the Step function of every
	 * registered Steppable class.
	 * 
	 * Not going to log this either.
	 */
	public synchronized void tick() {
		Steppable[] array = steppables.toArray(new Steppable[0]);
		//We want to avoid a concurren taccess exception, so we will only access the set
		//for as short a time as we can

		for(int i=0; i<array.length; i++)
			array[i].step();//Step all steppables
	}

	/**
	 * Register a Steppable class.
	 * 
	 * @param s:
	 *            Steppable to register.
	 */
	public synchronized void addSteppable(Steppable s) {
		if (s != null && !this.steppables.contains(s))//Set does this, but it is better to be safe than sorry
			this.steppables.add(s);
	}

	/**
	 * De-register a Steppable class.
	 * 
	 * @param s:
	 *            Steppable to de-register.
	 */
	public synchronized void removeSteppable(Steppable s) {
		if (s == null)//Can't remove nothing
			throw new NullPointerException("Cannot remove null from our set of Steppables.");
		if (this.steppables.isEmpty())//Can't remove something that is not in a set
			throw new IllegalArgumentException("This collection is empty.");
		if (!(this.steppables.contains(s)))//Same
			throw new IllegalArgumentException("Item not in collection.");
		this.steppables.remove(s);
	}

	/**
	 * This method gives back the state of the timer.
	 */
	public boolean getRunning() {
		return running;
	}

	/**
	 * This method sets the state of the timer.
	 */
	public void setRunning(boolean b) {
		running = b;
	}
}
