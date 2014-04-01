package funWithGrid;

import info.gridworld.grid.Location;

public class Monster extends TowerDefenseObject
{
	Location loc;
	int killGold;
	
	public Monster(Location location, int gold) 
	{
		loc = location;
		killGold = gold;
	}
	
	public void removeSelfFromGrid()//Needs implementation
	{
		
	}
}
