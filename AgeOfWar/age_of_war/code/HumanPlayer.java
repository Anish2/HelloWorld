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
	
	private ArrayList<Rectangle> buttons = new ArrayList<Rectangle>();
	
	private int specialUsed;
	
	public HumanPlayer(PApplet p) {
		super(p);
		// fill up buttons arraylist
		/*unit1Rect = new Rectangle();Fill in the exact positions later
		unit2Rect = new Rectangle();
		specialRect = new Rectangle();
		ageUpRect = new Rectangle();*/
	}

	/**
	 * Gets entities to build by user interaction with display.
	 */
	@Override
	public ArrayList<Integer> getMaterialsToBuild() 
	{
		ArrayList<Integer> temp = materialToBuild;
		materialToBuild = new ArrayList<Integer>();
		return temp;
	}
	
	public void mouseClicked() throws IOException
	{
		
		for (int i = 0; i < buttons.size(); i++) {
			if (buttons.get(i).contains(getParent().mouseX, getParent().mouseY)) {
				if (getAge() == AgeUtility.DARK)
					compileUnit(AgeUtility.CLUB_MAN);
				else
					compileUnit(AgeUtility.ALI_BABA);
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
	
	private void compileUnit(int type) throws IOException
	{
		int unit = AgeUtility.getUnits(getAge())[type];
		if(AgeUtility.getCost(unit) <= getGold())
		{
			materialToBuild.add(AgeUtility.getUnits(getAge())[type]);
		}
	}
	
	public boolean getSpecial() 
	{
		int temp = specialUsed;
		specialUsed = 0;
		return temp != 0;
	}

}
