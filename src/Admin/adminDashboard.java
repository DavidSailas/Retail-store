
package Admin;

import config.dbConnector;
import form.Loginfrom;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class adminDashboard extends javax.swing.JFrame {

    
    public adminDashboard() {
        initComponents();
        countOfAllProducts();
        availableStocks();
        outOfStocks();
    }

    public void countOfAllProducts() {
    try {
        dbConnector dbc = new dbConnector();
        ResultSet rs = dbc.getData("SELECT COUNT(*) AS NROWS FROM product_table");
        int rowCount = 0;
        if (rs.next()) {
            rowCount = rs.getInt("NROWS");
        }
        productCount.setText(rowCount + " ");
        rs.close();
    } catch (SQLException ex) {
        System.out.println("Errors: " + ex.getMessage());
    }
}

public void availableStocks() {
    try {
        dbConnector dbc = new dbConnector();
        ResultSet rs = dbc.getData("SELECT SUM(quantity) AS totalQuantity FROM product_table WHERE quantity > 0");
        int totalQuantity = 0;
        if (rs.next()) {
            totalQuantity = rs.getInt("totalQuantity");
        }
        availableStock.setText(totalQuantity + " ");
        rs.close();
    } catch (SQLException ex) {
        System.out.println("Errors: " + ex.getMessage());
    }
}

public void outOfStocks() {
    try {
        dbConnector dbc = new dbConnector();
        ResultSet rs = dbc.getData("SELECT COUNT(*) AS NROWS FROM product_table WHERE quantity <= 0");
        int rowCount = 0;
        if (rs.next()) {
            rowCount = rs.getInt("NROWS");
        }
        outOfStocks.setText(rowCount + " ");
        rs.close();
    } catch (SQLException ex) {
        System.out.println("Errors: " + ex.getMessage());
    }
}

// Method to display all products
public void countProduct() {
    try {
        dbConnector dbc = new dbConnector();
        ResultSet rs = dbc.getData("SELECT prod_name, quantity, price FROM product_table");
        
        // Set up the table model with custom columns
        DefaultTableModel model = new DefaultTableModel(new String[]{
            "Product Name", "Quantity", "Price"
        }, 0);
        
        // Populate the table with data from the result set
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("prod_name"),
                rs.getInt("quantity"),
                rs.getDouble("price")
            });
        }
        
        // Set the model for the table
        products.setModel(model);
        rs.close();
    } catch (SQLException ex) {
        System.out.println("Errors: " + ex.getMessage());
    }
}

// Method to display available stock products
public void loadAvailableStock() {
    try {
        dbConnector dbc = new dbConnector();
        ResultSet rs = dbc.getData("SELECT prod_name, quantity, price FROM product_table WHERE quantity > 0");
        
        // Set up the table model with custom columns
        DefaultTableModel model = new DefaultTableModel(new String[]{
            "Product Name", "Quantity", "Price"
        }, 0);
        
        // Populate the table with data from the result set
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("prod_name"),
                rs.getInt("quantity"),
                rs.getDouble("price")
            });
        }
        
        // Set the model for the table
        products.setModel(model);
        rs.close();
    } catch (SQLException ex) {
        System.out.println("Errors: " + ex.getMessage());
    }
}

