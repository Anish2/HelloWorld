package chatProgram;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileSearch implements Runnable
{
	private String filePath;
	private String keyword;
	public final static int NUM_FILES = 3;
	
	public FileSearch(String path, String key)
	{
		filePath = path;
		keyword = key;
	}
	
	public void run() 
	{			
		File file = new File(filePath);

		try {

			Scanner scan = new Scanner(file);
			try
			{	
				while(scan.hasNextLine() && !Thread.interrupted())
				{
					String line = scan.nextLine();

					if(line.contains(keyword))
					{
						System.out.println("File Name " + filePath);
						System.out.println(line);
					}
				}
			}
			finally
			{
				scan.close();
			}
		} 
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

}
