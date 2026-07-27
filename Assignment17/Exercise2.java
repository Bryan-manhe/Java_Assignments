/**
 * Assignment 17 - Exercise 2
 * Employee Registration Form using Java Swing GUI.
 * Takes employee details and displays them in a dialog box.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise2 extends JFrame implements ActionListener {

    private JTextField txtEmpId, txtName, txtSalary;
    private JComboBox<String> cmbDepartment, cmbPosition;
    private JRadioButton rbFullTime, rbPartTime;
    private ButtonGroup employmentGroup;
    private JTextArea txtAddress;
    private JButton btnSubmit, btnReset;

    public Exercise2() {
        setTitle("Employee Registration Form");
        setSize(650, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel titleLabel = new JLabel("EMPLOYEE REGISTRATION FORM", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(new Color(0, 100, 0));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Employee ID
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Employee ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        txtEmpId = new JTextField(20);
        formPanel.add(txtEmpId, gbc);

        // Name
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        txtName = new JTextField(20);
        formPanel.add(txtName, gbc);

        // Department
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Department:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        String[] departments = {"IT", "Engineering", "Marketing", "Finance", "HR", "Sales", "Operations"};
        cmbDepartment = new JComboBox<>(departments);
        formPanel.add(cmbDepartment, gbc);

        // Position
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Position:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        String[] positions = {"Junior", "Senior", "Lead", "Manager", "Director", "Intern"};
        cmbPosition = new JComboBox<>(positions);
        formPanel.add(cmbPosition, gbc);

        // Salary
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Salary ($):"), gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        txtSalary = new JTextField(20);
        formPanel.add(txtSalary, gbc);

        // Employment Type
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("Employment Type:"), gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        JPanel empTypePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbFullTime = new JRadioButton("Full-Time");
        rbPartTime = new JRadioButton("Part-Time");
        employmentGroup = new ButtonGroup();
        employmentGroup.add(rbFullTime);
        employmentGroup.add(rbPartTime);
        rbFullTime.setSelected(true);
        empTypePanel.add(rbFullTime);
        empTypePanel.add(rbPartTime);
        formPanel.add(empTypePanel, gbc);

        // Address
        gbc.gridx = 0; gbc.gridy = 6;
        formPanel.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1; gbc.gridy = 6;
        txtAddress = new JTextArea(3, 20);
        txtAddress.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(txtAddress);
        formPanel.add(scrollPane, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnSubmit = new JButton("Register Employee");
        btnSubmit.setBackground(new Color(0, 120, 0));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.setFont(new Font("Arial", Font.BOLD, 14));
        btnSubmit.addActionListener(this);

        btnReset = new JButton("Clear Form");
        btnReset.setBackground(new Color(150, 50, 50));
        btnReset.setForeground(Color.WHITE);
        btnReset.setFont(new Font("Arial", Font.BOLD, 14));
        btnReset.addActionListener(this);

        buttonPanel.add(btnSubmit);
        buttonPanel.add(btnReset);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(buttonPanel, BorderLayout.CENTER);
        southPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit) {
            String empId = txtEmpId.getText().trim();
            String name = txtName.getText().trim();
            String department = (String) cmbDepartment.getSelectedItem();
            String position = (String) cmbPosition.getSelectedItem();
            String salary = txtSalary.getText().trim();
            String employmentType = rbFullTime.isSelected() ? "Full-Time" : "Part-Time";
            String address = txtAddress.getText().trim();

            if (empId.isEmpty() || name.isEmpty() || salary.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Please fill Employee ID, Name, and Salary fields!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double salaryValue = Double.parseDouble(salary);
                if (salaryValue <= 0) {
                    JOptionPane.showMessageDialog(this,
                        "Salary must be a positive value!",
                        "Invalid Salary",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                    "Please enter a valid numeric salary!",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            String message = "EMPLOYEE REGISTERED SUCCESSFULLY\n"
                    + "================================\n"
                    + "Employee ID: " + empId + "\n"
                    + "Name: " + name + "\n"
                    + "Department: " + department + "\n"
                    + "Position: " + position + "\n"
                    + "Salary: $" + String.format("%.2f", Double.parseDouble(salary)) + "\n"
                    + "Employment Type: " + employmentType + "\n"
                    + "Address: " + (address.isEmpty() ? "N/A" : address) + "\n"
                    + "================================\n"
                    + "Registration Date: " + java.time.LocalDate.now();

            JOptionPane.showMessageDialog(this, message, "Employee Registration Details",
                JOptionPane.INFORMATION_MESSAGE);

        } else if (e.getSource() == btnReset) {
            txtEmpId.setText("");
            txtName.setText("");
            txtSalary.setText("");
            txtAddress.setText("");
            cmbDepartment.setSelectedIndex(0);
            cmbPosition.setSelectedIndex(0);
            rbFullTime.setSelected(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Exercise2();
        });
    }
}
