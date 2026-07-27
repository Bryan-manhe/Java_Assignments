/**
 * Assignment 08 - Exercise 1
 * Employee and Manager program where Manager accesses
 * Employee details using super keyword.
 */

// Base class
class Employee {
    protected int empId;
    protected String name;
    protected String department;
    protected double basicSalary;

    public Employee(int empId, String name, String department, double basicSalary) {
        this.empId = empId;
        this.name = name;
        this.department = department;
        this.basicSalary = basicSalary;
    }

    public void displayDetails() {
        System.out.println("Employee ID: " + empId);
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("Basic Salary: $" + String.format("%.2f", basicSalary));
    }

    public double calculateSalary() {
        return basicSalary;
    }
}

// Manager subclass using super
class Manager extends Employee {
    private double bonusPercentage;
    private int teamSize;

    public Manager(int empId, String name, String department, double basicSalary,
                   double bonusPercentage, int teamSize) {
        super(empId, name, department, basicSalary); // Calling parent constructor
        this.bonusPercentage = bonusPercentage;
        this.teamSize = teamSize;
    }

    @Override
    public void displayDetails() {
        super.displayDetails(); // Calling parent method
        System.out.println("Bonus Percentage: " + bonusPercentage + "%");
        System.out.println("Team Size: " + teamSize + " members");
    }

    @Override
    public double calculateSalary() {
        // Using super to access parent's basicSalary
        double bonus = super.basicSalary * (bonusPercentage / 100.0);
        return super.calculateSalary() + bonus;
    }

    public void showSuperUsage() {
        System.out.println("\n--- Super Keyword Usage in Manager ---");
        System.out.println("1. super(empId, name, department, basicSalary) -> Constructor");
        System.out.println("2. super.displayDetails() -> Called parent method");
        System.out.println("3. super.basicSalary -> Accessed parent field");
        System.out.println("4. super.calculateSalary() -> Called parent method");
    }
}

public class Exercise1 {
    public static void main(String[] args) {
        System.out.println("=== EMPLOYEE & MANAGER - SUPER KEYWORD DEMO ===\n");

        // Create Employee
        System.out.println("--- Employee Details ---");
        Employee emp = new Employee(1001, "Alice Johnson", "IT", 50000);
        emp.displayDetails();
        System.out.println("Salary: $" + String.format("%.2f", emp.calculateSalary()));

        System.out.println("\n--- Manager Details ---");
        Manager mgr = new Manager(2001, "Bob Smith", "IT", 70000, 15, 8);
        mgr.displayDetails();
        System.out.println("Total Salary (with bonus): $" + String.format("%.2f", mgr.calculateSalary()));

        mgr.showSuperUsage();

        System.out.println("\n=== KEY CONCEPTS ===");
        System.out.println("1. super() calls the parent class constructor");
        System.out.println("2. super.method() calls the parent class method");
        System.out.println("3. super.field accesses the parent class field");
    }
}
