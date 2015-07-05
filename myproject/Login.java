import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*; // For Database Access

public class Login extends JFrame implements ActionListener{
    
    JLabel login_id,login_pass,lock,bg_image;
    JTextField login_tid;
    JPasswordField login_tpass;
    JButton login_login;

    public Login(){
        Container c1=getContentPane();
        c1.setLayout(null);
        
		Color color1 = new Color(199,250,223);
		Color color2 = new Color(240,255,255);
		
		c1.setBackground(Color.white);
	    
        login_id=new JLabel("User Name");
        c1.add(login_id);
        login_pass=new JLabel("Password");
        c1.add(login_pass);
		//login_tid.setBackground(color2);
        login_tpass=new JPasswordField();
        c1.add(login_tpass);
	        //login_tpass.setBackground(color2);
	    ImageIcon login_img = new ImageIcon("images/d1.png");
        login_login=new JButton("",login_img);
        c1.add(login_login);
	
	
	lock=new JLabel("");
        c1.add(lock);
	lock.setForeground(Color.red);
	
	
	
        login_tid=new JTextField();
        c1.add(login_tid);
	login_tpass=new JPasswordField();
        c1.add(login_tpass);
	
	bg_image=new JLabel("");
        c1.add(bg_image);
	//lock.setIcon(new ImageIcon("images/lock.png"));
	
	login_login.setPressedIcon(new ImageIcon("images/d2.png"));
        
	    login_id.setBounds(70,137,70,25);
	    login_tid.setBounds(70,160,150,25);
	    login_pass.setBounds(70,190,70,25);
	    login_tpass.setBounds(70,210,150,25);
	    login_login.setBounds(200,40,280,294);
	    lock.setBounds(20,10,207,25);
	    bg_image.setBounds(0,0,1360,683);
        
	login_login.addActionListener(this);
	
	login_login.setHorizontalTextPosition(SwingConstants.CENTER);
	login_login.setBorderPainted(false);
	login_login.setContentAreaFilled(false);
	
        this.setSize(520,400);
        this.setVisible(true);
        setTitle("User Login");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setLocationRelativeTo(null);
	this.setResizable(false);
	this.setIconImage(new ImageIcon("images/favicon.png").getImage());
	
	bg_image.setIcon(new ImageIcon("images/image6.jpg"));
    
    }
    
    public void actionPerformed(ActionEvent event1){
		if(event1.getSource()==login_login){
			String searchid=login_tid.getText();
			String searchpass=login_tpass.getText();
                        loginUser(searchid,searchpass);
		}
	    
	}
	
    
    void loginUser(String searchid,String searchpass){
        String dbName ="jdbc:mysql://localhost:3306/program_data";
        String dbUserName = "root";
        String dbPassword = ""; 
        
        String q="Select * from login where id='"+searchid+"'";
        
        try{
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(dbName,dbUserName,dbPassword);
            
            Statement stm=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
             
            ResultSet rs=stm.executeQuery(q);
            
            if(rs.next()){
                String n=rs.getString("id");
                String a = rs.getString("pass");
                
                if(n.equals(login_tid.getText()) && a.equals(login_tpass.getText())){
		     this.setVisible(false);
                    AdminPanel admin=new AdminPanel();
                }
                else{
			lock.setText("Login failed.Wrong Password.");
                }
                
            }else{
                
                lock.setText("User Not Found");
            }
                        rs.close();
			stm.close();
			con.close(); 
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    public static void main(String args[]){
        Login l1=new Login();
    }
}

