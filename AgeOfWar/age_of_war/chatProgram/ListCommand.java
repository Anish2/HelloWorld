package chatProgram;

import java.io.IOException;
import java.net.UnknownHostException;

public class ListCommand extends InputOutput implements Runnable {

	public ListCommand(String hostname, String username)
			throws UnknownHostException, IOException {
		super(hostname, username);
	}

	public void run() {
		try {
			super.getPpl();
		} catch (InvalidResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
