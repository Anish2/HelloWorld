package ourVersion;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClientHandler implements Runnable
{
	private Socket sock;
	private String username;
	private int message_loc;
	private Server server;

	public ClientHandler(Server server, Socket sock)
	{
		this.server = server;
		this.sock = sock;
		message_loc = server.getMessages().size();
	}

	@Override
	public void run() {
		while (true) {

			try {
				Scanner in = new Scanner(sock.getInputStream());
				PrintStream out = new PrintStream(sock.getOutputStream());

				if (!server.getWhispers().isEmpty() && server.getWhispers().peek().getData()[0].equals(username)) {
					Message m = server.getWhispers().poll();
					out.println("200 ok WHISP "+m.getData()[1]);
				}
				while (!in.hasNextLine());
				
				String p = in.nextLine();
				StringTokenizer t = new StringTokenizer(p);
				if (!t.hasMoreTokens())
					continue;
				String cmd = t.nextToken();
				//System.out.println(cmd);

				if (cmd.equals("JOIN")) {
					String username = t.nextToken();
					this.username = username;
					if (!server.getUsers().contains(username)) {
						server.getUsers().add(username);
						//out.println("200 ok");
					}
					else {
						out.println("409 username in use");
					}
				}
				else if (cmd.equals("SEND")) {
					System.out.println("SEND");
					String message = "";
					while (t.hasMoreTokens())
						message += t.nextToken();

					server.addMessage(message);
					//out.println("200 ok");
				}
				else if (cmd.equals("FETCH")) {
					System.out.println("FETCH");
					if (message_loc == server.getMessages().size())
						out.println("201 ok but no messages");
					else {
						System.out.println("Public Messages: "+server.getMessages());
						if (server.getMessages().size() > 0) {
							out.println("200 ok "+server.getMessages().get(message_loc));
						}
						else {
							out.println("201 ok but no messages");
						}
						message_loc = server.getMessages().size();
					}
				}
				else if (cmd.equals("WHISP")) {
					String username = t.nextToken();

					String message = "";
					while (t.hasMoreTokens())
						message += t.nextToken();
					if (!server.getUsers().contains(username))
						out.println("404 no such user");
					else {
						Message m = new Message(InputOutput.WHISPER, new String[] {username, message});
						server.addPM(m);
						out.println("200 ok");
					}
				}
				else if (cmd.equals("LIST")) {
					String users = "";
					for (String str: server.getUsers())
						users += str;
					out.println("200 ok "+users);
				}

			} catch (IOException e) {
				e.printStackTrace();
			} 
		}


	} 

}

