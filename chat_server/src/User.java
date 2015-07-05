/**
 * the user detail are stored in this class 
 * @author Paras
 *
 */
public class User implements java.io.Serializable
{
	public String userName;
	public String hostname;
	public int isOnline;
	public boolean isConference=false;
/**
 * This constructor class initialized the user information
 * @param userName  the user name 
 * @param hostname the host name of the server 
 * @param isOnline user status information
 */
	public User(String userName,String hostname,int isOnline)
	{
		this.hostname=hostname;
		this.userName=userName;
		this.isOnline = isOnline;
	}

	public String toString() {
		return userName;
	}
}