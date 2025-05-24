/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import dao.UserDAO;
import model.User;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private JTextField privateCodeField;
    private JPasswordField passwordField;

public LoginFrame() {
    setTitle("Login - Arbre Généalogique Pro++");

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) (screenSize.width * 0.4);
    int height = (int) (screenSize.height * 0.3);
    setSize(width, height);
    setLocationRelativeTo(null);

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    initUI();
}

private void initUI() {
    // Fonts
    Font labelFont = new Font("SansSerif", Font.PLAIN, 22);
    Font fieldFont = new Font("SansSerif", Font.PLAIN, 16);

    // Main panel with vertical alignment
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

    // Row 1: Private Code
    JPanel codePanel = new JPanel(new BorderLayout(10, 0));
    JLabel codeLabel = new JLabel("Private Code:");
    codeLabel.setFont(labelFont);
    privateCodeField = new JTextField();
    privateCodeField.setFont(fieldFont);
    codePanel.add(codeLabel, BorderLayout.WEST);
    codePanel.add(privateCodeField, BorderLayout.CENTER);

    // Row 2: Password
    JPanel passPanel = new JPanel(new BorderLayout(10, 0));
    JLabel passLabel = new JLabel("Password:");
    passLabel.setFont(labelFont);
    passwordField = new JPasswordField();
    passwordField.setFont(fieldFont);
    passPanel.add(passLabel, BorderLayout.WEST);
    passPanel.add(passwordField, BorderLayout.CENTER);

    // Login Button
    JButton loginButton = new JButton("Login");
    loginButton.setFont(labelFont);
    loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    loginButton.setPreferredSize(new Dimension(150, 40));
    loginButton.addActionListener(e -> handleLogin());

    // Register link
    JLabel registerLabel = new JLabel("<html><a href='#'>Don't have an account? Register here</a></html>");
    registerLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
    registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    registerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    registerLabel.setForeground(Color.BLUE);
    registerLabel.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            new RegisterFrame().setVisible(true);
            LoginFrame.this.dispose();
        }
    });

    // Add spacing and components
    mainPanel.add(codePanel);
    mainPanel.add(Box.createVerticalStrut(20));
    mainPanel.add(passPanel);
    mainPanel.add(Box.createVerticalStrut(30));
    mainPanel.add(loginButton);
    mainPanel.add(Box.createVerticalStrut(20));
    mainPanel.add(registerLabel);

    setLayout(new BorderLayout());
    add(mainPanel, BorderLayout.CENTER);
}


private void handleLogin() {
        String code = privateCodeField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (code.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both private code and password.");
            return;
        }

        User user = UserDAO.loginUser(code, password);

        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login successful! Welcome, " + user.getFirstName() + ".");
            this.dispose();
            new ui.UserDashboard(user).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "❌ Invalid private code or password. Please try again.");
            passwordField.setText("");
        }
    }
}
