/**
 * Assignment 12 - Exercise 2
 * Library package with Book class. Import and display book information.
 */

import library.Book;

public class Exercise2 {
    public static void main(String[] args) {
        System.out.println("=== LIBRARY MANAGEMENT SYSTEM ===\n");

        // Create book objects from library package
        Book book1 = new Book(1001, "Introduction to Java", "John Smith", 59.99);
        Book book2 = new Book(1002, "Data Structures & Algorithms", "Jane Doe", 45.50);
        Book book3 = new Book(1003, "Database Systems", "Bob Wilson", 55.00);
        Book book4 = new Book(1004, "Operating Systems Concepts", "Sarah Brown", 65.00);

        // Display book details
        book1.displayInfo();
        book2.displayInfo();
        book3.displayInfo();
        book4.displayInfo();

        System.out.println("\n=== PACKAGE STRUCTURE ===");
        System.out.println("library.Book");
    }
}
