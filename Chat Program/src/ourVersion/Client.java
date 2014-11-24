package ourVersion;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Client implements Runnable {

	private Socket sock;
	private ClientManager m;
	private Scanner in;
	private PrintWriter out;
	private boolean stop;
	
	public Client(Socket s, ClientManager manager)
	{
		sock = s;
		m = manager;
	}

	public void run() {
		try
		{
			try
			{
				in = new Scanner(sock.getInputStream());
				out = new PrintWriter(sock.getOutputStream());
				doStuff();
			}
			finally
			{
				sock.close();
			}
		}
		catch (IOException e) // Change later
		{
			e.printStackTrace();
		}
	}

	private void doStuff() 
	{
		while(!stop)
		{
			if(!in.hasNext())
			{
				return;
			}
			else
			{
				String message = in.nextLine();
				StringTokenizer tokenizer = new StringTokenizer(message);
				String command = tokenizer.nextToken();
				switch(command)
				{
					case "SecretCommandToGrantYouAdminAcess12345":
					{
						//Implement secret command
					}
					case "SEND":
					{
						
					}
					case "WHISPER":
					{
						
					}
					case "LIST":
					{
						
					}
					case "NEAH PIZZA":
					{
						
					}
				}
			}
		}
	}

}
