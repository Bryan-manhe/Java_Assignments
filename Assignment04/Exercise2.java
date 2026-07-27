/**
 * Assignment 04 - Exercise 2
 * Employee Payroll System that accepts employee IDs, basic salary,
 * and bonus amounts from the user. Converts entered values into wrapper
 * objects and performs validation operations to ensure valid salary
 * values before calculating the net salary.
 */

import java.util.Scanner;

public class Exercise2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== EMPLOYEE PAYROLL SYSTEM ===\n");

        // Input employee details as String
        System.out.print("Enter Employee ID: ");
        String empIdStr = scanner.nextLine();

        System.out.print("Enter Employee Name: ");
        String empName = scanner.nextLine();

        System.out.print("Enter Basic Salary: ");
        String basicSalaryStr = scanner.nextLine();

        System.out.print("Enter Bonus Amount: ");
        String bonusStr = scanner.nextLine();

        System.out.print("Enter Tax Rate (%): ");
        String taxRateStr = scanner.nextLine();

        System.out.println("\n--- Processing Payroll ---");

        // Convert String values to wrapper objects with validation
        try {
            // Using wrapper classes for conversion
            Integer employeeId = Integer.valueOf(empIdStr);       // String -> Integer object
            Double basicSalary = Double.valueOf(basicSalaryStr);  // String -> Double object
            Double bonus = Double.valueOf(bonusStr);              // String -> Double object
            Double taxRate = Double.valueOf(taxRateStr);          // String -> Double object

            // Validation using wrapper class methods
            System.out.println("\n--- Validation Results ---");

            if (employeeId <= 0) {
                System.out.println("ERROR: Employee ID must be positive!");
                return;
            } else {
                System.out.println("✓ Employee ID " + employeeId + " is valid");
            }

            if (basicSalary <= 0) {
                System.out.println("ERROR: Basic salary must be greater than zero!");
                return;
            } else if (basicSalary < 10000) {
                System.out.println("⚠ Warning: Basic salary is below minimum wage threshold");
            } else {
                System.out.println("✓ Basic salary $" + String.format("%.2f", basicSalary) + " is valid");
            }

            if (bonus < 0) {
                System.out.println("ERROR: Bonus cannot be negative!");
                return;
            } else {
                System.out.println("✓ Bonus $" + String.format("%.2f", bonus) + " is valid");
            }

            if (taxRate < 0 || taxRate > 50) {
                System.out.println("ERROR: Tax rate must be between 0 and 50!");
                return;
            } else {
                System.out.println("✓ Tax rate " + taxRate + "% is valid");
            }

            // Calculate payroll
            System.out.println("\n--- Payroll Calculation ---");

            // Auto-unboxing: wrapper objects to primitives for calculation
            double grossSalary = basicSalary + bonus;
            double taxAmount = grossSalary * (taxRate / 100.0);
            double netSalary = grossSalary - taxAmount;

            // Autoboxing: primitives back to wrapper objects for display
            Double grossSalaryObj = Double.valueOf(grossSalary);
            Double taxAmountObj = Double.valueOf(taxAmount);
            Double netSalaryObj = Double.valueOf(netSalary);

            // Display payroll details
            System.out.println("Employee: " + empName + " (ID: " + employeeId + ")");
            System.out.println("Basic Salary: $" + String.format("%.2f", basicSalary));
            System.out.println("Bonus: $" + String.format("%.2f", bonus));
            System.out.println("Gross Salary: $" + String.format("%.2f", grossSalaryObj));
            System.out.println("Tax (" + taxRate + "%): $" + String.format("%.2f", taxAmountObj));
            System.out.println("Net Salary: $" + String.format("%.2f", netSalaryObj));

            // Additional wrapper class features
            System.out.println("\n--- Wrapper Class Features Used ---");
            System.out.println("Integer.valueOf(): \"" + empIdStr + "\" -> Integer " + employeeId);
            System.out.println("Double.valueOf(): \"" + basicSalaryStr + "\" -> Double " + basicSalary);
            System.out.println("Double comparison: basicSalary > 0 is " + (basicSalary.compareTo(0.0) > 0));
            System.out.println("Double.isNaN() check: " + Double.isNaN(netSalary));
            System.out.println("Double.isInfinite() check: " + Double.isInfinite(netSalary));

        } catch (NumberFormatException e) {
            System.out.println("\nERROR: Invalid number format entered. Please enter numeric values only.");
            System.out.println("Details: " + e.getMessage());
        }

        scanner.close();
    }
}
