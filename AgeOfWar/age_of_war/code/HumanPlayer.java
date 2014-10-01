package code;

import java.awt.Rectangle;
import java.util.ArrayList;

import processing.core.PApplet;

/**
 * Normal player that interacts with the display.
 * @author Anish Visaria, Eitan Zlatin
 *
 */
public class HumanPlayer extends Player {

	private ArrayList<Integer> materialToBuild = new ArrayList<Integer>();
	
	private Rectangle unit1Rect;
	private Rectangle unit2Rect;
	private Rectangle specialRect;
	private Rectangle ageUpRect;
	

	
	public HumanPlayer(PApplet p) {
		super(p);
		unit1Rect = new Rectangle();/*Fill in the exact positions later*/
		unit2Rect = new Rectangle();
		specialRect = new Rectangle();
		ageUpRect = new Rectangle();
	}

	/**
	 * Gets entities to build by user interaction with display.
	 */
	@Override
	public ArrayList<Integer> getMaterialsToBuild() 
	{
		return materialToBuild;
	}
	
	public void mouseClicked()
	{
		if(unit1Rect.contains(getParent().mouseX, getParent().mouseY))
		{
			buildUnit(0);
		}
		
		if(unit2Rect.contains(getParent().mouseX, getParent().mouseY))
		{
			buildUnit(1);
		}
		
		if(specialRect.contains(getParent().mouseX, getParent().mouseY))
		{
				setSpecial(AgeUtility.getSpecial(getAge()));
		}
	
		if(ageUpRect.contains(getParent().mouseX, getParent().mouseY))
		{
			if(getXp() > AgeUtility.xpToAgeUp(getAge()))
			{
				ageUp();
			}
		}
	}
	
	private void buildUnit(int type)
	{
		int unit = AgeUtility.getUnits(getAge())[type];
		if(AgeUtility.getCost(unit) <= getGold())
		{
			setGold(getGold() - AgeUtility.getCost(unit));
			materialToBuild.add(AgeUtility.getUnits(getAge())[type]);
		}
	}
	

}
