package ourVersion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.imageio.IIOException;

public class Server implements Runnable
{

	private boolean stop = false;
	private ClientManager manager;
	private ServerSocket sock;

	public Server(ClientManager m, int portNum) throws IOException
	{
		manager = m;
		sock = new ServerSocket(portNum);
	}

	public void run() 
	{	
		try
		{
			
			try
			{
				while (!stop)
				{
					Socket soc = sock.accept();
					System.out.println("server work");
					Client client = new Client(soc,manager);
					manager.addClient(client);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				sock.close();
			}
		}
		catch(IOException e)
		{
			System.out.println("Server failed");
		}


	}

}
