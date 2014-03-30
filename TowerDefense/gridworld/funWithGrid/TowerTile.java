package funWithGrid;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class TowerTile extends TowerDefenseObject // This is a tile that monster's cannot step on
{
	private Grid grid;
	private Location loc;
	
	public void putSelfInGrid(Grid g, Location l) 
	{
		grid = g;
		loc = l;
	}
	
	
}
