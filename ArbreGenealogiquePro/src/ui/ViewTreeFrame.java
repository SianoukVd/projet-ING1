package ui;

import dao.PersonDAO;
import model.Person;
import model.User;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ViewTreeFrame extends JFrame {

    private final User currentUser;
    private JTree familyTree;
    private DefaultTreeModel treeModel;
    private JTextArea detailsArea;
    private Map<Integer, Person> personMap;

    public ViewTreeFrame(User user) {
        this.currentUser = user;
        setTitle("Family Tree Viewer");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        List<Person> persons = PersonDAO.getPersonsByUser(currentUser.getId());
        personMap = new HashMap<>();
        for (Person p : persons) {
            personMap.put(p.getId(), p);
        }

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(currentUser.getFullName());
        for (Person p : persons) {
            String label = p.getRelation() + ": " + p.getName();
            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(label);
            rootNode.add(childNode);
        }

        treeModel = new DefaultTreeModel(rootNode);
        familyTree = new JTree(treeModel);
        familyTree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        detailsArea.setBorder(BorderFactory.createTitledBorder("Person Details"));

        familyTree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) familyTree.getLastSelectedPathComponent();
            if (node == null) {
                detailsArea.setText("");
                return;
            }
            String nodeName = node.getUserObject().toString();

            if (node.isRoot()) {
                detailsArea.setText("User: " + currentUser.getFullName());
                return;
            }

            String[] parts = nodeName.split(": ", 2);
            if (parts.length < 2) {
                detailsArea.setText("No details available.");
                return;
            }
            String relation = parts[0];
            String name = parts[1];

            Person selectedPerson = null;
            for (Person p : personMap.values()) {
                if (p.getName().equals(name) && p.getRelation().equalsIgnoreCase(relation)) {
                    selectedPerson = p;
                    break;
                }
            }
            if (selectedPerson != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Name: ").append(selectedPerson.getName()).append("\n");
                sb.append("Relation: ").append(selectedPerson.getRelation()).append("\n");
                sb.append("Birth Date: ").append(selectedPerson.getBirthDate()).append("\n");
                sb.append("Visibility: ").append(selectedPerson.getVisibility()).append("\n");
                sb.append("Registered User: ").append(selectedPerson.getRegisteredUserId() != null ? "Yes" : "No").append("\n");
                detailsArea.setText(sb.toString());
            } else {
                detailsArea.setText("No details found.");
            }
        });

        // Buttons panel
        JPanel buttonsPanel = new JPanel();
        JButton editButton = new JButton("Edit Selected");
        JButton deleteButton = new JButton("Delete Selected");
        JButton refreshButton = new JButton("Refresh Tree");
        JButton backButton = new JButton("Go to Back");

        editButton.addActionListener(e -> editSelectedPerson());
        deleteButton.addActionListener(e -> deleteSelectedPerson());
        refreshButton.addActionListener(e -> refreshTree());
        backButton.addActionListener(e -> {
            new UserDashboard(currentUser).setVisible(true);
            dispose();
        });

        buttonsPanel.add(editButton);
        buttonsPanel.add(deleteButton);
        buttonsPanel.add(refreshButton);
        buttonsPanel.add(backButton);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                new JScrollPane(familyTree), new JScrollPane(detailsArea));
        splitPane.setDividerLocation(300);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(splitPane, BorderLayout.CENTER);
        getContentPane().add(buttonsPanel, BorderLayout.SOUTH);

        refreshTree();
    }

    private void refreshTree() {
        List<Person> persons = PersonDAO.getPersonsByUser(currentUser.getId());
        personMap.clear();
        for (Person p : persons) {
            personMap.put(p.getId(), p);
        }

        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(currentUser.getFullName());
        for (Person p : persons) {
            String label = p.getRelation() + ": " + p.getName();
            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(label);
            rootNode.add(childNode);
        }

        treeModel.setRoot(rootNode);
        treeModel.reload();
        detailsArea.setText("");
    }

    private Person getSelectedPerson() {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) familyTree.getLastSelectedPathComponent();
        if (node == null || node.isRoot()) {
            JOptionPane.showMessageDialog(this, "Please select a family member.");
            return null;
        }

        String nodeName = node.getUserObject().toString();
        String[] parts = nodeName.split(": ", 2);
        if (parts.length < 2) {
            JOptionPane.showMessageDialog(this, "Invalid selection.");
            return null;
        }
        String relation = parts[0];
        String name = parts[1];

        for (Person p : personMap.values()) {
            if (p.getName().equals(name) && p.getRelation().equalsIgnoreCase(relation)) {
                return p;
            }
        }
        JOptionPane.showMessageDialog(this, "Person not found.");
        return null;
    }

    private void editSelectedPerson() {
        Person person = getSelectedPerson();
        if (person == null) return;

        JTextField nameField = new JTextField(person.getName());
        JTextField birthField = new JTextField(person.getBirthDate());
        JTextField relationField = new JTextField(person.getRelation());
        JComboBox<String> visibilityBox = new JComboBox<>(new String[]{"private", "public", "protected"});
        visibilityBox.setSelectedItem(person.getVisibility());

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Birth Date:"));
        panel.add(birthField);
        panel.add(new JLabel("Relation:"));
        panel.add(relationField);
        panel.add(new JLabel("Visibility:"));
        panel.add(visibilityBox);

        int result = JOptionPane.showConfirmDialog(this, panel, "Edit Person", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            person.setName(nameField.getText().trim());
            person.setBirthDate(birthField.getText().trim());
            person.setRelation(relationField.getText().trim());
            person.setVisibility(visibilityBox.getSelectedItem().toString());

            boolean updated = PersonDAO.updatePerson(person);
            if (updated) {
                JOptionPane.showMessageDialog(this, "Person updated.");
                refreshTree();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update.");
            }
        }
    }

    private void deleteSelectedPerson() {
        Person person = getSelectedPerson();
        if (person == null) return;

        if (person.getRegisteredUserId() != null) {
            JOptionPane.showMessageDialog(this, "Cannot delete a registered person without their approval.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete " + person.getName() + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            boolean deleted = PersonDAO.deletePerson(person.getId());
            if (deleted) {
                JOptionPane.showMessageDialog(this, "Person deleted.");
                refreshTree();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete.");
            }
        }
    }
}