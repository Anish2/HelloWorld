package code;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Fights opponent units and has ability to damage home base.
 * @author Anish Visaria, Eitan Zlatin
 *
 */
public class Unit implements Comparable{

	private int health, pos, attack_power, range, player;
	private PImage fightState;
	private PImage walkState;
	private boolean isFighting= false;
	private PApplet parent;

	public final int ypos = 375;


	/**
	 * Deploys unit with following characteristics and displays it on battlefield.
	 * @param maxHealth starting health of unit
	 * @param startingLocation initial position of unit on x-axis
	 * @param attackAbility damage this unit can inflict each attack
	 * @param range distance unit attacks from
	 * @param fightState image of unit attacking
	 * @param walkState image of unit walking
	 */
	public Unit(PApplet parent, int maxHealth, int startingLocation, int attackAbility, int range, PImage fightState,
			PImage walkState, int player)
	{
		this.player = player;
		this.parent = parent;
		health = maxHealth;
		pos = startingLocation;
		attack_power = attackAbility;
		this.range = range;
		this.fightState = fightState;
		this.walkState = walkState;

		parent.image(walkState, pos, ypos);
	}

	public int getPlayer()
	{
		return player;
	}



	/**
	 * Moves the unit to location position.
	 * @param position desired location on battlefield
	 */
	public void move(int position)
	{
		pos = position;
		parent.image(walkState, pos, ypos);
	}

	public void fight() {
		if (isFighting)
			parent.image(fightState, pos, ypos);
		else
			parent.image(walkState, pos, ypos);
	}

	/**
	 * Returns position of unit.
	 * @return position of unit
	 */
	public int getPos()
	{
		return pos;

	}

	/**
	 * Returns attack ability of unit.
	 * @return attack ability of unit
	 */
	public int getAttack()
	{
		return attack_power;

	}

	/**
	 * Returns range of unit.
	 * @return range of unit
	 */
	public int getRange()
	{
		return range;

	}

	/**
	 * Damages this unit.
	 * @param injury harm done to this unit
	 */
	public void damage(int injury) {
		health = health - injury;
	}

	/**
	 * Returns current health of unit.
	 * @return current health of unit
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Changes the state to fighting if isFighting, walking else
	 * @param isFighting true if the unit is fighting, false otherwise
	 */
	public void setState(boolean state) {
		isFighting = state;
	}




	public int compareTo(Object arg0) //Hope it works
	{
		Unit other = (Unit)arg0;
	
		return this.getPos() - other.getPos();
	}




}
