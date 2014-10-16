package code;

import java.util.ArrayList;


/**
 * Simulates computer player as opponent.
 * @author Anish Visaria, Eitan Zlatin
 *
 */
public class AIPlayer extends Player {

	private int playerNum; 
	
	/**
	 * Constructs AIPlayer
	 * @param p display object
	 * @param playerNum number of player
	 */
	public AIPlayer(Display p, int playerNum) {
		super(p);
		this.playerNum = playerNum;
		
	}

	/**
	 * Gets entities to build through computation.
	 */
	@Override
	public ArrayList<Integer> getMaterialsToBuild() {
		ArrayList<Integer> toBuild = new ArrayList<Integer>();
		if (Math.random() > 0.95)
			toBuild.add((int) (Math.random()*4));
		/*if (Math.random() > 0.95)
			toBuild.add(AgeUtility.EARTH_YAGURA);*/
		return toBuild;
	}

	/**
	 * Determines whether to use special ability or not.
	 */
	@Override
	public boolean getSpecial() {
		return false;
	}

	@Override
	public int playerNum() {
		return playerNum;
	}

	
}
