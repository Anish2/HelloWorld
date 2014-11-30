package ourVersion;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;



public class SendCommand implements Runnable {
	
	private PrintStream out;
	//private Scanner in;
	private String message;
	private InputOutput io;

	public SendCommand(String message, Socket sock, InputOutput io) throws IOException {
		this.io = io;
		this.message = message;
	//	in = new Scanner(sock.getInputStream());
		out = new PrintStream(sock.getOutputStream());
	}

	public void run() {
		
		io.getLock().lock();
		try {
			while (!io.can_print())
				io.getCondition().await();
			io.toggle_print();
			out.println("SEND " + message);
			out.flush();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		io.toggle_print();
		io.getCondition().signalAll();
		io.getLock().unlock();
		
		
	}
	
	
	
	

}
