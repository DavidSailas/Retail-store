
package User;

import config.dbConnector;
import form.Loginfrom;
import java.awt.Color;
import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;


public class sellproduct extends javax.swing.JFrame {


    public sellproduct() {
        initComponents();
        displayData();
    }
    
public void displayData() {
    try {
        dbConnector dbc = new dbConnector();

        // Fetch data from the database, including prod_id, quantity, and expire
        ResultSet rs = dbc.getData(
            "SELECT prod_id, prod_name, prod_status, category, price, quantity, expire " +
            "FROM product_table"
        );

        // Set up the table model with an additional column for prod_id
        DefaultTableModel model = new DefaultTableModel(new String[]{
            "Product ID", "Product Name", "Product Status", "Category", "Price"
        }, 0);

        // Get the current date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());

        // Populate the model with data
        while (rs.next()) {
            String prodId = rs.getString("prod_id");
            String productName = rs.getString("prod_name");
            String productStatus = rs.getString("prod_status");
            String category = rs.getString("category");
            double price = rs.getDouble("price");
            int quantity = rs.getInt("quantity");
            String expireDate = rs.getString("expire");

            // If the quantity is 0, set the product status to "Out of stock"
            if (quantity == 0) {
                productStatus = "Out of stock";
            }

            // Check the expiration date
            if (!"0001-12-31".equals(expireDate)) { // Exclude non-expiring products
                try {
                    Date expire = dateFormat.parse(expireDate);
                    Date current = dateFormat.parse(currentDate);
                    if (expire.before(current)) {
                        continue; // Skip expired products
                    }
                } catch (Exception e) {
                    System.out.println("Error parsing date: " + e.getMessage());
                }
            }

            // Add row to model
            model.addRow(new Object[]{
                prodId, // this will be hidden in the table
                productName,
                productStatus,
                category,
                "₱" + String.format("%.2f", price)
            });
        }

        // Set the table model
        stock.setModel(model);

        // Hide the Product ID column
        stock.getColumnModel().getColumn(0).setMinWidth(0);
        stock.getColumnModel().getColumn(0).setMaxWidth(0);
        stock.getColumnModel().getColumn(0).setWidth(0);

        // Custom cell renderer for product status
        stock.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                if (value != null) {
                    String status = value.toString();
                    if (status.equals("Available")) {
                        comp.setForeground(Color.GREEN);  // Green for Available
                    } else if (status.equals("Out of stock")) {
                        comp.setForeground(Color.RED);  // Red for Out of stock
                    } else {
                        comp.setForeground(Color.BLACK);  // Default for other statuses
                    }
                }
                return comp;
            }
        });

        rs.close();
    } catch (SQLException ex) {
        System.out.println("Errors: " + ex.getMessage());
    }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        stock = new javax.swing.JTable();
        label = new javax.swing.JLabel();
        searchBar = new javax.swing.JTextField();
        buyButton = new panelRoundComponents.PanelRound();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        label1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        panel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(89, 196, 19), 2));
        jPanel2.setMinimumSize(new java.awt.Dimension(785, 480));
        jPanel2.setPreferredSize(new java.awt.Dimension(785, 480));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("COUNTER");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        stock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        stock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stockMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(stock);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 760, 310));

        label.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        label.setForeground(new java.awt.Color(102, 102, 102));
        label.setText("Available Product's");
        jPanel2.add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 180, -1));

        searchBar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        searchBar.setForeground(new java.awt.Color(102, 102, 102));
        searchBar.setText(" Search");
        searchBar.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        searchBar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchBarFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchBarFocusLost(evt);
            }
        });
        searchBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBarActionPerformed(evt);
            }
        });
        searchBar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchBarKeyReleased(evt);
            }
        });
        jPanel2.add(searchBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, 280, -1));

        buyButton.setBackground(new java.awt.Color(83, 215, 105));
        buyButton.setRoundBottomLeft(10);
        buyButton.setRoundBottomRight(10);
        buyButton.setRoundTopLeft(10);
        buyButton.setRoundTopRight(10);
        buyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buyButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buyButtonMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                buyButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                buyButtonMouseReleased(evt);
            }
        });
        buyButton.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-buy-18.png"))); // NOI18N
        jLabel6.setText("BUY");
        buyButton.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 0, 90, 40));

        jPanel2.add(buyButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 450, 120, 40));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(89, 196, 19));
        jLabel7.setText("_____________________________________________________________________");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 760, -1));

        label1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        label1.setForeground(new java.awt.Color(102, 102, 102));
        label1.setText("Search:");
        jPanel2.add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 60, 60, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 800, 500));

        jPanel1.setBackground(new java.awt.Color(89, 196, 19));
        jPanel1.setPreferredSize(new java.awt.Dimension(200, 450));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel1.setBackground(new java.awt.Color(89, 196, 19));
        panel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel1MouseExited(evt);
            }
        });
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/selling (1).png"))); // NOI18N
        jLabel2.setText(" Counter");
        panel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 10, 180, -1));

        jPanel1.add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 200, 40));

        panel.setBackground(new java.awt.Color(89, 196, 19));
        panel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelMouseExited(evt);
            }
        });
        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/boxes.png"))); // NOI18N
        jLabel3.setText(" Inventory");
        panel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 10, 200, -1));

        jPanel1.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 200, 40));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Log out");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 470, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        title.setForeground(new java.awt.Color(0, 102, 0));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/IMPos (2).png"))); // NOI18N
        title.setText("IMPos");
        jPanel3.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 200, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 500));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void stockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stockMouseClicked


    }//GEN-LAST:event_stockMouseClicked

    private void searchBarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBarFocusGained
        if (searchBar.getText().equals(" Search")){
            searchBar.setText("");
            searchBar.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_searchBarFocusGained

    private void searchBarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBarFocusLost
        if (searchBar.getText().equals("")){
            searchBar.setText(" Search");
            searchBar.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_searchBarFocusLost

    private void searchBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBarActionPerformed

    private void searchBarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBarKeyReleased

try {
    dbConnector dbc = new dbConnector();
    String searchText = searchBar.getText().trim();
    ResultSet rs;

    // Modify the query to select the prod_id as well
    if (searchText.isEmpty()) {
        rs = dbc.getData("SELECT prod_id, prod_name, prod_status, category, price, quantity, expire FROM product_table");
    } else {
        rs = dbc.getData("SELECT prod_id, prod_name, prod_status, category, price, quantity, expire FROM product_table WHERE prod_name LIKE '%" + searchText + "%'");
    }

    // Set up the table model with an additional column for prod_id (hidden later)
    DefaultTableModel model = new DefaultTableModel(new String[]{
        "Product ID", "Product Name", "Product Status", "Category", "Price"
    }, 0);

    // Get the current date
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String currentDate = dateFormat.format(new Date());

    // Populate the model with data from the result set
    while (rs.next()) {
        String prodId = rs.getString("prod_id"); // Fetch prod_id
        String productName = rs.getString("prod_name");
        String productStatus = rs.getString("prod_status");
        String category = rs.getString("category");
        double price = rs.getDouble("price");
        int quantity = rs.getInt("quantity"); // Fetch quantity from the database
        String expireDate = rs.getString("expire");

        // If the quantity is 0, set the product status to "Out of stock"
        if (quantity == 0) {
            productStatus = "Out of stock";
        }

        // Check if the expire date is before the current date, if it's '0001-12-31' replace it with a default non-expiry value
        if ("0001-12-31".equals(expireDate)) {
            expireDate = "No Expiry Date";
        } else {
            try {
                Date expire = dateFormat.parse(expireDate);
                Date current = dateFormat.parse(currentDate);
                if (expire.before(current)) {
                    continue; // Skip expired products
                }
            } catch (Exception e) {
                System.out.println("Error parsing date: " + e.getMessage());
            }
        }

        // Add the data to the model (including prod_id in the data but hidden later)
        model.addRow(new Object[]{
            prodId,  // Product ID (this will be hidden in the table view)
            productName,
            productStatus,
            category,
            "₱" + String.format("%.2f", price)
        });
    }

    // Set the table model
    stock.setModel(model);

    // Hide the Product ID column (index 0)
    stock.getColumnModel().getColumn(0).setMinWidth(0);
    stock.getColumnModel().getColumn(0).setMaxWidth(0);
    stock.getColumnModel().getColumn(0).setWidth(0);

    // Custom cell renderer for product status (coloring based on status)
    stock.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (value != null) {
                String status = value.toString();
                if (status.equals("Available")) {
                    comp.setForeground(Color.GREEN); // Green for Available
                } else if (status.equals("Out of stock")) {
                    comp.setForeground(Color.RED); // Red for Out of stock
                } else {
                    comp.setForeground(Color.BLACK); // Default color
                }
            }
            return comp;
        }
    });

    // Close the result set
    rs.close();
} catch (SQLException ex) {
    System.out.println("Errors: " + ex.getMessage());
}


        
    }//GEN-LAST:event_searchBarKeyReleased

    private void buyButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buyButtonMouseClicked

