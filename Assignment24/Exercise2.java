/**
 * Assignment 24 - Exercise 2
 * Book Issue Tracking System GUI using Java Swing connected to a database.
 * Stores book ID, student name, issue date, return date, and allows record management.
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Exercise2 extends JFrame implements ActionListener {

    private static final String DB_URL = "jdbc:h2:~/bookissuedb;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    private JTextField txtIssueId, txtBookId, txtStudentName, txtIssueDate, txtReturnDate;
    private JButton btnIssue, btnView, btnReturn, btnDelete, btnClear;
    private JTable issueTable;
    private DefaultTableModel tableModel;
    private Connection conn;

    public Exercise2() {
        setTitle("Book Issue Tracking System");
        setSize(800, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel title = new JLabel("BOOK ISSUE TRACKING SYSTEM", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(140, 70, 0));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Issue Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Issue ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        txtIssueId = new JTextField(15);
        formPanel.add(txtIssueId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Book ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        txtBookId = new JTextField(15);
        formPanel.add(txtBookId, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Student Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        txtStudentName = new JTextField(15);
        formPanel.add(txtStudentName, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Issue Date:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        txtIssueDate = new JTextField(15);
        txtIssueDate.setText(java.time.LocalDate.now().toString());
        formPanel.add(txtIssueDate, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Return Date:"), gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        txtReturnDate = new JTextField(15);
        formPanel.add(txtReturnDate, gbc);

        // Buttons
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnIssue = new JButton("Issue Book");
        btnIssue.addActionListener(this);
        btnView = new JButton("View All");
        btnView.addActionListener(this);
        btnReturn = new JButton("Mark Returned");
        btnReturn.addActionListener(this);
        btnDelete = new JButton("Delete Record");
        btnDelete.addActionListener(this);
        btnClear = new JButton("Clear");
        btnClear.addActionListener(this);

        btnPanel.add(btnIssue);
        btnPanel.add(btnView);
        btnPanel.add(btnReturn);
        btnPanel.add(btnDelete);
        btnPanel.add(btnClear);
        formPanel.add(btnPanel, gbc);

        add(formPanel, BorderLayout.WEST);

        // Table
        tableModel = new DefaultTableModel(
                new String[]{"Issue ID", "Book ID", "Student Name", "Issue Date", "Return Date", "Status"}, 0);
        issueTable = new JTable(tableModel);
        issueTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        issueTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = issueTable.getSelectedRow();
                if (row >= 0) {
                    txtIssueId.setText(tableModel.getValueAt(row, 0).toString());
                    txtBookId.setText(tableModel.getValueAt(row, 1).toString());
                    txtStudentName.setText(tableModel.getValueAt(row, 2).toString());
                    txtIssueDate.setText(tableModel.getValueAt(row, 3).toString());
                    String retDate = tableModel.getValueAt(row, 4).toString();
                    txtReturnDate.setText(retDate.equals("-") ? "" : retDate);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(issueTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Issue Records"));
        add(scrollPane, BorderLayout.CENTER);

        initDatabase();
        setVisible(true);
    }

    private void initDatabase() {
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS book_issues ("
                    + "issue_id INT PRIMARY KEY, book_id INT, "
                    + "student_name VARCHAR(100), issue_date VARCHAR(20), "
                    + "return_date VARCHAR(20), status VARCHAR(20))");
            stmt.close();
            loadRecords();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "JDBC Driver not found!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
    }

    private void loadRecords() {
        tableModel.setRowCount(0);
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM book_issues ORDER BY issue_id");
            while (rs.next()) {
                String retDate = rs.getString("return_date");
                tableModel.addRow(new Object[]{
                        rs.getInt("issue_id"),
                        rs.getInt("book_id"),
                        rs.getString("student_name"),
                        rs.getString("issue_date"),
                        (retDate == null || retDate.isEmpty()) ? "-" : retDate,
                        rs.getString("status")
                });
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading records: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnIssue)      issueBook();
        else if (e.getSource() == btnView)  loadRecords();
        else if (e.getSource() == btnReturn) markReturned();
        else if (e.getSource() == btnDelete) deleteRecord();
        else if (e.getSource() == btnClear)  clearForm();
    }

    private void issueBook() {
        try {
            int issueId = Integer.parseInt(txtIssueId.getText().trim());
            int bookId = Integer.parseInt(txtBookId.getText().trim());
            String student = txtStudentName.getText().trim();
            String issueDate = txtIssueDate.getText().trim();
            String returnDate = txtReturnDate.getText().trim();

            if (student.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Enter student name!");
                return;
            }

            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO book_issues VALUES (?, ?, ?, ?, ?, ?)");
            ps.setInt(1, issueId);
            ps.setInt(2, bookId);
            ps.setString(3, student);
            ps.setString(4, issueDate);
            ps.setString(5, returnDate.isEmpty() ? "" : returnDate);
            ps.setString(6, returnDate.isEmpty() ? "Issued" : "Returned");
            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(this, "Book issued successfully!");
            loadRecords();
            clearForm();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "IDs must be numbers!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void markReturned() {
        try {
            int issueId = Integer.parseInt(txtIssueId.getText().trim());
            String returnDate = txtReturnDate.getText().trim();
            if (returnDate.isEmpty()) {
                returnDate = java.time.LocalDate.now().toString();
            }

            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE book_issues SET return_date=?, status=? WHERE issue_id=?");
            ps.setString(1, returnDate);
            ps.setString(2, "Returned");
            ps.setInt(3, issueId);
            int rows = ps.executeUpdate();
            ps.close();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Book marked as returned!");
                loadRecords();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Issue ID not found!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void deleteRecord() {
        try {
            int issueId = Integer.parseInt(txtIssueId.getText().trim());
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Delete record " + issueId + "?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                PreparedStatement ps = conn.prepareStatement(
                        "DELETE FROM book_issues WHERE issue_id=?");
                ps.setInt(1, issueId);
                int rows = ps.executeUpdate();
                ps.close();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Record deleted!");
                    loadRecords();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Issue ID not found!");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void clearForm() {
        txtIssueId.setText("");
        txtBookId.setText("");
        txtStudentName.setText("");
        txtIssueDate.setText(java.time.LocalDate.now().toString());
        txtReturnDate.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Exercise2());
    }
}
