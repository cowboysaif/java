import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*; // For Database Access

public class AddProduct extends JFrame implements ActionListener{

    JLabel pid,pname,unit_buy_price,unit_sell_price,quantity,date,bg_image;
    JTextField tpid,tpname,tunit_buy_price,tunit_sell_price,tquantity,tdate;
    JButton add,exit,date2;
    
	int pid5=100;
    public AddProduct(){
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
	    tdate.setEditable(false);
	
	ImageIcon button1 = new ImageIcon("images/button1.png");
	    
        add=new JButton("Add",button1);
        exit=new JButton("Close",button1);
	
		ImageIcon calendar = new ImageIcon("images/calendar.png");
		date2=new JButton("",calendar);
		con.add(date2);
		
		date2.setBorderPainted(false);
		date2.setContentAreaFilled(false);
		date2.setHorizontalTextPosition(SwingConstants.CENTER);
        
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
        con.add(add);
        con.add(exit);
	
	add.setHorizontalTextPosition(SwingConstants.CENTER);
	add.setBorderPainted(false);
	add.setContentAreaFilled(false);
	
	exit.setHorizontalTextPosition(SwingConstants.CENTER);
	exit.setBorderPainted(false);
	exit.setContentAreaFilled(false);
	
        
        setTitle("Add Product");
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
        add.setBounds(40,320,100,30);
        exit.setBounds(160,320,100,30);
	date2.setBounds(239,249,25,25);
        
        add.addActionListener(this);
        exit.addActionListener(this);
	date2.addActionListener(this);
	this.setIconImage(new ImageIcon("images/favicon.png").getImage());
	
		bg_image=new JLabel("");
		con.add(bg_image);
		bg_image.setBounds(0,0,1360,683);
		bg_image.setIcon(new ImageIcon("images/image6.jpg"));
		
		
		String dbName ="jdbc:mysql://localhost:3306/program_data";
		String dbUserName = "root";
		String dbPassword = ""; 
		
		String d="Select * from product";
		
		
		try{
		    
		    Class.forName("com.mysql.jdbc.Driver");
		    Connection con1 = DriverManager.getConnection(dbName,dbUserName,dbPassword);
			     
		    Statement stm=con1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		     
			ResultSet rsd=stm.executeQuery(d);
			
			while(rsd.next())
			{
				String n1=rsd.getString("id");
				int product_id=Integer.parseInt(n1);
					pid5=product_id;
			}
			
				rsd.close();
				stm.close();
				con1.close(); 
			
		 
		}catch(Exception e){
		    System.out.println(e);     
		}finally{
		 
		}
		
		
		int pid0=pid5+1;
		String pid1=""+pid0;
		tpid.setText(pid1);
		tpid.setEditable(false);
    }
    
    void addproduct(String pid,String pname,String buy_price,String sell_price,String pquantity,String date){
        String dbName ="jdbc:mysql://localhost:3306/program_data";
        String dbUserName = "root";
        String dbPassword = ""; 
        
        String q="insert into product (id , name, unit_buy_price , unit_sell_price, quantity,date ) values ('" + pid + "','"+ pname + "','"+ buy_price + "','"+ sell_price + "','"+ pquantity + "','"+ date + "')";
        
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
		
		String pid=tpid.getText();
		String pname=tpname.getText();
		String buy_price=tunit_buy_price.getText();
		String sell_price=tunit_sell_price.getText();
		String pquantity=tquantity.getText();
		String date=tdate.getText();
		
		
		
				String dbName ="jdbc:mysql://localhost:3306/program_data";
				String dbUserName = "root";
				String dbPassword = ""; 
				
				String q="Select * from product where name='"+pname+"'";
				
				try{
				    
				    Class.forName("com.mysql.jdbc.Driver");
				    Connection con = DriverManager.getConnection(dbName,dbUserName,dbPassword);
				    
				    Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				     
					ResultSet rs=stm.executeQuery(q); 
					if(rs.next()){
					JOptionPane.showMessageDialog(null,"Product Exist. Try new name.");
					}else 
					if(pid.equals("")){
						JOptionPane.showMessageDialog(null,"Enter ID");
						}
						else if(pname.equals("")){
						JOptionPane.showMessageDialog(null,"Enter Name");
						}
						else if(buy_price.equals("")){
						JOptionPane.showMessageDialog(null,"Enter Buy Price");
						}
						else if(sell_price.equals("")){
						JOptionPane.showMessageDialog(null,"Enter Sell Price");
						}
						else if(pquantity.equals("")){
						JOptionPane.showMessageDialog(null,"Enter Quantity");
						}
						else if(date.equals("")){
						JOptionPane.showMessageDialog(null,"Enter Date");
						}else{
						addproduct(pid,pname,buy_price,sell_price,pquantity,date);
							this.setVisible(false);
							AddProduct p1=new AddProduct();
						}
				    
				   
						stm.close();
						con.close(); 
				
			}catch(Exception e){
					JOptionPane.showMessageDialog(null,e);
				
			}	
		}
	
        
        else if(event1.getSource()==exit){
            this.setVisible(false);
        }
	else if(event1.getSource()==date2){
           tdate.setText(new DatePicker(this).setPickedDate());
        }
    }
    
    
    
    class DatePicker {
        int month = java.util.Calendar.getInstance().get(java.util.Calendar.MONTH);
        int year = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);;
        JLabel l = new JLabel("", JLabel.CENTER);
        String day = "";
        JDialog d;
        JButton[] button = new JButton[49];

        public DatePicker(JFrame parent) {
                d = new JDialog();
                d.setModal(true);
                String[] header = { "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat" };
                JPanel p1 = new JPanel(new GridLayout(7, 7));
                p1.setPreferredSize(new Dimension(430, 120));

                for (int x = 0; x < button.length; x++) {
                        final int selection = x;
                        button[x] = new JButton();
                        button[x].setFocusPainted(false);
                        button[x].setBackground(Color.white);
                        if (x > 6)
                                button[x].addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent ae) {
                                                day = button[selection].getActionCommand();
                                                d.dispose();
                                        }
                                });
                        if (x < 7) {
                                button[x].setText(header[x]);
                                button[x].setForeground(Color.red);
                        }
                        p1.add(button[x]);
                }
                JPanel p2 = new JPanel(new GridLayout(1, 3));
                JButton previous = new JButton("<< Previous");
                previous.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                                month--;
                                displayDate();
                        }
                });
                p2.add(previous);
                p2.add(l);
                JButton next = new JButton("Next >>");
                next.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                                month++;
                                displayDate();
                        }
                });
                p2.add(next);
                d.add(p1, BorderLayout.CENTER);
                d.add(p2, BorderLayout.SOUTH);
                d.pack();
                d.setLocationRelativeTo(parent);
                displayDate();
                d.setVisible(true);
        }

        public void displayDate() {
                for (int x = 7; x < button.length; x++)
                        button[x].setText("");
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                                "MMMM yyyy");
                java.util.Calendar cal = java.util.Calendar.getInstance();
                cal.set(year, month, 1);
                int dayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);
                int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
                for (int x = 6 + dayOfWeek, day = 1; day <= daysInMonth; x++, day++)
                        button[x].setText("" + day);
                l.setText(sdf.format(cal.getTime()));
                d.setTitle("Date Picker");
        }

        public String setPickedDate() {
                if (day.equals(""))
                        return day;
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                                "dd-MM-yyyy");
                java.util.Calendar cal = java.util.Calendar.getInstance();
                cal.set(year, month, Integer.parseInt(day));
                return sdf.format(cal.getTime());
        }
}

    
}
