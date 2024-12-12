/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import config.dbConnector;
import config.Session;
import form.Loginfrom;
import java.awt.Color;
import java.awt.Component;
import java.awt.Window;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author PC
 */
public class archivedusers extends javax.swing.JFrame {

 
    public archivedusers() {
        initComponents();
        displayData();
        DefaultTableModel model = (DefaultTableModel) archiveTbl.getModel();
    }

public void displayData() {
    try {
        dbConnector connector = new dbConnector();
        // Query to get only 'Archived' records
        ResultSet rs = connector.getData("SELECT id, fname, lname, status FROM user_table WHERE status = 'Archived'");

        // Create a model for the table with the required columns
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "First Name", "Last Name", "Status"});
        model.setRowCount(0); // Clear any existing rows

        // Process the result set and populate the table model
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("id"),
                rs.getString("fname"),
                rs.getString("lname"),
                rs.getString("status")
            });
        }

        // Set the model to the JTable
        archiveTbl.setModel(model);

        // Hide the "ID" column using the helper method
        hideColumn(archiveTbl, 0);

        // Apply custom cell renderer for the "Status" column to color "Archived" entries
        archiveTbl.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (value != null && value.toString().equals("Archived")) {
                    c.setForeground(Color.RED); // Set the color to red for "Archived" status
                } else {
                    c.setForeground(table.getForeground()); // Default color
                }
                return c;
            }
        });

        // Repaint the table header to apply changes
        JTableHeader th = archiveTbl.getTableHeader();
        th.repaint();

        // Close the result set
        rs.close();
    } catch (SQLException ex) {
        System.out.println("Errors: " + ex.getMessage());
    }
}

