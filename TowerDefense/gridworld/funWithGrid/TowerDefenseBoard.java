package funWithGrid;

import java.util.ArrayList;

import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

public class TowerDefenseBoard extends World<TowerDefenseObject>
{
	private Tower currentTowerToBuild; //Set this to a default once i make a basic tower
	private int gold = 200;// Decide on starting gold
	private int wave = 1; // The current wave
	private int lives = 10; // Decide on starting lives
	private ArrayList<Monster> toBeDeployed; // The monsters that are waiting to be deployed
	private ArrayList<Monster> inGrid; // The monsters that are already in the grid 
	public BoundedGrid<TowerDefenseObject> grid; //The grid
	int size = 20; // Size of the grid

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
		grid  = new BoundedGrid<TowerDefenseObject>(size, size);
		super.setMessage("Gold :" + gold + " Lives : " + lives + "Wave :" + wave + "\n" + "Current Tower Type :" + currentTowerToBuild.getClass() + " To Change This Tower Type, Press b for basic tower, s for splash tower, and l for long range tower" );
	}
	
	public void generateRandomField() // Needs testing
	{
		 ArrayList<Location> monsterPath = new ArrayList<Location>();
		 Location loc = new Location(0,0);
		 while(loc.getCol() != size - 1 || loc.getRow() != size - 1)
		 {
			 monsterPath.add(loc);
			 int rand = (int)(Math.random() * 2);
			 Location downLoc = new Location(loc.getRow() + 1, loc.getCol());
			 Location rightLoc = new Location(loc.getRow(), loc.getCol() + 1);
			 
			 if(!grid.isValid(rightLoc) && !grid.isValid(downLoc))
			 {
				 throw new IndexOutOfBoundsException("You are getting two invalid locations");
			 }
			 
			 if(rand == 0)
			 {
				 loc = downLoc;
			 }
			 else
			 {
				 loc = rightLoc;
			 }
			 
			 if(!grid.isValid(rightLoc))
			 {
				 loc = downLoc;
			 }
			 if(!grid.isValid(downLoc))
			 {
				 loc = rightLoc;
			 }
		 }
		
	}
}
