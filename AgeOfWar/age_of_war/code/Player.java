package code;

import java.util.ArrayList;

import processing.core.PApplet;

/**
 * Abstract class that is responsible for deciding whether to build a unit or yagura each move cycle.
 * Also handles special use during game.
 * @author Anish Visaria, Eitan Zlatin
 *
 */
public abstract class Player 
{
	private int currentAge = AgeUtility.DARK;
	private int gold = 100;
	private int xp = 0;
	private PApplet parent;

	/**
	 * Makes player with display
	 * @param p display object
	 */
	public Player(PApplet p)
	{
		parent = p;
	}
	
	/**
	 * Returns Xp of player.
	 * @return xp
	 */
	public int getXp()
	{
		return xp;
	}
	
	/**
	 * Returns display object
	 * @return display object
	 */
	public PApplet getParent()
	{
		return parent;
	}
	
	/**
	 * Adds xp to current player xp
	 * @param exp xp to add
	 */
	public void gainXP(int exp)
	{
		xp += exp;
	}
	
	
	/**
	 * Sets gold to a value.
	 * @param gold gold to set
	 */
	public void setGold(int gold)
	{
		this.gold = gold;
	}
	
	/**
	 * Changes current age.
	 */
	public void ageUp()
	{
		currentAge = AgeUtility.MEDIEVAL;
	}
	
	/**
	 * Returns list of unit or yagura types that player is able to build
	 * @return list of unit or yagura types
	 */
	public abstract ArrayList<Integer> getMaterialsToBuild();
	
	/**
	 * Returns a string representing the player's current age
	 * @return current age
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

	/**
	 * Returns whether this player used a special or not.
	 * @return special choice
	 */
	public abstract boolean getSpecial();
	
	/**
	 * Returns number of player.
	 * @return player num
	 */
	public abstract int playerNum();
}
