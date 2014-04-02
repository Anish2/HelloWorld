package jumper;

import java.awt.Color;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class Jumper extends Actor
{
	public Jumper(int r, int g, int b)
	{
			super();
		this.setColor(new Color(r,g,b));
	}
	
	public void act()
	{
		boolean flagTurned = false;
		
		if(!this.getGrid().isValid(getTwoSpaces()))
		{
			flagTurned = true;
			turn();
		}
		
		if(!flagTurned)
		{
			if(this.getGrid().get(getTwoSpaces()) !=null)
			{
				if(this.getGrid().get(getTwoSpaces()) instanceof Flower || this.getGrid().get(getTwoSpaces()) instanceof Rock)
				{
					this.turn();
					
					if(this.getGrid().get(getTwoSpaces()) ==null)
						this.jump();
					else
					{
						this.removeSelfFromGrid();
					}
				}
				else if(this.getGrid().get(getTwoSpaces()) instanceof Jumper)
				{
						this.jump();
				}
				else
				{
					this.turn();
				}
			}
			else
			{
				this.jump();
			}
		}
	}
	
	public Location getTwoSpaces()
	{
		return this.getLocation().getAdjacentLocation(this.getDirection()).getAdjacentLocation(this.getDirection());
	}
	
	public void turn()
	{
		this.setDirection(this.getDirection() + Location.HALF_RIGHT);
	}
	
	public void jump()
	{
		this.moveTo(getTwoSpaces());
	}
}