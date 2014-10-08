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

	private ArrayList<Unit> units = new ArrayList<Unit>();
	private PImage field;
	private int[] unit1, unit2, yagura, special, nextAge;
	private int rectSize = 80;
	private boolean rectOver = false;


	public void setup() {
		field = loadImage("C:\\Users\\Anish\\git\\HelloWorld\\AgeOfWar\\game_data\\field.jpg");
		size(field.width, field.height);
		unit1 = new int[] {width/4-rectSize-10, 5};
		unit2 = new int[] {unit1[0]+rectSize, 5};
		yagura = new int[] {unit2[0]+rectSize, 5};
		special = new int[] {yagura[0]+rectSize, 5};
		nextAge = new int[] {special[0]+rectSize, 5};
	}

	public void draw()
	{
		update(mouseX, mouseY);
		background(field);

		if (rectOver) {
			try {
				Unit unit = AgeUtility.makeUnit(this, AgeUtility.SHINOBI, 1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		stroke(0);
		rect(unit1[0], unit1[1], rectSize, rectSize);
		rect(unit2[0], unit2[1], rectSize, rectSize);
		rect(yagura[0], yagura[1], rectSize, rectSize);
		rect(special[0], special[1], rectSize, rectSize);
		rect(nextAge[0], nextAge[1], rectSize, rectSize);

	}

	public void update(int x, int y) {
		rectOver = overRect(unit1[0], unit1[1], rectSize, rectSize);
	}

	public boolean overRect(int x, int y, int width, int height)  {
		return mouseX >= x && mouseX <= x+width && mouseY >= y && mouseY <= y+height;
	}

	public void mousePressed() {
		/*if (rectOver) {
			try {

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}

	/**
	 * Updates age home base image.
	 * @param p player that needs update
	 */
	public void updateAge(Player p) {

	}

	public void updateUnits(ArrayList<Unit> units)
	{
		this.units = units;
	}


}
