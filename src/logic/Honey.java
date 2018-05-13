package logic;

/**
 * Honey Friction MOdifier
 * @author tdani
 *
 */
public class Honey extends FrictionModifier {

	private static final long serialVersionUID = 9050518178968596879L;
	
	/**
	 * the Friction, always 1.5 times the friction of its superclass.
	 * @return the friction of honey
	 */
	@Override
	public Double getFriction()
	{
		return 1.5*this.friction;
	}
}
