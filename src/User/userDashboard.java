
package User;

import config.dbConnector;
import form.Loginfrom;
import java.awt.Color;
import java.awt.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;


public class userDashboard extends javax.swing.JFrame {


    public userDashboard() {
        initComponents();
        displayData();
    }

public void displayData() {
    try {
        dbConnector dbc = new dbConnector();

        // Modify the SQL query to fetch prod_name, prod_status, category, expire, price, pid, and quantity
        ResultSet rs = dbc.getData(
            "SELECT prod_id, prod_name, prod_status, category, expire, price, quantity " +
            "FROM product_table"
        );

        // Create a custom table model with the necessary columns
        DefaultTableModel model = new DefaultTableModel(new String[]{
            "Product Name", "Product Status", "Category", "Expire Date", "Price"
        }, 0);

        // Populate the table with data
        while (rs.next()) {
            String productName = rs.getString("prod_name");
            String productStatus = rs.getString("prod_status");
            String category = rs.getString("category");
            String expireDate = rs.getString("expire");
            double price = rs.getDouble("price");

            // Check if the expire date is '9999-12-31' and replace it with 'No Expire'
            if ("9999-12-31".equals(expireDate)) {
                expireDate = "No Expire";
            }

            // Add data to the model
            model.addRow(new Object[]{
                productName,
                productStatus,
                category,
                expireDate,
                "₱" + String.format("%.2f", price)
            });
        }

        // Set the model for the stock table
        stock.setModel(model);

        // Add a listener to the table row click
        stock.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = stock.getSelectedRow();
                if (selectedRow >= 0) {
                    // Get the prod_name, prod_status, category, expire, price from the selected row
                    String productName = (String) stock.getValueAt(selectedRow, 0);
                    String productStatus = (String) stock.getValueAt(selectedRow, 1);
                    String category = (String) stock.getValueAt(selectedRow, 2);
                    String expireDate = (String) stock.getValueAt(selectedRow, 3);
                    String price = (String) stock.getValueAt(selectedRow, 4).toString().replace("₱", "");

                    // Fetch the prod_id and quantity from the database using the product name (or category)
                    try {
                        ResultSet rsDetails = dbc.getData(
                            "SELECT prod_id, quantity FROM product_table WHERE prod_name = '" + productName + "'"
                        );
                        if (rsDetails.next()) {
                            String prodId = rsDetails.getString("prod_id");
                            String quantity = rsDetails.getString("quantity");

                            // Set the data in the JTextFields
                            this.pid.setText(prodId);
                            this.productName.setText(productName);
                            this.category.setSelectedItem(category);
                            this.expire.setText(expireDate.equals("No Expire") ? "" : expireDate);  // Show "No Expire" or the actual expiration date
                            this.price.setText(price);
                            this.quantity.setText(quantity);  // Set the quantity in the field
                        }
                        rsDetails.close();
                    } catch (SQLException ex) {
                        System.out.println("Error fetching product details: " + ex.getMessage());
                    }
                }
            }
        });


                // Custom table cell renderer to change color based on the product status
        stock.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (value != null) {
                    String status = value.toString();
                    if (status.equals("Available")) {
                        comp.setForeground(Color.GREEN); // Green for available products
                    } else if (status.equals("Out of stock")) {
                        comp.setForeground(Color.RED); // Red for out-of-stock products
                    } else {
                        comp.setForeground(Color.BLACK); // Default white if status is unknown
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


        
        private void loadStockData() {
    try {
        dbConnector dbc = new dbConnector();
        String sql = "SELECT * FROM product_table";
        PreparedStatement pstmt = dbc.connect.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        // Assuming you use a DefaultTableModel for the JTable named "stock"
        DefaultTableModel model = (DefaultTableModel) stock.getModel();
        model.setRowCount(0); // Clear existing rows in the table

        while (rs.next()) {
            // Add rows to the table
            model.addRow(new Object[]{
                rs.getString("prod_id"),
                rs.getString("prod_name"),
                rs.getString("expire"),
                rs.getString("category"),
                rs.getString("price"),
                rs.getString("quantity")
            });
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error loading stock data: " + e.getMessage());
    }
}

        
        public void category(){
        try{
            dbConnector dbc = new dbConnector();
            ResultSet rs = dbc.getData("SELECT prod_id, prod_name, expire, category, price, quantity FROM product_table WHERE category = '"+ cat.getSelectedItem() +"'");
            stock.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
        }catch(SQLException ex){
            System.out.println("Errors: "+ex.getMessage());
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
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        stock = new javax.swing.JTable();
        deleteButton = new panelRoundComponents.PanelRound();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        addproduct = new panelRoundComponents.PanelRound();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        updateButton = new panelRoundComponents.PanelRound();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        label = new javax.swing.JLabel();
        pid = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        productName = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        price = new javax.swing.JTextField();
        quantity = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cat = new javax.swing.JComboBox<>();
        category = new javax.swing.JComboBox<>();
        searchBar = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        expire = new javax.swing.JTextField();

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
        jLabel2.setText("Counter");
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
        jLabel3.setText("Inventory");
        panel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 10, 200, -1));

        jPanel1.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 200, 40));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setText("Log out");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 470, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 500));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("INVENTORY");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 159, 580, 330));

        deleteButton.setBackground(new java.awt.Color(252, 61, 57));
        deleteButton.setRoundBottomLeft(10);
        deleteButton.setRoundBottomRight(10);
        deleteButton.setRoundTopLeft(10);
        deleteButton.setRoundTopRight(10);
        deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteButtonMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                deleteButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                deleteButtonMouseReleased(evt);
            }
        });
        deleteButton.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        deleteButton.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 0, -1, 48));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-delete-18.png"))); // NOI18N
        jLabel4.setText("DELETE");
        deleteButton.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 100, 40));

        jPanel2.add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, 120, 40));

        addproduct.setBackground(new java.awt.Color(91, 164, 252));
        addproduct.setRoundBottomLeft(10);
        addproduct.setRoundBottomRight(10);
        addproduct.setRoundTopLeft(10);
        addproduct.setRoundTopRight(10);
        addproduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addproductMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addproductMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                addproductMouseReleased(evt);
            }
        });
        addproduct.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        addproduct.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 0, -1, 48));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-add-18.png"))); // NOI18N
        jLabel10.setText("PRODUCT");
        addproduct.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1, 100, 40));

        jPanel2.add(addproduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, 120, 40));

        updateButton.setBackground(new java.awt.Color(83, 215, 105));
        updateButton.setRoundBottomLeft(10);
        updateButton.setRoundBottomRight(10);
        updateButton.setRoundTopLeft(10);
        updateButton.setRoundTopRight(10);
        updateButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updateButtonMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                updateButtonMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                updateButtonMouseReleased(evt);
            }
        });
        updateButton.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        updateButton.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 0, -1, 48));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icons8-update-18.png"))); // NOI18N
        jLabel8.setText("UPDATE");
        updateButton.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 100, 40));

        jPanel2.add(updateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, 120, 40));

        label.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        label.setForeground(new java.awt.Color(102, 102, 102));
        label.setText("Product's");
        jPanel2.add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 130, 100, -1));

        pid.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        pid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        pid.setEnabled(false);
        pid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pidActionPerformed(evt);
            }
        });
        jPanel2.add(pid, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 90, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel12.setText("Product Code");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 90, -1));

        productName.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        productName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        productName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productNameActionPerformed(evt);
            }
        });
        jPanel2.add(productName, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 140, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel13.setText("Expire Date");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel16.setText("Price");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, -1, -1));

        price.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        price.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel2.add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 90, -1));

        quantity.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        quantity.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel2.add(quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 90, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel15.setText("Quantity");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel14.setText("Category");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, -1));

        cat.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        cat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Snacks", "Drinks", "Canned goods", "Crackers", "Poultry products", "Beverage", "Condiments", "Dairy", "Grains ", "Bread", "Oil ", "Fat" }));
        cat.setSelectedIndex(-1);
        cat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                catActionPerformed(evt);
            }
        });
        jPanel2.add(cat, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 130, 140, -1));

        category.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Snacks", "Drinks", "Canned goods", "Crackers", "Poultry products", "Beverage", "Condiments", "Dairy", "Grains ", "Bread", "Oil ", "Fat" }));
        category.setSelectedIndex(-1);
        category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryActionPerformed(evt);
            }
        });
        jPanel2.add(category, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 140, -1));

        searchBar.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        searchBar.setForeground(new java.awt.Color(102, 102, 102));
        searchBar.setText("Search");
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
        jPanel2.add(searchBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 220, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel17.setText("Product Name");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        expire.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        expire.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel2.add(expire, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 140, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 800, 500));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void deleteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseClicked
        int rowIndex = stock.getSelectedRow();

        if (rowIndex < 0){
            JOptionPane.showMessageDialog(null, "Please select row to delete");
        } else{
            TableModel model = stock.getModel();
            Object value = model.getValueAt(rowIndex, 0);
            String id = value.toString();
            int a = JOptionPane.showConfirmDialog(null, "Are you sure to delete ID: " + id);
            if (a == JOptionPane.YES_OPTION){
                dbConnector dbc = new dbConnector();
                int ids = Integer.parseInt(id);
                dbc.deleteData(ids, "product_table");
                displayData();
            }
        }
    }//GEN-LAST:event_deleteButtonMouseClicked

    private void deleteButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMousePressed
        deleteButton.setBackground(new Color(227,52,55));
    }//GEN-LAST:event_deleteButtonMousePressed

    private void deleteButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseReleased
        deleteButton.setBackground(new Color(252,61,57));
    }//GEN-LAST:event_deleteButtonMouseReleased

    private void addproductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addproductMouseClicked

        addStock stock = new addStock();
        stock.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_addproductMouseClicked

    private void addproductMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addproductMousePressed
        addproduct.setBackground(new Color(88,151,238));
    }//GEN-LAST:event_addproductMousePressed

    private void addproductMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addproductMouseReleased
        addproduct.setBackground(new Color(91,164,252));
    }//GEN-LAST:event_addproductMouseReleased

    private void updateButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMouseClicked

