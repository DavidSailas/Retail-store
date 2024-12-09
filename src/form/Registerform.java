
package form;

import config.dbConnector;
import java.awt.Color;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Registerform extends javax.swing.JFrame {


    public Registerform() {
        initComponents();
    }

    public String userEmail;
    public String userUsername;
    
    public boolean isDuplicate(){
        dbConnector connector = new dbConnector();
        try{
            String query = "SELECT * FROM user_table  WHERE uname = '" + username.getText() + "' OR email = '" + email.getText() + "'";
            ResultSet resultSet = connector.getData(query);
            
            if (resultSet.next()){
                
                userEmail = resultSet.getString("email");
                if (userEmail.equalsIgnoreCase(email.getText())){
                    JOptionPane.showMessageDialog(null, "Email already used!");
                }
                
                userUsername = resultSet.getString("uname");
                if (userUsername.equalsIgnoreCase(username.getText())){
                    JOptionPane.showMessageDialog(null, "Username already used!");
                }
                
                return true;
            } else {
                return false;
            }
        }catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }
    
    public static String hashing(String Password){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            
            messageDigest.update(Password.getBytes());
            
            byte[] resultByteArray = messageDigest.digest();
            
            StringBuilder sb = new StringBuilder();
            
            for (byte b : resultByteArray){
                sb.append(String.format("%02x", b));
            }
            
            return sb.toString();
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Registerform.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        lname = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        username = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        phoneNumber = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        password = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        RegisterButton = new panelRoundComponents.PanelRound();
        jLabel14 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1000, 450));
        setPreferredSize(new java.awt.Dimension(1000, 450));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(1000, 450));
        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 450));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 450));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(30, 42, 71));
        jLabel1.setText("Sign in!");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, -1, 60));

        fname.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        fname.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        fname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnameActionPerformed(evt);
            }
        });
        jPanel1.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 130, 190, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("First name");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 110, -1, -1));

        lname.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lname.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        lname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lnameActionPerformed(evt);
            }
        });
        jPanel1.add(lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 130, 190, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("Last name");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 110, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Email");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 170, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Username");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 170, -1, -1));

        email.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        email.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        jPanel1.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, 190, -1));

        username.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        username.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });
        jPanel1.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 190, 190, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Password");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 230, -1, -1));

        phoneNumber.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        phoneNumber.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        phoneNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneNumberActionPerformed(evt);
            }
        });
        jPanel1.add(phoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 250, 190, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Contact No.");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 230, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 102, 102));
        jLabel9.setText("Already have an account?");

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(89, 196, 19));
        jLabel13.setText("Log in now!");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel13)))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 400, 223, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("_________________________");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 130, 190, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel16.setText("_________________________");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 250, 190, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("_________________________");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 130, 190, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("_________________________");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, 190, 30));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("_________________________");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 190, 190, 30));

        password.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        password.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        jPanel1.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 250, 190, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("_________________________");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 250, 190, 30));

        RegisterButton.setBackground(new java.awt.Color(89, 196, 19));
        RegisterButton.setRoundBottomLeft(60);
        RegisterButton.setRoundBottomRight(60);
        RegisterButton.setRoundTopLeft(60);
        RegisterButton.setRoundTopRight(60);
        RegisterButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RegisterButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                RegisterButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                RegisterButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                RegisterButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                RegisterButtonMouseReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("CREATE");
        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout RegisterButtonLayout = new javax.swing.GroupLayout(RegisterButton);
        RegisterButton.setLayout(RegisterButtonLayout);
        RegisterButtonLayout.setHorizontalGroup(
            RegisterButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RegisterButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addContainerGap())
        );
        RegisterButtonLayout.setVerticalGroup(
            RegisterButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        jPanel1.add(RegisterButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 320, 310, 60));

        jPanel4.setBackground(new java.awt.Color(89, 196, 19));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 450));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void fnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fnameActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void phoneNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phoneNumberActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_phoneNumberActionPerformed

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked

        Loginfrom lf = new Loginfrom();
        lf.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jLabel13MouseClicked

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordActionPerformed

    private void RegisterButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegisterButtonMouseClicked

dbConnector dbc = new dbConnector();
String userType = "User"; // Directly set type to "user"

if (fname.getText().isEmpty() || lname.getText().isEmpty() || email.getText().isEmpty() || phoneNumber.getText().isEmpty() || username.getText().isEmpty() || password.getText().isEmpty()) {
    JOptionPane.showMessageDialog(null, "All fields are required!");
} else if (password.getText().length() < 8) {
    JOptionPane.showMessageDialog(null, "Password must be 8 characters or more.");
} else if (isDuplicate()) {
    System.out.println("Duplicate");
} else {
    // Use userType directly in the SQL query
    if (dbc.insertData("INSERT INTO user_table(fname, lname, email, uname, pass, contact, type, status) "
            + "VALUES('" + fname.getText() + "','" + lname.getText() + "','" + email.getText() + "','" 
            + username.getText() + "','" + hashing(password.getText()) + "','" + phoneNumber.getText() 
            + "','" + userType + "','Pending')")) {
        JOptionPane.showMessageDialog(null, "Inserted Successfully.");
        Loginfrom lf = new Loginfrom();
        lf.setVisible(true);
        this.dispose();
    } else {
        JOptionPane.showMessageDialog(null, "Connection Error!");
    }
}

        
    }//GEN-LAST:event_RegisterButtonMouseClicked

    private void RegisterButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegisterButtonMouseEntered
        RegisterButton.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_RegisterButtonMouseEntered

    private void RegisterButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegisterButtonMouseExited
        RegisterButton.setBackground(new Color(89,196,19));
    }//GEN-LAST:event_RegisterButtonMouseExited

    private void RegisterButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegisterButtonMousePressed
        RegisterButton.setBackground(new Color(89,196,19));
    }//GEN-LAST:event_RegisterButtonMousePressed

    private void RegisterButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RegisterButtonMouseReleased
        RegisterButton.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_RegisterButtonMouseReleased

    private void lnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lnameActionPerformed

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
            java.util.logging.Logger.getLogger(Registerform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registerform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registerform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registerform.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registerform().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private panelRoundComponents.PanelRound RegisterButton;
    private javax.swing.JTextField email;
    private javax.swing.JTextField fname;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField lname;
    private javax.swing.JTextField password;
    private javax.swing.JTextField phoneNumber;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
