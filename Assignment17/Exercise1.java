/**
 * Assignment 17 - Exercise 1
 * Student Registration Form GUI using Swing.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise1 extends JFrame implements ActionListener {

    private JTextField txtName, txtRollNo, txtEmail;
    private JComboBox<String> cmbCourse, cmbSemester;
    private JRadioButton rbMale, rbFemale;
    private ButtonGroup genderGroup;
    private JTextArea txtDisplay;
    private JButton btnSubmit, btnReset;

    public Exercise1() {
        setTitle("Student Registration Form");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel titleLabel = new JLabel("STUDENT REGISTRATION FORM", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 70, 140));
        add(titleLabel, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Name
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Full Name:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        txtName = new JTextField(20);
        formPanel.add(txtName, gbc);

        // Roll No
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Roll Number:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        txtRollNo = new JTextField(20);
        formPanel.add(txtRollNo, gbc);

        // Email
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        txtEmail = new JTextField(20);
        formPanel.add(txtEmail, gbc);

        // Course
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Course:"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        String[] courses = {"Computer Science", "Mathematics", "Physics", "Chemistry", "Biology", "Engineering"};
        cmbCourse = new JComboBox<>(courses);
        formPanel.add(cmbCourse, gbc);

        // Semester
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Semester:"), gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        String[] semesters = {"1", "2", "3", "4", "5", "6", "7", "8"};
        cmbSemester = new JComboBox<>(semesters);
        formPanel.add(cmbSemester, gbc);

        // Gender
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("Gender:"), gbc);
        gbc.gridx = 1; gbc.gridy = 5;
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        genderGroup = new ButtonGroup();
        genderGroup.add(rbMale);
        genderGroup.add(rbFemale);
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);
        formPanel.add(genderPanel, gbc);

        add(formPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnSubmit = new JButton("Submit");
        btnSubmit.setBackground(new Color(0, 120, 200));
        btnSubmit.setForeground(Color.WHITE);
        btnSubmit.addActionListener(this);

        btnReset = new JButton("Reset");
        btnReset.setBackground(new Color(200, 50, 50));
        btnReset.setForeground(Color.WHITE);
        btnReset.addActionListener(this);

        buttonPanel.add(btnSubmit);
        buttonPanel.add(btnReset);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit) {
            String name = txtName.getText().trim();
            String rollNo = txtRollNo.getText().trim();
            String email = txtEmail.getText().trim();
            String course = (String) cmbCourse.getSelectedItem();
            String semester = (String) cmbSemester.getSelectedItem();
            String gender = rbMale.isSelected() ? "Male" : rbFemale.isSelected() ? "Female" : "Not Specified";

            if (name.isEmpty() || rollNo.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all required fields!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String message = "Student Registration Successful!\n\n"
                    + "Name: " + name + "\n"
                    + "Roll No: " + rollNo + "\n"
                    + "Email: " + email + "\n"
                    + "Course: " + course + "\n"
                    + "Semester: " + semester + "\n"
                    + "Gender: " + gender;

            JOptionPane.showMessageDialog(this, message, "Registration Details", JOptionPane.INFORMATION_MESSAGE);

        } else if (e.getSource() == btnReset) {
            txtName.setText("");
            txtRollNo.setText("");
            txtEmail.setText("");
            cmbCourse.setSelectedIndex(0);
            cmbSemester.setSelectedIndex(0);
            genderGroup.clearSelection();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Exercise1();
        });
    }
}
