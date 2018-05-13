package logic;

public class Oil extends FrictionModifier{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5183636879223466445L;

	@Override
	public Double getFriction()
	{
		return 0.5*this.friction;
	}
}
