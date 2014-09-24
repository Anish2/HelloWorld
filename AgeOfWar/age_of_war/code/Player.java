package code;

import java.util.ArrayList;

/**
 * Abstract class that is responsible for deciding whether to build a unit each move cycle.
 * @author Anish Visaria, Eitan Zlatin
 *
 */
public abstract class Player 
{
	private int currentAge = 0;
	private int gold = 100;
	private int xp = 0;
	private ArrayList<Integer> materialToBuild = new ArrayList<Integer>();
	
	/**
	 * Returns list of unit or yagura types that player is able to build
	 * @return list of unit or yagura types
	 */
	public abstract ArrayList<Integer> getMaterialsToBuild();
	
	/**
	 * Returns a string representing the player's current age
	 * @return "dark" if the player is in the dark age or "medieval" if the player is in the medieval age
	 */
	public int getAge() {
		return currentAge;
	}
	
	
	/**
	 * Returns gold of this player.
	 * @return amount of gold
	 */
	public int getGold() {
		return gold;
	}
	
	/*
	 * Processes gain or loss of gold.
	 * @param gold amount to be gained , negative if the gold is to be lost.
	 */
	private void processWarTransaction(int gold)
	{
		this.gold += gold;
	}
	
	/**
	 * Returns string representing special used
	 * @return "damage":damage all enemy units, "heal": restore all of this player's units health, "null" if no special
	 * 
	 */
	public int getSpecials() {
		return 0;
	}
	
	public void mouseClicked()
	{
		
	}
}
