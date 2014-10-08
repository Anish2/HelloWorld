package code;

import java.io.File;
import java.io.IOException;
import java.net.URL;
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

	private final int rectSize = 80;
	public final  int[] unit1 = new int[] {width/4-rectSize-10, 5};
	public final int[] unit2 = new int[] {unit1[0]+rectSize, 5};
	public final int[] yagura = new int[] {unit2[0]+rectSize, 5};
	public final int[] special = new int[] {yagura[0]+rectSize, 5};
	public final int[] nextAge = new int[] {special[0]+rectSize, 5};

	private boolean rectOver = false;


	public void setup() {

		field = loadImage("field.jpg");
		size(field.width, field.height);
	}

	public void draw()
	{
		update(mouseX, mouseY);
		background(field);

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
