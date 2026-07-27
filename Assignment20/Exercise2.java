/**
 * Assignment 20 - Exercise 2
 * Student Records Management using JDBC CRUD Operations
 * (Create, Read, Update, Delete on roll number, name, course, marks)
 */

import java.sql.*;
import java.util.Scanner;

public class Exercise2 {

    private static final String DB_URL = "jdbc:h2:~/studentcrud;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== STUDENT CRUD OPERATIONS ===\n");

        try {
            Class.forName("org.h2.Driver");

            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 Statement stmt = conn.createStatement()) {

                System.out.println("Connected to database successfully!\n");

                // Create student table
                stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS students (" +
                    "roll_number INT PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "course VARCHAR(50), " +
                    "marks DOUBLE)"
                );
                System.out.println("Table 'students' ready.\n");

                // Insert some sample data initially
                ResultSet countRs = stmt.executeQuery("SELECT COUNT(*) FROM students");
                countRs.next();
                if (countRs.getInt(1) == 0) {
                    System.out.println("Adding sample student records...");
                    PreparedStatement initPstmt = conn.prepareStatement(
                        "INSERT INTO students VALUES (?, ?, ?, ?)");
                    initPstmt.setInt(1, 101); initPstmt.setString(2, "Alice Johnson");
                    initPstmt.setString(3, "Computer Science"); initPstmt.setDouble(4, 88.5);
                    initPstmt.executeUpdate();
                    initPstmt.setInt(1, 102); initPstmt.setString(2, "Bob Smith");
                    initPstmt.setString(3, "Mathematics"); initPstmt.setDouble(4, 75.0);
                    initPstmt.executeUpdate();
                    initPstmt.setInt(1, 103); initPstmt.setString(2, "Carol Davis");
                    initPstmt.setString(3, "Physics"); initPstmt.setDouble(4, 92.0);
                    initPstmt.executeUpdate();
                    initPstmt.close();
                    System.out.println("Sample records added.\n");
                }

                // Interactive CRUD menu
                while (true) {
                    System.out.println("--- CRUD MENU ---");
                    System.out.println("1. CREATE (Add Student)");
                    System.out.println("2. READ (View Students)");
                    System.out.println("3. UPDATE (Modify Student)");
                    System.out.println("4. DELETE (Remove Student)");
                    System.out.println("5. Search Student by Name");
                    System.out.println("6. Exit");
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
                            System.out.println("\n--- Add New Student ---");
                            System.out.print("Enter Roll Number: ");
                            int roll = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter Name: ");
                            String sname = scanner.nextLine();
                            System.out.print("Enter Course: ");
                            String course = scanner.nextLine();
                            System.out.print("Enter Marks: ");
                            double marks = Double.parseDouble(scanner.nextLine());

                            try (PreparedStatement pstmt = conn.prepareStatement(
                                    "INSERT INTO students VALUES (?, ?, ?, ?)")) {
                                pstmt.setInt(1, roll);
                                pstmt.setString(2, sname);
                                pstmt.setString(3, course);
                                pstmt.setDouble(4, marks);
                                int rows = pstmt.executeUpdate();
                                System.out.println(rows + " student(s) added successfully!\n");
                            } catch (SQLException e) {
                                if (e.getErrorCode() == 23505 || e.getMessage().contains("primary key")) {
                                    System.out.println("Error: Roll number " + roll + " already exists!\n");
                                } else {
                                    System.out.println("Error: " + e.getMessage() + "\n");
                                }
                            }
                            break;

                        case 2: // READ
                            System.out.println("\n--- View All Students ---");
                            ResultSet rs = stmt.executeQuery(
                                "SELECT *, CASE WHEN marks >= 90 THEN 'A+' " +
                                "WHEN marks >= 80 THEN 'A' WHEN marks >= 70 THEN 'B' " +
                                "WHEN marks >= 60 THEN 'C' WHEN marks >= 50 THEN 'D' " +
                                "ELSE 'F' END as grade FROM students ORDER BY roll_number");

                            System.out.println(String.format("%-12s %-20s %-20s %-8s %-6s",
                                "Roll No", "Name", "Course", "Marks", "Grade"));
                            System.out.println("------------------------------------------------------------");

                            boolean hasRecords = false;
                            while (rs.next()) {
                                hasRecords = true;
                                System.out.println(String.format("%-12d %-20s %-20s %-8.1f %-6s",
                                    rs.getInt("roll_number"), rs.getString("name"),
                                    rs.getString("course"), rs.getDouble("marks"),
                                    rs.getString("grade")));
                            }
                            rs.close();
                            if (!hasRecords) {
                                System.out.println("No student records found.");
                            }
                            System.out.println();
                            break;

                        case 3: // UPDATE
                            System.out.println("\n--- Update Student ---");
                            System.out.print("Enter Roll Number to update: ");
                            int updateRoll = Integer.parseInt(scanner.nextLine());
                            System.out.print("Enter new Name: ");
                            String newName = scanner.nextLine();
                            System.out.print("Enter new Course: ");
                            String newCourse = scanner.nextLine();
                            System.out.print("Enter new Marks: ");
                            double newMarks = Double.parseDouble(scanner.nextLine());

                            try (PreparedStatement pstmt = conn.prepareStatement(
                                    "UPDATE students SET name=?, course=?, marks=? WHERE roll_number=?")) {
                                pstmt.setString(1, newName);
                                pstmt.setString(2, newCourse);
                                pstmt.setDouble(3, newMarks);
                                pstmt.setInt(4, updateRoll);
                                int rows = pstmt.executeUpdate();
                                if (rows > 0) {
                                    System.out.println("Student updated successfully!\n");
                                } else {
                                    System.out.println("Student with roll number " + updateRoll + " not found.\n");
                                }
                            }
                            break;

                        case 4: // DELETE
                            System.out.println("\n--- Delete Student ---");
                            System.out.print("Enter Roll Number to delete: ");
                            int deleteRoll = Integer.parseInt(scanner.nextLine());

                            try (PreparedStatement pstmt = conn.prepareStatement(
                                    "DELETE FROM students WHERE roll_number=?")) {
                                pstmt.setInt(1, deleteRoll);
                                int rows = pstmt.executeUpdate();
                                if (rows > 0) {
                                    System.out.println("Student deleted successfully!\n");
                                } else {
                                    System.out.println("Student with roll number " + deleteRoll + " not found.\n");
                                }
                            }
                            break;

                        case 5: // SEARCH
                            System.out.println("\n--- Search Student ---");
                            System.out.print("Enter name to search: ");
                            String searchName = scanner.nextLine();

                            try (PreparedStatement pstmt = conn.prepareStatement(
                                    "SELECT * FROM students WHERE LOWER(name) LIKE ? ORDER BY roll_number")) {
                                pstmt.setString(1, "%" + searchName.toLowerCase() + "%");
                                rs = pstmt.executeQuery();

                                System.out.println(String.format("%-12s %-20s %-20s %-8s",
                                    "Roll No", "Name", "Course", "Marks"));
                                System.out.println("------------------------------------------------------");

                                boolean found = false;
                                while (rs.next()) {
                                    found = true;
                                    System.out.println(String.format("%-12d %-20s %-20s %-8.1f",
                                        rs.getInt("roll_number"), rs.getString("name"),
                                        rs.getString("course"), rs.getDouble("marks")));
                                }
                                rs.close();
                                if (!found) {
                                    System.out.println("No students found matching \"" + searchName + "\".");
                                }
                                System.out.println();
                            }
                            break;

                        case 6:
                            System.out.println("Exiting Student Management System. Goodbye!");
                            System.out.println("\n=== CRUD OPERATIONS DEMONSTRATED ===");
                            System.out.println("1. CREATE - INSERT INTO students");
                            System.out.println("2. READ   - SELECT * FROM students");
                            System.out.println("3. UPDATE - UPDATE students SET ...");
                            System.out.println("4. DELETE - DELETE FROM students");
                            System.out.println("5. SEARCH - SELECT with LIKE");
                            System.out.println("6. PreparedStatement prevents SQL injection");
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
