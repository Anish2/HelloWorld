package code;

import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;

import processing.core.PApplet;

/**
 * Normal player that interacts with the display.
 * @author Anish Visaria, Eitan Zlatin
 *
 */
public class HumanPlayer extends Player {
 
	private ArrayList<Integer> materialToBuild = new ArrayList<Integer>();
	
	//Index 0 
	private ArrayList<Rectangle> buttons = new ArrayList<Rectangle>();
	
	private int specialUsed;
	private int playerNum;
	
	public HumanPlayer(PApplet p, int playerNum) {
		super(p);
		// fill up buttons arraylist
		/*unit1Rect = new Rectangle();Fill in the exact positions later
		unit2Rect = new Rectangle();
		specialRect = new Rectangle();
		ageUpRect = new Rectangle();*/
		this.playerNum = playerNum;
	}

	/**
	 * Returns the entities the player wants to build.
	 */
	@Override
	public ArrayList<Integer> getMaterialsToBuild() 
	{
		ArrayList<Integer> temp = materialToBuild;
		materialToBuild = new ArrayList<Integer>();
		return temp;
	}
	
	/**
	 * Detects when buttons are clicked and adds the units to the build list.
	 */
	public void mouseClicked() 
	{
		
		for (int i = 0; i < buttons.size(); i++) {
			if (buttons.get(i).contains(getParent().mouseX, getParent().mouseY)) {
				switch(i)
				{
				case 0:
					if (getAge() == AgeUtility.DARK)
					{
						compileUnit(AgeUtility.SHINOBI);
					}
					else
						compileUnit(AgeUtility.ALI_BABA);
				case 1: 
					if (getAge() == AgeUtility.DARK)
					{
						compileUnit(AgeUtility.SHINOBI);
					}
					else
						compileUnit(AgeUtility.ALI_BABA);
				}
			}
		}
		
		/*if(unit1Rect.contains(getParent().mouseX, getParent().mouseY))
		{
			if (getAge() == AgeUtility.DARK)
				compileUnit(AgeUtility.CLUB_MAN);
			else
				compileUnit(AgeUtility.ALI_BABA);
		}
		
		if(unit2Rect.contains(getParent().mouseX, getParent().mouseY))
		{
			if (getAge() == AgeUtility.DARK)
				compileUnit(AgeUtility.ARCHER);
			else
				compileUnit(AgeUtility.NINJA);
		}
		
		if(specialRect.contains(getParent().mouseX, getParent().mouseY))
		{
				specialUsed = AgeUtility.getSpecial(getAge());
		}
	
		if(ageUpRect.contains(getParent().mouseX, getParent().mouseY))
		{
			if(getXp() > AgeUtility.xpToAgeUp(getAge()))
			{
				ageUp();
			}
		}*/
	}
	
	private void compileUnit(int type) 
	{
		if(AgeUtility.getCost(type) <= getGold())
		{
			materialToBuild.add(type);
		}
	}
	
	/**
	 * Returns the special the player wants to use.
	 */
	public boolean getSpecial() 
	{
		int temp = specialUsed;
		specialUsed = 0;
		return temp != 0;
	}

	@Override
	public int playerNum() {
		// TODO Auto-generated method stub
		return playerNum;
	}

}
