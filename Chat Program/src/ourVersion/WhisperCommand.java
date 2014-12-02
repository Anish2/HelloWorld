package ourVersion;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;


public class WhisperCommand implements Runnable {
	
	private PrintStream out;
	private Scanner in;
	private String message;
	private InputOutput io;
	private String user;

	public WhisperCommand(String message, String username ,Socket sock ,InputOutput io) throws IOException
	{
		this.io = io;
		this.message = message;
		in = new Scanner(sock.getInputStream());
		out = new PrintStream(sock.getOutputStream());
		user = username;
	}

	public void run() {
		io.getLock().lock();
		try {
			while (!io.can_print())
				io.getCondition().await();
			io.toggle_print();
			out.println("WHISP "+user+" "+message);
			out.flush();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		io.toggle_print();
		io.getCondition().signalAll();
		io.getLock().unlock();
		
		String response = in.nextLine();

		StringTokenizer t = new StringTokenizer(response);
		if(t.hasMoreTokens())
		{
			if (!t.nextToken().equals("200")) {
				System.out.println(response);
			}
		}
		else {
			System.out.println("Whisp no response");
		}
		
	}

}
