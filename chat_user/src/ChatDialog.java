
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.Timer;
import javax.swing.tree.*;
import javax.swing.text.html.*;
import java.io.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;
import java.beans.*;
/**
 * This class is used to creat the chat dialogue window with which  the client make comunication with each other and 
 * exchange the file this class extend the Jframe class and implement actionlistner interface
 * @author Paras
 *
 */

public class ChatDialog extends JFrame implements ActionListener
{
	// variable decleration 
	JFileChooser choose; // used to choose file to send to the user
	JMenu file;
	JMenuItem sendd;
	JMenuBar menubar;
	private Client frame;
	private ChatDialog thisframe;
	private Container container;
	private JEditorPane recv;
	private JTextArea type;
	private JButton send;
	private User user;
	private int hwnd;
	private Timer timer=null;
	boolean isFocused = false;

//	private int w,h;

private DataInputStream dis;
private DataOutputStream dos;

/**
 * the constructor class 
 * @param frame - the client frame 
 * @param user- the user object 
 * @param dis provide the DataInputstream
 * @param dos provide the DataInputstream
 */
	public ChatDialog(Client frame,User user,DataInputStream dis,DataOutputStream dos)
	{
		this.frame = frame;
		this.user = user;
		initAwtContainer();
		this.dis=dis;
		this.dos=dos;
	}
/**
 * The initAwtcontainer container class defines the properties of the client dialogue 
 * class this define the text area and the display area so the client can write message 
 *
 */
	public void initAwtContainer()
	{
		thisframe = this;
		container= this.getContentPane();
		container.setLayout(null);
// the display text of the chat sesssion is prepared
	recv = new JEditorPane();
		recv.setEditorKit(new HTMLEditorKit());
	
		recv.setEditable(false);

		JScrollPane pane
			= new JScrollPane(recv,
					JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		pane.setBounds(10,10,290,100);

		type = new JTextArea();
		type.setFont(new Font("Arial",Font.PLAIN,11));
		type.setLineWrap(true);

		JScrollPane typepane
			= new JScrollPane(type,
					JScrollPane.VERTICAL_SCROLLBAR_NEVER,
					JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		typepane.setBounds(10,120,220,50);


		send = new JButton("Send");
		send.setBounds(235,120,65,50);
		send.addActionListener(this);

		container.add(pane);
		container.add(typepane);
		container.add(send);
/**
 * type.addKeyListnet listen for the key types it enter is press the message is send to the server
 * with the necessar object  for communication
 * other wise if escape is press the chatdialogue frame is set as invisible and 
 */
		type.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke)
			{
				if(ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
					setVisible(false);
					frame.removeFrame(user);
				} else if(ke.getKeyCode() == KeyEvent.VK_ENTER) {
					if(type.getText().length() == 0) return;
					appendData(frame.getMe().toString(),type.getText(),false);
					Message mymessage = new Message(10,type.getText());
					mymessage._destination = user.toString();

					try {


						       System.out.println("Message Destination="+user.toString()+"\nMessage="+mymessage._message);
						       frame.sendMessageToServer(mymessage);

						       
					}
					catch(Exception e) {
						JOptionPane.showMessageDialog(container,
							"Error sending message! Please try again",
							"Error",JOptionPane.ERROR_MESSAGE);
						}
					type.setText("");
				}
			}
		});


	/*	type.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent ke)
			{
				if(ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
					setVisible(false);
					frame.removeFrame(user);
				}
			}
		});*/


		send.addKeyListener(new KeyAdapter() {
					public void keyPressed(KeyEvent ke)
					{
						if(ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
							setVisible(false);
							frame.removeFrame(user);
						}
					}
		});

		recv.addMouseListener(new MouseInputAdapter() {
			public void mouseClicked(MouseEvent me) {
				isFocused = true;
				if(timer != null)timer.stop();
			}
		});

