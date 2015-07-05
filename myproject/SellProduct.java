import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*; // For Database Access
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import java.awt.print.*;

public class SellProduct extends JFrame implements ActionListener,ItemListener{
	
		JLabel pname,pid,psell_price,avail_qty,quantity,date,cname,soldby,datesold,invoice,totalprice,bg_image,previous_due,
			current_bill,ppaid,final_due;
		JTextField tpid,tpsell_price,tavail_qty,tquantity,tdate,tdatesold,tinvoice,ttotalprice,tprevious_due,tcurrent_bill,tpaid,tfinal_due;
		JButton add,delete,show,save,paid,print,date1;
		JComboBox combo_name,combo_cname,combo_soldby;
		
	
		private DefaultTableModel tableModel;
		private JTable table;
		
		int sell_invoice=1000;
		
		
				
				
				
		public SellProduct(){
	
		Container c1=getContentPane();
		c1.setLayout(null);
		
		
		Color color1 = new Color(199,250,223);
		Color color2 = new Color(240,255,255);
		
		c1.setBackground(color1);
		
			
		//code for Table creation starts
		int vsb = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS;
		int hsb = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS;
		
		Object columnHeader[] = { "Date" , "Invoice", "Product ID", "Product Name" , "Sell Price" , "Quantity" , "Total Price" , "Customer Name" , "Sold By" };
		Object sampleData [][] =
		{
			
		};
		tableModel=new DefaultTableModel(sampleData, columnHeader);
		table=new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table,vsb,hsb);
		c1.add(scrollPane, BorderLayout.CENTER );
		TableCellEditor tableCellEditor = table.getDefaultEditor(table.getColumnClass(0));
		JTableHeader tableHeader = table.getTableHeader();
		table.setFont(new Font("Century Gothic",Font.PLAIN, 15));
                table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableHeader.setFont(new Font("Trebuchet MS",Font.BOLD, 15)); 
		//code for Table creation ends
		
		JMenu user = new JMenu("  User  ");
		user.setMnemonic('F');
		JMenuItem adduser = new JMenuItem("Add User",new ImageIcon("images/chat_icon.png"));
		adduser.setMnemonic('N');
		user.add(adduser);
		JMenuItem seeuser = new JMenuItem("See User Details               ");
		seeuser.setMnemonic('O');
		user.add(seeuser);	
		JMenuItem logout = new JMenuItem("Log Out");
		logout.setMnemonic('x');
		user.add(logout);
		JMenuItem exit1 = new JMenuItem("Exit");
		exit1.setMnemonic('x');
		user.add(exit1);
		
		JMenu customer = new JMenu(" Customer ");
		customer.setMnemonic('F');
		JMenuItem addcustomer = new JMenuItem("Add Customer");
		addcustomer.setMnemonic('N');
		customer.add(addcustomer);
		JMenuItem seecustomer = new JMenuItem("Customer Details               ");
		seecustomer.setMnemonic('O');
		customer.add(seecustomer);	
		JMenuItem acc = new JMenuItem("Customer Account");
		acc.setMnemonic('x');
		customer.add(acc);
		
		JMenu product = new JMenu(" Product ");
		product.setMnemonic('F');
		JMenuItem addproduct = new JMenuItem("Add Product");
		addproduct.setMnemonic('N');
		product.add(addproduct);
		JMenuItem seeproduct = new JMenuItem("Product Details               ");
		seeproduct.setMnemonic('O');
		product.add(seeproduct);	
		JMenuItem deleteproduct = new JMenuItem("Delete Product");
		deleteproduct.setMnemonic('x');
		product.add(deleteproduct);
		
		JMenu sales = new JMenu(" Sales ");
		sales.setMnemonic('F');
		JMenuItem sellproduct = new JMenuItem("Sell Product");
		sellproduct.setMnemonic('N');
		sales.add(sellproduct);
		JMenuItem sellsreport = new JMenuItem("Sells Report               ");
		sellsreport.setMnemonic('O');
		sales.add(sellsreport);
		
		JMenu help = new JMenu(" Help ");
		help.setMnemonic('F');
		JMenuItem manual = new JMenuItem("Manual");
		manual.setMnemonic('N');
		help.add(manual);
		JMenuItem about = new JMenuItem("About Soft             ");
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
			
			
		
			//code for combobox starts from here
		String s1[] = null;
		String s2[] = null;
		String s3[] = null;
		String s[]=new String[20];
		String sname[]=new String[20];
		String soldbyname[]=new String[20];
		    
