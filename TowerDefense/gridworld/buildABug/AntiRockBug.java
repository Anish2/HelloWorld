package buildABug;

import java.util.ArrayList;
//Eitan Zlatin 3/1/14

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class AntiRockBug extends Bug
{
	private Location target = null;
	
	/**
	 * (Also, this bug is stupid and does not keep track of moving rocks. ROCKS ARE NOT SUPPOSED TO MOVE!)
	 * If target is not a rock bug, checks if a rock bug is within a 4 by 4 square, if so changes target to the rock bug
	 * if there is already a target, moves toward the target
	 * if there is not one spends a move using specialized AntiRockBug homing systems to find a new rock and lock on to it
	 * If there are no rocks or rock bugs in a 4x4 area then it turns
	 */
	public void act()
	{
		ArrayList<Actor> actors = getRocks(4);
		
		if(target == null)
		{
			if(actors.size() == 0)
			{
				turn();
			}
			else
			{
				int rand = (int)(Math.random() * actors.size());
				
				target = actors.get(rand).getLocation();
			}
		}
		
		if(target !=null && !(getGrid().get(target) instanceof RockBug))
		{
			for(Actor a : actors)
			{
				if(a instanceof RockBug)
				{
					target = a.getLocation();
				}
			}
		}
		
		if(target !=null)
		{
			Location nextLocation = this.getLocation().getAdjacentLocation(this.getLocation().getDirectionToward(target));
			if(nextLocation.equals(target))
			{
				target = null;
			}
			move(nextLocation);
		}
		
	}
	
	/*
	 * Precondition: Distance is even. 
	 * returns all the rocks and rock bugs within a distance/2 by distance/2 square.
	 */
	public ArrayList<Actor> getRocks(int distance)
	{
		int halfDistance = distance/2;
		int row = getLocation().getRow();
		int col = getLocation().getCol();
		ArrayList<Actor> actors = new ArrayList<Actor>();
		
		for(int x = row - halfDistance; x<= halfDistance + row; x++)
		{
			for(int y = col - halfDistance; y<= halfDistance + col; y++)
			{
				Location loc = new Location(x,y);
				if(getGrid().isValid(loc) && (getGrid().get(loc) instanceof Rock || getGrid().get(loc) instanceof RockBug))
				{
					actors.add(getGrid().get(loc));
				}
			}
		}
		
		return actors;
	}
	
	/**
	 * Moves to the next location if it is valid, else removes this from grid
	 * @param next the location to move to
	 */
	public void move(Location next)
	{
		Grid<Actor> gr = getGrid();
		if(gr == null)
		{
			return;
		}
		
		if(gr.isValid(next))
		{
			moveTo(next);
		}
		else
		{
			removeSelfFromGrid();
		}
	}
}
