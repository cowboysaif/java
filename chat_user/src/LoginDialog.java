

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.EventObject;
import java.util.Vector;
/**
 * This is class is used to develop the login interface to connect the user to the 
 * chat system. Here we provide the information like log in usename, password, server IP and 
 * the port to which it is listerning. this class extends the feature of JDialog and implement 
 * action listner 
 * @author Paras / Muskan
 *
 */
class LoginDialog extends JDialog implements ActionListener
{
	// variable decleration
	private static String _username=null,_password=null,_server=null;
	private JLabel label1,label2,label3,label4;
	private JTextField user,server,port;
	private JPasswordField password;
	private JButton ok,cancel;
	private Container container;
final String 	SERVER_HOST 		="localhost"; // "172.16.10.1";
	final int		SERVER_PORT 		= 2979;
/**
 * This constructor take the name for the dialogue box
 * @param frame 
 */
	public LoginDialog(JFrame frame)
	{
		super(frame,"Login",true);
		initDialogBox(frame);
	}
/**
 * the dialogue box is initialized with the necessay parameters
 * @param frame the Jframe class is used 
 */
	private void initDialogBox(JFrame frame)
	{
		// drawing the dialogue box with necessay fields
		container = this.getContentPane();
		container.setLayout(null);
		label1= new JLabel("Login name :");
		label1.setBounds(10,10,80,20);
		label2= new JLabel("  Password :");
		label2.setBounds(10,40,80,20);
		user= new JTextField();
		user.setBounds(100,10,100,20);
		password=new JPasswordField();
		password.setBounds(100,40,100,20);

		label3= new JLabel("    Server :");
		label3.setBounds(10,70,80,20);
		label4= new JLabel("      Port :");
		label4.setBounds(10,100,80,20);
		server= new JTextField(SERVER_HOST);
		server.setBounds(100,70,100,20);
		port=new JTextField(SERVER_PORT+"");
		port.setBounds(100,100,100,20);
		port.setEditable(false);
		ok=new JButton("Login");
		ok.setBounds(30,130,70,20);
		cancel= new JButton("Cancel");
		cancel.setBounds(110,130,80,20);

		container.add(label1);
		container.add(user);
		container.add(label2);
		container.add(password);
		container.add(label3);
		container.add(server);
		container.add(label4);
		container.add(port);
		container.add(ok);
		container.add(cancel);

		user.addActionListener(this);
		password.addActionListener(this);
		server.addActionListener(this);
		port.addActionListener(this);
		ok.addActionListener(this);
		cancel.addActionListener(this);

		this.setSize(220,190);
		this.setResizable(false);
		this.setLocationRelativeTo(frame);
		this.setLocation(450,220);
		this.setVisible(true);
	}
/**
 * proceed with the system when the correct information is provided in the dilogue box
 * other wise the message is displayed to say that all the correct information are not provided. 
 */
	public void actionPerformed(ActionEvent event)
	{
        if((event.getSource() == ok) || (event.getSource() == password)
        	||(event.getSource() == user) || (event.getSource() == server) ) {
			_username = user.getText();
			_password = new String(password.getPassword());
			_server = server.getText();
			if((_username.length()==0) || (_password.length() == 0))
			{
				JOptionPane.showMessageDialog(this,
					"Please enter a valid username and password","Error",
						JOptionPane.WARNING_MESSAGE);
				return;
			}
			if(_server.length()== 0)
			{
				JOptionPane.showMessageDialog(this,
					"Invalid server host","Error",
						JOptionPane.WARNING_MESSAGE);
				return;
			}

		} else if(event.getSource() == cancel) {
			//NOTHING
		}
		this.setVisible(false);

	}
/**
 * 
 * @return the username when called from processlogin method of the client class 
 */
	protected static String getUserName()
	{
		return _username;
	}
/**
 * 
 * @return the password information when called from processlogin method of the client class 
 */
	protected static String getPassword()
	{
		return _password;
	}
/**
 * 
 * @return server host string when called from processlogin method of the client class
 */
	protected static String getServerHost()
	{
		return _server;
	}
}