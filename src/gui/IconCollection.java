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
	private ImageIcon button; 		//The Image of a Switch.
	private ImageIcon box;			//The Image of a Crate.
	private ImageIcon floor;		//The Image of a Tile.
	private ImageIcon hole;			//The Image of a hole.
	private ImageIcon target;		//The Image of an EndTile.
	private ImageIcon wall;			//The Image of a Wall.
	private ImageIcon honey;		//The Image of the Honey.
	private ImageIcon oil;			//The Image of the Oil.
	private ImageIcon blueontile;	//The Image of a blue Worker.
	private ImageIcon redontile;	//The Image of a red Worker.
	private ImageIcon endoil;		//The Image of an EndTile with Oil.
	private ImageIcon endhoney;		//The Image of an EndTile with Honey.
	private ImageIcon buttonhoney;	//The Image of a switch with Honey.
	private ImageIcon buttonoil;	//The Image of a switch with Oil.
	private ImageIcon csupor;		//The Icon for the game.
	
	//Setting the icons in the constructor.
	
	private IconCollection() {
		button=new ImageIcon("assets/csk1_graf/Floor_with_Button.png");
		box=new ImageIcon("assets/csk1_graf/doboz.png");
		floor=new ImageIcon("assets/csk1_graf/floor.png");
		hole=new ImageIcon("assets/csk1_graf/Floor_with_HoleOpen.png");
		target=new ImageIcon("assets/csk1_graf/Floor_with_Target.png");
		wall=new ImageIcon("assets/csk1_graf/wall.png");
		blueontile=new ImageIcon("assets/csk1_graf/EasterEggBlueLeft.png");
		redontile=new ImageIcon("assets/csk1_graf/EasterEggRedLeft.png");
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
	public ImageIcon getBox() {return box;}
	public ImageIcon getFloor() {return floor;}
	public ImageIcon getHole() {return hole;}
	public ImageIcon getTarget() {return target;}
	public ImageIcon getWall() {return wall;}
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
	/**
	 * @param blueontile the blueontile to set
	 */
	public void setBlueontile(ImageIcon blueontile) {
		this.blueontile = blueontile;
	}
	/**
	 * @param redontile the redontile to set
	 */
	public void setRedontile(ImageIcon redontile) {
		this.redontile = redontile;
	}
}
