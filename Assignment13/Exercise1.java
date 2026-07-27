/**
 * Assignment 13 - Exercise 1
 * Program to write and read employee details from a file.
 */

import java.io.*;
import java.util.Scanner;

public class Exercise1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filename = "employees.txt";

        System.out.println("=== EMPLOYEE FILE HANDLING SYSTEM ===\n");

        // --- Writing employee details to file ---
        System.out.println("--- Writing Employee Details to File ---");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Write header
            writer.write("===== EMPLOYEE RECORDS =====");
            writer.newLine();
            writer.write("ID,Name,Department,Salary");
            writer.newLine();
            writer.write("----------------------------------------");
            writer.newLine();

            // Write employee records
            writer.write("101,Alice Johnson,Engineering,75000.00");
            writer.newLine();
            writer.write("102,Bob Smith,Marketing,65000.00");
            writer.newLine();
            writer.write("103,Carol Davis,Finance,70000.00");
            writer.newLine();
            writer.write("104,David Wilson,IT,80000.00");
            writer.newLine();
            writer.write("105,Eve Brown,HR,55000.00");
            writer.newLine();

            System.out.println("Employee details written to '" + filename + "' successfully!\n");

            // Allow user to add custom entry
            System.out.print("Do you want to add a custom employee? (yes/no): ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("yes")) {
                System.out.print("Enter Employee ID: ");
                String id = scanner.nextLine();
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Department: ");
                String dept = scanner.nextLine();
                System.out.print("Enter Salary: ");
                String salary = scanner.nextLine();

                writer.write(id + "," + name + "," + dept + "," + salary);
                writer.newLine();
                System.out.println("Custom employee added!\n");
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        // --- Reading employee details from file ---
        System.out.println("--- Reading Employee Details from File ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int recordCount = 0;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                if (line.contains(",")) {
                    recordCount++;
                }
            }

            System.out.println("\nTotal employee records (excluding headers): " + (recordCount - 1));

            // Using File class to get additional info
            File file = new File(filename);
            System.out.println("File size: " + file.length() + " bytes");
            System.out.println("File path: " + file.getAbsolutePath());

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        // --- Cleanup ---
        System.out.print("\nDelete file? (yes/no): ");
        String deleteChoice = scanner.nextLine();
        if (deleteChoice.equalsIgnoreCase("yes")) {
            File file = new File(filename);
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Could not delete file.");
            }
        } else {
            System.out.println("File '" + filename + "' preserved on disk.");
        }

        scanner.close();
    }
}
