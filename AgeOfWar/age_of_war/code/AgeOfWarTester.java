package code;

import java.io.IOException;

import processing.core.PApplet;

public class AgeOfWarTester {

	public static void main(String[] args) throws Exception 
	{
		Display d = new Display();
		War w = new War(d, new HumanPlayer(d,1), new AIPlayer(d,2));

		while (true) {
			w.act();
		}

	}

	public static void testFileReading() throws IOException
	{
		System.out.println(AgeUtility.readfileIntoList("game_data\\UnitInfo"));
	}

}
