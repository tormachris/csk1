package ansiliary;

public enum LevelElements {
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
	 */
WALL,
TILE,
ENDTILE,
BLUE,
RED,
CRATE,
HOLE,
TRAP,
SWITCH;
}
