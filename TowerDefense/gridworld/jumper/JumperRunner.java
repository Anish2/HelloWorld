package jumper;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;

public class JumperRunner 
{

	public static void main(String[] args)
	{   
		ActorWorld world = new ActorWorld();
		Jumper keishav = new Jumper(100, 100, 155);
		world.add(keishav);
		Rock rock = new Rock();
		world.add(rock);
		world.show();
	}

}
