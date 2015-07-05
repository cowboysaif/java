import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*; // For Database Access
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;

public class Accounts extends JFrame{
	JTabbedPane tbp;
	JLabel label3,bg_image;
	
	
	Accounts(){
		Container c1=getContentPane();
		c1.setLayout(null);
		
		
		tbp=new JTabbedPane();
		c1.add(tbp);
		
		label3=new JLabel("Customer Inquiry");
		c1.add(label3);
		bg_image=new JLabel("");
		c1.add(bg_image);
		
		label3.setBounds(390,20,900,80);
		label3.setFont(new Font("Verdana", Font.BOLD, 46));
		
		tbp.addTab("     Search By Name      ",new panel1());
		tbp.addTab("     Search By Invoice    ",new panel2());
		
		tbp.setBounds(10,100,1255,510);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		//setBackground();
		//tbp.setBackgroundAt(0,Color.RED);
		tbp.setToolTipTextAt(0,"Search Transaction Details by Name");
		tbp.setToolTipTextAt(1,"Search Transaction Details by Invoice");
		
		Color color1 = new Color(199,250,223);
		c1.setBackground(color1);
		
		this.setIconImage(new ImageIcon("images/favicon.png").getImage());
		
		
		this.setVisible(true);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1280,650);
		setTitle("Accounts");
		
		
		bg_image=new JLabel("");
		c1.add(bg_image);
		bg_image.setBounds(0,0,1360,683);
		bg_image.setIcon(new ImageIcon("images/image6.jpg"));
		
		
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		
		}
		
	
	}
	

class panel1 extends JPanel implements ItemListener,ActionListener{
	JLabel c_name,total_bill,total_paid,due;
	JTextField ttotal_bill,ttotal_paid,tdue;
	JButton show,reset;
	JComboBox combo_name;
	
	
	private DefaultTableModel tableModel;
		private JTable table;
	
	public panel1(){
		setLayout(null);
		c_name=new JLabel("Customer Name");
		add(c_name);
		c_name.setBounds(250,20,100,25);
		
		total_bill=new JLabel("Total Bill");
		add(total_bill);
		total_bill.setBounds(15,75,100,25);
		
		total_paid=new JLabel("Total Paid");
		add(total_paid);
		total_paid.setBounds(15,130,130,25);
		
		due=new JLabel("Total Due");
		add(due);
		due.setBounds(15,180,130,25);
		
		
		ttotal_bill=new JTextField();
		add(ttotal_bill);
		ttotal_bill.setBounds(15,100,150,25);
		
		ttotal_paid=new JTextField();
		add(ttotal_paid);
		ttotal_paid.setBounds(15,155,150,25);
		
		tdue=new JTextField();
		add(tdue);
		tdue.setBounds(15,205,150,25);
		
		show=new JButton("Show");
		add(show);
		show.setBounds(860,20,80,25);
		show.addActionListener(this);
		
		reset=new JButton("Reset");
		add(reset);
		reset.setBounds(950,20,80,25);
		reset.addActionListener(this);
		
		String s1[] = null;
		String s[]=new String[20];
		
		
		String dbName ="jdbc:mysql://localhost:3306/program_data";
		String dbUserName = "root";
		String dbPassword = ""; 
		
		
		String q="Select * from customer";
		
		
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
				for(int n=0;n<i;n++){
					s1[n] = s[n];
				}
			
			
				rs.close();
				stm.close();
				con.close(); 
			
		 
		}catch(Exception e){
		    System.out.println(e);     
		}finally{
		 
		}
		
		
		
		combo_name=new JComboBox(s1);
		add(combo_name);
		combo_name.setBounds(350,20,500,25);
		combo_name.setSelectedIndex(0);
		combo_name.addItemListener(this);
		combo_name.addActionListener(this);
		
		
		
		
		//code for Table creation starts
		int vsb = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS;
		int hsb = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS;
		
		Object columnHeader[] = { "Date" ,"Customer Name" , "Invoice", "Total Price" , "Previous Due", "Paid", "Final Due"};
		Object sampleData [][] =
		{
			
		};

