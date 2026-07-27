/**
 * Assignment 07 - Exercise 2
 * E-Commerce Product System where electronic, clothing, and grocery products
 * inherit common properties and implement product interfaces.
 */

// Interface for all products
interface ProductInterface {
    String getProductInfo();
    double calculateDiscountPrice();
    boolean isAvailable();
}

// Base class
abstract class Product {
    protected int productId;
    protected String productName;
    protected double price;
    protected int stockQuantity;

    public Product(int productId, String productName, double price, int stockQuantity) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public boolean isAvailable() {
        return stockQuantity > 0;
    }

    public void reduceStock(int quantity) {
        if (quantity <= stockQuantity) {
            stockQuantity -= quantity;
        }
    }

    public String getBaseInfo() {
        return "ID: " + productId + " | " + productName + " | $" + String.format("%.2f", price);
    }
}

// Electronic product
class ElectronicProduct extends Product implements ProductInterface {
    private String brand;
    private int warrantyMonths;

    public ElectronicProduct(int productId, String productName, double price,
                             int stockQuantity, String brand, int warrantyMonths) {
        super(productId, productName, price, stockQuantity);
        this.brand = brand;
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public String getProductInfo() {
        return "[ELECTRONIC] " + getBaseInfo() +
               " | Brand: " + brand +
               " | Warranty: " + warrantyMonths + " months";
    }

    @Override
    public double calculateDiscountPrice() {
        return price * 0.90; // 10% discount for electronics
    }
}

// Clothing product
class ClothingProduct extends Product implements ProductInterface {
    private String size;
    private String material;

    public ClothingProduct(int productId, String productName, double price,
                           int stockQuantity, String size, String material) {
        super(productId, productName, price, stockQuantity);
        this.size = size;
        this.material = material;
    }

    @Override
    public String getProductInfo() {
        return "[CLOTHING] " + getBaseInfo() +
               " | Size: " + size +
               " | Material: " + material;
    }

    @Override
    public double calculateDiscountPrice() {
        return price * 0.85; // 15% discount for clothing
    }
}

// Grocery product
class GroceryProduct extends Product implements ProductInterface {
    private String expiryDate;
    private boolean isPerishable;

    public GroceryProduct(int productId, String productName, double price,
                          int stockQuantity, String expiryDate, boolean isPerishable) {
        super(productId, productName, price, stockQuantity);
        this.expiryDate = expiryDate;
        this.isPerishable = isPerishable;
    }

    @Override
    public String getProductInfo() {
        return "[GROCERY] " + getBaseInfo() +
               " | Expiry: " + expiryDate +
               " | Perishable: " + (isPerishable ? "Yes" : "No");
    }

    @Override
    public double calculateDiscountPrice() {
        if (isPerishable) {
            return price * 0.80; // 20% discount on perishable items
        }
        return price * 0.95; // 5% discount on non-perishable
    }
}

public class Exercise2 {
    public static void main(String[] args) {
        System.out.println("=== E-COMMERCE PRODUCT SYSTEM ===\n");

        // Create products of different categories
        ElectronicProduct laptop = new ElectronicProduct(101, "Laptop Pro", 1200.00, 15, "TechBrand", 24);
        ElectronicProduct phone = new ElectronicProduct(102, "SmartPhone X", 899.99, 30, "MobileTech", 12);

        ClothingProduct shirt = new ClothingProduct(201, "Cotton Shirt", 39.99, 50, "L", "Cotton");
        ClothingProduct jeans = new ClothingProduct(202, "Denim Jeans", 59.99, 40, "M", "Denim");

        GroceryProduct milk = new GroceryProduct(301, "Organic Milk", 4.99, 100, "2026-08-15", true);
        GroceryProduct rice = new GroceryProduct(302, "Basmati Rice", 12.99, 200, "2027-01-01", false);

        // Display all products using polymorphism
        ProductInterface[] products = {laptop, phone, shirt, jeans, milk, rice};

        for (ProductInterface p : products) {
            System.out.println(p.getProductInfo());
            System.out.println("  Original Price: $" + String.format("%.2f", ((Product)p).price));
            System.out.println("  Discounted Price: $" + String.format("%.2f", p.calculateDiscountPrice()));
            System.out.println("  In Stock: " + (p.isAvailable() ? "Yes" : "No"));
            System.out.println();
        }

        System.out.println("=== KEY CONCEPTS ===");
        System.out.println("1. Inheritance: All products extend Product base class");
        System.out.println("2. Interface: All products implement ProductInterface");
        System.out.println("3. Polymorphism: ProductInterface reference for all product types");
        System.out.println("4. Method Overriding: calculateDiscountPrice() per category");
    }
}
