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
			String message = super.getNextChat();
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
		} catch (InvalidResponseException e) {
			e.printStackTrace();
		}
		
	}

}