// Helper method to hide a column in a JTable
private void hideColumn(JTable table, int columnIndex) {
    TableColumn column = table.getColumnModel().getColumn(columnIndex);
    column.setMinWidth(0);
    column.setMaxWidth(0);
    column.setPreferredWidth(0);
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popUp = new javax.swing.JPopupMenu();
        view = new javax.swing.JMenuItem();
        unarchive = new javax.swing.JMenuItem();
        viewpanel = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        u_id = new javax.swing.JLabel();
        fullname2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        umail = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        type = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        stats = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        back1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        confirmunarchive = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        noBT = new javax.swing.JButton();
        yesBT1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cancle = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        archiveTbl = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        panel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        panel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        panel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        panel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        title = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        label1 = new javax.swing.JLabel();
        searchBar = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();

        view.setText("View");
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });
        popUp.add(view);

        unarchive.setText("Unarchive");
        unarchive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unarchiveActionPerformed(evt);
            }
        });
        popUp.add(unarchive);

        viewpanel.setBackground(new java.awt.Color(255, 255, 255));
        viewpanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(83, 215, 105), 2));
        viewpanel.setMinimumSize(new java.awt.Dimension(350, 450));
        viewpanel.setPreferredSize(new java.awt.Dimension(350, 450));
        viewpanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 153, 153));
        jLabel12.setText("Id:");
        viewpanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 230, -1, -1));

        u_id.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        u_id.setText("sample");
        viewpanel.add(u_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, -1, -1));

        fullname2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        fullname2.setText("Fullname");
        viewpanel.add(fullname2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 260, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(153, 153, 153));
        jLabel6.setText("Username:");
        viewpanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, -1, -1));

        username.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        username.setText("sample");
        viewpanel.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 153, 153));
        jLabel7.setText("Email:");
        viewpanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, -1, -1));

        umail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        umail.setText("sample");
        viewpanel.add(umail, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 153));
        jLabel8.setText("Type:");
        viewpanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 320, -1, -1));

        type.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        type.setText("sample");
        viewpanel.add(type, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 340, -1, -1));

        jLabel13.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 153));
        jLabel13.setText("Status:");
        viewpanel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 390, -1, -1));

        stats.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        stats.setText("sample");
        viewpanel.add(stats, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 410, -1, -1));

        jPanel4.setBackground(new java.awt.Color(89, 196, 19));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("User Details");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(48, 0, 250, 50));

        back1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        back1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/left.png"))); // NOI18N
        back1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                back1MouseClicked(evt);
            }
        });
        jPanel4.add(back1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        viewpanel.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/circle-user (1).png"))); // NOI18N
        viewpanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, -1, -1));

        confirmunarchive.setBackground(new java.awt.Color(255, 255, 255));
        confirmunarchive.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(83, 215, 105), 2));
        confirmunarchive.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Are you sure you want to unarchive user?");
        confirmunarchive.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 420, 20));

        noBT.setBackground(new java.awt.Color(252, 61, 57));
        noBT.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        noBT.setForeground(new java.awt.Color(255, 255, 255));
        noBT.setText("NO");
        noBT.setBorderPainted(false);
        noBT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                noBTMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                noBTMouseExited(evt);
            }
        });
        noBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noBTActionPerformed(evt);
            }
        });
        confirmunarchive.add(noBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 80, -1));

        yesBT1.setBackground(new java.awt.Color(91, 164, 252));
        yesBT1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        yesBT1.setForeground(new java.awt.Color(255, 255, 255));
        yesBT1.setText("YES");
        yesBT1.setBorderPainted(false);
        yesBT1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                yesBT1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                yesBT1MouseExited(evt);
            }
        });
        yesBT1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yesBT1ActionPerformed(evt);
            }
        });
        confirmunarchive.add(yesBT1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 80, -1));

        jPanel3.setBackground(new java.awt.Color(89, 196, 19));

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText(" NOTICE !");
        jLabel15.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(255, 255, 255)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        confirmunarchive.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 50));
        confirmunarchive.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 420, 10));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 500));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(89, 196, 19), 2));
        jPanel1.setMinimumSize(new java.awt.Dimension(1000, 500));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 500));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cancle.setBackground(new java.awt.Color(255, 168, 32));
        cancle.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        cancle.setForeground(new java.awt.Color(255, 255, 255));
        cancle.setText("CANCEL");
        cancle.setPreferredSize(new java.awt.Dimension(60, 30));
        cancle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancleActionPerformed(evt);
            }
        });
        jPanel1.add(cancle, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 450, 100, -1));

        archiveTbl.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        archiveTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                archiveTblMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(archiveTbl);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, 750, 320));

        jPanel5.setBackground(new java.awt.Color(89, 196, 19));
        jPanel5.setPreferredSize(new java.awt.Dimension(200, 450));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jLabel3.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/file-circle-info.png"))); // NOI18N
        jLabel3.setText(" Sales Reports");
        panel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 10, 210, -1));

        jPanel5.add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 200, 40));

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

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/house-chimney (1).png"))); // NOI18N
        jLabel4.setText(" Dashboard");
        panel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 10, 190, -1));

        jPanel5.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 200, 40));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Log out");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 470, -1, -1));

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

        jPanel5.add(panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 200, 40));

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

        jLabel5.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/users (1).png"))); // NOI18N
        jLabel5.setText(" Users");
        panel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 10, 150, -1));

        jPanel5.add(panel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 200, 40));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        title.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        title.setForeground(new java.awt.Color(0, 102, 0));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/IMPos (2).png"))); // NOI18N
        title.setText("IMPos");
        jPanel2.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 40));

        jPanel5.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 200, 40));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 500));

        jLabel14.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(153, 153, 153));
        jLabel14.setText("ARCHIVED USERS");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, -1, -1));

        label1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        label1.setForeground(new java.awt.Color(102, 102, 102));
        label1.setText("Search:");
        jPanel1.add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 70, 60, 20));

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
        jPanel1.add(searchBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 70, 210, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(89, 196, 19));
        jLabel16.setText("_____________________________________________________________________");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 760, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void archiveTblMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_archiveTblMousePressed
        if (SwingUtilities.isRightMouseButton(evt)) {
            popUp.show(archiveTbl, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_archiveTblMousePressed

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed

String uid = archiveTbl.getValueAt(archiveTbl.getSelectedRow(), 0).toString();

try {
    dbConnector dbc = new dbConnector();
    TableModel tbl = archiveTbl.getModel();
    
    // Update the query to match the new database schema
    ResultSet rs = dbc.getData("SELECT * FROM user_table WHERE id = '" + uid + "'");

    if (rs.next()) {
        // Set the fields with the result set values
        u_id.setText(rs.getString("id"));  // Changed from "u_id" to "id"
        fullname2.setText(rs.getString("fname") + " " + rs.getString("lname"));  // Changed from "u_fname" and "u_lname" to "fname" and "lname"
        username.setText(rs.getString("uname"));  // Changed from "u_uname" to "uname"
        umail.setText(rs.getString("email"));  // Changed from "u_email" to "email"
        type.setText(rs.getString("type"));  // Changed from "u_type" to "type"

        String status = rs.getString("status");  // Changed from "u_status" to "status"
        stats.setText(status);  // Updated for consistency

        // If you need to handle the image column, you can retrieve it similarly
        String imagePath = rs.getString("image");
        // You can set the image or handle it as needed
    }

    Object[] options = {};
    JOptionPane.showOptionDialog(null, viewpanel, "",
        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
        null, options, null);

} catch(SQLException ex) {
    System.out.println("Error: " + ex.getMessage());
}

    }//GEN-LAST:event_viewActionPerformed

    private void unarchiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unarchiveActionPerformed
    
    int rowIndex = archiveTbl.getSelectedRow();
    if (rowIndex < 0) {
        JOptionPane.showMessageDialog(null, "Please select a user to unarchive.");
    } else {
        dbConnector connector = new dbConnector();
        TableModel tbl = archiveTbl.getModel();
        String userId = tbl.getValueAt(rowIndex, 0).toString();

            Object[] options = {};
            JOptionPane.showOptionDialog(null, confirmunarchive, "",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, null);
        
    } 
    
    }//GEN-LAST:event_unarchiveActionPerformed

    private void noBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noBTMouseEntered

    }//GEN-LAST:event_noBTMouseEntered

    private void noBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noBTMouseExited

    }//GEN-LAST:event_noBTMouseExited

    private void noBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noBTActionPerformed
        archivedusers au = new archivedusers();
        Window window = SwingUtilities.getWindowAncestor(confirmunarchive);
        window.dispose();
        au.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_noBTActionPerformed

    private void yesBT1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yesBT1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_yesBT1MouseEntered

    private void yesBT1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yesBT1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_yesBT1MouseExited

    private void yesBT1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yesBT1ActionPerformed
        
