
package tsunox_code;

import java.util.ArrayList;
import java.util.HashSet;

import processing.core.PApplet;
import processing.core.PFont;
import fisica.FBody;
import fisica.FBox;
import fisica.FCircle;
import fisica.FContact;
import fisica.FWorld;
import fisica.Fisica;

/**
 * Tsunox - named after tsunami and boxes
 * 
 * Goal of game is to destroy all the colored blocks in the shortest amount of time.
 * 
 * Uses physics engine - fisica by Richard Marxer
 * 
 * @author Anish Visaria and Eitan Zlatin
 */
@SuppressWarnings("serial")
public class TsunoxDisplay extends PApplet {

	private FWorld world;
	private FBox paddle;
	private int boxHeightGoal = 4;
	private int boxWidthGoal = 4;
	private ArrayList<BoxWrapper[][]> goals = new ArrayList<BoxWrapper[][]>();
	private ArrayList<FBody> balls = new ArrayList<FBody>();
	private ArrayList<DisBall> disBalls = new ArrayList<DisBall>();
	private int numGoals = 3;
	private int framesPerBall = 250;
	private double startTime;
	private int speed = 5;
	private float yoff = (float) 0.0; 
	private float water_level;
	private boolean red, green, blue, left, right, up, down;
	private PFont winner_font, time_font;

	public void setup() {
		startTime = System.currentTimeMillis()/1000.0;
		frameRate(75);
		size(640, 360);
		smooth();
		water_level = height-10;
		Fisica.init(this);

		world = new FWorld();

		paddle = new FBox(135,5);
		paddle.setRotation(0);
		paddle.setPosition(width/2, 5f * height/8);
		paddle.setStatic(true);
		paddle.setFill(255);
		paddle.setRestitution(1.3f); // How much stuff bounces
		paddle.setGrabbable(false);
		world.add(paddle);

		winner_font = createFont("COCOGOOSELETTERPRESS TRIAL.ttf", 75);
		time_font = createFont("YanoneKaffeesatz-Regular.otf", 20);

		// compute random position of blocks to destroy
		ArrayList<Integer> posNums = new ArrayList<Integer>();
		for(int x = 50; x <= width - 50; x++)
			posNums.add(x);

		for(int x = 0; x < numGoals; x++) {
			goals.add(new BoxWrapper[boxWidthGoal][boxHeightGoal]);
			int pos = posNums.get((int) random(0,posNums.size()));

			for(int y = pos - 50; y < pos + 50; y++)
				posNums.remove(new Integer(y));

			for(int a = 0; a < boxWidthGoal; a++) {
				for(int b = 0; b < boxHeightGoal; b++) {
					BoxWrapper box = new BoxWrapper(5f,5f,x+" "+a+" "+b);
					box.setRotation(0);
					box.setPosition(pos + a * 5f, height - 60 + b * 5f);
					box.setStatic(true);
					box.setRestitution(1.0f);
					box.setGrabbable(false);
					if(x == 0) {
						box.setStroke(0,0,255);
						box.setFill(0,0,255);
					}
					if(x == 1) {
						box.setStroke(0,255,0);
						box.setFill(0,255,0);
					}
					if(x==2) {
						box.setStroke(255,0,0);
						box.setFill(255,0,0);
					}
					goals.get(x)[a][b] = box;

					world.add(box);
				}
			}
		}

		world.draw();
		world.step();
	}

	public void draw() {

		background(0);
		fill(color(0, 200, 255));
		// water waves are made by polygons with large amounts of vertices
		beginShape(); 

		float xoff = 0;

		// Iterate over horizontal pixels
		for(float x = 0; x <= width; x += 10) {
			// Calculate a y value according to noise, map to 
			float y = map(noise(xoff, yoff), 0, 1, water_level-40,water_level); 
			// Set the vertex
			vertex(x, y-10); 

			xoff += 0.08;
		}

		yoff += 0.01;
		vertex(width, height);
		vertex(0, height);
		endShape(CLOSE);

		fill(255);


		if (hasWon()) {
			textAlign(CENTER);
			stroke(color(240,0,0));
			world.remove(paddle);
			textFont(winner_font);			
			text("You Win!",width/2,height/2);
		}
		else {

			textFont(time_font);
			text("Time : " + (int)(System.currentTimeMillis()/1000.0 - startTime),15,30);

			ArrayList<Integer> colors = new ArrayList<Integer>();
			if (!blue)
				colors.add(color(0,0,255));
			if (!red)
				colors.add(color(255,0,0));
			if (!green)
				colors.add(color(0,255,0));

			// spawns ball
			if (frameCount % framesPerBall == 0) {
				FCircle b = new FCircle(20);
				b.setPosition(random(width/2 - 70, width/2 + 70), 50);
				b.setVelocity(0, 100);
				b.setRestitution(0);
				b.setNoStroke();
				int c = colors.get((int)(Math.random() * colors.size()));
				b.setFill(red(c), green(c), blue(c));
				world.add(b);
				balls.add(b);
			}

			handlepaddleMovement();
			updateScore();
			handleTsunamiEffect();

			for(int x = 0; x < disBalls.size(); x++) {
				DisBall b = disBalls.get(x);
				if(b.checkGone())
					world.remove(b.circ);
			}
		}

		strokeWeight(1);
		stroke(255);

		world.draw();
		world.step();
	}


