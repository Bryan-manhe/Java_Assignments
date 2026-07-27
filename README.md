# Java Assignments - Complete Collection (Assignment 01 to Assignment 20)

This repository contains 20 Java programming assignments covering fundamental to advanced Java concepts.

## Assignments Overview

| Assignment | Topic | Exercises |
|------------|-------|-----------|
| **01** | Java Environment, JDK/JRE/JVM, Variables, Methods, Constructors | Concepts demonstration |
| **02** | Constructors (Default, Parameterized, Copy) | Student class, Mobile Phone Inventory |
| **03** | Method Overloading & Static Members | Calculator, Restaurant Billing |
| **04** | Wrapper Classes | Student Marks, Employee Payroll |
| **05** | ArrayList, Vector, StringBuffer | To-Do List, Course Registration |
| **06** | Inner Class & Anonymous Class | Vehicle program, Food Delivery |
| **07** | Inheritance & Interfaces | Shape area, E-Commerce System |
| **08** | Super Keyword | Employee/Manager, Vehicle Insurance |
| **09** | Final Keyword | Bank Account, Library Book Management |
| **10** | Abstract Classes & Methods | Payment System, Food Order System |
| **11** | Interfaces | Printable (Student/Employee), Switchable (Light/Fan) |
| **12** | User-Defined Packages | College Management, Library Package |
| **13** | File Handling | Employee file, Patient file |
| **14** | Exception Handling (try-catch) | ATM Withdrawal, Online Shopping |
| **15** | finally, throw, throws | Login System, ATM PIN Verification |
| **16** | User-Defined Exceptions | Voting System, Driving License |
| **17** | Swing & Applets | Student Registration Form, Employee Registration Form |
| **18** | Button Click Event Handling | GUI Calculator, Bank Balance Calculator |
| **19** | JDBC SELECT Operation | Student Records, Product Details |
| **20** | JDBC CRUD Operations | Employee CRUD, Student CRUD |

## How to Compile and Run

### Non-GUI Assignments (01-16, 19-20)
```bash
cd AssignmentXX
javac FileName.java
java FileName
```

### JDBC Assignments (19-20)
```bash
cd Assignment19
javac -cp "../h2.jar;." Exercise1.java
java -cp "../h2.jar;." Exercise1
```

### GUI Assignments (17-18)
```bash
cd Assignment17
javac Exercise1.java
java Exercise1
```

## Project Structure
```
Java_Assignments/
├── Assignment01/    through    ├── Assignment20/
│   ├── Exercise1.java              ├── Exercise1.java
│   ├── Exercise2.java              ├── Exercise2.java
│   └── Output/                     └── Output/
│       └── output.txt                  └── output.txt
├── h2.jar           (JDBC assignments dependency)
├── README.md
└── ...
```

## Requirements
- Java 17 or higher
- For JDBC assignments: H2 database (included as h2.jar)
- For GUI assignments: Java Swing (included in JDK)

## Author
Computer Science Student
