import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*; // For Database Access

public class AddUser extends JFrame implements ActionListener{
    
    JLabel id,pass,bg_image;
    JTextField tid,tpass;
    JButton add,exit;

    public AddUser(){
        Container c1=getContentPane();
        c1.setLayout(null);
	    
	        Color color1 = new Color(199,250,223);
		Color color2 = new Color(240,255,255);
		
		c1.setBackground(color1);
        
        id=new JLabel("User Name");
        c1.add(id);
        pass=new JLabel("Password");
        c1.add(pass);
        tid=new JTextField();
        c1.add(tid);
        tpass=new JPasswordField();
        c1.add(tpass);
	    
	    ImageIcon button1 = new ImageIcon("images/button1.png");
	    
        add=new JButton("Add",button1);
        c1.add(add);
        exit=new JButton("Close",button1);
        c1.add(exit);
	
	add.setHorizontalTextPosition(SwingConstants.CENTER);
	add.setBorderPainted(false);
	add.setContentAreaFilled(false);
	
	exit.setHorizontalTextPosition(SwingConstants.CENTER);
	exit.setBorderPainted(false);
	exit.setContentAreaFilled(false);
        
	    id.setBounds(20,20,70,25);
	    tid.setBounds(120,20,150,25);
	    pass.setBounds(20,65,70,25);
	    tpass.setBounds(120,65,150,25);
	    add.setBounds(40,110,100,30);
	    exit.setBounds(150,110,100,30);
        
	add.addActionListener(this);
	exit.addActionListener(this);
	
	
        this.setSize(300,200);
        this.setVisible(true);
        setTitle("Add User");
	this.setLocationRelativeTo(null);
	this.setResizable(false);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setIconImage(new ImageIcon("images/favicon.png").getImage());
    
		bg_image=new JLabel("");
		c1.add(bg_image);
		bg_image.setBounds(0,0,1360,683);
		bg_image.setIcon(new ImageIcon("images/image6.jpg"));
    }
    
    public void adduser(String pid,String pass){
	    
	    String dbName ="jdbc:mysql://localhost:3306/program_data";
		String dbUserName = "root";
		String dbPassword = ""; 
		
		String q="insert into login (id , pass) values ('" + pid + "','"+ pass + "')";
		
		try{
		    
		    Class.forName("com.mysql.jdbc.Driver");
		    Connection con = DriverManager.getConnection(dbName,dbUserName,dbPassword);
		    
		    Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		     
		    stm.executeUpdate(q);
			JOptionPane.showMessageDialog(null,"Product Successfully Inserted");
		    
		   
				stm.close();
				con.close(); 
		}catch(Exception e){
			JOptionPane.showMessageDialog(null,e);
		}
	    
	}
    
    public void actionPerformed(ActionEvent event1){
		if(event1.getSource()==add){
			String pid=tid.getText();
			String pass=tpass.getText();
			if(pid.equals("")){
				JOptionPane.showMessageDialog(null,"Enter User Name");
				}
			else if(pass.equals("")){
				JOptionPane.showMessageDialog(null,"Enter Password");
				}
			else{
				adduser(pid,pass);
			}
		}else 
		
		if(event1.getSource()==exit){
                    this.setVisible(false);
                }
	    
	}
    
   
}

