package browBalance;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import fisica.FBody;
import fisica.FBox;
import fisica.FCircle;
import fisica.FContact;
import fisica.FWorld;
import fisica.Fisica;

public class BrowDisplay extends PApplet
{
	private FWorld world;
	private FBox brow;
	private ArrayList<FBox> goals = new ArrayList<FBox>();
	private ArrayList<FBody> balls = new ArrayList<FBody>();
	private int numGoals = 3;
	private int lives = 100;
	private int score = 0;
	private int framesPerBall = 150;
	private int level = 1;
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private int numBalls = 0;
	private int maxBalls = 1;
	private ArrayList<FBox> obstacles = new ArrayList<FBox>();
	private int speed = 5;
	private float yoff = (float) 0.0; 
	private float water_level;


	public void drawNormal()
	{
		background(240);

		fill(color(204, 102, 0));
		// We are going to draw a polygon out of the wave points
		beginShape(); 

		float xoff = 0;       // Option #1: 2D Noise
		// float xoff = yoff; // Option #2: 1D Noise

		// Iterate over horizontal pixels
		for (float x = 0; x <= width; x += 10) {
			// Calculate a y value according to noise, map to 
			float y = map(noise(xoff, yoff), 0, 1, water_level-40,water_level); // Option #1: 2D Noise
			// float y = map(noise(xoff), 0, 1, 200,300);    // Option #2: 1D Noise
			
			// Set the vertex
			vertex(x, y-10); 
			// Increment x dimension for noise
			xoff += 0.08;
		}
		// increment y dimension for noise
		yoff += 0.01;
		vertex(width, height);
		vertex(0, height);
		endShape(CLOSE);

		//background(255);
		fill(0);
		water_level -= 0.01;
		text("Lives : " + (lives), 15, 30);
		text("Score : " + score, width - 100, 30);
		text("Level : " + level, width - 200,30);


		if (frameCount % framesPerBall == 0 && numBalls < maxBalls) 
		{
			FCircle b = new FCircle(20);
			b.setPosition(random(width/2 - 70, width/2 + 70), 50);
			b.setVelocity(0, 200);
			b.setRestitution(0);
			b.setNoStroke();
			switch((int)random(1,4))
			{
			case 1:
				b.setFill(255,0,0);
				break;
			case 2:
				b.setFill(0,255,0);
				break;
			case 3:
				b.setFill(0,0,255);
				break;
			}

			numBalls++;
			world.add(b);
			balls.add(b);
		}

		handleBrowMovement();
		updateScore();
		handleTsunamiEffect();
		updateLives();
		world.draw();
		world.step();

		strokeWeight(1);
		stroke(255);
	}

	public void setup() 
	{
		size(640, 360);
		smooth();
		water_level = height-10;
		Fisica.init(this);

		world = new FWorld();

		brow = new FBox(150,5);
		brow.setRotation(0);
		brow.setPosition(width/2, 6f * height/8);
		brow.setStatic(true);
		brow.setFill(0);
		brow.setRestitution(1.3f); // How much stuff bounces
		brow.setGrabbable(false);
		world.add(brow);

		PFont font = createFont("Arial",14);
		textFont(font);
		text("Live : " + lives, 15, 30);
		ArrayList<Integer> posNums = new ArrayList<Integer>();
		for(int x = 50; x <= width - 50; x++)
		{
			posNums.add(x);
		}

		for(int x = 0; x < numGoals; x++)
		{
			FBox goal = new FBox(50,20);
			goal.setRotation(0);
			int pos = posNums.get((int) random(0,posNums.size()));
			for(int y = pos - 50; y < pos + 50; y++)
			{
				posNums.remove(new Integer(y));
			}
			/*goal.setPosition(pos, height - 30); */
			goal.setPosition(pos,height - 60); 
			goal.setStatic(true);
			goal.setRestitution(1.0f);
			goal.setGrabbable(false);
			goals.add(goal);
		}

		goals.get(0).setFill(0,0,255);;
		goals.get(1).setFill(255,0,0);;
		goals.get(2).setFill(0,255,0);;

		for(int x = 0; x < numGoals; x++)
		{
			world.add(goals.get(x));
		}
	}

	public void draw()
	{
		maxBalls = score/20 + 1;
		drawNormal();
	}
	
	public void handleTsunamiEffect() {
		for (int i = 0; i < balls.size(); i++) {
			if (balls.get(i).getY() >= water_level-20) {
				//System.out.println("in here");
				if (Math.random() > 0.5)
					balls.get(i).addForce(random(850,1000), random(-1000,-575));
				else
					balls.get(i).addForce(random(-850,-1000), random(-1000,-575));
			}
		}
	}

	public void handleBrowMovement()
	{
		if(left & !right && !(brow.getX() < 0))
		{
			brow.setPosition(brow.getX() - speed, brow.getY());
		}
		if(right && ! left && !(brow.getX() > width))
		{
			brow.setPosition(brow.getX() + speed, brow.getY());
		}
		if(up && !down)
		{
			brow.setRotation(brow.getRotation() + PI/32);
		}
		if(down && !up)
		{
			brow.setRotation(brow.getRotation() - PI/32);
		}
	}

	@SuppressWarnings("unchecked")
	public void updateScore()
	{
		for(int x = 0; x < numGoals; x++)
		{
			for(FContact a : (ArrayList<FContact>)goals.get(x).getContacts())
			{
				if(goals.get(x).equals(a.getBody2()) && a.getBody1().getFillColor() == goals.get(x).getFillColor())
				{
					popBall(a.getBody1());
					score++;
				}
				if(goals.get(x).equals(a.getBody1()) && a.getBody2().getFillColor() == goals.get(x).getFillColor())
				{
					popBall(a.getBody2());
					score++;
				}
			}
		}
	}

	public void popBall(FBody ball)
	{
		int particles = (int)random(10,30);
		for(int x = 0; x < particles; x++)
		{
			FCircle particle = new FCircle(2);
			particle.setPosition(ball.getX() + random(-10,10), ball.getY() + random(0,10));
			particle.setGrabbable(false);
			//particle.setAngularVelocity(ball.getAngularVelocity() * 2);
			particle.setVelocity((float) (Math.pow(-1,(int)random(-1,2)) *random(100,300)), random(100,300));
			particle.setFillColor(ball.getFillColor() - 1);
			particle.setStroke(red(ball.getFillColor()), green(ball.getFillColor()), blue(ball.getFillColor())); // Fix this, using wrong color need to extract RGB
			world.add(particle);
		}
		world.remove(ball);
		balls.remove(ball);
		numBalls--;
	}

	@SuppressWarnings("unchecked")
	public void updateLives()
	{
		if(lives == 0)
		{
			System.exit(0);
		}
		ArrayList<FBody> body = (ArrayList<FBody>)world.getBodies();
		for(int x = 0 ; x < body.size(); x++)
		{
			FBody a = body.get(x);
			if(a instanceof FCircle && ((FCircle) a).getSize() == 20) // Regular size
			{
				if((a.getX() < -20 || a.getX() > width + 20 || a.getY() < -20 || a.getY() > height + 20))
				{
					world.remove(a);
					balls.remove(a);
					numBalls--;
					lives--;
				}
			}
		}
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
