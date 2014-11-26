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
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import chatProgram.ChatFrame;

public class InputOutput implements Runnable
{

	private PrintWriter print;
	private Scanner read;
	private Socket sock;
	private Queue<Message> tasks = new ConcurrentLinkedQueue<Message>();
	private Lock printingLock = new ReentrantLock();
	private Condition printing = printingLock.newCondition();

	public static final int SEND = 1;
	public static final int WHISPER = 2;


	public InputOutput(String host, int port, String username) throws UnknownHostException, IOException
	{
		sock = new Socket(host, port);

		InputStream in;
		OutputStream out;

		in = sock.getInputStream();
		out = sock.getOutputStream();
		read = new Scanner(in);

		print = new PrintWriter(out);

		print.println("JOIN " + username);
		print.flush();

	}

	public void addTask(Message task) {
		tasks.add(task);
	}

	public void sendChat(String chat) throws IOException
	{
		Thread t = new Thread(new SendCommand(chat, sock));
		t.start();
	}

	private void checkGoodResponse() throws InvalidResponseException
	{
		String response = read.nextLine();
		//System.out.println(response);
		StringTokenizer t = new StringTokenizer(response);
		String code = t.nextToken();

		if(!code.equals("200"))
			throw new InvalidResponseException(response);
	}

	public void getNextChat() throws UnknownHostException, IOException
	{
		Thread t = new Thread(new FetchCommand(sock));
		t.start();
	}

	public void whisper(String message, String user) throws InvalidResponseException
	{
		print.println("WHISP " + message + " " + user);
		print.flush();
		checkGoodResponse();
	}


	public void getUsers() throws IOException
	{
		Thread t = new Thread(new ListCommand(sock));
		t.start();

	}

	public void close() throws IOException
	{
		sock.close();
	}

	@SuppressWarnings("unchecked")
	public void run() 
	{

		// get next chat
		try {
			getNextChat();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while (true) 
		{
			//System.out.println("Loop ran");

			try {

				// execute tasks
				if (!tasks.isEmpty()) 
				{
					Message todo = tasks.poll();
					if (todo.getType() == SEND) 
						sendChat(todo.getData()[0]);
					else if (todo.getType() == WHISPER)
						whisper(todo.getData()[0], todo.getData()[1]);
				}


			} catch (IOException e) {
				e.printStackTrace();
			}						
		}
	}
}
