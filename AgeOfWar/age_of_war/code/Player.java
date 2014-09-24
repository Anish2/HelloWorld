package code;

import java.util.ArrayList;

/**
 * Abstract class that is responsible for deciding whether to build a unit each move cycle.
 * @author Anish Visaria, Eitan Zlatin
 *
 */
public abstract class Player {
	
	
	/**
	 * Returns list of unit or yagura types that player is able to build
	 * @return list of unit or yagura types
	 */
	public abstract ArrayList<String> getMaterialsToBuild();
	
	/**
	 * Returns a string representing the player's current age
	 * @return "dark" if the player is in the dark age or "medieval" if the player is in the medieval age
	 */
	public String getAge() {
		return null;
	}
	
	/**
	 * Returns gold of this player.
	 * @return amount of gold
	 */
	public int getGold() {
		return 0;
	}
	
	/**
	 * Processes gain or loss of gold.
	 * @param gold amount to be gained , negative if the gold is to be lost.
	 */
	public void processWarTransaction(int gold) {
		
	}
	
	/**
	 * Returns string representing special used
	 * @return "damage":damage all enemy units, "heal": restore all of this player's units health, "null" if no special
	 * 
	 */
	public String getSpecials() {
		return null;
	}
	
	
	

}
