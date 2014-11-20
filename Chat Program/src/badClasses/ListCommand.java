package badClasses;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import ourVersion.InputOutput;
import ourVersion.InvalidResponseException;

public class ListCommand extends InputOutput implements Runnable {

	public ListCommand(String username, Socket sock)
			throws UnknownHostException, IOException {
		super(sock, username);
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
