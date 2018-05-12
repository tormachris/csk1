/**
 * 
 */
package gui;

import javax.swing.*;

/**
 * @author krist
 * Provides implementation of Drawable and has an img member variable
 */
public abstract class AbstractGraphic implements Drawable {
	private ImageIcon img;
	protected void setIcon(ImageIcon _img) {img=_img;}
	protected ImageIcon getIcon() {return img;}
	protected AbstractGraphic() {}
	protected AbstractGraphic(ImageIcon _img) {img=_img;}

}
