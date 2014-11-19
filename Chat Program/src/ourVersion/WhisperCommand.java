package ourVersion;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class WhisperCommand extends InputOutput implements Runnable {
	
	private String message, user;

	public WhisperCommand(Socket sock, String username, String message, String user)
			throws UnknownHostException, IOException {
		super(sock, username);
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
