package ansiliary;

public final class LevelStorage {
	private LevelStorage() {}
	/**
	 * Mapping characters to level elements:
	 * Hash: Wall
	 * T:Tile
	 * B:Box
	 * H:Trap (hole linked to switch)
	 * E:EndTile
	 * W:Blue Worker
	 * S:Switch linked to the hole that came before it
	 * R:Red Worker
	 * O:Unlinked Hole (constantly open)
	 * F:map is over
	 * 
	 * EVERY MAP HAS TO BE 11X11
	 */
public static final String DEMOLEVEL=
"###########\n" + 
"######BTH##\n" + 
"#EE#TTT####\n" + 
"#TTTTBBW#S#\n" + 
"#T##TTTTTT#\n" + 
"#T#H##ST#T#\n" + 
"#TTETTT#T##\n" + 
"#TTRTTTT#E#\n" + 
"#TT########\n" + 
"###########";
}
