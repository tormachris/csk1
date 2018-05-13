package logic;

import java.io.Serializable;

public class FrictionModifier implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3407010397152164859L;
	protected final Double friction = Double.valueOf(1);
	
	public Double getFriction() 
	{
		return friction;
	}
	
	
}
