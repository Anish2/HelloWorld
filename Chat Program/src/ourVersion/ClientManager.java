package ourVersion;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;

public class ClientManager implements Runnable
{
	private ArrayList<Client> clients;
	private ArrayList<String> public_messages, usernames;
	private Queue<Message> pm = new ArrayBlockingQueue<Message>(50);

	public ClientManager()
	{
		clients = new ArrayList<Client>();
		public_messages = new ArrayList<String>();
		usernames = new ArrayList<String>();
	}

	public void addClient(Client c) {
		clients.add(c);
		c.setMessageLoc(public_messages.size());
		System.out.println("adding");
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

	@Override
	public void run() {
		while (true) {
			System.out.println("reached");
			System.out.println(clients);

			for (Client c: clients) {

				try {
					Scanner in = new Scanner(c.getSocket().getInputStream());
					PrintStream out = new PrintStream(c.getSocket().getOutputStream());

					if (!pm.isEmpty() && pm.peek().getData()[0].equals(c.getUsername())) {
						Message m = pm.poll();
						out.println("200 ok WHISP "+m.getData()[1]);
					}
					StringTokenizer t = new StringTokenizer(in.nextLine());
					String cmd = t.nextToken();
					System.out.println(cmd);

					if (cmd.equals("JOIN")) {
						String username = t.nextToken();
						c.setUsername(username);
						if (!usernames.contains(username)) {
							usernames.add(username);
							//out.println("200 ok");
						}
						else {
							out.println("409 username in use");
						}
					}
					else if (cmd.equals("SEND")) {
						String message = "";
						while (t.hasMoreTokens())
							message += t.nextToken();
						public_messages.add(message);
						out.println("200 ok");
					}
					else if (cmd.equals("FETCH")) {
						if (c.getMessageLoc() == public_messages.size())
							out.println("201 ok but no messages");
						else {
							out.println("200 ok "+public_messages.get(c.getMessageLoc()));
							c.setMessageLoc(c.getMessageLoc()+1);
						}
					}
					else if (cmd.equals("WHISP")) {
						String username = t.nextToken();
						String message = "";
						while (t.hasMoreTokens())
							message += t.nextToken();
						if (!usernames.contains(username))
							out.println("404 no such user");
						else {
							Message m = new Message(InputOutput.WHISPER, new String[] {username, message});
							pm.offer(m);
							out.println("200 ok");
						}
					}
					else if (cmd.equals("LIST")) {
						String users = "";
						for (String str: usernames)
							users += str;
						out.println("200 ok "+users);
					}


				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		}

	}

}
