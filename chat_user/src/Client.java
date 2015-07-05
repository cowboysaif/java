
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;
/**
 * this class extend the Jframe class and create the user interface this also implement the actionlister 
 * itemlister, and runable interfcae.  
 * @author Paras
 *
 */
public class Client extends JFrame implements ActionListener,ItemListener,Runnable
{
   
      private Toolkit toolkit = Toolkit.getDefaultToolkit();
      private Dimension screensize = toolkit.getScreenSize();
   //   private int width= screensize.width,height=screensize.height;

	
	private JLabel label;

	private JMenuBar menubar;
	private JMenu menu;
	private JMenuItem l_item,e_item;

	private String _username,_password,_server,_address;

    private static Client frame;
    Container container;
    private JComboBox combo;
    private UserTreePanel panel;  // UserTreePanel class
    Thread thread;
    Socket socket;
    private boolean connected = false;
		private boolean done=false;
		User user;
		private DataInputStream dis;
		private DataOutputStream dos;
		public static Vector _userlist = new Vector();
		ChatDialog dialog ; // ChatDialog class
		public static Hashtable frameTable = new Hashtable();
		PrintWriter pr;

/**
 * This method is invoked when the exit menu item is clicked and when existing the message header is 
 * set to variable 2 to indicate that the user is logout and this information is send to server can send the 
 * information out user logout to every connected user. 
 *
 */		
private void sendLogout()
	{
		try {
			Message message = new Message(2);
			user.isOnline = 2;
			message._user = user;
			sendMessageToServer(message);
		}
		catch(Exception e) { System.exit(0);}
	}

/**
 * The user status information is changed and this changed status is send to server class witht the message header 
 * 7 to indicate change in status   
 * @param status the inter value to represent the status 
 * 					Status 1- online
 * 					Status 2 -offline
 * 					status 3- busy
 * 					status 4- set ideal
 */
private void sendStatus(int status)
	{
		try {
			switch(status) {
				case 0:
					status = 1;//online
				break;
				case 1:
					status = 3;//busy
				break;
				case 2:
					status = 2;//offline
				break;
				default:
					status = 4;//idle
			}

			Message message = new Message(7);//7 idicates status has been changed
			user.isOnline = status;
			message._user = user;
			sendMessageToServer(message);
		}
		catch(Exception e) { System.exit(0);}
	}
/**
 * 
 * @param user
 */
public void removeFrame(User user)
	{
		synchronized(frameTable) {
			frameTable.remove(user.toString());
		}
	}
/**
 * when the chat between the two client is stated the chat frame method is invoked and this method calls the chat 
 * diaglogue to creat the chat frame 
 * 
 * @param user the user information 
 * @param diss dataInput stream 
 * @param doss dataoutput stream
 */
public void createFrame(User user,DataInputStream diss,DataOutputStream doss)
	{

				   dialog = (ChatDialog) frameTable.get(user.toString());
				   if(dialog == null) {
					dialog = new ChatDialog(this,user,dis,dos);
					dialog.setLocation(500, 500);
				}
				   frameTable.put(user.toString(),dialog);

					//getDispatcher().addObserver(dialog);

	}
/**
 *  The user information is provided when needed 
 * @return the current user information 
 */
public User getMe()
		{
			return user;
		}

/**
 * The constuctor method of the client class get the local host address and 
 * start the processand define the layout of the user interface window and 
 * generate the user interface 
 * @throws UnknownHostException
 */
public Client()throws UnknownHostException
{
           _address=InetAddress.getLocalHost().toString();
		     
		        frame = this;
		         container = this.getContentPane();
		  		container.setLayout(new FlowLayout());

		  
		 /** 
 * All the basic structure of the item for the client interface is drawn over here 
 */
		  		menubar= new JMenuBar(); // define menu bar and put item in it 
		  		menu = new JMenu("Login");
		  		l_item=new JMenuItem("Login");
		  		e_item=new JMenuItem("Exit");
		  		menu.add(l_item);
		  		menu.add(e_item);
		  		menubar.add(menu);

		   			this.setJMenuBar(menubar);


		  		l_item.addActionListener(this);
		  		e_item.addActionListener(this);

		  		label = new JLabel("Status");
		  		combo = new JComboBox();
		  		combo.addItem("I'm available");
		  		combo.addItem("Busy");
		  		combo.addItem("Invisible");
		  		combo.addItem("Away");
		  		combo.addItemListener(new ItemListener() {
		  			public void itemStateChanged(ItemEvent event){
		  				if(event.getStateChange() == ItemEvent.SELECTED) {
		  					sendStatus(((JComboBox)event.getSource()).getSelectedIndex());
		  				}
		  			}
		  		});

		  		this.addWindowListener(new WindowAdapter() {

		  			public void windowIconified(WindowEvent e)
		  			{
		  				
		  			}

		  			public void windowClosing(WindowEvent e)
		  			{
		  				if(JOptionPane.showConfirmDialog(container,
		  							"Are you sure you want to quit?",
		  							"Quit ",
		  							JOptionPane.OK_CANCEL_OPTION,
		  							JOptionPane.QUESTION_MESSAGE,
		  							null) == JOptionPane.YES_OPTION) {
		  					sendLogout();
		  				
		  					System.exit(0);
		  				}
		  			}
		  		});

		  		this.setSize(225, 400);
		  		this.setLocation(400,100);
		  		this.setVisible(true);
		  		this.setResizable(false);
		this.setTitle("Chat Client");
//after the creation of the frame login process is started
	processLogin();


}
/**
 * this methods create the login window for the client so that he can join the server and start the 
 * communication with the other user client. Here he has to specify the username, password, and server IP
 * and finally the initial connection messge is send to server.
 *
 */

public  void processLogin()
    {

				LoginDialog dialog = new LoginDialog(this);
				_username= dialog.getUserName();
				_password= dialog.getPassword();
		        _server = dialog.getServerHost();
     



				System.out.println("User Name="+_username);
				System.out.println("Password="+_password);
	            System.out.println("Host="+_server);

	            if(_username == null || _password==null)
	                return;
	            try
	            {
	            if((_username.length()!=0) || (_password.length() != 0))

	            {
	            	// user information is created and this information is send to the server   
	            user = (new User(LoginDialog.getUserName(),InetAddress.getLocalHost().toString(),1));

	            Message message = new Message(1);
				message._message = _password;
			    message._user = user;
			    socket = new Socket(_server, 2979); // server address and port to listern 
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());
				sendMessageToServer(message);
				connected = true;
			    thread = new Thread(this,"USER THREAD");
			    thread.start();
			     l_item.setEnabled(false);
			 }

			     }
                catch(Exception ee)
			    {
				  System.out.println(ee);
				}
			
		}
