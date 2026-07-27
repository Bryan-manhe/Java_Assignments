/**
 * Assignment 02 - Exercise 2
 * Mobile Phone Inventory System using different constructors
 * to initialize mobile details and create duplicate object records.
 */
public class Exercise2 {

    private String brand;
    private String model;
    private double price;
    private int stockQuantity;

    // Default constructor
    public Exercise2() {
        this.brand = "Unknown";
        this.model = "Unknown";
        this.price = 0.0;
        this.stockQuantity = 0;
    }

    // Parameterized constructor
    public Exercise2(String brand, String model, double price, int stockQuantity) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // Copy constructor - creates a duplicate object
    public Exercise2(Exercise2 mobile) {
        this.brand = mobile.brand;
        this.model = mobile.model;
        this.price = mobile.price;
        this.stockQuantity = mobile.stockQuantity;
    }

    // Method to display mobile details
    public void display() {
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Price: $" + price);
        System.out.println("Stock Quantity: " + stockQuantity);
        System.out.println("------------------------");
    }

    // Method to update stock
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public static void main(String[] args) {
        System.out.println("=== MOBILE PHONE INVENTORY SYSTEM ===\n");

        // Using default constructor
        System.out.println("1. Default Constructor (Empty Record):");
        Exercise2 mobile1 = new Exercise2();
        mobile1.display();

        // Using parameterized constructor
        System.out.println("2. Parameterized Constructor:");
        Exercise2 mobile2 = new Exercise2("Apple", "iPhone 15 Pro", 1099.99, 50);
        mobile2.display();

        Exercise2 mobile3 = new Exercise2("Samsung", "Galaxy S24", 899.99, 75);
        mobile3.display();

        Exercise2 mobile4 = new Exercise2("Google", "Pixel 8", 699.99, 30);
        mobile4.display();

        // Using copy constructor to create duplicate record
        System.out.println("3. Copy Constructor (Duplicate Record):");
        Exercise2 mobile5 = new Exercise2(mobile2); // Copy of iPhone 15 Pro
        mobile5.display();

        // Modifying original and verifying copy is independent
        System.out.println("4. Stock Update - Original Modified, Copy Unaffected:");
        mobile2.addStock(10);
        System.out.println("Original (iPhone 15 Pro) after adding 10 units:");
        mobile2.display();
        System.out.println("Copy (iPhone 15 Pro) - unchanged:");
        mobile5.display();

        System.out.println("\n=== CONSTRUCTOR TYPES DEMONSTRATED ===");
        System.out.println("1. Default Constructor - initializes with default values");
        System.out.println("2. Parameterized Constructor - initializes with specific values");
        System.out.println("3. Copy Constructor - creates a duplicate object");
    }
}
