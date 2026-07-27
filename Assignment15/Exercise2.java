/**
 * Assignment 15 - Exercise 2
 * ATM PIN Verification program that throws an exception for an invalid PIN
 * and uses a finally block to display a completion message.
 */

import java.util.Scanner;

class InvalidPinException extends Exception {
    public InvalidPinException(String message) {
        super(message);
    }
}

public class Exercise2 {

    private static final int CORRECT_PIN = 1234;
    private static double balance = 5000.00;
    private static int pinAttempts = 0;

    // Method using throws
    public static boolean verifyPin(int enteredPin) throws InvalidPinException {
        if (String.valueOf(enteredPin).length() != 4) {
            throw new InvalidPinException("PIN must be exactly 4 digits!");
        }

        if (enteredPin != CORRECT_PIN) {
            pinAttempts++;
            throw new InvalidPinException("Incorrect PIN entered. Attempt " + pinAttempts);
        }

        return true;
    }

    // Method that uses throw for amount validation
    public static void withdraw(double amount) throws InvalidPinException {
        if (amount <= 0) {
            throw new InvalidPinException("Withdrawal amount must be positive!");
        }
        if (amount > balance) {
            throw new InvalidPinException("Insufficient balance! Available: $" +
                String.format("%.2f", balance));
        }
        balance -= amount;
        System.out.println("Withdrawal of $" + String.format("%.2f", amount) + " successful!");
        System.out.println("Remaining balance: $" + String.format("%.2f", balance));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== ATM PIN VERIFICATION SYSTEM ===\n");
        System.out.println("(Using throw, throws, and finally)\n");

        // PIN Verification
        System.out.println("--- Step 1: PIN Verification ---");
        System.out.print("Enter 4-digit PIN: ");
        String pinInput = scanner.nextLine();

        try {
            int enteredPin;
            try {
                enteredPin = Integer.parseInt(pinInput);
            } catch (NumberFormatException e) {
                throw new InvalidPinException("PIN must contain only digits!");
            }

            if (verifyPin(enteredPin)) {
                System.out.println("PIN verified successfully!");
                System.out.println("Current balance: $" + String.format("%.2f", balance));

                // Transaction
                System.out.println("\n--- Step 2: Withdrawal ---");
                System.out.print("Enter withdrawal amount: $");
                String amountInput = scanner.nextLine();

                try {
                    double amount = Double.parseDouble(amountInput);
                    withdraw(amount);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Invalid amount format!");
                } catch (InvalidPinException e) {
                    System.out.println("Transaction Error: " + e.getMessage());
                }
            }

        } catch (InvalidPinException e) {
            System.out.println("PIN Error: " + e.getMessage());

            if (pinAttempts >= 3) {
                System.out.println("\nCARD BLOCKED! Too many incorrect PIN attempts.");
                System.out.println("Please contact your bank.");
            }

        } finally {
            // Finally block - always executes regardless of exceptions
            System.out.println("\n========================================");
            System.out.println("FINALLY: PIN verification process completed.");
            System.out.println("This message always appears.");
            System.out.println("========================================");
        }

        System.out.println("\n--- Demonstration: finally with System.exit() ---");
        // Note: System.exit() would stop finally from executing
        System.out.println("'finally' does NOT execute if System.exit() is called");
        System.out.println("but it DOES execute even if return is called.");

        scanner.close();
    }
}
