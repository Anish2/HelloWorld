package critters;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class KingCrab extends CrabCritter
{
	public void processActors(ArrayList<Actor> actors)
	{
		for(Actor actor : actors)
		{
			Location loc = actor.getLocation();
			Location farLoc = loc.getAdjacentLocation(this.getLocation().getDirectionToward(loc));
			if(!getGrid().isValid(farLoc) || getGrid().get(farLoc) != null)
			{
				actor.removeSelfFromGrid();
			}
			else
			{
				actor.moveTo(farLoc);
			}
		}
		
	}
}
