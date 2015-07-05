/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * user_acc.java
 *
 * Created on Nov 12, 2010, 11:45:50 AM
 */

package wizards;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import server.FileOperations.XML;
import server.FileOperations.encrypt_and_decrypt;

/**
 *
 * @author taifur
 */
public class user_acc extends javax.swing.JFrame {

    /** Creates new form user_acc */
    public user_acc() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel18 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel2.setBackground(new java.awt.Color(252, 252, 252));
        jPanel2.setMinimumSize(new java.awt.Dimension(710, 460));
        jPanel2.setLayout(null);

        jLabel10.setFont(new java.awt.Font("DejaVu Sans", 1, 14));
        jLabel10.setText("Welcome to Radiology Technology's file transfer wizard");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(20, 40, 450, 30);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/server/rack-server.jpg"))); // NOI18N
        jPanel2.add(jLabel11);
        jLabel11.setBounds(470, 0, 250, 330);

        jLabel12.setFont(new java.awt.Font("DejaVu Sans", 1, 12));
        jLabel12.setText("Step 2 of 9");
        jPanel2.add(jLabel12);
        jLabel12.setBounds(490, 410, 180, 30);

        jLabel13.setFont(new java.awt.Font("DejaVu Sans", 1, 12));
        jLabel13.setText("Welcome admin !");
        jPanel2.add(jLabel13);
        jLabel13.setBounds(20, 80, 190, 30);

        jLabel14.setFont(new java.awt.Font("DejaVu Sans", 1, 12));
        jLabel14.setText("Password :");
        jPanel2.add(jLabel14);
        jLabel14.setBounds(20, 260, 220, 20);
        jPanel2.add(jTextField1);
        jTextField1.setBounds(20, 220, 210, 30);

        jLabel15.setText("Please type the username and password ");
        jPanel2.add(jLabel15);
        jLabel15.setBounds(20, 110, 310, 30);

        jLabel16.setText("of desired user. He/ she would then log in by this");
        jPanel2.add(jLabel16);
        jLabel16.setBounds(20, 130, 340, 30);

        jLabel17.setText("username and password.");
        jPanel2.add(jLabel17);
        jLabel17.setBounds(20, 150, 250, 30);

        jButton3.setText("Create user");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3);
        jButton3.setBounds(20, 360, 150, 40);
        jPanel2.add(jPasswordField1);
        jPasswordField1.setBounds(20, 300, 210, 30);

        jLabel18.setFont(new java.awt.Font("DejaVu Sans", 1, 12));
        jLabel18.setText("Username :");
        jPanel2.add(jLabel18);
        jLabel18.setBounds(20, 190, 220, 20);

        jButton1.setText("Exit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(190, 359, 110, 40);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 710, 460);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            String temp_user_name = jTextField1.getText();
            char[] text1 = jPasswordField1.getPassword();
            String temp_user_pass = String.valueOf(text1);
            int i = 0;
            char temp;
            int count = 0;
            while (i < text1.length) {
                temp = text1[i];
                if (java.lang.Character.isLetter(temp) == true) {
                    count++;
                }
                i++;
            }
            XML x = new XML();
            encrypt_and_decrypt y = new encrypt_and_decrypt();
            y.decrypt("dbase.rtft", "dbase.xml");
            String[] children = x.get_child("dbase.xml", "user_info");
            boolean already_exist = false;
            for (int j = 0; j < children.length; j++) {
                if (children[j].equals(temp_user_name)) {
                    already_exist = true;
                }
            }

            if ( temp_user_name.isEmpty()) {

                 JOptionPane.showMessageDialog(rootPane, "Empty username");
            }
            if (count == 0 || text1.length < 6) {
                JOptionPane.showMessageDialog(rootPane, "Password must contain a letter, and atleast one character. Please try again.");
            } else if (temp_user_pass.isEmpty()) {
                JOptionPane.showMessageDialog(rootPane, "Empty password");
            } else if (already_exist == true) {
                JOptionPane.showMessageDialog(rootPane, "Username already exist.");
            } else {
                try {

                    y.decrypt("dbase.rtft", "dbase.xml");
                    x.add_sub_element("dbase.xml", "user_info", temp_user_name, temp_user_name);
                    x.add_sub_element("dbase.xml", temp_user_name, "user_pass", temp_user_pass);
                    x.add_sub_element("dbase.xml", temp_user_name, "first_time", "true");
                    y.encrypt("dbase.xml", "dbase.rtft");
                    JOptionPane.showMessageDialog(rootPane, "User successfully created !");
                    setVisible(false);
                } catch (ParserConfigurationException ex) {
                    Logger.getLogger(user_acc.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SAXException ex) {
                    Logger.getLogger(user_acc.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TransformerConfigurationException ex) {
                    Logger.getLogger(user_acc.class.getName()).log(Level.SEVERE, null, ex);
                } catch (TransformerException ex) {
                    Logger.getLogger(user_acc.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                    Logger.getLogger(user_acc.class.getName()).log(Level.SEVERE, null, ex);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(user_acc.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(user_acc.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(user_acc.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(user_acc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(user_acc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(user_acc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(user_acc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(user_acc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(user_acc.class.getName()).log(Level.SEVERE, null, ex);
        }

}//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

       setVisible(false);// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new user_acc().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

}