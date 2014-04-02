package critters;

import info.gridworld.grid.Location;

import java.util.ArrayList;

public class QuickCrab extends CrabCritter
{
	public ArrayList<Location> getMoveLocations()
	{
		ArrayList<Location> locs = new ArrayList<Location>();
		//
		ArrayList<Location> leftRight = super.getMoveLocations();
		
		for(Location loc : leftRight)
		{
			Location farLoc = loc.getAdjacentLocation(this.getLocation().getDirectionToward(loc));
			if(getGrid().isValid(farLoc) && getGrid().get(farLoc) == null)
			{
				locs.add(farLoc);
			}
		}
		
		if(locs.size() == 0)
		{
			return super.getMoveLocations();
		}
		
		return locs;
	}
	
}
