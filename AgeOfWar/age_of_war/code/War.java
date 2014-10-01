package code;

import java.util.ArrayList;

/**
 * Handles displaying the game, updating the player stats.
 * @author Anish Visaria and Eitan Zlatin
 *
 */
public class War {
	
	private Player a, b;
	private ArrayList<Unit> units = new ArrayList<Unit>();
	private ArrayList<Yagura> yaguras = new ArrayList<Yagura>();
	
	/**
	 * Constructs a War with two players.
	 * @param a player one
	 * @param b player two
	 */
	public War(Player a, Player b) 
	{
		this.a = a;
		this.b = b;
	}
	
	/**
	 * Activates all units and yaguras on battlefield to attack or change position.
	 * Deploys units and yaguras that a Player desires on the battlefield.
	 * Executes all actions player commands including upgrades.
	 */
	public void act() {
		
	}
	
/*	*//**
	 * Adds a unit to the list of units on battlefield.
	 * @param u unit to add
	 *//*
	public void addUnit(Unit u) {
		units.add(u);
	}
	
	
	*//**
	 * Adds yagura to list of yaguras.
	 * @param y yagura to add
	 *//*
	public void addYagura(Yagura y) {
		yaguras.add(y);
	}

	*/

}
