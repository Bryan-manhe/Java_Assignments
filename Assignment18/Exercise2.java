/**
 * Assignment 18 - Exercise 2
 * Bank Balance Calculator GUI application using Java Swing.
 * User enters initial balance and transaction amount.
 * Buttons perform deposit (addition) and withdrawal (subtraction).
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exercise2 extends JFrame implements ActionListener {

    private JTextField txtInitialBalance, txtTransactionAmount;
    private JTextField txtCurrentBalance, txtLastTransaction;
    private JButton btnDeposit, btnWithdraw, btnReset;
    private JLabel lblStatus;

    private double currentBalance = 0.0;
    private boolean initialized = false;

    public Exercise2() {
        setTitle("Bank Balance Calculator");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Title
        JLabel title = new JLabel("BANK BALANCE CALCULATOR", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(0, 80, 0));
        title.setBorder(BorderFactory.createEmptyBorder(15, 0, 10, 0));
        add(title, BorderLayout.NORTH);

        // Main Panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Initial Balance
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("Initial Balance ($):"), gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        txtInitialBalance = new JTextField(15);
        mainPanel.add(txtInitialBalance, gbc);

        // Set Balance Button
        gbc.gridx = 2; gbc.gridy = 0;
        JButton btnSetBalance = new JButton("Set");
        btnSetBalance.setBackground(new Color(0, 100, 200));
        btnSetBalance.setForeground(Color.WHITE);
        btnSetBalance.addActionListener(e -> {
            try {
                double bal = Double.parseDouble(txtInitialBalance.getText().trim());
                if (bal < 0) {
                    JOptionPane.showMessageDialog(this, "Balance cannot be negative!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                currentBalance = bal;
                initialized = true;
                txtCurrentBalance.setText(String.format("%.2f", currentBalance));
                lblStatus.setText("Status: Balance set to $" + String.format("%.2f", currentBalance));
                txtTransactionAmount.setEnabled(true);
                btnDeposit.setEnabled(true);
                btnWithdraw.setEnabled(true);
                txtInitialBalance.setEditable(false);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Enter a valid number!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        mainPanel.add(btnSetBalance, gbc);

        // Transaction Amount
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("Transaction Amount ($):"), gbc);
        gbc.gridx = 1; gbc.gridy = 1;
        txtTransactionAmount = new JTextField(15);
        txtTransactionAmount.setEnabled(false);
        mainPanel.add(txtTransactionAmount, gbc);

        // Separator
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 3;
        mainPanel.add(new JSeparator(), gbc);
        gbc.gridwidth = 1;

        // Current Balance Display
        gbc.gridx = 0; gbc.gridy = 3;
        mainPanel.add(new JLabel("Current Balance ($):"), gbc);
        gbc.gridx = 1; gbc.gridy = 3;
        txtCurrentBalance = new JTextField(15);
        txtCurrentBalance.setEditable(false);
        txtCurrentBalance.setFont(new Font("Arial", Font.BOLD, 16));
        txtCurrentBalance.setForeground(new Color(0, 100, 0));
        txtCurrentBalance.setText("0.00");
        mainPanel.add(txtCurrentBalance, gbc);

        // Last Transaction
        gbc.gridx = 0; gbc.gridy = 4;
        mainPanel.add(new JLabel("Last Transaction:"), gbc);
        gbc.gridx = 1; gbc.gridy = 4;
        txtLastTransaction = new JTextField(15);
        txtLastTransaction.setEditable(false);
        mainPanel.add(txtLastTransaction, gbc);

        // Status
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 3;
        lblStatus = new JLabel("Status: Please set your initial balance");
        lblStatus.setFont(new Font("Arial", Font.ITALIC, 12));
        mainPanel.add(lblStatus, gbc);

        add(mainPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));

        btnDeposit = new JButton("DEPOSIT +");
        btnDeposit.setFont(new Font("Arial", Font.BOLD, 14));
        btnDeposit.setBackground(new Color(0, 150, 0));
        btnDeposit.setForeground(Color.WHITE);
        btnDeposit.setEnabled(false);
        btnDeposit.addActionListener(this);

        btnWithdraw = new JButton("WITHDRAW -");
        btnWithdraw.setFont(new Font("Arial", Font.BOLD, 14));
        btnWithdraw.setBackground(new Color(200, 50, 0));
        btnWithdraw.setForeground(Color.WHITE);
        btnWithdraw.setEnabled(false);
        btnWithdraw.addActionListener(this);

        btnReset = new JButton("RESET");
        btnReset.setFont(new Font("Arial", Font.BOLD, 14));
        btnReset.setBackground(new Color(100, 100, 100));
        btnReset.setForeground(Color.WHITE);
        btnReset.addActionListener(this);

        buttonPanel.add(btnDeposit);
        buttonPanel.add(btnWithdraw);
        buttonPanel.add(btnReset);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(buttonPanel, BorderLayout.CENTER);
        southPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        add(southPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDeposit || e.getSource() == btnWithdraw) {
            String amountStr = txtTransactionAmount.getText().trim();
            if (amountStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter transaction amount!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                double amount = Double.parseDouble(amountStr);

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(this, "Transaction amount must be positive!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (e.getSource() == btnDeposit) {
                    currentBalance += amount;
                    txtLastTransaction.setText("Deposit: +$" + String.format("%.2f", amount));
                    lblStatus.setText("Success: Deposited $" + String.format("%.2f", amount));
                } else {
                    if (amount > currentBalance) {
                        JOptionPane.showMessageDialog(this,
                            "Insufficient balance!\nCurrent: $" + String.format("%.2f", currentBalance) +
                            "\nWithdrawal: $" + String.format("%.2f", amount),
                            "Transaction Failed", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    currentBalance -= amount;
                    txtLastTransaction.setText("Withdrawal: -$" + String.format("%.2f", amount));
                    lblStatus.setText("Success: Withdrew $" + String.format("%.2f", amount));
                }

                txtCurrentBalance.setText(String.format("%.2f", currentBalance));
                txtTransactionAmount.setText("");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == btnReset) {
            currentBalance = 0.0;
            initialized = false;
            txtInitialBalance.setText("");
            txtInitialBalance.setEditable(true);
            txtTransactionAmount.setText("");
            txtTransactionAmount.setEnabled(false);
            txtCurrentBalance.setText("0.00");
            txtLastTransaction.setText("");
            lblStatus.setText("Status: Reset complete. Set your initial balance.");
            btnDeposit.setEnabled(false);
            btnWithdraw.setEnabled(false);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Exercise2();
        });
    }
}
