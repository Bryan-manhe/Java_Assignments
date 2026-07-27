/**
 * Assignment 11 - Exercise 1
 * Interface Printable implemented in Student and Employee classes.
 */

// Interface
interface Printable {
    void printDetails();
    String getFormattedData();
}

// Student class implementing Printable
class Student implements Printable {
    private int rollNumber;
    private String name;
    private String course;
    private double marks;

    public Student(int rollNumber, String name, String course, double marks) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.course = course;
        this.marks = marks;
    }

    @Override
    public void printDetails() {
        System.out.println("========== STUDENT RECORD ==========");
        System.out.println(getFormattedData());
        System.out.println("====================================\n");
    }

    @Override
    public String getFormattedData() {
        return "Roll No: " + rollNumber + "\n" +
               "Name: " + name + "\n" +
               "Course: " + course + "\n" +
               "Marks: " + marks + "\n" +
               "Grade: " + getGrade();
    }

    private String getGrade() {
        if (marks >= 90) return "A+";
        if (marks >= 80) return "A";
        if (marks >= 70) return "B";
        if (marks >= 60) return "C";
        if (marks >= 50) return "D";
        return "F";
    }
}

// Employee class implementing Printable
class Employee implements Printable {
    private int empId;
    private String name;
    private String department;
    private double salary;

    public Employee(int empId, String name, String department, double salary) {
        this.empId = empId;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public void printDetails() {
        System.out.println("========== EMPLOYEE RECORD =========");
        System.out.println(getFormattedData());
        System.out.println("====================================\n");
    }

    @Override
    public String getFormattedData() {
        return "Emp ID: " + empId + "\n" +
               "Name: " + name + "\n" +
               "Department: " + department + "\n" +
               "Salary: $" + String.format("%.2f", salary) + "\n" +
               "Annual Package: $" + String.format("%.2f", salary * 12);
    }
}

public class Exercise1 {
    public static void main(String[] args) {
        System.out.println("=== INTERFACE DEMONSTRATION - Printable ===\n");

        // Create objects that implement Printable
        Printable student = new Student(101, "Alice Johnson", "Computer Science", 85.5);
        Printable employee = new Employee(2001, "Bob Smith", "Engineering", 6500.00);

        // Using interface methods
        System.out.println("Calling printDetails() via Printable reference:");
        student.printDetails();
        employee.printDetails();

        // Demonstrating polymorphism with Printable array
        System.out.println("--- Polymorphism: Printable Array ---");
        Printable[] printables = {
            new Student(102, "Carol Davis", "Mathematics", 92.0),
            new Employee(2002, "David Wilson", "Marketing", 5500.00),
            new Student(103, "Eve Brown", "Physics", 78.0)
        };

        for (Printable p : printables) {
            System.out.println(p.getFormattedData());
            System.out.println("---");
        }

        System.out.println("\n=== KEY CONCEPTS ===");
        System.out.println("1. Printable interface with abstract methods");
        System.out.println("2. Student and Employee implement Printable");
        System.out.println("3. Polymorphism via Printable reference");
    }
}
