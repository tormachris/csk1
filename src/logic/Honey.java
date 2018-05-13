package logic;

public class Honey extends FrictionModifier {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9050518178968596879L;
	@Override
	public Double getFriction()
	{
		return 1.5*this.friction;
	}
}
