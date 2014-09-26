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
	private ArrayList<Integer> materialToBuild = new ArrayList<Integer>();
	
	private Rectangle unit1Rect;
	private Rectangle unit2Rect;
	private Rectangle specialRect;
	
	private int specialUsed;
	
	public Player(PApplet p)
	{
		parent = p;
		unit1Rect = new Rectangle();/*Fill in the exact positions later*/
		unit2Rect = new Rectangle();
		specialRect = new Rectangle();
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
	 */
	private void processWarTransaction(int gold)
	{
		this.gold += gold;
	}
	
	/**
	 * Returns an int representing special used
	 * @return the int corresponding to the used special, -1 if no special was used.
	 * 
	 */
	public int getSpecials() {
		return 0;
	}
	
	public void mouseClicked()
	{
		if(unit1Rect.contains(parent.mouseX, parent.mouseY))
		{
			materialToBuild.add(AgeUtility.getUnits(getAge())[0]);
		}
		
		if(unit2Rect.contains(parent.mouseX, parent.mouseY))
		{
			materialToBuild.add(AgeUtility.getUnits(getAge())[1]);
		}
		
		if(specialRect.contains(parent.mouseX, parent.mouseY))
		{
			
		}
	}
}
