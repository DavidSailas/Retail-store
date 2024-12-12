/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package User;

import config.dbConnector;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author PC
 */
public class payment extends javax.swing.JFrame {

    /**
     * Creates new form payment
     */
    public payment() {
        initComponents();
    }

    public static String getCurrentDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return today.format(formatter);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        printButton = new panelRoundComponents.PanelRound();
        jLabel19 = new javax.swing.JLabel();
        cancelButton = new panelRoundComponents.PanelRound();
        jLabel21 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        cash = new javax.swing.JTextField();
        change = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        total = new javax.swing.JTextField();
        quantity = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        availstock = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        price = new javax.swing.JTextField();
        pid = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(248, 248, 248));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(89, 196, 19), 2));
        jPanel1.setMinimumSize(new java.awt.Dimension(440, 500));
        jPanel1.setPreferredSize(new java.awt.Dimension(451, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        printButton.setBackground(new java.awt.Color(83, 215, 105));
        printButton.setToolTipText("Print");
        printButton.setRoundBottomLeft(10);
        printButton.setRoundBottomRight(10);
        printButton.setRoundTopLeft(10);
        printButton.setRoundTopRight(10);
        printButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                printButtonMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                printButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                printButtonMouseReleased(evt);
            }
        });

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Pay");

        javax.swing.GroupLayout printButtonLayout = new javax.swing.GroupLayout(printButton);
        printButton.setLayout(printButtonLayout);
        printButtonLayout.setHorizontalGroup(
            printButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, printButtonLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        printButtonLayout.setVerticalGroup(
            printButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jPanel1.add(printButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 430, -1, -1));

        cancelButton.setBackground(new java.awt.Color(252, 61, 57));
        cancelButton.setToolTipText("Cancel");
        cancelButton.setRoundBottomLeft(10);
        cancelButton.setRoundBottomRight(10);
        cancelButton.setRoundTopLeft(10);
        cancelButton.setRoundTopRight(10);
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelButtonMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                cancelButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cancelButtonMouseReleased(evt);
            }
        });

        jLabel21.setBackground(new java.awt.Color(255, 255, 255));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Cancel ");

        javax.swing.GroupLayout cancelButtonLayout = new javax.swing.GroupLayout(cancelButton);
        cancelButton.setLayout(cancelButtonLayout);
        cancelButtonLayout.setHorizontalGroup(
            cancelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cancelButtonLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jLabel21)
                .addGap(29, 29, 29))
        );
        cancelButtonLayout.setVerticalGroup(
            cancelButtonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cancelButtonLayout.createSequentialGroup()
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(cancelButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 430, -1, -1));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Payment:");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 200, -1, 28));

        cash.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        cash.setText(" ");
        cash.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        cash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashActionPerformed(evt);
            }
        });
        jPanel1.add(cash, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 195, 37));

        change.setEditable(false);
        change.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        change.setText(" ");
        change.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        change.setEnabled(false);
        change.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeActionPerformed(evt);
            }
        });
        jPanel1.add(change, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 340, 195, 37));

        jLabel23.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(102, 102, 102));
        jLabel23.setText("Change:");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, -1, 28));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(89, 196, 19));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Cash payment");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 24, 420, -1));

        jLabel24.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(102, 102, 102));
        jLabel24.setText("Total:");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 300, -1, 28));

        total.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        total.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        total.setEnabled(false);
        total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalActionPerformed(evt);
            }
        });
        jPanel1.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 195, 37));

        quantity.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        quantity.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityActionPerformed(evt);
            }
        });
        quantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                quantityKeyReleased(evt);
            }
        });
        jPanel1.add(quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 140, 195, 35));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Quantity:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 150, -1, -1));

        jLabel16.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Available Stock:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        availstock.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        availstock.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        availstock.setEnabled(false);
        availstock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                availstockActionPerformed(evt);
            }
        });
        jPanel1.add(availstock, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 90, 195, 35));

        jLabel20.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("Price:");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, -1, 28));

        price.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        price.setText(" ");
        price.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        price.setEnabled(false);
        price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceActionPerformed(evt);
            }
        });
        jPanel1.add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, 195, 37));

        pid.setForeground(new java.awt.Color(255, 255, 255));
        pid.setText("jLabel2");
        jPanel1.add(pid, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 440, 10));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 438, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    double customerChange;
    int productNewQuantity;
    String productId;
    int quantitySold;
    
    private void printButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printButtonMouseClicked

