package funWithGrid;

import info.gridworld.grid.Location;

public class TowerDefenseRunner 
{
	public static void main(String[] args)
	{
		TowerDefenseBoard t = new TowerDefenseBoard();
		
		t.show();
		t.generateRandomField();

		
		Tower tow = new Tower();
		tow.putSelfInGrid(t.getGrid(), new Location(4,5));
		
		TowerTile to = new TowerTile();
		to.putSelfInGrid(t.getGrid(), new Location(3,5));
		
		Monster m = new Monster(250,1000);
		m.putSelfInGrid(t.getGrid(), new Location(1,1));

		//t.generateRandomField();


	}
}
