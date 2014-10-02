package code;

import java.io.IOException;

public class AgeOfWarTester {

	public static void main(String[] args) throws IOException 
	{
		testFileReading();

	}
	
	public static void testFileReading() throws IOException
	{
		System.out.println(AgeUtility.readfile("game_data\\UnitInfo"));
	}

}
