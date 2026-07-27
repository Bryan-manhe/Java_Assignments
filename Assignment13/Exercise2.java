/**
 * Assignment 13 - Exercise 2
 * Program to write patient details (patient ID, name, age, and diagnosis)
 * to a file and read the details from the file for display.
 */

import java.io.*;
import java.util.Scanner;

public class Exercise2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filename = "patients.txt";

        System.out.println("=== PATIENT RECORD FILE HANDLING SYSTEM ===\n");

        // --- Writing patient details ---
        System.out.println("--- Writing Patient Details to File ---");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("===== PATIENT RECORDS =====");
            writer.newLine();
            writer.write(String.format("%-12s %-20s %-5s %s", "PatientID", "Name", "Age", "Diagnosis"));
            writer.newLine();
            writer.write("----------------------------------------------------------");
            writer.newLine();

            // Pre-populated patient records
            String[][] patients = {
                {"P1001", "Alice Johnson", "35", "Hypertension"},
                {"P1002", "Bob Smith", "28", "Fractured Arm"},
                {"P1003", "Carol Davis", "45", "Diabetes Type 2"},
                {"P1004", "David Wilson", "60", "Heart Disease"},
                {"P1005", "Eve Brown", "22", "Viral Infection"}
            };

            for (String[] p : patients) {
                writer.write(String.format("%-12s %-20s %-5s %s", p[0], p[1], p[2], p[3]));
                writer.newLine();
            }

            System.out.println("Patient details written to '" + filename + "' successfully!\n");

            // Add custom patient
            System.out.print("Add a new patient? (yes/no): ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("yes")) {
                System.out.print("Enter Patient ID: ");
                String id = scanner.nextLine();
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                System.out.print("Enter Age: ");
                String age = scanner.nextLine();
                System.out.print("Enter Diagnosis: ");
                String diagnosis = scanner.nextLine();

                writer.write(String.format("%-12s %-20s %-5s %s", id, name, age, diagnosis));
                writer.newLine();
                System.out.println("Patient record added!\n");
            }

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

        // --- Reading patient details ---
        System.out.println("--- Reading Patient Details from File ---\n");
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Display file statistics
            File file = new File(filename);
            System.out.println("\n--- File Statistics ---");
            System.out.println("File name: " + file.getName());
            System.out.println("File size: " + file.length() + " bytes");
            System.out.println("Absolute path: " + file.getAbsolutePath());

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
            }
        } else {
            System.out.println("File '" + filename + "' preserved on disk.");
        }

        scanner.close();
    }
}
