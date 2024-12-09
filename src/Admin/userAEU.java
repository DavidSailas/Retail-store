
package Admin;


import config.PasswordHasher;
import config.dbConnector;
import config.Session;
import java.awt.Color;
import java.awt.Image;
import java.awt.Window;
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
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class userAEU extends javax.swing.JFrame {

   
    public userAEU() {
        initComponents();
    }

    Color navcolor =  new Color(204,204,204);
    Color hovercolor =  new Color(0,92,229);
    
   public String destination = "";
   File selectedFile;
   public String path;
   public String oldpath;
    
   public int FileExistenceChecker(String path){
        File file = new File(path);
        String fileName = file.getName();
        
        Path filePath = Paths.get("src/image", fileName);
        boolean fileExists = Files.exists(filePath);
        
        if (fileExists) {
            return 1;
        } else {
            return 0;
        }
    }

   public static int getHeightFromWidth(String imagePath, int desiredWidth) {
        try {
           
            File imageFile = new File(imagePath);
            BufferedImage image = ImageIO.read(imageFile);
            
            int originalWidth = image.getWidth();
            int originalHeight = image.getHeight();
            
            int newHeight = (int) ((double) desiredWidth / originalWidth * originalHeight);
            
            return newHeight;
        } catch (IOException ex) {
            System.out.println("No image found!");
        }
        
        return -1;
    }
   
   public  ImageIcon ResizeImage(String ImagePath, byte[] pic, JLabel label) {
    ImageIcon MyImage = null;
        if(ImagePath !=null){
            MyImage = new ImageIcon(ImagePath);
        }else{
            MyImage = new ImageIcon(pic);
        }
        
    int newHeight = getHeightFromWidth(ImagePath, label.getWidth());

    Image img = MyImage.getImage();
    Image newImg = img.getScaledInstance(label.getWidth(), newHeight, Image.SCALE_SMOOTH);
    ImageIcon image = new ImageIcon(newImg);
    return image;
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
    
    public static String email, username;
    
    public boolean dupCheck(){
        
        dbConnector connector = new dbConnector();
        
        try{
            
            String query = "SELECT * FROM user_table  WHERE uname = '" + u_uname.getText() + "' OR email = '" + u_email.getText() + "'";
            ResultSet resultSet = connector.getData(query);
            
            if(resultSet.next()){
             
                email = resultSet.getString("email");
                if(email.equals(u_email.getText())){
                    JOptionPane.showMessageDialog(null,"Email is Already Used!");
                    u_email.setText("");
                }
                
                username = resultSet.getString("uname");
                if(username.equals(u_uname.getText())){
                    JOptionPane.showMessageDialog(null,"Username is Already Used!");
                    u_uname.setText("");
                }
                
                return true;
                
            }else{
                return false;
            }
            
        }catch(SQLException ex){
            
            System.out.println(""+ex);
            return false;
        }
        
    }
    
public boolean upCheck() {
    dbConnector connector = new dbConnector();
    String uname = u_uname.getText();
    String email = u_email.getText();
    String uid = u_id.getText();

    String query = "SELECT email, uname FROM user_table WHERE (uname = ? OR email = ?) AND id != ?";
    try (PreparedStatement pst = connector.connect.prepareStatement(query)) {
        pst.setString(1, uname);
        pst.setString(2, email);
        pst.setString(3, uid);
        ResultSet resultSet = pst.executeQuery();

        if (resultSet.next()) {
            if (email.equals(resultSet.getString("email"))) {
                JOptionPane.showMessageDialog(null, "Email is Already Used!");
                u_email.setText("");
            }
            if (uname.equals(resultSet.getString("uname"))) {
                JOptionPane.showMessageDialog(null, "Username is Already Used!");
                u_uname.setText("");
            }
            return true;
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
    }
    return false;
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        confirmarchive = new javax.swing.JPanel();
        ACCOUNT_NAME = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        noBT = new javax.swing.JButton();
        yesBT1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        remove = new javax.swing.JButton();
        addProfile = new javax.swing.JButton();
        image = new javax.swing.JLabel();
        u_fname = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        u_lname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        u_email = new javax.swing.JTextField();
        u_uname = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        u_pass = new javax.swing.JTextField();
        u_stats = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        u_id = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        u_type = new javax.swing.JComboBox<>();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        cancle = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();

        confirmarchive.setBackground(new java.awt.Color(255, 255, 255));
        confirmarchive.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ACCOUNT_NAME.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        ACCOUNT_NAME.setForeground(new java.awt.Color(0, 0, 102));
        ACCOUNT_NAME.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ACCOUNT_NAME.setText("SAMPLE");
        confirmarchive.add(ACCOUNT_NAME, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 420, 30));

        jLabel21.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 102));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Are you sure you want to archive user?");
        confirmarchive.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 420, 20));

        noBT.setBackground(new java.awt.Color(0, 51, 184));
        noBT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
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
        confirmarchive.add(noBT, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 80, -1));

        yesBT1.setBackground(new java.awt.Color(0, 51, 184));
        yesBT1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
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
        confirmarchive.add(yesBT1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 80, -1));

        jPanel3.setBackground(new java.awt.Color(0, 92, 229));

        jLabel15.setBackground(new java.awt.Color(255, 255, 255));
        jLabel15.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText(" NOTICE ");
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

        confirmarchive.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 50));
        confirmarchive.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 420, 10));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(236, 236, 236));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(89, 196, 19));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel5.setMinimumSize(new java.awt.Dimension(500, 450));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        remove.setBackground(new java.awt.Color(255, 255, 255));
        remove.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        remove.setForeground(new java.awt.Color(89, 196, 19));
        remove.setText("REMOVE");
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });
        jPanel5.add(remove, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, 150, -1));

        addProfile.setBackground(new java.awt.Color(255, 255, 255));
        addProfile.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        addProfile.setForeground(new java.awt.Color(89, 196, 19));
        addProfile.setText("SELECT");
        addProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProfileActionPerformed(evt);
            }
        });
        jPanel5.add(addProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 370, 150, -1));

        image.setBackground(new java.awt.Color(255, 255, 255));
        image.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel5.add(image, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 310, 290));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 470, 450));

        u_fname.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        u_fname.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        u_fname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                u_fnameActionPerformed(evt);
            }
        });
        jPanel4.add(u_fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, 150, 30));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("First name:");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, -1, 20));

        u_lname.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        u_lname.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        u_lname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                u_lnameActionPerformed(evt);
            }
        });
        jPanel4.add(u_lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 150, 30));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Last name:");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, -1, 20));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("Email:");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 150, -1, 20));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 102, 102));
        jLabel6.setText("Username:");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, -1, 20));

        u_email.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        u_email.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        u_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                u_emailActionPerformed(evt);
            }
        });
        jPanel4.add(u_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, 150, 30));

        u_uname.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        u_uname.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        u_uname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                u_unameActionPerformed(evt);
            }
        });
        jPanel4.add(u_uname, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 150, 30));

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setText("Password:");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 210, -1, 20));

        u_pass.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        u_pass.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        u_pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                u_passActionPerformed(evt);
            }
        });
        jPanel4.add(u_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 230, 150, 30));

        u_stats.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        u_stats.setForeground(new java.awt.Color(0, 0, 102));
        u_stats.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Active", "Pending", "" }));
        u_stats.setSelectedIndex(-1);
        u_stats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                u_statsActionPerformed(evt);
            }
        });
        jPanel4.add(u_stats, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 290, 150, 30));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 102, 102));
        jLabel8.setText("User Type:");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, -1, 20));

        add.setBackground(new java.awt.Color(91, 164, 252));
        add.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("ADD");
        add.setPreferredSize(new java.awt.Dimension(60, 30));
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });
        jPanel4.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 100, -1));

        jLabel18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 102, 102));
        jLabel18.setText("User ID:");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, -1, 20));

        u_id.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        u_id.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        u_id.setEnabled(false);
        u_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                u_idActionPerformed(evt);
            }
        });
        jPanel4.add(u_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 150, 30));

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 102, 102));
        jLabel19.setText("User Status:");
        jPanel4.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, -1, 20));

        u_type.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        u_type.setForeground(new java.awt.Color(0, 0, 102));
        u_type.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "User", " " }));
        u_type.setSelectedIndex(-1);
        jPanel4.add(u_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 290, 150, 30));

        update.setBackground(new java.awt.Color(83, 215, 105));
        update.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        update.setForeground(new java.awt.Color(255, 255, 255));
        update.setText("UPDATE");
        update.setPreferredSize(new java.awt.Dimension(60, 30));
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        jPanel4.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, 100, -1));

        delete.setBackground(new java.awt.Color(252, 61, 57));
        delete.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        delete.setForeground(new java.awt.Color(255, 255, 255));
        delete.setText("ARCHIVE");
        delete.setPreferredSize(new java.awt.Dimension(60, 30));
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        jPanel4.add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, 100, -1));

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
        jPanel4.add(cancle, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 370, 100, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setText("_____________________");
        jPanel4.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, -1, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel22.setText("_____________________");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, -1, 30));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("_____________________");
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, -1, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setText("_____________________");
        jPanel4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, -1, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("_____________________");
        jPanel4.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 180, -1, 30));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("_____________________");
        jPanel4.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, -1, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 450));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void u_fnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_u_fnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_u_fnameActionPerformed

    private void u_lnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_u_lnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_u_lnameActionPerformed

    private void u_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_u_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_u_emailActionPerformed

    private void u_unameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_u_unameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_u_unameActionPerformed

    private void u_passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_u_passActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_u_passActionPerformed

    private void u_statsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_u_statsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_u_statsActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        
