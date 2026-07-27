/**
 * Assignment 23 - Exercise 2
 * Retrieve employee records from a database and display them
 * one by one using ResultSet. Shows employee ID, name, department, and salary.
 */

import java.sql.*;
import java.util.Scanner;

public class Exercise2 {

    private static final String DB_URL = "jdbc:h2:~/employeersdb;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("  EMPLOYEE RECORDS - RESULTSET NAVIGATION");
        System.out.println("========================================\n");

        Connection conn = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();

            // Create employee table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS employees ("
                    + "emp_id INT PRIMARY KEY, "
                    + "name VARCHAR(100), "
                    + "department VARCHAR(50), "
                    + "salary DOUBLE)" );

            // Insert sample data
            ResultSet countRs = stmt.executeQuery("SELECT COUNT(*) FROM employees");
            countRs.next();
            if (countRs.getInt(1) == 0) {
                PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO employees VALUES (?, ?, ?, ?)");
                ps.setInt(1, 101); ps.setString(2, "Alice Johnson");
                ps.setString(3, "Engineering"); ps.setDouble(4, 75000);
                ps.executeUpdate();

                ps.setInt(1, 102); ps.setString(2, "Bob Smith");
                ps.setString(3, "Marketing"); ps.setDouble(4, 65000);
                ps.executeUpdate();

                ps.setInt(1, 103); ps.setString(2, "Carol Davis");
                ps.setString(3, "Finance"); ps.setDouble(4, 70000);
                ps.executeUpdate();

                ps.setInt(1, 104); ps.setString(2, "David Wilson");
                ps.setString(3, "IT"); ps.setDouble(4, 80000);
                ps.executeUpdate();

                ps.setInt(1, 105); ps.setString(2, "Eve Brown");
                ps.setString(3, "HR"); ps.setDouble(4, 55000);
                ps.executeUpdate();

                ps.setInt(1, 106); ps.setString(2, "Frank Miller");
                ps.setString(3, "Engineering"); ps.setDouble(4, 82000);
                ps.executeUpdate();

                ps.close();
            }
            countRs.close();

            // Create scrollable ResultSet
            Statement scrollStmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            String query = "SELECT * FROM employees ORDER BY emp_id";
            ResultSet rs = scrollStmt.executeQuery(query);

            // Menu-driven navigation
            while (true) {
                System.out.println("--- NAVIGATION MENU ---");
                System.out.println("1. Show First Record");
                System.out.println("2. Show Last Record");
                System.out.println("3. Show Next Record");
                System.out.println("4. Show Previous Record");
                System.out.println("5. Go to Record by Position");
                System.out.println("6. Display All Records (One by One)");
                System.out.println("7. Exit");
                System.out.print("Enter choice: ");

                int choice;
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input.\n");
                    continue;
                }

                switch (choice) {
                    case 1:
                        if (rs.first()) {
                            displayRecord(rs, rs.getRow());
                        } else {
                            System.out.println("No records found.\n");
                        }
                        break;

                    case 2:
                        if (rs.last()) {
                            displayRecord(rs, rs.getRow());
                        } else {
                            System.out.println("No records found.\n");
                        }
                        break;

                    case 3:
                        if (rs.next()) {
                            displayRecord(rs, rs.getRow());
                        } else {
                            System.out.println("Already at the last record.\n");
                            // Move back to last
                            rs.last();
                        }
                        break;

                    case 4:
                        if (rs.previous()) {
                            displayRecord(rs, rs.getRow());
                        } else {
                            System.out.println("Already at the first record.\n");
                            rs.first();
                        }
                        break;

                    case 5:
                        System.out.print("Enter position (1 to " + getRowCount(rs) + "): ");
                        int pos = Integer.parseInt(scanner.nextLine());
                        if (rs.absolute(pos)) {
                            displayRecord(rs, rs.getRow());
                        } else {
                            System.out.println("Invalid position.\n");
                        }
                        break;

                    case 6:
                        System.out.println("\n--- All Employee Records ---");
                        rs.beforeFirst();
                        int num = 0;
                        while (rs.next()) {
                            num++;
                            System.out.println("   ===== Employee " + num + " =====");
                            System.out.println("   Employee ID  : " + rs.getInt("emp_id"));
                            System.out.println("   Name         : " + rs.getString("name"));
                            System.out.println("   Department   : " + rs.getString("department"));
                            System.out.println("   Salary       : $" + String.format("%.2f", rs.getDouble("salary")));
                            System.out.println("   ---------------------------");
                        }
                        System.out.println("   Total employees: " + num + "\n");
                        break;

                    case 7:
                        System.out.println("Exiting. Goodbye!");
                        rs.close();
                        scrollStmt.close();
                        stmt.close();
                        scanner.close();
                        return;

                    default:
                        System.out.println("Invalid choice.\n");
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Error: JDBC Driver not found.");
        } catch (SQLException e) {
            System.out.println("Database Error: " + e.getMessage());
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) { }
            scanner.close();
        }
    }

    private static void displayRecord(ResultSet rs, int position) throws SQLException {
        System.out.println("\n   ===== Record " + position + " =====");
        System.out.println("   Employee ID  : " + rs.getInt("emp_id"));
        System.out.println("   Name         : " + rs.getString("name"));
        System.out.println("   Department   : " + rs.getString("department"));
        System.out.println("   Salary       : $" + String.format("%.2f", rs.getDouble("salary")));
        System.out.println("   --------------------------\n");
    }

    private static int getRowCount(ResultSet rs) throws SQLException {
        int current = rs.getRow();
        rs.last();
        int count = rs.getRow();
        if (current == 0) {
            rs.beforeFirst();
        } else {
            rs.absolute(current);
        }
        return count;
    }
}
