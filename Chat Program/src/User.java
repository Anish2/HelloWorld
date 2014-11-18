package chatFromScratch;


import java.io.IOException;
import java.net.Socket;

public class User {

	private Socket socket;
	private String name;
	
	public User(Socket s, String name){
		this.socket = s;
		this.name = name;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void close() throws IOException{
		this.name = null;
		this.socket.close();
	}
	
	
}
