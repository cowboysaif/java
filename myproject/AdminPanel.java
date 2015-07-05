import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*; // For Database Access
import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import javax.swing.border.TitledBorder;

public class AdminPanel extends JFrame implements ActionListener,ItemListener{
    JButton add_product,delete_product,update_product,add_customer,add_user,
	sale_product,accounts,exit,reset,refresh,settings,add_payment;

    JTextField tname,tid,tsell_price,tbuy_price,tquantity,tdate;
    JLabel bg_image,name,id,sell_price,buy_price,quantity,date,company_name;
	
	JComboBox combo;
   
    public AdminPanel(){
        Container c1=getContentPane();
        c1.setLayout(null);
	    
	    TitledBorder title;
	title = BorderFactory.createTitledBorder("title");
	    
	Color color1 = new Color(199,250,223);
	//c1.setBackground(color1);
	
	    
		JMenu user = new JMenu("  User  ");
		user.setMnemonic('F');
		JMenuItem adduser = new JMenuItem("    Add User",new ImageIcon("images/add_user.png"));
		adduser.setMnemonic('N');
		user.add(adduser);
		adduser.setAccelerator(KeyStroke.getKeyStroke('U', CTRL_DOWN_MASK));
	    
		JMenuItem seeuser = new JMenuItem("    See User Details               ",new ImageIcon("images/user_details.png"));
		seeuser.setMnemonic('O');
		user.add(seeuser);	
	    
		JMenuItem logout = new JMenuItem("    Log Out",new ImageIcon("images/Log_Out.png"));
		logout.setMnemonic('x');
		user.add(logout);
		logout.setAccelerator(KeyStroke.getKeyStroke('L', CTRL_DOWN_MASK));
		
		JMenuItem exit1 = new JMenuItem("    Exit",new ImageIcon("images/exit.png"));
		exit1.setMnemonic('x');
		user.add(exit1);
		exit1.setAccelerator(KeyStroke.getKeyStroke('E', CTRL_DOWN_MASK));
		
		JMenu customer = new JMenu(" Customer ");
		customer.setMnemonic('F');
		JMenuItem addcustomer = new JMenuItem("     Add Customer",new ImageIcon("images/add_customer.png"));
		addcustomer.setMnemonic('N');
		customer.add(addcustomer);
		addcustomer.setAccelerator(KeyStroke.getKeyStroke('C', CTRL_DOWN_MASK));
		
		JMenuItem seecustomer = new JMenuItem("     Customer Details               ",new ImageIcon("images/customer_details.png"));
		seecustomer.setMnemonic('O');
		customer.add(seecustomer);	
		
		JMenuItem acc = new JMenuItem("     Customer Account",new ImageIcon("images/customer_account.png"));
		acc.setMnemonic('x');
		customer.add(acc);
		acc.setAccelerator(KeyStroke.getKeyStroke('A', CTRL_DOWN_MASK));
		
		JMenu product = new JMenu(" Product ");
		product.setMnemonic('F');
		JMenuItem addproduct = new JMenuItem("     Add Product",new ImageIcon("images/add_product.png"));
		addproduct.setMnemonic('N');
		product.add(addproduct);
		addproduct.setAccelerator(KeyStroke.getKeyStroke('P', CTRL_DOWN_MASK));
		
		JMenuItem seeproduct = new JMenuItem("     Product Details               ",new ImageIcon("images/product_details.png"));
		seeproduct.setMnemonic('O');
		product.add(seeproduct);
		seeproduct.setAccelerator(KeyStroke.getKeyStroke('r', CTRL_DOWN_MASK));
		
		JMenuItem update = new JMenuItem("     update Product               ",new ImageIcon("images/update_product.png"));
		update.setMnemonic('O');
		product.add(update);
		
		JMenuItem deleteproduct = new JMenuItem("     Delete Product",new ImageIcon("images/delete_product.png"));
		deleteproduct.setMnemonic('x');
		product.add(deleteproduct);
		deleteproduct.setAccelerator(KeyStroke.getKeyStroke('s', CTRL_DOWN_MASK));
		
		JMenu sales = new JMenu(" Sales ");
		sales.setMnemonic('F');
		JMenuItem sellproduct = new JMenuItem("     Sell Product",new ImageIcon("images/sell_product.png"));
		sellproduct.setMnemonic('N');
		sales.add(sellproduct);
		sellproduct.setAccelerator(KeyStroke.getKeyStroke('p', CTRL_DOWN_MASK));
		
		JMenuItem sellsreport = new JMenuItem("     Sells Report               ",new ImageIcon("images/sales_report.png"));
		sellsreport.setMnemonic('O');
		sales.add(sellsreport);
		sellsreport.setAccelerator(KeyStroke.getKeyStroke('q', CTRL_DOWN_MASK));
		
		JMenu help = new JMenu(" Help ");
		help.setMnemonic('F');
		JMenuItem manual = new JMenuItem("     Manual",new ImageIcon("images/manual.png"));
		manual.setMnemonic('N');
		help.add(manual);
		JMenuItem about = new JMenuItem("     About Soft             ",new ImageIcon("images/about.png"));
		about.setMnemonic('O');
		help.add(about);
	    
		JMenuBar menu1 = new JMenuBar();
		c1.add(menu1);
		setJMenuBar(menu1);
		menu1.add(user);
		menu1.add(customer);
		menu1.add(product);
		menu1.add(sales);
		menu1.add(help);
		
		adduser.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					AddUser u1=new AddUser();
				}
			}
		);
		seeuser.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					
				}
			}
		);
		logout.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					
					Login l1=new Login();
				}
			}
		);
		exit1.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					System.exit(0);
				}
			}
		);
		addcustomer.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					AddCustomer a=new AddCustomer();
				}
			}
		);
		seecustomer.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					
				}
			}
		);
		acc.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					Accounts a1=new Accounts();
				}
			}
		);
		addproduct.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					AddProduct ap=new AddProduct();
				}
			}
		);
		seeproduct.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					UpdateProduct up=new UpdateProduct();
				}
			}
		);
		update.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					UpdateProduct up=new UpdateProduct();
				}
			}
		);
		deleteproduct.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					DeleteProduct dp=new DeleteProduct();
				}
			}
		);
		sellproduct.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					SellProduct sp=new SellProduct();
				}
			}
		);
		sellsreport.addActionListener(
			new ActionListener(){
				public void actionPerformed(ActionEvent e)
				{
					Accounts ac=new Accounts();
				}
			}
		);
	    
	//code for combobox starts from here
	String s1[] = null;
	String s[]=new String[20];
	    
	String dbName ="jdbc:mysql://localhost:3306/program_data";
        String dbUserName = "root";
        String dbPassword = ""; 
        
        
        String q="Select * from product";
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbName,dbUserName,dbPassword);
                     
            Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
             
            ResultSet rs=stm.executeQuery(q); 
             int i=0;
            while(rs.next())
                {
			s[i] = rs.getString("name");
			i++;
                }
			s1 = new String[i];
			for(int j=0;j<i;j++){
				s1[j] = s[j];
			}
			
			rs.close();
			stm.close();
			con.close(); 
		
         
        }catch(Exception e){
            System.out.println(e);     
        }finally{
         
        }
	Color color2 = new Color(240,255,255);
	
	combo=new JComboBox(s1);
	c1.add(combo);
	combo.setBackground(color2);
	
        
	combo.setSelectedIndex(0);
	combo.addItemListener(this);
	combo.addActionListener(this);
	
	//code for combo ends here
	    
	ImageIcon button = new ImageIcon("images/button.png");
	ImageIcon button1 = new ImageIcon("images/button1.png");
	ImageIcon refreshb = new ImageIcon("images/refresh.png");
	
        add_product=new JButton("Add Product",button);
        c1.add(add_product);
	add_product.setFont(new Font("Forte", Font.BOLD, 16));
	add_product.setForeground(Color.white);
	
        delete_product=new JButton("Delete Product",button);
        c1.add(delete_product);
	delete_product.setFont(new Font("Forte", Font.BOLD, 16));
	delete_product.setForeground(Color.white);
	
	update_product=new JButton("Update Product",button);
	c1.add(update_product);
	update_product.setFont(new Font("Forte", Font.BOLD, 16));
	update_product.setForeground(Color.white);
	
        add_customer=new JButton("Add Customer",button);
        c1.add(add_customer);
	add_customer.setForeground(Color.white);
	add_customer.setFont(new Font("Forte", Font.BOLD, 16));
	
        add_user=new JButton("Add User",button);
        c1.add(add_user);
	add_user.setForeground(Color.white);
	add_user.setFont(new Font("Forte", Font.BOLD, 16));
	
        sale_product=new JButton("Sale Product",button);
        c1.add(sale_product);
	sale_product.setForeground(Color.white);
	sale_product.setFont(new Font("Forte", Font.BOLD, 16));
	
        accounts=new JButton("Accounts",button);
        c1.add(accounts);
	accounts.setForeground(Color.white);
	accounts.setFont(new Font("Forte", Font.BOLD, 16));
	
        exit=new JButton("LogOut",button);
        c1.add(exit);
	exit.setForeground(Color.white);
	exit.setFont(new Font("Forte", Font.BOLD, 16));
	
	//show=new JButton("Show",button1);
	//c1.add(show);
	//show.setForeground(Color.white);
	//show.setFont(new Font("Forte", Font.BOLD, 16));
	
	reset=new JButton("Reset",button1);
	c1.add(reset);
	reset.setForeground(Color.white);
	reset.setFont(new Font("Forte", Font.BOLD, 16));
	
	refresh=new JButton("",refreshb);
	c1.add(refresh);
	
	settings=new JButton("Settings",button);
	c1.add(settings);
	settings.setForeground(Color.white);
	settings.setFont(new Font("Forte", Font.BOLD, 16));
	
	add_payment=new JButton("Add Payment",button);
	c1.add(add_payment);
	add_payment.setForeground(Color.white);
	add_payment.setFont(new Font("Forte", Font.BOLD, 16));
	
	settings.setHorizontalTextPosition(SwingConstants.CENTER);
	settings.setBorderPainted(false);
	settings.setContentAreaFilled(false);
	
	add_product.setHorizontalTextPosition(SwingConstants.CENTER);
	add_product.setBorderPainted(false);
	add_product.setContentAreaFilled(false);
	
	delete_product.setHorizontalTextPosition(SwingConstants.CENTER);
	delete_product.setBorderPainted(false);
	delete_product.setContentAreaFilled(false);
	
	update_product.setHorizontalTextPosition(SwingConstants.CENTER);
	update_product.setBorderPainted(false);
	update_product.setContentAreaFilled(false);
	
	add_customer.setHorizontalTextPosition(SwingConstants.CENTER);
	add_customer.setBorderPainted(false);
	add_customer.setContentAreaFilled(false);
	
	add_user.setHorizontalTextPosition(SwingConstants.CENTER);
	add_user.setBorderPainted(false);
	add_user.setContentAreaFilled(false);
	
	sale_product.setHorizontalTextPosition(SwingConstants.CENTER);
	sale_product.setBorderPainted(false);
	sale_product.setContentAreaFilled(false);
	
	accounts.setHorizontalTextPosition(SwingConstants.CENTER);
	accounts.setBorderPainted(false);
	accounts.setContentAreaFilled(false);
	
	exit.setHorizontalTextPosition(SwingConstants.CENTER);
	exit.setBorderPainted(false);
	exit.setContentAreaFilled(false);
	
	add_payment.setHorizontalTextPosition(SwingConstants.CENTER);
	add_payment.setBorderPainted(false);
	add_payment.setContentAreaFilled(false);
	
	//show.setHorizontalTextPosition(SwingConstants.CENTER);
	//show.setBorderPainted(false);
	//show.setContentAreaFilled(false);
	
	reset.setHorizontalTextPosition(SwingConstants.CENTER);
	reset.setBorderPainted(false);
	reset.setContentAreaFilled(false);
	
	refresh.setHorizontalTextPosition(SwingConstants.CENTER);
	refresh.setBorderPainted(false);
	refresh.setContentAreaFilled(false);
	
	add_product.setRolloverIcon(new ImageIcon("images/brollover.png"));
	delete_product.setRolloverIcon(new ImageIcon("images/brollover.png"));
	update_product.setRolloverIcon(new ImageIcon("images/brollover.png"));
	add_customer.setRolloverIcon(new ImageIcon("images/brollover.png"));
	add_user.setRolloverIcon(new ImageIcon("images/brollover.png"));
	sale_product.setRolloverIcon(new ImageIcon("images/brollover.png"));
	accounts.setRolloverIcon(new ImageIcon("images/brollover.png"));
	exit.setRolloverIcon(new ImageIcon("images/brollover.png"));
	settings.setRolloverIcon(new ImageIcon("images/brollover.png"));
	add_payment.setRolloverIcon(new ImageIcon("images/brollover.png"));
	
	add_product.setPressedIcon(new ImageIcon("images/bpressed.png"));
	delete_product.setPressedIcon(new ImageIcon("images/bpressed.png"));
	update_product.setPressedIcon(new ImageIcon("images/bpressed.png"));
	add_customer.setPressedIcon(new ImageIcon("images/bpressed.png"));
	add_user.setPressedIcon(new ImageIcon("images/bpressed.png"));
	sale_product.setPressedIcon(new ImageIcon("images/bpressed.png"));
	accounts.setPressedIcon(new ImageIcon("images/bpressed.png"));
	exit.setPressedIcon(new ImageIcon("images/bpressed.png"));
	settings.setPressedIcon(new ImageIcon("images/bpressed.png"));
	add_payment.setPressedIcon(new ImageIcon("images/bpressed.png"));
	
	name=new JLabel("Product Name");
	c1.add(name);
	name.setForeground(Color.white);
	
	
	company_name=new JLabel("Mahbub Enterprise");
	c1.add(company_name);
	company_name.setForeground(Color.white);
	company_name.setFont(new Font("Adobe Caslon Pro", Font.BOLD, 66));
	
	tname=new JTextField();
	c1.add(tname);
	
	tname.setBackground(color2);
	id=new JLabel("Product Id");
	c1.add(id);
	id.setForeground(Color.white);
	
	tid=new JTextField();
	c1.add(tid);
	tid.setBackground(color2);
	
	sell_price=new JLabel("Unit Sell Price");
	c1.add(sell_price);
	sell_price.setForeground(Color.white);
	
	tsell_price=new JTextField();
	c1.add(tsell_price);
	tsell_price.setBackground(color2);
	
	buy_price=new JLabel("Unit Buy Price");
	c1.add(buy_price);
	buy_price.setForeground(Color.white);
	
	tbuy_price=new JTextField();
	c1.add(tbuy_price);
	tbuy_price.setBackground(color2);
	
	quantity=new JLabel("Quantity Available");
	c1.add(quantity);
	quantity.setForeground(Color.white);
	
	tquantity=new JTextField();
	c1.add(tquantity);
	tquantity.setBackground(color2);
	
	date=new JLabel("Added Date");
	c1.add(date);
	date.setForeground(Color.white);
	
	tdate=new JTextField();
	c1.add(tdate);
	tdate.setBackground(color2);
	
	JLabel logo=new JLabel();
	c1.add(logo);
	JLabel logo_text=new JLabel("Coder_SK");
	c1.add(logo_text);
	JLabel bg=new JLabel();
	c1.add(bg);
	
	
        bg_image=new JLabel("");
        c1.add(bg_image);
	
	
	logo.setIcon(new ImageIcon("images/globe.png"));
	bg.setIcon(new ImageIcon("images/bg.png"));
	//bg_image.setIcon(new ImageIcon("images/sd.png"));
	
        add_product.addActionListener(this);
        delete_product.addActionListener(this);
	update_product.addActionListener(this);
        add_customer.addActionListener(this);
        add_user.addActionListener(this);
        sale_product.addActionListener(this);
        accounts.addActionListener(this);
        exit.addActionListener(this);
	//show.addActionListener(this);
	reset.addActionListener(this);
	refresh.addActionListener(this);
        settings.addActionListener(this);
	add_payment.addActionListener(this);
	
	company_name.setBounds(360,10,750,100);
	name.setBounds(240,190,150,25);
	tname.setBounds(240,215,150,25);
	id.setBounds(430,190,150,25);
	tid.setBounds(430,215,100,25);
	buy_price.setBounds(570,190,150,25);
	tbuy_price.setBounds(570,215,100,25);
	sell_price.setBounds(710,190,150,25);
	tsell_price.setBounds(710,215,100,25);
	quantity.setBounds(850,190,150,25);
	tquantity.setBounds(850,215,100,25);
	date.setBounds(990,190,150,25);
	tdate.setBounds(990,215,100,25);
	
	
        bg_image.setBounds(0,0,1360,683);
        add_product.setBounds(40,120,159,45);
        delete_product.setBounds(40,170,159,45);
	update_product.setBounds(40,220,159,45);
        add_customer.setBounds(40,270,159,45);
        add_user.setBounds(40,320,159,45);
        sale_product.setBounds(40,370,159,45);
        accounts.setBounds(40,420,159,45);
	settings.setBounds(40,470,159,45);
	add_payment.setBounds(40,570,159,45);
        exit.setBounds(40,520,159,45);
	combo.setBounds(240,120,180,30);
	//show.setBounds(430,120,100,30);
	reset.setBounds(430,120,100,30);
	refresh.setBounds(620,530,100,100);
	
	logo.setBounds(1200,20,100,100);
	logo_text.setBounds(1165,640,150,25);
	bg.setBounds(730,333,614,373);
        
	
			tname.setEditable(false);
			tid.setEditable(false);
			tbuy_price.setEditable(false);
			tsell_price.setEditable(false);
			tquantity.setEditable(false);
			tdate.setEditable(false);
        
        setTitle("Admin Panel");
        this.setSize(1355,730);
        this.setVisible(true);
        //this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setExtendedState(JFrame.MAXIMIZED_BOTH);
	setBackground();
	//this.setResizable(false);
	this.setIconImage(new ImageIcon("images/favicon.png").getImage());
    }
    
    public void itemStateChanged(ItemEvent e){
	    int selection;
	    selection=combo.getSelectedIndex();
	    if ( e.getStateChange() == ItemEvent.SELECTED ){
		    
		    Object item1=combo.getSelectedItem();
			String item=item1.toString();
		
		
			String dbName ="jdbc:mysql://localhost:3306/program_data";
			String dbUserName = "root";
			String dbPassword = ""; 
			
			String q="Select * from product where name='"+item+"'";
        
			try{
			    
			    Class.forName("com.mysql.jdbc.Driver");
			    Connection con = DriverManager.getConnection(dbName,dbUserName,dbPassword);
				     
			    Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			     
			    ResultSet rs=stm.executeQuery(q); 
			     int i=0;
			    while(rs.next())
				{
					String pname = rs.getString("name");
					String pid = rs.getString("id");
					String pbuy_price = rs.getString("unit_buy_price");
					String psell_price = rs.getString("unit_sell_price");
					String pquantity = rs.getString("quantity");
					String pdate = rs.getString("date");
					
					tname.setText(pname);
					tid.setText(pid);
					tbuy_price.setText(pbuy_price);
					tsell_price.setText(psell_price);
					tquantity.setText(pquantity);
					tdate.setText(pdate);
					
				}
					rs.close();
					stm.close();
					con.close(); 
				
			 
			}catch(Exception e1){
			    System.out.println(e1);     
			}finally{
			 
			}
			
			
		    }
	    }
    
    public void actionPerformed(ActionEvent event){
        if(event.getSource()==add_product){
		AddProduct p1=new AddProduct();	
		}else
	if(event.getSource()==delete_product){
		DeleteProduct d1=new DeleteProduct();	
		}else
	if(event.getSource()==update_product){
		UpdateProduct up1=new UpdateProduct();
		}else
	if(event.getSource()==add_customer){
		AddCustomer ac1=new AddCustomer();
		}else
	if(event.getSource()==add_user){
		AddUser u1=new AddUser();
		}else
	if(event.getSource()==sale_product){
		SellProduct sp1=new SellProduct();
		}else
	if(event.getSource()==accounts){
		Accounts a= new Accounts();
		}else
	if(event.getSource()==settings){
		 //this.setVisible(false);
		settings l1=new settings();
		}else
	if(event.getSource()==add_payment){
		Payment p= new Payment();
		}else
	if(event.getSource()==exit){
		 this.setVisible(false);
		Login l1=new Login();
		}else
	if(event.getSource()==refresh){
		 this.setVisible(false);
		AdminPanel ap1=new AdminPanel();
		}else
	if(event.getSource()==reset){
					tname.setText("");
					tid.setText("");
					tbuy_price.setText("");
					tsell_price.setText("");
					tquantity.setText("");
					tdate.setText("");
		}
	
    }
    
    public void setBackground(){
	        String dbName ="jdbc:mysql://localhost:3306/program_data";
		String dbUserName = "root";
		String dbPassword = ""; 
		
		String p="Select * from settings";
		    
		try{
		    
		    Class.forName("com.mysql.jdbc.Driver");
		    Connection con = DriverManager.getConnection(dbName,dbUserName,dbPassword);
		    
		    Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
		     
		    ResultSet rs=stm.executeQuery(p);
			
			if(rs.next()){
			String n=rs.getString("img_name");
				bg_image.setIcon(new ImageIcon("images/"+n));
				int a=Integer.parseInt(rs.getString("a"));
				int b=Integer.parseInt(rs.getString("b"));
				int c=Integer.parseInt(rs.getString("c"));
				
				Color color3=new Color(a,b,c);
				
				
				
				
				
				date.setForeground(color3);
				quantity.setForeground(color3);
				buy_price.setForeground(color3);
				sell_price.setForeground(color3);
				id.setForeground(color3);
				name.setForeground(color3);
			
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
    
    public static void main(String[] args) {
        AdminPanel admin=new AdminPanel();
    }
    
    
 
}
