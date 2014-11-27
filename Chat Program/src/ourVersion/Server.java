package ourVersion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Server implements Runnable
{
	private boolean stop = false;
	private ServerSocket sock;
	private ArrayList<String> public_messages, usernames;
	private Queue<Message> pm;

	public Server(int portNum) throws IOException
	{
		pm = new ConcurrentLinkedQueue<Message>();
		sock = new ServerSocket(portNum);
		public_messages = new ArrayList<String>();
		usernames = new ArrayList<String>();
	}

	public void addMessage(String m) {
		public_messages.add(m);
	}

	public void addUsername(String u) {
		usernames.add(u);
	}

	public ArrayList<String> getUsers() {
		return usernames;
	}

	public ArrayList<String> getMessages() {
		return public_messages;
	}

	public void addPM(Message m) {
		pm.offer(m);
	}

	public Queue<Message> getWhispers() {
		return pm;
	}

	public void run() 
	{	
		while (!stop)
		{
			try {
				Socket soc = sock.accept();
				System.out.println("server work");
				ClientHandler client = new ClientHandler(this, soc);
				Thread t = new Thread(client);
				t.start();
			} catch(IOException e) {
				System.out.println("Server failed");
				e.printStackTrace();
			}
		}

	}

}
