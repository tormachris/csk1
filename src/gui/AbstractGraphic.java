/**
 * 
 */
package gui;

import java.io.Serializable;

import javax.swing.*;

/**
 * @author krist
 * Provides implementation of Drawable and has an img member variable
 */
public abstract class AbstractGraphic implements Drawable,Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6985603582233021589L;
	private ImageIcon img;
	protected void setIcon(ImageIcon oimg) {img=oimg;}
	protected ImageIcon getIcon() {return img;}
	protected AbstractGraphic() {img=IconCollection.getInstance().getCsupor();}
	protected AbstractGraphic(ImageIcon oimg) {img=oimg;}

}
