/**
 * 
 */
package gui;

import ansiliary.*;

import java.io.Serializable;

/**
 * @author krist
 *
 */
public class IconCollection implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6379120099609785077L;
	private static IconCollection instance = null;
	private StretchIcon button;
	private StretchIcon demo;
	private StretchIcon box;
	private StretchIcon floor;
	private StretchIcon hole;
	private StretchIcon target;
	private StretchIcon wall;
	private StretchIcon workerblue;
	private StretchIcon workerred;
	private IconCollection() {
		button=new StretchIcon("https://tormakristof.eu/files/csk1_graf/button.png");
		demo=new StretchIcon("https://tormakristof.eu/files/csk1_graf/demo.png");
		box=new StretchIcon("https://tormakristof.eu/files/csk1_graf/doboz.png");
		floor=new StretchIcon("https://tormakristof.eu/files/csk1_graf/floor.png");
		hole=new StretchIcon("https://tormakristof.eu/files/csk1_graf/hole.png");
		target=new StretchIcon("https://tormakristof.eu/files/csk1_graf/target.png");
		wall=new StretchIcon("https://tormakristof.eu/files/csk1_graf/wall.png");
		workerblue=new StretchIcon("https://tormakristof.eu/files/csk1_graf/worker_blue.png");
		workerred=new StretchIcon("https://tormakristof.eu/files/csk1_graf/worker_red.png");
	}
	/**
	 * This method realizes IconCollection being a singleton in Java.
	 */
	public static IconCollection getInstance() {
		if(instance == null) {
			instance = new IconCollection();
		}
		return instance;
	}
	public StretchIcon getButton() {return button;}
	public StretchIcon getDemo() {return demo;}
	public StretchIcon getBox() {return box;}
	public StretchIcon getFloor() {return floor;}
	public StretchIcon getHole() {return hole;}
	public StretchIcon getTarget() {return target;}
	public StretchIcon getWall() {return wall;}
	public StretchIcon getWorkerBlue() {return workerblue;}
	public StretchIcon getWorkerRed() {return workerred;}
}
