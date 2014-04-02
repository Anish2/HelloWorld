package funWithGrid;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Monster extends TowerDefenseObject
{
	int killGold;
	int health;
	
	public Monster(int gold, int health) 
	{
		killGold = gold;
		this.health = health;
	}
}
