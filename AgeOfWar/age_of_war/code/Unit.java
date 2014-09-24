package code;

import processing.core.PImage;

/**
 * Fights opponent units and has ability to damage home base.
 * @author Anish Visaria, Eitan Zlatin
 *
 */
public class Unit {
	
	/**
	 * Deploys unit with following characteristics and displays it on battlefield.
	 * @param maxHealth starting health of unit
	 * @param startingLocation initial position of unit on x-axis
	 * @param attackAbility damage this unit can inflict each attack
	 * @param range distance unit attacks from
	 * @param fightState image of unit attacking
	 * @param walkState image of unit walking
	 */
	public Unit(int maxHealth, int startingLocation, int attackAbility, int range, PImage fightState,
			PImage walkState)
	{
		
	}
	
	/**
	 * Moves the unit to location position.
	 * @param position desired location on battlefield
	 */
	public void move(int position)
	{
		
	}
	
	/**
	 * Returns position of unit.
	 * @return position of unit
	 */
	public int getPos()
	{
		return 0;
		
	}
	
	/**
	 * Returns attack ability of unit.
	 * @return attack ability of unit
	 */
	public int getAttack()
	{
		return 0;
		
	}
	
	/**
	 * Returns range of unit.
	 * @return range of unit
	 */
	public int getRange()
	{
		return 0;
		
	}
	
	/**
	 * Damages this unit.
	 * @param injury harm done to this unit
	 */
	public void damage(int injury) {
		
	}
	
	/**
	 * Returns current health of unit.
	 * @return current health of unit
	 */
	public int getHealth() {
		return 0;
	}
	
	/**
	 * Changes the state to fighting if isFighting, walking else
	 * @param isFighting true if the unit is fighting, false otherwise
	 */
	public void setState(boolean isFighting) {
		
	}
	

}