dbConnector dbc = new dbConnector();
payment pay = new payment();

int rowIndex = stock.getSelectedRow();
if (rowIndex < 0) {
    JOptionPane.showMessageDialog(null, "Please select a product first.");
} else {
    try {
        // Retrieve the hidden Product ID from the selected row
        String productId = stock.getValueAt(rowIndex, 0).toString();

        // Fetch quantity and price from the database using prod_id
        ResultSet rs = dbc.getData(
            "SELECT quantity, price FROM product_table WHERE prod_id = '" + productId + "'"
        );

        if (rs.next()) {
            int availableStock = rs.getInt("quantity");
            double productPrice = rs.getDouble("price");

            // Check if stock is available
            if (availableStock > 0) {
                // Pass data to the payment form
                pay.setVisible(true);
                pay.pid.setText(productId); // Assuming pay has a JLabel for product ID
                pay.availstock.setText(String.valueOf(availableStock)); // JTextField for available stock
                pay.price.setText(String.format("%.2f", productPrice)); // JTextField for price
            } else {
                // Notify that the product is out of stock
                JOptionPane.showMessageDialog(null, "The selected product is out of stock.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Product not found!");
        }
        rs.close();
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
    }
}
        
    }//GEN-LAST:event_buyButtonMouseClicked

    private void buyButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buyButtonMousePressed
        buyButton.setBackground(new Color(70,194,99));
    }//GEN-LAST:event_buyButtonMousePressed

    private void buyButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buyButtonMouseReleased
        buyButton.setBackground(new Color(83,215,105));
    }//GEN-LAST:event_buyButtonMouseReleased

    private void buyButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buyButtonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_buyButtonMouseEntered

    private void panel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel1MouseClicked
        sellproduct r = new sellproduct();
        r.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panel1MouseClicked

    private void panel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel1MouseEntered
        panel1.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_panel1MouseEntered

    private void panel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel1MouseExited
        panel1.setBackground(new Color(89,196,19));
    }//GEN-LAST:event_panel1MouseExited

    private void panelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseClicked
        userDashboard ud = new userDashboard();
        ud.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelMouseClicked

    private void panelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseEntered
        panel.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_panelMouseEntered

    private void panelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseExited
        panel.setBackground(new Color(89,196,19));
    }//GEN-LAST:event_panelMouseExited

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        Loginfrom lf = new Loginfrom();
        lf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

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
            java.util.logging.Logger.getLogger(sellproduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(sellproduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(sellproduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(sellproduct.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new sellproduct().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private panelRoundComponents.PanelRound buyButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JLabel label1;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panel1;
    private javax.swing.JTextField searchBar;
    private javax.swing.JTable stock;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
