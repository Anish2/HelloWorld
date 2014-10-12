package code;

import java.awt.Rectangle;
import java.util.ArrayList;

import processing.core.PApplet;

/**
 * Abstract class that is responsible for deciding whether to build a unit each move cycle.
 * @author Anish Visaria, Eitan Zlatin
 *
 */
public abstract class Player 
{
	private int currentAge = AgeUtility.DARK;
	private int gold = 100;
	private int xp = 0;
	private PApplet parent;

	public Player(Display p)
	{
		parent = p;
	}
	
	public int getXp()
	{
		return xp;
	}
	
	public PApplet getParent()
	{
		return parent;
	}
	
	public void gainXP(int exp)
	{
		xp += exp;
	}
	
	
	
	public void setGold(int gold)
	{
		this.gold = gold;
	}
	
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

	public abstract boolean getSpecial();
	
	public abstract int playerNum();
}
