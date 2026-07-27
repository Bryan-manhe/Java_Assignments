/**
 * Assignment 22 - Exercise 2
 * Hospital staff login authentication using PreparedStatement.
 * Authenticates doctor/nurse login with ID and password.
 * Displays appropriate access messages based on result.
 */

import java.sql.*;
import java.util.Scanner;

public class Exercise2 {

    private static final String DB_URL = "jdbc:h2:~/hospitaldb;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("  HOSPITAL STAFF LOGIN SYSTEM");
        System.out.println("  (PreparedStatement Authentication)");
        System.out.println("========================================\n");

        Connection conn = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();

            // Create hospital staff table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS hospital_staff ("
                    + "staff_id VARCHAR(20) PRIMARY KEY, "
                    + "name VARCHAR(100), "
                    + "password VARCHAR(100), "
                    + "role VARCHAR(30), "
                    + "department VARCHAR(50))");

            // Insert sample hospital staff
            ResultSet countRs = stmt.executeQuery("SELECT COUNT(*) FROM hospital_staff");
            countRs.next();
            if (countRs.getInt(1) == 0) {
                PreparedStatement insertStaff = conn.prepareStatement(
                        "INSERT INTO hospital_staff VALUES (?, ?, ?, ?, ?)");

                insertStaff.setString(1, "DOC001");
                insertStaff.setString(2, "Dr. Sarah Williams");
                insertStaff.setString(3, "docpass1");
                insertStaff.setString(4, "Doctor");
                insertStaff.setString(5, "Cardiology");
                insertStaff.executeUpdate();

                insertStaff.setString(1, "DOC002");
                insertStaff.setString(2, "Dr. James Miller");
                insertStaff.setString(3, "docpass2");
                insertStaff.setString(4, "Doctor");
                insertStaff.setString(5, "Neurology");
                insertStaff.executeUpdate();

                insertStaff.setString(1, "NUR001");
                insertStaff.setString(2, "Nurse Emily Davis");
                insertStaff.setString(3, "nursepass1");
                insertStaff.setString(4, "Nurse");
                insertStaff.setString(5, "General Ward");
                insertStaff.executeUpdate();

                insertStaff.setString(1, "NUR002");
                insertStaff.setString(2, "Nurse Robert Wilson");
                insertStaff.setString(3, "nursepass2");
                insertStaff.setString(4, "Nurse");
                insertStaff.setString(5, "ICU");
                insertStaff.executeUpdate();

                insertStaff.close();
                System.out.println("Sample hospital staff records created.\n");
            }
            countRs.close();
            stmt.close();

            // --- LOGIN using PreparedStatement ---
            System.out.print("Enter Staff ID: ");
            String staffId = scanner.nextLine();

            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            // Parameterized query using PreparedStatement - secure against SQL injection
            String sql = "SELECT * FROM hospital_staff WHERE staff_id = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, staffId);
            pstmt.setString(2, password);

            System.out.println("\nAuthenticating via PreparedStatement...");
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                String role = rs.getString("role");
                String department = rs.getString("department");

                System.out.println("\n========================================");
                System.out.println("  ✓ ACCESS GRANTED");
                System.out.println("========================================");
                System.out.println("  Staff ID   : " + staffId);
                System.out.println("  Name       : " + name);
                System.out.println("  Role       : " + role);
                System.out.println("  Department : " + department);

                // Role-specific access messages
                System.out.println("\n  ----- Access Level -----");
                if (role.equalsIgnoreCase("Doctor")) {
                    System.out.println("  You have full access to:");
                    System.out.println("  • Patient medical records");
                    System.out.println("  • Prescription management");
                    System.out.println("  • Lab test results");
                    System.out.println("  • Surgery schedules");
                } else if (role.equalsIgnoreCase("Nurse")) {
                    System.out.println("  You have access to:");
                    System.out.println("  • Patient vitals monitoring");
                    System.out.println("  • Medication administration");
                    System.out.println("  • Patient care notes");
                    System.out.println("  • Ward management");
                }
                System.out.println("========================================");
            } else {
                System.out.println("\n========================================");
                System.out.println("  ✗ ACCESS DENIED");
                System.out.println("========================================");
                System.out.println("  Invalid Staff ID or Password.");
                System.out.println("  Please check your credentials and try again.");
                System.out.println("========================================");
            }

            rs.close();
            pstmt.close();

            // Demonstrate PreparedStatement reusability
            System.out.println("\n--- PreparedStatement Reusability Demo ---");
            PreparedStatement searchStmt = conn.prepareStatement(
                    "SELECT * FROM hospital_staff WHERE department = ?");

            System.out.println("\nStaff in Cardiology:");
            searchStmt.setString(1, "Cardiology");
            ResultSet searchRs = searchStmt.executeQuery();
            while (searchRs.next()) {
                System.out.println("  " + searchRs.getString("staff_id") + " - "
                        + searchRs.getString("name") + " (" + searchRs.getString("role") + ")");
            }
            searchRs.close();

            System.out.println("\nStaff in ICU:");
            searchStmt.setString(1, "ICU");
            searchRs = searchStmt.executeQuery();
            while (searchRs.next()) {
                System.out.println("  " + searchRs.getString("staff_id") + " - "
                        + searchRs.getString("name") + " (" + searchRs.getString("role") + ")");
            }
            searchRs.close();
            searchStmt.close();

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
