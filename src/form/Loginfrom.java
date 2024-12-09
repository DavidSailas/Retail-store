package form;

import Admin.adminDashboard;
import User.userDashboard;
import config.Session;
import config.dbConnector;
import java.awt.Color;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Loginfrom extends javax.swing.JFrame {


    public Loginfrom() {
        initComponents();
        show.setVisible(false);
    }

    public static String status;
    public static String type;
    
    public static boolean loginAcc(String username, String password){
        dbConnector connector = new dbConnector();
        try{
            String query = "SELECT * FROM user_table  WHERE uname = '" + username + "' AND pass = '" + password + "'";
            ResultSet resultSet = connector.getData(query);
            
            if (resultSet.next()){
                status = resultSet.getString("status");
                type = resultSet.getString("type");
                
                Session session = Session.getInstance();
                session.setUid(resultSet.getInt("id"));
                session.setFname(resultSet.getString("fname"));
                session.setLname(resultSet.getString("lname"));
                session.setEmail(resultSet.getString("email"));
                session.setUsername(resultSet.getString("uname"));
                session.setContact(resultSet.getString("contact"));
                session.setStatus(resultSet.getString("status"));
                session.setType(resultSet.getString("type"));
                
                return true;
            } else{
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
            Logger.getLogger(Loginfrom.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        username = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        hide = new javax.swing.JLabel();
        show = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        logInButton = new panelRoundComponents.PanelRound();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(30, 42, 71));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Welcome to ");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 340, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Let's get Started");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 430, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Username");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 60, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("__________________________________________________________");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, -1, 20));

        username.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        username.setBorder(null);
        jPanel2.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 290, 30));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Password");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 60, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("__________________________________________________________");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, -1, 20));

        hide.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/crossed-eye.png"))); // NOI18N
        hide.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hideMouseClicked(evt);
            }
        });
        jPanel2.add(hide, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, -1, -1));

        show.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/eye.png"))); // NOI18N
        show.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        show.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                showMouseClicked(evt);
            }
        });
        jPanel2.add(show, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 260, -1, -1));

        password.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        password.setBorder(null);
        password.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordActionPerformed(evt);
            }
        });
        jPanel2.add(password, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 290, 30));

        logInButton.setBackground(new java.awt.Color(89, 196, 19));
        logInButton.setRoundBottomLeft(60);
        logInButton.setRoundBottomRight(60);
        logInButton.setRoundTopLeft(60);
        logInButton.setRoundTopRight(60);
        logInButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logInButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logInButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logInButtonMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                logInButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                logInButtonMouseReleased(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("LOG IN");
        jLabel10.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout logInButtonLayout = new javax.swing.GroupLayout(logInButton);
        logInButton.setLayout(logInButtonLayout);
        logInButtonLayout.setHorizontalGroup(
            logInButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logInButtonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addContainerGap())
        );
        logInButtonLayout.setVerticalGroup(
            logInButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        jPanel2.add(logInButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 310, 60));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(89, 196, 19));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Posify!");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, 250, -1));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Don't have an account? ");

        jLabel14.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(89, 196, 19));
        jLabel14.setText("Sign up!");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)))
        );

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 390, 210, -1));

        jPanel3.setBackground(new java.awt.Color(89, 196, 19));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 520, 450));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1000, 450));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void passwordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordActionPerformed
       
    }//GEN-LAST:event_passwordActionPerformed

    private void hideMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hideMouseClicked
        hide.setVisible(false);
        show.setVisible(true);
        password.setEchoChar((char) 0);
    }//GEN-LAST:event_hideMouseClicked

    private void showMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showMouseClicked
        hide.setVisible(true);
        show.setVisible(false);
        password.setEchoChar('*');
    }//GEN-LAST:event_showMouseClicked

    private void logInButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logInButtonMouseClicked
        if (loginAcc(username.getText(), hashing(password.getText()))){
            if (!status.equalsIgnoreCase("Active")){
                JOptionPane.showMessageDialog(null, "Your account is In-Active, Please contact the Administrator!");
            } else {
                if (type.equals("Admin")){
                    JOptionPane.showMessageDialog(null, "Log in successfully");
                    adminDashboard admin = new adminDashboard();
                    admin.setVisible(true);
                    this.dispose();
                } else if (type.equals("User")){
                    JOptionPane.showMessageDialog(null, "Log in successfully");
                    userDashboard user = new userDashboard();
                    user.setVisible(true);
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Account does not exist!", "Notice", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Log in failed");
        }
    }//GEN-LAST:event_logInButtonMouseClicked

    private void logInButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logInButtonMouseEntered
        logInButton.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_logInButtonMouseEntered

    private void logInButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logInButtonMouseExited
        logInButton.setBackground(new Color(89,196,19));
    }//GEN-LAST:event_logInButtonMouseExited

    private void logInButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logInButtonMousePressed
        logInButton.setBackground(new Color(89,196,19));
    }//GEN-LAST:event_logInButtonMousePressed

    private void logInButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logInButtonMouseReleased
        logInButton.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_logInButtonMouseReleased

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked

        Registerform rf = new Registerform();
        rf.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jLabel14MouseClicked


    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Loginfrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Loginfrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Loginfrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Loginfrom.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Loginfrom().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel hide;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private panelRoundComponents.PanelRound logInButton;
    private javax.swing.JPasswordField password;
    private javax.swing.JLabel show;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
