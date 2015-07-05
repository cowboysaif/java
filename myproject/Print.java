import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;
import java.sql.*; // For Database Access

public class Print{
	static JFrame frmMain;
	static Container con;
	
	JLabel label1,logo,company_name;
	JTextField t1;
	
	public Print(){
		
		frmMain=new JFrame ("Printing Details");
		frmMain.setSize(600, 700);
		con = frmMain.getContentPane();
		con.setLayout(null);
		
		label1=new JLabel("Print This");
		con.add(label1);
		label1.setBounds(40,40,100,25);
		
		logo=new JLabel("");
		con.add(logo);
		logo.setBounds(250,10,100,100);
		logo.setIcon(new ImageIcon("images/refresh.png"));
		
		t1=new JTextField();
		con.add(t1);
		t1.setBounds(150,40,100,25);
		
		frmMain.setVisible(true);
		
		int inv=0;
		String dbName ="jdbc:mysql://localhost:3306/program_data";
		String dbUserName = "root";
		String dbPassword = ""; 
		
		String p="Select * from sell_product";
		    
		try{
		    
		    Class.forName("com.mysql.jdbc.Driver");
		    Connection con = DriverManager.getConnection(dbName,dbUserName,dbPassword);
		    
		    Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		     
		    ResultSet rs=stm.executeQuery(p);
			
			while(rs.next()){
				String n1=rs.getString("invoice");
				int s1=Integer.parseInt(n1);
				inv=s1;
			
			}
			String invoice=""+inv;
			t1.setText(invoice);
			
				rs.close();
				stm.close();
				con.close(); 
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,e);
		}
		
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	public static void main (String[] args){
		
		Print p1=new Print();
		
   
      }
	
}