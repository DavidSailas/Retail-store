
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
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;



public class userDashboard extends javax.swing.JFrame {


    public userDashboard() {
        initComponents();
        displayData();

    }

private boolean notificationShownOutOfStock = false;
private boolean notificationShown = false;
private boolean isLoggedIn = false;

public void resetFlags() {
    notificationShown = false; // Reset notification flag for new login session
    isLoggedIn = true; // Ensure the user is logged in
}

public void displayData() {
    try {
        dbConnector dbc = new dbConnector();

        // Fetch data from the database excluding 'Archived' status
        ResultSet rs = dbc.getData(
            "SELECT prod_id, prod_name, prod_status, category, expire, price, quantity " +
            "FROM product_table WHERE prod_status != 'Archived'"
        );

        // Define table model columns
        DefaultTableModel model = new DefaultTableModel(new String[]{
            "Product ID", "Product Name", "Product Status", "Category", "Expire Date", "Price"
        }, 0);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = dateFormat.format(new Date());
        StringBuilder lowStockMessage = new StringBuilder();
        StringBuilder outOfStockMessage = new StringBuilder();

        // Process each row
        while (rs.next()) {
            String productId = rs.getString("prod_id");
            String productName = rs.getString("prod_name");
            String productStatus = rs.getString("prod_status");
            String category = rs.getString("category");
            String expireDate = rs.getString("expire");
            double price = rs.getDouble("price");
            int quantity = rs.getInt("quantity");

            // Update product status if quantity is 0
            if (quantity == 0 && !"Out of stock".equals(productStatus)) {
                productStatus = "Out of stock";
                dbc.updateData("UPDATE product_table SET prod_status = 'Out of stock' WHERE prod_id = '" + productId + "'");
            }

            // Replace default expiration date
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

            // Add row to the table model
            model.addRow(new Object[]{
                productId,
                productName,
                productStatus,
                category,
                expireDate,
                "₱" + String.format("%.2f", price)
            });

            // Collect notifications for low or out-of-stock products
            if (quantity <= 5 && quantity > 0) {
                lowStockMessage.append("<b style='color:yellow;'>Warning:</b> Product <font color='blue'>")
                        .append(productName)
                        .append("</font> has low stock. Only ")
                        .append(quantity)
                        .append(" left.<br>");
            }
            if (quantity == 0) {
                outOfStockMessage.append("<b style='color:red;'>Out of stock:</b> Product <font color='blue'>")
                        .append(productName)
                        .append("</font> is out of stock.<br>");
            }
        }

        stock.setModel(model);

        // Hide the Product ID column
        hideColumn(stock, 0);
        
        // Selection listener to display details
        stock.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = stock.getSelectedRow();
                if (selectedRow >= 0) {
                    String productName = (String) stock.getValueAt(selectedRow, 1);
                    String productStatus = (String) stock.getValueAt(selectedRow, 2);
                    String category = (String) stock.getValueAt(selectedRow, 3);
                    String expireDate = (String) stock.getValueAt(selectedRow, 4);
                    String price = (String) stock.getValueAt(selectedRow, 5).toString().replace("₱", "");

                    try {
                        ResultSet rsDetails = dbc.getData(
                            "SELECT prod_id, quantity FROM product_table WHERE prod_name = '" + productName + "'"
                        );
                        if (rsDetails.next()) {
                            this.pid.setText(rsDetails.getString("prod_id"));
                            this.productName.setText(productName);
                            this.category.setSelectedItem(category);
                            this.expire.setText("No Expiry Date".equals(expireDate) ? "0001-12-31" : expireDate);
                            this.price.setText(price);
                            this.quantity.setText(rsDetails.getString("quantity"));
                        }
                        rsDetails.close();
                    } catch (SQLException ex) {
                        System.out.println("Error fetching product details: " + ex.getMessage());
                    }
                }
            }
        });
        
        // Custom renderer for Product Status
        stock.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (value != null) {
                    String status = value.toString();
                    comp.setForeground("Available".equals(status) ? Color.GREEN :
                                       "Out of stock".equals(status) ? Color.RED : Color.BLACK);
                }
                return comp;
            }
        });

        // Display notifications for low stock or out-of-stock products
        if (lowStockMessage.length() > 0 && !notificationShown && isLoggedIn) {
            JOptionPane.showMessageDialog(
                mainPanel,
                "<html>" + lowStockMessage + "</html>",
                "Low Stock Notification",
                JOptionPane.WARNING_MESSAGE
            );
            notificationShown = true;
        }

        if (outOfStockMessage.length() > 0 && !notificationShownOutOfStock && isLoggedIn) {
            JOptionPane.showMessageDialog(
                mainPanel,
                "<html>" + outOfStockMessage + "</html>",
                "Out of Stock Notification",
                JOptionPane.ERROR_MESSAGE
            );
            notificationShownOutOfStock = true;
        }

        rs.close();
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
}




