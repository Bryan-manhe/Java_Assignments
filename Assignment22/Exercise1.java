/**
 * Assignment 22 - Exercise 1
 * Login application using PreparedStatement to check username and password.
 * Demonstrates PreparedStatement for secure query execution and SQL injection prevention.
 */

import java.sql.*;
import java.util.Scanner;

public class Exercise1 {

    private static final String DB_URL = "jdbc:h2:~/logindb;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("  LOGIN SYSTEM USING PreparedStatement");
        System.out.println("========================================\n");

        Connection conn = null;

        try {
            // Load driver and connect
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();

            // Create users table and insert sample users
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users ("
                    + "username VARCHAR(50) PRIMARY KEY, "
                    + "password VARCHAR(100), "
                    + "role VARCHAR(30))");

            ResultSet countRs = stmt.executeQuery("SELECT COUNT(*) FROM users");
            countRs.next();
            if (countRs.getInt(1) == 0) {
                PreparedStatement insertUser = conn.prepareStatement(
                        "INSERT INTO users VALUES (?, ?, ?)");

                insertUser.setString(1, "admin");
                insertUser.setString(2, "admin123");
                insertUser.setString(3, "Administrator");
                insertUser.executeUpdate();

                insertUser.setString(1, "student1");
                insertUser.setString(2, "pass123");
                insertUser.setString(3, "Student");
                insertUser.executeUpdate();

                insertUser.setString(1, "teacher");
                insertUser.setString(2, "teach456");
                insertUser.setString(3, "Teacher");
                insertUser.executeUpdate();

                insertUser.close();
                System.out.println("Sample users created.\n");
            }
            countRs.close();
            stmt.close();

            // --- LOGIN using PreparedStatement ---
            System.out.print("Enter Username: ");
            String username = scanner.nextLine();

            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            // Using PreparedStatement - prevents SQL injection
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            System.out.println("\nExecuting: " + sql.replace("?", "***"));
            System.out.println("Using PreparedStatement with parameterized values.\n");

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                System.out.println("========================================");
                System.out.println("  LOGIN SUCCESSFUL!");
                System.out.println("  Welcome, " + username + "!");
                System.out.println("  Role: " + role);
                System.out.println("========================================");
            } else {
                System.out.println("========================================");
                System.out.println("  LOGIN FAILED!");
                System.out.println("  Invalid username or password.");
                System.out.println("========================================");
            }

            rs.close();
            pstmt.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Error: JDBC Driver not found. " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
            scanner.close();
        }
    }
}
