package ourVersion;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Client {

	private Socket sock;
	private ClientManager m;
	private Scanner in;
	private PrintWriter out;
	private String username;
	private boolean stop;
	private int message_loc;

	public Client(Socket s, ClientManager manager)
	{
		sock = s;
		m = manager;
	}

	public Socket getSocket() {
		return sock;
	}
	
	public int getMessageLoc() {
		return message_loc;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String u) {
		username = u;
	}
	
	public void setMessageLoc(int loc) {
		message_loc = loc;
	}

}
