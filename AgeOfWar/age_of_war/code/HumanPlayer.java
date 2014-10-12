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

	ArrayList<int[]> buttons = new ArrayList<int[]>();
	private int rectSize;

	private int specialUsed;
	private int playerNum;

	public HumanPlayer(Display p, int playerNum) {
		super(p);
		Display d = p;
		buttons.add(d.unit1);
		buttons.add(d.unit2);
		buttons.add(d.yagura);
		buttons.add(d.special);
		buttons.add(d.nextAge);
		this.playerNum = playerNum;
		rectSize = d.rectSize;
	}

	/**
	 * Returns the entities the player wants to build.
	 */
	@Override
	public ArrayList<Integer> getMaterialsToBuild() 
	{
		materialToBuild.clear();
		mouseClicked();
		return materialToBuild;
	}

	public void mouseClicked() {
		if (getParent().mousePressed) {
			System.out.println("I got this");
			compileUnit(AgeUtility.SHINOBI);
		}
		/*if (getParent().mousePressed) {
			for (int i = 0; i < buttons.size(); i++) {
				if (buttons.get(i)[0] >= getParent().mouseX && buttons.get(i)[0]+rectSize <= getParent().mouseX
						&& buttons.get(i)[1] >= getParent().mouseY && buttons.get(i)[1]+rectSize <= getParent().mouseY) {
					
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
							compileUnit(AgeUtility.ARCHER);
						}
						else
							compileUnit(AgeUtility.NINJA);
					case 2:
						if (getAge() == AgeUtility.DARK)
						{
							compileUnit(AgeUtility.EARTH_YAGURA);
						}
						else
							compileUnit(AgeUtility.WIND_YAGURA);
					case 3:
						if (getAge() == AgeUtility.DARK)
						{
							specialUsed = AgeUtility.DAMAGE_SPECIAL;
						}
						else
							specialUsed = AgeUtility.HEAL_SPECIAL;
					}
				}
			}
		}*/
	}


	/**
	 * Detects when buttons are clicked and adds the units to the build list.
	 */


	private void compileUnit(int type) 
	{
		if(AgeUtility.getCost(type) <= getGold())
		{
			setGold(getGold()-AgeUtility.getCost(type));
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
		return playerNum;
	}

}
