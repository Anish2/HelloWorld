package funWithGrid;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public abstract class TowerDefenseObject 
{
	private Location loc;
	private Grid<TowerDefenseObject> grid;
	
	/**
	 * Returns current location.
	 * @return location
	 */
	public Location getLoc() {
		return loc;
	}

	/**
	 * Returns current grid.
	 * @return grid
	 */
	public Grid<TowerDefenseObject> getGrid() {
		return grid;
	}

	/**
	 * removes this object from current grid.
	 */
	public void removeSelfFromGrid()
	{
		grid.remove(loc);
		grid = null;
		loc = null;
	}
	
	/**
	 * Puts this object in grid gr at location loc.
	 * @param gr Grid
	 * @param loc location
	 */
	public void putSelfInGrid(Grid<TowerDefenseObject> gr, Location loc)
	{
		this.loc = loc;
		grid = gr;
		grid.put(loc, this);
	}
	
	public abstract void act();
	
}
