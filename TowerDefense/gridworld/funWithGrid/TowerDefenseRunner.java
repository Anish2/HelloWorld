package funWithGrid;

import info.gridworld.grid.Location;

public class TowerDefenseRunner 
{
	public static void main(String[] args)
	{
		TowerDefenseBoard t = new TowerDefenseBoard();
		
		t.show();
		
		Tower tow = new Tower();
		tow.putSelfInGrid(t.getGrid(), new Location(4,5));
		

		//t.generateRandomField();

	}
}
