package logic;

/**
 * Oil Friction MOdifier
 * @author tdani
 *
 */
public class Oil extends FrictionModifier{

	private static final long serialVersionUID = -5183636879223466445L;

	/**
	 * the Friction, always 0.2 times the friction of its superclass.
	 * @return the friction of oil
	 */
	@Override
	public Double getFriction()
	{
		return 0.2*this.friction;
	}
}
