/**
 * Assignment 08 - Exercise 2
 * Vehicle Insurance System where child insurance classes access
 * parent vehicle information using the super keyword.
 */

// Base class
class Vehicle {
    protected String registrationNumber;
    protected String model;
    protected int year;
    protected double purchasePrice;

    public Vehicle(String registrationNumber, String model, int year, double purchasePrice) {
        this.registrationNumber = registrationNumber;
        this.model = model;
        this.year = year;
        this.purchasePrice = purchasePrice;
    }

    public void displayVehicleInfo() {
        System.out.println("Registration: " + registrationNumber);
        System.out.println("Model: " + model);
        System.out.println("Year: " + year);
        System.out.println("Purchase Price: $" + String.format("%.2f", purchasePrice));
    }

    public double calculateCurrentValue() {
        int age = 2026 - year;
        double depreciation = purchasePrice * 0.10 * age; // 10% depreciation per year
        return Math.max(0, purchasePrice - depreciation);
    }
}

// Car Insurance subclass
class CarInsurance extends Vehicle {
    private String policyNumber;
    private String ownerName;
    private String coverageType;

    public CarInsurance(String registrationNumber, String model, int year,
                        double purchasePrice, String policyNumber, String ownerName,
                        String coverageType) {
        super(registrationNumber, model, year, purchasePrice);
        this.policyNumber = policyNumber;
        this.ownerName = ownerName;
        this.coverageType = coverageType;
    }

    @Override
    public void displayVehicleInfo() {
        System.out.println("--- CAR INSURANCE POLICY ---");
        System.out.println("Policy Number: " + policyNumber);
        System.out.println("Owner: " + ownerName);
        super.displayVehicleInfo(); // Using super to access parent method
        System.out.println("Coverage Type: " + coverageType);
    }

    public double calculatePremium() {
        double currentValue = super.calculateCurrentValue(); // Using super
        double basePremium = currentValue * 0.05;
        double coverageMultiplier = coverageType.equalsIgnoreCase("Comprehensive") ? 1.5 : 1.0;
        return basePremium * coverageMultiplier;
    }

    public void displayInsuranceDetails() {
        displayVehicleInfo();
        System.out.println("Current Value: $" + String.format("%.2f", super.calculateCurrentValue()));
        System.out.println("Annual Premium: $" + String.format("%.2f", calculatePremium()));
    }
}

// Bike Insurance subclass
class BikeInsurance extends Vehicle {
    private String policyNumber;
    private String ownerName;
    private boolean hasAntiTheftDevice;

    public BikeInsurance(String registrationNumber, String model, int year,
                         double purchasePrice, String policyNumber, String ownerName,
                         boolean hasAntiTheftDevice) {
        super(registrationNumber, model, year, purchasePrice);
        this.policyNumber = policyNumber;
        this.ownerName = ownerName;
        this.hasAntiTheftDevice = hasAntiTheftDevice;
    }

    @Override
    public void displayVehicleInfo() {
        System.out.println("--- BIKE INSURANCE POLICY ---");
        System.out.println("Policy Number: " + policyNumber);
        System.out.println("Owner: " + ownerName);
        super.displayVehicleInfo();
        System.out.println("Anti-Theft Device: " + (hasAntiTheftDevice ? "Yes" : "No"));
    }

    public double calculatePremium() {
        double currentValue = super.calculateCurrentValue();
        double premium = currentValue * 0.03;
        if (hasAntiTheftDevice) {
            premium *= 0.90; // 10% discount for anti-theft device
        }
        return premium;
    }

    public void displayInsuranceDetails() {
        displayVehicleInfo();
        System.out.println("Current Value: $" + String.format("%.2f", super.calculateCurrentValue()));
        System.out.println("Annual Premium: $" + String.format("%.2f", calculatePremium()));
    }
}

public class Exercise2 {
    public static void main(String[] args) {
        System.out.println("=== VEHICLE INSURANCE SYSTEM - SUPER KEYWORD DEMO ===\n");

        // Car Insurance
        CarInsurance carIns = new CarInsurance(
            "ABC-1234", "Toyota Camry", 2022, 35000,
            "POL-1001", "Alice Johnson", "Comprehensive"
        );
        carIns.displayInsuranceDetails();

        System.out.println();

        // Bike Insurance
        BikeInsurance bikeIns = new BikeInsurance(
            "XYZ-5678", "Harley Davidson Street 750", 2023, 15000,
            "POL-2001", "Bob Smith", true
        );
        bikeIns.displayInsuranceDetails();

        System.out.println("\n=== Super Keyword Usage ===");
        Vehicle base = new Vehicle("TEST-001", "Generic", 2024, 20000);
        base.displayVehicleInfo();
        System.out.println("\nChild classes used super to:");
        System.out.println("1. Call parent constructor: super(registration, model, year, price)");
        System.out.println("2. Call parent method: super.displayVehicleInfo()");
        System.out.println("3. Call parent method for calculation: super.calculateCurrentValue()");
    }
}
