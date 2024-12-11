
package Admin;

import static User.payment.getCurrentDate;
import config.dbConnector;
import form.Loginfrom;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.List;
import java.sql.Date;
import java.time.LocalDate;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.jfree.chart.axis.CategoryTick;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.data.category.CategoryDataset;


public class salesreport extends javax.swing.JFrame {


public salesreport() {
    initComponents();
    displayData();
    displayBarChart();
    displayDailySalesBarChart();
}

public void displayData() {
    try {
        // Get today's date
        LocalDate today = LocalDate.now();
        String todayDate = Date.valueOf(today).toString();

        dbConnector dbc = new dbConnector();
        
        // Query to get total quantity sold and total sales for each product for today
        ResultSet rs = dbc.getData(
            "SELECT p.prod_name AS ProductName, " +
            "SUM(s.quantity_sold) AS TotalQuantitySold, " +
            "SUM(p.price * s.quantity_sold) AS TotalSales " +
            "FROM sales s " +
            "JOIN product_table p ON s.prod_id = p.prod_id " +
            "WHERE s.Date = '" + todayDate + "' " + // Filter for today's sales
            "GROUP BY p.prod_name " + // Group by product name
            "ORDER BY TotalQuantitySold DESC" // Order by total quantity sold, from highest to lowest
        );

        // Custom table model
        DefaultTableModel model = new DefaultTableModel(new String[]{
            "Product Name", "Total Quantity Sold", "Total Sales"
        }, 0);

        // Populate the table with data
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("ProductName"),
                rs.getInt("TotalQuantitySold"),
                String.format("₱%.2f", rs.getDouble("TotalSales"))
            });
        }

        // If no data is found for today's sales, show a default message
        if (model.getRowCount() == 0) {
            model.addRow(new Object[]{"-", "No Sales Today", "-"});
        }

        // Set the model to the JTable
        topsales.setModel(model);

        rs.close();
    } catch (SQLException ex) {
        System.out.println("Errors: " + ex.getMessage());
    }
}

public void displayBarChart() {
    try {
        // Get today's date
        LocalDate today = LocalDate.now();
        String todayDate = Date.valueOf(today).toString();

        dbConnector dbc = new dbConnector();
        
        // Query to get total sales for each product for today
        ResultSet rs = dbc.getData(
            "SELECT p.prod_name AS ProductName, " +
            "SUM(p.price * s.quantity_sold) AS TotalSales " +
            "FROM sales s " +
            "JOIN product_table p ON s.prod_id = p.prod_id " +
            "WHERE s.Date = '" + todayDate + "' " +
            "GROUP BY p.prod_name " +
            "ORDER BY TotalSales DESC"
        );

        // Create a dataset for the bar chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Populate the dataset with data from the result set
        while (rs.next()) {
            String productName = rs.getString("ProductName");
            double totalSales = rs.getDouble("TotalSales");
            dataset.addValue(totalSales, "Total Sales", productName);
        }

        // If no data is found for today's sales, add a default value
        if (dataset.getRowCount() == 0) {
            dataset.addValue(0, "Total Sales", "No Sales Today");
        }

        // Create the bar chart using the dataset
        JFreeChart barChart = ChartFactory.createBarChart(
            "Earnings Statistics",  
            null,  
            null,  
            dataset,  // Dataset for the chart
            PlotOrientation.VERTICAL,
            false,  // No legend
            true,   // Tooltips enabled
            false   // URLs disabled
        );

        // Customize the chart appearance
        CategoryPlot categoryPlot = barChart.getCategoryPlot();
        categoryPlot.setBackgroundPaint(Color.WHITE); // Set background color
        categoryPlot.setOutlineVisible(false); // Remove outline
        categoryPlot.setDomainGridlinesVisible(false); // Hide X-axis gridlines
        categoryPlot.setRangeGridlinesVisible(false);  // Hide Y-axis gridlines

        // Customize the renderer (bar color)
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        renderer.setSeriesPaint(0, new Color(0, 255, 0)); // Green bars
        renderer.setBarPainter(new StandardBarPainter()); // Solid bars, no gradient
        renderer.setShadowVisible(false); // No shadow effect

        // Create a ChartPanel to hold the chart
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(600, 400)); // Set panel size

        // Clear and add the chart to the "barChart" panel
        barChartPanel.removeAll();  // Clear existing content in the panel
        barChartPanel.setLayout(new BorderLayout());
        barChartPanel.add(chartPanel, BorderLayout.CENTER); // Add the chart to the panel
        barChartPanel.validate(); // Revalidate the panel to apply changes
        barChartPanel.repaint(); // Repaint the panel to refresh the display

        rs.close();
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
}

public void displayDataWithoutTable() {
    try {
        // Get today's date
        LocalDate today = LocalDate.now();
        String todayDate = Date.valueOf(today).toString();

        dbConnector dbc = new dbConnector();

        // Query to get sales data for today
        ResultSet rs = dbc.getData(
            "SELECT sales.sale_id AS Sale_Id, product_table.prod_name AS Product_Name, " +
            "product_table.category AS Category, product_table.price AS Price, " +
            "sales.quantity_sold AS Quantity_Sold, sales.date AS Date, " +
            "(product_table.price * sales.quantity_sold) AS Total " +
            "FROM sales " +
            "JOIN product_table ON sales.prod_id = product_table.prod_id " +
            "WHERE sales.date = '" + todayDate + "' " +
            "ORDER BY sales.quantity_sold DESC"
        );

        double totalSales = 0; // Accumulate daily total

        // Iterate through the result set and process the data
        System.out.println("Today's Sales Data:");
        while (rs.next()) {
            int saleId = rs.getInt("Sale_Id");
            String productName = rs.getString("Product_Name");
            String category = rs.getString("Category");
            double price = rs.getDouble("Price");
            int quantitySold = rs.getInt("Quantity_Sold");
            double rowTotal = rs.getDouble("Total"); // Total sales for the row
            totalSales += rowTotal;

            // Log the data to the console
            System.out.printf("Sale ID: %d | Product: %s | Category: %s | Price: ₱%.2f | Quantity: %d | Total: ₱%.2f%n",
                    saleId, productName, category, price, quantitySold, rowTotal);
        }

        // Display the total sales for today
        System.out.println("Total Daily Sales: ₱" + String.format("%.2f", totalSales));

        rs.close();
    } catch (SQLException ex) {
        System.out.println("Error in displayDataWithoutTable: " + ex.getMessage());
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

        rs.close();
    } catch (SQLException ex) {
        System.out.println("Error in dailySales: " + ex.getMessage());
    }
}

