package ourVersion;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.StringTokenizer;

import chatProgram.ChatFrame;

public class ListCommand implements Runnable {

	private PrintStream out;
	private Scanner in;

	public ListCommand(Socket sock) throws IOException {
		in = new Scanner(sock.getInputStream());
		out = new PrintStream(sock.getOutputStream());
	}

	public void run() {
		out.println("LIST");
		out.flush();
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
