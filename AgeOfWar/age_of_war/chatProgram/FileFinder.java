package chatProgram;

public class FileFinder {

	public static void main(String[] args) 
	{
		String wordToSearch = args[0]; 
		
		for(int x = 1 ; x < args.length; x++)
		{
			new Thread(new FileSearch(args[x],wordToSearch)).start();
		}
		
		
//		String filePath1 = args[1];
//		String filePath2 = args[2];
//		String filePath3 = args[3];
//		
//		Thread file1 = new Thread(new FileSearch(filePath1, wordToSearch));
//		Thread file2 = new Thread(new FileSearch(filePath2, wordToSearch));
//		Thread file3 = new Thread(new FileSearch(filePath3, wordToSearch));
//		
//		file1.start();
//		file2.start();
//		file3.start();
	}

}
