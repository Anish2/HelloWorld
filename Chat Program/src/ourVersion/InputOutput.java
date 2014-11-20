package ourVersion;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.concurrent.ArrayBlockingQueue;

import chatProgram.ChatFrame;

public class InputOutput implements Runnable
{

	private PrintWriter print;
	private Scanner read;
	private Socket sock;
	private Queue<Message> tasks = new ArrayBlockingQueue<Message>(10);
	
	public static final int SEND = 1;
	public static final int WHISPER = 2;
	

	public InputOutput(String host, int port, String username) throws UnknownHostException, IOException
	{
		sock = new Socket(host, port);

		InputStream in;
		OutputStream out;

		in = sock.getInputStream();
		out = sock.getOutputStream();

		print = new PrintWriter(out);

		print.println("JOIN " + username);

		checkGoodResponse();

		read = new Scanner(in);

	}
	
	public void addTask(Message task) {
		tasks.offer(task);
	}

	public void sendChat(String chat) throws InvalidResponseException
	{
		print.println("SEND " + chat);
		print.flush();
		checkGoodResponse();
	}

	private void checkGoodResponse() throws InvalidResponseException
	{
		String response = read.nextLine();
		
		StringTokenizer t = new StringTokenizer(response);
		String code = t.nextToken();

		if(!code.equals("200"))
			throw new InvalidResponseException(response);
	}

	public String getNextChat() throws InvalidResponseException
	{
		print.println("FETCH");
		print.flush();

		String response = read.nextLine();

		StringTokenizer tokenizer = new StringTokenizer(response);

		String num = tokenizer.nextToken();
		tokenizer.nextToken();
		String message = tokenizer.nextToken();

		if(num.equals("200"))
		{
			return message;
		}
		else if (num.equals("201"))
		{
			return null;
		}
		else 
		{
			throw new InvalidResponseException(response);
		}
	}

	public void whisper(String message, String user) throws InvalidResponseException
	{
		print.println("WHISPER " + message + " " + user);
		print.flush();
		checkGoodResponse();
	}


	public String[] getUsers() throws InvalidResponseException
	{
		print.println("LIST");
		String response = read.nextLine();

		StringTokenizer t = new StringTokenizer(response);

		if (!t.nextToken().equals("200"))
			throw new InvalidResponseException(response);
		t.nextToken();
		
		String[] ppl = new String[t.countTokens()];
		int c = 0;

		while (t.hasMoreTokens())
		{
			ppl[c] = t.nextToken();
			c++;
		}

		return ppl;

	}
	
	public void close() throws IOException
	{
		sock.close();
	}

	@SuppressWarnings("unchecked")
	public void run() {
		
		
		try {
			// get next chat
			String chat = getNextChat();
			if (chat != null) {
				// append chat to list of messages
				ChatFrame.ta_conversation.append(chat + "\n");
			}
			
			// update list of users
			String[] users = getUsers();
			ChatFrame.jl_online.setListData(users);
			
			// execute tasks
			if (!tasks.isEmpty()) {
				Message todo = tasks.poll();
				if (todo.getType() == SEND) 
					sendChat(todo.getData()[0]);
				else if (todo.getType() == WHISPER)
					whisper(todo.getData()[0], todo.getData()[1]);
			}
			
			
		} catch (InvalidResponseException e) {
			e.printStackTrace();
		}						
		
	}
}
