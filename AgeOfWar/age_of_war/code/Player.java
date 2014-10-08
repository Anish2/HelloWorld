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
	private int currentAge = 0;
	private int gold = 100;
	private int xp = 0;
	private PApplet parent;
	
	/*public void setSpecial(int special)
	{
		specialUsed = special;
	}*/
	
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
		xp +=exp;
	}
	
	public Player(PApplet p)
	{
		parent = p;
	}
	
	public void setGold(int gold)
	{
		this.gold = gold;
	}
	
	public void ageUp()
	{
		currentAge++;
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
	
	/*
	
	 * Processes gain or loss of gold.
	 * @param gold amount to be gained , negative if the gold is to be lost.
	 
	private void processWarTransaction(int gold)
	{
		this.gold += gold;
	}*/
	
	/**
	 * Returns an int representing special used
	 * @return the int corresponding to the used special, -1 if no special was used.
	 * 
	 */
	public abstract boolean getSpecial();
	
	public abstract int playerNum();
}
