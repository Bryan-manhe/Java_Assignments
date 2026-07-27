/**
 * Assignment 16 - Exercise 2
 * Driving License System that throws a custom exception if the user's
 * age is below 18. If the age is valid, display eligibility.
 */

import java.util.Scanner;

// Custom exception class
class LicenseAgeException extends Exception {
    public LicenseAgeException(String message) {
        super(message);
    }
}

class DrivingLicenseApplicant {
    private String name;
    private int age;
    private boolean hasLearnerPermit;
    private boolean passedTest;

    public DrivingLicenseApplicant(String name, int age, boolean hasLearnerPermit, boolean passedTest) {
        this.name = name;
        this.age = age;
        this.hasLearnerPermit = hasLearnerPermit;
        this.passedTest = passedTest;
    }

    public void checkEligibility() throws LicenseAgeException {
        if (age < 18) {
            throw new LicenseAgeException(name + " is only " + age + " years old. Minimum age for driving license is 18!");
        }
    }

    public void processApplication() {
        try {
            checkEligibility();

            System.out.println("\nProcessing application for: " + name);
            System.out.println("Age: " + age + " - Eligible for driving license");

            if (!hasLearnerPermit) {
                System.out.println("Status: Must obtain a learner permit first");
                return;
            }

            if (!passedTest) {
                System.out.println("Status: Must pass the driving test");
                return;
            }

            System.out.println("License Approved! " + name + " is issued a driving license.");

        } catch (LicenseAgeException e) {
            System.out.println("Application Rejected: " + e.getMessage());
        }
    }
}

public class Exercise2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== DRIVING LICENSE SYSTEM - USER-DEFINED EXCEPTION ===\n");

        // Test with sample applicants
        System.out.println("--- Sample Applications ---");
        DrivingLicenseApplicant[] applicants = {
            new DrivingLicenseApplicant("Alice Johnson", 22, true, true),
            new DrivingLicenseApplicant("Bob Smith", 16, true, true),
            new DrivingLicenseApplicant("Carol Davis", 19, true, false),
            new DrivingLicenseApplicant("David Wilson", 17, false, false),
            new DrivingLicenseApplicant("Eve Brown", 25, true, true)
        };

        for (DrivingLicenseApplicant applicant : applicants) {
            applicant.processApplication();
            System.out.println();
        }

        // Interactive section
        System.out.println("--- New Application ---");
        try {
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            System.out.print("Enter your age: ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("Do you have a learner permit? (yes/no): ");
            String permitStr = scanner.nextLine();
            boolean hasPermit = permitStr.equalsIgnoreCase("yes");

            System.out.print("Have you passed the driving test? (yes/no): ");
            String testStr = scanner.nextLine();
            boolean passed = testStr.equalsIgnoreCase("yes");

            DrivingLicenseApplicant applicant = new DrivingLicenseApplicant(name, age, hasPermit, passed);
            applicant.processApplication();

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter a valid age!");
        }

        System.out.println("\n=== KEY CONCEPTS ===");
        System.out.println("1. Created custom LicenseAgeException by extending Exception");
        System.out.println("2. Thrown when applicant age is below 18");
        System.out.println("3. Caught and handled in processApplication()");

        scanner.close();
    }
}
