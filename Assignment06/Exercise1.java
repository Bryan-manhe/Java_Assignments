/**
 * Assignment 06 - Exercise 1
 * Vehicle program where an inner class displays vehicle details
 * and anonymous class performs an action.
 */

// Interface for actions
interface VehicleAction {
    void performAction();
}

// Outer class
class Vehicle {
    private String brand;
    private String model;
    private int year;

    public Vehicle(String brand, String model, int year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }

    // Inner class to display vehicle details
    class DetailsDisplayer {
        public void display() {
            System.out.println("=== Vehicle Details ===");
            System.out.println("Brand: " + brand);
            System.out.println("Model: " + model);
            System.out.println("Year: " + year);
            System.out.println("=======================");
        }
    }

    // Method that uses an anonymous class for starting
    public void startEngine() {
        // Anonymous class implementing VehicleAction interface
        VehicleAction startAction = new VehicleAction() {
            @Override
            public void performAction() {
                System.out.println(brand + " " + model + ": Engine started. Vroom!");
            }
        };
        startAction.performAction();
    }

    // Method that uses an anonymous class for stopping
    public void stopEngine() {
        VehicleAction stopAction = new VehicleAction() {
            @Override
            public void performAction() {
                System.out.println(brand + " " + model + ": Engine stopped.");
            }
        };
        stopAction.performAction();
    }
}

public class Exercise1 {
    public static void main(String[] args) {
        System.out.println("=== VEHICLE PROGRAM - INNER CLASS & ANONYMOUS CLASS ===\n");

        // Create vehicle objects
        Vehicle car = new Vehicle("Toyota", "Camry", 2024);
        Vehicle bike = new Vehicle("Harley-Davidson", "Street 750", 2023);

        // Using inner class to display details
        System.out.println("--- Inner Class Demo ---");
        Vehicle.DetailsDisplayer carDetails = car.new DetailsDisplayer();
        carDetails.display();

        Vehicle.DetailsDisplayer bikeDetails = bike.new DetailsDisplayer();
        bikeDetails.display();

        // Using anonymous classes for actions
        System.out.println("--- Anonymous Class Demo ---");
        car.startEngine();
        car.stopEngine();

        System.out.println();

        bike.startEngine();
        bike.stopEngine();

        System.out.println("\n=== CONCEPTS DEMONSTRATED ===");
        System.out.println("1. Inner Class (DetailsDisplayer) - displays vehicle details");
        System.out.println("2. Anonymous Class (startAction) - implements VehicleAction interface");
        System.out.println("3. Anonymous Class (stopAction) - implements VehicleAction interface");
    }
}
