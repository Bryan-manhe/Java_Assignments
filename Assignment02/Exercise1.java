/**
 * Assignment 02 - Exercise 1
 * Student class demonstrating default and parameterized constructors
 * to initialize student name and roll number.
 */
public class Exercise1 {

    private String name;
    private int rollNumber;

    // Default constructor
    public Exercise1() {
        this.name = "Not Assigned";
        this.rollNumber = 0;
    }

    // Parameterized constructor
    public Exercise1(String name, int rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
    }

    // Method to display student details
    public void display() {
        System.out.println("Student Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("----------------------");
    }

    public static void main(String[] args) {
        System.out.println("=== STUDENT DETAILS USING CONSTRUCTORS ===\n");

        // Using default constructor
        System.out.println("1. Default Constructor:");
        Exercise1 student1 = new Exercise1();
        student1.display();

        // Using parameterized constructor
        System.out.println("2. Parameterized Constructor:");
        Exercise1 student2 = new Exercise1("Alice Johnson", 101);
        student2.display();

        Exercise1 student3 = new Exercise1("Bob Smith", 102);
        student3.display();

        Exercise1 student4 = new Exercise1("Carol Davis", 103);
        student4.display();
    }
}
