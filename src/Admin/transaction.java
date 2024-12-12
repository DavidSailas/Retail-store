
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
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URI;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;



public class transaction extends javax.swing.JFrame {


    public transaction() {
        initComponents();
        displayData();
        productSold();
        weeklySales();
        dailySales();
       
    }



public String getCurrentDate() {
    LocalDate today = LocalDate.now();
    return today.toString();  // Returns date in YYYY-MM-DD format
}

public String getLastWeekDate() {
    LocalDate today = LocalDate.now();
    LocalDate lastWeek = today.minusWeeks(1);  // Subtract one week from today
    return lastWeek.toString();  // Returns date in YYYY-MM-DD format
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
            "(product_table.price * sales.quantity_sold) AS Total, product_table.expire AS Expire " +
            "FROM sales " +
            "JOIN product_table ON sales.prod_id = product_table.prod_id " +
            "WHERE sales.date = '" + todayDate + "' " +
            "ORDER BY sales.quantity_sold DESC"
        );

        // Add 'Price' and 'Total' columns to the table model
        DefaultTableModel model = new DefaultTableModel(new String[]{
            "Sale Id", "Product Name", "Category", "Price", "Quantity Sold", "Total", "Date"
        }, 0);

        double totalSales = 0; // Accumulate daily total

        // Populate the table with data
        while (rs.next()) {
            double rowTotal = rs.getDouble("Total"); // Get row total
            totalSales += rowTotal; // Add to the daily total

            model.addRow(new Object[]{
                rs.getString("Sale_Id"),
                rs.getString("Product_Name"),
                rs.getString("Category"),
                rs.getDouble("Price"),
                rs.getInt("Quantity_Sold"),
                String.format("₱%.2f", rowTotal), // Format total as currency
                rs.getString("Date"),
            });
        }

        sales_list.setModel(model);

        // Hide specific columns if necessary (optional)
        hideColumn(sales_list, 0); // Example: Hide "Sale Id" column (index 0)
        hideColumn(sales_list, 3);
        hideColumn(sales_list, 5);
        // Display daily total sales below the table
        dailySale.setText("₱" + String.format("%.2f", totalSales));

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

        LocalDate today = LocalDate.now();
        String todayDate = Date.valueOf(today).toString();

        ResultSet rs = dbc.getData(
            "SELECT COUNT(*) AS NROWS FROM sales WHERE date = '" + todayDate + "'"
        );

        int rowCount = 0;
        if (rs.next()) {
            rowCount = rs.getInt("NROWS");
        }
        
        product.setText("" + rowCount + " ");
    } catch (SQLException ex) {
        System.out.println("Errors: " + ex.getMessage());
    }
}


public void weeklySales() {
    try {
        dbConnector dbc = new dbConnector();
        String currentDate = getCurrentDate();  // Get today's date (YYYY-MM-DD format)
        String lastWeekDate = getLastWeekDate();  // Calculate the date 7 days ago

        // Query for weekly sales total
        ResultSet rs = dbc.getData(
            "SELECT SUM(product_table.price * sales.quantity_sold) AS TotalSales " +
            "FROM sales " +
            "JOIN product_table ON sales.prod_id = product_table.prod_id " +
            "WHERE sales.date BETWEEN '" + lastWeekDate + "' AND '" + currentDate + "'"
        );

        double totalWeeklySales = 0;
        if (rs.next()) {
            totalWeeklySales = rs.getDouble("TotalSales");
        }

        // Update weekly total sales JLabel
        totalSale.setText("₱" + String.format("%.2f", totalWeeklySales));
        System.out.println("₱" + totalWeeklySales);  // Debug output to verify the total

        rs.close();
    } catch (SQLException ex) {
        System.out.println("Error in weeklySales: " + ex.getMessage());
    }
}

