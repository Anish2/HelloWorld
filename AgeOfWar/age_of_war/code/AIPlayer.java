package code;

import java.util.ArrayList;

import processing.core.PApplet;

/**
 * Simulates computer player as opponent.
 * @author Anish Visaria, Eitan Zlatin
 *
 */
public class AIPlayer extends Player {

	
	public AIPlayer(PApplet p) {
		super(p);
		
	}

	/**
	 * Gets entities to build through computation.
	 */
	@Override
	public ArrayList<Integer> getMaterialsToBuild() {
		ArrayList<Integer> toBuild = new ArrayList<Integer>();
		toBuild.add((int) (Math.random()*4));
		return toBuild;
	}

	/**
	 * Determines whether to use special ability or not.
	 */
	@Override
	public boolean getSpecial() {
		return true;
	}

	
}
