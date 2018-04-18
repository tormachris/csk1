package logic;

/** Represents the directions that exist in the game.
 * @author Kristof
 * @version 1.0
 * @since 1.0
*/
public enum Direction {
	
	NORTH //NORTH is UP in the game.
	,SOUTH//SOUTH is DOWN in the game.
	,WEST //WEST is LEFT in the game
	,EAST; //EAST is RIGHT in the game
		//The enum contains quarters and not directions to be more expandable.

public static Direction getOpposite(Direction d) {
		if(d.equals(Direction.NORTH))return Direction.SOUTH;
		else if(d.equals(Direction.SOUTH)) return Direction.NORTH;
		else if(d.equals(Direction.WEST))return Direction.EAST;
		else return Direction.WEST;
	}
}
