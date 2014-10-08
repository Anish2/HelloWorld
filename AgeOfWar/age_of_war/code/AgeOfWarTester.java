package code;

import java.io.IOException;

import processing.core.PApplet;

public class AgeOfWarTester {

	public static void main(String[] args) throws Exception 
	{
		Display d = new Display();
//		testFileReading();
		

	}
	
	public static void testFileReading() throws IOException
	{
		System.out.println(AgeUtility.readfile("game_data\\UnitInfo"));
	}

}
