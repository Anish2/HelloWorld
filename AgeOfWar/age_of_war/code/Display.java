package code;

import java.io.IOException;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Shows battlefield movement, fighting, and age updates.
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
//	private boolean rectOver = false;

	public void setup() 
	{
		player1= new HumanPlayer(this,1);
		w = new War(this, player1, new AIPlayer(this,2));

		field = loadImage("field.jpg");
		size(field.width, field.height);
	}

	public void draw()
	{
	//	update(mouseX, mouseY);
		size(field.width, field.height);
		background(field);
		
		fill(255,0,0);
		rect(0, 300, 200, 250);
		
		fill(0,0,255);
		rect(1000, 300, 200, 250);
		
		fill(255);
		
		
		stroke(0);
		rect(unit1[0], unit1[1], rectSize, rectSize);
		rect(unit2[0], unit2[1], rectSize, rectSize);
		rect(yagura[0], yagura[1], rectSize, rectSize);
		rect(special[0], special[1], rectSize, rectSize);
		rect(nextAge[0], nextAge[1], rectSize, rectSize);
		
		
		fill(0);
		text("Melle unit", 25, 50);
		text("Ranged unit", 100, 50);
		text("Yagura", 190, 50);
		text("Use Special", 260, 50);
		text("Age Up", 350, 50);
		
		fill(255);
		text("Gold :" + player1.getGold(), 850 , 50);
		text("Xp :" + player1.getXp(), 950 , 50);
		text("Age :" + (player1.getAge() - 3), 1050 , 50);//The three is to convert it from 4 and 5 to 1 and 2
		
		
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
	

	//public void update(int x, int y) {
		//rectOver = overRect(unit1[0], unit1[1], rectSize, rectSize);
//=	}

	public boolean overRect(int x, int y, int width, int height)  {
		return mouseX >= x && mouseX <= x+width && mouseY >= y && mouseY <= y+height;
	}



	/**
	 * Updates age home base image.
	 * @param p player that needs update
	 */
	public void updateAge(Player p) {

	}

	//public void updateUnits(ArrayList<Unit> units)
	//{
	//	this.units = units;
//	}


}
