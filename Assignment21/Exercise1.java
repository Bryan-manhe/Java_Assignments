/**
 * Assignment 21 - Exercise 1
 * Connect Java with database and display database connection status.
 * Demonstrates DriverManager, Connection, and Statement interfaces.
 */

import java.sql.*;

public class Exercise1 {

    // Database URL using H2 in MySQL compatibility mode
    private static final String DB_URL = "jdbc:h2:~/testdb;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("JDBC DATABASE CONNECTION DEMONSTRATION");
        System.out.println("========================================\n");

        Connection conn = null;
        Statement stmt = null;

        try {
            // Step 1: Load the JDBC driver
            System.out.println("1. Loading JDBC driver...");
            Class.forName("org.h2.Driver");
            System.out.println("   Driver loaded successfully: org.h2.Driver\n");

            // Step 2: Establish connection using DriverManager
            System.out.println("2. Establishing database connection...");
            System.out.println("   URL: " + DB_URL);
            System.out.println("   User: " + DB_USER);

            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            System.out.println("   Connection Status: SUCCESS\n");

            // Step 3: Display connection details
            System.out.println("3. Database Connection Details:");
            System.out.println("   Database Product: " + conn.getMetaData().getDatabaseProductName());
            System.out.println("   Database Version: " + conn.getMetaData().getDatabaseProductVersion());
            System.out.println("   Driver Name: " + conn.getMetaData().getDriverName());
            System.out.println("   Driver Version: " + conn.getMetaData().getDriverVersion());
            System.out.println("   Auto-commit Mode: " + conn.getAutoCommit());
            System.out.println("   Transaction Isolation: " + conn.getTransactionIsolation());
            System.out.println("   Connection is closed? " + conn.isClosed() + "\n");

            // Step 4: Create Statement and execute a test query
            System.out.println("4. Creating Statement and executing test query...");
            stmt = conn.createStatement();

            // Check Statement details
            System.out.println("   Statement created successfully");
            System.out.println("   Statement type: " + stmt.getClass().getName());
            System.out.println("   Max rows allowed: " + stmt.getMaxRows());
            System.out.println("   Query timeout: " + stmt.getQueryTimeout() + " seconds\n");

            // Step 5: Execute a simple query to verify the connection works
            System.out.println("5. Running test query (SELECT 1)...");
            ResultSet rs = stmt.executeQuery("SELECT 1 AS test_value");
            if (rs.next()) {
                System.out.println("   Query executed successfully. Test value: " + rs.getInt("test_value"));
            }
            rs.close();

            System.out.println("\n========================================");
            System.out.println("CONNECTION ESTABLISHED SUCCESSFULLY!");
            System.out.println("========================================");

        } catch (ClassNotFoundException e) {
            System.out.println("\nERROR: JDBC Driver not found!");
            System.out.println("Make sure H2 database JAR is on the classpath.");
            System.out.println("Compile: javac -cp ../h2.jar;. Exercise1.java");
            System.out.println("Run:     java -cp ../h2.jar;. Exercise1");
            System.out.println("Details: " + e.getMessage());

        } catch (SQLException e) {
            System.out.println("\nERROR: Database connection failed!");
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Message: " + e.getMessage());

        } finally {
            // Step 6: Close resources
            System.out.println("\n6. Closing resources...");
            try {
                if (stmt != null) {
                    stmt.close();
                    System.out.println("   Statement closed");
                }
                if (conn != null) {
                    conn.close();
                    System.out.println("   Connection closed");
                    System.out.println("   Connection is closed? " + conn.isClosed());
                }
            } catch (SQLException e) {
                System.out.println("   Error closing resources: " + e.getMessage());
            }
            System.out.println("\n=== JDBC DEMONSTRATION COMPLETE ===");
        }
    }
}
