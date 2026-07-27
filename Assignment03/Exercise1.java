/**
 * Assignment 03 - Exercise 1
 * Calculator program using overloaded methods for addition
 * of integers and decimals. Uses a static variable to count calculations.
 */
public class Exercise1 {

    // Static variable to count total calculations performed
    private static int calculationCount = 0;

    // Overloaded method for integer addition (2 parameters)
    public static int add(int a, int b) {
        calculationCount++;
        return a + b;
    }

    // Overloaded method for integer addition (3 parameters)
    public static int add(int a, int b, int c) {
        calculationCount++;
        return a + b + c;
    }

    // Overloaded method for double addition (2 parameters)
    public static double add(double a, double b) {
        calculationCount++;
        return a + b;
    }

    // Overloaded method for double addition (3 parameters)
    public static double add(double a, double b, double c) {
        calculationCount++;
        return a + b + c;
    }

    // Static method to get calculation count
    public static int getCalculationCount() {
        return calculationCount;
    }

    // Static method to reset counter
    public static void resetCalculationCount() {
        calculationCount = 0;
    }

    public static void main(String[] args) {
        System.out.println("=== CALCULATOR WITH METHOD OVERLOADING ===\n");

        System.out.println("--- Integer Addition ---");
        System.out.println("add(10, 20) = " + add(10, 20));
        System.out.println("add(5, 15, 25) = " + add(5, 15, 25));
        System.out.println("add(100, 200) = " + add(100, 200));
        System.out.println("add(1, 2, 3) = " + add(1, 2, 3));

        System.out.println("\n--- Decimal Addition ---");
        System.out.println("add(10.5, 20.3) = " + add(10.5, 20.3));
        System.out.println("add(5.1, 15.2, 25.3) = " + add(5.1, 15.2, 25.3));
        System.out.println("add(99.99, 0.01) = " + add(99.99, 0.01));
        System.out.println("add(1.5, 2.5, 3.5) = " + add(1.5, 2.5, 3.5));

        System.out.println("\n--- Calculation Statistics ---");
        System.out.println("Total calculations performed: " + getCalculationCount());

        // Demonstrating static method through class name
        System.out.println("\nAccessing static method via class name:");
        System.out.println("Exercise1.getCalculationCount() = " + Exercise1.getCalculationCount());
    }
}
