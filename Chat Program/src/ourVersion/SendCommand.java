package ourVersion;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import chatProgram.ChatFrame;

public class SendCommand extends InputOutput implements Runnable {
	
	private String message;

	public SendCommand(String username, String message, Socket sock)
			throws UnknownHostException, IOException {
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