// Method to display out of stock products
public void loadOutOfStock() {
    try {
        dbConnector dbc = new dbConnector();
        ResultSet rs = dbc.getData("SELECT prod_name, quantity, price FROM product_table WHERE quantity <= 0");
        
        // Set up the table model with custom columns
        DefaultTableModel model = new DefaultTableModel(new String[]{
            "Product Name", "Quantity", "Price"
        }, 0);
        
        // Populate the table with data from the result set
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("prod_name"),
                rs.getInt("quantity"),
                rs.getDouble("price")
            });
        }
        
        // Set the model for the table
        products.setModel(model);
        rs.close();
    } catch (SQLException ex) {
        System.out.println("Errors: " + ex.getMessage());
    }
}

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popUp = new javax.swing.JPopupMenu();
        view = new javax.swing.JMenuItem();
        viewpanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        prodname = new javax.swing.JLabel();
        cat = new javax.swing.JLabel();
        quansold = new javax.swing.JLabel();
        price = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        prodid = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        expire = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        outOfStocks = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        productCount = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        availableStock = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        products = new javax.swing.JTable();
        label = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        panel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        panel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();

        view.setText("View");
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });
        popUp.add(view);

        viewpanel.setBackground(new java.awt.Color(255, 255, 255));
        viewpanel.setMinimumSize(new java.awt.Dimension(400, 400));
        viewpanel.setPreferredSize(new java.awt.Dimension(400, 400));

        jPanel4.setBackground(new java.awt.Color(89, 196, 19));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("  VIEW DETAILS");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel14.setText("Category");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel15.setText("Product Name");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel16.setText("Quantity Sold");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel17.setText("Price");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel18.setText("Total");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel19.setText("Date");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel20.setText("Time");

        prodname.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        prodname.setText("Product Name");

        cat.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        cat.setText("Category");

        quansold.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        quansold.setText("Quantity Sold");

        price.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        price.setText("Price");

        total.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        total.setText("Total");

        date.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        date.setText("Date");

        time.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        time.setText("Time");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel25.setText("Product Id");

        prodid.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        prodid.setText("Product Id");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel21.setText("Expire Date");

        expire.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        expire.setText("Expire Date");

        javax.swing.GroupLayout viewpanelLayout = new javax.swing.GroupLayout(viewpanel);
        viewpanel.setLayout(viewpanelLayout);
        viewpanelLayout.setHorizontalGroup(
            viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(viewpanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewpanelLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(cat, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewpanelLayout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(quansold, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewpanelLayout.createSequentialGroup()
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(expire, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewpanelLayout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewpanelLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewpanelLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewpanelLayout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewpanelLayout.createSequentialGroup()
                        .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel25))
                        .addGap(63, 63, 63)
                        .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(prodid)
                            .addComponent(prodname)))))
        );
        viewpanelLayout.setVerticalGroup(
            viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewpanelLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(prodid))
                .addGap(11, 11, 11)
                .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(prodname))
                .addGap(11, 11, 11)
                .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(cat))
                .addGap(11, 11, 11)
                .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(quansold))
                .addGap(11, 11, 11)
                .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(expire))
                .addGap(11, 11, 11)
                .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(price))
                .addGap(11, 11, 11)
                .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(total))
                .addGap(11, 11, 11)
                .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(date))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(time)
                    .addComponent(jLabel20))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("DASHBOARD");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(89, 196, 19), 2));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("View");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

        outOfStocks.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        outOfStocks.setForeground(new java.awt.Color(0, 128, 0));
        outOfStocks.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        outOfStocks.setText("0");
        jPanel3.add(outOfStocks, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 32, 210, 30));

        jLabel12.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 128, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Product out of stock");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 210, -1));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 70, 210, 100));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(89, 196, 19), 2));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("View");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

        productCount.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        productCount.setForeground(new java.awt.Color(0, 128, 0));
        productCount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productCount.setText("0");
        jPanel6.add(productCount, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 32, 210, 30));

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 128, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Count of all products");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 210, -1));

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, 210, 100));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(89, 196, 19), 2));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("View");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel8.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

        availableStock.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        availableStock.setForeground(new java.awt.Color(0, 128, 0));
        availableStock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        availableStock.setText("0");
        jPanel8.add(availableStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 32, 210, 30));

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 128, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Available stocks");
        jPanel8.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 210, -1));

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, 210, 100));

        products.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                productsMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(products);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 670, 240));

        label.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        label.setForeground(new java.awt.Color(102, 102, 102));
        label.setText("Reports");
        jPanel2.add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 340, -1));

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
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Top Sales");
        panel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 10, 200, -1));

        jPanel1.add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 200, 40));

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

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("Log out");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 470, -1, -1));

        panel2.setBackground(new java.awt.Color(89, 196, 19));
        panel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel2MouseExited(evt);
            }
        });
        panel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Sales Reports");
        panel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 10, 200, -1));

        jPanel1.add(panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 200, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 500));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        countProduct();
        label.setText("Count of all product's");
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        loadAvailableStock();
        label.setText("Available stock's");
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        loadOutOfStock();
        label.setText("Out of stock's");
    }//GEN-LAST:event_jLabel6MouseClicked

    private void panel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel1MouseClicked
        Analytics a = new Analytics();
        a.setVisible(true);
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

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        Loginfrom lf = new Loginfrom();
        lf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void panel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel2MouseClicked
        salereport r = new salereport();
        r.setVisible(true);
        this.dispose();    }//GEN-LAST:event_panel2MouseClicked

    private void panel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_panel2MouseEntered

    private void panel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_panel2MouseExited

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed

