/**
 * Assignment 19 - Exercise 2
 * Display product details using SELECT query with JDBC.
 *
 * Uses H2 database (MySQL compatible mode).
 * SQL script to create the table is in products.sql
 */

import java.sql.*;
import java.util.Scanner;

public class Exercise2 {

    private static final String DB_URL = "jdbc:h2:~/productdb;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== PRODUCT DETAILS - DATABASE SELECT DEMO ===\n");

        try {
            Class.forName("org.h2.Driver");

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 Statement stmt = conn.createStatement()) {

                System.out.println("Connected to database successfully!\n");

                // Create table
                stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS products (" +
                    "product_id INT PRIMARY KEY, " +
                    "product_name VARCHAR(100), " +
                    "category VARCHAR(50), " +
                    "quantity INT, " +
                    "price DOUBLE)"
                );

                // Insert sample data
                ResultSet countRs = stmt.executeQuery("SELECT COUNT(*) FROM products");
                countRs.next();
                if (countRs.getInt(1) == 0) {
                    stmt.executeUpdate("INSERT INTO products VALUES (201, 'Laptop Pro', 'Electronics', 25, 1299.99)");
                    stmt.executeUpdate("INSERT INTO products VALUES (202, 'Wireless Mouse', 'Electronics', 100, 29.99)");
                    stmt.executeUpdate("INSERT INTO products VALUES (203, 'Cotton T-Shirt', 'Clothing', 150, 19.99)");
                    stmt.executeUpdate("INSERT INTO products VALUES (204, 'Denim Jeans', 'Clothing', 80, 49.99)");
                    stmt.executeUpdate("INSERT INTO products VALUES (205, 'Organic Milk', 'Grocery', 200, 4.99)");
                    stmt.executeUpdate("INSERT INTO products VALUES (206, 'Basmati Rice', 'Grocery', 300, 12.99)");
                    stmt.executeUpdate("INSERT INTO products VALUES (207, 'Bluetooth Speaker', 'Electronics', 60, 79.99)");
                    stmt.executeUpdate("INSERT INTO products VALUES (208, 'Running Shoes', 'Clothing', 45, 89.99)");
                    System.out.println("Sample product records inserted.\n");
                }

                // --- ALL PRODUCTS ---
                System.out.println("--- All Products (SELECT * FROM products) ---");
                System.out.println(String.format("%-12s %-22s %-15s %-10s %-10s",
                    "Prod ID", "Product Name", "Category", "Qty", "Price"));
                System.out.println("----------------------------------------------------------------");

                ResultSet rs = stmt.executeQuery("SELECT * FROM products ORDER BY product_id");
                while (rs.next()) {
                    System.out.println(String.format("%-12d %-22s %-15s %-10d $%-9.2f",
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("category"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")));
                }
                rs.close();

                // --- PRODUCTS BY CATEGORY ---
                System.out.println("\n--- Products by Category ---");
                Statement catStmt = conn.createStatement();
                rs = catStmt.executeQuery("SELECT DISTINCT category FROM products ORDER BY category");
                while (rs.next()) {
                    String category = rs.getString("category");
                    System.out.println("\nCategory: " + category);

                    Statement prodStmt = conn.createStatement();
                    ResultSet catRs = prodStmt.executeQuery(
                        "SELECT * FROM products WHERE category = '" + category + "' ORDER BY product_id"
                    );
                    System.out.println(String.format("  %-12s %-22s %-10s %-10s", "ID", "Name", "Qty", "Price"));
                    while (catRs.next()) {
                        System.out.println(String.format("  %-12d %-22s %-10d $%-9.2f",
                            catRs.getInt("product_id"),
                            catRs.getString("product_name"),
                            catRs.getInt("quantity"),
                            catRs.getDouble("price")));
                    }
                    catRs.close();
                    prodStmt.close();
                }
                rs.close();
                catStmt.close();

                // --- LOW STOCK ALERT ---
                System.out.println("\n--- Low Stock Products (quantity < 50) ---");
                rs = stmt.executeQuery("SELECT * FROM products WHERE quantity < 50 ORDER BY quantity");
                System.out.println(String.format("%-12s %-22s %-10s", "ID", "Name", "Qty"));
                System.out.println("------------------------------------------");

                while (rs.next()) {
                    System.out.println(String.format("%-12d %-22s %-10d",
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getInt("quantity")));
                }
                rs.close();

                // --- SUMMARY ---
                System.out.println("\n--- Summary Statistics ---");
                rs = stmt.executeQuery(
                    "SELECT category, COUNT(*) as count, SUM(quantity) as total_qty, " +
                    "MIN(price) as min_price, MAX(price) as max_price " +
                    "FROM products GROUP BY category"
                );
                System.out.println(String.format("%-15s %-8s %-12s %-12s %-12s",
                    "Category", "Items", "Total Qty", "Min Price", "Max Price"));
                System.out.println("------------------------------------------------------------");

                while (rs.next()) {
                    System.out.println(String.format("%-15s %-8d %-12d $%-11.2f $%-11.2f",
                        rs.getString("category"),
                        rs.getInt("count"),
                        rs.getInt("total_qty"),
                        rs.getDouble("min_price"),
                        rs.getDouble("max_price")));
                }
                rs.close();

                countRs = stmt.executeQuery("SELECT COUNT(*) FROM products");
                countRs.next();
                System.out.println("\nTotal products: " + countRs.getInt(1));

            } catch (SQLException e) {
                System.out.println("Database Error: " + e.getMessage());
            }

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
            System.out.println("Make sure H2 database JAR is on the classpath.");
            System.out.println("Compile: javac -cp ../h2.jar;. Exercise2.java");
            System.out.println("Run:     java -cp ../h2.jar;. Exercise2");
        }

        scanner.close();
    }
}