	private void handleTsunamiEffect() { // water fluid physics logic
		for (int i = 0; i < balls.size(); i++) {
			if (balls.get(i).getY() >= water_level-20) {
				if (Math.random() > 0.5) {
					balls.get(i).addForce(random(850, 1000), random(-1000, -575));
					balls.get(i).addImpulse(random(50.0f, 65.0f), random(-65.0f, -50.0f));
				}
				else {
					balls.get(i).addForce(random(-850,-1000), random(-1000,-575));
					balls.get(i).addImpulse(random(-65.0f,-50.0f), random(-65.0f,-50.0f));
				}
			}
		}
	}

	private void handlepaddleMovement() {
		if(left & !right && !(paddle.getX() < 0))
			paddle.setPosition(paddle.getX() - speed, paddle.getY());
		if(right && ! left && !(paddle.getX() > width))
			paddle.setPosition(paddle.getX() + speed, paddle.getY());
		if(up && !down)
			paddle.setRotation(paddle.getRotation() + PI/32);
		if(down && !up)
			paddle.setRotation(paddle.getRotation() - PI/32);
	}

	@SuppressWarnings("unchecked")
	private boolean hasWon() {
		HashSet<BoxWrapper> set = new HashSet<BoxWrapper>();
		for (FBody f: (ArrayList<FBody>)world.getBodies())
			if (f instanceof BoxWrapper)
				set.add((BoxWrapper) f);

		// blue checking
		blue = isColorGone(set, 0);

		// green checking
		green = isColorGone(set, 1);

		// red checking
		red = isColorGone(set, 2);

		return blue && green && red;
	}

	private boolean isColorGone(HashSet<BoxWrapper> set, int index) {
		for (int r = 0; r < goals.get(index).length; r++) {
			for (int c = 0; c < goals.get(index)[0].length; c++) {
				if (set.contains(goals.get(index)[r][c])) {
					return false;
				}
			}
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	private void updateScore() {
		for(int x = 0; x < numGoals; x++) {
			for(int z = 0; z < boxWidthGoal; z++) {
				for(int b = 0; b < boxHeightGoal; b++) {
					for(FContact a : (ArrayList<FContact>)goals.get(x)[z][b].getContacts()) {
						if(goals.get(x)[z][b].equals(a.getBody2()) && a.getBody1().getFillColor() == goals.get(x)[z][b].getFillColor()) {
							popBall(a.getBody1());

							BoxWrapper[] arr = getAdjacentBoxes(goals.get(x),z,b);

							for(int q = 0; q < arr.length; q++)	{
								BoxWrapper p = arr[q];
								if(p !=null)
									world.remove(p);
								p = null;								
							}
							world.remove(goals.get(x)[z][b]);

							world.remove(a.getBody2());
						}
						if(goals.get(x)[z][b].equals(a.getBody1()) && a.getBody2().getFillColor() == goals.get(x)[z][b].getFillColor())	{
							popBall(a.getBody2());

							BoxWrapper[] arr = getAdjacentBoxes(goals.get(x),z,b);

							for(int q = 0; q < arr.length; q++)	{
								BoxWrapper p = arr[q];
								if(p !=null)
									world.remove(p);
								p = null;
							} 
							world.remove(goals.get(x)[z][b]);
						}
					}
				}
			}
		}
	}

	private BoxWrapper[] getAdjacentBoxes(BoxWrapper[][] a, int row , int col) {
		ArrayList<BoxWrapper> arr = new ArrayList<BoxWrapper>();
		if (row+1 < a.length) 
			arr.add(a[row+1][col]);
		if (row-1 >= 0)
			arr.add(a[row-1][col]);
		if (col-1 >= 0) 
			arr.add(a[row][col-1]);
		if (col+1 < a[0].length)
			arr.add(a[row][col+1]);

		BoxWrapper[] stockArr = new BoxWrapper[arr.size()];
		stockArr = arr.toArray(stockArr);
		return stockArr;
	}

	private void popBall(FBody ball)	{
		int particles = (int)random(10,30);
		for(int x = 0; x < particles; x++) {
			DisBall particle = new DisBall(new FCircle(2),3000);
			particle.circ.setPosition(ball.getX() + random(-10,10), ball.getY() + random(0,10));
			particle.circ.setGrabbable(false);			
			particle.circ.setVelocity((float) (Math.pow(-1,(int)random(-1,2)) *random(100,300)), random(100,300));
			particle.circ.setFillColor(ball.getFillColor() - 1);
			particle.circ.setStroke(red(ball.getFillColor()), green(ball.getFillColor()), blue(ball.getFillColor())); 
			disBalls.add(particle);
			world.add(particle.circ);
		}
		world.remove(ball);

		balls.remove(ball);
	}

	public void keyReleased()
	{
		if (key == CODED)
		{
			if (keyCode == LEFT)
			{
				left = false;
			}
			if (keyCode == RIGHT)
			{
				right = false;
			}
			if(keyCode == UP)
			{
				up = false;
			}
			if(keyCode == DOWN)
			{
				down = false;
			}
		}
	}

	public void keyPressed()
	{
		if (key == CODED)
		{
			if (keyCode == LEFT)
			{
				left = true;
			}
			if (keyCode == RIGHT)
			{
				right = true;
			}
			if(keyCode == UP)
			{
				up = true;
			}
			if(keyCode == DOWN)
			{
				down = true;
			}
		}
	}
}