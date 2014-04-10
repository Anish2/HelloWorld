package funWithGrid;

import java.util.ArrayList;

import info.gridworld.grid.Location;

public class Tower extends TowerDefenseObject
{
	private int damage, sellGold, level;

	/**
	 * Constructs a level 1 Tower with damage 5.
	 */
	public Tower()
	{
		damage = 5;
		setSellGold();
		level = 1;
	}

	/**
	 * Upgrades Tower.
	 */
	public void levelUp()
	{
		damage += 5;
		setSellGold();
		level++;
	}
	
	/**
	 * Returns level.
	 * @return level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Return damage capability.
	 * @return damage
	 */
	public int getDamage()
	{
		return damage;
	}


	/**
	 * Return sell gold. Sell gold is 65% of damage level.
	 * @return sell gold
	 */
	public int getSellGold()
	{
		return sellGold;
	}

	private void setSellGold() 
	{
		sellGold = (int)(damage*0.65);
	}

	/**
	 * Damages one monster within halfDistance range.
	 */
	public void act()
	{
		int halfDistance = 2;
		int row = getLoc().getRow();
		int col = getLoc().getCol();
		ArrayList<Monster> monsters = new ArrayList<Monster>();

		for(int x = row - halfDistance; x<= halfDistance + row; x++)
		{
			for(int y = col - halfDistance; y<= halfDistance + col; y++)
			{
				Location loc = new Location(x,y);
				if(getGrid().isValid(loc) && getGrid().get(loc) instanceof Monster)
				{
					monsters.add((Monster) getGrid().get(loc));
				}
			}
		}
		
		if (monsters.size() == 0) return;
		
		int rand = (int)(Math.random() * monsters.size());

		monsters.get(rand).damage(damage);

	}


}
