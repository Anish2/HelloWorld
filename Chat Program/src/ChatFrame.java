package chatFromScratch;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class ChatFrame {
	// should start from constructor

	private static ChatClient chatClient;
	// Gui
	public JFrame mainWindow = new JFrame();

	// Chat Window
	private JButton b_disconnect = new JButton("Disconnect");

	private JLabel l_message = new JLabel("Message: ");
	public static JTextField tf_message = new JTextField(25);

	private JLabel l_conversation = new JLabel();
	public static JTextArea ta_conversation = new JTextArea();
	private JScrollPane sp_conversation = new JScrollPane();

	private JLabel l_online = new JLabel();
	public static JList jl_online = new JList();
	private JScrollPane sp_online = new JScrollPane();

	private JLabel l_loggedInAs = new JLabel();
	private JLabel l_loggedInAsBox = new JLabel();

	public ChatFrame(String userName, String hostName, int port) {
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.connect(userName, hostName, port);
		mainWindow();
		mainWindowAction();
	}

	private void connect(String userName, String hostName, int port) {
		try {
			final String HOST = "localhost";
			Socket SOCK = new Socket(HOST, port);
			System.out.println("You connected to: " + HOST);

			chatClient = new ChatClient(userName, SOCK);

			// send name to add to online list of users on the right
			PrintWriter out = new PrintWriter(SOCK.getOutputStream());
			out.println(userName);
			out.flush();

			Thread t = new Thread(chatClient);
			t.start();
			mainWindow.setTitle(chatClient.getUser().getName()
					+ "'s conversation");
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Server not responding.");
			System.exit(0);
		}
	}

	/****************************************** CONNECTIONS FROM GUI TO SERVER/CLIENT ********************************************/
	
	private void mainWindowAction(){
		tf_message.addActionListener(
				new ActionListener()
				{
					
					public void actionPerformed(ActionEvent e) {
						action_TF_Send();
					}
				});
		b_disconnect.addActionListener(
				new ActionListener()
				{
					
					public void actionPerformed(ActionEvent e) {
						action_B_Disconnect();
					}
				});
	}
	
	private void action_TF_Send(){
		if(!tf_message.getText().equals(""))
		{
			chatClient.SEND(tf_message.getText());
			tf_message.requestFocus();
		}
	}
	
	private static void action_B_Disconnect(){
		try{
			chatClient.DISCONNECT();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/****************************************** GRAPHICS ***************************************************/
	private void mainWindow() {
		mainWindow.setSize(900, 900);
		mainWindow.setLocation(220, 180);
		mainWindow.setResizable(false);
		mainWindow.setVisible(true);
		configureMainWindow();
	}

	private void configureMainWindow() {
		
		mainWindow.setLayout(null);

		// left side
		ta_conversation.setColumns(20);
		ta_conversation.setFont(new Font("Tahoma", 0, 12));
		ta_conversation.setForeground(new Color(0, 0, 255));
		ta_conversation.setLineWrap(true);
		ta_conversation.setRows(5);
		ta_conversation.setEditable(false);

		sp_conversation
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp_conversation
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp_conversation.setViewportView(ta_conversation);
		mainWindow.getContentPane().add(sp_conversation);
		sp_conversation.setBounds(20, 150, 570, 600);

		l_conversation.setHorizontalAlignment(SwingConstants.CENTER);
		l_conversation.setText("Conversation");
		mainWindow.getContentPane().add(l_conversation);
		l_conversation.setBounds(223, 134, 140, 16);

		mainWindow.getContentPane().add(l_message);
		l_message.setBounds(20, 770, 62, 20);

		tf_message.setForeground(new Color(0, 0, 255));
		tf_message.requestFocus();
		mainWindow.getContentPane().add(tf_message);
		tf_message.setBounds(82, 770, 512, 30);

		// right
		l_online.setHorizontalAlignment(SwingConstants.CENTER);
		l_online.setText("Currently Online");
		l_online.setToolTipText("");
		mainWindow.getContentPane().add(l_online);
		l_online.setBounds(694, 134, 103, 16);

		// String[] testNames = {"Bob", "Sue", "Jenny", "Anna"};
		jl_online.setForeground(new Color(0, 0, 255));
		// jl_online.setListData(testNames);
		sp_online
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		sp_online
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sp_online.setViewportView(jl_online);
		mainWindow.getContentPane().add(sp_online);
		sp_online.setBounds(610, 150 , 270 , 600);
		
		// top
		l_loggedInAs.setFont(new Font("Tahoma", 0, 15));
		l_loggedInAs.setText("Currently Logged In As");
		mainWindow.add(l_loggedInAs);
		l_loggedInAs.setBounds(375, 0, 150, 20);

		String name = this.chatClient.getUser().getName();

		l_loggedInAsBox.setHorizontalAlignment(SwingConstants.CENTER);
		l_loggedInAsBox.setFont(new Font("Tahoma", 0, 25));
		l_loggedInAsBox.setForeground(new Color(0, 0, 0));
		l_loggedInAsBox.setBorder(BorderFactory.createLineBorder(new Color(0,0, 0)));
		l_loggedInAsBox.setText(name);
		l_loggedInAsBox.setPreferredSize(new Dimension(100, 100));
		mainWindow.add(l_loggedInAsBox);
		l_loggedInAsBox.setBounds(350, 40, 200, 40);

		
		b_disconnect.setForeground(new Color(0, 0, 0));
		mainWindow.add(b_disconnect); 
		b_disconnect.setBounds(375, 100, 150, 30);
		 
		this.tf_message.requestFocus();

	}

}