dbConnector dbc = new dbConnector();
userDashboard sell = new userDashboard();

try {
    double cashAmount = Double.parseDouble(cash.getText().trim()); // Amount provided by the user

    // Fetch product details
    ResultSet rs = dbc.getData("SELECT quantity, price FROM product_table WHERE prod_id = '" + pid.getText().trim() + "'");
    if (rs.next()) {
        int availableQuantity = rs.getInt("quantity"); // Quantity available in stock
        double productPrice = rs.getDouble("price"); // Price per item

        // Validate user-entered quantity
        if (!quantity.getText().isEmpty()) {
            int desiredQuantity = Integer.parseInt(quantity.getText().trim()); // Quantity user wants to buy

            if (desiredQuantity > 0) {
                if (availableQuantity >= desiredQuantity) {
                    // Calculate the total amount
                    double totalAmount = desiredQuantity * productPrice;
                    total.setText(String.format("₱%.2f", totalAmount)); // Display total in total JTextField

                    double changeAmount = cashAmount - totalAmount; // Calculate change
                    if (changeAmount >= 0) {
                        // Update the database
                        int newQuantity = availableQuantity - desiredQuantity;
                        dbc.updateData("UPDATE product_table SET quantity = " + newQuantity + 
                                       (newQuantity == 0 ? ", prod_status = 'Out of stock'" : "") + 
                                       " WHERE prod_id = '" + pid.getText().trim() + "'");

                        // Get current date and time
                        LocalDateTime now = LocalDateTime.now();
                        String currentDate = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // Date part
                        String currentTime = now.format(DateTimeFormatter.ofPattern("HH:mm:ss")); // Time part

                        // Insert into sales table with both date and time
                        dbc.updateData("INSERT INTO sales (prod_id, quantity_sold, Date, time) VALUES ('" + 
                                        pid.getText().trim() + "', " + desiredQuantity + 
                                        ", '" + currentDate + "', '" + currentTime + "')");

                        // Notify user of successful purchase
                        change.setText(String.format("₱%.2f", changeAmount)); // Display change
                        JOptionPane.showMessageDialog(
                            null,
                            "<html><h2 style='color: green;'>Purchase Successful!</h2>" +
                                    "<p>Total Amount: <b>₱" + String.format("%.2f", totalAmount) + "</b></p>" +
                                    "<p>Change: <b>₱" + String.format("%.2f", changeAmount) + "</b></p>" +
                                    "<p>Date: " + currentDate + "</p>" +
                                    "<p>Time: " + currentTime + "</p></html>",
                            "Transaction Complete",
                            JOptionPane.INFORMATION_MESSAGE
                        );

                        sell.displayData(); // Refresh user dashboard

                        // Return to user dashboard
                        sell.setVisible(true);
                        this.dispose();
                    } else {
                        // Notify insufficient cash
                        JOptionPane.showMessageDialog(
                            null,
                            "<html><h2 style='color: red;'>Insufficient Cash!</h2>" +
                                    "<p>You need <b>₱" + String.format("%.2f", -changeAmount) + "</b> more.</p></html>",
                            "Transaction Error",
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                } else {
                    // Notify insufficient stock
                    JOptionPane.showMessageDialog(
                        null,
                        "<html><h2 style='color: red;'>Insufficient Stock!</h2>" +
                                "<p>Only <b>" + availableQuantity + "</b> items available.</p></html>",
                        "Stock Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            } else {
                JOptionPane.showMessageDialog(
                    null,
                    "<html><h2 style='color: red;'>Invalid Quantity!</h2>" +
                            "<p>Please enter a quantity greater than 0.</p></html>",
                    "Input Error",
                    JOptionPane.WARNING_MESSAGE
                );
            }
        } else {
            JOptionPane.showMessageDialog(
                null,
                "<html><h2 style='color: red;'>Missing Quantity!</h2>" +
                        "<p>Please enter a quantity to purchase.</p></html>",
                "Input Error",
                JOptionPane.WARNING_MESSAGE
            );
        }
    } else {
        JOptionPane.showMessageDialog(
            null,
            "<html><h2 style='color: red;'>Product Not Found!</h2>" +
                    "<p>The product ID you entered does not exist.</p></html>",
            "Database Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
    rs.close();
} catch (NumberFormatException ex) {
    JOptionPane.showMessageDialog(
        null,
        "<html><h2 style='color: red;'>Invalid Input!</h2>" +
                "<p>Please enter valid numbers for cash and quantity.</p></html>",
        "Input Error",
        JOptionPane.WARNING_MESSAGE
    );
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(
        null,
        "<html><h2 style='color: red;'>Database Error!</h2>" +
                "<p>" + ex.getMessage() + "</p></html>",
        "Database Error",
        JOptionPane.ERROR_MESSAGE
    );
}

        
    }//GEN-LAST:event_printButtonMouseClicked

    private void printButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printButtonMousePressed
        printButton.setBackground(new Color(70,194,99));
    }//GEN-LAST:event_printButtonMousePressed

    private void printButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_printButtonMouseReleased
        printButton.setBackground(new Color(83,215,105));
    }//GEN-LAST:event_printButtonMouseReleased

    private void cancelButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseClicked
        this.dispose();
    }//GEN-LAST:event_cancelButtonMouseClicked

    private void cancelButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMousePressed
        cancelButton.setBackground(new Color(227,52,55));
    }//GEN-LAST:event_cancelButtonMousePressed

    private void cancelButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseReleased
        cancelButton.setBackground(new Color(252,61,57));
    }//GEN-LAST:event_cancelButtonMouseReleased

    private void changeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_changeActionPerformed

    private void quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityActionPerformed

    private void totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalActionPerformed

    private void cashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cashActionPerformed

    private void availstockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_availstockActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_availstockActionPerformed

    private void priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceActionPerformed

    private void quantityKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantityKeyReleased

