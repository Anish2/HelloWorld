package code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import processing.core.PApplet;

/**
 * Handles displaying the game, updating the player stats.
 * @author Anish Visaria and Eitan Zlatin
 *
 */
public class War {
	
	private Player a, b;
	private ArrayList<Unit> units = new ArrayList<Unit>();
	private ArrayList<Yagura> yaguras = new ArrayList<Yagura>();
	private Display parent;
	
	/**
	 * Constructs a War with two players.
	 * @param a player one
	 * @param b player two
	 */
	public War(Display d, Player a, Player b) 
	{
		parent = d;
		this.a = a;
		this.b = b;
	} 
	
	/**
	 * Activates all units and yaguras on battlefield to attack or change position.
	 * Deploys units and yaguras that a Player desires on the battlefield.
	 * Executes all actions player commands including upgrades.
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public void act() throws IOException 
	{
		/*if (parent.mousePressed)
			System.out.println("It Works");*/
		

		/*Collections.sort(units);
		
		for (int x = 0 ; x < units.size(); x ++) 
		{
			Unit u = units.get(x);
			Unit adjacentU = units.get(x + 1);
			if(u.getPos() + u.getRange() <=  adjacentU.getPos() && )
			{
				
			}
			u.move(u.getPos()+4);
		}*/
		
		
		
		/*for (Yagura y: yaguras) {
			y.move(u.getPos()+2);
		}*/
		
		for (Unit u: units) {
			u.move(u.getPos()+4);
		}
		
		for (int type: a.getMaterialsToBuild()) {
			units.add(AgeUtility.makeUnit(parent, type, 1));
		}

		//System.out.println(a.getGold());
		//System.out.println(units);
		
		
		
	}
	

}
