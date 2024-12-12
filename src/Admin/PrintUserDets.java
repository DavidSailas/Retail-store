/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import config.dbConnector;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/**
 *
 * @author chris
 */
public class PrintUserDets extends javax.swing.JFrame {

    /**
     * Creates new form PrintUserDets
     */
    public PrintUserDets() {
        initComponents();
         date();
    }

     private void date() {
       
        Date d = new Date();
    
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd,yyyy");
        String dt = sdf.format(d);
        date.setText(dt);
    }

    
        Timer t;
        SimpleDateFormat st;
    
    private void time(){

        t = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                Date dt = new Date();
                st = new SimpleDateFormat("hh:mm:ss a");
                
                String tm = st.format(dt);              
            }
        });
        
        t.start();
        
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        page = new javax.swing.JPanel();
        date = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        image = new javax.swing.JLabel();
        uid = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        fullname = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        umail = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        type = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(89, 196, 19));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        page.setBackground(new java.awt.Color(255, 255, 255));
        page.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(89, 196, 19), 2));
        page.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        date.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        date.setText("DATE");
        page.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 530, 30));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("SYSTEM USER DETAILS REPORT");
        page.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 530, 30));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(89, 196, 19));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("IMPos");
        page.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 47, 530, -1));

        image.setBackground(new java.awt.Color(255, 255, 255));
        image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(27, 57, 77), 2));
        page.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 262, 170, 170));

        uid.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        uid.setText("id");
        page.add(uid, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 282, 60, -1));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("ID:");
        page.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 282, 50, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Name:");
        page.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 312, 70, -1));

        fullname.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        fullname.setText("name");
        page.add(fullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 312, 190, -1));

        username.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        username.setText("username");
        page.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 342, 190, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("User:");
        page.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 342, 70, -1));

        umail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        umail.setText("email");
        page.add(umail, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 372, 190, -1));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 102, 102));
        jLabel11.setText("Email:");
        page.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 372, 70, -1));

        type.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        type.setText("type");
        page.add(type, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 402, 120, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Type:");
        page.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 402, 60, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(89, 196, 19));
        jLabel2.setText("________________________________________________________");
        page.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        jPanel1.add(page, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 530, 520));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 617, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 595, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
 
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrintUserDets.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrintUserDets.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrintUserDets.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrintUserDets.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrintUserDets().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel date;
    public javax.swing.JLabel fullname;
    public javax.swing.JLabel image;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JPanel page;
    public javax.swing.JLabel type;
    public javax.swing.JLabel uid;
    public javax.swing.JLabel umail;
    public javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