Session sess = Session.getInstance();
        
dbConnector dbc = new dbConnector();
        
int rowIndex = archiveTbl.getSelectedRow();
if (rowIndex < 0) {
    JOptionPane.showMessageDialog(null, "Please select a user to unarchive.");
} else {
    TableModel tbl = archiveTbl.getModel();
    
    // Update the table name and column names to reflect the new database schema
    String sqlUpdate = "UPDATE user_table SET status = ? WHERE id = ?";
    
    try (PreparedStatement pstUpdate = dbc.connect.prepareStatement(sqlUpdate)) {
        
        String userId = tbl.getValueAt(rowIndex, 0).toString();
        System.out.println("User ID to unarchive: " + userId); 

        pstUpdate.setString(1, "Pending");
        pstUpdate.setString(2, userId);

        int rowsAffected = pstUpdate.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected); 

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null, "User data unarchived successfully.");

            Window window = SwingUtilities.getWindowAncestor(confirmunarchive);
            if (window != null) {
                window.dispose();
            }

            displayData();  // Refresh the data after unarchiving
        } else {
            JOptionPane.showMessageDialog(null, "No records found to unarchive.");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "SQL Error: " + ex.getMessage());
    }
}


    }//GEN-LAST:event_yesBT1ActionPerformed

    private void back1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_back1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_back1MouseClicked

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

    // Check if the search text is empty
    if (searchText.isEmpty()) {
        // Display all archived users
        rs = dbc.getData(
            "SELECT CONCAT(fname, ' ', lname) AS Name, uname AS Username, status AS Status " +
            "FROM user_table " +
            "WHERE status = 'Archived'"
        );
    } else {
        // Search by name (first name or last name) for archived users
        String query = "SELECT CONCAT(fname, ' ', lname) AS Name, uname AS Username, status AS Status " +
        "FROM user_table " +
        "WHERE status = 'Archived' AND (fname LIKE ? OR lname LIKE ?)";
        PreparedStatement stmt = dbc.connect.prepareStatement(query);
        String searchPattern = "%" + searchText + "%";
        stmt.setString(1, searchPattern); // Match first name
        stmt.setString(2, searchPattern); // Match last name
        rs = stmt.executeQuery();
    }

    // Create the table model with the required columns
    DefaultTableModel model = new DefaultTableModel(new String[]{
        "Name", "Username", "Status"
    }, 0);

    // Populate the table with data
    while (rs.next()) {
        model.addRow(new Object[]{
            rs.getString("Name"),
            rs.getString("Username"),
            rs.getString("Status")
        });
    }

    // Set the model for the archive table
    archiveTbl.setModel(model);

    // Apply custom cell renderer to the Status column to color "Archived" entries
    archiveTbl.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (value != null && value.toString().equals("Archived")) {
                comp.setForeground(Color.RED); // Red for archived status
            } else {
                comp.setForeground(table.getForeground()); // Default color
            }
            return comp;
        }
    });

    // Refresh the table header
    JTableHeader th = archiveTbl.getTableHeader();
    th.repaint();

    // Close the result set
    rs.close();
} catch (SQLException ex) {
    System.out.println("Errors: " + ex.getMessage());
}

    }//GEN-LAST:event_searchBarKeyReleased

    private void cancleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancleActionPerformed
        users u = new users();
        u.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancleActionPerformed

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
            java.util.logging.Logger.getLogger(archivedusers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(archivedusers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(archivedusers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(archivedusers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new archivedusers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable archiveTbl;
    private javax.swing.JLabel back1;
    private javax.swing.JButton cancle;
    private javax.swing.JPanel confirmunarchive;
    private javax.swing.JLabel fullname2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label1;
    private javax.swing.JButton noBT;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JPopupMenu popUp;
    private javax.swing.JTextField searchBar;
    private javax.swing.JLabel stats;
    private javax.swing.JLabel title;
    private javax.swing.JLabel type;
    private javax.swing.JLabel u_id;
    private javax.swing.JLabel umail;
    private javax.swing.JMenuItem unarchive;
    private javax.swing.JLabel username;
    private javax.swing.JMenuItem view;
    public javax.swing.JPanel viewpanel;
    private javax.swing.JButton yesBT1;
    // End of variables declaration//GEN-END:variables
}
