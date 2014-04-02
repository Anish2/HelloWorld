package critterHWQuiz.p4;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A WolfCritter has a 50% chance of eating a SheepCritter
 * immediately in front of it. A WolfCritter does not eat
 * any other actors and does not consider any other locations.
 * 
 * If a WolfCritter has eaten for 4 consecutive runs of act
 * (including the current run) it produces a new WolfCritter
 * immediately in front of it.
 * 
 * If a WolfCritter has not eaten for 10 consecutive runs of
 * act it dies (removes itself from the grid). If a WolfCritter
 * does not die it moves like a Critter.
 *
 */
public class WolfCritter extends Critter
{
	public int hunger;
	public int saturation;
	
	public WolfCritter()
	{
		hunger = 0;
		saturation = 0;
	}
	
	public ArrayList<Actor> getActors()
	{
		Location thisLoc = this.getLocation();
		Location newLoc = thisLoc.getAdjacentLocation(this.getDirection());
		ArrayList<Actor> actors = new ArrayList<Actor>();
		
		if(getGrid().isValid(newLoc) && getGrid().get(newLoc) instanceof SheepCritter)
		{
			actors.add(getGrid().get(newLoc));
		}
		return actors;
	}
	
	public void processActors(ArrayList<Actor> actors)
	{
		if(actors.size() == 0)
		{
			starve();
			return;
		}
		
		int rand = (int)(2 * Math.random());
//		int rand = 0;
		
		if(rand == 0)
		{
			hunger = 0;
			saturation++;
			
			if(saturation >= 4)
			{
				WolfCritter newCritter = new WolfCritter();
				newCritter.putSelfInGrid(getGrid(), actors.get(0).getLocation());
			}
			else
			{
				actors.get(0).removeSelfFromGrid();
			}
		}
		else
		{
			starve();
		}
	}
	
	public void starve()
	{
		hunger++;
		saturation = 0;
	}
	
	public Location selectMoveLocation(ArrayList<Location> locs)
	{
		if(hunger >=10)
		{
			return null;
		}
		return super.selectMoveLocation(locs);
	}
}
