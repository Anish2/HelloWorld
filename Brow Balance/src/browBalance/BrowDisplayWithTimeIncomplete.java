package browBalance;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import fisica.FBox;
import fisica.FCircle;
import fisica.FContact;
import fisica.FWorld;
import fisica.Fisica;

public class BrowDisplayWithTimeIncomplete extends PApplet
{
	private FWorld world;
	private FBox brow;
	private ArrayList<FBox> goals = new ArrayList<FBox>();
	private int numGoals = 3;
	private int gameTime = 60;
	private int score = 0;
	private int framesPerBall = 150;
	private long startTime;
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private int speed = 5;


	public void setup() 
	{
		size(800, 500);
		smooth();

		Fisica.init(this);

		world = new FWorld();

		brow = new FBox(150,10);
		brow.setRotation(0);
		brow.setPosition(width/2, 6f * height/8);
		brow.setStatic(true);
		brow.setFill(0);
		brow.setRestitution(1.3f); // How much stuff bounces
		brow.setGrabbable(false);
		world.add(brow);

		PFont font = createFont("Arial",14);
		textFont(font);
		text("Time : " + gameTime, 15, 30);
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
				// System.out.println(pos);
				//System.out.println(posNums);
			}
			goal.setPosition(pos, height - 30); 
			goal.setStatic(true);
			goal.setRestitution(0);
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


		startTime = System.currentTimeMillis();
	}

	public void draw(){
		background(255);
		fill(0);
		int timeElapsed = (int) ((System.currentTimeMillis()- startTime)/1000.0);
		text("Time : " + (gameTime - timeElapsed), 15, 30);
		text("Score : " + score, width - 100, 30);
		text("Level : " + score/5, width - 200,30);
		
		
		if (frameCount % framesPerBall == 0) 
		{
			FCircle b = new FCircle(20);
			b.setPosition( random(width/2 - 70, width/2 +70), 50);
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


			world.add(b);
		}

		handleBrowMovement();
		updateScore();

		world.draw();
		world.step();

		strokeWeight(1);
		stroke(255);
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
					world.remove(a.getBody1());
					score++;
				}
				if(goals.get(x).equals(a.getBody1()) && a.getBody2().getFillColor() == goals.get(x).getFillColor())
				{
					world.remove(a.getBody2());
					score++;
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
