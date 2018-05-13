/**
 * 
 */
package gui;

import java.io.Serializable;

import javax.swing.ImageIcon;

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
	private ImageIcon button;
	private ImageIcon demo;
	private ImageIcon box;
	private ImageIcon floor;
	private ImageIcon hole;
	private ImageIcon target;
	private ImageIcon wall;
	private ImageIcon workerblue;
	private ImageIcon workerred;
	private ImageIcon honey;
	private ImageIcon oil;
	private ImageIcon blueontile;
	private ImageIcon redontile;
	private ImageIcon endoil;
	private ImageIcon endhoney;
	private ImageIcon buttonhoney;
	private ImageIcon buttonoil;
	private ImageIcon csupor;
	private IconCollection() {
		button=new ImageIcon("assets/csk1_graf/Floor_with_Button.png");
		demo=new ImageIcon("assets/csk1_graf/demo.png");
		box=new ImageIcon("assets/csk1_graf/doboz.png");
		floor=new ImageIcon("assets/csk1_graf/floor.png");
		hole=new ImageIcon("assets/csk1_graf/Floor_with_HoleOpen.png");
		target=new ImageIcon("assets/csk1_graf/Floor_with_Target.png");
		wall=new ImageIcon("assets/csk1_graf/wall.png");
		workerblue=new ImageIcon("assets/csk1_graf/worker_blue.png");
		workerred=new ImageIcon("assets/csk1_graf/worker_red.png");
		blueontile=new ImageIcon("assets/csk1_graf/EasterEggBlue.png");
		redontile=new ImageIcon("assets/csk1_graf/EasterEggRed.png");
		honey=new ImageIcon("assets/csk1_graf/Floor_with_Honey.png");
		oil=new ImageIcon("assets/csk1_graf/Floor_with_Oil.png");
		endoil=new ImageIcon("assets/csk1_graf/Floor_with_Target_with_Oil.png");
		endhoney=new ImageIcon("assets/csk1_graf/Floor_with_Target_with_Honey.png");
		buttonhoney=new ImageIcon("assets/csk1_graf/Floor_with_Button_with_Honey.png");
		buttonoil=new ImageIcon("assets/csk1_graf/Floor_with_Button_with_Oil.png");
		csupor=new ImageIcon("assets/csk1_graf/HoneyJar.png");
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
	public ImageIcon getWorkerBlue() {return workerblue;}
	public ImageIcon getWorkerRed() {return workerred;}
	/**
	 * @return the honey
	 */
	public ImageIcon getHoney() {
		return honey;
	}
	/**
	 * @return the oil
	 */
	public ImageIcon getOil() {
		return oil;
	}
	/**
	 * @return the blueontile
	 */
	public ImageIcon getBlueontile() {
		return blueontile;
	}
	/**
	 * @return the redontile
	 */
	public ImageIcon getRedontile() {
		return redontile;
	}
	/**
	 * @return the endoil
	 */
	public ImageIcon getEndoil() {
		return endoil;
	}
	/**
	 * @return the endhoney
	 */
	public ImageIcon getEndhoney() {
		return endhoney;
	}
	/**
	 * @return the buttonhoney
	 */
	public ImageIcon getButtonhoney() {
		return buttonhoney;
	}
	/**
	 * @return the buttonoil
	 */
	public ImageIcon getButtonoil() {
		return buttonoil;
	}
	/**
	 * @return the csupor
	 */
	public ImageIcon getCsupor() {
		return csupor;
	}
}
