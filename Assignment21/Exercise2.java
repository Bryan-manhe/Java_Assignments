/**
 * Assignment 21 - Exercise 2
 * Connect with a database and display the connection status.
 * After successful connection, show "Student database is connected successfully."
 * Demonstrates DriverManager, Connection, and Statement.
 */

import java.sql.*;

public class Exercise2 {

    // Database URL for Student database
    private static final String DB_URL = "jdbc:h2:~/studentdb;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("  STUDENT DATABASE CONNECTION SYSTEM");
        System.out.println("========================================\n");

        Connection conn = null;
        Statement stmt = null;

        try {
            // Step 1: Load JDBC Driver via DriverManager
            System.out.print("[1/5] Loading JDBC driver... ");
            Class.forName("org.h2.Driver");
            System.out.println("COMPLETE");
            System.out.println("       Driver class loaded: org.h2.Driver\n");

            // Step 2: Define connection parameters
            System.out.print("[2/5] Preparing connection parameters... ");
            String url = DB_URL;
            String user = DB_USER;
            String password = DB_PASSWORD;
            System.out.println("COMPLETE");
            System.out.println("       Database URL : " + url);
            System.out.println("       User         : " + user);
            System.out.println("       Password     : " + (password.isEmpty() ? "[empty]" : "[hidden]"));
            System.out.println();

            // Step 3: Establish connection using DriverManager.getConnection()
            System.out.print("[3/5] Connecting to Student database... ");
            conn = DriverManager.getConnection(url, user, password);

            // Display connection status
            if (!conn.isClosed()) {
                System.out.println("COMPLETE");

                // THE KEY MESSAGE - Student database connected successfully
                System.out.println("\n       ========================================");
                System.out.println("       *** STUDENT DATABASE IS CONNECTED SUCCESSFULLY! ***");
                System.out.println("       ========================================");

                System.out.println("\n       Connection Object: " + conn.getClass().getName());
                System.out.println("       Database Product: " + conn.getMetaData().getDatabaseProductName());
                System.out.println("       Database Version: " + conn.getMetaData().getDatabaseProductVersion());
            }
            System.out.println();

            // Step 4: Create Statement and create Student table
            System.out.print("[4/5] Creating Statement object... ");
            stmt = conn.createStatement();
            System.out.println("COMPLETE");
            System.out.println("       Statement Object: " + stmt.getClass().getName());

            // Create a simple student table to verify functionality
            String createSQL = "CREATE TABLE IF NOT EXISTS student_info ("
                    + "student_id INT PRIMARY KEY, "
                    + "student_name VARCHAR(100), "
                    + "course VARCHAR(50))";
            stmt.executeUpdate(createSQL);
            System.out.println("       Table 'student_info' is ready for use.");
            System.out.println();

            // Step 5: Verify by inserting and retrieving a record
            System.out.print("[5/5] Verifying database operations... ");

            // Insert a sample record
            stmt.executeUpdate("INSERT INTO student_info VALUES (101, 'Alice Johnson', 'Computer Science')");
            stmt.executeUpdate("INSERT INTO student_info VALUES (102, 'Bob Smith', 'Mathematics')");

            // Retrieve and display
            ResultSet rs = stmt.executeQuery("SELECT * FROM student_info");
            System.out.println("COMPLETE");
            System.out.println("\n       Sample records in Student database:");
            System.out.println("       -----------------------------------------");
            System.out.println(String.format("       %-12s %-20s %s", "Student ID", "Name", "Course"));
            System.out.println("       -----------------------------------------");
            while (rs.next()) {
                System.out.println(String.format("       %-12d %-20s %s",
                        rs.getInt("student_id"),
                        rs.getString("student_name"),
                        rs.getString("course")));
            }
            rs.close();
            System.out.println("       -----------------------------------------\n");

            // Final success message
            System.out.println("========================================");
            System.out.println("  ✓ STUDENT DATABASE CONNECTED SUCCESSFULLY");
            System.out.println("  ✓ All database operations completed");
            System.out.println("========================================");

        } catch (ClassNotFoundException e) {
            System.out.println("FAILED");
            System.out.println("\nERROR: JDBC Driver not found.");
            System.out.println("Please ensure h2.jar is in the classpath.");
            System.out.println("Compile: javac -cp ../h2.jar;. Exercise2.java");
            System.out.println("Run:     java -cp ../h2.jar;. Exercise2");

        } catch (SQLException e) {
            System.out.println("FAILED");
            System.out.println("\nERROR: Could not connect to Student database.");
            System.out.println("SQL State : " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Message   : " + e.getMessage());

        } finally {
            // Close resources
            System.out.println("\n--- Closing Resources ---");
            try {
                if (stmt != null) {
                    stmt.close();
                    System.out.println("  Statement closed.");
                }
                if (conn != null) {
                    conn.close();
                    System.out.println("  Connection closed.");
                    System.out.println("  Connection closed? " + conn.isClosed());
                }
            } catch (SQLException e) {
                System.out.println("  Error closing: " + e.getMessage());
            }
            System.out.println("--- Done ---");
        }
    }
}
