package critters;

import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

//
public class BlusterCritter extends Critter
{
	private int courage;
	
	public BlusterCritter(int c)
	{
		courage = c;
	}
	
	public ArrayList<Actor> getActors()
	{
		ArrayList<Actor> list = new ArrayList<Actor>();
		Grid<Actor> gr = this.getGrid();
		int row = this.getLocation().getRow();
		int col = this.getLocation().getCol();
		
		for(int x = row - 2; x <= row + 2; x++)
		{
			for(int y = col - 2; y<=col+2; y++)
			{
				Location loc = new Location(x,y);
				if(gr.isValid(loc) && gr.get(loc) !=null && (x!=this.getLocation().getRow() && y!=this.getLocation().getCol()))
				{
					if(gr.get(loc) instanceof Critter)
					list.add(gr.get(loc));
				}
			}
		}
		
		return list;
	}
	
	public void processActors(ArrayList<Actor> actors)
	{
		if(actors.size() < courage)
		{
			this.setColor(this.getColor().brighter());
		}
		else
		{
			this.setColor(this.getColor().darker());
		}
		
	}
}
