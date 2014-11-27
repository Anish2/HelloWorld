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

import ourVersion.ChatFrame;

public class InputOutput implements Runnable
{

	private PrintWriter print;
	private Scanner read;
	private Socket sock;
	private Queue<Message> tasks = new ConcurrentLinkedQueue<Message>();
	private Lock cmd_lock = new ReentrantLock();
	private Condition cmd_access = cmd_lock.newCondition();
	private boolean can_print = true;
	private ChatFrame frame;

	public static final int SEND = 1;
	public static final int WHISPER = 2;


	public InputOutput(String host, int port, String username, ChatFrame chatFrame) throws UnknownHostException, IOException
	{
		this.frame = chatFrame;
		sock = new Socket(host, port);

		InputStream in;
		OutputStream out;

		in = sock.getInputStream();
		out = sock.getOutputStream();
		read = new Scanner(in);

		print = new PrintWriter(out);

		cmd_lock.lock();
		try {
			while (!can_print)
				cmd_access.await();
			toggle_print();
			print.println("JOIN " + username);
			print.flush();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		toggle_print();
		cmd_access.signalAll();
		cmd_lock.unlock();
	}

	public boolean can_print() {
		return can_print;
	}

	public void toggle_print() {
		try {
			Thread.sleep(15);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		can_print = !can_print;
	}

	public Lock getLock() {
		return cmd_lock;
	}

	public ChatFrame getFrame() {
		return frame;
	}

	public Condition getCondition() {
		return cmd_access;
	}

	public void addTask(Message task) {
		tasks.add(task);
	}

	public void sendChat(String chat) throws IOException
	{
		Thread t = new Thread(new SendCommand(chat, sock, this));
		t.start();
	}

	public void getNextChat() throws UnknownHostException, IOException
	{
		Thread t = new Thread(new FetchCommand(sock, this));
		t.start();
	}

	public void whisper(String message, String user) throws InvalidResponseException
	{
		print.println("WHISP " + message + " " + user);
		print.flush();
	}


	public void getUsers() throws IOException
	{
		Thread t = new Thread(new ListCommand(sock, this));
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
