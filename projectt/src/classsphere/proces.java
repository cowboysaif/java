/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * proces.java
 *
 * Created on Jul 10, 2011, 12:43:41 AM
 */
package classsphere;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import java.awt.Component;
import javax.swing.UIManager;
/**
 *
 * @author Anindo
 */
public class proces extends javax.swing.JFrame {

    /** Creates new form proces */
    public proces() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("COMPLETED SUCCESSFULLY");

        jPanel1.setBackground(new java.awt.Color(51, 153, 0));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/classsphere/Untitled.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 30));
        jLabel2.setForeground(new java.awt.Color(153, 255, 0));
        jLabel2.setText("PASSPORT PROCESS");

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(153, 255, 0));
        jLabel3.setText("YOU HAVE SUCCESSFULLY COMPLETED DIGITALIZED ELECTRONIC PASSPORT");

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(153, 255, 0));
        jLabel4.setText("APPLICATION PROCESS.CONGRATS.");

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(153, 255, 0));
        jLabel5.setText("PRESS NEXT TO GET YOUR DELIVERY SLIP & PRINT IT.");

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 18));
        jLabel6.setForeground(new java.awt.Color(153, 255, 0));
        jLabel6.setText("PLEASE BRING YOUR NATIONAL ID CARD & DELIVERY SLIP AT THE TIME OF");

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 18));
        jLabel7.setForeground(new java.awt.Color(153, 255, 0));
        jLabel7.setText("PASSPORT DELIVERY.");

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 18));
        jLabel8.setForeground(new java.awt.Color(153, 255, 0));
        jLabel8.setText("IF AUTHORITY FIND ANY KIND OF LACKINGS IN YOUR APPLICATION THEY WILL ");

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 18));
        jLabel9.setForeground(new java.awt.Color(153, 255, 0));
        jLabel9.setText("INFORM YOU THROUGH SMS OR EMAIL.");

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 18));
        jLabel10.setForeground(new java.awt.Color(153, 255, 0));
        jLabel10.setText("POLICE VARIFICATION WILL TAKE PLACE WITHIN 7 DAYS.");

        jButton1.setBackground(new java.awt.Color(153, 255, 0));
        jButton1.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jButton1.setText("DELIVERY SLIP");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(153, 255, 0));
        jButton2.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jButton2.setText("VOICE INSTRUCTION");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(346, 346, 346)
                .addComponent(jLabel2)
                .addContainerGap(321, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addContainerGap(105, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(441, 441, 441)
                .addComponent(jButton1)
                .addContainerGap(383, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(412, 412, 412)
                .addComponent(jButton2)
                .addContainerGap(352, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(40, 40, 40)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addGap(32, 32, 32)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        delivery d=new delivery();
d.setVisible(true);

       delivery. jLabel10.setText(passform.jTextField1.getText());
      delivery. jLabel31.setText(passform.jTextField1.getText());
      delivery. jLabel11.setText(passform.jTextField4.getText());
      delivery. jLabel32.setText(passform.jTextField2.getText());
      delivery. jLabel33.setText(passform.jTextField5.getText());
      delivery. jLabel12.setText(passform2.jTextField3.getText());
      delivery. jLabel13.setText(passform2.jTextField6.getText());
      delivery. jLabel14.setText(passform2.jTextField4.getText());
      delivery. jLabel16.setText(passform2.jTextField5.getText());
      delivery. jLabel29.setText(passform.jTextField14.getText());
      delivery. jLabel30.setText(passform.jTextField14.getText());
      delivery. jLabel28.setText(passform3.jTextField1.getText());
      delivery. jLabel35.setText(passform3.jTextField7.getText());
      delivery. jLabel36.setText(passform3.jTextField8.getText());
      delivery. jLabel41.setText(passform3.jTextField10.getText());
      delivery. jLabel34.setText(passform3.jTextField10.getText());
      
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      String stext ="YOU            HAVE             SUCCESSFULLY            COMPLETED          DIGITALIZED             ELECTRONIC          PASSPORT            APPLICATION             PROCESS.            CONGRATS.           PRESS            NEXT           TO           GET            YOUR            DELIVERY             SLIP           AND            PRINT            IT.";
        if (stext != null && stext.trim().length() > 0){
            VoiceManager voiceManager = null;
            Voice syntheticVoice = null;
            
            try {
   //             TextToBeSpoken.setText(TextToBeSpoken.getText()+"PH0 conmpleted");
voiceManager = VoiceManager.getInstance();
//TextToBeSpoken.setText(TextToBeSpoken.getText()+"PH1 conmpleted");
syntheticVoice = voiceManager.getVoice("kevin");
//TextToBeSpoken.setText(TextToBeSpoken.getText()+"PH2 conmpleted");
syntheticVoice.allocate();
//TextToBeSpoken.setText(TextToBeSpoken.getText()+"PH3 conmpleted");
syntheticVoice.speak(stext);
//super("Done!");
//TextToBeSpoken.setText(TextToBeSpoken.getText()+"DONE!");
} catch (Exception e) {
} finally {
syntheticVoice.deallocate();
}
        }    // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new proces().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
