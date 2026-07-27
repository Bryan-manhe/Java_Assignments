/**
 * Assignment 14 - Exercise 1
 * ATM program handling invalid withdrawal amount using try-catch,
 * nested try, and multiple catch blocks.
 */

import java.util.Scanner;

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

public class Exercise1 {

    private String accountNumber;
    private double balance;

    public Exercise1(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > balance) {
            throw new InsufficientBalanceException("Insufficient balance! Available: $" +
                String.format("%.2f", balance) + ", Requested: $" + String.format("%.2f", amount));
        }
        balance -= amount;
        System.out.println("Withdrawal successful! Amount: $" + String.format("%.2f", amount));
        System.out.println("Remaining balance: $" + String.format("%.2f", balance));
    }

    public void displayBalance() {
        System.out.println("Account: " + accountNumber);
        System.out.println("Current Balance: $" + String.format("%.2f", balance));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Exercise1 atm = new Exercise1("ACC1001", 5000.00);

        System.out.println("=== ATM WITHDRAWAL SYSTEM ===\n");
        atm.displayBalance();

        System.out.print("\nEnter withdrawal amount: $");
        String input = scanner.nextLine();

        // Nested try-catch blocks
        try {
            // Outer try - for parsing
            double amount;

            try {
                // Inner try - for number format
                amount = Double.parseDouble(input);

                if (amount <= 0) {
                    System.out.println("Error: Withdrawal amount must be positive!");
                } else if (amount % 1 != 0) {
                    System.out.println("Error: ATM dispenses only whole dollar amounts!");
                } else {
                    try {
                        atm.withdraw(amount);
                    } catch (InsufficientBalanceException e) {
                        System.out.println("Transaction Failed: " + e.getMessage());
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid amount format! Please enter a valid number.");
                System.out.println("Details: " + e.getMessage());
            }

        } catch (Exception e) {
            // Outer catch for any unexpected exceptions
            System.out.println("Unexpected error: " + e.getMessage());
        }

        System.out.println("\n--- Multiple Catch Example ---");
        try {
            String[] operations = {"1000", "abc", "-500", "2000"};
            for (String op : operations) {
                try {
                    int val = Integer.parseInt(op);
                    if (val < 0) {
                        System.out.println("Cannot withdraw negative amount: " + val);
                        continue;
                    }
                    if (val > 1000) {
                        System.out.println("Daily limit exceeded: " + val);
                        continue;
                    }
                    System.out.println("Processing withdrawal: $" + val);
                } catch (NumberFormatException e) {
                    System.out.println("NumberFormatException caught: \"" + op + "\" is not a valid number");
                } catch (IllegalArgumentException e) {
                    System.out.println("IllegalArgumentException: " + e.getMessage());
                }
            }
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        scanner.close();
    }
}
