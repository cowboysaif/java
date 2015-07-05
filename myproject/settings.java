import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*; // For Database Access

public class settings extends JFrame implements ActionListener,ItemListener{
	JButton setbg;
	JComboBox combo2;
	JLabel bg_image;
	
	
	public settings(){
		
		Container c1=getContentPane();
		c1.setLayout(null);
	    
	    
	    String image_s[]=new String[8];
	    image_s[0]="image1.jpg";
	    image_s[1]="image2.jpg";
	    image_s[2]="image3.jpg";
	    image_s[3]="image4.jpg";
	    image_s[4]="image5.jpg";
	    image_s[5]="image6.jpg";
		
	    combo2=new JComboBox(image_s);
		c1.add(combo2);
		//combo2.setBackground(color2);
		
		
		combo2.setSelectedIndex(0);
		combo2.addItemListener(this);
		combo2.addActionListener(this);
		    combo2.setBounds(40,50,150,25);
		    setbg=new JButton("Set");
		    c1.add(setbg);
		    setbg.setBounds(200,50,100,25);
		    setbg.addActionListener(this);
		    
		setTitle("Admin Panel");
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
		
	public void itemStateChanged(ItemEvent e){
	    int selection;
	    selection=combo2.getSelectedIndex();
	    }
	public void actionPerformed(ActionEvent event){
		if(event.getSource()==setbg){
				Object name1=combo2.getSelectedItem();
				String name=name1.toString();
			
			
				if(name.equals("image1.jpg")){
					String a="255";
					String b="0";
					String c="0";
					update_img(name,a,b,c);
					}else
					if(name.equals("image2.jpg")){
					String a="255";
					String b="255";
					String c="255";
					update_img(name,a,b,c);
					}else
					if(name.equals("image3.jpg")){
					String a="13";
					String b="13";
					String c="13";
					update_img(name,a,b,c);
					}else
					if(name.equals("image4.jpg")){
					String a="255";
					String b="99";
					String c="71";
					update_img(name,a,b,c);
					}else
					if(name.equals("image5.jpg")){
					String a="255";
					String b="255";
					String c="255";
					update_img(name,a,b,c);
					}else
					if(name.equals("image6.jpg")){
					String a="0";
					String b="0";
					String c="0";
					update_img(name,a,b,c);
					}
				
				//a1.bg_image.setIcon(new ImageIcon("images/"+name));
					this.setVisible(false);
			}
		
		}
	void update_img(String name,String a,String b,String c){
			String dbName ="jdbc:mysql://localhost:3306/program_data";
			String dbUserName = "root";
			String dbPassword = ""; 
			
			String q="Update settings set img_name='" + (name)+ "', a='" + (a)+ "', b='" + (b)+ "', c='" + (c)+ "'";
			
			    
			try{
			    
			    Class.forName("com.mysql.jdbc.Driver");
			    Connection con = DriverManager.getConnection(dbName,dbUserName,dbPassword);
			    
			    Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			     
			  
			    
			    stm.executeUpdate(q);
				JOptionPane.showMessageDialog(null,"Image Successfully set. Just Refresh The Admin Panel");
			    
					stm.close();
					con.close(); 
			}catch(Exception e){
				JOptionPane.showMessageDialog(null,e);
			}
		
		}
	
	public static void main(String arga[]){
		settings s1=new settings();
		
		}
	
	
	}