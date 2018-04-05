/**
 * 
 */
package logic;

import java.io.*;

/** Interprets commands and executes them.
 * Just like a command line program :)
 * @author Kristof
 * @version 1.0
 * @since 1.0
*/
public final class Commander {

	public String scan() {
		String toreturn="";
		
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        try {
			toreturn = stdin.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return toreturn.toLowerCase();	//we are not case sensitive
	}
	
	public void interpreter() {
		//First, we break up the raw input into command and arguments.
		String rawin=scan();
		String[] input=rawin.split(" ");
		while (true) { //Let's process a bunch of inputs.
			switch (input[0]) {
		
			case "newmap":
				break;
			case "newtile":
				break;
			case "connecttiles":
				break;
			case "newthing":
				break;
			case "toggletimer":
				break;
			case "gettimerstate":
				break;
			case "putfrictionmodifierontile":
			break;
			case "gettilestate":
				break;
			case "moveworker":
				break;
			case "step":
			break;
			case "getgamestate":
				break;
			case "xmlprepare":
				xmlinterpreter();	//Change to XML processer state.
				break;
			case "xmlover"://This command is invalid in this state, but let's leave this here for accounting purposes.
				break;
			default:
				break;
			}
		}
	}
	
	public void xmlinterpreter() {
		
	}
}
