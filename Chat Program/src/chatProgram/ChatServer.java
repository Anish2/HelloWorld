package chatProgram;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * WHO EVER RUNS THIS CLASS IS THE ADMIN
 * @author eashaan
 *
 */
public class ChatServer implements Runnable{

	private ServerSocket serverSocket;
	public static ArrayList<User> users = new ArrayList<User>();
	public static final String SC_USERLIST = "!@#$%^&*()";
	public static final String SC_WHISPER = "/w";
	
	public ChatServer(ServerSocket socket) throws IOException{
		serverSocket = socket;

	}
	
	public ChatServer(int port) throws IOException{
		serverSocket = new ServerSocket(port);
	
	}
	
	/**
	 * All this does is wait for a socket to join, gets its user name and pass it to the multiPlayerServer Class as a new thread
	 */
	public void run() {
		System.out.println("Server started");
		try{

			while(true){
				System.out.println("Waiting for someone to join...");
				Socket userSocket = this.serverSocket.accept();
				System.out.println("@SERVER to Admin: This person joined: " + userSocket.toString());
				//add this user and send the new list of users
				addUser(userSocket);
				//send this data to a new multiPlayerServer
				MutliplayerServer chat = new MutliplayerServer(userSocket);
				Thread t = new Thread(chat);
				t.start();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Takes in the user name first of all. Adds the user name to array list. Then it sends everybody the new list of users
	 * @param socket
	 * @throws IOException 
	 */
	public void addUser(Socket socket) throws IOException{
		Scanner serverIn = new Scanner(socket.getInputStream());
		String username = serverIn.nextLine();
		users.add(new User(socket, username));
		
		ArrayList<String> onlineNames = new ArrayList<String>();
		for(int i = 0; i < users.size(); i++){
			onlineNames.add(users.get(i).getName());
		}
		
		for(int i = 0; i < users.size(); i++){
			Socket tempSoc = users.get(i).getSocket();
			PrintWriter output = new PrintWriter(tempSoc.getOutputStream());
			output.println(SC_USERLIST + onlineNames.toString());
			output.flush();
		}
	}
}
