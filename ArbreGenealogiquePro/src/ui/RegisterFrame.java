/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import javax.swing.JOptionPane;
import ui.LoginFrame;


/**
 *
 * @author vincentdelb
 */
public class RegisterFrame extends javax.swing.JFrame {

   public RegisterFrame() {
        initComponents();
        setLocationRelativeTo(null);
        setResizable(true);
    }

    @SuppressWarnings("unchecked")
   
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ssnField = new javax.swing.JTextField();
        firstNameField = new javax.swing.JTextField();
        registerButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        nationalityField = new javax.swing.JTextField();
        privateCodeField = new javax.swing.JTextField();
        publicCodeField = new javax.swing.JTextField();
        passwordField = new javax.swing.JPasswordField();
        birthDateField = new javax.swing.JTextField();
        lastNameField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(null);

        jLabel7.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 24)); // NOI18N
        jLabel7.setText("Nationality:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(556, 144, 132, 37);

        jLabel6.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 24)); // NOI18N
        jLabel6.setText("Last Name:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(53, 276, 129, 37);

        jLabel2.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 24)); // NOI18N
        jLabel2.setText("First Name:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(58, 211, 133, 37);
        getContentPane().add(ssnField);
        ssnField.setBounds(209, 150, 315, 23);

        firstNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstNameFieldActionPerformed(evt);
            }
        });
        getContentPane().add(firstNameField);
        firstNameField.setBounds(209, 211, 315, 23);

        registerButton.setBackground(new java.awt.Color(0, 0, 0));
        registerButton.setFont(new java.awt.Font("American Typewriter", 1, 22)); // NOI18N
        registerButton.setForeground(new java.awt.Color(255, 255, 255));
        registerButton.setText("Register");
        registerButton.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });
        getContentPane().add(registerButton);
        registerButton.setBounds(339, 395, 446, 49);

        jLabel5.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 24)); // NOI18N
        jLabel5.setText("SSN:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(58, 144, 55, 37);

        jPanel2.setBackground(new java.awt.Color(0, 102, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setForeground(new java.awt.Color(0, 51, 255));

        jLabel1.setFont(new java.awt.Font("PT Serif", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Register New User");

        jButton1.setBackground(new java.awt.Color(0, 102, 204));
        jButton1.setFont(new java.awt.Font("American Typewriter", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logout.png"))); // NOI18N
        jButton1.setText("Long Out");
        jButton1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 191, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(360, 360, 360))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 1089, 91);

        jLabel8.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 24)); // NOI18N
        jLabel8.setText("Public Code:");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(556, 211, 145, 37);

        jLabel9.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 24)); // NOI18N
        jLabel9.setText("Birth Date:");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(58, 340, 124, 37);

        jLabel10.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 24)); // NOI18N
        jLabel10.setText("Private Code:");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(556, 276, 154, 37);

        jLabel11.setFont(new java.awt.Font("Hiragino Mincho ProN", 0, 24)); // NOI18N
        jLabel11.setText("Password:");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(556, 340, 116, 37);
        getContentPane().add(nationalityField);
        nationalityField.setBounds(728, 150, 315, 23);
        getContentPane().add(privateCodeField);
        privateCodeField.setBounds(728, 282, 315, 23);
        getContentPane().add(publicCodeField);
        publicCodeField.setBounds(728, 217, 315, 23);
        getContentPane().add(passwordField);
        passwordField.setBounds(728, 340, 315, 23);

        birthDateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                birthDateFieldActionPerformed(evt);
            }
        });
        getContentPane().add(birthDateField);
        birthDateField.setBounds(209, 346, 315, 23);

        lastNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastNameFieldActionPerformed(evt);
            }
        });
        getContentPane().add(lastNameField);
        lastNameField.setBounds(209, 282, 315, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void firstNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_firstNameFieldActionPerformed

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
       String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String birthDate = birthDateField.getText().trim();
        String nationality = nationalityField.getText().trim();
        String ssn = ssnField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();
        String publicCode = publicCodeField.getText().trim();
        String privateCode = privateCodeField.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || birthDate.isEmpty() ||
            nationality.isEmpty() || ssn.isEmpty() || password.isEmpty() ||
            publicCode.isEmpty() || privateCode.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        model.User user = new model.User(
            ssn,
            firstName,
            lastName,
            birthDate,
            nationality,
            password,
            publicCode,
            privateCode
        );

        boolean success = dao.UserDAO.registerUser(user);

        if (success) {
            JOptionPane.showMessageDialog(this, "✅ Registration successful!");
            this.dispose();
            new LoginFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "❌ Registration failed. SSN or codes might already exist.");
        }
    }//GEN-LAST:event_registerButtonActionPerformed

    private void birthDateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_birthDateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_birthDateFieldActionPerformed

    private void lastNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lastNameFieldActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField birthDateField;
    private javax.swing.JTextField firstNameField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField lastNameField;
    private javax.swing.JTextField nationalityField;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JTextField privateCodeField;
    private javax.swing.JTextField publicCodeField;
    private javax.swing.JButton registerButton;
    private javax.swing.JTextField ssnField;
    // End of variables declaration//GEN-END:variables
}
