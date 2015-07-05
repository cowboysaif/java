

import java.io.IOException;

/**
 * This class helps to convert the user messege to byte stream from oject stream or viceversa  
 * @author Paras /Muskan
 *
 */
public class ChatUtils
{
	/**
	 * This method converts the object type to the byte array to return 
	 * @param object get the instance of user provided object  
	 * @return the byte stream from the provided object
	 * @throws IOException 
	 */
	public static byte[] objectToBytes (Object object) throws IOException
	{
		java.io.ObjectOutputStream out;
		java.io.ByteArrayOutputStream bs;

		bs = new java.io.ByteArrayOutputStream ();
		out = new java.io.ObjectOutputStream (bs);
		out.writeObject (object);
		out.close ();

		return bs.toByteArray ();
	}
/**
 * This method convert the given byte stream to object type
 * @param bytes  the byte of user message is passed.
 * @return the object for the provided byte stream of data
 * @throws IOException
 * @throws ClassNotFoundException
 */
	public static Object bytesToObject (byte bytes[]) throws IOException,ClassNotFoundException
	{
		Object res;
		java.io.ObjectInputStream in;
		java.io.ByteArrayInputStream bs;

		bs = new java.io.ByteArrayInputStream (bytes);
		in = new java.io.ObjectInputStream(bs);
		res = in.readObject ();
		in.close ();
		bs.close ();
		return res;
	}
}