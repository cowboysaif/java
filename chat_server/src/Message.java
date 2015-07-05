
/**
 * This class handle all the users messgae. So that every commnication between the users just send the message as a 
 * objet to the server class. The server class decode this messgae object of the client class and send back the 
 * messgae object to the targetd client. Here all the necessay information for communication are hidden in the 
 * different variable of the message class.
 * 
 * @author Paras/ Muskan
 *
 */
public class Message implements java.io.Serializable
{
	public int 	  			_header; // the header information
	public String 			_username; //username 
	public String 			_destination; // the destination address 
	public String 			_message; // the message body
	public String 			_host;// the host address 
	public User 			_user; //user status fetched from user class
	public java.util.Vector userlist; // store user information  
	public String 			_data; //the information contant 
     public java.util.Vector _filecontent; 
     public String _filename;  
	public Message()
	{
		//EMPTY MESSAGE
	}
/**
 * constructor method that takes the header
 * @param header 
 */
	
	public Message(int header)
	{
		_header = header;
	}
/**
 * constructor of the messgae class to communicate 
 * @param header of the message
 * @param message the messgage body
 */
	public Message(int header,String message)
	{
		_header = header;
		_message=message;
	}
/**
 * The methods returns the message
 * @return the string message
 */
	public String getMessage()
	{
		return _message;
	}
}