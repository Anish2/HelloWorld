package chatProgram;

import java.io.IOException;
import java.net.UnknownHostException;

public class FetchCommand extends InputOutput implements Runnable {

	public FetchCommand(String hostname, String username)
			throws UnknownHostException, IOException {
		super(hostname, username);
	}

	public void run() {
		try {
			super.getNextChat();
		} catch (InvalidResponseException e) {
			e.printStackTrace();
		}
		
	}

}
