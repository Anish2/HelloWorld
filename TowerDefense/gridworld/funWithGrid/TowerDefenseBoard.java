package funWithGrid;

import java.util.ArrayList;

import info.gridworld.grid.Location;
import info.gridworld.world.World;

public class TowerDefenseBoard extends World<TowerDefenseObject>
{
	private Tower currentTowerToBuild; //Set this to a default once i make a basic tower
	private int gold;// Decide on starting gold
	private int wave = 1; // The current wave
	private int lives; // Decide on starting lives
	private ArrayList<Monster> toBeDeployed; // The monsters that are waiting to be deployed
	private ArrayList<Monster> inGrid; // The monsters that are already in the grid
	
	public boolean keyPressed(String description, Location loc)
	{
		System.out.println(description);
		return false;
	}
	
	public boolean locationClicked(Location loc)
	{
		return false;
	}
}
