
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

public class EitanBoxAttempt extends PApplet
{
	private FWorld world;
	private FBox brow;
	private int boxHeightGoal = 4;
	private int boxWidthGoal = 4;
	private ArrayList<FBox[][]> goals = new ArrayList<FBox[][]>();
	private ArrayList<FBody> balls = new ArrayList<FBody>();
	private ArrayList<DisBall> disBalls = new ArrayList<DisBall>();
	private int numGoals = 3;
	private int lives = 100;
	private int score = 0;
	private int framesPerBall = 250;
	private int counter = 0;
	private int box_counter = boxHeightGoal*boxWidthGoal;
	private ArrayList<Integer> goalCounters = new ArrayList<Integer>();
	private int level = 1;
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private double startTime;
	//private int numBalls = 0;
	//private int maxBalls = 1;
	private ArrayList<FBox> obstacles = new ArrayList<FBox>();
	private int speed = 5;
	private float yoff = (float) 0.0; 
	private float water_level;


	public void drawNormal()
	{
		background(0);

		fill(color(0, 200, 255));
		//fill(0);
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

		fill(255);
		//water_level -= 0.01;
		//text("Lives : " + (lives), 15, 30);
		

		if (hasWon()) {
			textAlign(CENTER);
			stroke(color(240,0,0));
			textFont(createFont("H:\\git\\HelloWorld\\Brow Balance\\src\\samples\\data\\COCOGOOSELETTERPRESS TRIAL.ttf", 75));
			//textFont(createFont("C:\\Users\\Anish\\git\\HelloWorld\\Brow Balance\\src\\samples\\data\\COCOGOOSELETTERPRESS TRIAL.ttf", 75));			
			text("You Won!",width/2,height/2);
		}
		else {
			
			text("Time : " + (int)(System.currentTimeMillis()/1000.0 - startTime),15,30);
			text("Score : " + score, width - 100, 30);
			text("Level : " + level, width - 200,30);
			
			
			if (frameCount % framesPerBall == 0) //&& numBalls < maxBalls) 
			{
				FCircle b = new FCircle(20);
				b.setPosition(random(width/2 - 70, width/2 + 70), 50);
				b.setVelocity(0, 100);
				b.setRestitution(0);
				b.setNoStroke();
				
					switch((int)(Math.random() * numGoals) + 1)
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
				
					

				//numBalls++;
				world.add(b);
				balls.add(b);
			}

			handleBrowMovement();
			updateScore();
			handleTsunamiEffect();
			
			for(int x = 0; x < disBalls.size(); x++)
			{
				DisBall b = disBalls.get(x);
				if(b.checkGone())
				{
					world.remove(b.circ);
				}
			}

			//updateLives();
			world.draw();
			world.step();

			strokeWeight(1);
			stroke(255);
		}
	}

	public void setup() 
	{

		startTime = System.currentTimeMillis()/1000.0;
		frameRate(75);
		size(640, 360);
		smooth();
		water_level = height-10;
		Fisica.init(this);

		world = new FWorld();
		//world.setGravity(5f,5f);

		brow = new FBox(135,5);
		brow.setRotation(0);
		brow.setPosition(width/2, 5f * height/8);
		brow.setStatic(true);
		brow.setFill(255);
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
			goals.add(new FBox[boxWidthGoal][boxHeightGoal]);
			//FBox goal = new FBox(50,20);
			//goal.setRotation(0);
			int pos = posNums.get((int) random(0,posNums.size()));
			for(int y = pos - 50; y < pos + 50; y++)
			{
				posNums.remove(new Integer(y));
			}
			for(int a = 0; a < boxWidthGoal; a++)
			{
				for(int b = 0; b < boxHeightGoal; b++)
				{
					FBox box = new FBox(5f,5f);
					box.setRotation(0);
					box.setPosition(pos + a * 5f, height - 60 + b * 5f);
					box.setStatic(true);
					box.setRestitution(1.0f);
					box.setGrabbable(false);
					if(x == 0)
					{
						box.setStroke(0,0,255);
						box.setFill(0,0,255);
					}
					if(x == 1)
					{
						box.setStroke(0,255,0);
						box.setFill(0,255,0);
					}
					if(x==2)
					{
						box.setStroke(255,0,0);
						box.setFill(255,0,0);
					}
					goals.get(x)[a][b] = box;
					world.add(box);
					box_counter++;
				}
			}
			/*goal.setPosition(pos, height - 30); */
			/*goal.setPosition(pos,height - 60); 
			goal.setStatic(true);
			goal.setRestitution(1.0f);
			goal.setGrabbable(false);
			goals.add(goal);*/
		}

