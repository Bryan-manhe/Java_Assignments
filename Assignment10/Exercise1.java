/**
 * Assignment 10 - Exercise 1
 * Abstract Payment class and implement Credit Card and UPI payment classes.
 */

import java.util.Scanner;

// Abstract class
abstract class Payment {
    protected double amount;
    protected String transactionId;
    protected static int transactionCounter = 10000;

    public Payment(double amount) {
        this.amount = amount;
        this.transactionId = "TXN" + (++transactionCounter);
    }

    // Abstract method - must be implemented by subclasses
    public abstract void processPayment();

    // Concrete method
    public void displayReceipt() {
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Amount: $" + String.format("%.2f", amount));
        System.out.println("Payment Status: Successful");
    }
}

// Credit Card payment implementation
class CreditCardPayment extends Payment {
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;

    public CreditCardPayment(double amount, String cardNumber, String cardHolderName, String expiryDate) {
        super(amount);
        this.cardNumber = maskCardNumber(cardNumber);
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
    }

    private String maskCardNumber(String cardNumber) {
        if (cardNumber.length() >= 4) {
            return "XXXX-XXXX-XXXX-" + cardNumber.substring(cardNumber.length() - 4);
        }
        return "XXXX";
    }

    @Override
    public void processPayment() {
        System.out.println("\n--- Processing Credit Card Payment ---");
        System.out.println("Card Holder: " + cardHolderName);
        System.out.println("Card Number: " + cardNumber);
        System.out.println("Expiry: " + expiryDate);
        System.out.println("Validating card details...");
        System.out.println("Authorizing payment of $" + String.format("%.2f", amount) + "...");
        System.out.println("Payment approved by bank!");
        displayReceipt();
    }
}

// UPI Payment implementation
class UPIPayment extends Payment {
    private String upiId;
    private String upiPin;

    public UPIPayment(double amount, String upiId, String upiPin) {
        super(amount);
        this.upiId = upiId;
        this.upiPin = upiPin;
    }

    @Override
    public void processPayment() {
        System.out.println("\n--- Processing UPI Payment ---");
        System.out.println("UPI ID: " + upiId);
        System.out.println("Connecting to UPI server...");
        System.out.println("Verifying UPI PIN...");
        System.out.println("Processing payment of $" + String.format("%.2f", amount) + "...");
        System.out.println("Payment successful via UPI!");
        displayReceipt();
    }
}

public class Exercise1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== PAYMENT SYSTEM - ABSTRACT CLASS DEMO ===\n");

        System.out.println("Select Payment Method:");
        System.out.println("1. Credit Card");
        System.out.println("2. UPI");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter amount: $");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        Payment payment = null;

        if (choice == 1) {
            System.out.print("Enter Card Number: ");
            String cardNo = scanner.nextLine();
            System.out.print("Enter Card Holder Name: ");
            String holderName = scanner.nextLine();
            System.out.print("Enter Expiry Date (MM/YY): ");
            String expiry = scanner.nextLine();
            payment = new CreditCardPayment(amount, cardNo, holderName, expiry);
        } else if (choice == 2) {
            System.out.print("Enter UPI ID (e.g., name@bank): ");
            String upiId = scanner.nextLine();
            System.out.print("Enter UPI PIN: ");
            String upiPin = scanner.nextLine();
            payment = new UPIPayment(amount, upiId, upiPin);
        } else {
            System.out.println("Invalid choice!");
            scanner.close();
            return;
        }

        // Polymorphism - calling abstract method via parent reference
        payment.processPayment();

        System.out.println("\n=== KEY CONCEPTS ===");
        System.out.println("1. Abstract class Payment with abstract method processPayment()");
        System.out.println("2. CreditCardPayment and UPIPayment provide implementations");
        System.out.println("3. Polymorphism - Payment reference used for both implementations");

        scanner.close();
    }
}
