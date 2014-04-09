package funWithGrid;

import java.util.ArrayList;

import javax.swing.text.BadLocationException;

import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

public class TowerDefenseBoard2 extends World<TowerDefenseObject>
{
	//private Tower currentTowerToBuild = new Tower(); //Set this to a default once i make a basic tower
	private int gold = 200;// Decide on starting gold
	private int wave = 1; // The current wave
	private int lives = 10; // Decide on starting lives
	private ArrayList<Monster> toBeDeployed; // The monsters that are waiting to be deployed
	//private ArrayList<Monster> inGrid; // The monsters that are already in the grid 
	private BoundedGrid<TowerDefenseObject> grid; //The grid
	private final int size = 20; // Size of the grid
	private final int towercost = 100; // Cost to build a tower
	private final int monsterGold = 20;//Gold recieved for killing a monster

	public void addGold(int amt)
	{
		gold += amt;
	}

	public boolean hasMonsters(){
		for(int x = 0; x < grid.getOccupiedLocations().size(); x++)
		{
			if(grid.get(grid.getOccupiedLocations().get(x)) instanceof Monster)
			{
				return true;
			}
		}

		return false;
	}

	public boolean keyPressed(String description, Location loc)
	{
		if(description.equals("Q"))
		{
			nextWave();
			do
			{
				step();
				this.show();
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} while(hasMonsters());
		}

		return false;
	}



	public void nextWave()
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


	public boolean locationClicked(Location loc)
	{
		System.out.println("yes");
		TowerDefenseObject object = getGrid().get(loc);
		if(object == null)
		{
			return true;
		}
		if(object instanceof Tower)
		{
			int levelupcost = 50;
			if(gold > levelupcost)
			{
				gold-= levelupcost;
				((Tower) object).levelUp();
			}
		}
		if(object instanceof TowerTile)
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
		if(lives == 0)
		{
			//Figure out how to end game
		}
		Location end = new Location(size-1,size-1);
		TowerDefenseObject monster = (Monster)getGrid().get(end);
		if(monster !=null)
		{
			monster.removeSelfFromGrid();
			lives--;
		}
		updateMessage();

		ArrayList<Location> locs = getGrid().getOccupiedLocations();
		for(int x = 0; x < locs.size(); x++)
		{
			TowerDefenseObject o = getGrid().get(locs.get(x));
			if(o != null)
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

	public void updateMessage()
	{
		super.setMessage("Gold : " + gold + "  Lives :  " + lives + "  Wave : " + wave + "\n" + "Current Tower Type : Basic" +  "   Press q to send the next wave"  );
	}


	public TowerDefenseBoard2()
	{
		toBeDeployed = new ArrayList<Monster>();
		grid  = new BoundedGrid<TowerDefenseObject>(size, size);
		setGrid(grid);
		updateMessage();	}

	public void generateRandomField() 
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
