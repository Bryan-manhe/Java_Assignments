/**
 * Assignment 04 - Exercise 1
 * Program to convert student marks from String format to Integer
 * and calculate total marks using wrapper classes.
 */
public class Exercise1 {

    public static void main(String[] args) {
        System.out.println("=== STUDENT MARKS CALCULATOR USING WRAPPER CLASSES ===\n");

        // Student marks in String format (simulating input from a file or user)
        String[] markStrings = {"85", "90", "78", "92", "88"};

        // Convert String to Integer using wrapper class Integer.parseInt()
        System.out.println("Converting String marks to Integer objects:");
        Integer[] marks = new Integer[markStrings.length];
        for (int i = 0; i < markStrings.length; i++) {
            marks[i] = Integer.parseInt(markStrings[i]); // Autoboxing after parsing
            System.out.println("  Subject " + (i + 1) + ": \"" + markStrings[i] + "\" -> " + marks[i]);
        }

        // Calculate total using wrapper objects (auto-unboxing)
        int total = 0;
        for (Integer mark : marks) {
            total += mark; // Auto-unboxing: Integer to int
        }

        System.out.println("\n--- Results ---");
        System.out.println("Number of Subjects: " + marks.length);
        System.out.println("Total Marks: " + total);
        System.out.println("Average Marks: " + (total / (double) marks.length));

        // Demonstrate additional wrapper class features
        System.out.println("\n--- Wrapper Class Features ---");
        System.out.println("Max value from Integer: " + Integer.MAX_VALUE);
        System.out.println("Min value from Integer: " + Integer.MIN_VALUE);

        String binaryStr = Integer.toBinaryString(total);
        System.out.println("Total in Binary: " + binaryStr);
        System.out.println("Total in Hexadecimal: " + Integer.toHexString(total));

        // Parsing with error handling
        System.out.println("\n--- Input Validation ---");
        String invalidMark = "abc";
        try {
            Integer.parseInt(invalidMark);
        } catch (NumberFormatException e) {
            System.out.println("Error: Cannot parse \"" + invalidMark + "\" - " + e.getMessage());
        }

        System.out.println("\n=== WRAPPER CLASS CONCEPTS DEMONSTRATED ===");
        System.out.println("1. Integer.parseInt() - String to primitive conversion");
        System.out.println("2. Autoboxing - int to Integer automatically");
        System.out.println("3. Auto-unboxing - Integer to int automatically");
        System.out.println("4. Integer.MAX_VALUE / MIN_VALUE - Constants");
        System.out.println("5. Integer.toBinaryString() / toHexString() - Format conversion");
    }
}
