/**
 * Assignment 18 - Exercise 1
 * GUI Calculator where buttons perform addition and subtraction.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise1 extends JFrame implements ActionListener {

    private JTextField txtNum1, txtNum2, txtResult;
    private JButton btnAdd, btnSubtract, btnClear;

    public Exercise1() {
        setTitle("Simple Calculator - Add & Subtract");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel title = new JLabel("CALCULATOR", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(new Color(0, 70, 140));
        add(title, BorderLayout.NORTH);

        // Center Panel
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Number 1
        gbc.gridx = 0; gbc.gridy = 0;
        centerPanel.add(new JLabel("Number 1:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        txtNum1 = new JTextField(15);
        centerPanel.add(txtNum1, gbc);

        // Number 2
        gbc.gridx = 0; gbc.gridy = 1;
        centerPanel.add(new JLabel("Number 2:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        txtNum2 = new JTextField(15);
        centerPanel.add(txtNum2, gbc);

        // Result
        gbc.gridx = 0; gbc.gridy = 2;
        centerPanel.add(new JLabel("Result:"), gbc);
        gbc.gridx = 1; gbc.gridy = 2;
        txtResult = new JTextField(15);
        txtResult.setEditable(false);
        txtResult.setFont(new Font("Arial", Font.BOLD, 14));
        txtResult.setForeground(new Color(0, 100, 0));
        centerPanel.add(txtResult, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        btnAdd = new JButton("ADD +");
        btnAdd.setFont(new Font("Arial", Font.BOLD, 14));
        btnAdd.setBackground(new Color(0, 150, 0));
        btnAdd.setForeground(Color.WHITE);
        btnAdd.addActionListener(this);

        btnSubtract = new JButton("SUBTRACT -");
        btnSubtract.setFont(new Font("Arial", Font.BOLD, 14));
        btnSubtract.setBackground(new Color(200, 50, 0));
        btnSubtract.setForeground(Color.WHITE);
        btnSubtract.addActionListener(this);

        btnClear = new JButton("CLEAR");
        btnClear.setFont(new Font("Arial", Font.BOLD, 14));
        btnClear.setBackground(new Color(100, 100, 100));
        btnClear.setForeground(Color.WHITE);
        btnClear.addActionListener(this);

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnSubtract);
        buttonPanel.add(btnClear);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(buttonPanel, BorderLayout.CENTER);
        southPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String num1Str = txtNum1.getText().trim();
        String num2Str = txtNum2.getText().trim();

        if (num1Str.isEmpty() || num2Str.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both numbers!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double num1 = Double.parseDouble(num1Str);
            double num2 = Double.parseDouble(num2Str);
            double result = 0;

            if (e.getSource() == btnAdd) {
                result = num1 + num2;
                txtResult.setText(num1 + " + " + num2 + " = " + result);
            } else if (e.getSource() == btnSubtract) {
                result = num1 - num2;
                txtResult.setText(num1 + " - " + num2 + " = " + result);
            } else if (e.getSource() == btnClear) {
                txtNum1.setText("");
                txtNum2.setText("");
                txtResult.setText("");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Exercise1();
        });
    }
}
