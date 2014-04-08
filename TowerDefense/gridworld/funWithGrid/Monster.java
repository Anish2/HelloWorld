package funWithGrid;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Monster extends TowerDefenseObject
{
	int killGold;
	int health;
	TowerDefenseBoard board;
	
	public Monster(int gold, int health, TowerDefenseBoard b) 
	{
		killGold = gold;
		this.health = health;
		board = b;
	}
	
	public void damage(int amt)
	{
		health -= amt;
		if(isDead())
		{
			board.addGold(killGold);
			removeSelfFromGrid();
		}
	}
	
	public boolean isDead() 
	{
		return health <= 0;
	}
	
	public void act()
	{
		 Location downLoc = new Location(getLoc().getRow() + 1, getLoc().getCol());
		 Location rightLoc = new Location(getLoc().getRow(), getLoc().getCol() + 1);
		 if(getGrid().isValid(downLoc) && getGrid().get(downLoc) == null)
		 {
			 Grid<TowerDefenseObject> grid = getGrid();
			 this.removeSelfFromGrid();
			 this.putSelfInGrid(grid, downLoc);
		 }
		 else if(getGrid().isValid(rightLoc) && getGrid().get(rightLoc) == null)
		 {
			 Grid<TowerDefenseObject> grid = getGrid();
			 this.removeSelfFromGrid();
			 this.putSelfInGrid(grid, rightLoc);
		 }
	}
}
