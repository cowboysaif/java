import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*; // For Database Access

public class AddCustomer extends JFrame implements ActionListener{
    JLabel name,address,phone,bg_image;
    JTextField tname,taddress,tphone;
    JButton add,reset;
    
    public AddCustomer(){
    
        Container c1=getContentPane();
        c1.setLayout(null);
	    
		Color color1 = new Color(199,250,223);
		Color color2 = new Color(240,255,255);
		
		c1.setBackground(color1);
        
        name=new JLabel("Name");
        address=new JLabel("Address");
        phone=new JLabel("Phone");
        tname=new JTextField();
        taddress=new JTextField();
        tphone=new JTextField();
	    ImageIcon button1 = new ImageIcon("images/button1.png");
	    
        add=new JButton("Add Customer",button1);
	reset=new JButton("Reset",button1);
        
        c1.add(name);
        c1.add(address);
        c1.add(phone);
        c1.add(tname);
        c1.add(taddress);
        c1.add(tphone);
        c1.add(add);
	c1.add(reset);
	
	add.setHorizontalTextPosition(SwingConstants.CENTER);
	add.setBorderPainted(false);
	add.setContentAreaFilled(false);
	
	reset.setHorizontalTextPosition(SwingConstants.CENTER);
	reset.setBorderPainted(false);
	reset.setContentAreaFilled(false);
        
        name.setBounds(40,20,120,25);
        tname.setBounds(180,20,150,25);
        address.setBounds(40,60,120,25);
        taddress.setBounds(180,60,180,25);
        phone.setBounds(40,100,120,25);
        tphone.setBounds(180,100,150,25);
        add.setBounds(40,180,130,30);
	reset.setBounds(210,180,100,30);
        
        add.addActionListener(this);
	reset.addActionListener(this);
        
        setTitle("Add Customer");
        this.setSize(600,500);
        this.setVisible(true);
	this.setLocationRelativeTo(null);
	this.setResizable(false);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setIconImage(new ImageIcon("images/favicon.png").getImage());
	
		bg_image=new JLabel("");
		c1.add(bg_image);
		bg_image.setBounds(0,0,1360,683);
		bg_image.setIcon(new ImageIcon("images/image6.jpg"));
        
    }
    void addcustomer(String name,String add,String phone){
        String dbName ="jdbc:mysql://localhost:3306/program_data";
        String dbUserName = "root";
        String dbPassword = ""; 
        
        String q="insert into customer (name, address , phone) values ('" + name + "','"+ add + "','"+ phone + "')";
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbName,dbUserName,dbPassword);
            
            Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
             
            stm.executeUpdate(q);
		JOptionPane.showMessageDialog(null,"Customer Successfully Added");
            
           
			stm.close();
			con.close(); 
        }catch(Exception e){
		JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void actionPerformed(ActionEvent event){
    
        if(event.getSource()==add){
            String name=tname.getText();
            String add=taddress.getText();
            String phone=tphone.getText();
		
		if(name.equals("")){
			JOptionPane.showMessageDialog(null,"Enter Customer Name");
			}
			else if(add.equals("")){
			JOptionPane.showMessageDialog(null,"Enter Customer Address");
			}
			else if(phone.equals("")){
			JOptionPane.showMessageDialog(null,"Enter Customer Phone Number");
			}else{
				addcustomer(name,add,phone);
			}
        }else
	if(event.getSource()==reset){
		tname.setText("");
		taddress.setText("");
		tphone.setText("");
	}
    }
   
    public static void main(String[] args) {
        AddCustomer c1=new AddCustomer();
    }
}