		String dbName ="jdbc:mysql://localhost:3306/program_data";
		String dbUserName = "root";
		String dbPassword = ""; 
		
		
		String q="Select * from product";
		String p="Select * from customer";
		String r="Select * from login";
		String d="Select * from sell_product";
		
		
		try{
		    
		    Class.forName("com.mysql.jdbc.Driver");
		    Connection con = DriverManager.getConnection(dbName,dbUserName,dbPassword);
			     
		    Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			Statement stm1=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			Statement stm2=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			Statement stm3=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
		     
		    ResultSet rs=stm.executeQuery(q); 
		    ResultSet rs1=stm1.executeQuery(p);
		    ResultSet rs2=stm2.executeQuery(r);
			ResultSet rsd=stm3.executeQuery(d);
			
		     int i=0;
		     int j=0;
		     int k=0;
			
		    while(rs.next())
			{
				s[i] = rs.getString("name");
				i++;
			}
				s3 = new String[i];
				for(int n=0;n<i;n++){
					s3[n] = s[n];
				}
			while(rs1.next())
			{
				sname[j]=rs1.getString("name");
				j++;
			}
				s2 = new String[j];
				for(int m=0;m<j;m++){
					s2[m] = sname[m];
				}
			while(rs2.next())
			{
				soldbyname[k]=rs2.getString("id");
				k++;
			}
				s1 = new String[k];
				for(int l=0;l<k;l++){
					s1[l] = soldbyname[l];
				}
			
			while(rsd.next())
			{
				String n1=rsd.getString("invoice");
				int sell_inv=Integer.parseInt(n1);
				//String sell_inv=Integer.toString(sell_invoice);
				//if(n1.equals(sell_inv)){
					sell_invoice=sell_inv;
					
					//}else{
						//System.out.println("Different"+sell_invoice);
						//}
			}
			
			
			
				rs.close();
				rs1.close();
				rs2.close();
				rsd.close();
				stm.close();
				stm1.close();
				stm2.close();
				con.close(); 
			
		 
		}catch(Exception e){
		    System.out.println(e);     
		}finally{
		 
		}
		
		combo_name=new JComboBox(s3);
		c1.add(combo_name);
		combo_cname=new JComboBox(s2);
		c1.add(combo_cname);
		combo_soldby=new JComboBox(s1);
		c1.add(combo_soldby);
		
		combo_name.setBackground(color2);
		combo_cname.setBackground(color2);
		combo_soldby.setBackground(color2);
		
		combo_name.setSelectedIndex(0);
		combo_name.addItemListener(this);
		combo_name.addActionListener(this);
		combo_cname.setSelectedIndex(0);
		combo_cname.addItemListener(this);
		combo_cname.addActionListener(this);
		combo_soldby.setSelectedIndex(0);
		combo_soldby.addItemListener(this);
		combo_soldby.addActionListener(this);
		
		//code for combo ends here
		
		pname=new JLabel("Product Name");
		c1.add(pname);
		//pname.setForeground(Color.white);
		
		pid=new JLabel("Product ID");
		c1.add(pid);
		//pid.setForeground(Color.white);
		
		psell_price=new JLabel("Product Sell Price");
		c1.add(psell_price);
		//psell_price.setForeground(Color.white);
		
		avail_qty=new JLabel("Available Quantity");
		c1.add(avail_qty);
		//avail_qty.setForeground(Color.white);
		
		quantity=new JLabel("Sell Quantity");
		c1.add(quantity);
		//quantity.setForeground(Color.white);
		
		date=new JLabel("Added Date");
		c1.add(date);
		//date.setForeground(Color.white);
		
		cname=new JLabel("Customer Name");
		c1.add(cname);
		//cname.setForeground(Color.white);
		
		soldby=new JLabel("Sold By");
		c1.add(soldby);
		//soldby.setForeground(Color.white);
		
		datesold=new JLabel("Date");
		c1.add(datesold);
		//datesold.setForeground(Color.white);
		
		invoice=new JLabel("Invoice");
		c1.add(invoice);
		//invoice.setForeground(Color.white);
		
		totalprice=new JLabel("Total Price");	
		c1.add(totalprice);
		//totalprice.setForeground(Color.white);
			
		
		tpid=new JTextField();
		c1.add(tpid);
		tpid.setBackground(color2);
		tpid.setEditable(false);
		
