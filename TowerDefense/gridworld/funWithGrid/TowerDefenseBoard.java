package funWithGrid;

import java.util.ArrayList;

import info.gridworld.grid.Location;
import info.gridworld.world.World;

public class TowerDefenseBoard extends World<TowerDefenseObject>
{
	private Tower currentTowerToBuild; //Set this to a default once i make a basic tower
	private int gold = 200;// Decide on starting gold
	private int wave = 1; // The current wave
	private int lives = 10; // Decide on starting lives
	private ArrayList<Monster> toBeDeployed; // The monsters that are waiting to be deployed
	private ArrayList<Monster> inGrid; // The monsters that are already in the grid ///////

	public boolean keyPressed(String description, Location loc)
	{
		System.out.println(description);
		return false;
	}

	public boolean locationClicked(Location loc)
	{
		return false;
	}

	public TowerDefenseBoard()
	{
		toBeDeployed = new ArrayList<Monster>();
		inGrid = new ArrayList<Monster>();
		super.setMessage("Gold :" + gold + " Lives : " + lives + "Wave :" + wave + "\n" + "Current Tower Type :" + currentTowerToBuild.getClass() + " To Change This Tower Type, Press b for basic tower, s for splash tower, and l for long range 

				tower" );
	}
}
