package ourVersion;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ChatMain {

	private static JFrame introWindow = new JFrame("Chat Program");
	private static final JLabel l_username = new JLabel("Username: ");
	private static final JLabel l_portNum = new JLabel("Port Number: ");

	private static final JTextField tf_userName = new JTextField();
	private static final JTextField tf_portNum = new JTextField();

	private static final JButton b_joinServer = new JButton("Join Chat Room");


	public static void main(String[] args) throws IOException{

	
			
			ClientManager manager = new ClientManager();
			Thread m = new Thread(manager);
			m.start();
			
			Server chat = new Server(manager, 8888);
			Thread t = new Thread(chat);
			t.start();
			
			
			makeWindow();
			//ChatFrame frame = new ChatFrame("Username", "localhost", 8888);

		
		
	}

	private static void makeWindow(){

		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());

			addWindowStuff();

			
			introWindow.setLocationByPlatform(true);
			introWindow.setLocation(1000, 300);
			introWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			introWindow.setSize(500, 500);
			introWindow.setResizable(false);
			introWindow.setVisible(true);

		} catch (Exception e) {
			System.out.println("Error setting Java LAF: " + e);
		}
	}

	private static void addWindowStuff(){

		int x = 100, y = 100;

		JPanel panel = new JPanel();
		panel.setLayout(null);


		l_username.setBounds(x, y, 75, 100);
		l_portNum.setBounds(x, y + 30, 90, 100);

		tf_userName.setBounds(x + 75, y + 40, 500 - 180 - 75, 25);
		tf_portNum.setBounds(x + 90, y + 40 + 30, 500 - 195 - 75, 25);

		b_joinServer.setBounds(x + 250 - 220/2 - 100, y + 40 + 30 + 60 + 70, 220, 50);



		panel.add(l_username);
		panel.add(l_portNum);

		panel.add(tf_userName);
		panel.add(tf_portNum);

		panel.add(b_joinServer);

		introWindow.add(panel);

		b_joinServer.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						if(check()) {							

							ChatFrame frame = new ChatFrame(tf_userName.getText(), "localhost",
									Integer.parseInt(tf_portNum.getText()));

						}

					}
				});
	}



	public static boolean check(){
		String name = tf_userName.getText();
		String port = tf_portNum.getText();
		String securityName = name.trim();
		String securityPort = port.trim();
		int actualPort = -1;

		if(securityName.length() >= 1)
		{
			l_username.setForeground(Color.black);
		}
		if(securityPort.length() >= 1)
		{
			l_portNum.setForeground(Color.black);
		}

		if(securityName == null || securityName.equals("")){
			l_username.setForeground(Color.red);
			return false;
		}
		if(port == null || port.equals("")){
			l_portNum.setForeground(Color.red);
			return false;
		}


		try{
			actualPort = Integer.parseInt(securityPort);

		}
		catch(Exception e){
			//e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Please enter a valid number");
			return false;
		}	

		return true;
	}
}
