import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*; // For Database Access

public class DeleteProduct extends JFrame implements ActionListener{

    JLabel pid,pname,unit_buy_price,unit_sell_price,quantity,date,bg_image;
    JTextField tpid,tpname,tunit_buy_price,tunit_sell_price,tquantity,tdate;
    JButton delete,show;
    
    public DeleteProduct(){
        Container con=getContentPane();
        con.setLayout(null);
	    
		Color color1 = new Color(199,250,223);
		Color color2 = new Color(240,255,255);
		
		con.setBackground(color1);
        
        pid=new JLabel("Product ID");
        tpid=new JTextField();
	    tpid.setBackground(color2);
        pname=new JLabel("Product Name");
        tpname=new JTextField();
	    tpname.setBackground(color2);
        unit_buy_price=new JLabel("Unit Buy Price");
        tunit_buy_price=new JTextField();
	    tunit_buy_price.setBackground(color2);
        unit_sell_price=new JLabel("Unit Sell Price");
        tunit_sell_price=new JTextField();
	    tunit_sell_price.setBackground(color2);
        quantity=new JLabel("Product Quantity");
        tquantity=new JTextField();
	    tquantity.setBackground(color2);
	date=new JLabel("Date");
	tdate=new JTextField();
	    tdate.setBackground(color2);
	    
	    ImageIcon button1 = new ImageIcon("images/button1.png");
	    
        delete=new JButton("Delete",button1);
        show=new JButton("Show",button1);
        
        con.add(pid);
        con.add(tpid);
        con.add(pname);
        con.add(tpname);
        con.add(unit_buy_price);
        con.add(tunit_buy_price);
        con.add(unit_sell_price);
        con.add(tunit_sell_price);
        con.add(quantity);
        con.add(tquantity);
	con.add(date);
	con.add(tdate);
        con.add(delete);
        con.add(show);
	
	delete.setHorizontalTextPosition(SwingConstants.CENTER);
	delete.setBorderPainted(false);
	delete.setContentAreaFilled(false);
	
	show.setHorizontalTextPosition(SwingConstants.CENTER);
	show.setBorderPainted(false);
	show.setContentAreaFilled(false);
        
        setTitle("Delete Product");
        this.setSize(600,500);
        this.setVisible(true);
	this.setLocationRelativeTo(null);
	this.setResizable(false);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        pid.setBounds(40,50,150,25);
        tpid.setBounds(160,50,130,25);
        pname.setBounds(40,90,150,25);
        tpname.setBounds(160,90,200,25);
        unit_buy_price.setBounds(40,130,150,25);
        tunit_buy_price.setBounds(160,130,80,25);
        unit_sell_price.setBounds(40,170,150,25);
        tunit_sell_price.setBounds(160,170,80,25);
        quantity.setBounds(40,210,150,25);
        tquantity.setBounds(160,210,50,25);
        date.setBounds(40,250,150,25);
	tdate.setBounds(160,250,80,25);
        delete.setBounds(40,320,100,30);
        show.setBounds(160,320,100,30);
        
        delete.addActionListener(this);
        show.addActionListener(this);
	this.setIconImage(new ImageIcon("images/favicon.png").getImage());
	
		bg_image=new JLabel("");
		con.add(bg_image);
		bg_image.setBounds(0,0,1360,683);
		bg_image.setIcon(new ImageIcon("images/image6.jpg"));
    
    }
    
    void showproduct(String pid,String pname,String buy_price,String sell_price,String pquantity,String edate){
        String dbName ="jdbc:mysql://localhost:3306/program_data";
        String dbUserName = "root";
        String dbPassword = ""; 
        
        String p="Select * from product where id='"+pid+"'";
	    
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbName,dbUserName,dbPassword);
            
            Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
             
	    ResultSet rs=stm.executeQuery(p);
		
		if(rs.next()){
                String n=rs.getString("id");
                String a = rs.getString("name");
		String b = rs.getString("unit_buy_price");
		String c = rs.getString("unit_sell_price");
		String d = rs.getString("quantity");
		String e=rs.getString("date");
			
                
			tpid.setText(n);
			tpname.setText(a);
			tunit_buy_price.setText(b);
			tunit_sell_price.setText(c);
			tquantity.setText(d);
			tdate.setText(e);
                
		}else{
			
			JOptionPane.showMessageDialog(null,"Product Not Found");
		}
            
			rs.close();
			stm.close();
			con.close(); 
        }catch(Exception e){
		JOptionPane.showMessageDialog(null,e);
        }
        
    }
    
    
    void deleteproduct(String pid){
        String dbName ="jdbc:mysql://localhost:3306/program_data";
        String dbUserName = "root";
        String dbPassword = ""; 
        
        String q="Delete from product where id='"+pid+"' ";
        
	    
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbName,dbUserName,dbPassword);
            
            Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
             
	    
            stm.executeUpdate(q);
		JOptionPane.showMessageDialog(null,"Product Successfully Deleted");
            
			stm.close();
			con.close(); 
        }catch(Exception e){
		JOptionPane.showMessageDialog(null,e);
        }
        
    }
    
    
    public void actionPerformed(ActionEvent event1){
        if(event1.getSource()==delete){
		String pid=tpid.getText();
		if(pid.equals("")){
			JOptionPane.showMessageDialog(null,"Enter Product ID");
			}
		else{
			deleteproduct(pid);
		}
		
		tpname.setText("");
		tunit_buy_price.setText("");
		tunit_sell_price.setText("");
		tquantity.setText("");
		tdate.setText("");
		
        }
        else if(event1.getSource()==show){
		String pid=tpid.getText();
		String pname=tpname.getText();
		String buy_price=tunit_buy_price.getText();
		String sell_price=tunit_sell_price.getText();
		String pquantity=tquantity.getText();
		String edate=tdate.getText();
		
		showproduct(pid,pname,buy_price,sell_price,pquantity,edate);
        }
    }
public static void main(String args[]){
        DeleteProduct dp1=new DeleteProduct();
    }
    
}
