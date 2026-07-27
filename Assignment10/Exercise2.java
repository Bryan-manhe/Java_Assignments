/**
 * Assignment 10 - Exercise 2
 * Abstract FoodOrder class with abstract method calculateBill().
 * Implement DineInOrder and TakeAwayOrder subclasses.
 */

import java.util.Scanner;

// Abstract class
abstract class FoodOrder {
    protected int orderId;
    protected String customerName;
    protected double foodAmount;
    protected static int orderCounter = 500;

    public FoodOrder(String customerName, double foodAmount) {
        this.orderId = ++orderCounter;
        this.customerName = customerName;
        this.foodAmount = foodAmount;
    }

    // Abstract method - subclasses must implement
    public abstract double calculateBill();

    // Concrete method
    public void displayOrder() {
        System.out.println("Order ID: #" + orderId);
        System.out.println("Customer: " + customerName);
        System.out.println("Food Amount: $" + String.format("%.2f", foodAmount));
    }
}

// Dine-In Order subclass
class DineInOrder extends FoodOrder {
    private int tableNumber;
    private int numberOfGuests;

    public DineInOrder(String customerName, double foodAmount, int tableNumber, int numberOfGuests) {
        super(customerName, foodAmount);
        this.tableNumber = tableNumber;
        this.numberOfGuests = numberOfGuests;
    }

    @Override
    public double calculateBill() {
        double serviceCharge = foodAmount * 0.10; // 10% service charge
        double gst = foodAmount * 0.05;           // 5% GST
        double total = foodAmount + serviceCharge + gst;

        System.out.println("--- DINE-IN ORDER ---");
        displayOrder();
        System.out.println("Table Number: " + tableNumber);
        System.out.println("Guests: " + numberOfGuests);
        System.out.println("Service Charge (10%): $" + String.format("%.2f", serviceCharge));
        System.out.println("GST (5%): $" + String.format("%.2f", gst));
        return total;
    }
}

// Takeaway Order subclass
class TakeAwayOrder extends FoodOrder {
    private double packagingCharge;
    private boolean hasLoyaltyCard;

    public TakeAwayOrder(String customerName, double foodAmount, double packagingCharge, boolean hasLoyaltyCard) {
        super(customerName, foodAmount);
        this.packagingCharge = packagingCharge;
        this.hasLoyaltyCard = hasLoyaltyCard;
    }

    @Override
    public double calculateBill() {
        double gst = foodAmount * 0.05;            // 5% GST
        double discount = hasLoyaltyCard ? foodAmount * 0.05 : 0; // 5% loyalty discount
        double total = foodAmount + packagingCharge + gst - discount;

        System.out.println("--- TAKEAWAY ORDER ---");
        displayOrder();
        System.out.println("Packaging Charge: $" + String.format("%.2f", packagingCharge));
        System.out.println("GST (5%): $" + String.format("%.2f", gst));
        if (hasLoyaltyCard) {
            System.out.println("Loyalty Discount (5%): -$" + String.format("%.2f", discount));
        }
        return total;
    }
}

public class Exercise2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== FOOD ORDER SYSTEM - ABSTRACT CLASS DEMO ===\n");

        System.out.println("Select Order Type:");
        System.out.println("1. Dine-In");
        System.out.println("2. Takeaway");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Customer Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Food Amount: $");
        double foodAmount = scanner.nextDouble();
        scanner.nextLine();

        FoodOrder order = null;

        if (choice == 1) {
            System.out.print("Enter Table Number: ");
            int tableNo = scanner.nextInt();
            System.out.print("Enter Number of Guests: ");
            int guests = scanner.nextInt();
            order = new DineInOrder(name, foodAmount, tableNo, guests);
        } else if (choice == 2) {
            System.out.print("Enter Packaging Charge: $");
            double packaging = scanner.nextDouble();
            System.out.print("Do you have a Loyalty Card? (true/false): ");
            boolean loyalty = scanner.nextBoolean();
            order = new TakeAwayOrder(name, foodAmount, packaging, loyalty);
        } else {
            System.out.println("Invalid choice!");
            scanner.close();
            return;
        }

        // Polymorphism - calling abstract method
        double totalBill = order.calculateBill();
        System.out.println("\nTotal Bill: $" + String.format("%.2f", totalBill));
        System.out.println("Payment Status: Pending");

        System.out.println("\n=== KEY CONCEPTS ===");
        System.out.println("1. Abstract class FoodOrder with abstract method calculateBill()");
        System.out.println("2. DineInOrder and TakeAwayOrder provide specific implementations");
        System.out.println("3. Polymorphism via FoodOrder reference");

        scanner.close();
    }
}
