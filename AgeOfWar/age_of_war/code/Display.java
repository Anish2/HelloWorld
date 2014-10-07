package code;

import java.util.ArrayList;

import processing.core.PApplet;

/**
 * Shows battlefield movement, fighting, and age updates.
 * @author Anish Visaria, Eitan Zlatin
 *
 */
public class Display {
	
	private PApplet parent;
	private ArrayList<Unit> units = new ArrayList<Unit>();
	/**
	 * Constructs display and initializes original setup to default age.
	 */
	public Display(PApplet p) {
		parent = p;
	}
	
	/**
	 * Shows display frame on screen.
	 */
	public void show() {
		parent.show();
	}
	
	/**
	 * Hides display frame.
	 */
	public void hide() {
		parent.hide();
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
	
	public void draw()
	{
		for(Unit)
	}
	
	
}
	