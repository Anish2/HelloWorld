package code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import processing.core.PApplet;


/**
 * Handles displaying the game, updating the player stats.
 * @author Anish Visaria and Eitan Zlatin
 *
 */
public class War {

	private Player a, b;
	private ArrayList<Unit> units = new ArrayList<Unit>();
	private ArrayList<Yagura> yaguras = new ArrayList<Yagura>();
	private PApplet parent;
	private long coolA, coolB;
	private int scoreA, scoreB;

	/**
	 * Constructs war with 2 players and display.
	 * @param d display object
	 * @param a player one
	 * @param b player two
	 */
	public War(PApplet d, Player a, Player b) 
	{
		parent = d;
		this.a = a;
		this.b = b;
	} 

	/**
	 * Activates all units and yaguras on battlefield to attack or change position.
	 * Deploys units and yaguras that a Player desires on the battlefield.
	 * Executes all actions player commands including upgrades.
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public void act() throws IOException, InterruptedException 
	{
		// handle specials
		handle_specials();


		// handle fighting
		unit_combat();

		// handle yagura combat
		yagura_combat();

		for (Yagura y: yaguras) {
			y.display();
		}

		for (Unit u: units) {
			if (u.getPlayer() == 1) {
				u.move(u.getPos()+4);
				if (u.getPos() >= AgeUtility.player2UnitStartingLocation)
					scoreA++;
				if (scoreA >= 5) {
					JOptionPane.showMessageDialog(null, "Player 1 wins!");
					System.exit(0);
				}
			}
			else {
				u.move(u.getPos()-4);
				if (u.getPos() <= AgeUtility.player1UnitStartingLocation)
					scoreB++;
				if (scoreB >= 5) {
					JOptionPane.showMessageDialog(null, "Player 2 wins!");
					System.exit(0);
				}
			}
		}

		for (int type: a.getMaterialsToBuild()) {
			if (type > AgeUtility.ALI_BABA)
				yaguras.add(AgeUtility.makeYagura(parent, type, 1));
			else
				units.add(AgeUtility.makeUnit(parent, type, 1));
		}

		for (int type: b.getMaterialsToBuild()) {
			if (type > AgeUtility.ALI_BABA)
				yaguras.add(AgeUtility.makeYagura(parent, type, 2));
			else
				units.add(AgeUtility.makeUnit(parent, type, 2));
		}
		
		


	}

	private void yagura_combat() throws IOException {
		for (int i = 0; i < yaguras.size(); i++) {
			for (int u = 0; u < units.size(); u++) {
				if (yaguras.get(i).getPlayer() != units.get(u).getPlayer() && 
						Math.abs(yaguras.get(i).XPOS-units.get(u).getPos()) <= yaguras.get(i).getRange()) {
					units.get(u).damage(yaguras.get(i).getAttack());
					if (units.get(u).getHealth() <= 0) {
						killUnit(u);
						u--;
					}
				}
			}
		}
	}

	private void unit_combat() throws IOException {
		Collections.sort(units);
		int unit_side = 0;
		if (!units.isEmpty())
			unit_side = units.get(0).getPlayer();

		for (int i = 1; i < units.size(); i++) {
			if (units.get(i).getPlayer() != unit_side) {
				int distance = Math.abs(units.get(i).getPos()-units.get(i-1).getPos());
				if (distance <= 80) {
					while (units.get(i).getHealth() > 0 && units.get(i-1).getHealth() > 0) {
						units.get(i).fight();
						units.get(i-1).fight();
						double rand = Math.random();
						if (rand > 0.5)
							units.get(i).damage(units.get(i-1).getAttack());
						else
							units.get(i-1).damage(units.get(i).getAttack());
					}

					if (units.get(i).getHealth() <= 0) {
						killUnit(i);
					}
					else {
						killUnit(i-1);
					}

					i--;

				}
			}
			unit_side = units.get(i).getPlayer();
		}
	}

	private void handle_specials() throws IOException {
		long sec = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
		int current_special = AgeUtility.getSpecial(a.getAge());
		if (a.getSpecial() && (coolA == 0 || sec-coolA >= AgeUtility.getCooldown(current_special))) {
			coolA = sec;
			if (current_special == AgeUtility.DAMAGE_SPECIAL)
				damage_special(b.playerNum());
			else
				heal_special(a.playerNum());
		}

		sec = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
		current_special = AgeUtility.getSpecial(b.getAge());

		if (b.getSpecial() && (coolB == 0 || sec-coolB >= AgeUtility.getCooldown(current_special))) {
			coolB = sec;
			if (current_special == AgeUtility.DAMAGE_SPECIAL)
				damage_special(a.playerNum());
			else
				heal_special(b.playerNum());
		}
	}

	private void damage_special(int player) throws IOException {
		for (int i = 0; i < units.size(); i++) {
			if (units.get(i).getPlayer() == player)
				units.get(i).damage(30);
			if (units.get(i).getHealth() <= 0) {
				killUnit(i);
				i--;
			}
		}
		displayLightning();
	}

	private void heal_special(int player) {
		for (int i = 0; i < units.size(); i++) {
			if (units.get(i).getPlayer() == player)
				units.get(i).heal();
		}
		displayLightning();
	}

	private void killUnit(int pos) throws IOException {
		Player victor = (a.playerNum() != units.get(pos).getPlayer()) ? a : b;
		victor.setGold(victor.getGold()+(int)(AgeUtility.getCost(units.get(pos).getType())/1.5));
		victor.gainXP(AgeUtility.getCost(units.get(pos).getType()));
		units.remove(pos);
	}

	private void displayLightning()
	{
		parent.fill(255,255,0);
		parent.stroke(255,255,0);
		parent.quad(500,100,530,100,510,150,480,150);
		parent.quad(480,150,550,150,540,160,470,160);
		parent.quad(510,160,540,160,510,210,480,210);

		JOptionPane.showMessageDialog(null, "Special used successfully.");
	}


}
