package ourVersion;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class InputOutput
{
	public static final int PORT_NUM = 80;

	private PrintWriter print;
	private Scanner read;
	private Socket sock;

	public InputOutput(Socket sock, String username) throws UnknownHostException, IOException
	{
		this.sock = sock;
		InputStream in;
		OutputStream out;

		in = sock.getInputStream();
		out = sock.getOutputStream();

		print = new PrintWriter(out);

		print.println("JOIN " + username);

		checkGoodResponse();

		read = new Scanner(in);

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
		print.println("Whisper " + message + " " + user);
		print.flush();
		checkGoodResponse();
	}


	public void close() throws IOException
	{
		sock.close();
	}

	public ArrayList<String> getPpl() throws InvalidResponseException
	{
		print.println("LIST");

		ArrayList<String> ppl = new ArrayList<String>();
		String response = read.nextLine();

		StringTokenizer t = new StringTokenizer(response);

		if (!t.nextToken().equals("200"))
			throw new InvalidResponseException(response);
		t.nextToken();

		while (t.hasMoreTokens())
		{
			ppl.add(t.nextToken());
		}

		return ppl;

	}
}
