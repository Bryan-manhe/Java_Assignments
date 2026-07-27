/**
 * Assignment 16 - Exercise 1
 * Voting System that throws a custom exception if age is below 18.
 */

import java.util.Scanner;

// Custom exception class
class UnderAgeException extends Exception {
    public UnderAgeException(String message) {
        super(message);
    }
}

class Voter {
    private String name;
    private int age;
    private String voterId;

    public Voter(String name, int age, String voterId) {
        this.name = name;
        this.age = age;
        this.voterId = voterId;
    }

    public void castVote() throws UnderAgeException {
        if (age < 18) {
            throw new UnderAgeException(name + " is only " + age + " years old. Minimum voting age is 18!");
        }
        System.out.println("Vote cast successfully by " + name + " (Voter ID: " + voterId + ")");
    }

    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age + ", Voter ID: " + voterId);
    }
}

public class Exercise1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== VOTING SYSTEM - USER-DEFINED EXCEPTION ===\n");

        // Test with different voters
        Voter[] voters = {
            new Voter("Alice Johnson", 22, "VID101"),
            new Voter("Bob Smith", 16, "VID102"),
            new Voter("Carol Davis", 19, "VID103"),
            new Voter("David Wilson", 15, "VID104"),
            new Voter("Eve Brown", 25, "VID105")
        };

        for (Voter voter : voters) {
            voter.displayInfo();
            try {
                voter.castVote();
            } catch (UnderAgeException e) {
                System.out.println("Cannot vote: " + e.getMessage());
            }
            System.out.println();
        }

        // Interactive section
        System.out.println("--- Interactive Voter Registration ---");
        try {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            System.out.print("Enter your age: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter your Voter ID: ");
            String voterId = scanner.nextLine();

            Voter newVoter = new Voter(name, age, voterId);
            newVoter.castVote();

        } catch (UnderAgeException e) {
            System.out.println("Registration Failed: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid age!");
        }

        System.out.println("\n=== KEY CONCEPTS ===");
        System.out.println("1. Created custom UnderAgeException by extending Exception");
        System.out.println("2. Thrown when voter age is below 18");
        System.out.println("3. Caught and handled in the calling code");

        scanner.close();
    }
}
