/**
 * Assignment 23 - Exercise 1
 * Display database records one by one using ResultSet navigation methods.
 * Demonstrates next(), previous(), first(), last(), absolute(), relative().
 */

import java.sql.*;

public class Exercise1 {

    private static final String DB_URL = "jdbc:h2:~/recordsdb;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  RESULTSET NAVIGATION DEMONSTRATION");
        System.out.println("========================================\n");

        Connection conn = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();

            // Create table and insert sample data
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS books ("
                    + "book_id INT PRIMARY KEY, title VARCHAR(100), "
                    + "author VARCHAR(100), price DOUBLE, stock INT)");

            ResultSet countRs = stmt.executeQuery("SELECT COUNT(*) FROM books");
            countRs.next();
            if (countRs.getInt(1) == 0) {
                stmt.executeUpdate("INSERT INTO books VALUES (101, 'Java Programming', 'John Smith', 599.99, 20)");
                stmt.executeUpdate("INSERT INTO books VALUES (102, 'Python Basics', 'Jane Doe', 449.50, 15)");
                stmt.executeUpdate("INSERT INTO books VALUES (103, 'Data Structures', 'Bob Wilson', 699.00, 10)");
                stmt.executeUpdate("INSERT INTO books VALUES (104, 'Database Systems', 'Alice Brown', 549.99, 25)");
                stmt.executeUpdate("INSERT INTO books VALUES (105, 'Web Development', 'Tom Harris', 399.00, 30)");
            }
            countRs.close();

            // Create scrollable ResultSet
            String query = "SELECT * FROM books ORDER BY book_id";
            Statement scrollStmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = scrollStmt.executeQuery(query);

            System.out.println("--- 1. Forward Navigation (next()) ---");
            int count = 0;
            while (rs.next()) {
                count++;
                System.out.println("   Record " + count + ": " + rs.getInt("book_id")
                        + " | " + rs.getString("title")
                        + " | $" + rs.getDouble("price"));
            }
            System.out.println("   Total records: " + count + "\n");

            // --- BACKWARD NAVIGATION ---
            System.out.println("--- 2. Backward Navigation (previous()) ---");
            while (rs.previous()) {
                System.out.println("   " + rs.getInt("book_id") + " | "
                        + rs.getString("title"));
            }
            System.out.println();

            // --- FIRST and LAST ---
            System.out.println("--- 3. Direct Navigation (first() / last()) ---");
            rs.first();
            System.out.println("   First record: " + rs.getInt("book_id")
                    + " | " + rs.getString("title"));

            rs.last();
            System.out.println("   Last record : " + rs.getInt("book_id")
                    + " | " + rs.getString("title"));
            System.out.println("   Total rows  : " + rs.getRow() + "\n");

            // --- ABSOLUTE POSITIONING ---
            System.out.println("--- 4. Absolute Positioning (absolute(n)) ---");
            rs.absolute(3);
            System.out.println("   Record at position 3: " + rs.getInt("book_id")
                    + " | " + rs.getString("title") + " | $" + rs.getDouble("price"));

            rs.absolute(1);
            System.out.println("   Record at position 1: " + rs.getInt("book_id")
                    + " | " + rs.getString("title"));
            System.out.println();

            // --- RELATIVE POSITIONING ---
            System.out.println("--- 5. Relative Positioning (relative(n)) ---");
            System.out.println("   Current: " + rs.getInt("book_id") + " | " + rs.getString("title"));
            rs.relative(2);
            System.out.println("   After relative(2): " + rs.getInt("book_id") + " | " + rs.getString("title"));
            rs.relative(-1);
            System.out.println("   After relative(-1): " + rs.getInt("book_id") + " | " + rs.getString("title"));
            System.out.println();

            // --- DISPLAY ONE BY ONE ---
            System.out.println("--- 6. Displaying Records One by One ---");
            rs.beforeFirst();
            int recordNum = 0;
            while (rs.next()) {
                recordNum++;
                System.out.println("   === Record " + recordNum + " ===");
                System.out.println("   Book ID  : " + rs.getInt("book_id"));
                System.out.println("   Title    : " + rs.getString("title"));
                System.out.println("   Author   : " + rs.getString("author"));
                System.out.println("   Price    : $" + rs.getDouble("price"));
                System.out.println("   Stock    : " + rs.getInt("stock"));
                System.out.println("   ---------------------");
            }

            rs.close();
            scrollStmt.close();
            stmt.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Error: JDBC Driver not found.");
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { }
        }
    }
}
