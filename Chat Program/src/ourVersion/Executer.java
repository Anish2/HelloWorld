package ourVersion;

import java.io.IOException;
import java.net.ServerSocket;

public class Executer
{
	private static int port =  8888;
	
	public static void main(String[] args) 
	{
		ClientManager manager = new ClientManager();
		Thread m = new Thread(manager);
		m.start();
	}

}
