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

	public final static int player1UnitStartingLocation = 100;
	public final static int player2UnitStartingLocation = 900;

	private final static int UNIT_WIDTH = 70;
	private final static int UNIT_HEIGHT = 85;

	private final static int YAGURA_WIDTH = 90;
	private final static int YAGURA_HEIGHT = 85;

	/**
	 * Returns an array of the units that can be built in age containing the meele unit in the first index and the ranged unit in the second.
	 * @param age The age that the units will be built in
	 * @return The array containing the units
	 * @throws IOException 
	 */
	public static int[] getUnits(int age) throws IOException
	{
		int[] units = new int[2];


		ArrayList<ArrayList<String>> file = readfileIntoList("UnitInfo");

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

	private static ArrayList<ArrayList<String>> readfileIntoList(String fileName) throws IOException
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

		if (type < 0 || type > 3)
			throw new InvalidTypeException("No such unit type exists.");

		File bgURL = new File("data\\UnitInfo");
		ArrayList<ArrayList<String>> file = readfileIntoList(bgURL.getAbsolutePath());
		int healthCol = file.get(0).indexOf("Health");
		int attackCol = file.get(0).indexOf("Attack");
		int rangeCol = file.get(0).indexOf("Range");

		int health = Integer.parseInt(file.get(type+1).get(healthCol));
		int attack = Integer.parseInt(file.get(type+1).get(attackCol));
		int range = Integer.parseInt(file.get(type+1).get(rangeCol));

		char direction = (player == 1) ? 'l' : 'r';
		int location = (player == 1) ? player1UnitStartingLocation : player2UnitStartingLocation;

		int num = type+1;
		health = Integer.parseInt(file.get(num).get(healthCol));
		attack = Integer.parseInt(file.get(num).get(attackCol));
		range = Integer.parseInt(file.get(num).get(rangeCol));	

		PImage walkImg = parent.loadImage(type+"w"+direction+".png");
		PImage fightImg = parent.loadImage(type+"f"+direction+".png");

		walkImg.resize(UNIT_WIDTH, UNIT_HEIGHT);
		fightImg.resize(UNIT_WIDTH, UNIT_HEIGHT);

		Unit u = new Unit(parent,health,location,attack,range,fightImg, walkImg, player, type);
		return u;
	}

	/**
	 * Returns yagura with characteristics according to type.
	 * @param type type of yagura
	 * @param player 1 if the yagura belongs to player 1, 2 if it belongs to player 2.
	 * @return built yagura
	 * @throws IOException 
	 */
	public static Yagura makeYagura(PApplet parent, int type, int player) throws IOException {

		if (type != AgeUtility.EARTH_YAGURA && type != AgeUtility.WIND_YAGURA)
			throw new InvalidTypeException("Yagura type does not exist.");

		File bgURL = new File("data\\YaguraInfo");
		ArrayList<ArrayList<String>> data = readfileIntoList(bgURL.getAbsolutePath());
		int attackCol = data.get(0).indexOf("Attack");
		int rangeCol = data.get(0).indexOf("Range");

		int row = (type == AgeUtility.EARTH_YAGURA) ? 1 : 2;
		char direction = (player == 1) ? 'l' : 'r';
		int location = (player == 1) ? player1UnitStartingLocation : player2UnitStartingLocation;

		PImage restImg = parent.loadImage(type+"r"+direction+".png");
		PImage activeImg = parent.loadImage(type+"a"+direction+".png");

		restImg.resize(YAGURA_WIDTH, YAGURA_HEIGHT);
		activeImg.resize(YAGURA_WIDTH, YAGURA_HEIGHT);

		Yagura y = new Yagura(restImg, activeImg, Integer.parseInt(data.get(row).get(attackCol)), location,
				Integer.parseInt(data.get(row).get(rangeCol)), parent, type, player);

		return y;
	}

	/**
	 * Returns cost of type of unit or yagura.
	 * @param type type of unit or yagura
	 * @return cost
	 * @throws IOException 
	 */
	public static int getCost(int type) throws IOException {
		if (type <= AgeUtility.ALI_BABA && type >= AgeUtility.SHINOBI) {
			File bgURL = new File("data\\UnitInfo");
			ArrayList<ArrayList<String>> data = readfileIntoList(bgURL.getAbsolutePath());
			int costCol = data.get(0).indexOf("Cost");
			return Integer.parseInt(data.get(type+1).get(costCol));
		}
		else {
			File bgURL = new File("data\\YaguraInfo");			
			ArrayList<ArrayList<String>> data = readfileIntoList(bgURL.getAbsolutePath());
			int costCol = data.get(0).indexOf("Cost");
			if (type == AgeUtility.EARTH_YAGURA) 
				return Integer.parseInt(data.get(1).get(costCol));
			else if (type == AgeUtility.WIND_YAGURA)
				return Integer.parseInt(data.get(2).get(costCol));
			else
				throw new InvalidTypeException("This type of unit or yagura does not exist.");
		}
	}


	/**
	 * Returns int representing special available in a certain age
	 * @param age given age
	 * @return special available
	 */
	public static int getSpecial(int age) {
		return (age == AgeUtility.DARK) ? AgeUtility.DAMAGE_SPECIAL : AgeUtility.HEAL_SPECIAL;
	}

	/**
	 * Returns the amount of xp required for the player to age up from the current age
	 * @param currentAge the current age
	 * @return the xp to age up
	 */
	public static int xpToAgeUp(int currentAge)
	{
		switch(currentAge) {
		case AgeUtility.DARK:
			return 700;
		}
		return 0;
	}

	/**
	 * Returns the cooldown on a the special passed in.
	 * @param special which special the cooldown is desired to know
	 * @return the cooldown of the special
	 * @throws IOException 
	 */
	public static int getCooldown(int special) throws IOException
	{
		if (special != HEAL_SPECIAL && special != DAMAGE_SPECIAL)
			throw new InvalidTypeException("Special type does not exist.");

		File bgURL = new File("data\\SpecialInfo");
		ArrayList<ArrayList<String>> data = readfileIntoList(bgURL.getAbsolutePath());
		int coolCol = data.get(0).indexOf("Cooldown");
		return (special == AgeUtility.HEAL_SPECIAL) ? Integer.parseInt(data.get(2).get(coolCol)) 
				: Integer.parseInt(data.get(1).get(coolCol));
	}
}
