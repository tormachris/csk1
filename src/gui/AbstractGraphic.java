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
	private static final long serialVersionUID = -6985603582233021589L;//Ser UID
	private ImageIcon img;//Data memeber
	protected void setIcon(ImageIcon oimg) {img=oimg;}
	protected ImageIcon getIcon() {return img;}
	//Some constructors
	protected AbstractGraphic() {img=IconCollection.getInstance().getCsupor();}
	protected AbstractGraphic(ImageIcon oimg) {img=oimg;}

}
