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
	public void act() 
	{
		
		
		
	}
	

}
