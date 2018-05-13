package logic;

import java.io.Serializable;

/**
 * Represents the normal friction of a tile superclass of other Friction MOdifiers
 * @author tdani
 *
 */
public class FrictionModifier implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3407010397152164859L;
	protected final Double friction = Double.valueOf(1);
	
	/**
	 * 
	 * @return the friction
	 */
	public Double getFriction() 
	{
		return friction;
	}
	
	
}
