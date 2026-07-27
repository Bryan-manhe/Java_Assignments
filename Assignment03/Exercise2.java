/**
 * Assignment 03 - Exercise 2
 * Restaurant Billing Application where overloaded methods calculate bills
 * for dine-in, takeaway, and delivery orders.
 * Static variables track total orders across all instances.
 */

import java.util.Scanner;

public class Exercise2 {

    // Static variables to track total orders
    private static int totalOrders = 0;
    private static double totalRevenue = 0.0;

    // Instance variables
    private int orderId;
    private String customerName;

    public Exercise2(int orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
    }

    // Overloaded method 1: Dine-in bill calculation (includes service charge)
    public double calculateBill(double foodAmount) {
        double serviceCharge = foodAmount * 0.10; // 10% service charge
        double gst = foodAmount * 0.05;            // 5% GST
        double total = foodAmount + serviceCharge + gst;
        updateStats(total);
        System.out.println("  Dine-in Order #" + orderId + " for " + customerName);
        System.out.println("  Food Amount: $" + String.format("%.2f", foodAmount));
        System.out.println("  Service Charge (10%): $" + String.format("%.2f", serviceCharge));
        System.out.println("  GST (5%): $" + String.format("%.2f", gst));
        return total;
    }

    // Overloaded method 2: Takeaway bill calculation (packaging charge, no service charge)
    public double calculateBill(double foodAmount, double packagingCharge) {
        double gst = foodAmount * 0.05; // 5% GST
        double total = foodAmount + packagingCharge + gst;
        updateStats(total);
        System.out.println("  Takeaway Order #" + orderId + " for " + customerName);
        System.out.println("  Food Amount: $" + String.format("%.2f", foodAmount));
        System.out.println("  Packaging Charge: $" + String.format("%.2f", packagingCharge));
        System.out.println("  GST (5%): $" + String.format("%.2f", gst));
        return total;
    }

    // Overloaded method 3: Delivery bill calculation (includes delivery fee and service charge)
    public double calculateBill(double foodAmount, double deliveryFee, double distanceKm) {
        double serviceCharge = foodAmount * 0.08;      // 8% service charge
        double deliveryCharge = deliveryFee + (distanceKm * 2.0); // $2 per km
        double gst = foodAmount * 0.05;                // 5% GST
        double total = foodAmount + serviceCharge + deliveryCharge + gst;
        updateStats(total);
        System.out.println("  Delivery Order #" + orderId + " for " + customerName);
        System.out.println("  Food Amount: $" + String.format("%.2f", foodAmount));
        System.out.println("  Service Charge (8%): $" + String.format("%.2f", serviceCharge));
        System.out.println("  Delivery Fee: $" + String.format("%.2f", deliveryFee));
        System.out.println("  Distance: " + String.format("%.1f", distanceKm) + " km");
        System.out.println("  Distance Charge: $" + String.format("%.2f", distanceKm * 2.0));
        System.out.println("  GST (5%): $" + String.format("%.2f", gst));
        return total;
    }

    // Private method to update static statistics
    private void updateStats(double billAmount) {
        totalOrders++;
        totalRevenue += billAmount;
    }

    // Static methods to get statistics
    public static int getTotalOrders() {
        return totalOrders;
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== RESTAURANT BILLING APPLICATION ===\n");
        System.out.println("Method Overloading Demo: Same method name 'calculateBill'");
        System.out.println("with different parameters for different order types.\n");

        // Dine-in order
        System.out.println("--- Order 1: Dine-In ---");
        Exercise2 order1 = new Exercise2(1001, "Alice");
        double bill1 = order1.calculateBill(45.50);
        System.out.println("  Total Bill: $" + String.format("%.2f", bill1) + "\n");

        // Takeaway order
        System.out.println("--- Order 2: Takeaway ---");
        Exercise2 order2 = new Exercise2(1002, "Bob");
        double bill2 = order2.calculateBill(32.00, 2.50);
        System.out.println("  Total Bill: $" + String.format("%.2f", bill2) + "\n");

        // Delivery order
        System.out.println("--- Order 3: Delivery ---");
        Exercise2 order3 = new Exercise2(1003, "Carol");
        double bill3 = order3.calculateBill(55.00, 3.50, 5.0);
        System.out.println("  Total Bill: $" + String.format("%.2f", bill3) + "\n");

        // Another dine-in order
        System.out.println("--- Order 4: Dine-In ---");
        Exercise2 order4 = new Exercise2(1004, "David");
        double bill4 = order4.calculateBill(28.75);
        System.out.println("  Total Bill: $" + String.format("%.2f", bill4) + "\n");

        // Display static statistics
        System.out.println("========================================");
        System.out.println("RESTAURANT SUMMARY (Static Variables)");
        System.out.println("========================================");
        System.out.println("Total Orders Processed: " + getTotalOrders());
        System.out.println("Total Revenue: $" + String.format("%.2f", getTotalRevenue()));
        System.out.println("\nAccessing static members via class name:");
        System.out.println("Exercise2.getTotalOrders() = " + Exercise2.getTotalOrders());
        System.out.println("Exercise2.getTotalRevenue() = $" + String.format("%.2f", Exercise2.getTotalRevenue()));

        scanner.close();
    }
}
