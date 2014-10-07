package code;

import java.io.IOException;

import processing.core.PApplet;

public class AgeOfWarTester {

	public static void main(String[] args) throws Exception 
	{
		PApplet display = new PApplet();
//		testFileReading();
		Unit unit = AgeUtility.makeUnit(AgeUtility.SHINOBI, 1);

	}
	
	public static void testFileReading() throws IOException
	{
		System.out.println(AgeUtility.readfile("game_data\\UnitInfo"));
	}

}
