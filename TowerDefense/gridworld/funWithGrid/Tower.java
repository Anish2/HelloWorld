package funWithGrid;

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
	
	
}
