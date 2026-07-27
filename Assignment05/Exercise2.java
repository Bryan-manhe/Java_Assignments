/**
 * Assignment 05 - Exercise 2
 * Student Course Registration System using ArrayList to store courses
 * and StringBuffer to display them. Allows users to add, remove,
 * and view registered courses.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Exercise2 {

    private String studentName;
    private int studentId;
    private ArrayList<String> courses;

    public Exercise2(String studentName, int studentId) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.courses = new ArrayList<>();
    }

    public void addCourse(String course) {
        if (courses.contains(course)) {
            System.out.println("Warning: Course \"" + course + "\" is already registered.");
        } else {
            courses.add(course);
            System.out.println("Course \"" + course + "\" added successfully.");
        }
    }

    public void removeCourse(String course) {
        if (courses.remove(course)) {
            System.out.println("Course \"" + course + "\" removed successfully.");
        } else {
            System.out.println("Course \"" + course + "\" not found in your registration.");
        }
    }

    public void displayCourses() {
        System.out.println("\n==================================");
        System.out.println("REGISTERED COURSES FOR " + studentName.toUpperCase());
        System.out.println("Student ID: " + studentId);
        System.out.println("==================================");

        if (courses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            // Using StringBuffer to build the display
            StringBuffer sb = new StringBuffer();
            sb.append("Total Courses: ").append(courses.size()).append("\n");
            sb.append("----------------------------------\n");
            for (int i = 0; i < courses.size(); i++) {
                sb.append(i + 1).append(". ").append(courses.get(i)).append("\n");
            }
            sb.append("==================================");
            System.out.print(sb.toString());
        }
        System.out.println();
    }

    public int getCourseCount() {
        return courses.size();
    }

    public boolean hasCourse(String course) {
        return courses.contains(course);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== STUDENT COURSE REGISTRATION SYSTEM ===\n");

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Exercise2 registration = new Exercise2(name, id);
        System.out.println("\nWelcome, " + name + "! Let's register your courses.\n");

        // Interactive menu
        int choice;
        do {
            System.out.println("--- MENU ---");
            System.out.println("1. Add Course");
            System.out.println("2. Remove Course");
            System.out.println("3. View All Courses");
            System.out.println("4. Check Course Registration");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter course name to add: ");
                    String addCourse = scanner.nextLine();
                    registration.addCourse(addCourse);
                    break;
                case 2:
                    System.out.print("Enter course name to remove: ");
                    String removeCourse = scanner.nextLine();
                    registration.removeCourse(removeCourse);
                    break;
                case 3:
                    registration.displayCourses();
                    break;
                case 4:
                    System.out.print("Enter course name to check: ");
                    String checkCourse = scanner.nextLine();
                    if (registration.hasCourse(checkCourse)) {
                        System.out.println("Yes, \"" + checkCourse + "\" is registered.");
                    } else {
                        System.out.println("No, \"" + checkCourse + "\" is not registered.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting registration system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        } while (choice != 5);

        scanner.close();
    }
}