// Method to hide a specific column in the JTable
private void hideColumn(JTable table, int columnIndex) {
    TableColumn column = table.getColumnModel().getColumn(columnIndex);
    column.setMinWidth(0);
    column.setMaxWidth(0);
    column.setPreferredWidth(0);
    column.setResizable(false);
}


public void Loginform() {
    // Set isLoggedIn to true when login is successful
    isLoggedIn = true;
    notificationShown = false; // Reset the notification shown flag for the next time
    displayData();
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
        title = new javax.swing.JLabel();
        mainPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        stock = new javax.swing.JTable();
        searchBar = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        pid = new javax.swing.JTextField();
        category = new javax.swing.JComboBox<>();
        productName = new javax.swing.JTextField();
        price = new javax.swing.JTextField();
        quantity = new javax.swing.JTextField();
        expire = new javax.swing.JTextField();
        deleteButton = new panelRoundComponents.PanelRound();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        addproduct = new panelRoundComponents.PanelRound();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        updateButton = new panelRoundComponents.PanelRound();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        label1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
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
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/selling.png"))); // NOI18N
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
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/boxes (1).png"))); // NOI18N
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

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        title.setForeground(new java.awt.Color(0, 102, 0));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/IMPos (2).png"))); // NOI18N
        title.setText("IMPos");
        jPanel2.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 40));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 200, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 500));

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));
        mainPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(89, 196, 19), 2));
        mainPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("INVENTORY");
        mainPanel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

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

        mainPanel.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(395, 99, 390, 380));

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
        mainPanel.add(searchBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 210, -1));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Product Details", javax.swing.border.TitledBorder.LEADING, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Product Code:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 100, -1));

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Product Name:");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 102, 102));
        jLabel13.setText("Expire Date:");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Category:");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        jLabel16.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Price:");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Quantity:");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        pid.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        pid.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        pid.setEnabled(false);
        pid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pidActionPerformed(evt);
            }
        });
        jPanel3.add(pid, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 190, -1));

        category.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Snacks", "Drinks & Beverages", "Canned Goods", "Crackers & Bread", "Poultry Products", "Condiments & Seasonings", "Dairy Products", "Grains", "Cooking Essentials", "Personal Care Products", "Hygiene Products", "Household Items", "Stationery & School Supplies", "Footwear & Accessories", "Cigarettes & Tobacco", "Instant Meals", "Baby Products", "Confectionery", "Miscellaneous", "Medicinal Products" }));
        category.setSelectedIndex(-1);
        category.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryActionPerformed(evt);
            }
        });
        jPanel3.add(category, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 190, -1));

        productName.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        productName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        productName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productNameActionPerformed(evt);
            }
        });
        jPanel3.add(productName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 190, -1));

        price.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        price.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceActionPerformed(evt);
            }
        });
        jPanel3.add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 210, 190, -1));

        quantity.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        quantity.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel3.add(quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 190, -1));

        expire.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        expire.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        expire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expireActionPerformed(evt);
            }
        });
        jPanel3.add(expire, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 250, 190, -1));

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
        deleteButton.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 80, 40));

        jPanel3.add(deleteButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 310, 100, 40));

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
        addproduct.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 1, 80, 40));

        jPanel3.add(addproduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 310, 100, 40));

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
        updateButton.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 80, 40));

        jPanel3.add(updateButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 100, 40));

        mainPanel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 360, 390));

        label1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        label1.setForeground(new java.awt.Color(102, 102, 102));
        label1.setText("Search:");
        mainPanel.add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 60, 20));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(89, 196, 19));
        jLabel6.setText("_____________________________________________________________________");
        mainPanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 760, -1));

        getContentPane().add(mainPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 800, 500));

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

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        Loginfrom lf = new Loginfrom();
        lf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void updateButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMouseReleased
        updateButton.setBackground(new Color(83,215,105));
    }//GEN-LAST:event_updateButtonMouseReleased

    private void updateButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updateButtonMousePressed
        updateButton.setBackground(new Color(70,194,99));
    }//GEN-LAST:event_updateButtonMousePressed

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
        if ("No Expiry Date".equals(expireDate)) {
            expireDate = "0001-12-31";  // If "No Expire" is selected, use "0001-12-31"
        }

        // Determine the product status based on the quantity
        int qty = Integer.parseInt(quantity.getText());
        String status = (qty > 0) ? "Available" : "Out of stock";

        // Update the product in the database, including the status
        String sql = "UPDATE product_table SET prod_name = ?, category = ?, price = ?, quantity = ?, expire = ?, prod_status = ? WHERE prod_id = ?";
        PreparedStatement pstmt = dbc.connect.prepareStatement(sql);
        pstmt.setString(1, productName.getText());
        pstmt.setString(2, cat);
        pstmt.setString(3, price.getText());
        pstmt.setInt(4, qty);  // Ensure quantity is updated
        pstmt.setString(5, expireDate);  // Set the expiration date
        pstmt.setString(6, status);  // Set the product status
        pstmt.setString(7, pid.getText());  // Use the pid from the JTextField

        int rowsUpdated = pstmt.executeUpdate();
        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(null, "Product updated successfully!");
            displayData();  // Refresh the table data
            // Clear the input fields
            pid.setText("");
            productName.setText("");
            category.setSelectedIndex(-1);
            price.setText("");
            quantity.setText("");
            expire.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "No product found with the given ID.");
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Please enter a valid number for quantity and price.");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "An error occurred: " + e.getMessage());
    }
}


    }//GEN-LAST:event_updateButtonMouseClicked

    private void addproductMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addproductMouseReleased
        addproduct.setBackground(new Color(91,164,252));
    }//GEN-LAST:event_addproductMouseReleased

    private void addproductMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addproductMousePressed
        addproduct.setBackground(new Color(88,151,238));
    }//GEN-LAST:event_addproductMousePressed

    private void addproductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addproductMouseClicked

        addStock stock = new addStock();
        stock.setVisible(true);
        
    }//GEN-LAST:event_addproductMouseClicked

    private void deleteButtonMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseReleased
        deleteButton.setBackground(new Color(252,61,57));
    }//GEN-LAST:event_deleteButtonMouseReleased

    private void deleteButtonMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMousePressed
        deleteButton.setBackground(new Color(227,52,55));
    }//GEN-LAST:event_deleteButtonMousePressed

    private void deleteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteButtonMouseClicked

int rowIndex = stock.getSelectedRow();

if (rowIndex < 0) {
    JOptionPane.showMessageDialog(null, "Please select a row to delete");
} else {
    // Get the table model
    DefaultTableModel model = (DefaultTableModel) stock.getModel();

    // Get the value from the hidden ID column (index 0, assuming 'prod_id' is hidden there)
    Object value = model.getValueAt(rowIndex, 0); // Column 0 is hidden 'prod_id'
    String id = value.toString();

    // Check if the ID is a valid integer
    try {
        int ids = Integer.parseInt(id); // Convert the retrieved value to an integer (prod_id)
        int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this product?", "Confirm Archive", JOptionPane.YES_NO_OPTION);
        if (a == JOptionPane.YES_OPTION) {
            dbConnector dbc = new dbConnector();

            // Query to update the product status to 'Archived' in the database
            String query = "UPDATE product_table SET prod_status = 'Archived' WHERE prod_id = ?";
            try (PreparedStatement stmt = dbc.connect.prepareStatement(query)) {
                stmt.setInt(1, ids); // Set the product ID
                int rowsAffected = stmt.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Product Delete successfully!");
                    model.removeRow(rowIndex);
                    pid.setText("");
                    productName.setText("");
                    category.setSelectedIndex(-1);
                    price.setText("");
                    quantity.setText("");
                    expire.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, "Product not found.");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error updating product status: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Invalid ID format: " + id, "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    }//GEN-LAST:event_deleteButtonMouseClicked

    private void expireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expireActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_expireActionPerformed

    private void priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceActionPerformed

    private void productNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productNameActionPerformed

    private void categoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoryActionPerformed

    private void pidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pidActionPerformed

    private void searchBarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBarKeyReleased

try {
    dbConnector dbc = new dbConnector();
    String searchText = searchBar.getText().trim();
    ResultSet rs;

    // Check if search text is empty
    if (searchText.isEmpty()) {
        // If empty, display all products with the required columns including prod_id and quantity
        rs = dbc.getData("SELECT prod_id, prod_name, prod_status, category, expire, price, quantity FROM product_table");
    } else {
        // If not empty, search by product name using a prepared statement
        String query = "SELECT prod_id, prod_name, prod_status, category, expire, price, quantity FROM product_table WHERE prod_name LIKE ?";
        PreparedStatement stmt = dbc.connect.prepareStatement(query);
        stmt.setString(1, "%" + searchText + "%"); // Set the search term in the prepared statement
        rs = stmt.executeQuery();
    }

    // Create the table model with the required columns
    DefaultTableModel model = new DefaultTableModel(new String[]{
        "Product ID", "Product Name", "Product Status", "Category", "Expire Date", "Price"
    }, 0);

    // Get the current date
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    String currentDate = dateFormat.format(new Date());

    // Populate the table with data
    while (rs.next()) {
        String productId = rs.getString("prod_id");
        String productName = rs.getString("prod_name");
        String productStatus = rs.getString("prod_status");
        String category = rs.getString("category");
        String expireDate = rs.getString("expire");
        double price = rs.getDouble("price");
        int quantity = rs.getInt("quantity"); // Fetch quantity

        // If quantity is 0, set the product status to "Out of stock"
        if (quantity == 0) {
            productStatus = "Out of stock";
        }

        // Check if the expire date is '0001-12-31' and replace it with 'No Expiry'
        if ("0001-12-31".equals(expireDate)) {
            expireDate = "No Expiry Date";
        }

        // Check if the expire date is before the current date and skip expired products
        try {
            Date expire = dateFormat.parse(expireDate);
            Date current = dateFormat.parse(currentDate);
            if (expire.before(current)) {
                continue; // Skip expired products
            }
        } catch (Exception e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }

        // Add data to the model
        model.addRow(new Object[]{
            productId,
            productName,
            productStatus,
            category,
            expireDate,
            "₱" + String.format("%.2f", price)
        });
    }

    // Set the model for the stock table
    stock.setModel(model);

    // Hide the "Product ID" column
    stock.getColumnModel().getColumn(0).setMinWidth(0);
    stock.getColumnModel().getColumn(0).setMaxWidth(0);
    stock.getColumnModel().getColumn(0).setWidth(0);
    
    // Selection listener to display details
        stock.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = stock.getSelectedRow();
                if (selectedRow >= 0) {
                    String productName = (String) stock.getValueAt(selectedRow, 1);
                    String productStatus = (String) stock.getValueAt(selectedRow, 2);
                    String category = (String) stock.getValueAt(selectedRow, 3);
                    String expireDate = (String) stock.getValueAt(selectedRow, 4);
                    String price = (String) stock.getValueAt(selectedRow, 5).toString().replace("₱", "");

                    try {
                        ResultSet rsDetails = dbc.getData(
                            "SELECT prod_id, quantity FROM product_table WHERE prod_name = '" + productName + "'"
                        );
                        if (rsDetails.next()) {
                            this.pid.setText(rsDetails.getString("prod_id"));
                            this.productName.setText(productName);
                            this.category.setSelectedItem(category);
                            this.expire.setText("No Expiry Date".equals(expireDate) ? "0001-12-31" : expireDate);
                            this.price.setText(price);
                            this.quantity.setText(rsDetails.getString("quantity"));
                        }
                        rsDetails.close();
                    } catch (SQLException ex) {
                        System.out.println("Error fetching product details: " + ex.getMessage());
                    }
                }
            }
        });
        
    // Apply custom cell renderer to change color based on the product status
    stock.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
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
                    comp.setForeground(Color.BLACK); // Default color if status is unknown
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

    private void searchBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchBarActionPerformed

    private void searchBarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBarFocusLost
        if (searchBar.getText().equals("")){
            searchBar.setText(" Search");
            searchBar.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_searchBarFocusLost

    private void searchBarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBarFocusGained
        if (searchBar.getText().equals(" Search")){
            searchBar.setText("");
            searchBar.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_searchBarFocusGained

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
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panel1;
    private javax.swing.JTextField pid;
    private javax.swing.JTextField price;
    private javax.swing.JTextField productName;
    private javax.swing.JTextField quantity;
    private javax.swing.JTextField searchBar;
    private javax.swing.JTable stock;
    private javax.swing.JLabel title;
    private panelRoundComponents.PanelRound updateButton;
    // End of variables declaration//GEN-END:variables
}