try {
    // Check if the quantity field is empty
    String quantityText = quantity.getText().trim();
    if (quantityText.isEmpty()) {
        total.setText(""); // Clear the total if quantity is empty
        return; // Exit the method
    }

    // Parse the quantity
    int q = Integer.parseInt(quantityText);

    // Validate the quantity
    if (q == 0) {
        JOptionPane.showMessageDialog(null, "Quantity cannot be 0");
    } else {
        // Parse the price and calculate the total
        String priceText = price.getText().trim();
        if (priceText.isEmpty()) {
            total.setText(""); // Clear the total if price is empty
            return; // Exit the method
        }

        double p = Double.parseDouble(priceText);
        double t = p * q;

        // Set the total
        total.setText(String.format("%.2f", t));
    }
} catch (NumberFormatException e) {
    JOptionPane.showMessageDialog(null, "Please enter valid numbers for quantity and price.");
}

          
    }//GEN-LAST:event_quantityKeyReleased

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
            java.util.logging.Logger.getLogger(payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new payment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField availstock;
    private panelRoundComponents.PanelRound cancelButton;
    private javax.swing.JTextField cash;
    private javax.swing.JTextField change;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JPanel jPanel1;
    public javax.swing.JLabel pid;
    public javax.swing.JTextField price;
    private panelRoundComponents.PanelRound printButton;
    private javax.swing.JTextField quantity;
    public javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables
}
