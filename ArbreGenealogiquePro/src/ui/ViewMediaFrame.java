package ui;

import dao.MediaDAO;
import model.Media;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.List;

public class ViewMediaFrame extends JFrame {
    private final User currentUser;

    public ViewMediaFrame(User user) {
        this.currentUser = user;
        setTitle("View Media");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (screenSize.width * 0.7);
        int height = (int) (screenSize.height * 0.6);
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();
    }

private void initUI() {
    JPanel mediaPanel = new JPanel();
    mediaPanel.setLayout(new BoxLayout(mediaPanel, BoxLayout.Y_AXIS));

    List<Media> mediaList = MediaDAO.getMediaByUser(currentUser.getId());

    if (mediaList.isEmpty()) {
        JLabel label = new JLabel("No media files uploaded.");
        mediaPanel.add(label);
    } else {
        for (Media m : mediaList) {
            File file = new File(m.getFilePath());
            if (file.exists() && isImageFile(file)) {
                ImageIcon icon = new ImageIcon(m.getFilePath());
                Image img = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(img);

                JLabel imageLabel = new JLabel(scaledIcon);
                imageLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                imageLabel.addMouseListener(new MouseAdapter() {
                    public void mouseClicked(MouseEvent e) {
                        showFullImage(m.getFilePath());
                    }
                });

                JLabel descriptionLabel = new JLabel("Description: " + m.getDescription());
                JLabel infoLabel = new JLabel("Uploaded: " + m.getUploadDate() + " | Visibility: " + m.getVisibility());

                JButton deleteButton = new JButton("Delete");
                deleteButton.addActionListener(e -> {
                    int confirm = JOptionPane.showConfirmDialog(this, 
                        "Are you sure you want to delete this media?", 
                        "Confirm Delete", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        boolean deletedFromDB = MediaDAO.deleteMedia(m.getId());
                        if (deletedFromDB) {
                            // Optionally delete the actual file from disk:
                            if (file.exists()) file.delete();
                            JOptionPane.showMessageDialog(this, "Media deleted successfully.");
                            // Refresh UI
                            getContentPane().removeAll();
                            initUI();
                            revalidate();
                            repaint();
                        } else {
                            JOptionPane.showMessageDialog(this, "Failed to delete media.");
                        }
                    }
                });

                JPanel singleMediaPanel = new JPanel();
                singleMediaPanel.setLayout(new BoxLayout(singleMediaPanel, BoxLayout.Y_AXIS));
                singleMediaPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                singleMediaPanel.add(imageLabel);
                singleMediaPanel.add(descriptionLabel);
                singleMediaPanel.add(infoLabel);
                singleMediaPanel.add(deleteButton);  // Add delete button here

                mediaPanel.add(singleMediaPanel);
            } else {
                JLabel unsupportedLabel = new JLabel("Unsupported or missing file: " + m.getFilePath());
                mediaPanel.add(unsupportedLabel);
            }
        }
    }

    JScrollPane scrollPane = new JScrollPane(mediaPanel);

    JButton backButton = new JButton("Back to Dashboard");
    backButton.addActionListener(e -> {
        new UserDashboard(currentUser).setVisible(true);
        dispose();
    });

    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    bottomPanel.add(backButton);

    setLayout(new BorderLayout());
    add(scrollPane, BorderLayout.CENTER);
    add(bottomPanel, BorderLayout.SOUTH);
}
    
    private boolean isImageFile(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".png") || name.endsWith(".jpg") || name.endsWith(".jpeg") || name.endsWith(".gif");
    }

    private void showFullImage(String filePath) {
        JFrame fullImageFrame = new JFrame("Image Viewer");
        fullImageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fullImageFrame.setSize(800, 600);
        fullImageFrame.setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon(filePath);
        JLabel fullImageLabel = new JLabel(icon);
        JScrollPane scrollPane = new JScrollPane(fullImageLabel);

        fullImageFrame.add(scrollPane);
        fullImageFrame.setVisible(true);
    }
}