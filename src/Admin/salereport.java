
package Admin;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import config.PanelPrinter;
import config.dbConnector;
import form.Loginfrom;
import java.awt.Color;
import java.awt.Window;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
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
        // Get today's date
        LocalDate today = LocalDate.now();
        String todayDate = Date.valueOf(today).toString();

        dbConnector dbc = new dbConnector();
        ResultSet rs = dbc.getData(
            "SELECT sales.sale_id AS Sale_Id, product_table.prod_name AS Product_Name, " +
            "product_table.category AS Category, product_table.price AS Price, " +
            "sales.quantity_sold AS Quantity_Sold, sales.date AS Date, sales.time AS Time, " +
            "(product_table.price * sales.quantity_sold) AS Total " +
            "FROM sales " +
            "JOIN product_table ON sales.prod_id = product_table.prod_id " +
            "WHERE sales.date = '" + todayDate + "' " + // Filter for today's sales
            "ORDER BY sales.quantity_sold DESC"
        );

        // Custom table model
        DefaultTableModel model = new DefaultTableModel(new String[]{
            "Sale Id", "Product Name", "Category", "Quantity Sold", "Date"
        }, 0);

        // Populate the table with data
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("Sale_Id"),
                rs.getString("Product_Name"),
                rs.getString("Category"),
                rs.getInt("Quantity_Sold"),
                rs.getString("Date"),
            });
        }
        sales_list.setModel(model);

        // Hide specific columns (optional)
        hideColumn(sales_list, 0); // Example: Hide "Sale Id" column (index 0)

        rs.close();
    } catch (SQLException ex) {
        System.out.println("Errors: " + ex.getMessage());
    }
}

