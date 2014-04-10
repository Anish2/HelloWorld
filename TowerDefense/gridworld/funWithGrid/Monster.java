package funWithGrid;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Monster extends TowerDefenseObject
{
	int killGold;
	int health;
	TowerDefenseBoard board;

	/**
	 * Constructs monster with kill gold, health, and board.
	 * @param gold kill gold
	 * @param health strength
	 * @param b board
	 */
	public Monster(int gold, int health, TowerDefenseBoard b) 
	{
		killGold = gold;
		this.health = health;
		board = b;
	}

	/**
	 * Damages monster by amount amt.
	 * @param amt amount to damage.
	 */
	public void damage(int amt)
	{
		health -= amt;
		if(isDead())
		{
			board.addGold(killGold);
			removeSelfFromGrid();
		}
	}

	/**
	 * Checks if the monster is dead.
	 * @return if monster is dead
	 */
	public boolean isDead() 
	{
		return health <= 0;
	}

	/**
	 * String representation.
	 */
	public String toString() {
		return("Monster with health:"  + health);
	}

	/**
	 * Follows path of the board.
	 */
	public void act()
	{
		Location downLoc = new Location(getLoc().getRow() + 1, getLoc().getCol());
		Location rightLoc = new Location(getLoc().getRow(), getLoc().getCol() + 1);
		if(getGrid().isValid(downLoc) && getGrid().get(downLoc) == null)
		{
			move(downLoc);
		}
		else if(getGrid().isValid(rightLoc) && getGrid().get(rightLoc) == null)
		{
			move(rightLoc);
		}
	}

	private void move(Location loc) {
		Grid<TowerDefenseObject> g = getGrid();
		this.removeSelfFromGrid();
		this.putSelfInGrid(g, loc);
	}
}
