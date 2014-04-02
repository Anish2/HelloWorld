package critters;

import info.gridworld.actor.Actor;//
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class ChameleonKid extends ChameleonCritterModified
{
	public ArrayList<Actor> getActors()
	{
		ArrayList<Actor> actors = new ArrayList<Actor>();
		
		Location front = (getLocation().getAdjacentLocation(getDirection() + Location.AHEAD));
		Location back =  (getLocation().getAdjacentLocation(getDirection() + Location.HALF_CIRCLE));
		if(getGrid().isValid(front) && getGrid().get(front) !=null)
		{
			actors.add(getGrid().get(front));
		}
		if(getGrid().isValid(back)&& getGrid().get(back) !=null)
		{
			actors.add(getGrid().get(back));
		}
		
		return actors;
	}
}
