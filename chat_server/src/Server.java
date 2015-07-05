import java.io.*;
import java.net.*;
import java.util.*;
/**
 * 
 * Server.java
 * @author Paras Pant/ Muskan Regmi
 * Java project to fulfill the course requirement .
 * This server class help to manage the client and listen the client and does the operation
 *  
 *
 */
public class Server
{
/**
 * define a socket to accept the client to communicate ie listen for other to connect
 */
ServerSocket server;
/**
 * define a socket to accept communicate 
 */
Socket socket;
/**
 * the size of message to be send during communication is defined 
 */
byte[]data;
/**
 * Stores the socket information of every user when user is log in 
 */
private static Hashtable userTable = new Hashtable();
/**
 * It stores the object of User class 
 */
private static Hashtable userList = new Hashtable();
/**
 * The number of socket of the user is store 
 */
private static Vector list;
/**
 * The host ip is stored
 */
String hostname;
/** 
 * new instance of service class
  */
Service service;

/*
 * this is the constructor method of the server class which runs 
 * when the object of sever class is made in the main method 
 * it manage the over all activity and first listen to the client. when they are connected 
 */
public Server()
{

try
{
list=new Vector(10);// vector size is defined 
server=new ServerSocket(2979,10); // the port number and the length of queue is defined
while(true)
{

                          socket=server.accept(); // waiting for client to join 
                          if(socket!=null)
                          {
						  synchronized(list) // synchronized the client  
						  					      {
						  						     list.addElement(socket);
						  					     }


						  }
                          // communicate with client when they join at first to check the communication
                          DataInputStream dis=new DataInputStream(socket.getInputStream());
					      data = new byte[8192];
					      System.out.println("Before");
					      dis.read(data);
					      System.out.println("After");
					      // creat the user object 
					      Message message = ((Message)ChatUtils.bytesToObject(data));
					      hostname=message._host;
					      System.out.println("Joined client "+message._username+" at "+message._host+"...");
					      //update user table
					      synchronized(userTable)
					      {
						        userTable.put(message._username,socket);
					      }
					      //update userlist 
					      synchronized(userList)
					      {
					    	  userList.put(message._user.toString(),message._user);
					      }


        sendUserList(message);
        writeToClients(message);
        service = new Service(socket,hostname,message._user); // sevice class is called and a thread for user is 
        													 //created
	}

}
catch(Exception ee)
{
System.out.println(ee);
}



}
/** 
 * Send the userlist to all user
 * @param message message object is send
 */
public void sendUserList(Message message)
{
        int header;
        String destination;
        header=message._header;
        message._header=6;
        message.userlist=new Vector(userList.values());
        destination=message._destination;
        message._destination=message._username;
        writeToClient(message);


        message._header=header;
        message._destination=destination;



}
/**
 * server sends the message to all clients
 * @param message the message object is send
 */
public static void writeToClient(Message message)
	{
		Socket socket;//define socket
		byte[] data; // define size of the message 
		DataOutputStream dos; // define data output stream
		synchronized(userTable)
		{
			try {
				  socket = (Socket)userTable.get(message._destination);
				  dos=new DataOutputStream(socket.getOutputStream());
				  data=ChatUtils.objectToBytes(message);
				  dos.write(data,0,data.length);
                  System.out.println("Message="+message._message+"\n Message dEStination="+message._destination+"\nMessage User="+message._username);

			    }
			    catch(Exception e)
			    {
				   System.out.println("SEND EXCEPTION"+e);
			    }
		}
	}
/**
 * synchronized the communication between the users.
 * @param message the message object is send
 */
public static synchronized void writeToClients(Message message)
	{
		byte[] data;// define size of the message
		DataOutputStream dos;// define data output stream
		int count;
		for(count=0;count<list.size();count++)
		  {
			try {
				   dos=new DataOutputStream(((Socket)list.elementAt(count)).getOutputStream());
				   data=ChatUtils.objectToBytes(message);
				   dos.write(data,0,data.length);
			    }
			catch(Exception e) {
				System.out.println("Output exception");
			}
		}//END FOR
	System.out.println("Total no of Clients that received message so far="+count);

	}

/**
 * the main class of the server, default constructor of the Server class is called 
 * @param args
 */
public static void main(String args[])
{
   Server obj=new Server(); 

}

/**
 * proces the client status looking the information in the message object 
 * @param message the message object is send
 */
public static synchronized void processClientMessage(Message message)
	{
		switch(message._header) {

			case 7://status changed
				updateUser(message._user);
				writeToClients(message);
				break;

			case 2://CLIENT_LOGOUT
				removeUser(message._user);
				writeToClients(message);
				break;

            default:
				writeToClient(message);

		}
	}

/**
 * call for update the users information update it in a userlist 
 * @param user the User object is send. 
 */
public static void updateUser(User user)
	{
		//User myuser;
		synchronized(userList)// synchronised the user's information
		{
			userList.put(user.toString(),user);
		}
	}

/**
 * call for remove the user when user is logout of the system 
 * @param user the User's object is send. 
 */
public static synchronized void removeUser(User user)
	{
		try {
			Socket socket = (Socket)userTable.get(user.toString());
			list.removeElement(socket);
			userList.remove(user.toString());
			userTable.remove(user.toString());
		}
		catch(Exception e) {
			System.out.println("ERROR REMOVING SOCKET "+e);
		}
	}



}
/**
 * this class is defined to do the service and to create the new thread for 
 * every client when they are connected it implement the runable interface. 
 * @author Paras/ Muskan
 *
 */
class Service implements Runnable
{
	
	private DataInputStream dis;// create instanc of input data stream
	private Socket socket;// creat instance ofsocket object 
	private boolean done=false; 
	private Thread thread; // create instance os thread object 
	private String hostname; //define variable to take the hostname
	private User user; // create instance of user 
/**
 * 
 * @param _socket take the user connected socket 
 * @param _hostname take the hostname of the server computer 
 * @param user take the instance of user class
 */
	public Service(Socket _socket,String _hostname,User user)
	{
		try	{
				this.socket = _socket;
				this.hostname=_hostname;
				this.user = user;
				dis=new DataInputStream(socket.getInputStream());
				thread=new Thread(this,"SERVICE");
				thread.start();// thread is stared 
		    }
		    catch(Exception e)
		    {
			  System.out.println("service constructor"+e);
		    }
	 }
/**
 *the new thread for each connected client is creates and the job is done
 *untill the client logout of the system 
 */
	public void run()
	{
		byte[] data;
		while(!done)
		{
			try
			{
					data = new byte[8192];
					dis.read(data);
					Message message = ((Message)ChatUtils.bytesToObject(data));
					Server.processClientMessage(message);
			}
			catch(Exception e)
			{
					done = true;
					Server.removeUser(user);
					Message message = new Message(2);//2=offline
					user.isOnline = 2;//offline
					message._user = user;
					Server.writeToClients(message);
					System.out.println("Server Here"+e);
					try
					{
						socket.close();
					}
				    catch(Exception se)
					{
					System.out.println("ERROR CLOSING SOCKET "+se);
				    }
				
			}
		}//END WHILE
	}
}