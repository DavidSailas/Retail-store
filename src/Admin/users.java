/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import config.PanelPrinter;
import config.dbConnector;
import form.Loginfrom;
import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author PC
 */
public class users extends javax.swing.JFrame {

    /**
     * Creates new form users
     */
    public users() {
        initComponents();
        displayData();
    }
    
   public String destination = "";
   File selectedFile;
   public String path;
   public String oldpath;
    
   public int FileExistenceChecker(String path){
        File file = new File(path);
        String fileName = file.getName();
        
        Path filePath = Paths.get("src/u_images", fileName);
        boolean fileExists = Files.exists(filePath);
        
        if (fileExists) {
            return 1;
        } else {
            return 0;
        }
    }

   public ImageIcon resizeImage(ImageIcon originalIcon, int targetWidth, int targetHeight) {
        Image originalImage = originalIcon.getImage();


        int newHeight = getHeightFromWidth(originalImage, targetWidth);

        BufferedImage resizedImage = new BufferedImage(targetWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        resizedImage.createGraphics().drawImage(originalImage, 0, 0, targetWidth, newHeight, null);
        return new ImageIcon(resizedImage);
}

    public int getHeightFromWidth(Image image, int desiredWidth) {
        int originalWidth = image.getWidth(null);
        int originalHeight = image.getHeight(null);

        return (int) ((double) desiredWidth / originalWidth * originalHeight);
    }
    
   public void imageUpdater(String existingFilePath, String newFilePath){
        File existingFile = new File(existingFilePath);
        if (existingFile.exists()) {
            String parentDirectory = existingFile.getParent();
            File newFile = new File(newFilePath);
            String newFileName = newFile.getName();
            File updatedFile = new File(parentDirectory, newFileName);
            existingFile.delete();
            try {
                Files.copy(newFile.toPath(), updatedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image updated successfully.");
            } catch (IOException e) {
                System.out.println("Error occurred while updating the image: "+e);
            }
        } else {
            try{
                Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }catch(IOException e){
                System.out.println("Error on update!");
            }
        }
   }


public void displayData() {
    try {
        dbConnector connector = new dbConnector();

        // Fetch only the required columns from the database
        ResultSet rs = connector.getData(
            "SELECT id AS Id, CONCAT(fname, ' ', lname) AS Name, uname AS Username, status AS Status, type AS Type " +
            "FROM user_table " +
            "WHERE status <> 'Archived'");

        DefaultTableModel model = new DefaultTableModel(new String[]{
            "Id", "Name", "Username", "Status", "Type"
        }, 0); // Initialize table model with column names

        // Populate the table with data
        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("Id"),           
                rs.getString("Name"),         
                rs.getString("Username"),     
                rs.getString("Status"),       
                rs.getString("Type")          
            });
        }

        userTbl.setModel(model);

        // Hide the "Id" column
        hideColumn(userTbl, 0); // Index 0 corresponds to the "Id" column

        // Set custom rendering for the "Status" column
        userTbl.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                String status = (String) table.getValueAt(row, 3); // Status is at index 3
                if (status != null) {
                    if (status.equals("Active")) {
                        setForeground(Color.GREEN);
                    } else if (status.equals("Pending")) {
                        setForeground(Color.ORANGE);
                    } else {
                        setForeground(table.getForeground());
                    }
                }
                return c;
            }
        });

        // Refresh the table headers
        JTableHeader th = userTbl.getTableHeader();
        th.repaint();

        rs.close();
    } catch (SQLException ex) {
        System.out.println("Error: " + ex.getMessage());
    }
}