		tpsell_price=new JTextField();
		c1.add(tpsell_price);
		tpsell_price.setBackground(color2);
		tpsell_price.setText("0");
		tpsell_price.setEditable(false);
		
		tavail_qty=new JTextField();
		c1.add(tavail_qty);
		tavail_qty.setBackground(color2);
		tavail_qty.setText("0");
		tavail_qty.setEditable(false);
		
		tquantity=new JTextField();
		c1.add(tquantity);
		tquantity.setBackground(color2);
		tquantity.setText("0");
		
		tdate=new JTextField();
		c1.add(tdate);
		tdate.setBackground(color2);
		tdate.setEditable(false);
		
		ImageIcon calendar = new ImageIcon("images/calendar.png");
		date1=new JButton("",calendar);
		c1.add(date1);
		
		
		
		tdatesold=new JTextField();
		c1.add(tdatesold);
		tdatesold.setBackground(color2);
		tdatesold.setEditable(false);
		
		
		tinvoice=new JTextField();
		c1.add(tinvoice);
		tinvoice.setBackground(color2);
		//String current_invoice=Integer.toString(sell_invoice);
		int sell_inv0=sell_invoice+1;
		String sell_inv1=""+sell_inv0;
		tinvoice.setText(sell_inv1);
		tinvoice.setEditable(false);
		
		
		
		previous_due=new JLabel("Previous Due");
		c1.add(previous_due);
		previous_due.setForeground(Color.red);
		
		current_bill=new JLabel("Current Bill");
		c1.add(current_bill);
		current_bill.setForeground(Color.red);
		
		ppaid=new JLabel("Current Pay");
		c1.add(ppaid);
		ppaid.setForeground(Color.red);
		
		//final_due=new JLabel("Final Due");
		//c1.add(final_due);
		//final_due.setForeground(Color.white);
		
		
		
		tprevious_due=new JTextField();
		c1.add(tprevious_due);
		tcurrent_bill=new JTextField();
		c1.add(tcurrent_bill);
		tpaid=new JTextField();
		c1.add(tpaid);
		tfinal_due=new JTextField();
		c1.add(tfinal_due);
		
		
		ttotalprice=new JTextField();
		c1.add(ttotalprice);
		ttotalprice.setBackground(color2);
		ttotalprice.setText("0");
		ttotalprice.setEditable(false);	
		
		
		ImageIcon button1 = new ImageIcon("images/button1.png");
		
		add=new JButton("Add",button1);
		c1.add(add);
		add.setForeground(Color.white);
		add.setFont(new Font("Forte", Font.BOLD, 16));
	
		delete=new JButton("Delete",button1);
		c1.add(delete);
		delete.setForeground(Color.white);
		delete.setFont(new Font("Forte", Font.BOLD, 16));
	
		show=new JButton("Show",button1);
		c1.add(show);
		show.setForeground(Color.white);
		show.setFont(new Font("Forte", Font.BOLD, 16));
		
		save=new JButton("Save",button1);
		c1.add(save);
		save.setForeground(Color.white);
		save.setFont(new Font("Forte", Font.BOLD, 16));
		
		paid=new JButton("Paid",button1);
		c1.add(paid);
		paid.setForeground(Color.white);
		paid.setFont(new Font("Forte", Font.BOLD, 16));
		
		//refresh=new JButton("Refresh",button1);
		//c1.add(refresh);
		//refresh.setForeground(Color.white);
		//refresh.setFont(new Font("Forte", Font.BOLD, 16));
		
		print=new JButton("Print",button1);
		c1.add(print);
		print.setForeground(Color.white);
		print.setFont(new Font("Forte", Font.BOLD, 16));
		
		
		bg_image=new JLabel("");
		c1.add(bg_image);
		
		add.setHorizontalTextPosition(SwingConstants.CENTER);
		add.setBorderPainted(false);
		add.setContentAreaFilled(false);
	
		delete.setHorizontalTextPosition(SwingConstants.CENTER);
		delete.setBorderPainted(false);
		delete.setContentAreaFilled(false);
		
		show.setHorizontalTextPosition(SwingConstants.CENTER);
		show.setBorderPainted(false);
		show.setContentAreaFilled(false);
		
		save.setHorizontalTextPosition(SwingConstants.CENTER);
		save.setBorderPainted(false);
		save.setContentAreaFilled(false);
		
