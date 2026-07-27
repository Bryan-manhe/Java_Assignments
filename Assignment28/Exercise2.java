/**
 * Assignment 28 - Exercise 2
 * Temperature Converter using RMI.
 * Converts Celsius to Fahrenheit and Fahrenheit to Celsius remotely.
 *
 * Steps to run:
 *   1. javac Exercise2.java
 *   2. java Exercise2 server    (starts RMI server on port 1098)
 *   3. java Exercise2 client    (in another terminal, runs RMI client)
 */

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

// Remote interface
interface TemperatureConverter extends Remote {
    double celsiusToFahrenheit(double celsius) throws RemoteException;
    double fahrenheitToCelsius(double fahrenheit) throws RemoteException;
}

// Remote implementation
class TemperatureConverterImpl extends UnicastRemoteObject implements TemperatureConverter {
    protected TemperatureConverterImpl() throws RemoteException {
        super();
    }

    public double celsiusToFahrenheit(double celsius) throws RemoteException {
        return (celsius * 9.0 / 5.0) + 32;
    }

    public double fahrenheitToCelsius(double fahrenheit) throws RemoteException {
        return (fahrenheit - 32) * 5.0 / 9.0;
    }
}

public class Exercise2 {

    // Server
    public static void startServer() {
        try {
            System.out.println("========================================");
            System.out.println("  RMI TEMPERATURE CONVERTER - SERVER");
            System.out.println("========================================\n");

            TemperatureConverterImpl converter = new TemperatureConverterImpl();
            Registry registry = LocateRegistry.createRegistry(1098);
            registry.rebind("TempConverterService", converter);

            System.out.println("Temperature converter server on port 1098...");
            System.out.println("Press Ctrl+C to stop.\n");

        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    // Client
    public static void startClient() {
        try {
            System.out.println("========================================");
            System.out.println("  RMI TEMPERATURE CONVERTER - CLIENT");
            System.out.println("========================================\n");

            Registry registry = LocateRegistry.getRegistry("localhost", 1098);
            TemperatureConverter conv = (TemperatureConverter) registry.lookup("TempConverterService");

            System.out.println("Connected to remote temperature converter.\n");

            double celsius = 100.0;
            double fahrenheit = 212.0;

            System.out.println("--- Celsius to Fahrenheit ---");
            double result1 = conv.celsiusToFahrenheit(celsius);
            System.out.println("  " + celsius + "°C = " + String.format("%.2f", result1) + "°F");

            double result2 = conv.celsiusToFahrenheit(0);
            System.out.println("  0°C = " + String.format("%.2f", result2) + "°F");

            double result3 = conv.celsiusToFahrenheit(37);
            System.out.println("  37°C (Body temp) = " + String.format("%.2f", result3) + "°F\n");

            System.out.println("--- Fahrenheit to Celsius ---");
            double result4 = conv.fahrenheitToCelsius(fahrenheit);
            System.out.println("  " + fahrenheit + "°F = " + String.format("%.2f", result4) + "°C");

            double result5 = conv.fahrenheitToCelsius(32);
            System.out.println("  32°F (Freezing) = " + String.format("%.2f", result5) + "°C");

            double result6 = conv.fahrenheitToCelsius(98.6);
            System.out.println("  98.6°F (Body temp) = " + String.format("%.2f", result6) + "°C\n");

            System.out.println("All conversions completed successfully!");

        } catch (Exception e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("server")) {
            startServer();
        } else if (args.length > 0 && args[0].equals("client")) {
            startClient();
        } else {
            System.out.println("Usage:");
            System.out.println("  java Exercise2 server    (start RMI server)");
            System.out.println("  java Exercise2 client    (run RMI client)");
        }
    }
}
