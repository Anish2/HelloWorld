package chatProgram;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * This class just takes care of the chat room and updates its users
 * @author eashaan
 *
 */
public class MutliplayerServer implements Runnable{
	Socket sock;
	private Scanner input;
	private PrintWriter output;
	String message = "";
	
	public MutliplayerServer(Socket s){
		this.sock = s;	
	}
	
	/**
	 * Removes any connections that might be empty
	 * outputs if anyone disconnected
	 * @throws IOException 
	 */
	private void checkDisconnected() throws IOException{
		if(!sock.isConnected()){
			for(int i = 0; i < ChatServer.users.size(); i++){
				if(ChatServer.users.get(i).getSocket() == sock)
					ChatServer.users.remove(i);
			}
			
			for(int i = 0; i < ChatServer.users.size(); i++){
				Socket tempSoc = ChatServer.users.get(i).getSocket();
				PrintWriter	out = new PrintWriter(tempSoc.getOutputStream());
				out.println(sock.getLocalAddress().getHostName() + " disconnected");
				out.flush();
				System.out.println(tempSoc.getLocalAddress().getHostName() + " disconnected");
			}
		}
	}

	public void run() {
		try{
			try{
				input = new Scanner(sock.getInputStream());
				output = new PrintWriter(sock.getOutputStream());
				
				while(true){
					checkDisconnected();
					
					if(!input.hasNext())
						return;
					this.message = input.nextLine();
					handleSendingMessageToClients();
				}
			}
			finally{
				sock.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void handleSendingMessageToClients() throws IOException{			
		sendToAll();

	}
	
	public void sendToAll() throws IOException{
		//System.out.println("One of the Clients said: " + this.message);
		
		for(int i = 0; i < ChatServer.users.size(); i++){
			Socket tempSoc = ChatServer.users.get(i).getSocket();
			PrintWriter	out = new PrintWriter(tempSoc.getOutputStream());
			out.println(this.message);
			out.flush();
			System.out.println("Sent to: " + tempSoc.getLocalAddress().getHostName());
		}
	}

}
