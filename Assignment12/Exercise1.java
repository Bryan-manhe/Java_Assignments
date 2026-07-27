/**
 * Assignment 12 - Exercise 1
 * College Management System using user-defined packages.
 * Uses separate packages for student and faculty details.
 */

import college.student.Student;
import college.faculty.Faculty;

public class Exercise1 {
    public static void main(String[] args) {
        System.out.println("=== COLLEGE MANAGEMENT SYSTEM ===\n");

        // Create student objects from college.student package
        Student student1 = new Student(101, "Alice Johnson", "Computer Science", 4);
        Student student2 = new Student(102, "Bob Smith", "Mathematics", 2);
        Student student3 = new Student(103, "Carol Davis", "Physics", 6);

        // Create faculty objects from college.faculty package
        Faculty faculty1 = new Faculty(201, "Dr. Williams", "Computer Science", "Professor");
        Faculty faculty2 = new Faculty(202, "Dr. Anderson", "Mathematics", "Associate Professor");
        Faculty faculty3 = new Faculty(203, "Dr. Taylor", "Physics", "Assistant Professor");

        // Display student details
        System.out.println("--- Students ---");
        student1.displayInfo();
        student2.displayInfo();
        student3.displayInfo();

        // Display faculty details
        System.out.println("--- Faculty ---");
        faculty1.displayInfo();
        faculty2.displayInfo();
        faculty3.displayInfo();

        System.out.println("\n=== PACKAGE STRUCTURE ===");
        System.out.println("college.student.Student");
        System.out.println("college.faculty.Faculty");
    }
}
