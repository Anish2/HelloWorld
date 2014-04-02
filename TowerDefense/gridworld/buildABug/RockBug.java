package buildABug;

import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

//This bug belongs to keshav
public class RockBug extends Bug{

	/**
	 * A default constructor that calls on the super class's default constructor, and sets the color
	 * to red
	 */
	public RockBug(){
		super();
	}
	
	/**
	 * A constructor that sets the color of this bug, which is passed in
	 * @param c the color of the bug 
	 */
	public RockBug(Color c){
		super(c);
	}

	

	/**
	 * An overridden move method implemented to poop out rocks every turn that are the same color as this
	 * bug. The bug also changes color, and poops out 3 rocks toward the AntiRockBug if it is within
	 * a 4 square radius of this bug. Turns if it cannot move forward. 
	 */
	public void move(){
		
			Grid<Actor> gr = getGrid();
			if (gr == null)
			return;
			
			Location loc = getLocation();
			Location next = loc.getAdjacentLocation(getDirection());
			
			if (canMove())
			moveTo(next);
			else
			turn();
			
			boolean near = false;
			Location locOfAnti = null;
			for (int x = getLocation().getRow()-2;x<getLocation().getRow()+2;x++){
				for (int y = getLocation().getCol()-2;y<getLocation().getCol()+2;y++){
					if (gr.isValid(new Location(x,y))&& gr.get(new Location(x,y)) instanceof AntiRockBug){
						near = true;
						locOfAnti = new Location(x,y);
					}
				}
			}
			if (near && locOfAnti != null){
				int[] dirs = {getLocation().getDirectionToward(locOfAnti),getLocation().getDirectionToward(locOfAnti)-45,getLocation().getDirectionToward(locOfAnti)+45};
				ArrayList<Location> a = getLocationsInDirections(dirs);	
				for (Location l:a){
					Rock r = new Rock(getColor());
					r.putSelfInGrid(gr, l);
				}
			}
			else{
				Rock r = new Rock(getColor());
				r.putSelfInGrid(gr, loc);
			}
			setColor(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));			
	}
	
	/**
	 * A method to return the locations in the directions that are included as elements in dirs
	 * @param dirs the directions in which locations are needed
	 * @return a list of locations in relation to this bug based on the directions passed in dirs
	 */
	public ArrayList<Location> getLocationsInDirections(int[] dirs){
		ArrayList<Location> locs = new ArrayList<Location>();
		
		for (int x = 0;x<dirs.length;x++){
		if (getGrid().isValid(getLocation().getAdjacentLocation(dirs[x]))&& getGrid().get(getLocation().getAdjacentLocation(dirs[x])) == null)
			locs.add(getLocation().getAdjacentLocation(dirs[x]));
		}
		
		return locs;
	}
	
}