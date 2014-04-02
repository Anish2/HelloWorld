package critterHWQuiz.p4;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.Color;

import critters.BlusterCritter;
import critters.ChameleonCritterModified;
import critters.ChameleonKid;
import critters.KingCrab;
import critters.QuickCrab;
import critters.RockHound;

public class WolfRunner 
{
	 public static void main(String[] args)
	    {
	        ActorWorld world = new ActorWorld();
	        world.add(new Location(7, 8), new Rock());
	        world.add(new Location(3, 3), new Rock());
	        world.add(new Location(2, 8), new Flower(Color.BLUE));
	        world.add(new Location(5, 5), new Flower(Color.PINK));
	        world.add(new Location(1, 5), new Flower(Color.RED));
	        world.add(new Location(7, 2), new Flower(Color.YELLOW));
	        world.add(new SheepCritter());
	        world.add(new WolfCritter());
	        world.show();
	    }
}
