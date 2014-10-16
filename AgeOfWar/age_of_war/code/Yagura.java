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
	 * Deploys yagura(Japanese word for tower) with following characteristics
	 * and displays it on battlefield.
	 * 
	 * @param picture
	 *            yagura's picture
	 * @param attack
	 *            attack ability
	 * @param location
	 *            yagura's placement of y-axis
	 * @param range
	 *            range of firing and damage inflictment
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
	
	
	public int getType()
	{
		return type;
	}
	
	public int getPlayer()
	{
		return player;
	}

	/**
	 * Gets placement on y-axis of battlefield
	 * 
	 * @return placement on y-axis
	 */
	public int getPlacement() {
		return location;
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
	
	public void display()
	{
		parent.image(restState, XPOS, YPOS);
	}

}
