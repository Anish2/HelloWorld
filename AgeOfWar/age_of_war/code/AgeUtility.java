package code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import processing.core.PImage;

/**
 * Creates units and reads hardcoded information from a file and communicates that to the display.
 * @author Anish Visaria, Eitan Zlatin
 *
 */
public class AgeUtility 
{
	public static final int SHINOBI = 0;
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
	 * @throws IOException 
	 */
	public static int[] getUnits(int age) throws IOException
	{
		int[] units = new int[2];
		
		
		ArrayList<ArrayList<String>> file = readfile("game_data\\UnitInfo");

		int ageCol = file.get(0).indexOf("Age");
		int numberCol = file.get(0).indexOf("Number");
		
		int row = 0;
		for(int x = 0; x < file.size(); x++)
		{
			if(Integer.parseInt(file.get(x).get(ageCol)) == age)
			{
				units[row] = Integer.parseInt(file.get(x).get(numberCol));
			}
		}
	
		return units;
	}
	
	public static ArrayList<ArrayList<String>> readfile(String fileName) throws IOException
	{
		ArrayList<ArrayList<String>> file = new ArrayList<ArrayList<String>>();
		BufferedReader in = new BufferedReader(new FileReader(new File(fileName)));
		
		
		String p = in.readLine();
		int line = 0;
		
		while(p != null)
		{
			StringTokenizer t = new StringTokenizer(p);
			file.add(new ArrayList<String>());
			while(t.hasMoreTokens())
			{
				String token = t.nextToken();
				file.get(line).add(token);
			}
			line++;
			p = in.readLine();
		}
		
		in.close();
		
		return file;
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
	
	
	/**
	 * Returns int representing special available in a certain age
	 * @param age given age
	 * @return special available
	 */
	public static int getSpecial(int age) {
		return 0;
	}
	
	public static int xpToAgeUp(int currentAge)
	{
		return 0;
	}
	
	public static int getCooldown(int special)
	{
		return 0;
	}
}