// Method to hide a column in the JTable
private void hideColumn(JTable table, int columnIndex) {
    TableColumn column = table.getColumnModel().getColumn(columnIndex);
    column.setMinWidth(0);
    column.setMaxWidth(0);
    column.setPreferredWidth(0);
    column.setResizable(false);
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


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popUp = new javax.swing.JPopupMenu();
        view = new javax.swing.JMenuItem();
        viewpanel = new javax.swing.JPanel();
        print = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
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
        sale_id = new javax.swing.JLabel();
        exportData = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        pdf = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        panel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
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
        searchBar = new javax.swing.JTextField();
        export = new javax.swing.JButton();

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

        print.setBackground(new java.awt.Color(89, 196, 19));
        print.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        print.setForeground(new java.awt.Color(255, 255, 255));
        print.setText(" EXPORT");
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(89, 196, 19));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("  VIEW DETAILS");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
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
        jLabel25.setText("Sale Id");

        sale_id.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        sale_id.setText("Sale Id");

        javax.swing.GroupLayout viewpanelLayout = new javax.swing.GroupLayout(viewpanel);
        viewpanel.setLayout(viewpanelLayout);
        viewpanelLayout.setHorizontalGroup(
            viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(viewpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(viewpanelLayout.createSequentialGroup()
                        .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addGap(63, 63, 63)
                        .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sale_id)
                            .addComponent(prodname)
                            .addComponent(cat, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(quansold, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 125, Short.MAX_VALUE))
        );
        viewpanelLayout.setVerticalGroup(
            viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewpanelLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(sale_id))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewpanelLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20))
                    .addGroup(viewpanelLayout.createSequentialGroup()
                        .addComponent(prodname)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(quansold)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(price)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(total)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(date)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(time)))
                .addGap(27, 27, 27)
                .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        exportData.setBackground(new java.awt.Color(255, 255, 255));
        exportData.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        exportData.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 320, 10));

        jPanel7.setBackground(new java.awt.Color(0, 92, 229));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setBackground(new java.awt.Color(0, 92, 229));
        jLabel22.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText(" System Users Data");
        jPanel7.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 60));

        exportData.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 60));
        exportData.add(nameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 290, 30));

        jLabel23.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(204, 204, 204));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Download reports as:");
        exportData.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 290, 30));

        jLabel24.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(27, 57, 77));
        jLabel24.setText("File name:");
        exportData.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, -1, 20));

        pdf.setBackground(new java.awt.Color(0, 51, 184));
        pdf.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        pdf.setForeground(new java.awt.Color(255, 255, 255));
        pdf.setText(" PDF");
        pdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfActionPerformed(evt);
            }
        });
        exportData.add(pdf, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 110, 30));

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

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Log out");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 470, -1, -1));

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

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Sales Reports");
        panel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 10, 200, -1));

        jPanel1.add(panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 200, 40));

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
        sales_list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sales_listMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(sales_list);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 740, 260));

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
        jPanel2.add(searchBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, 220, -1));

        export.setBackground(new java.awt.Color(89, 196, 19));
        export.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        export.setForeground(new java.awt.Color(255, 255, 255));
        export.setText("EXPORT");
        export.setBorder(null);
        export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportActionPerformed(evt);
            }
        });
        jPanel2.add(export, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 190, 100, 20));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 800, 500));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        Loginfrom lf = new Loginfrom();
        lf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

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
        // Default query when no search text is provided (fetch today's data)
        rs = dbc.getData(
            "SELECT sales.sale_id AS Sale_Id, product_table.prod_name AS Product_Name, " +
            "product_table.category AS Category, product_table.price AS Price, " +
            "sales.quantity_sold AS Quantity_Sold, sales.date AS Date, sales.time AS Time, " +
            "(product_table.price * sales.quantity_sold) AS Total " +
            "FROM sales " +
            "JOIN product_table ON sales.prod_id = product_table.prod_id " +
            "WHERE sales.date = CURDATE() " + // Fetch only today's sales
            "ORDER BY sales.quantity_sold DESC"
        );
        
        // Refresh the display data with all sales for today
        displayData(); // Call to refresh the data if the search is empty or cleared
        
    } else {
        // Query with search text filtering
        rs = dbc.getData(
            "SELECT sales.sale_id AS Sale_Id, product_table.prod_name AS Product_Name, " +
            "product_table.category AS Category, product_table.price AS Price, " +
            "sales.quantity_sold AS Quantity_Sold, sales.date AS Date, sales.time AS Time, " +
            "(product_table.price * sales.quantity_sold) AS Total " +
            "FROM sales " +
            "JOIN product_table ON sales.prod_id = product_table.prod_id " +
            "WHERE product_table.prod_name LIKE '%" + searchText + "%' " +
            "OR sales.date LIKE '%" + searchText + "%' " +
            "OR product_table.category LIKE '%" + searchText + "%' " +
            "ORDER BY sales.quantity_sold DESC"
        );
    }

    // Update the table model
    DefaultTableModel model = new DefaultTableModel(new String[]{
        "Sale Id", "Product Name", "Category", "Quantity Sold", "Date"
    }, 0);

    while (rs.next()) {
        model.addRow(new Object[]{
            rs.getString("Sale_Id"),
            rs.getString("Product_Name"),
            rs.getString("Category"),
            rs.getInt("Quantity_Sold"),
            rs.getString("Date"),
        });
    }

    sales_list.setModel(model);

    // Hide specific columns (optional)
    hideColumn(sales_list, 0);

    rs.close();
} catch (SQLException ex) {
    System.out.println("Errors: " + ex.getMessage());
}

        
    }//GEN-LAST:event_searchBarKeyReleased

    private void sales_listMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sales_listMousePressed
                if (SwingUtilities.isRightMouseButton(evt)) {
            popUp.show(sales_list, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_sales_listMousePressed

    private void pdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pdfActionPerformed

if (nameField.getText().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Please name the PDF first to generate.");
    return;
}

String name = nameField.getText() + ".pdf";
String location = System.getProperty("user.home") + "/Documents/";

try {
    dbConnector dbc = new dbConnector();
    String query = "SELECT sales.sale_id AS Sale_Id, product_table.prod_name AS Product_Name, " +
            "product_table.category AS Category, product_table.price AS Price, " +
            "sales.quantity_sold AS Quantity_Sold, sales.date AS Date, sales.time AS Time, " +
            "(product_table.price * sales.quantity_sold) AS Total " +
            "FROM sales " +
            "JOIN product_table ON sales.prod_id = product_table.prod_id " +
            "ORDER BY sales.quantity_sold DESC";
    ResultSet resultSet = dbc.getData(query);

// Initialize the document and table
com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A5.rotate());
PdfWriter.getInstance(document, new FileOutputStream(location + name));
document.open();

// Create a font for the header
Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
Font cellFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);

// Create a table with 7 columns
PdfPTable pdfPTable = new PdfPTable(7);
pdfPTable.setWidthPercentage(100); // Make table width span the entire page
pdfPTable.setSpacingBefore(10f);
pdfPTable.setSpacingAfter(10f);

// Set column widths
float[] columnWidths = {1.5f, 3f, 3f, 2f, 2f, 2.5f, 2.5f};
pdfPTable.setWidths(columnWidths);

// Create header cells
PdfPCell headerCell = new PdfPCell();
headerCell.setBackgroundColor(BaseColor.DARK_GRAY);
headerCell.setPadding(5);
headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);

