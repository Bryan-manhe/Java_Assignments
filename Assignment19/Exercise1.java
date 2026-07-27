/**
 * Assignment 19 - Exercise 1
 * Display student records from database using SELECT query.
 *
 * Uses H2 database (MySQL compatible mode).
 * SQL script to create the table is in students.sql
 */

import java.sql.*;
import java.util.Scanner;

public class Exercise1 {

    // Database connection URL (H2 in MySQL compatibility mode)
    private static final String DB_URL = "jdbc:h2:~/studentdb;MODE=MySQL;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== STUDENT RECORDS - DATABASE SELECT DEMO ===\n");

        try {
            // Load H2 driver
            Class.forName("org.h2.Driver");

            // Establish connection
            try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                 Statement stmt = conn.createStatement()) {

                System.out.println("Connected to database successfully!\n");

                // Create table if not exists
                stmt.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS students (" +
                    "roll_number INT PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "course VARCHAR(50), " +
                    "semester INT, " +
                    "marks DOUBLE)"
                );

                // Insert sample data (only if table is empty)
                ResultSet countRs = stmt.executeQuery("SELECT COUNT(*) FROM students");
                countRs.next();
                if (countRs.getInt(1) == 0) {
                    stmt.executeUpdate("INSERT INTO students VALUES (101, 'Alice Johnson', 'Computer Science', 4, 88.5)");
                    stmt.executeUpdate("INSERT INTO students VALUES (102, 'Bob Smith', 'Mathematics', 2, 75.0)");
                    stmt.executeUpdate("INSERT INTO students VALUES (103, 'Carol Davis', 'Physics', 6, 92.0)");
                    stmt.executeUpdate("INSERT INTO students VALUES (104, 'David Wilson', 'Chemistry', 3, 81.5)");
                    stmt.executeUpdate("INSERT INTO students VALUES (105, 'Eve Brown', 'Biology', 5, 79.0)");
                    stmt.executeUpdate("INSERT INTO students VALUES (106, 'Frank Miller', 'Computer Science', 2, 95.5)");
                    stmt.executeUpdate("INSERT INTO students VALUES (107, 'Grace Lee', 'Mathematics', 4, 68.0)");
                    System.out.println("Sample student records inserted.\n");
                }

                // --- SELECT ALL RECORDS ---
                System.out.println("--- SELECT * FROM students ---");
                ResultSet rs = stmt.executeQuery("SELECT * FROM students ORDER BY roll_number");

                // Display column headers
                System.out.println(String.format("%-12s %-20s %-20s %-10s %-8s",
                    "Roll No", "Name", "Course", "Semester", "Marks"));
                System.out.println("--------------------------------------------------------------");

                while (rs.next()) {
                    int rollNo = rs.getInt("roll_number");
                    String name = rs.getString("name");
                    String course = rs.getString("course");
                    int semester = rs.getInt("semester");
                    double marks = rs.getDouble("marks");

                    System.out.println(String.format("%-12d %-20s %-20s %-10d %-8.1f",
                        rollNo, name, course, semester, marks));
                }
                rs.close();

                // --- SELECT WITH CONDITION ---
                System.out.println("\n--- Students with marks > 80 ---");
                rs = stmt.executeQuery("SELECT * FROM students WHERE marks > 80 ORDER BY marks DESC");
                System.out.println(String.format("%-12s %-20s %-10s", "Roll No", "Name", "Marks"));
                System.out.println("------------------------------------------");

                while (rs.next()) {
                    System.out.println(String.format("%-12d %-20s %-10.1f",
                        rs.getInt("roll_number"), rs.getString("name"), rs.getDouble("marks")));
                }
                rs.close();

                // --- SELECT WITH AGGREGATE ---
                System.out.println("\n--- Aggregate Functions ---");
                rs = stmt.executeQuery(
                    "SELECT course, COUNT(*) as count, AVG(marks) as avg_marks " +
                    "FROM students GROUP BY course"
                );
                System.out.println(String.format("%-22s %-10s %-10s", "Course", "Students", "Avg Marks"));
                System.out.println("------------------------------------------");

                while (rs.next()) {
                    System.out.println(String.format("%-22s %-10d %-10.1f",
                        rs.getString("course"), rs.getInt("count"), rs.getDouble("avg_marks")));
                }
                rs.close();

                // Display total record count
                countRs = stmt.executeQuery("SELECT COUNT(*) as total FROM students");
                countRs.next();
                System.out.println("\nTotal student records: " + countRs.getInt("total"));

            } catch (SQLException e) {
                System.out.println("Database Error: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found: " + e.getMessage());
            System.out.println("Make sure H2 database JAR is on the classpath.");
            System.out.println("Compile & run with: javac -cp ../h2.jar;. Exercise1.java");
            System.out.println("               and: java -cp ../h2.jar;. Exercise1");
        }

        scanner.close();
    }
}
