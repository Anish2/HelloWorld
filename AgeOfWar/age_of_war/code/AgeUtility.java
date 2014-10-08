package code;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import processing.core.PApplet;
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
	
	public final static int player1UnitStartingLocation = 100;//Subject to change
	public final static int player2UnitStartingLocation = 900;
	
	/**
	 * Returns an array of the units that can be built in age containing the meele unit in the first index and the ranged unit in the second.
	 * @param age The age that the units will be built in
	 * @return The array containing the units
	 * @throws IOException 
	 */
	public static int[] getUnits(int age) throws IOException
	{
		int[] units = new int[2];
		
		
		ArrayList<ArrayList<String>> file = readfile("UnitInfo");

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
	 * @param player 1 if the unit belongs to player 1, 2 if it belongs to player 2.
	 * @return built Unit
	 * @throws IOException 
	 * @throws Exception 
	 */
	public static Unit makeUnit(PApplet parent, int type, int player) throws IOException
	{
		
		File bgURL = new File("data\\UnitInfo");
		ArrayList<ArrayList<String>> file = readfile(bgURL.getAbsolutePath());
		
		int numberCol = file.get(0).indexOf("Number");
		int healthCol = file.get(0).indexOf("Health");
		int attackCol = file.get(0).indexOf("Attack");
		int rangeCol = file.get(0).indexOf("Range");
		
		int location, health = 0, attack = 0, range = 0;
		if(player == 1)
		{
			location = player1UnitStartingLocation;
		}
		else
		{
			location = player2UnitStartingLocation;
		}
		//System.out.println(file);
		for(int x = 1 ; x < file.size(); x++)
		{
			if(Integer.parseInt(file.get(x).get(numberCol)) == type)
			{
				health = Integer.parseInt(file.get(x).get(healthCol));
				attack = Integer.parseInt(file.get(x).get(attackCol));
				range = Integer.parseInt(file.get(x).get(rangeCol));							
			}
		}
		
		PImage img = parent.loadImage("0.png");
		
		Unit u = new Unit(parent,health,location,attack,range,img, img);
		return u;
	}
	
	/**
	 * Returns yagura with characteristics according to type.
	 * @param type type of yagura
	 * @param player 1 if the yagura belongs to player 1, 2 if it belongs to player 2.
	 * @return built yagura
	 */
	public static Yagura makeYagura(int type, int player) {
		return null;
	}
	
	/**
	 * Returns cost of type of unit or yagura.
	 * @param type type of unit or yagura
	 * @return cost
	 */
	public static int getCost(int type) {
		return 0;
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
	
	/**
	 * Returns the amount of xp required for the player to age up from the current age
	 * @param currentAge the current age
	 * @return the xp to age up
	 */
	public static int xpToAgeUp(int currentAge)
	{
		return 0;
	}
	
	/**
	 * Returns the cooldown on a the special passed in.
	 * @param special which special the cooldown is desired to know
	 * @return the cooldown of the special
	 */
	public static int getCooldown(int special)
	{
		return 0;
	}
}