// Check if any row is selected
int selectedRow = products.getSelectedRow();
if (selectedRow == -1) {
    JOptionPane.showMessageDialog(null, "Please select a product record to view.");
    return;
}

// Assuming column 0 holds `prodid`
String prodId = products.getValueAt(products.getSelectedRow(), 0).toString();

try {
    dbConnector dbc = new dbConnector();

    // Debugging: Print the retrieved prodId to verify correctness
    System.out.println("Selected Product ID: " + prodId);

    // Fetch details for the selected product
    ResultSet rs = dbc.getData(
        "SELECT " +
        "product_table.prod_id AS prodid, " +
        "product_table.prod_name AS prodname, " +
        "product_table.category AS cat, " +
        "sales.quantity_sold AS quansold, " +
        "product_table.expire, " +
        "product_table.price AS price, " +
        "(sales.quantity_sold * product_table.price) AS total, " +
        "sales.date AS date, " +
        "sales.time AS time " +
        "FROM product_table " +
        "LEFT JOIN sales ON product_table.prod_id = sales.prod_id " +
        "WHERE product_table.prod_id = '" + prodId + "'"
    );

    // Check if the query returned data
    if (rs.next()) {
        // Populate the viewpanel with fetched data
        prodid.setText(rs.getString("prodid"));
        prodname.setText(rs.getString("prodname"));
        cat.setText(rs.getString("cat"));
        quansold.setText(rs.getString("quansold"));
        expire.setText(rs.getString("expire"));
        price.setText(rs.getString("price"));
        total.setText(rs.getString("total"));
        date.setText(rs.getString("date"));
        time.setText(rs.getString("time"));

        // Show the viewpanel in a dialog
        Object[] options = {};
        JOptionPane.showOptionDialog(null, viewpanel, "Product Details",
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, options, null);
    } else {
        JOptionPane.showMessageDialog(null, "No data found for the selected product.");
    }

    rs.close();
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(null, "Error fetching product details: " + ex.getMessage());
}
        
    }//GEN-LAST:event_viewActionPerformed

    private void productsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_productsMousePressed
                        if (SwingUtilities.isRightMouseButton(evt)) {
            popUp.show(products, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_productsMousePressed

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
            java.util.logging.Logger.getLogger(adminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel availableStock;
    private javax.swing.JLabel cat;
    private javax.swing.JLabel date;
    private javax.swing.JLabel expire;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JLabel outOfStocks;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPopupMenu popUp;
    private javax.swing.JLabel price;
    private javax.swing.JLabel prodid;
    private javax.swing.JLabel prodname;
    private javax.swing.JLabel productCount;
    private javax.swing.JTable products;
    private javax.swing.JLabel quansold;
    private javax.swing.JLabel time;
    private javax.swing.JLabel total;
    private javax.swing.JMenuItem view;
    private javax.swing.JPanel viewpanel;
    // End of variables declaration//GEN-END:variables
}
