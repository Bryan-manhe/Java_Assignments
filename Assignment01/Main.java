/**
 * Assignment 01: Java Environment Setup, JDK/JRE/JVM, Variables, Methods & Constructors
 *
 * This program demonstrates:
 * - JDK, JRE, JVM concepts
 * - Different types of variables (local, instance, static)
 * - Methods (with/without return type, with/without parameters)
 * - Constructors (default, parameterized)
 *
 * Theory:
 * JDK (Java Development Kit) includes JRE and development tools for compiling Java code.
 * JRE (Java Runtime Environment) provides libraries and JVM for running Java applications.
 * JVM (Java Virtual Machine) executes Java bytecode, providing platform independence.
 */
public class Main {

    // Static variable (class-level, shared across all instances)
    static String courseName = "Computer Science";

    // Instance variables
    String studentName;
    int studentAge;

    // Default constructor
    public Main() {
        this.studentName = "Unknown";
        this.studentAge = 0;
    }

    // Parameterized constructor
    public Main(String name, int age) {
        this.studentName = name;
        this.studentAge = age;
    }

    // Method with return type and parameters
    public int calculateYearOfBirth(int currentYear) {
        return currentYear - this.studentAge;
    }

    // Method without return type (void)
    public void displayStudentInfo() {
        // Local variable (exists only within this method)
        String message = "Student Information:";
        System.out.println(message);
        System.out.println("Name: " + this.studentName);
        System.out.println("Age: " + this.studentAge);
        System.out.println("Course: " + courseName);
        System.out.println("----------------------------");
    }

    // Static method (belongs to class, not instance)
    public static void showJVMInfo() {
        System.out.println("--- JVM Information ---");
        System.out.println("Java Version: " + System.getProperty("java.version"));
        System.out.println("Java Home: " + System.getProperty("java.home"));
        System.out.println("JVM Name: " + System.getProperty("java.vm.name"));
        System.out.println("JVM Version: " + System.getProperty("java.vm.version"));
        System.out.println("========================\n");
    }

    public static void main(String[] args) {
        // Displaying JDK/JRE/JVM concepts
        System.out.println("========================================");
        System.out.println("ASSIGNMENT 01: JAVA FUNDAMENTALS");
        System.out.println("========================================\n");

        // JVM Information
        showJVMInfo();

        // Creating objects using constructors
        System.out.println("--- Default Constructor ---");
        Main student1 = new Main();
        student1.displayStudentInfo();

        System.out.println("--- Parameterized Constructor ---");
        Main student2 = new Main("Alice Johnson", 20);
        student2.displayStudentInfo();

        Main student3 = new Main("Bob Smith", 22);
        student3.displayStudentInfo();

        // Demonstrating method with return value
        int birthYear = student2.calculateYearOfBirth(2026);
        System.out.println(student2.studentName + " was born in: " + birthYear);

        // Modifying static variable (affects all instances)
        System.out.println("--- Static Variable Demonstration ---");
        System.out.println("Original course: " + courseName);
        courseName = "Software Engineering";
        System.out.println("Modified course: " + courseName);
        student1.displayStudentInfo(); // Now shows "Software Engineering"

        // Restore for consistency
        courseName = "Computer Science";

        System.out.println("\n========================================");
        System.out.println("KEY CONCEPTS DEMONSTRATED:");
        System.out.println("1. JDK - Java Development Kit (javac compiler)");
        System.out.println("2. JRE - Java Runtime Environment (java runtime)");
        System.out.println("3. JVM - Java Virtual Machine (platform independence)");
        System.out.println("4. Variables - Local, Instance, Static");
        System.out.println("5. Methods - Return type, void, static, parameterized");
        System.out.println("6. Constructors - Default, Parameterized");
        System.out.println("========================================");
    }
}
