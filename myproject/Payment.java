import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*; // For Database Access
import javax.swing.border.TitledBorder;

public class Payment extends JFrame implements ActionListener,ItemListener{
	JLabel date,customer_name,previous_due,pay_amount,bg_image;
	JTextField tdate,tprevious_due,tpay_amount;
	JButton paid,date2;
	JComboBox combo;
	
	public Payment(){
		Container c1=getContentPane();
		c1.setLayout(null);
		
		
		
		    JPanel panel = new JPanel();
		    c1.add(panel);
		    TitledBorder title;
		    title = BorderFactory.createTitledBorder("Receive Payment");
		    panel.setBorder(title);
		    panel.setLayout(null);
		    panel.setBounds(20,20,645,355);
		
		bg_image=new JLabel("");
		c1.add(bg_image);
		bg_image.setBounds(0,0,1360,683);
		bg_image.setIcon(new ImageIcon("images/image6.jpg"));
		
		String s1[] = null;
		String s[]=new String[20];
		
		
			String dbName ="jdbc:mysql://localhost:3306/program_data";
			String dbUserName = "root";
			String dbPassword = ""; 
			
			
			String q="Select * from due";
			
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
		
			combo=new JComboBox(s1);
			panel.add(combo);
			combo.setBackground(Color.white);
			combo.setSelectedIndex(0);
			combo.addItemListener(this);
			combo.addActionListener(this);
			combo.setBounds(350,110,150,25);
	
		
		date=new JLabel("Date:");
		panel.add(date);
		date.setBounds(150,60,100,25);
		
		customer_name=new JLabel("Customer Name:");
		panel.add(customer_name);
		customer_name.setBounds(150,110,100,25);
		
		previous_due=new JLabel("Previous Due:");
		panel.add(previous_due);
		previous_due.setBounds(150,160,100,25);
		
		pay_amount=new JLabel("Pay Amount:");
		panel.add(pay_amount);
		pay_amount.setBounds(150,210,100,25);
		
		tdate=new JTextField();
		panel.add(tdate);
		tdate.setBounds(350,60,150,25);
		
		tprevious_due=new JTextField();
		panel.add(tprevious_due);
		tprevious_due.setBounds(350,160,150,25);
		
		tpay_amount=new JTextField();
		panel.add(tpay_amount);
		tpay_amount.setBounds(350,210,150,25);
		
		paid=new JButton("Paid");
		panel.add(paid);
		paid.setBounds(400,260,100,30);
		paid.addActionListener(this);
		
		ImageIcon calendar = new ImageIcon("images/calendar.png");
		date2=new JButton("",calendar);
		panel.add(date2);
		date2.setBounds(499,59,25,25);
		date2.addActionListener(this);
		
		date2.setBorderPainted(false);
		date2.setContentAreaFilled(false);
		date2.setHorizontalTextPosition(SwingConstants.CENTER);
		
		
		this.setTitle("Payment");
		this.setSize(700,430);
		this.setVisible(true);
		//this.setResizable(false);
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		
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
			
			String q="Select * from due where name='"+item+"'";
        
			try{
			    
			    Class.forName("com.mysql.jdbc.Driver");
			    Connection con = DriverManager.getConnection(dbName,dbUserName,dbPassword);
				     
			    Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			     
			    ResultSet rs=stm.executeQuery(q); 
			     int i=0;
				
					    while(rs.next())
						{
							String finaldue = rs.getString("due_amount");
							tprevious_due.setText(finaldue);
							
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
			if(event.getSource()==paid){
					String PPdue=tprevious_due.getText();
					int p=Integer.parseInt(PPdue);
					String s=tpay_amount.getText();
					int t=Integer.parseInt(s);
					Object cname1=combo.getSelectedItem();
						String cname=cname1.toString();
					if(p==0){
						JOptionPane.showMessageDialog(null,""+cname+" has no due.");
						}
					else if(p<t){
						JOptionPane.showMessageDialog(null,""+cname+" has not that much due.");
					} 
					else{
						int r=p-t;
						String final_due=""+r;
					
						
						String invoice="0";
						String pdue=tprevious_due.getText();
						String cur_bill="0";
						String pay_type="Cash";
						String sell_date=tdate.getText();
						
						if(sell_date.equals("")){
							JOptionPane.showMessageDialog(null,"Please select Date. Thank You.");
							}else{
							String dbName ="jdbc:mysql://localhost:3306/program_data";
							String dbUserName = "root";
							String dbPassword = ""; 
							
							String m="insert into accounts (date,Customer_name , invoice, Previous_due , Current_bill, Paid,Final_due,payment_type ) values ('" + sell_date + "','" + cname + "','"+ invoice + "','"+ pdue + "','"+ cur_bill + "','"+ s + "','"+ final_due + "','"+ pay_type + "')";
							String j="Update due set due_amount='" + (final_due)+ "' where name='"+cname+"'";
							try{
							    
							    Class.forName("com.mysql.jdbc.Driver");
							    Connection con = DriverManager.getConnection(dbName,dbUserName,dbPassword);
							    
							    Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
								Statement stm1=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
							     
							    stm.executeUpdate(m);
								JOptionPane.showMessageDialog(null,"Paid. "+cname+"'s Final Due is : BDT "+final_due);
								stm1.executeUpdate(j);
							    
								this.setVisible(false);
							   
									stm.close();
									con.close(); 
							}catch(Exception e){
								JOptionPane.showMessageDialog(null,e);
							}
						}
					}
				}else
			if(event.getSource()==date2){
					tdate.setText(new DatePicker(this).setPickedDate());
				}
	    }
    
	public static void main(String[] args) {
		Payment admin=new Payment();
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