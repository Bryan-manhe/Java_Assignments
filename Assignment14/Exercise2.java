/**
 * Assignment 14 - Exercise 2
 * Online Shopping program that handles an invalid product quantity
 * entered by the user using a try-catch block.
 */

import java.util.*;

class Product {
    private int productId;
    private String name;
    private double price;
    private int stockQuantity;

    public Product(int productId, String name, double price, int stockQuantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public int getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }

    public boolean reduceStock(int quantity) {
        if (quantity <= stockQuantity) {
            stockQuantity -= quantity;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("ID: %d | %s | $%.2f | Stock: %d", productId, name, price, stockQuantity);
    }
}

public class Exercise2 {

    private List<Product> products;
    private List<String> cart;
    private double totalAmount;

    public Exercise2() {
        products = new ArrayList<>();
        cart = new ArrayList<>();
        totalAmount = 0.0;

        // Sample products
        products.add(new Product(101, "Laptop", 899.99, 10));
        products.add(new Product(102, "Mouse", 29.99, 50));
        products.add(new Product(103, "Keyboard", 79.99, 30));
        products.add(new Product(104, "Monitor", 399.99, 15));
        products.add(new Product(105, "Headphones", 149.99, 25));
    }

    public void displayProducts() {
        System.out.println("\n--- Available Products ---");
        for (Product p : products) {
            System.out.println(p);
        }
        System.out.println("--------------------------");
    }

    public void addToCart(int productId, int quantity) {
        // Validate quantity - this will be caught by try-catch in main
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero! You entered: " + quantity);
        }

        Product selected = null;
        for (Product p : products) {
            if (p.getProductId() == productId) {
                selected = p;
                break;
            }
        }

        if (selected == null) {
            System.out.println("Product ID " + productId + " not found!");
            return;
        }

        if (quantity > selected.getStockQuantity()) {
            System.out.println("Insufficient stock! Available: " + selected.getStockQuantity() +
                               ", Requested: " + quantity);
            return;
        }

        selected.reduceStock(quantity);
        double cost = selected.getPrice() * quantity;
        cart.add(quantity + " x " + selected.getName() + " = $" + String.format("%.2f", cost));
        totalAmount += cost;
        System.out.println("Added " + quantity + " x " + selected.getName() + " to cart!");
    }

    public void displayCart() {
        System.out.println("\n========== SHOPPING CART ==========");
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            for (String item : cart) {
                System.out.println("  " + item);
            }
            System.out.println("----------------------------------");
            System.out.println("Total: $" + String.format("%.2f", totalAmount));
        }
        System.out.println("===================================\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Exercise2 shop = new Exercise2();

        System.out.println("=== ONLINE SHOPPING SYSTEM ===\n");
        shop.displayProducts();

        while (true) {
            System.out.println("\n--- Options ---");
            System.out.println("1. Add product to cart");
            System.out.println("2. View cart");
            System.out.println("3. Checkout & Exit");
            System.out.print("Enter choice: ");

            String choiceStr = scanner.nextLine();
            int choice;

            try {
                choice = Integer.parseInt(choiceStr);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number (1-3).");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    String idStr = scanner.nextLine();
                    System.out.print("Enter Quantity: ");
                    String quantityStr = scanner.nextLine();

                    try {
                        int productId = Integer.parseInt(idStr);
                        int quantity = Integer.parseInt(quantityStr);

                        // This may throw IllegalArgumentException for invalid quantity
                        try {
                            shop.addToCart(productId, quantity);
                        } catch (IllegalArgumentException e) {
                            System.out.println("Quantity Error: " + e.getMessage());
                        }

                    } catch (NumberFormatException e) {
                        System.out.println("Error: Product ID and Quantity must be valid numbers!");
                    }
                    break;

                case 2:
                    shop.displayCart();
                    break;

                case 3:
                    if (!shop.cart.isEmpty()) {
                        shop.displayCart();
                        System.out.println("Thank you for shopping with us!");
                    } else {
                        System.out.println("Cart is empty. Goodbye!");
                    }
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please enter 1-3.");
            }
        }
    }
}