		paid.setHorizontalTextPosition(SwingConstants.CENTER);
		paid.setBorderPainted(false);
		paid.setContentAreaFilled(false);
		
		//refresh.setHorizontalTextPosition(SwingConstants.CENTER);
		//refresh.setBorderPainted(false);
		//refresh.setContentAreaFilled(false);
		
		print.setHorizontalTextPosition(SwingConstants.CENTER);
		print.setBorderPainted(false);
		print.setContentAreaFilled(false);
		
		date1.setBorderPainted(false);
		date1.setContentAreaFilled(false);
		date1.setHorizontalTextPosition(SwingConstants.CENTER);
		
		
		invoice.setBounds(40,107,100,25);
		tinvoice.setBounds(90,107,100,25);
		pname.setBounds(203,107,85,25);
		combo_name.setBounds(293,107,150,25);
		cname.setBounds(455,107,100,25);
		combo_cname.setBounds(555,107,150,25);
		soldby.setBounds(715,107,60,25);
		combo_soldby.setBounds(766,107,150,25);
		datesold.setBounds(927,107,40,25);
		tdatesold.setBounds(963,107,120,25);
		pid.setBounds(40,150,150,25);
		tpid.setBounds(40,175,150,25);
		avail_qty.setBounds(230,150,150,25);
		tavail_qty.setBounds(230,175,150,25);
		psell_price.setBounds(420,150,150,25);
		tpsell_price.setBounds(420,175,150,25);
		quantity.setBounds(610,150,150,25);
		tquantity.setBounds(610,175,150,25);
		date.setBounds(800,150,150,25);
		tdate.setBounds(800,175,150,25);
		
		previous_due.setBounds(752,495,100,25);
		current_bill.setBounds(882,495,100,25);
		ppaid.setBounds(1012,495,100,25);
		//final_due.setBounds(1022,495,100,25);
		
		tprevious_due.setBounds(752,522,100,25);
		tcurrent_bill.setBounds(882,522,100,25);
		tpaid.setBounds(1012,522,100,25);
		//tfinal_due.setBounds(1022,522,100,25);
		
		delete.setBounds(1140,180,100,30);
		add.setBounds(1140,138,100,30);
		show.setBounds(1140,98,100,30);
		//save.setBounds(1142,460,100,30);
		paid.setBounds(1142,520,100,30);
		//refresh.setBounds(1142,580,100,30);
		print.setBounds(1142,580,100,30);
		date1.setBounds(1083,107,26,25);
		
		
		totalprice.setBounds(990,150,100,25);
		ttotalprice.setBounds(990,175,122,25);
		scrollPane.setBounds(40,250,1200,199);
		bg_image.setBounds(0,0,1360,683);
		
		
		add.addActionListener(this);
		delete.addActionListener(this);
		show.addActionListener(this);
		save.addActionListener(this);
		paid.addActionListener(this);
		//refresh.addActionListener(this);
		date1.addActionListener(this);
		print.addActionListener(new Print1());
		//String current_invoice=Integer.toString(sell_invoice);
		//tinvoice.setText(current_invoice);
		
