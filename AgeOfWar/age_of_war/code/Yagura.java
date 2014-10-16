package code;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Shoots enemy units within range.
 * @author Anish Visaria, Eitan Zlatin
 * 
 */
public class Yagura {
	
	private int attack, location, range, type, player;
	private PImage restState, attackState;
	private boolean state = false;
	private PApplet parent;
	public final int XPOS, YPOS = 245;

	/**
	 * Makes yagura with characteristics given.
	 * @param restState rest image
	 * @param attackState attack image
	 * @param attack damage of attack
	 * @param location location on y-axis
	 * @param range range of yagura
	 * @param p papplet object
	 * @param type type of yagura
	 * @param player number of player
	 */
	public Yagura(PImage restState, PImage attackState, int attack, int location, int range, PApplet p, int type, int player) {
		this.restState = restState;
		this.attack = attack;
		this.location = location;
		this.range = range;
		this.attackState = attackState;
		parent = p;
		this.type = type;
		XPOS = location-20;
		this.player = player;
		
		parent.image(restState, XPOS, YPOS);
	}

	/**
	 * Gets attack ability.
	 * 
	 * @return attack ability
	 */
	public int getAttack() {
		return attack;
	}
	
	/**
	 * Returns type of yagura.
	 * @return type
	 */
	public int getType()
	{
		return type;
	}
	
	/**
	 * Returns player num of owner.
	 * @return player num
	 */
	public int getPlayer()
	{
		return player;
	}

	/**
	 * Returns range of firing
	 * 
	 * @return range of firing
	 */
	public int getRange() {
		return range;

	}
	
	/**
	 * Sets yagura to attack mode or dormant mode
	 * @param state attack state of yagura
	 */
	public void setAttackState(boolean state) {
		this.state = state;
	}
	
	/**
	 * Displays yagura on field.
	 */
	public void display()
	{
		parent.image(restState, XPOS, YPOS);
	}

}
