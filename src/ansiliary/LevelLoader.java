package ansiliary;

import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 * This class loads levels from files or a built-in LevelStorage class.
 * 
 * @author kristof
 *
 */
public class LevelLoader {
	private static final Logger LOGGER = Logger.getLogger(LevelLoader.class.getName());

	private LevelLoader() {
		LOGGER.setLevel(Level.ALL);
		ConsoleHandler handler = new ConsoleHandler();
		handler.setFormatter(new SimpleFormatter());
		LOGGER.addHandler(handler);
		handler.setLevel(Level.ALL);
	}

	/**
	 * Mapping characters to level elements: Hash: Wall T:Tile B:Box H:Trap (hole
	 * linked to switch) E:EndTile W:Blue Worker S:Switch linked to the hole that
	 * came before it R:Red Worker O:Unlinked Hole (constantly open) F:map is over
	 * 
	 * EVERY MAP HAS TO BE 11X11
	 */
	private static String loadLevel(File candidate) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(candidate))) { // Try-with-resource
			String line = "";
			while ((line = br.readLine()) != null) {
				LOGGER.log(Level.FINE, "Read line was {0}" , line);
				if (line.equals(""))
					break;
				line = line.toUpperCase();
				for (Integer i = 0; i < line.length(); i++) {
					char c = Character.toUpperCase(line.charAt(i));
					if (c == '#' || c == 'T' || c == 'B' || c == 'H' || c == 'E' || c == 'W' || c == 'S' || c == 'R'
							|| c == 'O') {
						sb.append(c);
						LOGGER.fine(() -> "Character was " + c);
					} else if (c == 'F') {
						LOGGER.log(Level.FINE,"Character was F, file is over, breaking");
						break;
					}
				}
				sb.append('\n');
			}
		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
		if (sb.toString().length() == 11 * 11) {
			LOGGER.log(Level.FINE, "File was right size");
			return sb.toString();
		} else {
			LOGGER.log(Level.FINE, "File was wrong size, ignoring");
			return "";
		}
	}

	public static Queue<LevelElements> getLevel(String candidate) {
		LinkedList<LevelElements> toReturn = new LinkedList<>();
		if (candidate.equals("")) {
			LOGGER.log(Level.FINE, "String was wrong size, ignoring");
			return toReturn;
		}
		for (Integer i = 0; i < candidate.length(); i++) {
			Character c = Character.toUpperCase(candidate.charAt(i));
			switch (c) {
			case ('#'):
				toReturn.add(LevelElements.WALL);
				break;
			case ('T'):
				toReturn.add(LevelElements.TILE);
				break;
			case ('B'):
				toReturn.add(LevelElements.CRATE);
				break;
			case ('H'):
				toReturn.add(LevelElements.TRAP);
				break;
			case ('E'):
				toReturn.add(LevelElements.ENDTILE);
				break;
			case ('W'):
				toReturn.add(LevelElements.BLUE);
				break;
			case ('S'):
				toReturn.add(LevelElements.SWITCH);
				break;
			case ('R'):
				toReturn.add(LevelElements.RED);
				break;
			case ('O'):
				toReturn.add(LevelElements.HOLE);
				break;
			default:
				break;
			}
		}
		if (toReturn.size() == 11 * 11) {
			LOGGER.log(Level.FINE,"String was right size, returning list");
			return toReturn;
		}
		else {
			LOGGER.log(Level.FINE,"String was wrong size, passing empty list");
			return new LinkedList<>();
		}
	}

	public static Queue<LevelElements> getLevelFromFile(File candidate) {
		LOGGER.log(Level.FINE,"Attempting to parse file into processable list");
		return getLevel(loadLevel(candidate));
	}
}
