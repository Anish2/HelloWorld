package chatProgram;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JOptionPane;


/** 
 * This will need to communicate with the GUI
 * @author eashaan
 *
 */
public class ChatClient implements Runnable{

	private User user;
	private Scanner serverIn;
	private PrintWriter serverOut;
	
	public ChatClient(String name, Socket soc){
		user = new User(soc, name);
	}
	
	public ChatClient(String name, String host, int port) throws UnknownHostException, IOException{
		user = new User(new Socket(host, port), name);
	}
	
	public void run() {
		try{
			try{
				serverIn = new Scanner(user.getSocket().getInputStream());
				serverOut = new PrintWriter(user.getSocket().getOutputStream());
				serverOut.flush();
				
				//You might have to send the message about u joining
				 while(true){
					 RECIEVE();
				 }
			}
			finally{
				this.user.close();
				this.serverIn.close();
				this.serverOut.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void DISCONNECT() throws IOException{
		serverOut.println(this.user.getName() + " has disconnected");
		serverOut.flush();
		this.user.close();
		JOptionPane.showMessageDialog(null, "You Disconnected");
		System.exit(0);
	}
	
	/**
	 * Checks if the message is for it or if it is not for it
	 */
	
	public void RECIEVE(){
		if(serverIn.hasNext()){
			String message = serverIn.nextLine();
			if(message.contains(ChatServer.SC_USERLIST)){
				String t1 = message.substring(ChatServer.SC_USERLIST.length());
				t1 = t1.replace("[", "");
				t1 = t1.replace("]", "");
				
				String[] currrentUsers = t1.split(", ");
				System.out.println(currrentUsers[0] + " " + message);
				ChatFrame.jl_online.setListData(currrentUsers);  //SET THE USER GUI
			}
			
			
			else
			{
				ChatFrame.ta_conversation.append(message + "\n");
				//APPEND TO THE CONVERSATION
			}
		}
	}
	
	public void SEND(String message){
		
		
		serverOut.println(this.user.getName() + ": " + message);
		serverOut.flush();
		System.out.println("@CLIENT: You sent the following message to the server: " + message);
		//Reset the text of the commenting text field to ""
		ChatFrame.tf_message.setText("");
	}
	
	
	public User getUser(){
		return this.user;
	}

	public void WHISPER(String nextToken, String nextToken2) {
		// TODO Auto-generated method stub
		
	}


}
