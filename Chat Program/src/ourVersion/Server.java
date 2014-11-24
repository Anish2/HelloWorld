package ourVersion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.IIOException;

public class Server implements Runnable
{
	private  int port;
	private boolean stop = false;
	
	public Server(int portNum)
	{
		port = portNum;
	}
	
	public void run() 
	{	
		try
		{
			ServerSocket sock = new ServerSocket(port);
			
			ClientManager manager = new ClientManager();
			try
			{
				while(!stop)
				{
					Socket soc = sock.accept();
					Client client = new Client(soc,manager);
				//	manager.addClient(client);
					Thread t = new Thread(client);
					t.start();
				}
			}
			finally
			{
				sock.close();
				//Close all the connections
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		
	}
	
}
