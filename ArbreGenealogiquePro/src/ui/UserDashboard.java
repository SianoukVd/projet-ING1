package ui;

import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserDashboard extends JFrame {
    private final User currentUser;

    public UserDashboard(User user) {
        this.currentUser = user;
        setTitle("User Dashboard");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.45);
        int height = (int) (screenSize.height * 0.55);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setLayout(new BorderLayout());

        JLabel header = new JLabel("Welcome to Your Dashboard", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 30));
        header.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));
        header.setForeground(new Color(33, 37, 41)); 
        mainPanel.add(header, BorderLayout.NORTH);

        // Panel 
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setLayout(new GridLayout(0, 1, 0, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 50, 50));

        Font buttonFont = new Font("Segoe UI Semibold", Font.PLAIN, 22);

        buttonPanel.add(createMaterialButton("Add Family Media", buttonFont, e -> openFrame(new AddMediaFrame(currentUser))));
        buttonPanel.add(createMaterialButton("View Family Media", buttonFont, e -> openFrame(new ViewMediaFrame(currentUser))));
        buttonPanel.add(createMaterialButton("Add Person to Family Tree", buttonFont, e -> openFrame(new AddPersonFrame(currentUser))));
        buttonPanel.add(createMaterialButton("View Family Tree", buttonFont, e -> openFrame(new ViewTreeFrame(currentUser))));
        buttonPanel.add(createMaterialButton("Logout", buttonFont, e -> {
            dispose();
            new LoginFrame().setVisible(true);
        }));

        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private JButton createMaterialButton(String text, Font font, java.awt.event.ActionListener action) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setFocusPainted(false);
        button.setBackground(new Color(25, 118, 210));  // Material Design
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.addActionListener(action);

        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(21, 101, 192), 1, true),
                BorderFactory.createEmptyBorder(15, 30, 15, 30)
        ));
        button.setBackground(new Color(25, 118, 210));
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(c.getBackground());
                g2.fillRoundRect(0, 0, c.getWidth(), c.getHeight(), 20, 20);
                super.paint(g2, c);
                g2.dispose();
            }
        });

        // hover 
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(13, 71, 161));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(25, 118, 210));
            }
        });

        return button;
    }

    private void openFrame(JFrame frame) {
        frame.setVisible(true);
        dispose();
    }
}