		tableModel=new DefaultTableModel(sampleData, columnHeader);
		table=new JTable(tableModel);
		JTableHeader header = table.getTableHeader();
		Color color1 = new Color(185,211,238);
		header.setBackground(color1);
		JScrollPane scrollPane = new JScrollPane(table,vsb,hsb);
		add(scrollPane, BorderLayout.CENTER );
		TableCellEditor tableCellEditor = table.getDefaultEditor(table.getColumnClass(0));
		JTableHeader tableHeader = table.getTableHeader();
		table.setFont(new Font("Century Gothic",Font.PLAIN, 15));
                table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableHeader.setFont(new Font("Trebuchet MS",Font.BOLD, 15)); 
		scrollPane.setBounds(187,75,1050,399);
		table.getParent().setBackground(Color.WHITE);
		//code for Table creation ends
		
		
		}
		
		
	
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==show){
				
				Object name1=combo_name.getSelectedItem();
				String name=name1.toString();
			
				String dbName ="jdbc:mysql://localhost:3306/program_data";
				String dbUserName = "root";
				String dbPassword = ""; 
				
				String q="Select * from accounts where Customer_name='"+name+"'";
		
				try{
				    
				    Class.forName("com.mysql.jdbc.Driver");
				    Connection con = DriverManager.getConnection(dbName,dbUserName,dbPassword);
					     
				    Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				     
				    ResultSet rs=stm.executeQuery(q); 
					int b=0;
					int c=0;
					
				    while(rs.next())
					{
						String a_cname=rs.getString("Customer_name");
						String a_date=rs.getString("date");
						String a_invoice=rs.getString("invoice");
						String a_cbill=rs.getString("Current_bill");
						String a_paid=rs.getString("Paid");
						String a_fdue=rs.getString("Final_due");
						String a_pdue=rs.getString("Previous_due");
						
						Object rowData[] = {a_date, a_cname, a_invoice,a_cbill,a_pdue,a_paid,a_fdue};
						tableModel.addRow(rowData);
						
						int bill1=Integer.parseInt(a_cbill);
						b=b+bill1;
						
						int paid1=Integer.parseInt(a_paid);
						c=c+paid1;
						
					}
					ttotal_bill.setText(Integer.toString(b));
					ttotal_paid.setText(Integer.toString(c));
					
					int d=b-c;
					tdue.setText(Integer.toString(d));
					
					
						rs.close();
						stm.close();
						con.close(); 
					
				 
				}catch(Exception e1){
				    System.out.println(e1);     
				}finally{
				 
				}
			
			}else 
			if(e.getSource()==reset){
				/*try
					{
					    int rowNo =table.getSelectedRow();
			   
					    
					}
					catch(Exception e)
					{
					    JOptionPane.showMessageDialog(this, "Error : " + e);
					}*/
				ttotal_bill.setText("");
				ttotal_paid.setText("");
				tdue.setText("");
				
				int row_count=table.getRowCount();
				int i=0;
				for(i=row_count-1;i>=0;i--){
					tableModel.removeRow(i); 
					}
				}
		
		}
		
		public void itemStateChanged(ItemEvent e){
	    int selection;
	    selection=combo_name.getSelectedIndex();
	    }
		
		
	}
	
class panel2 extends JPanel implements ActionListener{
	JLabel c_name1,total_bill1,total_paid1,due1,pdue;
	JTextField ttotal_bill1,ttotal_paid1,tdue1,tinvoice,tpdue;
	JButton show1,reset1;
	
	
	private DefaultTableModel tableModel1;
		private JTable table1;
	
