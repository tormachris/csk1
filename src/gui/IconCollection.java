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
	private StretchIcon honey;
	private StretchIcon oil;
	private StretchIcon blueontile;
	private StretchIcon redontile;
	private StretchIcon endoil;
	private StretchIcon endhoney;
	private StretchIcon buttonhoney;
	private StretchIcon buttonoil;
	private IconCollection() {
		button=new StretchIcon("https://tormakristof.eu/files/csk1_graf/Floor_with_Button.png");
		demo=new StretchIcon("https://tormakristof.eu/files/csk1_graf/demo.png");
		box=new StretchIcon("https://tormakristof.eu/files/csk1_graf/doboz.png");
		floor=new StretchIcon("https://tormakristof.eu/files/csk1_graf/floor.png");
		hole=new StretchIcon("https://tormakristof.eu/files/csk1_graf/Floor_with_HoleOpen.png");
		target=new StretchIcon("https://tormakristof.eu/files/csk1_graf/Floor_with_Target.png");
		wall=new StretchIcon("https://tormakristof.eu/files/csk1_graf/wall.png");
		workerblue=new StretchIcon("https://tormakristof.eu/files/csk1_graf/worker_blue.png");
		workerred=new StretchIcon("https://tormakristof.eu/files/csk1_graf/worker_red.png");
		blueontile=new StretchIcon("https://tormakristof.eu/files/csk1_graf/Floor_with_BlueWorker.png");
		redontile=new StretchIcon("https://tormakristof.eu/files/csk1_graf/Floor_with_RedWorker.png");
		honey=new StretchIcon("https://tormakristof.eu/files/csk1_graf/Floor_with_Honey.png");
		oil=new StretchIcon("https://tormakristof.eu/files/csk1_graf/Floor_with_Oil.png");
		endoil=new StretchIcon("https://tormakristof.eu/files/csk1_graf/Floor_with_Target_with_Oil.png");
		endhoney=new StretchIcon("https://tormakristof.eu/files/csk1_graf/Floor_with_Target_with_Honey.png");
		buttonhoney=new StretchIcon("https://tormakristof.eu/files/csk1_graf/Floor_with_Button_with_Honey.png");
		buttonoil=new StretchIcon("https://tormakristof.eu/files/csk1_graf/Floor_with_Button_with_Oil.png");
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
	/**
	 * @return the honey
	 */
	public StretchIcon getHoney() {
		return honey;
	}
	/**
	 * @return the oil
	 */
	public StretchIcon getOil() {
		return oil;
	}
	/**
	 * @return the blueontile
	 */
	public StretchIcon getBlueontile() {
		return blueontile;
	}
	/**
	 * @return the redontile
	 */
	public StretchIcon getRedontile() {
		return redontile;
	}
	/**
	 * @return the endoil
	 */
	public StretchIcon getEndoil() {
		return endoil;
	}
	/**
	 * @return the endhoney
	 */
	public StretchIcon getEndhoney() {
		return endhoney;
	}
	/**
	 * @return the buttonhoney
	 */
	public StretchIcon getButtonhoney() {
		return buttonhoney;
	}
	/**
	 * @return the buttonoil
	 */
	public StretchIcon getButtonoil() {
		return buttonoil;
	}
}
