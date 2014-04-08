package funWithGrid;

import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class Tower extends TowerDefenseObject
{
	private int damage;
	private int sellGold; // Do we want them to be able to sell towers, or is that too much
	
	public Tower()
	{
		damage = 10;
	}
	
	public void levelUp()
	{
		damage+=5;
	}
	
	public int getDamage()
	{
		return damage;
	}
	
	public void setDamage(int damage) 
	{
		this.damage = damage;
	}
	
	public int getSellGold()
	{
		return sellGold;
	}
	
	public void setSellGold(int sellGold) 
	{
		this.sellGold = sellGold;
	}
	
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
				if(getGrid().get(loc) instanceof Monster)
				{
					monsters.add((Monster) getGrid().get(loc));
				}
			}
		}
		
		int rand = (int)(Math.random() * monsters.size());
		if(monsters.size() !=0)
		{
			monsters.get(rand).damage(damage);
		}
	}
	
	
}