	public panel2(){
		setLayout(null);
		c_name1=new JLabel("Invoice No");
		add(c_name1);
		c_name1.setBounds(190,20,100,25);
		
		total_bill1=new JLabel("Total Bill");
		add(total_bill1);
		total_bill1.setBounds(15,75,100,25);
		
		total_paid1=new JLabel("Total Paid");
		add(total_paid1);
		total_paid1.setBounds(15,130,130,25);
		
		due1=new JLabel("Total Due");
		add(due1);
		due1.setBounds(15,180,130,25);
		
		
		ttotal_bill1=new JTextField();
		add(ttotal_bill1);
		ttotal_bill1.setBounds(15,100,150,25);
		
		ttotal_paid1=new JTextField();
		add(ttotal_paid1);
		ttotal_paid1.setBounds(15,155,150,25);
		
		tdue1=new JTextField();
		add(tdue1);
		tdue1.setBounds(15,205,150,25);
		
		pdue=new JLabel("Previous Due");
		add(pdue);
		pdue.setBounds(15,230,150,25);
		
		tpdue=new JTextField();
		add(tpdue);
		tpdue.setBounds(15,255,150,25);
		
		tinvoice=new JTextField();
		add(tinvoice);
		tinvoice.setBounds(300,20,150,25);
		
		show1=new JButton("Show");
		add(show1);
		show1.setBounds(460,20,80,25);
		show1.addActionListener(this);
		
		reset1=new JButton("Reset");
		add(reset1);
		reset1.setBounds(550,20,80,25);
		reset1.addActionListener(this);
		
		
		String s11[] = null;
		String s1[]=new String[20];
		
		
		
		
		//code for Table creation starts
		int vsb = JScrollPane.VERTICAL_SCROLLBAR_ALWAYS;
		int hsb = JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS;
		
		Object columnHeader[] = { "Date" ,"Invoice" , "Product ID", "Product Name" , "Sell Price", "Quantity", "Customer Name","Sold By","Total"};
		Object sampleData [][] =
		{
			
		};

		tableModel1=new DefaultTableModel(sampleData, columnHeader);
		table1=new JTable(tableModel1);
		JTableHeader header1 = table1.getTableHeader();
		Color color1 = new Color(185,211,238);
		header1.setBackground(color1);
		JScrollPane scrollPane1 = new JScrollPane(table1,vsb,hsb);
		add(scrollPane1, BorderLayout.CENTER );
		TableCellEditor tableCellEditor1 = table1.getDefaultEditor(table1.getColumnClass(0));
		JTableHeader tableHeader1 = table1.getTableHeader();
		table1.setFont(new Font("Century Gothic",Font.PLAIN, 15));
                table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableHeader1.setFont(new Font("Trebuchet MS",Font.BOLD, 15)); 
		scrollPane1.setBounds(187,75,1050,399);
		table1.getParent().setBackground(Color.WHITE);
		//code for Table creation ends
		
		
		}
		
		
	
		public void actionPerformed(ActionEvent e){
			if(e.getSource()==show1){
				
				//Object name11=combo_name1.getSelectedItem();
				String name1=tinvoice.getText();
			
				String dbName ="jdbc:mysql://localhost:3306/program_data";
				String dbUserName = "root";
				String dbPassword = ""; 
				
				String q="Select * from sell_product where invoice='"+name1+"'";
				String p="Select * from accounts where invoice='"+name1+"'";
		
				try{
				    
				    Class.forName("com.mysql.jdbc.Driver");
				    Connection con = DriverManager.getConnection(dbName,dbUserName,dbPassword);
					     
				        Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
					Statement stm1=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
				     
				    ResultSet rs=stm.executeQuery(q); 
					ResultSet rs1=stm1.executeQuery(p); 
					
					if(rs1.next()){
						
						String cbill=rs1.getString("Current_bill");
						String paid=rs1.getString("Paid");
						String fdue=rs1.getString("Final_due");
						String pdue=rs1.getString("Previous_due");
						ttotal_bill1.setText(cbill);
						ttotal_paid1.setText(paid);
						tdue1.setText(fdue);
						tpdue.setText(pdue);
						
						}

				    while(rs.next())
					{
						String a_date1=rs.getString("date");
						String a_invoice1=rs.getString("invoice");
						String pid=rs.getString("pid");
						String pname=rs.getString("pname");
						String sellp=rs.getString("sell_price");
						String qty=rs.getString("quantity");
						String a_cname1=rs.getString("customer_name");
						String soldby=rs.getString("soldby");
						String total=rs.getString("total");
						
						Object rowData1[] = {a_date1, a_invoice1, pid, pname, sellp,qty,a_cname1,soldby,total};
						tableModel1.addRow(rowData1);
						
						//int bill11=Integer.parseInt(a_cbill1);
						//b1=b1+bill11;
						
						//int paid11=Integer.parseInt(a_paid1);
						//c1=c1+paid11;
						
						
					}
					
					//ttotal_bill1.setText(Integer.toString(b1));
					//ttotal_paid1.setText(Integer.toString(c1));
					
					//int d1=b1-c1;
					//tdue1.setText(Integer.toString(d1));
					
					
						rs.close();
						rs1.close();
						stm.close();
						stm1.close();
						con.close(); 
					
				 
				}catch(Exception e11){
				    System.out.println(e11);     
				}finally{
				 
				}
				
				
			
			}else 
			if(e.getSource()==reset1){
				/*try
					{
					    int rowNo =table.getSelectedRow();
			   
					    
					}
					catch(Exception e)
					{
					    JOptionPane.showMessageDialog(this, "Error : " + e);
					}*/
				ttotal_bill1.setText("");
				ttotal_paid1.setText("");
				tdue1.setText("");
				tpdue.setText("");
				
				int row_count1=table1.getRowCount();
				int i=0;
				for(i=row_count1-1;i>=0;i--){
					tableModel1.removeRow(i); 
					}
				}
		
		}
		
	
	}