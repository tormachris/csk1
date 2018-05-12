/**
 * 
 */
package gui;

import javax.swing.*;

/**
 * @author krist
 *
 */
public class IconCollection {
	private static IconCollection instance = null;
	private ImageIcon button;
	private ImageIcon demo;
	private ImageIcon box;
	private ImageIcon floor;
	private ImageIcon hole;
	private ImageIcon target;
	private ImageIcon wall;
	private ImageIcon worker_blue;
	private ImageIcon worker_red;
	private IconCollection() {
		button=new ImageIcon("https://tormakristof.eu/files/csk1_graf/button.png");
		demo=new ImageIcon("https://tormakristof.eu/files/csk1_graf/demo.png");
		box=new ImageIcon("https://tormakristof.eu/files/csk1_graf/doboz.png");
		floor=new ImageIcon("https://tormakristof.eu/files/csk1_graf/floor.png");
		hole=new ImageIcon("https://tormakristof.eu/files/csk1_graf/hole.png");
		target=new ImageIcon("https://tormakristof.eu/files/csk1_graf/target.png");
		wall=new ImageIcon("https://tormakristof.eu/files/csk1_graf/wall.png");
		worker_blue=new ImageIcon("https://tormakristof.eu/files/csk1_graf/worker_blue.png");
		worker_red=new ImageIcon("https://tormakristof.eu/files/csk1_graf/worker_red.png");
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
	public ImageIcon getButton() {return button;}
	public ImageIcon getDemo() {return demo;}
	public ImageIcon getBox() {return box;}
	public ImageIcon getFloor() {return floor;}
	public ImageIcon getHole() {return hole;}
	public ImageIcon getTarget() {return target;}
	public ImageIcon getWall() {return wall;}
	public ImageIcon getWorkerBlue() {return worker_blue;}
	public ImageIcon getWorkerRed() {return worker_red;}
}
