/**
 * Assignment 11 - Exercise 2
 * Interface Switchable implemented in Light and Fan classes.
 */

// Interface
interface Switchable {
    void turnOn();
    void turnOff();
    String getStatus();
}

// Light class implementing Switchable
class Light implements Switchable {
    private String location;
    private int wattage;
    private boolean isOn;

    public Light(String location, int wattage) {
        this.location = location;
        this.wattage = wattage;
        this.isOn = false;
    }

    @Override
    public void turnOn() {
        isOn = true;
        System.out.println("💡 " + location + " light turned ON (" + wattage + "W)");
    }

    @Override
    public void turnOff() {
        isOn = false;
        System.out.println("💡 " + location + " light turned OFF");
    }

    @Override
    public String getStatus() {
        return "Light at " + location + " (" + wattage + "W): " + (isOn ? "ON" : "OFF");
    }
}

// Fan class implementing Switchable
class Fan implements Switchable {
    private String location;
    private int speedLevels;
    private boolean isOn;
    private int currentSpeed;

    public Fan(String location, int speedLevels) {
        this.location = location;
        this.speedLevels = speedLevels;
        this.isOn = false;
        this.currentSpeed = 0;
    }

    @Override
    public void turnOn() {
        isOn = true;
        currentSpeed = 1; // Start at lowest speed
        System.out.println("🌀 " + location + " fan turned ON at speed " + currentSpeed);
    }

    @Override
    public void turnOff() {
        isOn = false;
        currentSpeed = 0;
        System.out.println("🌀 " + location + " fan turned OFF");
    }

    @Override
    public String getStatus() {
        return "Fan at " + location + " (" + speedLevels + " speeds): " +
               (isOn ? "ON (Speed " + currentSpeed + ")" : "OFF");
    }

    // Additional method specific to Fan
    public void increaseSpeed() {
        if (isOn && currentSpeed < speedLevels) {
            currentSpeed++;
            System.out.println("🌀 " + location + " fan speed increased to " + currentSpeed);
        } else if (!isOn) {
            System.out.println("Cannot change speed - fan is OFF");
        } else {
            System.out.println("Already at maximum speed (" + speedLevels + ")");
        }
    }
}

public class Exercise2 {
    public static void main(String[] args) {
        System.out.println("=== INTERFACE DEMONSTRATION - Switchable ===\n");

        // Create devices
        Light livingRoomLight = new Light("Living Room", 60);
        Light bedroomLight = new Light("Bedroom", 40);
        Fan ceilingFan = new Fan("Living Room", 5);
        Fan exhaustFan = new Fan("Bathroom", 3);

        // Using Switchable interface
        System.out.println("--- Operating Devices via Switchable Interface ---\n");

        // Turn on devices
        Switchable[] devices = {livingRoomLight, bedroomLight, ceilingFan, exhaustFan};

        System.out.println("1. Turning ON all devices:");
        for (Switchable device : devices) {
            device.turnOn();
        }

        System.out.println("\n2. Device Status:");
        for (Switchable device : devices) {
            System.out.println("   " + device.getStatus());
        }

        // Fan-specific operation
        System.out.println("\n3. Fan-specific operation:");
        ceilingFan.increaseSpeed();
        ceilingFan.increaseSpeed();

        System.out.println("\n4. Turning OFF all devices:");
        for (Switchable device : devices) {
            device.turnOff();
        }

        System.out.println("\n5. Final Status:");
        for (Switchable device : devices) {
            System.out.println("   " + device.getStatus());
        }

        System.out.println("\n=== KEY CONCEPTS ===");
        System.out.println("1. Switchable interface defines common device operations");
        System.out.println("2. Light and Fan implement Switchable with unique behaviors");
        System.out.println("3. Polymorphism via Switchable reference array");
    }
}
