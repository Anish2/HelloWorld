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

			StringTokenizer tokenizer = new StringTokenizer(response);

			String num = tokenizer.nextToken();
			tokenizer.nextToken();

			String message = tokenizer.nextToken();
			//System.out.println(response);
			if(num.equals("200"))
			{
				io.getFrame().edit_text(message);
				System.out.println("Chat Frame: "+ChatFrame.ta_conversation.getText());
			}
		}

	}

	/*public void appendNewText(String txt) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				outputText.setText(outputText.getText + txt);
				//outputText.setText(outputText.getText + "\n"+ txt); Windows LineSeparator
			}
		});
	}*/
}