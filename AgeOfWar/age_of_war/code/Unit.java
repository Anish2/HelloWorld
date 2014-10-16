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

	
	private int maxHealth;
	public final int YPOS = 375;


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
		this.maxHealth = maxHealth;
		pos = startingLocation;
		attack_power = attackAbility;
		this.range = range;
		this.fightState = fightState;
		this.walkState = walkState;

		this.displayImage(walkState);
	}

	public int getPlayer()
	{
		return player;
	}

	private void displayImage(PImage image)
	{
		
//		double percentHealth = health/ maxHealth;
//		
//		parent.fill(0,255,0);
//		parent.rect(pos, YPOS + 50, 20, 10);
//		
//		parent.fill(255,0,0);
//		parent.rect(pos, YPOS + 50, (float) (20 * percentHealth), 10);
		parent.image(image, pos, YPOS);
	}

	/**
	 * Moves the unit to location position.
	 * @param position desired location on battlefield
	 */
	public void move(int position)
	{
		pos = position;
		this.displayImage(walkState);
	}

	public void fight() {
		if (isFighting)
			this.displayImage(fightState);
		else
			this.displayImage(walkState);
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
