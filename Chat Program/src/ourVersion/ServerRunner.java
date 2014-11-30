package ourVersion;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerRunner
{
	private static int port =  8888;

	public static void main(String[] args) throws IOException 
	{
		Server chat = new Server(8888);
		Thread t = new Thread(chat);
		t.start();
	}

}