public void displayDailySalesBarChart() {
    try {
        dbConnector dbc = new dbConnector();

        // Query to get daily sales grouped by date
        ResultSet rs = dbc.getData(
            "SELECT sales.date AS SaleDate, SUM(product_table.price * sales.quantity_sold) AS TotalSales " +
            "FROM sales " +
            "JOIN product_table ON sales.prod_id = product_table.prod_id " +
            "GROUP BY sales.date " +
            "ORDER BY sales.date ASC"
        );

        // Create a dataset for the bar chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        ArrayList<String> formattedLabels = new ArrayList<>(); // Store formatted labels

        // Populate the dataset with daily sales data and formatted labels
        while (rs.next()) {
            String saleDate = rs.getString("SaleDate");
            double totalSales = rs.getDouble("TotalSales");
            dataset.addValue(totalSales, "Daily Sales", saleDate);

            // Split the date and format it
            String[] dateParts = saleDate.split("-");
            String day = dateParts[2];  // Day part
            String month = getMonthName(Integer.parseInt(dateParts[1])); // Month part (converted to string)

            // Create custom label with Month and Day
            formattedLabels.add(month + " " + day);  // Format as "Dec 10"
        }

        // Create the bar chart
        JFreeChart barChart = ChartFactory.createBarChart(
            "Daily Sales",      // Chart title
            null,               // X-axis label
            null,  // Y-axis label
            dataset,            // Dataset
            PlotOrientation.VERTICAL,
            false,              // No legend
            true,               // Tooltips enabled
            false               // URLs disabled
        );

        // Customize the chart appearance
        CategoryPlot categoryPlot = barChart.getCategoryPlot();
        categoryPlot.setBackgroundPaint(Color.WHITE); // Set background color
        categoryPlot.setOutlineVisible(false);        // Remove outline
        categoryPlot.setDomainGridlinesVisible(false); // Hide X-axis gridlines
        categoryPlot.setRangeGridlinesVisible(true);  // Show Y-axis gridlines

        // Customize the renderer (bar color)
        BarRenderer renderer = (BarRenderer) categoryPlot.getRenderer();
        renderer.setSeriesPaint(0, new Color(89, 196, 19)); // Green bar
        renderer.setBarPainter(new StandardBarPainter()); // Solid bars
        renderer.setShadowVisible(false);                // No shadow effect

        // Set tooltips for the bars using CategoryToolTipGenerator
        renderer.setToolTipGenerator(new CategoryToolTipGenerator() {
            @Override
            public String generateToolTip(CategoryDataset dataset, int row, int column) {
                // Use the formatted label for tooltips
                return formattedLabels.get(column);
            }
        });

        // Set customized labels using CategoryAxis tick labels
        CategoryAxis domainAxis = categoryPlot.getDomainAxis();

        // Update the labels manually using the formatted labels
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD);
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // Rotate labels for better readability

        // Add the chart to a panel
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(800, 600)); // Set panel size

        // Add the chart panel to the "dailyBarChart" panel
        dailyBarChart.removeAll();  // Remove existing components if any
        dailyBarChart.setLayout(new BorderLayout());
        dailyBarChart.add(chartPanel, BorderLayout.CENTER);  // Add the chart to the panel
        dailyBarChart.validate();
        dailyBarChart.repaint(); // Update the display to show the chart

        rs.close();
    } catch (SQLException ex) {
        System.out.println("Error in displayDailySalesBarChart: " + ex.getMessage());
    }
}

private String getMonthName(int month) {
    String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    return months[month - 1];
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        topsales = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        dailyBarChart = new javax.swing.JPanel();
        barChartPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        panel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        panel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        panel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        topsales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(topsales);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 307, 740, 170));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(89, 196, 19), 2));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        label.setForeground(new java.awt.Color(102, 102, 102));
        label.setText("Top Sale's");
        jPanel2.add(label, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 110, -1));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Sales Reports");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        dailyBarChart.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(89, 196, 19)));
        dailyBarChart.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        dailyBarChart.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(dailyBarChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 360, 210));

        barChartPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(89, 196, 19)));
        barChartPanel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        barChartPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(barChartPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 360, 210));

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
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/file-circle-info (1).png"))); // NOI18N
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

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
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
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/assessment.png"))); // NOI18N
        jLabel11.setText(" Transaction");
        panel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 10, 190, -1));

        jPanel1.add(panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 200, 40));

        title.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("Posify");
        jPanel1.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

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

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 500));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        Loginfrom lf = new Loginfrom();
        lf.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel9MouseClicked

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
            java.util.logging.Logger.getLogger(salesreport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(salesreport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(salesreport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(salesreport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new salesreport().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel barChartPanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel dailyBarChart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JLabel title;
    private javax.swing.JTable topsales;
    // End of variables declaration//GEN-END:variables
}