// Add table headers with styling
String[] headers = {"Sale ID", "Product Name", "Quantity Sold", "Price", "Total", "Date", "Time"};
for (String header : headers) {
    headerCell.setPhrase(new Phrase(header, headerFont));
    pdfPTable.addCell(headerCell);
}

// Add rows to the table
if (resultSet.next()) {
    do {
        // Sale ID
        PdfPCell saleIdCell = new PdfPCell(new Phrase(resultSet.getString("Sale_Id"), cellFont));
        saleIdCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        saleIdCell.setPadding(5);
        pdfPTable.addCell(saleIdCell);

        // Product Name
        PdfPCell productNameCell = new PdfPCell(new Phrase(resultSet.getString("Product_Name"), cellFont));
        productNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        productNameCell.setPadding(5);
        pdfPTable.addCell(productNameCell);

        // Quantity Sold
        PdfPCell quantityCell = new PdfPCell(new Phrase(resultSet.getString("Quantity_Sold"), cellFont));
        quantityCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        quantityCell.setPadding(5);
        pdfPTable.addCell(quantityCell);

        // Price
        PdfPCell priceCell = new PdfPCell(new Phrase(resultSet.getString("Price"), cellFont));
        priceCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        priceCell.setPadding(5);
        pdfPTable.addCell(priceCell);

        // Total
        PdfPCell totalCell = new PdfPCell(new Phrase(resultSet.getString("Total"), cellFont));
        totalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        totalCell.setPadding(5);
        pdfPTable.addCell(totalCell);

        // Date
        PdfPCell dateCell = new PdfPCell(new Phrase(resultSet.getString("Date"), cellFont));
        dateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        dateCell.setPadding(5);
        pdfPTable.addCell(dateCell);

        // Time
        PdfPCell timeCell = new PdfPCell(new Phrase(resultSet.getString("Time"), cellFont));
        timeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        timeCell.setPadding(5);
        pdfPTable.addCell(timeCell);
    } while (resultSet.next());
}

// Add the table to the document
document.add(pdfPTable);
document.close();


    Window window = SwingUtilities.getWindowAncestor(exportData);
    if (window != null) {
        window.dispose();
    }
    JOptionPane.showMessageDialog(null, "Successfully Generated");
    nameField.setText("");
} catch (DocumentException | FileNotFoundException e) {
    System.err.println("Document Error: " + e);
} catch (SQLException ex) {
    System.out.println("SQL Error: " + ex);
}

        
    }//GEN-LAST:event_pdfActionPerformed

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed

JPanel myPanel = new JPanel();

