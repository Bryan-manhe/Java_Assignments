/**
 * Assignment 07 - Exercise 1
 * Shape application where Circle and Rectangle inherit from Shape
 * and calculate area.
 */

// Base class
abstract class Shape {
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    // Abstract method - must be implemented by subclasses
    public abstract double calculateArea();

    // Concrete method
    public void display() {
        System.out.println("Shape Color: " + color);
        System.out.println("Area: " + String.format("%.2f", calculateArea()));
    }
}

// Circle subclass
class Circle extends Shape {
    private double radius;

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    public void displayCircleInfo() {
        System.out.println("--- Circle ---");
        System.out.println("Radius: " + radius);
        display();
    }
}

// Rectangle subclass
class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle(String color, double length, double width) {
        super(color);
        this.length = length;
        this.width = width;
    }

    @Override
    public double calculateArea() {
        return length * width;
    }

    public void displayRectangleInfo() {
        System.out.println("--- Rectangle ---");
        System.out.println("Length: " + length + ", Width: " + width);
        display();
    }
}

public class Exercise1 {
    public static void main(String[] args) {
        System.out.println("=== SHAPE AREA CALCULATOR - INHERITANCE DEMO ===\n");

        // Create shapes
        Circle circle = new Circle("Red", 7.5);
        Rectangle rectangle = new Rectangle("Blue", 10.0, 6.0);

        circle.displayCircleInfo();
        System.out.println();
        rectangle.displayRectangleInfo();

        // Polymorphism - using parent class reference
        System.out.println("\n--- Polymorphism: Shape reference ---");
        Shape shape1 = new Circle("Green", 5.0);
        Shape shape2 = new Rectangle("Yellow", 8.0, 4.5);

        shape1.display();
        System.out.println();
        shape2.display();

        System.out.println("\n=== KEY CONCEPTS ===");
        System.out.println("1. Inheritance: Circle and Rectangle extend Shape");
        System.out.println("2. Method Overriding: calculateArea() in both subclasses");
        System.out.println("3. Polymorphism: Shape reference can hold any subclass");
    }
}
