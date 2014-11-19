package chatProgram;

import java.io.IOException;
import java.net.UnknownHostException;

public class SendCommand extends InputOutput implements Runnable {
	
	private String message;

	public SendCommand(String hostname, String username, String message)
			throws UnknownHostException, IOException {
		super(hostname, username);
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
