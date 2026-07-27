/**
 * Assignment 06 - Exercise 2
 * Food Delivery Application where an inner class handles order details
 * and anonymous classes handle delivery status updates.
 */

import java.util.ArrayList;

// Interface for delivery status updates
interface DeliveryStatus {
    void updateStatus(String status);
}

// Outer class
class FoodDelivery {
    private String restaurantName;
    private String customerName;
    private ArrayList<String> orderItems;

    public FoodDelivery(String restaurantName, String customerName) {
        this.restaurantName = restaurantName;
        this.customerName = customerName;
        this.orderItems = new ArrayList<>();
    }

    public void addItem(String item) {
        orderItems.add(item);
    }

    // Inner class to handle order details
    class OrderHandler {
        private int orderId;
        private static int orderCounter = 1000;

        public OrderHandler() {
            this.orderId = ++orderCounter;
        }

        public void displayOrderDetails() {
            System.out.println("========== ORDER DETAILS ==========");
            System.out.println("Order ID: #" + orderId);
            System.out.println("Restaurant: " + restaurantName);
            System.out.println("Customer: " + customerName);
            System.out.println("Items Ordered:");
            if (orderItems.isEmpty()) {
                System.out.println("  - No items ordered");
            } else {
                for (int i = 0; i < orderItems.size(); i++) {
                    System.out.println("  " + (i + 1) + ". " + orderItems.get(i));
                }
            }
            System.out.println("===================================");
        }

        public int getOrderId() {
            return orderId;
        }
    }

    // Methods that use anonymous classes for delivery status
    public void processDelivery() {
        // Anonymous class for "Order Placed" status
        DeliveryStatus placed = new DeliveryStatus() {
            @Override
            public void updateStatus(String status) {
                System.out.println("[" + status + "] Your order from " + restaurantName + " has been placed successfully!");
            }
        };
        placed.updateStatus("ORDER PLACED");

        // Anonymous class for "Preparing" status
        DeliveryStatus preparing = new DeliveryStatus() {
            @Override
            public void updateStatus(String status) {
                System.out.println("[" + status + "] Restaurant " + restaurantName + " is preparing your food...");
            }
        };
        preparing.updateStatus("PREPARING");

        // Anonymous class for "Out for Delivery" status
        DeliveryStatus outForDelivery = new DeliveryStatus() {
            @Override
            public void updateStatus(String status) {
                System.out.println("[" + status + "] Your order is out for delivery to " + customerName + "!");
            }
        };
        outForDelivery.updateStatus("OUT FOR DELIVERY");

        // Anonymous class for "Delivered" status
        DeliveryStatus delivered = new DeliveryStatus() {
            @Override
            public void updateStatus(String status) {
                System.out.println("[" + status + "] Order delivered to " + customerName + ". Enjoy your meal!");
            }
        };
        delivered.updateStatus("DELIVERED");
    }
}

public class Exercise2 {
    public static void main(String[] args) {
        System.out.println("=== FOOD DELIVERY APPLICATION ===\n");

        // Create a food delivery order
        FoodDelivery order1 = new FoodDelivery("Pizza Palace", "Alice Johnson");
        order1.addItem("Margherita Pizza");
        order1.addItem("Garlic Bread");
        order1.addItem("Coke");

        // Use inner class to handle order details
        System.out.println("--- Inner Class: Order Handler ---");
        FoodDelivery.OrderHandler handler1 = order1.new OrderHandler();
        handler1.displayOrderDetails();

        // Use anonymous classes for delivery status
        System.out.println("\n--- Anonymous Classes: Delivery Status Updates ---");
        order1.processDelivery();

        // Another order
        System.out.println();
        FoodDelivery order2 = new FoodDelivery("Burger Barn", "Bob Smith");
        order2.addItem("Double Cheeseburger");
        order2.addItem("French Fries");
        order2.addItem("Milkshake");

        FoodDelivery.OrderHandler handler2 = order2.new OrderHandler();
        handler2.displayOrderDetails();

        System.out.println("\n--- Anonymous Classes: Delivery Status Updates ---");
        order2.processDelivery();

        System.out.println("\n=== CONCEPTS DEMONSTRATED ===");
        System.out.println("1. Inner Class (OrderHandler) - handles order details");
        System.out.println("2. Anonymous Classes (DeliveryStatus) - handle status updates");
    }
}