Session sess = Session.getInstance();
int adminID = sess.getUid();

PasswordHasher pH = new PasswordHasher();
String password = pH.hashPassword(u_pass.getText());

// Check if required fields are filled
if (u_fname.getText().isEmpty() || u_lname.getText().isEmpty() || u_email.getText().isEmpty()
        || u_uname.getText().isEmpty() || u_type.getSelectedIndex() == -1 || u_stats.getSelectedIndex() == -1) {
    JOptionPane.showMessageDialog(null, "All fields are required!");
} else if (u_pass.getText().length() < 8) {
    JOptionPane.showMessageDialog(null, "Password should be at least 8 characters!");
    u_pass.setText("");
} else if (dupCheck()) {
    System.out.println("Duplicate Exists!");
} else {
    dbConnector dbc = new dbConnector();

    // Determine the image path: uploaded file or default image
    String imageDestination = (selectedFile != null) ? destination : "u_default";

    // SQL Insert
    if (dbc.insertData("INSERT INTO user_table (fname, lname, email, uname, pass, type, status, image, contact) " +
            "VALUES ('" + u_fname.getText() + "','" + u_lname.getText() + "','" + u_email.getText() + "','" +
            u_uname.getText() + "','" + password + "','" + u_type.getSelectedItem() + "','" + 
            u_stats.getSelectedItem() + "','" + imageDestination + "', '')")) {
        try {
            ResultSet resultSet = dbc.getData("SELECT id FROM user_table WHERE uname = '" + u_uname.getText() + "'");
            if (resultSet.next()) {
                String newUserId = resultSet.getString("id");


                // Copy the uploaded image file if present
                if (selectedFile != null) {
                    try {
                        Files.copy(selectedFile.toPath(), new File(destination).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException ex) {
                        System.out.println("Insert Image Error: " + ex);
                    }
                }

                JOptionPane.showMessageDialog(null, "User Registered Successfully!");
                users u = new users();
                u.setVisible(true);
                this.dispose();
            }
        } catch (SQLException ex) {
            Logger.getLogger(userAEU.class.getName()).log(Level.SEVERE, null, ex);
        }
    } else {
        JOptionPane.showMessageDialog(null, "Connection Error!");
    }
}
      
    }//GEN-LAST:event_addActionPerformed

    private void u_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_u_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_u_idActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed

        Session sess = Session.getInstance();
        
        int adminID = sess.getUid();
        
          if(u_fname.getText().isEmpty() || u_lname.getText().isEmpty() || u_email.getText().isEmpty() 
                || u_uname.getText().isEmpty() || u_type.getSelectedIndex() == -1 || u_stats.getSelectedIndex() == -1)
        {
            
            JOptionPane.showMessageDialog(null,"All fields are required!");
            
        }
            else if(upCheck()){
            
            System.out.println("Duplicate Exist!");
            
        }
        else{
             
        dbConnector dbc = new dbConnector();
        
        dbc.updateData("UPDATE user_product SET fname = '" + u_fname.getText() + "', lname = '" + u_lname.getText()
                + "', email = '" + u_email.getText() + "', uname = '" + u_uname.getText()
                + "', type = '" + u_type.getSelectedItem()
                + "', status = '" + u_stats.getSelectedItem() + "',image = '" + destination + "' WHERE id = '" + u_id.getText() + "'");
        
        
        if(destination.isEmpty()){
            File existingFile = new File(oldpath);
            if(existingFile.exists()){
                existingFile.delete();
            }
        }else{
        if (oldpath != null && !oldpath.equals(path)) {
            imageUpdater(oldpath, path);
        }
                
        }
        

        
            users u = new users();
            u.setVisible(true);
            this.dispose();
        
        }
    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
    
    ACCOUNT_NAME.setText(u_fname.getText() + " " + u_lname.getText());
        Object[] options = {};
    
       JOptionPane.showOptionDialog(null, confirmarchive, "",
       JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, options, null);
      
    }//GEN-LAST:event_deleteActionPerformed

    private void cancleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancleActionPerformed
        users u = new users();
        u.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_cancleActionPerformed

    private void noBTMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noBTMouseEntered

    }//GEN-LAST:event_noBTMouseEntered

    private void noBTMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noBTMouseExited

    }//GEN-LAST:event_noBTMouseExited

    private void noBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noBTActionPerformed
        users u = new users();
        u.setVisible(true);
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
        
        
        int adminID = sess.getUid();
        
    dbConnector dbc = new dbConnector();

  
    String sql = "UPDATE user_table SET status = ? WHERE id = ?";

    try (PreparedStatement pst = dbc.connect.prepareStatement(sql)) {
        pst.setString(1, "Archived"); 
        pst.setString(2, u_id.getText()); 
        int rowsAffected = pst.executeUpdate();

        if (rowsAffected > 0) {
            Window window = SwingUtilities.getWindowAncestor(confirmarchive);
            window.dispose();
            JOptionPane.showMessageDialog(null, "User data archived.");
            
            
            users u = new users();
            u.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "No records found to archive.");
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "SQL Error: " + ex.getMessage());
    }
    
    }//GEN-LAST:event_yesBT1ActionPerformed

    private void addProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProfileActionPerformed

        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            try {
                selectedFile = fileChooser.getSelectedFile();
                destination = "src/image/" + selectedFile.getName();
                path  = selectedFile.getAbsolutePath();

                if(FileExistenceChecker(path) == 1){
                    JOptionPane.showMessageDialog(null, "File Already Exist, Rename or Choose another!");
                    destination = "";
                    path="";
                }else{
                    image.setIcon(ResizeImage(path, null, image));
                    addProfile.setText(" Edit Profile");
                    remove.setEnabled(true);
                }
            } catch (Exception ex) {
                System.out.println("File Error!");
            }
        }
    }//GEN-LAST:event_addProfileActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed

        remove.setEnabled(false);
        addProfile.setText(" Add user profile");
        image.setIcon(null);
        destination = "";
        path = "";
    }//GEN-LAST:event_removeActionPerformed

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
            java.util.logging.Logger.getLogger(userAEU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(userAEU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(userAEU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(userAEU.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new userAEU().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel ACCOUNT_NAME;
    public javax.swing.JButton add;
    public javax.swing.JButton addProfile;
    private javax.swing.JButton cancle;
    private javax.swing.JPanel confirmarchive;
    private javax.swing.JButton delete;
    public javax.swing.JLabel image;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JButton noBT;
    public javax.swing.JButton remove;
    public javax.swing.JTextField u_email;
    public javax.swing.JTextField u_fname;
    public javax.swing.JTextField u_id;
    public javax.swing.JTextField u_lname;
    public javax.swing.JTextField u_pass;
    public javax.swing.JComboBox<String> u_stats;
    public javax.swing.JComboBox<String> u_type;
    public javax.swing.JTextField u_uname;
    public javax.swing.JButton update;
    private javax.swing.JButton yesBT1;
    // End of variables declaration//GEN-END:variables
}