		this.setSize(300,200);
		this.setVisible(true);
		setTitle("Selling Product");
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		setBackground();
	
		}
		
	
	 public void itemStateChanged(ItemEvent e){
			int selection;
			selection=combo_name.getSelectedIndex();
		 
		  if ( e.getStateChange() == ItemEvent.SELECTED ){
				Object item1=combo_name.getSelectedItem();
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
						
						
						tpid.setText(pid);
						tpsell_price.setText(psell_price);
						tavail_qty.setText(pquantity);
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
	
	   public void sell_product(String invoice_no,String name,String cname,String soldby,String sell_date,String product_id,String sell_price,String sell_qty,String total){
			String grand_total=ttotalprice.getText();
		   
			String dbName ="jdbc:mysql://localhost:3306/program_data";
			String dbUserName = "root";
			String dbPassword = ""; 
			
			String p="insert into sell_product (invoice , pid, pname , sell_price, quantity,customer_name,soldby,date,total,Grand_total ) values ('" + invoice_no + "','"+ product_id + "','"+ name + "','"+ sell_price + "','"+ sell_qty + "','"+ cname + "','"+ soldby + "','"+ sell_date + "','"+ total + "','"+ grand_total + "')";
			String q="Select * from product where name='"+name+"'";
		   
			try{
			    
			    Class.forName("com.mysql.jdbc.Driver");
			    Connection con = DriverManager.getConnection(dbName,dbUserName,dbPassword);
			    
			    Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				Statement stm1=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				Statement stm2=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			     
			    stm.executeUpdate(p);
			    ResultSet rs=stm1.executeQuery(q);
				while(rs.next()){
						String quantity = rs.getString("quantity");
						int avail_qty=Integer.parseInt(quantity);
					
						int sell_qty1=Integer.parseInt(sell_qty);
					
						if(avail_qty>=sell_qty1){
						int qty=avail_qty-sell_qty1;
							
							String r="Update product set quantity='" + (qty)+ "' where name='"+name+"'";
							stm2.executeUpdate(r);
							
						}else{
							JOptionPane.showMessageDialog(null,"Product out of stock");
							}
						
					}
					
					rs.close();
					stm.close();
					stm1.close();
					stm2.close();
					con.close(); 
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,e);
			}
		
		}
		
	
		public void actionPerformed(ActionEvent event1){
			if(event1.getSource()==show){
			Object item1=combo_name.getSelectedItem();
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
						
						
						tpid.setText(pid);
						tpsell_price.setText(psell_price);
						tavail_qty.setText(pquantity);
						tdate.setText(pdate);
						
					}
						rs.close();
						stm.close();
						con.close(); 
					
				 
				}catch(Exception e){
				    System.out.println(e);     
				}finally{
				 
				}
	
			}
			
			
			else if(event1.getSource()==add){
				Object name1=combo_name.getSelectedItem();
				String name=name1.toString();
				String sell_qty=tquantity.getText();
				int sell=Integer.parseInt(sell_qty);
				getQty();
				
				
				if(sell<getQty()){
				
					Object cname1=combo_cname.getSelectedItem();
					String cname=cname1.toString();
					Object soldby1=combo_soldby.getSelectedItem();
					String soldby=soldby1.toString();
					String sell_date=tdatesold.getText();
					String product_id=tpid.getText();
				
					tdatesold.setBorder(BorderFactory.createLineBorder(Color.white));
					combo_name.setBorder(BorderFactory.createLineBorder(Color.white));
					tquantity.setBorder(BorderFactory.createLineBorder(Color.white));
				
					if(sell_date.equals("")){
						JOptionPane.showMessageDialog(this, "Select Date");
						tdatesold.setBorder(BorderFactory.createLineBorder(Color.red));
					}else 
					if(product_id.equals("")){
						JOptionPane.showMessageDialog(this, "Select Product");
						combo_name.setBorder(BorderFactory.createLineBorder(Color.red));
					}else 
					if(sell_qty.equals("0")){
						JOptionPane.showMessageDialog(this, "Enter Quantity");
						tquantity.setBorder(BorderFactory.createLineBorder(Color.red));
					}
					else{
						
						String sell_price=tpsell_price.getText();
						String invoice_no=tinvoice.getText();
						int num1=Integer.parseInt(sell_price);
						int num2=Integer.parseInt(sell_qty);
						int total=num1*num2;
						
						//String total1=Integer.toString(total);
						String total1=ttotalprice.getText();
						int total2=Integer.parseInt(total1);
						int total4=total2+total;
						String total5=""+total4;
						ttotalprice.setText(total5);
						
						Object rowData[] = {sell_date, invoice_no, product_id,name,sell_price,sell_qty,total,cname,soldby};
						tableModel.addRow(rowData);
						tpid.setText("");
						tpsell_price.setText("0");
						tquantity.setText("0");
						tavail_qty.setText("0");
						tdate.setText("");
						
						//sell_product(invoice_no,name,cname,soldby,sell_date,product_id,sell_price,sell_qty,total);
						//sell_invoice=sell_invoice+1;
						//String current_invoice=Integer.toString(sell_invoice);
						//tinvoice.setText(current_invoice);
						//this.setVisible(false);
						//SellProduct s1=new SellProduct();
						Object cusname1=combo_cname.getSelectedItem();
						String cusname=cusname1.toString();
						String previous_due="0";
						
						String dbName ="jdbc:mysql://localhost:3306/program_data";
						String dbUserName = "root";
						String dbPassword = ""; 
						
						String p="Select * from accounts where Customer_name='"+cusname+"'";
						    
						try{
						    
						    Class.forName("com.mysql.jdbc.Driver");
						    Connection con = DriverManager.getConnection(dbName,dbUserName,dbPassword);
						    
						    Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
						     
						    ResultSet rs=stm.executeQuery(p);
							while(rs.next()){
								
							String pdue = rs.getString("Final_due");
								previous_due=pdue;
								
							
							}
						    
								rs.close();
								stm.close();
								con.close(); 
						}catch(Exception e){
							JOptionPane.showMessageDialog(null,e);
						}
						
						
						tprevious_due.setText(previous_due);
						tcurrent_bill.setText(total5);
						tquantity.setBorder(BorderFactory.createLineBorder(Color.green));
					}
				}else{
					JOptionPane.showMessageDialog(this, ""+getQty()+"Product available");
					tquantity.setText("0");
					tquantity.setBorder(BorderFactory.createLineBorder(Color.red));
					}
			}else if(event1.getSource()==delete){
				try
					{
					    int rowNo =table.getSelectedRow();
			   
					    tableModel.removeRow(rowNo); 
					}
					catch(Exception e)
					{
					    JOptionPane.showMessageDialog(this, "Error : " + e);
					}
			
			}else if(event1.getSource()==date1){
				tdatesold.setText(new DatePicker(this).setPickedDate());
			
			}/*else if(event1.getSource()==save){
			
				int row_count=table.getRowCount();
				
				String invoice_no="";
				String name="";
				String cname="";
				String soldby="";
				String sell_date="";
				String product_id="";
				String sell_price="";
				String sell_qty="";
				String total="";
				
				
				for(int z=0;z<row_count;z++){
					for(int y=0;y<9;y++){
						if(y==0){
							Object cellValue_date0 = table.getValueAt(z,y);
							String cellValue_date=cellValue_date0.toString();
							sell_date=cellValue_date;
							//JOptionPane.showMessageDialog(this, "Date is :  " + cellValue_date);
						}else if(y==1){
							Object cellValue_invoice0 = table.getValueAt(z,y);
							String cellValue_invoice=cellValue_invoice0.toString();
							invoice_no=cellValue_invoice;
							//JOptionPane.showMessageDialog(this, "Invoice  :   " + cellValue_invoice);
						}else if(y==2){
							Object cellValue_pid0 = table.getValueAt(z,y);
							String cellValue_pid=cellValue_pid0.toString();
							product_id=cellValue_pid;
							//JOptionPane.showMessageDialog(this, "Product ID : " + cellValue_pid);
						}else if(y==3){
							Object cellValue_pname0 = table.getValueAt(z,y);
							String cellValue_pname=cellValue_pname0.toString();
							name=cellValue_pname;
							//JOptionPane.showMessageDialog(this, "Product name : " + cellValue_pname);
						}else if(y==4){
							Object cellValue_sellprice0 = table.getValueAt(z,y);
							String cellValue_sellprice=cellValue_sellprice0.toString();
							sell_price=cellValue_sellprice;
							//JOptionPane.showMessageDialog(this, "Sell Price : " + cellValue_sellprice);
						}else if(y==5){
							Object cellValue_quantity0 = table.getValueAt(z,y);
							String cellValue_quantity=cellValue_quantity0.toString();
							sell_qty=cellValue_quantity;
							//JOptionPane.showMessageDialog(this, "Quantity : " + cellValue_quantity);
						}else if(y==6){
							Object cellValue_totalprice0 = table.getValueAt(z,y);
							String cellValue_totalprice=cellValue_totalprice0.toString();
							total=cellValue_totalprice;
							//JOptionPane.showMessageDialog(this, "Total Price : " + cellValue_totalprice);
						}else if(y==7){
							Object cellValue_cname0 = table.getValueAt(z,y);
							String cellValue_cname=cellValue_cname0.toString();
							cname=cellValue_cname;
							//JOptionPane.showMessageDialog(this, "Customer name : " + cellValue_cname);
						}else if(y==8){
							Object cellValue_soldby0 = table.getValueAt(z,y);
							String cellValue_soldby=cellValue_soldby0.toString();
							soldby=cellValue_soldby;
							//JOptionPane.showMessageDialog(this, "Sold By : " + cellValue_soldby);
						}
						
					}//end of y for loop
					
					sell_product(invoice_no,name,cname,soldby,sell_date,product_id,sell_price,sell_qty,total);
					//deduct_qty(name,sell_qty);
					
				}//end of z for loop
				JOptionPane.showMessageDialog(null,""+row_count+" Products Successfully Added");
				
				
				Object cusname1=combo_cname.getSelectedItem();
				String cusname=cusname1.toString();
				String previous_due="0";
				
				String dbName ="jdbc:mysql://localhost:3306/program_data";
				String dbUserName = "root";
				String dbPassword = ""; 
				
				String p="Select * from accounts where Customer_name='"+cusname+"'";
				    
				try{
				    
				    Class.forName("com.mysql.jdbc.Driver");
				    Connection con = DriverManager.getConnection(dbName,dbUserName,dbPassword);
				    
				    Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				     
				    ResultSet rs=stm.executeQuery(p);
					while(rs.next()){
						
					String pdue = rs.getString("Final_due");
						previous_due=pdue;
						
					
					}
				    
						rs.close();
						stm.close();
						con.close(); 
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,e);
				}
				
				
				tprevious_due.setText(previous_due);
				String tp1=ttotalprice.getText();
				tcurrent_bill.setText(tp1);
			}//end function of save button
			*/
			
			else if(event1.getSource()==paid){
				
					
					String PPdue=tprevious_due.getText();
					int p=Integer.parseInt(PPdue);
					String ttcurrent_bill=tcurrent_bill.getText();
					int q=Integer.parseInt(ttcurrent_bill);
					String s=tpaid.getText();
					int t=Integer.parseInt(s);
					int r=p+q-t;
					String final_due=""+r;
					tfinal_due.setText(final_due);
				
					Object cname1=combo_cname.getSelectedItem();
					String cname=cname1.toString();
					String invoice=tinvoice.getText();
					String pdue=tprevious_due.getText();
					String cur_bill=tcurrent_bill.getText();
					String final_due1=tfinal_due.getText();
					String pay_type="Cash";
					String sell_date=tdatesold.getText();
					
					
					String dbName ="jdbc:mysql://localhost:3306/program_data";
					String dbUserName = "root";
					String dbPassword = ""; 
					
					String m="insert into accounts (date,Customer_name , invoice, Previous_due , Current_bill, Paid,Final_due,payment_type ) values ('" + sell_date + "','" + cname + "','"+ invoice + "','"+ pdue + "','"+ cur_bill + "','"+ s + "','"+ final_due1 + "','"+ pay_type + "')";
					
					try{
					    
					    Class.forName("com.mysql.jdbc.Driver");
					    Connection con = DriverManager.getConnection(dbName,dbUserName,dbPassword);
					    
					    Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
					     
					    stm.executeUpdate(m);
						JOptionPane.showMessageDialog(null,"Paid. Your Final Due is : "+final_due);
					    
							stm.close();
							con.close(); 
					}catch(Exception e){
						JOptionPane.showMessageDialog(null,e);
					}
					
					String o="Select * from due where name='"+cname+"'";
					String n="insert into due (name,due_amount) values ('" + cname + "','"+ final_due1 + "')";
					String j="Update due set due_amount='" + (final_due1)+ "' where name='"+cname+"'";
					
					try{
					    
					    Class.forName("com.mysql.jdbc.Driver");
					    Connection con = DriverManager.getConnection(dbName,dbUserName,dbPassword);
					    
					    Statement stm3=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
						Statement stm1=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
						Statement stm2=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
					     
						ResultSet rs=stm3.executeQuery(o);
						if(rs.next()){
							
							stm1.executeUpdate(j);
							}else{
								stm2.executeUpdate(n);
								}
					    
					   
							stm2.close();
								stm1.close();
								stm3.close();
							con.close(); 
					}catch(Exception e){
						JOptionPane.showMessageDialog(null,e);
					}
					
				   saveSell();
					
						this.setVisible(false);
						SellProduct sp1=new SellProduct();
				}
				
			else if(event1.getSource()==print){
			
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
	
		public static void main(String args[]){
			SellProduct s1=new SellProduct();
	
		}
		int getQty(){
				
					Object name1=combo_name.getSelectedItem();
					String name=name1.toString();
					String sell_qty=tquantity.getText();
			int avail_qty1=0;
				
					String dbName ="jdbc:mysql://localhost:3306/program_data";
					String dbUserName = "root";
					String dbPassword = ""; 
					
					String q="Select * from product where name='"+name+"'";
				   
					try{
					    
					    Class.forName("com.mysql.jdbc.Driver");
					    Connection con = DriverManager.getConnection(dbName,dbUserName,dbPassword);
					    
					    Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
					     
					    ResultSet rs=stm.executeQuery(q);
						while(rs.next()){
								String quantity = rs.getString("quantity");
								avail_qty1=Integer.parseInt(quantity);
							}
					   
							
							rs.close();
							stm.close();
							con.close(); 
					}catch(Exception e){
						JOptionPane.showMessageDialog(null,e);
					}
					return avail_qty1;
				}
		public void saveSell(){
				int row_count=table.getRowCount();
				
				String invoice_no="";
				String name="";
				String cname="";
				String soldby="";
				String sell_date="";
				String product_id="";
				String sell_price="";
				String sell_qty="";
				String total="";
				
				
				for(int z=0;z<row_count;z++){
					for(int y=0;y<9;y++){
						if(y==0){
							Object cellValue_date0 = table.getValueAt(z,y);
							String cellValue_date=cellValue_date0.toString();
							sell_date=cellValue_date;
							//JOptionPane.showMessageDialog(this, "Date is :  " + cellValue_date);
						}else if(y==1){
							Object cellValue_invoice0 = table.getValueAt(z,y);
							String cellValue_invoice=cellValue_invoice0.toString();
							invoice_no=cellValue_invoice;
							//JOptionPane.showMessageDialog(this, "Invoice  :   " + cellValue_invoice);
						}else if(y==2){
							Object cellValue_pid0 = table.getValueAt(z,y);
							String cellValue_pid=cellValue_pid0.toString();
							product_id=cellValue_pid;
							//JOptionPane.showMessageDialog(this, "Product ID : " + cellValue_pid);
						}else if(y==3){
							Object cellValue_pname0 = table.getValueAt(z,y);
							String cellValue_pname=cellValue_pname0.toString();
							name=cellValue_pname;
							//JOptionPane.showMessageDialog(this, "Product name : " + cellValue_pname);
						}else if(y==4){
							Object cellValue_sellprice0 = table.getValueAt(z,y);
							String cellValue_sellprice=cellValue_sellprice0.toString();
							sell_price=cellValue_sellprice;
							//JOptionPane.showMessageDialog(this, "Sell Price : " + cellValue_sellprice);
						}else if(y==5){
							Object cellValue_quantity0 = table.getValueAt(z,y);
							String cellValue_quantity=cellValue_quantity0.toString();
							sell_qty=cellValue_quantity;
							//JOptionPane.showMessageDialog(this, "Quantity : " + cellValue_quantity);
						}else if(y==6){
							Object cellValue_totalprice0 = table.getValueAt(z,y);
							String cellValue_totalprice=cellValue_totalprice0.toString();
							total=cellValue_totalprice;
							//JOptionPane.showMessageDialog(this, "Total Price : " + cellValue_totalprice);
						}else if(y==7){
							Object cellValue_cname0 = table.getValueAt(z,y);
							String cellValue_cname=cellValue_cname0.toString();
							cname=cellValue_cname;
							//JOptionPane.showMessageDialog(this, "Customer name : " + cellValue_cname);
						}else if(y==8){
							Object cellValue_soldby0 = table.getValueAt(z,y);
							String cellValue_soldby=cellValue_soldby0.toString();
							soldby=cellValue_soldby;
							//JOptionPane.showMessageDialog(this, "Sold By : " + cellValue_soldby);
						}
						
					}//end of y for loop
					
					sell_product(invoice_no,name,cname,soldby,sell_date,product_id,sell_price,sell_qty,total);
					//deduct_qty(name,sell_qty);
					
				}//end of z for loop
				//JOptionPane.showMessageDialog(null,""+row_count+" Products Successfully Added");
				
				
				Object cusname1=combo_cname.getSelectedItem();
				String cusname=cusname1.toString();
				String previous_due="0";
				
				String dbName ="jdbc:mysql://localhost:3306/program_data";
				String dbUserName = "root";
				String dbPassword = ""; 
				
				String p="Select * from accounts where Customer_name='"+cusname+"'";
				    
				try{
				    
				    Class.forName("com.mysql.jdbc.Driver");
				    Connection con = DriverManager.getConnection(dbName,dbUserName,dbPassword);
				    
				    Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				     
				    ResultSet rs=stm.executeQuery(p);
					while(rs.next()){
						
					String pdue = rs.getString("Final_due");
						previous_due=pdue;
						
					
					}
				    
						rs.close();
						stm.close();
						con.close(); 
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,e);
				}
				
				
				tprevious_due.setText(previous_due);
				String tp1=ttotalprice.getText();
				tcurrent_bill.setText(tp1);
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
