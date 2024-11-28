
package Admin;

import config.dbConnector;
import form.Loginfrom;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;


public class salereport extends javax.swing.JFrame {


    public salereport() {
        initComponents();
        displayData();
        productSold();
        totalSales();
        dailySales();
    }


public static String getCurrentDate() {
    LocalDate today = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    return today.format(formatter);
}

public void displayData() {
    try {
        dbConnector dbc = new dbConnector();
        ResultSet rs = dbc.getData(
            "SELECT sales.sale_id AS Sale_Id, product_table.prod_name AS Product_Name, " +
            "product_table.category AS Category, product_table.price AS Price, " +
            "sales.quantity_sold AS Quantity_Sold, sales.date AS Date, sales.time AS Time, " +
            "(product_table.price * sales.quantity_sold) AS Total " +
            "FROM sales " +
            "JOIN product_table ON sales.prod_id = product_table.prod_id " +
            "ORDER BY sales.quantity_sold DESC"
        );

        // Custom table model
        DefaultTableModel model = new DefaultTableModel(new String[]{
            "Product Name", "Category", "Price", "Quantity Sold", "Date", "Time", "Total"
        }, 0);

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("Product_Name"),
                rs.getString("Category"),
                rs.getDouble("Price"),
                rs.getInt("Quantity_Sold"),
                rs.getString("Date"),
                rs.getString("Time"),
                rs.getDouble("Total")
            });
        }
        sales_list.setModel(model);
        rs.close();
    } catch (SQLException ex) {
        System.out.println("Errors: " + ex.getMessage());
    }
}

public void productSold() {
    try {
        dbConnector dbc = new dbConnector();
        ResultSet rs = dbc.getData("SELECT COUNT(*) AS NROWS FROM sales");
        int rowCount = 0;
        if (rs.next()) {
            rowCount = rs.getInt("NROWS");
        }
        product.setText("" + rowCount + " ");
    } catch (SQLException ex) {
        System.out.println("Errors: " + ex.getMessage());
    }
}

public void totalSales() {
    try {
        dbConnector dbc = new dbConnector();
        ResultSet rs = dbc.getData(
            "SELECT SUM(product_table.price * sales.quantity_sold) AS TotalSales " +
            "FROM sales " +
            "JOIN product_table ON sales.prod_id = product_table.prod_id"
        );
        double totalSales = 0;
        if (rs.next()) {
            totalSales = rs.getDouble("TotalSales");
        }
        totalSale.setText("₱" + String.format("%.2f", totalSales));
    } catch (SQLException ex) {
        System.out.println("Errors: " + ex.getMessage());
    }
}

public void dailySales() {
    try {
        dbConnector dbc = new dbConnector();
        String currentDate = getCurrentDate();
        ResultSet rs = dbc.getData(
            "SELECT SUM(product_table.price * sales.quantity_sold) AS TotalSales " +
            "FROM sales " +
            "JOIN product_table ON sales.prod_id = product_table.prod_id " +
            "WHERE sales.date = '" + currentDate + "'"
        );

        double totalSales = 0;
        if (rs.next()) {
            totalSales = rs.getDouble("TotalSales");
        }
        dailySale.setText("₱" + String.format("%.2f", totalSales));
    } catch (SQLException ex) {
        System.out.println("Errors: " + ex.getMessage());
    }
}

public void countOfAllProducts() {
    try {
        dbConnector dbc = new dbConnector();
        ResultSet rs = dbc.getData("SELECT COUNT(*) AS NROWS FROM product_table");
        int rowCount = 0;
        if (rs.next()) {
            rowCount = rs.getInt("NROWS");
        }
        product.setText("" + rowCount + " ");
    } catch (SQLException ex) {
        System.out.println("Errors: " + ex.getMessage());
    }
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        panel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        totalSale = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        product = new javax.swing.JLabel();
        dailySale = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        sales_list = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Sales Reports");
        panel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 10, 200, -1));

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
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Dashboard");
        panel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 10, 200, -1));

        jPanel1.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 200, 40));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Log out");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 470, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 500));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("DASHBOARD");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        totalSale.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        totalSale.setForeground(new java.awt.Color(0, 128, 0));
        totalSale.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalSale.setText("0");
        jPanel2.add(totalSale, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 200, 30));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 128, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Total Sales");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 130, 30));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 128, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Product");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 130, 30));

        product.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        product.setForeground(new java.awt.Color(0, 128, 0));
        product.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        product.setText("0");
        jPanel2.add(product, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 120, 200, 30));

        dailySale.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        dailySale.setForeground(new java.awt.Color(0, 128, 0));
        dailySale.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        dailySale.setText("0");
        jPanel2.add(dailySale, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 120, 200, 30));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 128, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Daily Sales");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, 130, 30));

        label.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        label.setForeground(new java.awt.Color(102, 102, 102));
        label.setText("Sale's List");
        jPanel2.add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 120, -1));

        sales_list.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        sales_list.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(sales_list);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 740, 260));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 800, 500));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void panel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel1MouseClicked
        salereport r = new salereport();
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
        adminDashboard ad = new adminDashboard();
        ad.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panelMouseClicked

    private void panelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseEntered
        panel.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_panelMouseEntered

    private void panelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMouseExited
        panel.setBackground(new Color(89,196,19));
    }//GEN-LAST:event_panelMouseExited

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        Loginfrom lf = new Loginfrom();
        lf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

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
            java.util.logging.Logger.getLogger(salereport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(salereport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(salereport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(salereport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new salereport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dailySale;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panel1;
    private javax.swing.JLabel product;
    private javax.swing.JTable sales_list;
    private javax.swing.JLabel totalSale;
    // End of variables declaration//GEN-END:variables
}