		this.setResizable(false);
		this.setSize(310,230);
		this.setTitle(user+" - Message");
		this.setLocation(300,300);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e)
			{
				setVisible(false);
				if(timer != null) timer.stop();
				frame.removeFrame(user);
			}

			public void windowActivated(WindowEvent ae) {
				isFocused = true;
				if(timer != null) timer.stop();
			}

			public void windowDeactivated(WindowEvent ae) {
				isFocused = false;
			}
    		public void windowOpened( WindowEvent e ){
    		    type.requestFocus();
    	    }
		});

		menubar=new JMenuBar();
		file=new JMenu("File");
		sendd=new JMenuItem("Send");
		sendd.addActionListener(this);
		file.add(sendd);
		menubar.add(file);
		setJMenuBar(menubar);
		this.setVisible(true);
		type.requestFocus();

		//sun.awt.SunToolkit tk = (sun.awt.SunToolkit)Toolkit.getDefaultToolkit();
		//hwnd = tk.getNativeWindowHandleFromComponent(this);
		timer = new Timer(500,new FlashwindowListener(thisframe));
		isFocused = false;
	}


	public void startFlashing()
	{
		isFocused = false;
		timer.start();
	}

	public void update(Object object)
	{
		Message message = (Message)object;
		if(!message._username.equals(user.toString()))
			return;
		switch(message._header)	{
			case 2://CLIENT_LOGOUT
				appendData(null,
					"<FONT COLOR='red' STYLE='font-size:10pt;font-family:Arial'><b>"+user+" logged off at "+(new Date())+"<b></font>",
					false);
				type.setEnabled(false);
				send.setEnabled(false);
			break;
		/*	case 4://PRIVATE_CHAT
			//	appendData(user.toString(),message.getMessage(),true);
				//if(!isFocused) timer.start();
			break;*/
		}
	}


	public String toString()
	{
		return user.toString();
	}

	public void appendData(String user,String str,boolean received)
	{
//		StringBuffer  bfr= new StringBuffer(str);

		if(user != null) {
			if(received) {
				str ="<FONT COLOR='red' STYLE='font-size:10pt;font-family:Arial'>"+user+": </FONT><FONT STYLE='font-size:10pt;font-family:Arial'>"+str;
			} else {
				str ="<FONT COLOR='blue' STYLE='font-size:10pt;font-family:Arial'>"+user+": </FONT><FONT STYLE='font-size:10pt;font-family:Arial'>"+str;
			}
		} else {
			str ="<FONT COLOR='red' STYLE='font-size:10pt;font-family:Arial'><B>"+str;
		}

		str+="</FONT>";//Line break

		try {
		((HTMLEditorKit)recv.getEditorKit()).read(new java.io.StringReader(str),
		 						recv.getDocument(), recv.getDocument().getLength());
		 recv.setCaretPosition(recv.getDocument().getLength());
	 	} catch(Exception e){}
	}


/**
 * this method look after the send of text containt or the file. To send text it just take the text messgage put the header 
 * ie message header 10 and send the message object to the server.
 * when the file is to be send at first the curent directory loction of the file is invoked then it take the file name 
 * and path is readed the header of the file send mode is define and the file containt is place in the vector os the 
 * vector is initialized then the file is readed and placed in the vector then the message object is created and send to 
 * server.
 */ 
	public void actionPerformed(ActionEvent event)
	{

		if(event.getSource()==sendd)
		{
		  Vector myvec=new Vector();

		  BufferedReader br;
		  String line;
		  choose=new JFileChooser();
		  choose.setCurrentDirectory(new File("."));
		  int result=choose.showOpenDialog(this);
		  if(result==JFileChooser.APPROVE_OPTION)
		  {
		   String name=choose.getSelectedFile().getPath();
		  String nam=choose.getSelectedFile().getName();
		  System.out.println("File is-->"+nam);
		   Message mymessage=new Message(15);
		   mymessage._filecontent=new Vector();

		  try
		       {
		       		br=new BufferedReader(new FileReader(name));
		       	
		       		while((line=br.readLine())!=null)
		       		{
		  			  mymessage._filecontent.addElement(line);

		  			}
		  br.close();

		  //mymessage._filecontent=new Vector(myvec);
		  mymessage._destination=user.toString();
		  mymessage._filename=nam;


try {
					  System.out.println("Message Destination="+user.toString()+"\nMessage="+mymessage._message);
					  frame.sendMessageToServer(mymessage);
					   appendData(null,"<FONT COLOR='green' STYLE='font-size:10pt;font-family:Courier New'><b>"+user.toString()+" Send File "+name+"<b></font>",false);


					}
					catch(Exception e) {
						JOptionPane.showMessageDialog(container,
							"Error sending message! Please try again",
							"Error",JOptionPane.ERROR_MESSAGE);
						}

		  
		       }
		       catch(Exception er)
		       {

		  	    System.out.println(er);
		  	 }

		  }
			}

		if((event.getSource() == type)||(event.getSource() == send)) {
			if(type.getText().length() == 0) return;
			appendData(frame.getMe().toString(),type.getText(),false);
			Message mymessage = new Message(10,type.getText());
			mymessage._destination = user.toString();
			try {
				frame.sendMessageToServer(mymessage);
			}
			catch(Exception e) {
				System.out.println("Error sending message");
			}
			type.setText("");
		}
	}
}
//
//	FlashWindowListener: Class for flashing the msg window.
//						 Uses JNI call to achieve that
//

class FlashwindowListener implements ActionListener
{
	private Window chatwindow;
	private final native void flashWindow(Window chatwindow);

	public FlashwindowListener(Window window)
	{
		this.chatwindow = window;
	}

	public void actionPerformed(ActionEvent ae)
	{
		flashWindow(chatwindow);
	}
}