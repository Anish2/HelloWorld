package code;


import java.io.IOException;
import java.util.ArrayList;



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

	/**
	 * constructs HumanPlayer.
	 * @param p display object
	 * @param playerNum number of player
	 */
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
		try {
			mouseClicked();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return materialToBuild;
	}


	private void mouseClicked() throws IOException {
		if (getParent().mousePressed) {
			final int X = getParent().mouseX;
			final int Y = getParent().mouseY;
			for (int i = 0; i < buttons.size(); i++) {
				if (buttons.get(i)[0] <= X && buttons.get(i)[0]+rectSize >= X
						&& buttons.get(i)[1] <= Y && buttons.get(i)[1]+rectSize >= Y) {
					switch(i)
					{
					case 0:
						if (getAge() == AgeUtility.DARK)
						{
							compileUnit(AgeUtility.SHINOBI);
						}
						else
							compileUnit(AgeUtility.ALI_BABA);
						break;
					case 1: 
						if (getAge() == AgeUtility.DARK)
						{
							compileUnit(AgeUtility.ARCHER);
						}
						else
							compileUnit(AgeUtility.NINJA);
						break;
					case 2:
						if (getAge() == AgeUtility.DARK)
						{
							compileUnit(AgeUtility.EARTH_YAGURA);
						}
						else
							compileUnit(AgeUtility.WIND_YAGURA);
						break;
					case 3:
						if (getAge() == AgeUtility.DARK)
						{
							specialUsed = AgeUtility.DAMAGE_SPECIAL;
						}
						else
							specialUsed = AgeUtility.HEAL_SPECIAL;
						break;
					case 4:
						if (getXp() >= AgeUtility.xpToAgeUp(getAge())) {
							this.ageUp();
						}
						break;
					}
				}
			}
		}
	}



	private void compileUnit(int type) throws IOException 
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
