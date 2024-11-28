
package User;

import config.dbConnector;
import java.awt.Color;
import javax.swing.JOptionPane;

public class addStock extends javax.swing.JFrame {


    public addStock() {
        initComponents();
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        productName = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        price = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        quant = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        category = new javax.swing.JComboBox<>();
        submitButton = new panelRoundComponents.PanelRound();
        jLabel3 = new javax.swing.JLabel();
        cancel = new panelRoundComponents.PanelRound();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setBackground(new java.awt.Color(128, 128, 128));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(128, 128, 128));
        jLabel6.setText("Product Details");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel1.setText("Product Name");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        productName.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        productName.setForeground(new java.awt.Color(130, 130, 130));
        productName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        productName.setPreferredSize(new java.awt.Dimension(6, 30));
        productName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                productNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                productNameFocusLost(evt);
            }
        });
        productName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productNameActionPerformed(evt);
            }
        });
        jPanel1.add(productName, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 160, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel16.setText("Price");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 100, -1));

        price.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        price.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel1.add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 160, 30));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel15.setText("Quantity");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 120, -1));

        quant.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        quant.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel1.add(quant, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 160, 30));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel14.setText("Category");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, -1, -1));

        category.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Snacks", "Drinks", "Canned goods", "Crackers", "Poultry products", "Beverage", "Condiments", "Dairy", "Grains ", "Bread", "Oil ", "Fat" }));
        category.setSelectedIndex(-1);
        jPanel1.add(category, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 160, 30));

        submitButton.setBackground(new java.awt.Color(83, 215, 105));
        submitButton.setRoundBottomLeft(10);
        submitButton.setRoundBottomRight(10);
        submitButton.setRoundTopLeft(10);
        submitButton.setRoundTopRight(10);
        submitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                submitButtonMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                submitButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                submitButtonMouseReleased(evt);
            }
        });
        submitButton.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ADD");
        submitButton.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 40));

        jPanel1.add(submitButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, 70, -1));

        cancel.setBackground(new java.awt.Color(252, 61, 57));
        cancel.setRoundBottomLeft(10);
        cancel.setRoundBottomRight(10);
        cancel.setRoundTopLeft(10);
        cancel.setRoundTopRight(10);
        cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cancelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cancelMouseReleased(evt);
            }
        });
        cancel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("CANCEL");
        cancel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 40));

        jPanel1.add(cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 310, 70, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 470, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void productNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_productNameFocusGained

    }//GEN-LAST:event_productNameFocusGained

    private void productNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_productNameFocusLost

    }//GEN-LAST:event_productNameFocusLost

    private void productNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productNameActionPerformed
        // TODO addStock your handling code here:
    }//GEN-LAST:event_productNameActionPerformed

    private void submitButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitButtonMouseClicked
        dbConnector dbc = new dbConnector();
        if (productName.getText().isEmpty() || quant.getText().isEmpty() || price.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required!");
        } else {
            if (dbc.insertData("INSERT INTO product_table (prod_name, category, price, quantity, prod_status)"
                + "VALUES('" + productName.getText() + "','" + category.getSelectedItem() + "','"+ price.getText()+"','"+ quant.getText() + "', 'Available')")) {
                
                
        }
        else {
            JOptionPane.showMessageDialog(null, "Connection Error!");
        }
        userDashboard ud = new userDashboard();
        ud.setVisible(true);
        this.dispose();
        }
    }//GEN-LAST:event_submitButtonMouseClicked

    private void submitButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitButtonMousePressed
        submitButton.setBackground(new Color(70,194,99));
    }//GEN-LAST:event_submitButtonMousePressed

    private void submitButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submitButtonMouseReleased
        submitButton.setBackground(new Color(83,215,105));
    }//GEN-LAST:event_submitButtonMouseReleased

    private void cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseClicked
        userDashboard ud = new userDashboard();
        ud.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancelMouseClicked

    private void cancelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMousePressed
        // TODO addStock your handling code here:
    }//GEN-LAST:event_cancelMousePressed

    private void cancelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseReleased
        // TODO addStock your handling code here:
    }//GEN-LAST:event_cancelMouseReleased

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
            java.util.logging.Logger.getLogger(addStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addStock().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private panelRoundComponents.PanelRound cancel;
    private javax.swing.JComboBox<String> category;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField price;
    private javax.swing.JTextField productName;
    private javax.swing.JTextField quant;
    private panelRoundComponents.PanelRound submitButton;
    // End of variables declaration//GEN-END:variables
}
