package ourVersion;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

public class ClientManager 
{
	private ArrayList<Client> clients;
	private ArrayList<String> public_messages;
	
	public ClientManager()
	{
		clients = new ArrayList<Client>();
		public_messages = new ArrayList<String>();
	}
	
	public void addClient(Client c) {
		clients.add(c);
		c.setMessageLoc(public_messages.size());
	}
	
	public void whisper(String message, Client c) throws IOException {
		PrintStream out = new PrintStream(c.getSocket().getOutputStream());
		out.println("200 "+message);
	}
	
	public void getNextMessage(Client c) throws IOException {
		
		PrintStream out = new PrintStream(c.getSocket().getOutputStream());
		if (c.getMessageLoc() < public_messages.size()) {
			int temp = c.getMessageLoc();
			c.setMessageLoc(c.getMessageLoc()+1);
			
			out.println("200 "+public_messages.get(temp));
		}
		
		out.println("201");
	}
	
	public void getUsers(Client c) throws IOException {
		String users = "";
		for (Client temp: clients) {
			users = users + " "+temp.getUsername();
		}
		
		PrintStream out = new PrintStream(c.getSocket().getOutputStream());
		
		out.println("200 "+users);
		
	}
	
}
