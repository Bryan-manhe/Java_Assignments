/**
 * Assignment 20 - Exercise 1
 * Employee Records Management using JDBC CRUD Operations
 * (Create, Read, Update, Delete)
 */

import java.sql.*;
import java.util.Scanner;

public class Exercise1 {

    private static final String DB_URL = "jdbc:h2:~/employeedb;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== EMPLOYEE CRUD OPERATIONS ===\n");

        try {
            Class.forName("org.h2.Driver");

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 Statement stmt = conn.createStatement()) {

                System.out.println("Connected to database successfully!\n");

                // Create employee table
                stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS employees (" +
                    "emp_id INT PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "department VARCHAR(50), " +
                    "designation VARCHAR(50), " +
                    "salary DOUBLE)"
                );
                System.out.println("Table 'employees' ready.\n");

                // Interactive CRUD menu
                while (true) {
                    System.out.println("--- CRUD MENU ---");
                    System.out.println("1. CREATE (Add Employee)");
                    System.out.println("2. READ (View Employees)");
                    System.out.println("3. UPDATE (Modify Employee)");
                    System.out.println("4. DELETE (Remove Employee)");
                    System.out.println("5. Exit");
                    System.out.print("Enter choice: ");

                    int choice;
                    try {
                        choice = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input!\n");
                        continue;
                    }

                    switch (choice) {
                        case 1: // CREATE
                            System.out.println("\n--- Add New Employee ---");
                            System.out.print("Enter Employee ID: ");
                            int eid = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter Name: ");
                            String ename = scanner.nextLine();
                            System.out.print("Enter Department: ");
                            String dept = scanner.nextLine();
                            System.out.print("Enter Designation: ");
                            String desig = scanner.nextLine();
                            System.out.print("Enter Salary: ");
                            double sal = Double.parseDouble(scanner.nextLine());

                            String insertSQL = "INSERT INTO employees VALUES (?, ?, ?, ?, ?)";
                            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
                                pstmt.setInt(1, eid);
                                pstmt.setString(2, ename);
                                pstmt.setString(3, dept);
                                pstmt.setString(4, desig);
                                pstmt.setDouble(5, sal);
                                int rows = pstmt.executeUpdate();
                                System.out.println(rows + " employee(s) added successfully!\n");
                            }
                            break;

                        case 2: // READ
                            System.out.println("\n--- View Employees ---");
                            ResultSet rs = stmt.executeQuery("SELECT * FROM employees ORDER BY emp_id");

                            System.out.println(String.format("%-8s %-20s %-15s %-15s %-10s",
                                "Emp ID", "Name", "Department", "Designation", "Salary"));
                            System.out.println("------------------------------------------------------------------------");

                            boolean hasRecords = false;
                            while (rs.next()) {
                                hasRecords = true;
                                System.out.println(String.format("%-8d %-20s %-15s %-15s $%-9.2f",
                                    rs.getInt("emp_id"), rs.getString("name"),
                                    rs.getString("department"), rs.getString("designation"),
                                    rs.getDouble("salary")));
                            }
                            rs.close();
                            if (!hasRecords) {
                                System.out.println("No employees found.");
                            }
                            System.out.println();
                            break;

                        case 3: // UPDATE
                            System.out.println("\n--- Update Employee ---");
                            System.out.print("Enter Employee ID to update: ");
                            int updateId = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter new Department: ");
                            String newDept = scanner.nextLine();
                            System.out.print("Enter new Designation: ");
                            String newDesig = scanner.nextLine();
                            System.out.print("Enter new Salary: ");
                            double newSal = Double.parseDouble(scanner.nextLine());

                            String updateSQL = "UPDATE employees SET department=?, designation=?, salary=? WHERE emp_id=?";
                            try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
                                pstmt.setString(1, newDept);
                                pstmt.setString(2, newDesig);
                                pstmt.setDouble(3, newSal);
                                pstmt.setInt(4, updateId);
                                int rows = pstmt.executeUpdate();
                                System.out.println(rows + " employee(s) updated!\n");
                            }
                            break;

                        case 4: // DELETE
                            System.out.println("\n--- Delete Employee ---");
                            System.out.print("Enter Employee ID to delete: ");
                            int deleteId = Integer.parseInt(scanner.nextLine());

                            String deleteSQL = "DELETE FROM employees WHERE emp_id=?";
                            try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
                                pstmt.setInt(1, deleteId);
                                int rows = pstmt.executeUpdate();
                                System.out.println(rows + " employee(s) deleted!\n");
                            }
                            break;

                        case 5:
                            System.out.println("Exiting Employee Management System. Goodbye!");
                            System.out.println("\n=== CRUD OPERATIONS DEMONSTRATED ===");
                            System.out.println("1. CREATE - INSERT INTO employees");
                            System.out.println("2. READ   - SELECT * FROM employees");
                            System.out.println("3. UPDATE - UPDATE employees SET ...");
                            System.out.println("4. DELETE - DELETE FROM employees");
                            System.out.println("5. PreparedStatement for parameterized queries");
                            scanner.close();
                            return;

                        default:
                            System.out.println("Invalid choice!\n");
                    }
                }

            } catch (SQLException e) {
                System.out.println("Database Error: " + e.getMessage());
            }

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
            System.out.println("Make sure H2 database JAR is on the classpath.");
        }
    }
}
