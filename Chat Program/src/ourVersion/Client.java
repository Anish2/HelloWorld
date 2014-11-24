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
	private String username;
	private boolean stop;
	private int message_loc;

	public Client(Socket s, ClientManager manager)
	{
		sock = s;
		m = manager;
	}

	public Socket getSocket() {
		return sock;
	}
	
	public int getMessageLoc() {
		return message_loc;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setMessageLoc(int loc) {
		message_loc = loc;
	}
	
	public void run() {

		try {
			in = new Scanner(sock.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		doStuff();

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
