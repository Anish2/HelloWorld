package ourVersion;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import chatProgram.ChatFrame;

public class ListCommand implements Runnable {

	private PrintStream out;
	private Scanner in;
	private InputOutput io;

	public ListCommand(Socket sock, InputOutput io) throws IOException {
		this.io = io;
		in = new Scanner(sock.getInputStream());
		out = new PrintStream(sock.getOutputStream());
	}

	public void run() {
		
		io.getLock().lock();
		try {
			
			while (!io.can_print())
				io.getCondition().await();
			io.toggle_print();
			out.println("LIST");
			out.flush();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		io.toggle_print();
		io.getCondition().signalAll();
		io.getLock().unlock();
		
		
		String response = in.nextLine();

		StringTokenizer t = new StringTokenizer(response);

		if (t.nextToken().equals("200")) {
			t.nextToken();

			String[] ppl = new String[t.countTokens()];
			int c = 0;

			while (t.hasMoreTokens())
			{
				ppl[c] = t.nextToken();
				c++;
			}

			// update list of users
			ChatFrame.jl_online.setListData(ppl);
		}

	}

}