dbConnector dbc = new dbConnector();

String cat = (String) category.getSelectedItem();
String expireDate = expire.getText();  // Assuming `expire` is a JTextField or similar for the expiration date

if (pid.getText().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Select a product to update");
} else if (productName.getText().isEmpty() || cat.isEmpty() || price.getText().isEmpty() || quantity.getText().isEmpty() || expireDate.isEmpty()) {
    JOptionPane.showMessageDialog(null, "All fields are required!");
} else {
    try {
        // Check if the expire date is valid, if not set it to "9999-12-31"
        if ("No Expire".equals(expireDate)) {
            expireDate = "9999-12-31";  // If "No Expire" is selected, use "9999-12-31"
        }

        // Modify the SQL query to include the expiration date
        String sql = "UPDATE product_table SET prod_name = ?, category = ?, price = ?, quantity = ?, expire = ? WHERE prod_id = ?";
        PreparedStatement pstmt = dbc.connect.prepareStatement(sql);
        pstmt.setString(1, productName.getText());
        pstmt.setString(2, cat);
        pstmt.setString(3, price.getText());
        pstmt.setString(4, quantity.getText());  // Ensure quantity is updated
        pstmt.setString(5, expireDate);  // Set the expiration date
        pstmt.setString(6, pid.getText());  // Use the pid from the JTextField

        int rowsUpdated = pstmt.executeUpdate();
        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(null, "Product updated successfully!");
            loadStockData(); // Refresh the stock table
        } else {
            JOptionPane.showMessageDialog(null, "No product found with the given ID.");
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
    }
}


    }//GEN-LAST:event_updateButtonMouseClicked

    private void updateButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMousePressed
        updateButton.setBackground(new Color(70,194,99));
    }//GEN-LAST:event_updateButtonMousePressed

    private void updateButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMouseReleased
        updateButton.setBackground(new Color(83,215,105));
    }//GEN-LAST:event_updateButtonMouseReleased

    private void searchBarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBarFocusGained
        if (searchBar.getText().equals("Search")){
            searchBar.setText("");
            searchBar.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_searchBarFocusGained

    private void searchBarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBarFocusLost
        if (searchBar.getText().equals("")){
            searchBar.setText("Search");
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

            if (searchText.isEmpty()) {
                rs = dbc.getData("SELECT prod_id, prod_name, category, price, quantity FROM product_table");
            } else {
                rs = dbc.getData("SELECT prod_id, prod_name, category, price, quantity FROM product_table WHERE prod_name LIKE '%" + searchText + "%'");
            }

            stock.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
        } catch (SQLException ex) {
            System.out.println("Errors: " + ex.getMessage());
        }
    }//GEN-LAST:event_searchBarKeyReleased

    private void categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryActionPerformed

    private void stockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stockMouseClicked

        
        int rowIndex = stock.getSelectedRow();

        if (rowIndex < 0){
            JOptionPane.showMessageDialog(null, "Please select row to update");
        } else{
            
            try {
                dbConnector dbc = new dbConnector();
                TableModel tbl = stock.getModel();
                ResultSet rs = dbc.getData("SELECT * FROM product_table WHERE prod_id = '"+ tbl.getValueAt(rowIndex, 0) +"'");
                
                if (rs.next()){
                    pid.setText("" + rs.getInt("prod_id"));
                    productName.setText("" + rs.getString("prod_name"));
                    category.setSelectedItem("" + rs.getString("category"));
                    price.setText("" + rs.getString("price"));
                    quantity.setText("" + rs.getString("quantity"));
                    
                }
                
            } catch(SQLException ex){
                System.out.println(""+ ex);
            }
        }
        
    }//GEN-LAST:event_stockMouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        Loginfrom lf = new Loginfrom();
        lf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void catActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_catActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_catActionPerformed

    private void pidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pidActionPerformed

    private void productNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productNameActionPerformed

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
            java.util.logging.Logger.getLogger(userDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(userDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(userDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(userDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new userDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private panelRoundComponents.PanelRound addproduct;
    private javax.swing.JComboBox<String> cat;
    private javax.swing.JComboBox<String> category;
    private panelRoundComponents.PanelRound deleteButton;
    private javax.swing.JTextField expire;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panel1;
    private javax.swing.JTextField pid;
    private javax.swing.JTextField price;
    private javax.swing.JTextField productName;
    private javax.swing.JTextField quantity;
    private javax.swing.JTextField searchBar;
    private javax.swing.JTable stock;
    private panelRoundComponents.PanelRound updateButton;
    // End of variables declaration//GEN-END:variables
}
