package code;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Fights opponent units and has ability to damage home base.
 * @author Anish Visaria, Eitan Zlatin
 *
 */
public class Unit implements Comparable<Unit> {

	private int health, pos, attack_power, range, player, type, maxhealth;
	private PImage fightState;
	private PImage walkState;
	private boolean isFighting= false;
	private PApplet parent;


	public final int YPOS = 375;


	/**
	 * Makes unit with characteristics given.
	 * @param parent papplet object
	 * @param maxHealth starting health
	 * @param startingLocation start location on x-axis
	 * @param attackAbility damage of attack
	 * @param range range of attack
	 * @param fightState fight state picture
	 * @param walkState walk state picture
	 * @param player player num or unit owner
	 * @param type type of unit
	 */
	public Unit(PApplet parent, int maxHealth, int startingLocation, int attackAbility, int range, PImage fightState,
			PImage walkState, int player, int type)
	{
		this.player = player;
		this.parent = parent;
		health = maxHealth;
		this.maxhealth = maxHealth;
		pos = startingLocation;
		attack_power = attackAbility;
		this.range = range;
		this.fightState = fightState;
		this.walkState = walkState;
		this.type = type;

		this.displayImage(walkState);
	}

	/**
	 * Returns player num.
	 * @return player num
	 */
	public int getPlayer()
	{
		return player;
	}

	/**
	 * Returns type of unit
	 * @return unit type
	 */
	public int getType()
	{
		return type;
	}

	private void displayImage(PImage image)
	{

		double percentHealth = health/ maxhealth;
		int width = 60, height = 35;

		parent.fill(255,0,0);
		parent.rect(pos, YPOS - 10, height, width);

		parent.fill(0,255,0);
		parent.rect(pos, YPOS - 10, height, (float) (width * percentHealth));
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

	/**
	 * Places unit in fight state. (displays fightState image)
	 */
	public void fight() {
		this.displayImage(fightState);
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

	public void heal() {
		health = maxhealth;
	}

	/**
	 * Changes the state to fighting if isFighting, walking else
	 * @param isFighting true if the unit is fighting, false otherwise
	 */
	public void setState(boolean state) {
		isFighting = state;
	}



	/**
	 * Compares units by position
	 */
	public int compareTo(Unit u) 
	{
		return this.getPos() - u.getPos();
	}




}
