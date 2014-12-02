package ourVersion;

import java.io.IOException;

public class ServerRunner
{
	private static final int PORT =  8888;

	public static void main(String[] args) throws IOException 
	{
		Server chat = new Server(PORT);
		Thread t = new Thread(chat);
		t.start();
	}

	// 10.5.100.185
}