/**
 * Here the messgage object is send to the server with the defined server name and port number .
 * then this messgage is send to the server.
 * @param message the message object of the message class is send to the server
 * @throws Exception if there is errot in connection then the ecception is thrown 
 */
public synchronized void sendMessageToServer(Message message) throws Exception
	{
		message._user = user;
		byte[] data;
				//PACK THE USERNAME AND HOST ADDRESS EVERYTIME

				message._username = _username;
		         message._host = _address;
				data = ChatUtils.objectToBytes(message);
				dos.write(data,0,data.length);
		        dos.flush();

	System.out.println("Message Sent......"+message._message);

	}


/**
 * To run the thread create the thread and run until either connection variable is set to false and 
 * or the user is log out of the system.
 */

public void run()
    {

		while(connected && !done)
		{
			try {

                  		byte[] data;
				      	data = new byte[8192];
				  		System.out.println("Before Reading");
				  		dis.read(data);
				  		System.out.println("After Reading");
				  		Message message = (Message)ChatUtils.bytesToObject(data);
				  		System.out.println("Message Header="+message._header);
						String str;
						switch(message._header)
						{

                           case 15: // this block is used to send the file to the user when the
                        	   //client has to send the file it sefine its messgae header as 15
                          try
                          {

                          System.out.println("Before PrintWriter...");
                          pr=new PrintWriter(new FileWriter("C:///"+message._filename),true);
                          System.out.println("No Of Lines in a File="+message._filecontent.size());

                          Enumeration enum1=message._filecontent.elements();
                          while (enum1.hasMoreElements())
                          {

						     str=(String)enum1.nextElement();
						     pr.println(str);
						  }
					      pr.close();
                          dialog.appendData(message._user.toString(),"File Named  "+message._filename+"received successfully from "+message._user.toString()+"and is stored in C: drive",true);

					      }
					      catch(Exception er)
					      {
						    System.out.println(er);
						  }
                           break;

                           	// for initial login and communication with the server here user tree panel is used to make all the 
                           // changes in the client window
                              case 6:
							  	for(int cnt=0;cnt<message.userlist.size();cnt++)
							  					_userlist.add(message.userlist.elementAt(cnt).toString());

							      System.out.println("After For Loop");
							  				panel = new UserTreePanel(this,message.userlist,dis,dos,user);
							  				System.out.println("After Panel");
							  				panel.setPreferredSize(new Dimension(220,300));
							  				container.add(panel);
							  				container.add(label);
							  				container.add(combo);
							  				container.validate();
							  			break;

							  			case 1://LOGIN
							  				if(!message._username.equals(_username))
							  				_userlist.add(message._user.toString());
							  				
							  				panel.updateUser(message._user); 
							  				System.out.println("Login Message USer--"+message._user.toString());

							  			break;

							  			case 7://change status
							  				panel.updateUser(message._user);
							  			break;
							  			case 2://LOGOUT
							  				if(_userlist.contains(message._user.toString())) {
							  					_userlist.remove(message._user.toString());
							  					panel.removeUser(message._user);

							  				}

							  			case 10: // general communiation 

                                              createFrame(message._user,dis,dos);
							  			      //dialog = (ChatDialog) frameTable.get(user.toString());
							  			      System.out.println(message._user.toString()+"  Message-->"+message._message);
							  			      dialog.appendData(message._user.toString(),message._message,true);

							  			break;
			default:


						}
				}
			catch(Exception se) // if lofin error occures than the exception is thrown 
			   {
				      JOptionPane.showMessageDialog(frame,"Server connection reset!! Please login again","Talk2Me: Error",JOptionPane.ERROR_MESSAGE);
					  done = true;
			    	  System.out.println("Error:::"+se);
			   }

	   	}//END WHILE
	   
  
    }
/**
 * The client object is called and the defult constructor of the client runs.
 * @param args 
 * @throws Exception if there is a ptoblem in connection exception is thrown
 */

public static void main(String args[])throws Exception
{
Client obje=new Client();
}

public void actionPerformed(ActionEvent arg0) {
	// TODO Auto-generated method stub
	
}

public void itemStateChanged(ItemEvent arg0) {
	// TODO Auto-generated method stub
	
}

}


