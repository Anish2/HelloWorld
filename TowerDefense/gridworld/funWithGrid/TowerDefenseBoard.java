package funWithGrid;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

/**
 * Tower Defense Game Board
 * @author Anish Visaria and Eitan Zlatin
 *
 */
public class TowerDefenseBoard extends World<TowerDefenseObject>
{
	private int gold = 700;
	private int wave = 1; 
	private int lives = 10; 
	private ArrayList<Monster> toBeDeployed;
	private final int size = 20; 
	private final int towercost = 100; 
	private final int monsterGold = 20;

	/**
	 * Constructs a new board. Grid is set to size 20. Random field is generated.
	 */
	public TowerDefenseBoard()
	{
		toBeDeployed = new ArrayList<Monster>();
		BoundedGrid<TowerDefenseObject> grid  = new BoundedGrid<TowerDefenseObject>(size, size);
		setGrid(grid);
		generateRandomField();
		updateMessage();	
	}

	/**
	 * Adds gold to current gold. May only be called by a Monster.
	 * @param amt gold to be added
	 */
	public void addGold(int amt)
	{
		gold += amt;
		updateMessage();	
	}

	/**
	 * Handles key presses, such as sending the next wave.
	 */
	public boolean keyPressed(String description, Location loc)
	{

		if(description.equals("Q"))
		{
			nextWave();
			return true;
		}

		return false;
	}

	private void nextWave()
	{
		int numberOfMonsters = (int)(Math.random() * wave) + 1;
		int monsterHealth = ((int)(Math.random() * wave) + 1) * 10;
		for(int i = 0 ; i < numberOfMonsters; i++)
		{
			Monster monster = new Monster(monsterGold,monsterHealth, this);
			toBeDeployed.add(monster);
		}
		wave++;
	}


	/**
	 * Handles actions needed when location is clicked. If click is a tower options are shown. If click is a tile,
	 * new Tower is constructed.
	 */
	public boolean locationClicked(Location loc)
	{

		TowerDefenseObject object = getGrid().get(loc);

		if(object instanceof Tower)
		{

			int levelupcost = 50;

			Object[] options = {"Upgrade Tower", "Sell Tower", "Cancel"};
			int n = JOptionPane.showOptionDialog(null,
					"Tower Level: "+((Tower)object).getLevel()+
					"\nCost to upgrade: "+levelupcost+
					"\nGold obtained from sale: "+((Tower)object).getSellGold(),
					"Tower Defense",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					options,
					options[0]);

			if (n == 0) {
				if(gold >= levelupcost)
				{
					gold -= levelupcost;
					((Tower) object).levelUp();
				}
				else {
					JOptionPane.showMessageDialog(null,
							"Not enough gold to upgrade.");
					return false;
				}

			}
			else if (n == 1) {
				addGold(((Tower)object).getSellGold());
				object.removeSelfFromGrid();
				TowerTile tile = new TowerTile();
				tile.putSelfInGrid(getGrid(), loc);
			}
		}

		else if(object instanceof TowerTile)
		{
			if(gold >= towercost)
			{
				gold = gold - towercost;
				object.removeSelfFromGrid();
				Tower tower = new Tower();
				tower.putSelfInGrid(getGrid(), loc);
			}
		}

		updateMessage();
		return true;
	}

	public void step()
	{
		if(lives <= 0)
		{
			Object[] options = {"Exit"};
			JOptionPane.showOptionDialog(null,
					"You have lost.",
					"Tower Defense",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					options,
					options[0]);


			System.exit(0);

		}

		Location end = new Location(size-1,size-1);
		TowerDefenseObject monster = (Monster)getGrid().get(end);
		if(monster != null)
		{
			monster.removeSelfFromGrid();
			lives--;
		}
		updateMessage();

		ArrayList<Location> locs = getGrid().getOccupiedLocations();
		for(int x = 0; x < locs.size(); x++)
		{
			TowerDefenseObject o = getGrid().get(locs.get(x));
			if (o != null)
			{
				o.act();
			}
		}

		if (toBeDeployed.size() != 0)
		{
			Monster toSpawn = toBeDeployed.get(0);
			toSpawn.putSelfInGrid(getGrid(), new Location(0,0));
			toBeDeployed.remove(0);
		}
	}

	private void updateMessage()
	{
		super.setMessage("Gold : " + gold + "  Lives :  " + lives + "  Wave : " + wave +
				"\n" + "Press q to send the next wave, then hit run." +
				" To build a tower, click stop then click on the space you want to build it at. For "
				+ "more options click on the Tower");
	}


	private void generateRandomField() 
	{

		ArrayList<Location> monsterPath = new ArrayList<Location>();
		Location loc = new Location(0,0);

		while(loc.getCol() != size -1 || loc.getRow() != size -1 )
		{
			monsterPath.add(loc);
			int rand = (int)(Math.random() * 2);
			Location downLoc = new Location(loc.getRow() + 1, loc.getCol());
			Location rightLoc = new Location(loc.getRow(), loc.getCol() + 1);

			if(!getGrid().isValid(rightLoc) && !getGrid().isValid(downLoc))
			{
				throw new IndexOutOfBoundsException("You are getting two invalid locations");
			}

			if(rand == 0)
			{
				loc = downLoc;
			}
			else
			{
				loc = rightLoc;
			}

			if(!getGrid().isValid(rightLoc))
			{
				loc = downLoc;
			}
			if(!getGrid().isValid(downLoc))
			{
				loc = rightLoc;
			}

		}

		for(int x = 0; x < size; x++)
		{
			for(int y =0; y < size; y++)
			{

				loc = new Location(x,y);
				if(!monsterPath.contains(loc))
				{
					TowerTile tile = new TowerTile();
					tile.putSelfInGrid(getGrid(),loc);//
				}

			}
		}


		getGrid().get(new Location(size-1,size-1)).removeSelfFromGrid();


	}


}
