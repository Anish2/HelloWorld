package code;

import java.io.IOException;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Displays buttons for the human to click, the player's bases, and the humans current gold and xp.
 * Also displays cost for each unit to make and yagura.
 * @author Anish Visaria, Eitan Zlatin
 *
 */
public class Display extends PApplet {

	private PImage field;
	private War w;

	public final int rectSize = 80;
	public final  int[] unit1 = new int[] {width/10+10, 5};
	public final int[] unit2 = new int[] {unit1[0]+rectSize, 5};
	public final int[] yagura = new int[] {unit2[0]+rectSize, 5};
	public final int[] special = new int[] {yagura[0]+rectSize, 5};
	public final int[] nextAge = new int[] {special[0]+rectSize, 5};

	private HumanPlayer player1;

	/**
	 * Makes war and initializes battle arena size and bgImage.
	 */
	public void setup() 
	{
		player1= new HumanPlayer(this,1);
		w = new War(this, player1, new AIPlayer(this,2));

		field = loadImage("field.jpg");
		size(field.width, field.height);
	}

	/**
	 * Draws and updates field.
	 */
	public void draw()
	{
		size(field.width, field.height);
		background(field);

		fill(255,0,0);
		rect(0, 300, 200, 250);

		fill(0,0,255);
		rect(1000, 300, 200, 250);

		fill(255);

		stroke(0);

		fill(255);
		rect(unit1[0], unit1[1], rectSize, rectSize);
		rect(unit2[0], unit2[1], rectSize, rectSize);
		rect(yagura[0], yagura[1], rectSize, rectSize);
		rect(special[0], special[1], rectSize, rectSize);
		rect(nextAge[0], nextAge[1], rectSize, rectSize);


		fill(0);
		text("Melle unit", 25, 50);
		try {
			text("" + AgeUtility.getCost(AgeUtility.getUnits(player1.getAge())[0]) + " gold", 35 ,65);
		} catch (IOException e1) { 
			e1.printStackTrace(); 
		}
		text("Ranged unit", 100, 50);
		try {
			text("" + AgeUtility.getCost(AgeUtility.getUnits(player1.getAge())[1]) + " gold", 110 ,65);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		text("Yagura", 190, 50);
		int cost = 0;
		try {
			cost = (player1.getAge() == AgeUtility.DARK) ? AgeUtility.getCost(AgeUtility.EARTH_YAGURA) :
				AgeUtility.getCost(AgeUtility.WIND_YAGURA);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		text("" + cost + " gold" , 190, 65);
		
		text("Use Special", 260, 50);
		text("Age Up", 350, 50);

		fill(255);
		text("Gold :" + player1.getGold(), 850 , 50);
		text("Xp :" + player1.getXp(), 950 , 50);
		text("Age :" + (player1.getAge() - 3), 1050 , 50);


		
			try {
				w.act();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}






}
