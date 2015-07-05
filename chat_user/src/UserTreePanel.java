

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import javax.swing.tree.*;
import java.io.*;

/**
 * This class is used to handel the client window and make the necessay updates in the client window
 * It yses the Jtree to show the list of the user in the client window.
 * @author Paras
 *
 */ 
public class UserTreePanel extends JPanel
{
	private Vector _userlist ;
	private JTree usrtree;
	private DefaultTreeModel treeModel;
	private DefaultMutableTreeNode rootNode;
	private Client frame;
    private Hashtable nodeTable = new Hashtable();
    private UserTreePanel panel;
	private static final String libName = "client";
	private User user;

private DataInputStream dis;
private DataOutputStream dos;
/**
 * This constructor of userTreePanel class take the following parameter from the calling client class 
 * to make a client window, after initializing the readed parameter it invoke the initAwtContainer method 
 * to create necessary information 
 * @param frame It take the client frame of calling method
 * @param vector the number of available user connected 
 * @param dis the input data stream
 * @param dos takes output datastream
 * @param user the User object 
 * @throws Exception if error occure throw exception
 */
	public UserTreePanel(Client frame,Vector vector,DataInputStream dis,DataOutputStream dos,User user) throws Exception
	{
		this.frame = frame;
		this._userlist = vector;
           this.dis=dis;
           this.dos=dos;
           this.user=user;

		//frame.getDispatcher().addObserver(new DefaultObserver(frame));
		initAwtContainer();

	}
/**
 * this class adds the necessary component for the client interface and display the user list in his window.
 * @throws Exception
 */
	private void initAwtContainer() throws Exception
	{
		this.setLayout(new FlowLayout());

		//Create the nodes.
		rootNode = new DefaultMutableTreeNode("Joined User");
		createNodes(rootNode);

		//Create a tree that allows one selection at a time.
		treeModel = new DefaultTreeModel(rootNode);
		usrtree = new JTree(treeModel);
		usrtree.getSelectionModel().setSelectionMode
						(TreeSelectionModel.SINGLE_TREE_SELECTION);

		//Enable tool tips.
		ToolTipManager.sharedInstance().registerComponent(usrtree);

		System.out.println("ok1");
		usrtree.setCellRenderer(new MyRenderer());
		System.out.println("ok");
		usrtree.addMouseListener(new MyMouseAdapter(frame,usrtree,dis,dos,user));
		System.out.println("o2");
		JScrollPane scrollpane;
		scrollpane=new JScrollPane(usrtree,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		scrollpane.setPreferredSize(new Dimension(200,330));
		this.add(scrollpane);
		System.out.println("ok3");
	}
/**
 * This methods create the user node checking the userlist
 * @param top the parameter for the root node is needed
 */
   	private void createNodes(DefaultMutableTreeNode top) {
		DefaultMutableTreeNode user = null;

		for(int count = 0; count<_userlist.size();count++) {
			user = new DefaultMutableTreeNode((User)_userlist.elementAt(count));
			nodeTable.put(((User)_userlist.elementAt(count)).toString(),user);
        	top.add(user);
		}
    }

/**
 * this used to add user in the node 
 * @param child the user that is newly added
 */
	public void addUser(Object child)
	{
		DefaultMutableTreeNode childNode =
				new DefaultMutableTreeNode(child);
		treeModel.insertNodeInto(childNode,rootNode,rootNode.getChildCount());
		nodeTable.put(((User)child).toString(),childNode);
	}
/**
 * When the user exit from the system his information is to be deleted so this remove user 
 * delet the user and update the tree nodes
 * @param user
 */
	public void removeUser(User user)
	{
		MutableTreeNode node = (MutableTreeNode)nodeTable.get(user.toString());
		node.setUserObject(user);
		treeModel.reload(node);
	}
/**
 * when the new user join to the system his information is to be updated so this information is update to the 
 *  * @param user the new user object is needed to update teh tree model
 */
	public void updateUser(User user)
	{
		MutableTreeNode node;
		node = (MutableTreeNode)nodeTable.get(user.toString());
		if(node == null) {
			addUser(user);
			return;
		}
		node.setUserObject(user);
		treeModel.reload(node);
		nodeTable.put(user.toString(),node);
	}

}


/**
 * MyRenderer: Tree cell rendering class is used to change to the status of user like away, busy
 * 
 */


class MyRenderer extends DefaultTreeCellRenderer {
	final ClassLoader loader = ClassLoader.getSystemClassLoader();
	final ImageIcon rootIcon = new ImageIcon(loader.getResource("images/root.gif")),
					onlineIcon = new ImageIcon(loader.getResource("images/online.gif")),
					offlineIcon = new ImageIcon(loader.getResource("images/offline.gif")),
					busyIcon = new ImageIcon(loader.getResource("images/busy.gif")),
					idleIcon = new ImageIcon(loader.getResource("images/idle.gif"));

