package funWithGrid;

public class Tower extends TowerDefenseObject
{
	private int damage;
	private int level = 1; //What level is the tower at
	private int sellGold; // Do we want them to be able to sell towers, or is that too much
	
	public int getDamage()
	{
		return damage;
	}
	
	public void setDamage(int damage) 
	{
		this.damage = damage;
	}
	
	public int getLevel() 
	{
		return level;
	}
	
	public void setLevel(int level) 
	{
		this.level = level;
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
