package badClasses;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import ourVersion.InputOutput;
import ourVersion.InvalidResponseException;
import chatProgram.ChatFrame;

public class SendCommand extends InputOutput implements Runnable {
	
	private String message;

	public SendCommand(String username, String message, String host, int port)
			throws UnknownHostException, IOException {
		Socket sock = new Socket(host, port);
		super(sock, username);
		this.message = message;

	}

	public void run() {
		
		try {
			super.sendChat(message);
			ChatFrame.tf_message.setText("");
		} catch (InvalidResponseException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	

}
