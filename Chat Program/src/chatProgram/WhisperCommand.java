package chatProgram;

import java.io.IOException;
import java.net.UnknownHostException;

public class WhisperCommand extends InputOutput implements Runnable {
	
	private String message, user;

	public WhisperCommand(String hostname, String username, String message, String user)
			throws UnknownHostException, IOException {
		super(hostname, username);
		this.message = message;
		this.user = user;

	}

	public void run() {
		try {
			super.whisper(message, user);
		} catch (InvalidResponseException e) {
			e.printStackTrace();
		}
		
	}

}