try {
    dbConnector dbc = new dbConnector();
    ResultSet rs = dbc.getData(
        "SELECT product_table.prod_name AS prodname, " +
        "product_table.category AS cat, " +
        "sales.quantity_sold AS quansold, " +
        "product_table.price AS price, " +
        "(product_table.price * sales.quantity_sold) AS total, " +
        "sales.date AS date, " +
        "sales.time AS time " +
        "FROM sales " +
        "JOIN product_table ON sales.prod_id = product_table.prod_id " +
        "WHERE sales.sale_id = '" + sales_list.getValueAt(sales_list.getSelectedRow(), 0).toString() + "'"
    );

    if (rs.next()) {
        // Create a PrintSalesDetails instance
        PrintSalesDetails psd = new PrintSalesDetails();

        // Populate the fields in the print panel
        psd.prodname.setText(rs.getString("prodname"));
        psd.cat.setText(rs.getString("cat"));
        psd.quansold.setText(rs.getString("quansold"));
        psd.price.setText(rs.getString("price"));
        psd.total.setText(rs.getString("total"));
        psd.date.setText(rs.getString("date"));
        psd.time.setText(rs.getString("time"));

        // Use PanelPrinter to print or export the populated panel
        PanelPrinter pPrint = new PanelPrinter(psd.page); // Assuming `page` is a JPanel in PrintSalesDetails
        pPrint.printPanel();
    }
} catch (SQLException ex) {
    System.out.println("Error: " + ex.getMessage());
}

            
    }//GEN-LAST:event_printActionPerformed

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed

// Check if any row is selected
int selectedRow = sales_list.getSelectedRow();
if (selectedRow == -1) {
    JOptionPane.showMessageDialog(null, "Please select a sale record to view.");
    return;
}


 String saleId = sales_list.getValueAt(sales_list.getSelectedRow(), 0).toString(); // Assuming column 0 holds `sale_id`

try {
    dbConnector dbc = new dbConnector();

    // Debugging: Print the retrieved saleId to verify correctness
    System.out.println("Selected Sale ID: " + saleId);

    // Fetch details for the selected sale record
    ResultSet rs = dbc.getData(
        "SELECT sales.sale_id AS saleId, " +
        "product_table.prod_name AS prodname, " +
        "sales.quantity_sold AS quansold, " +
        "product_table.price AS price, " +
        "(sales.quantity_sold * product_table.price) AS total, " +
        "sales.date AS date, " +
        "sales.time AS time " +
        "FROM sales " +
        "JOIN product_table ON sales.prod_id = product_table.prod_id " +
        "WHERE sales.sale_id = '" + saleId + "'"
    );

    // Check if the query returned data
    if (rs.next()) {
        // Populate the viewpanel with fetched data
        sale_id.setText(rs.getString("saleId"));
        prodname.setText(rs.getString("prodname"));
        quansold.setText(rs.getString("quansold"));
        price.setText(rs.getString("price"));
        total.setText(rs.getString("total"));
        date.setText(rs.getString("date"));
        time.setText(rs.getString("time"));

        // Show the viewpanel in a dialog
        Object[] options = {};
        JOptionPane.showOptionDialog(null, viewpanel, "Sale Details",
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, options, null);
    } else {
        JOptionPane.showMessageDialog(null, "No data found for the selected sale.");
    }

    rs.close();
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(null, "Error fetching sale details: " + ex.getMessage());
}
      
        
    }//GEN-LAST:event_viewActionPerformed

    private void exportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportActionPerformed

        Object[] options = {};
        JOptionPane.showOptionDialog(null, exportData, "",
            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, options, null);
    }//GEN-LAST:event_exportActionPerformed

    private void panel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel2MouseClicked
        salereport r = new salereport();
        r.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panel2MouseClicked

    private void panel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_panel2MouseEntered

    private void panel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_panel2MouseExited

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
    private javax.swing.JLabel cat;
    private javax.swing.JLabel dailySale;
    private javax.swing.JLabel date;
    private javax.swing.JButton export;
    private javax.swing.JPanel exportData;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JTextField nameField;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JButton pdf;
    private javax.swing.JPopupMenu popUp;
    private javax.swing.JLabel price;
    private javax.swing.JButton print;
    private javax.swing.JLabel prodname;
    private javax.swing.JLabel product;
    private javax.swing.JLabel quansold;
    private javax.swing.JLabel sale_id;
    private javax.swing.JTable sales_list;
    private javax.swing.JTextField searchBar;
    private javax.swing.JLabel time;
    private javax.swing.JLabel total;
    private javax.swing.JLabel totalSale;
    private javax.swing.JMenuItem view;
    private javax.swing.JPanel viewpanel;
    // End of variables declaration//GEN-END:variables
}
