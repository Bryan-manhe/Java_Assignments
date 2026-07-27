/**
 * Assignment 24 - Exercise 1
 * Library Management System GUI connected with database.
 * Combine Swing and JDBC for CRUD operations in a graphical interface.
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Exercise1 extends JFrame implements ActionListener {

    private static final String DB_URL = "jdbc:h2:~/librarydb;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    private JTextField txtBookId, txtTitle, txtAuthor, txtYear;
    private JTextArea txtDisplay;
    private JButton btnAdd, btnView, btnUpdate, btnDelete, btnClear;
    private JTable bookTable;
    private DefaultTableModel tableModel;
    private Connection conn;

    public Exercise1() {
        setTitle("Library Management System");
        setSize(750, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel title = new JLabel("LIBRARY MANAGEMENT SYSTEM", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(0, 70, 140));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Book Details"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Book ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        txtBookId = new JTextField(15);
        formPanel.add(txtBookId, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Title:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        txtTitle = new JTextField(15);
        formPanel.add(txtTitle, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Author:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        txtAuthor = new JTextField(15);
        formPanel.add(txtAuthor, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Year:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        txtYear = new JTextField(15);
        formPanel.add(txtYear, gbc);

        // Buttons
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        JPanel btnPanel = new JPanel(new FlowLayout());
        btnAdd = new JButton("Add Book");
        btnAdd.addActionListener(this);
        btnView = new JButton("View All");
        btnView.addActionListener(this);
        btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(this);
        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(this);
        btnClear = new JButton("Clear");
        btnClear.addActionListener(this);

        btnPanel.add(btnAdd);
        btnPanel.add(btnView);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnClear);
        formPanel.add(btnPanel, gbc);

        add(formPanel, BorderLayout.WEST);

        // Table display
        tableModel = new DefaultTableModel(new String[]{"Book ID", "Title", "Author", "Year"}, 0);
        bookTable = new JTable(tableModel);
        bookTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bookTable.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = bookTable.getSelectedRow();
                if (row >= 0) {
                    txtBookId.setText(tableModel.getValueAt(row, 0).toString());
                    txtTitle.setText(tableModel.getValueAt(row, 1).toString());
                    txtAuthor.setText(tableModel.getValueAt(row, 2).toString());
                    txtYear.setText(tableModel.getValueAt(row, 3).toString());
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(bookTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Book Records"));
        add(scrollPane, BorderLayout.CENTER);

        // Initialize database
        initDatabase();

        setVisible(true);
    }

    private void initDatabase() {
        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS books ("
                    + "book_id INT PRIMARY KEY, title VARCHAR(200), "
                    + "author VARCHAR(100), year INT)");
            stmt.close();
            loadBooks();
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(this, "JDBC Driver not found!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error: " + e.getMessage());
        }
    }

    private void loadBooks() {
        tableModel.setRowCount(0);
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM books ORDER BY book_id");
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("year")
                });
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading books: " + e.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            addBook();
        } else if (e.getSource() == btnView) {
            loadBooks();
        } else if (e.getSource() == btnUpdate) {
            updateBook();
        } else if (e.getSource() == btnDelete) {
            deleteBook();
        } else if (e.getSource() == btnClear) {
            clearForm();
        }
    }

    private void addBook() {
        try {
            int id = Integer.parseInt(txtBookId.getText().trim());
            String title = txtTitle.getText().trim();
            String author = txtAuthor.getText().trim();
            int year = Integer.parseInt(txtYear.getText().trim());

            PreparedStatement ps = conn.prepareStatement(
                    "INSERT INTO books VALUES (?, ?, ?, ?)");
            ps.setInt(1, id);
            ps.setString(2, title);
            ps.setString(3, author);
            ps.setInt(4, year);
            ps.executeUpdate();
            ps.close();

            JOptionPane.showMessageDialog(this, "Book added successfully!");
            loadBooks();
            clearForm();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Book ID and Year must be numbers!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void updateBook() {
        try {
            int id = Integer.parseInt(txtBookId.getText().trim());
            String title = txtTitle.getText().trim();
            String author = txtAuthor.getText().trim();
            int year = Integer.parseInt(txtYear.getText().trim());

            PreparedStatement ps = conn.prepareStatement(
                    "UPDATE books SET title=?, author=?, year=? WHERE book_id=?");
            ps.setString(1, title);
            ps.setString(2, author);
            ps.setInt(3, year);
            ps.setInt(4, id);
            int rows = ps.executeUpdate();
            ps.close();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Book updated!");
                loadBooks();
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this, "Book ID not found!");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void deleteBook() {
        try {
            int id = Integer.parseInt(txtBookId.getText().trim());
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Delete book ID " + id + "?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                PreparedStatement ps = conn.prepareStatement(
                        "DELETE FROM books WHERE book_id=?");
                ps.setInt(1, id);
                int rows = ps.executeUpdate();
                ps.close();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(this, "Book deleted!");
                    loadBooks();
                    clearForm();
                } else {
                    JOptionPane.showMessageDialog(this, "Book ID not found!");
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    private void clearForm() {
        txtBookId.setText("");
        txtTitle.setText("");
        txtAuthor.setText("");
        txtYear.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Exercise1());
    }
}
