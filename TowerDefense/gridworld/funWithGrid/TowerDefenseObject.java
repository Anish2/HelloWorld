package funWithGrid;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class TowerDefenseObject 
{
	private Location loc;
	private Grid<TowerDefenseObject> grid;
	
	public Location getLoc() {
		return loc;
	}

	public Grid<TowerDefenseObject> getGrid() {
		return grid;
	}

	public void removeSelfFromGrid()
	{
		grid = null;
		grid.remove(loc);
		loc = null;
	}
	
	public void putSelfInGrid(Grid<TowerDefenseObject> gr, Location loc)
	{
		this.loc = loc;
		grid = gr;
		grid.put(loc, this);
	}
}