	public MyRenderer()
	{

	}
/**
 * Configures the renderer based on the passed in components. The value is set from messaging the tree with 
 * convertValueToText, which ultimately invokes toString on value. The foreground color is set based 
 * on the selection and the icon is set based on on leaf and expanded. 
 */
	public Component getTreeCellRendererComponent(
						JTree tree,
						Object value,
						boolean sel,
						boolean expanded,
						boolean leaf,
						int row,
						boolean hasFocus) {

		super.getTreeCellRendererComponent(
						tree, value, sel,
						expanded, leaf, row,
						hasFocus);


		if (leaf) {
			User user=getUser(value);
			switch(user.isOnline) {
				case 1 :
					setIcon(onlineIcon);//ONLINE USER
					setToolTipText("I am online @"+user.hostname);
					break;
				case 2:
					setIcon(offlineIcon);//OFFLINE
					setToolTipText("Offline");
					break;
				case 3 :
					setIcon(busyIcon);//BUSY USER
					setToolTipText("I am busy");
					break;
				case 4 :
					setIcon(idleIcon);//IDLE USER
					setToolTipText("Away from computer");
					break;
				default:
					setIcon(offlineIcon);//OFFLINE
					setToolTipText("Offline");
				}
		} else {
			setIcon(rootIcon);//ROOT
			setToolTipText(null);
		}

		return this;
	}
/**
 * This is method is used to get the information at the particular node 
 * @param the status 
 * @return the node information 
 */
	private User getUser(Object value)
	{
		DefaultMutableTreeNode node =
					(DefaultMutableTreeNode)value;
		User nodeInfo =
					(User)(node.getUserObject());
		return nodeInfo;
	}

}

/**
 * a
 * @author Paras
 *
 */

class MyMouseAdapter extends MouseAdapter
{
	private Client frame;
	private JTree tree;
DataInputStream dis;
DataOutputStream dos;
User user;
	public MyMouseAdapter(Client frame,JTree tree,DataInputStream dis,DataOutputStream dos,User user)
	{
		this.frame = frame;
		this.tree = tree;
		this.dis=dis;
		this.dos=dos;
		this.user=user;
	}

	public void mouseClicked(MouseEvent e) {
		int selRow = tree.getRowForLocation(e.getX(), e.getY());
		TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
//		ChatDialog dialog;
		DefaultMutableTreeNode node;
		if(selRow > 0 )
		{

			if(e.getClickCount() == 2) {
				node = (DefaultMutableTreeNode)selPath.getLastPathComponent();
				User user = (User)(node.getUserObject());
				if(user.isOnline != 2)
				{
                  System.out.println("UserName="+user.userName);
                  System.out.println("HostName="+user.hostname);


                   frame.createFrame(user,dis,dos);



				} else
				{
					JOptionPane.showMessageDialog(frame,
							"Click on an online user to send a message",
							"Error",JOptionPane.INFORMATION_MESSAGE);
				}




			}
		}
	}
}


