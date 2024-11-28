package form;

import config.dbConnector;
import java.awt.Color;
import java.awt.Font;
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        signIn = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        fname = new javax.swing.JTextField();
        password = new javax.swing.JTextField();
        lname = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        username = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        phoneNumber = new javax.swing.JTextField();
        registerButton = new panelRoundComponents.PanelRound();
        jLabel5 = new javax.swing.JLabel();
        PhoneNumber1 = new javax.swing.JLabel();
        PhoneNumber2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        type = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sign up");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 820, 50));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setText("Password");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 330, -1, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("First Name");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("Last Name");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, -1, -1));

        signIn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        signIn.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        signIn.setText("Sign in");
        signIn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        signIn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signInMouseClicked(evt);
            }
        });
        jPanel1.add(signIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 600, 50, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("User Type");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 410, -1, -1));

        fname.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        fname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        fname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fnameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fnameFocusLost(evt);
            }
        });
        jPanel1.add(fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 290, 40));

        password.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        password.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 350, 290, 40));

        lname.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lname.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        lname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lnameFocusGained(evt);
            }
        });
        jPanel1.add(lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 290, 40));

        email.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        email.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, 290, 40));

        username.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        username.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, 290, 40));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Email Address");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, -1, -1));

        phoneNumber.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        phoneNumber.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        jPanel1.add(phoneNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 270, 290, 40));

        registerButton.setBackground(new java.awt.Color(89, 196, 19));
        registerButton.setRoundBottomLeft(60);
        registerButton.setRoundBottomRight(60);
        registerButton.setRoundTopLeft(60);
        registerButton.setRoundTopRight(60);
        registerButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                registerButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                registerButtonMouseReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("REGISTER");

        javax.swing.GroupLayout registerButtonLayout = new javax.swing.GroupLayout(registerButton);
        registerButton.setLayout(registerButtonLayout);
        registerButtonLayout.setHorizontalGroup(
            registerButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                .addContainerGap())
        );
        registerButtonLayout.setVerticalGroup(
            registerButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(registerButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(registerButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 510, 270, 70));

        PhoneNumber1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        PhoneNumber1.setForeground(new java.awt.Color(51, 51, 51));
        PhoneNumber1.setText("Phone Number");
        jPanel1.add(PhoneNumber1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 250, -1, -1));

        PhoneNumber2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        PhoneNumber2.setForeground(new java.awt.Color(153, 153, 153));
        PhoneNumber2.setText("Already have an account?");
        jPanel1.add(PhoneNumber2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 600, -1, -1));

        jLabel8.setForeground(new java.awt.Color(204, 204, 204));
        jLabel8.setText("____________________________________________________________________________________________________________________________________");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 800, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setText("Username");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 330, -1, -1));

        type.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User" }));
        type.setSelectedIndex(-1);
        jPanel1.add(type, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, 290, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 820, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 660, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void signInMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signInMouseClicked

        Loginfrom lf = new Loginfrom();
        lf.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_signInMouseClicked

    private void fnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fnameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_fnameFocusGained

    private void fnameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fnameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_fnameFocusLost

    private void lnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lnameFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_lnameFocusGained

    private void registerButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonMouseClicked

        dbConnector dbc = new dbConnector();
        String selectedItem = (String) type.getSelectedItem();

        if (fname.getText().isEmpty() || lname.getText().isEmpty() || email.getText().isEmpty() || phoneNumber.getText().isEmpty() || username.getText().isEmpty() || password.getText().isEmpty() || selectedItem == null){
            JOptionPane.showMessageDialog(null, "All fields are required!");
        } else if (password.getText().length() < 8){
            JOptionPane.showMessageDialog(null, "Password must be 8 above");
        } else if (isDuplicate()){
            System.out.println("Duplicate");
        } else {
            if (dbc.insertData("INSERT INTO user_table(fname, lname, email, uname, pass, contact, type, status)"
                + "VALUES('" + fname.getText() + "','" + lname.getText() + "','" + email.getText() + "','" + username.getText() + "','" + hashing(password.getText()) + "','" + phoneNumber.getText() + "','" + type.getSelectedItem() + "','Pending')"))
        {
            JOptionPane.showMessageDialog(null, "Inserted Successfully.");

        Loginfrom lf = new Loginfrom();
        lf.setVisible(true);
        this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Connection Error!");
        }
        }
    }//GEN-LAST:event_registerButtonMouseClicked

    private void registerButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonMousePressed
        registerButton.setBackground(new Color(89,196,19));
    }//GEN-LAST:event_registerButtonMousePressed

    private void registerButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonMouseReleased
        registerButton.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_registerButtonMouseReleased

    private void userTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userTypeActionPerformed
       
    }//GEN-LAST:event_userTypeActionPerformed

    private void registerButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonMouseEntered
        registerButton.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_registerButtonMouseEntered

    private void registerButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerButtonMouseExited
        registerButton.setBackground(new Color(89,196,19));
    }//GEN-LAST:event_registerButtonMouseExited

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
    private javax.swing.JLabel PhoneNumber1;
    private javax.swing.JLabel PhoneNumber2;
    private javax.swing.JTextField email;
    private javax.swing.JTextField fname;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField lname;
    private javax.swing.JTextField password;
    private javax.swing.JTextField phoneNumber;
    private panelRoundComponents.PanelRound registerButton;
    private javax.swing.JLabel signIn;
    public javax.swing.JComboBox<String> type;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