// Method to hide specific columns
private void hideColumn(JTable table, int columnIndex) {
    TableColumn column = table.getColumnModel().getColumn(columnIndex);
    column.setMinWidth(0);
    column.setMaxWidth(0);
    column.setPreferredWidth(0);
    column.setResizable(false);
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popUp = new javax.swing.JPopupMenu();
        view = new javax.swing.JMenuItem();
        edit = new javax.swing.JMenuItem();
        viewpanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        u_id = new javax.swing.JLabel();
        fullname = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        umail = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        type = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        stats = new javax.swing.JLabel();
        print = new javax.swing.JButton();
        image = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        contact = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        userTbl = new javax.swing.JTable();
        searchBar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
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

        view.setText("View");
        view.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewActionPerformed(evt);
            }
        });
        popUp.add(view);

        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });
        popUp.add(edit);

        viewpanel.setBackground(new java.awt.Color(255, 255, 255));
        viewpanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Id:");
        viewpanel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 82, -1, -1));

        u_id.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        u_id.setText("sample");
        viewpanel.add(u_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(102, 82, -1, -1));

        fullname.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        fullname.setText("Fullname");
        viewpanel.add(fullname, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 284, -1, -1));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Username:");
        viewpanel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, -1, -1));

        username.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        username.setText("sample");
        viewpanel.add(username, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 130, -1, -1));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Email:");
        viewpanel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, -1, -1));

        umail.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        umail.setText("sample");
        viewpanel.add(umail, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("Type:");
        viewpanel.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 110, -1, -1));

        type.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        type.setText("sample");
        viewpanel.add(type, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, -1, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 102, 102));
        jLabel10.setText("Status:");
        viewpanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, -1, -1));

        stats.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        stats.setText("sample");
        viewpanel.add(stats, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, -1, -1));

        print.setBackground(new java.awt.Color(89, 196, 19));
        print.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        print.setForeground(new java.awt.Color(255, 255, 255));
        print.setText(" EXPORT");
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });
        viewpanel.add(print, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 331, 150, 30));

        image.setBackground(new java.awt.Color(255, 255, 255));
        image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        viewpanel.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 110, 156, 156));

        jPanel4.setBackground(new java.awt.Color(89, 196, 19));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("  VIEW DETAILS");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
        );

        viewpanel.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, -1));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 102, 102));
        jLabel12.setText("Contact Number:");
        viewpanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, -1, -1));

        contact.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        contact.setText("sample");
        viewpanel.add(contact, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 240, -1, -1));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        userTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        userTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                userTblMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(userTbl);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 133, 730, 340));

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
        jPanel2.add(searchBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 210, -1));

        jButton1.setText("Archived");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 100, -1, -1));

        jButton2.setText("Add User");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, -1, -1));

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
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/users (1).png"))); // NOI18N
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
        // Display all non-archived users
        rs = dbc.getData(
            "SELECT CONCAT(fname, ' ', lname) AS Name, uname AS Username, status AS Status, type AS Type " +
            "FROM user_table " +
            "WHERE status <> 'Archived'"
        );
    } else {
        // Search by name, last name, or username
        String query = "SELECT CONCAT(fname, ' ', lname) AS Name, uname AS Username, status AS Status, type AS Type " +
                       "FROM user_table " +
                       "WHERE status <> 'Archived' AND (fname LIKE ? OR lname LIKE ? OR uname LIKE ?)";
        PreparedStatement stmt = dbc.connect.prepareStatement(query);
        String searchPattern = "%" + searchText + "%";
        stmt.setString(1, searchPattern); // Match first name
        stmt.setString(2, searchPattern); // Match last name
        stmt.setString(3, searchPattern); // Match username
        rs = stmt.executeQuery();
    }

    // Create the table model with the required columns
    DefaultTableModel model = new DefaultTableModel(new String[]{
        "Name", "Username", "Status", "Type"
    }, 0);

    // Populate the table with data
    while (rs.next()) {
        model.addRow(new Object[]{
            rs.getString("Name"),
            rs.getString("Username"),
            rs.getString("Status"),
            rs.getString("Type")
        });
    }

    // Set the model for the user table
    userTbl.setModel(model);

    // Apply custom cell renderer to the Status column to change color
    userTbl.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (value != null) {
                String status = value.toString();
                if (status.equals("Active")) {
                    comp.setForeground(Color.GREEN); // Green for active status
                } else if (status.equals("Pending")) {
                    comp.setForeground(Color.ORANGE); // Orange for pending status
                } else {
                    comp.setForeground(Color.BLACK); // Default color for other statuses
                }
            }
            return comp;
        }
    });

    // Refresh the table header
    JTableHeader th = userTbl.getTableHeader();
    th.repaint();

    // Close the result set
    rs.close();
} catch (SQLException ex) {
    System.out.println("Errors: " + ex.getMessage());
}
        
    }//GEN-LAST:event_searchBarKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        archivedusers au = new archivedusers();
        au.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        userAEU ur = new userAEU();
        ur.update.setEnabled(false);
        ur.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void viewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewActionPerformed

        String uid = userTbl.getValueAt(userTbl.getSelectedRow(), 0).toString();

        try {
            dbConnector dbc = new dbConnector();
            TableModel tbl = userTbl.getModel();
            ResultSet rs = dbc.getData("SELECT * FROM user_table WHERE id = '" + uid + "'");

            if (rs.next()) {

                String imagePath = rs.getString("image");

                ImageIcon originalIcon = new ImageIcon(imagePath);

                ImageIcon resizedIcon = resizeImage(originalIcon, 170, 170);

                image.setIcon(resizedIcon);

                u_id.setText(rs.getString("id"));
                fullname.setText(rs.getString("fname") + " " + rs.getString("lname"));
                username.setText(rs.getString("uname"));
                umail.setText(rs.getString("email"));
                type.setText(rs.getString("type"));
                contact.setText(rs.getString("contact"));

                String as = rs.getString("status");
                if(as.equals("Active")){

                    stats.setText(""+as);
                }else{

                    stats.setText(""+as);
                }

            }

            Object[] options = {};
            JOptionPane.showOptionDialog(null, viewpanel, "",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
                null, options, null);

        }catch(SQLException ex){
            System.out.println(""+ex);
        }
    }//GEN-LAST:event_viewActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed

        int rowIndex = userTbl.getSelectedRow();

        if(rowIndex < 0){
            JOptionPane.showMessageDialog(null, "Please select an Item!");
        }else{
            try{
                dbConnector connector = new dbConnector();
                TableModel tbl = userTbl.getModel();
                ResultSet resultSet = connector.getData("SELECT * FROM user_table WHERE id = '"+tbl.getValueAt(rowIndex, 0)+"'");
                if(resultSet.next()){
                    userAEU ur = new userAEU();
                    ur.u_id.setText(""+resultSet.getInt("id"));
                    ur.u_fname.setText(""+resultSet.getString("fname"));
                    ur.u_lname.setText(""+resultSet.getString("lname"));
                    ur.u_email.setText(""+resultSet.getString("email"));
                    ur.u_uname.setText(""+resultSet.getString("uname"));
                    ur.u_pass.setText("");
                    ur.u_pass.setEnabled(false);
                    ur.u_type.setSelectedItem(""+resultSet.getString("type"));
                    ur.u_stats.setSelectedItem(""+resultSet.getString("status"));
                    ur.add.setEnabled(false);
                    ur.update.setEnabled(true);
                    ur.image.setIcon(ur.ResizeImage(resultSet.getString("image"), null, ur.image));
                    ur.oldpath = resultSet.getString("image");
                    ur.path = resultSet.getString("image");
                    ur.destination = resultSet.getString("image");
                    ur.setVisible(true);
                    this.dispose();

                    if(resultSet.getString("image").isEmpty()){
                        ur.addProfile.setText("Add user profile");
                    }
                }
            }catch(SQLException ex){
                System.out.println(""+ex);
            }
        }
    }//GEN-LAST:event_editActionPerformed

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed

        JPanel myPanel = new JPanel();

        try {
            dbConnector dbc = new dbConnector();
            ResultSet rs = dbc.getData("SELECT * FROM user_table WHERE id = '" + u_id.getText() + "'");

            if (rs.next()) {
                PrintUserDets pud = new PrintUserDets();
                String imagePath = rs.getString("image");

                ImageIcon originalIcon = new ImageIcon(imagePath);

                ImageIcon resizedIcon = resizeImage(originalIcon, 170, 170);

                pud.image.setIcon(resizedIcon);

                pud.uid.setText(rs.getString("id"));
                pud.fullname.setText(rs.getString("fname") + " " + rs.getString("lname"));
                pud.username.setText(rs.getString("uname"));
                pud.umail.setText(rs.getString("email"));
                pud.type.setText(rs.getString("type"));

                String as = rs.getString("status");

                stats.setText(""+as);

                PanelPrinter pPrint = new PanelPrinter(pud.page);
                pPrint.printPanel();
            }

        }catch(SQLException ex){
            System.out.println(""+ex);
        }
    }//GEN-LAST:event_printActionPerformed

    private void userTblMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userTblMousePressed
                        if (SwingUtilities.isRightMouseButton(evt)) {
            popUp.show(userTbl, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_userTblMousePressed

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
            java.util.logging.Logger.getLogger(users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(users.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new users().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel contact;
    private javax.swing.JMenuItem edit;
    private javax.swing.JLabel fullname;
    public javax.swing.JLabel image;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panel;
    private javax.swing.JPanel panel1;
    private javax.swing.JPanel panel2;
    private javax.swing.JPanel panel3;
    private javax.swing.JPopupMenu popUp;
    private javax.swing.JButton print;
    private javax.swing.JTextField searchBar;
    private javax.swing.JLabel stats;
    private javax.swing.JLabel title;
    private javax.swing.JLabel type;
    private javax.swing.JLabel u_id;
    private javax.swing.JLabel umail;
    private javax.swing.JTable userTbl;
    private javax.swing.JLabel username;
    private javax.swing.JMenuItem view;
    private javax.swing.JPanel viewpanel;
    // End of variables declaration//GEN-END:variables
}
