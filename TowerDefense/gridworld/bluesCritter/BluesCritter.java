package bluesCritter;

import java.awt.Color;
import java.util.ArrayList;

import buildABug.RockBug;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class BluesCritter extends Critter
{
	private Location rock;
	
	/**
	 * Sets rock to null
	 * returns a random color.blue rock in the first index of the arraylist, and all its neighbors in the succesive ones
	 * if no color.blue rock is within a 10x10 square of this actor, returns an empty arraylist
	 */
	public ArrayList<Actor> getActors()
	{
		rock = null;
		ArrayList<Actor> rocks = getRocks(10);
		ArrayList<Actor> actors = new ArrayList<Actor>();
		
		
		if(rocks.size() == 0)
		{
			return actors;
		}
		int rand = (int)(Math.random() * rocks.size());
		
		actors.add(rocks.get(rand));
		
		ArrayList<Location> neighbors = (getGrid().getOccupiedAdjacentLocations(actors.get(rand).getLocation()));
		
		for(Location loc : neighbors)
		{
			actors.add(getGrid().get(loc));
		}
		
		return actors;
	}
	
	/**
	 * Eats all the actors of next to the selected color.blue rock
	 * Sets the location of rock equal to the location of the rock that was found
	 * if actors.size() == 0, does nothing
	 */
	public void processActors(ArrayList<Actor> actors)
	{
		
		if(actors.size() == 0)
		{
			return;
		}
		
		rock = actors.get(0).getLocation();
		actors.remove(0);
		
		for(Actor a : actors)
		{
			if(a != this)
			a.removeSelfFromGrid();
		}
		
	}
	
	/**
	 * If this critter found a blue rock, returns the location of the blue rock
	 * else returns this critters location
	 * @return
	 */
	public ArrayList<Location> getMoveLocations()
	{
		ArrayList<Location> loc = new ArrayList<Location>();
		if(rock != null)
		{
			loc.add(rock);
			return loc;
		}
		else
		{
			loc.add(this.getLocation());
			return loc;
		}
	}
	

	/*
	 * Precondition: Distance is even. 
	 * returns all the blue rocks within a distance by distance square.
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
				if(getGrid().isValid(loc) && (getGrid().get(loc) instanceof Rock && (getGrid().get(loc).getColor().equals(Color.BLUE))))
				{
					actors.add(getGrid().get(loc));
				}
			}
		}
		
		return actors;
	}
}
