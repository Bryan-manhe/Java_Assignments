/**
 * Assignment 15 - Exercise 1
 * Login program that throws an exception for invalid password
 * and uses finally block for cleanup.
 */

import java.util.Scanner;

// Custom exception class
class InvalidPasswordException extends Exception {
    public InvalidPasswordException(String message) {
        super(message);
    }
}

public class Exercise1 {

    private static final String USERNAME = "admin";
    private static final String PASSWORD = "password123";
    private static int attemptCount = 0;
    private static final int MAX_ATTEMPTS = 3;

    // Method using 'throws' to declare exception
    public static boolean login(String username, String password) throws InvalidPasswordException {
        if (!username.equals(USERNAME)) {
            throw new InvalidPasswordException("Username not found: " + username);
        }

        if (!password.equals(PASSWORD)) {
            throw new InvalidPasswordException("Invalid password for user: " + username);
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== LOGIN SYSTEM - throw, throws, finally ===\n");

        while (attemptCount < MAX_ATTEMPTS) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            attemptCount++;

            try {
                System.out.println("\nAttempt " + attemptCount + " of " + MAX_ATTEMPTS);
                System.out.println("Verifying credentials...");

                // This may throw an exception
                if (login(username, password)) {
                    System.out.println("Login successful! Welcome, " + username + "!");
                    break;
                }

            } catch (InvalidPasswordException e) {
                System.out.println("Login Failed: " + e.getMessage());
                System.out.println("Remaining attempts: " + (MAX_ATTEMPTS - attemptCount));

            } finally {
                // Finally block - always executes
                System.out.println("--- Finally: Login attempt completed. ---");

                if (attemptCount >= MAX_ATTEMPTS) {
                    System.out.println("\nAccount locked due to too many failed attempts!");
                }
            }

            System.out.println();
        }

        // Demonstrate finally with return statement
        System.out.println("\n--- Demonstrating 'finally' always executes ---");
        try {
            System.out.println("1. Inside try block");
            System.out.println("2. About to return, but finally will execute first...");
            return; // Even with return, finally executes
        } finally {
            System.out.println("3. FINALLY BLOCK: This always executes even after return!");
        }

        // This line never executes because of the return above, but finally already ran
    }
}
