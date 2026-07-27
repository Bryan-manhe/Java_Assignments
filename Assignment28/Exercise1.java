/**
 * Assignment 28 - Exercise 1
 * Remote Calculator using RMI.
 * Performs add, subtract, multiply, divide operations remotely.
 *
 * Steps to run:
 *   1. javac Exercise1.java
 *   2. java Exercise1 server    (starts RMI server on port 1099)
 *   3. java Exercise1 client    (in another terminal, runs RMI client)
 */

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

// Remote interface
interface Calculator extends Remote {
    int add(int a, int b) throws RemoteException;
    int subtract(int a, int b) throws RemoteException;
    int multiply(int a, int b) throws RemoteException;
    int divide(int a, int b) throws RemoteException;
}

// Remote implementation
class CalculatorImpl extends UnicastRemoteObject implements Calculator {
    protected CalculatorImpl() throws RemoteException {
        super();
    }

    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    public int subtract(int a, int b) throws RemoteException {
        return a - b;
    }

    public int multiply(int a, int b) throws RemoteException {
        return a * b;
    }

    public int divide(int a, int b) throws RemoteException {
        if (b == 0) throw new RemoteException("Division by zero!");
        return a / b;
    }
}

public class Exercise1 {

    // Server
    public static void startServer() {
        try {
            System.out.println("========================================");
            System.out.println("  RMI CALCULATOR - SERVER");
            System.out.println("========================================\n");

            CalculatorImpl calculator = new CalculatorImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("CalculatorService", calculator);

            System.out.println("Calculator server is running on port 1099...");
            System.out.println("Press Ctrl+C to stop.\n");

        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }

    // Client
    public static void startClient() {
        try {
            System.out.println("========================================");
            System.out.println("  RMI CALCULATOR - CLIENT");
            System.out.println("========================================\n");

            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Calculator calc = (Calculator) registry.lookup("CalculatorService");

            System.out.println("Connected to remote calculator.\n");

            int a = 50, b = 10;

            System.out.println("a = " + a + ", b = " + b + "\n");

            int sum = calc.add(a, b);
            System.out.println("Addition       : " + a + " + " + b + " = " + sum);

            int diff = calc.subtract(a, b);
            System.out.println("Subtraction    : " + a + " - " + b + " = " + diff);

            int product = calc.multiply(a, b);
            System.out.println("Multiplication : " + a + " × " + b + " = " + product);

            int quotient = calc.divide(a, b);
            System.out.println("Division       : " + a + " ÷ " + b + " = " + quotient);

            System.out.println("\nAll operations completed successfully!");

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
            System.out.println("  java Exercise1 server    (start RMI server)");
            System.out.println("  java Exercise1 client    (run RMI client)");
        }
    }
}