public void dailySales() {
    try {
        dbConnector dbc = new dbConnector();
        String currentDate = getCurrentDate();  // Get today's date (YYYY-MM-DD format)

        // Query for daily sales total
        ResultSet rs = dbc.getData(
            "SELECT SUM(product_table.price * sales.quantity_sold) AS TotalSales " +
            "FROM sales " +
            "JOIN product_table ON sales.prod_id = product_table.prod_id " +
            "WHERE sales.date = '" + currentDate + "'"
        );

        double totalDailySales = 0;
        if (rs.next()) {
            totalDailySales = rs.getDouble("TotalSales");
        }

        // Update daily total sales JLabel
        dailySale.setText("₱" + String.format("%.2f", totalDailySales));
        System.out.println("₱" + totalDailySales);  // Debug output to verify the total

        rs.close();
    } catch (SQLException ex) {
        System.out.println("Error in dailySales: " + ex.getMessage());
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
        jLabel26 = new javax.swing.JLabel();
        expire = new javax.swing.JLabel();
        exportData = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        pdf = new javax.swing.JButton();
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
        label1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        panel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        panel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        panel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();

        view.setText("View");
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });
        popUp.add(view);

        viewpanel.setBackground(new java.awt.Color(255, 255, 255));
        viewpanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(89, 196, 19), 2));
        viewpanel.setMaximumSize(new java.awt.Dimension(450, 500));
        viewpanel.setMinimumSize(new java.awt.Dimension(450, 500));
        viewpanel.setPreferredSize(new java.awt.Dimension(450, 500));

        print.setBackground(new java.awt.Color(255, 24, 9));
        print.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        print.setForeground(new java.awt.Color(255, 255, 255));
        print.setText(" EXPORT");
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(89, 196, 19));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("  TRANSACTION DETAILS");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 448, 50));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setText("Category:");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(102, 102, 102));
        jLabel15.setText("Product Name:");

        jLabel16.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(102, 102, 102));
        jLabel16.setText("Quantity Sold");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 102, 102));
        jLabel17.setText("Price:");

        jLabel18.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("Total:");

        jLabel19.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("Date:");

        jLabel20.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 102, 102));
        jLabel20.setText("Time:");

        prodname.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        prodname.setText("Product Name");

        cat.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        cat.setText("Category");

        quansold.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        quansold.setText("Quantity Sold");

        price.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        price.setText("Price");

        total.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        total.setText("Total");

        date.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        date.setText("Date");

        time.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        time.setText("Time");

        jLabel25.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(102, 102, 102));
        jLabel25.setText("Sale Id:");

        sale_id.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        sale_id.setText("Sale Id");

        jLabel26.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(102, 102, 102));
        jLabel26.setText("Expire Date:");

        expire.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        expire.setText("Expire Date");

        javax.swing.GroupLayout viewpanelLayout = new javax.swing.GroupLayout(viewpanel);
        viewpanel.setLayout(viewpanelLayout);
        viewpanelLayout.setHorizontalGroup(
            viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(viewpanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(viewpanelLayout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addGap(164, 164, 164)
                        .addComponent(sale_id, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewpanelLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140)
                        .addComponent(cat, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewpanelLayout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(99, 99, 99)
                        .addComponent(prodname, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewpanelLayout.createSequentialGroup()
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(110, 110, 110)
                        .addComponent(quansold, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewpanelLayout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120)
                        .addComponent(expire, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewpanelLayout.createSequentialGroup()
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140)
                        .addComponent(price, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewpanelLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140)
                        .addComponent(total, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(viewpanelLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(viewpanelLayout.createSequentialGroup()
                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140)
                        .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        viewpanelLayout.setVerticalGroup(
            viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewpanelLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(sale_id))
                .addGap(18, 18, 18)
                .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(cat))
                .addGap(18, 18, 18)
                .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(prodname))
                .addGap(18, 18, 18)
                .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(quansold))
                .addGap(18, 18, 18)
                .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(expire))
                .addGap(18, 18, 18)
                .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(price))
                .addGap(18, 18, 18)
                .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(total))
                .addGap(18, 18, 18)
                .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(date))
                .addGap(18, 18, 18)
                .addGroup(viewpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(time))
                .addGap(18, 18, 18)
                .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        exportData.setBackground(new java.awt.Color(255, 255, 255));
        exportData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(89, 196, 19), 2));
        exportData.setMinimumSize(new java.awt.Dimension(470, 300));
        exportData.setPreferredSize(new java.awt.Dimension(470, 300));

        jPanel7.setBackground(new java.awt.Color(89, 196, 19));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setBackground(new java.awt.Color(89, 196, 19));
        jLabel22.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Generate Report");
        jPanel7.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 60));

        jLabel23.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(204, 204, 204));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Download reports as:");

        jLabel24.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(27, 57, 77));
        jLabel24.setText("File name:");

        pdf.setBackground(new java.awt.Color(255, 24, 9));
        pdf.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        pdf.setForeground(new java.awt.Color(255, 255, 255));
        pdf.setText(" PDF");
        pdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pdfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout exportDataLayout = new javax.swing.GroupLayout(exportData);
        exportData.setLayout(exportDataLayout);
        exportDataLayout.setHorizontalGroup(
            exportDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(exportDataLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel24))
            .addGroup(exportDataLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(exportDataLayout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(exportDataLayout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(pdf, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        exportDataLayout.setVerticalGroup(
            exportDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exportDataLayout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(pdf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(89, 196, 19), 2));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("TRANSACTION");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        totalSale.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        totalSale.setForeground(new java.awt.Color(0, 128, 0));
        totalSale.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalSale.setText("0");
        jPanel2.add(totalSale, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 200, 30));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 128, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Weekly Sales");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 160, 30));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 128, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Product Sold");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 160, 30));

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
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, 130, 30));

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
        searchBar.setText(" Search date");
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

        export.setBackground(new java.awt.Color(255, 24, 9));
        export.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        export.setForeground(new java.awt.Color(255, 255, 255));
        export.setText("EXPORT");
        export.setBorder(null);
        export.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportActionPerformed(evt);
            }
        });
        jPanel2.add(export, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 190, 100, 20));

        label1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        label1.setForeground(new java.awt.Color(102, 102, 102));
        label1.setText("Search:");
        jPanel2.add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, 60, 20));

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
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/file-circle-info.png"))); // NOI18N
        jLabel2.setText(" Sales Reports");
        panel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 10, 210, -1));

        jPanel1.add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 200, 40));

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
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/house-chimney (1).png"))); // NOI18N
        jLabel3.setText(" Dashboard");
        panel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 10, 190, -1));

        jPanel1.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 200, 40));

        jLabel28.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Log out");
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 470, -1, -1));

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
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/assessment (1).png"))); // NOI18N
        jLabel11.setText(" Transaction");
        panel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 10, 190, -1));

        jPanel1.add(panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 200, 40));

        panel3.setBackground(new java.awt.Color(89, 196, 19));
        panel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panel3MouseExited(evt);
            }
        });
        panel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/users.png"))); // NOI18N
        jLabel4.setText(" Users");
        panel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 10, 150, -1));

        jPanel1.add(panel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 200, 40));

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

    private void searchBarFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBarFocusGained
        if (searchBar.getText().equals(" Search date")){
            searchBar.setText("");
            searchBar.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_searchBarFocusGained

    private void searchBarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchBarFocusLost
        if (searchBar.getText().equals("")){
            searchBar.setText(" Search date");
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
String fullPath = location + name;

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
    PdfWriter.getInstance(document, new FileOutputStream(fullPath));
    document.open();

    // Create a font for the header
    Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
    Font cellFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);

    // Create a table with 7 columns
    PdfPTable pdfPTable = new PdfPTable(7);
    pdfPTable.setWidthPercentage(100);
    pdfPTable.setSpacingBefore(10f);
    pdfPTable.setSpacingAfter(10f);

    // Set column widths
    float[] columnWidths = {1.5f, 3f, 3f, 2f, 2f, 2.5f, 2.5f};
    pdfPTable.setWidths(columnWidths);

    // Add table headers and rows
    String[] headers = {"Sale ID", "Product Name", "Quantity Sold", "Price", "Total", "Date", "Time"};
    for (String header : headers) {
        PdfPCell headerCell = new PdfPCell(new Phrase(header, headerFont));
        headerCell.setBackgroundColor(new BaseColor(89, 196, 19)); 
        headerCell.setPadding(5);
        headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        pdfPTable.addCell(headerCell);
    }

    if (resultSet.next()) {
        do {
            pdfPTable.addCell(new PdfPCell(new Phrase(resultSet.getString("Sale_Id"), cellFont)));
            pdfPTable.addCell(new PdfPCell(new Phrase(resultSet.getString("Product_Name"), cellFont)));
            pdfPTable.addCell(new PdfPCell(new Phrase(resultSet.getString("Quantity_Sold"), cellFont)));
            pdfPTable.addCell(new PdfPCell(new Phrase(resultSet.getString("Price"), cellFont)));
            pdfPTable.addCell(new PdfPCell(new Phrase(resultSet.getString("Total"), cellFont)));
            pdfPTable.addCell(new PdfPCell(new Phrase(resultSet.getString("Date"), cellFont)));
            pdfPTable.addCell(new PdfPCell(new Phrase(resultSet.getString("Time"), cellFont)));
        } while (resultSet.next());
    }

    document.add(pdfPTable);
    document.close();

    // Show JOptionPane to notify and open file
    int response = JOptionPane.showOptionDialog(null, 
            "PDF generated successfully!\nFile saved to: " + fullPath + "\nWould you like to open the file?", 
            "PDF Generated",
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.INFORMATION_MESSAGE, 
            null, 
            new Object[]{"Open File", "Close"}, 
            "Open File");

 if (response == JOptionPane.YES_OPTION) {
    try {
        // Construct the file path as a URI
        File file = new File(fullPath);
        if (file.exists()) {
            // Convert file path to URL for browser compatibility
            String fileUrl = file.toURI().toURL().toString();
            
            // Open the PDF in the system's default web browser
            Desktop.getDesktop().browse(new URI(fileUrl));
        } else {
            JOptionPane.showMessageDialog(null, "The file does not exist.");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "An error occurred while opening the file in the browser.");
    }
}


    nameField.setText("");
} catch (DocumentException | FileNotFoundException e) {
    e.printStackTrace();
    JOptionPane.showMessageDialog(null, "An error occurred while generating the PDF: " + e.getMessage());
} catch (SQLException ex) {
    ex.printStackTrace();
    JOptionPane.showMessageDialog(null, "An error occurred while accessing the database: " + ex.getMessage());
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
        "sales.time AS time, " +
        "product_table.expire AS expire " +
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

        // Handle expiration date
        String expireDate = rs.getString("expire");
        if ("0001-12-31".equals(expireDate)) {
            psd.expire.setText("No Expiry Date"); // Set 'No Expire' if expiration date is 9999-12-31
        } else {
            psd.expire.setText(expireDate); // Otherwise, display the actual expiration date
        }

        // Use PanelPrinter to print or export the populated panel
        PanelPrinter pPrint = new PanelPrinter(psd.page); // Assuming `page` is a JPanel in PrintSalesDetails
        pPrint.printPanel();

        // Notify user via JOptionPane after printing
        JOptionPane.showMessageDialog(null, "Print completed successfully!", "Print Notification", JOptionPane.INFORMATION_MESSAGE);
    }
} catch (SQLException ex) {
    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
} catch (Exception ex) {
    JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    // Fetch details for the selected sale record, including the 'expire' field
    ResultSet rs = dbc.getData(
        "SELECT sales.sale_id AS saleId, " +
        "product_table.prod_name AS prodname, " +
        "sales.quantity_sold AS quansold, " +
        "product_table.price AS price, " +
        "(sales.quantity_sold * product_table.price) AS total, " +
        "sales.date AS date, " +
        "sales.time AS time, " +
        "product_table.expire AS expire " +  // Add expire field here
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

        // Get the expire date and handle "9999-12-31" as "No Expire"
        String expireDate = rs.getString("expire");
        if ("9999-12-31".equals(expireDate)) {
            expire.setText("No Expire");
        } else {
            expire.setText(expireDate);  // Set the actual expire date if it's different
        }

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

    private void panel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel1MouseClicked
        salesreport a = new salesreport();
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

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        Loginfrom lf = new Loginfrom();
        lf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel28MouseClicked

    private void panel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel2MouseClicked
        transaction r = new transaction();
        r.setVisible(true);
    }//GEN-LAST:event_panel2MouseClicked

    private void panel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel2MouseEntered
        panel2.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_panel2MouseEntered

    private void panel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel2MouseExited
        panel2.setBackground(new Color(89,196,19));
    }//GEN-LAST:event_panel2MouseExited

    private void panel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel3MouseClicked
        users u = new users();
        u.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_panel3MouseClicked

    private void panel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel3MouseEntered
        panel3.setBackground(new Color(204,204,204));
    }//GEN-LAST:event_panel3MouseEntered

    private void panel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel3MouseExited
        panel3.setBackground(new Color(89,196,19));
    }//GEN-LAST:event_panel3MouseExited
    
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
            java.util.logging.Logger.getLogger(transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaction.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaction().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cat;
    private javax.swing.JLabel dailySale;
    private javax.swing.JLabel date;
    private javax.swing.JLabel expire;
    private javax.swing.JButton export;
    private javax.swing.JPanel exportData;
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
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label;
    private javax.swing.JLabel label1;
    private javax.swing.JTextField nameField;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
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
    private javax.swing.JLabel title;
    private javax.swing.JLabel total;
    private javax.swing.JLabel totalSale;
    private javax.swing.JMenuItem view;
    private javax.swing.JPanel viewpanel;
    // End of variables declaration//GEN-END:variables
}
