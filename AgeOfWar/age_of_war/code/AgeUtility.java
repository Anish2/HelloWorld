package code;

import processing.core.PImage;

/**
 * Creates units and reads hardcoded information from a file and communicates that to the display.
 * @author Anish Visaria, Eitan Zlatin
 *
 */
public class AgeUtility 
{
	public static final int CLUB_MAN = 0;
	public static final int ARCHER = 1;
	public static final int NINJA = 2;
	public static final int ALI_BABA = 3;
	
	public static final int DARK = 4;
	public static final int MEDIEVAL = 5;
	
	public static final int EARTH_YAGURA = 6;
	public static final int WIND_YAGURA = 7;
	
	public static final int DAMAGE_SPECIAL = 8;
	public static final int HEAL_SPECIAL = 9;
	
	/**
	 * Returns an array of the units that can be built in age containing the meele unit in the first index and the ranged unit in the second.
	 * @param age The age that the units will be built in
	 * @return The array containing the units
	 */
	public static int[] getUnits(int age)
	{
		return null;
	}
	/**
	 * Returns Unit with characteristics according to type.
	 * @param type type of unit
	 * @return built Unit
	 */
	public static Unit makeUnit(int type) {
		return null;
	}
	
	/**
	 * Returns yagura with characteristics according to type.
	 * @param type type of yagura
	 * @return built yagura
	 */
	public static Yagura makeYagura(int type) {
		return null;
	}
	
	/**
	 * Returns cost of type of unit or yagura.
	 * @param type type of unit or yagura
	 * @return cost
	 */
	public static int getCost(int type) {
		return 100000;
	}
	
	/**
	 * Returns image of home base for given age.
	 * @param age age name
	 * @return image of age home base
	 */
	public static PImage getAgePicture(int age) {
		return null;		
	}
}
