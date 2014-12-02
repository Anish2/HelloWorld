package ourVersion;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class FetchCommand implements Runnable {

	private PrintStream out;
	private Scanner in;
	private InputOutput io;

	public FetchCommand(Socket sock, InputOutput io)
			throws UnknownHostException, IOException {
		this.io = io;
		in = new Scanner(sock.getInputStream());
		out = new PrintStream(sock.getOutputStream());
	}

	public void run() {

		while (true) {

			io.getLock().lock();
			try {
				while (!io.can_print())
					io.getCondition().await();
				io.toggle_print();
				out.println("FETCH");
				out.flush();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			io.toggle_print();
			io.getCondition().signalAll();
			io.getLock().unlock();

			while (!in.hasNextLine());

			String response = in.nextLine();

			StringTokenizer t = new StringTokenizer(response);

			String num = t.nextToken();
			t.nextToken();

			String message = "";
			while (t.hasMoreTokens()) {
				message += t.nextToken()+" ";
			}
			
			

			if(num.equals("200"))
			{
				//System.out.println(response);
				io.getFrame().edit_text(message);
				//System.out.println("Chat Frame: "+ChatFrame.ta_conversation.getText());
			}


		}

	}
}