		/*goals.get(0).setFill(0,0,255);;
		goals.get(1).setFill(255,0,0);;
		goals.get(2).setFill(0,255,0);;
		 *//*
		for(int x = 0; x < numGoals; x++)
		{
			world.add(goals.get(x));
		}*/
	}

	public void draw()
	{
		//maxBalls = score/20 + 1;
		drawNormal();
	}

	public void handleTsunamiEffect() {
		/*for (int i = 0; i < balls.size(); i++) {
			if (balls.get(i).getY() >= water_level-20) {
				//System.out.println("in here");
				if (Math.random() > 0.5)
					balls.get(i).addForce(random(850,1000), random(-1000,-575));
				else
					balls.get(i).addForce(random(-850,-1000), random(-1000,-575));
			}
		}*/
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

	public boolean hasWon() {
		return (counter >= box_counter);		
	}

	@SuppressWarnings("unchecked")
	public void updateScore()
	{
		for(int x = 0; x < numGoals; x++)
		{
			for(int z = 0; z < boxWidthGoal; z++)
			{
				for(int b = 0; b < boxHeightGoal; b++)
				{
					for(FContact a : (ArrayList<FContact>)goals.get(x)[z][b].getContacts())
					{
						//System.out.println("Contact detected");
						if(goals.get(x)[z][b].equals(a.getBody2()) && a.getBody1().getFillColor() == goals.get(x)[z][b].getFillColor())
						{
							//System.out.println("Popping ball");
							popBall(a.getBody1());
							
							FBox[] arr = getAdjacentBoxes(goals.get(x),z,b);

							for(int q = 0; q < arr.length; q++)
							{
								FBox p = arr[q];
								if(p !=null)
									world.remove(p);
								p = null;
								counter++;
							}
							world.remove(goals.get(x)[z][b]);
							//goals.get(x)[z][b] = null;
							//counter++;

							world.remove(a.getBody2());
							//goals.get(x)[goals.get(x).length - 1][collumn].setStatic(false);
							score++;
						}
						if(goals.get(x)[z][b].equals(a.getBody1()) && a.getBody2().getFillColor() == goals.get(x)[z][b].getFillColor())
						{
							//System.out.println("Popping ball");
							popBall(a.getBody2());

							FBox[] arr = getAdjacentBoxes(goals.get(x),z,b);

							for(int q = 0; q < arr.length; q++)
							{
								FBox p = arr[q];
								if(p !=null)
									world.remove(p);
								p = null;
								counter++;
							} 
							world.remove(goals.get(x)[z][b]);
							//goals.get(x)[z][b] = null;
							//counter++;

							//int collumn = (int) (Math.random() * boxHeightGoal);
							//goals.get(x)[goals.get(x).length - 1][collumn].setStatic(false);
							score++;
						}
					}
				}
			}
		}
	}

	public FBox[] getAdjacentBoxes(FBox[][] a, int row , int col)
	{
		ArrayList<FBox> arr = new ArrayList<FBox>();
		if (row+1 < a.length) {
			arr.add(a[row+1][col]);
		}
		if (row-1 >= 0) {
			arr.add(a[row-1][col]);
		}
		if (col-1 >= 0) {
			arr.add(a[row][col-1]);
		}
		if (col+1 < a[0].length) {
			arr.add(a[row][col+1]);
		}

		FBox[] stockArr = new FBox[arr.size()];
		stockArr = arr.toArray(stockArr);
		return stockArr;
	}

	public void popBall(FBody ball)
	{
		int particles = (int)random(10,30);
		for(int x = 0; x < particles; x++)
		{
			DisBall particle = new DisBall(new FCircle(2),3000);
			particle.circ.setPosition(ball.getX() + random(-10,10), ball.getY() + random(0,10));
			particle.circ.setGrabbable(false);
			//particle.circ.setAngularVelocity(ball.getAngularVelocity() * 2);
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