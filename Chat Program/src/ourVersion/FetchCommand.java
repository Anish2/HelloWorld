package ourVersion;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.locks.Condition;

import chatProgram.ChatFrame;

public class FetchCommand implements Runnable {

	private PrintStream out;
	private Scanner in;

	public FetchCommand(Socket sock)
			throws UnknownHostException, IOException {
		in = new Scanner(sock.getInputStream());
		out = new PrintStream(sock.getOutputStream());
	}

	public void run() {
		
		while (true) {
			out.println("FETCH");
			out.flush();

			while (!in.hasNextLine());

			String response = in.nextLine();

			StringTokenizer tokenizer = new StringTokenizer(response);

			String num = tokenizer.nextToken();
			tokenizer.nextToken();

			String message = tokenizer.nextToken();
			System.out.println("Num: "+num);
			if(num.equals("200"))
			{
				//System.out.println(message);
				// append chat to list of messages
				ChatFrame.ta_conversation.append(message + "\n");
				System.out.println("Chat Frame: "+ChatFrame.ta_conversation.getText());
			}
		}

	}
}