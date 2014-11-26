package ourVersion;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.locks.Condition;

import chatProgram.ChatFrame;

public class SendCommand implements Runnable {
	
	private PrintStream out;
	private Scanner in;
	private String message;

	public SendCommand(String message, Socket sock) throws IOException {
		this.message = message;
		in = new Scanner(sock.getInputStream());
		out = new PrintStream(sock.getOutputStream());
	}

	public void run() {
		
		out.println("SEND " + message);
		out.flush();
		
	}
	
	
	
	

}
