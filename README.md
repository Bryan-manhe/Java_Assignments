# Java Assignments

A hands-on collection of Java exercises covering core language concepts, object-oriented programming, GUI basics, database access, concurrency, and networking. The repository is organized by assignment so each topic can be explored independently.

> **Learning project:** These exercises are intended for practice and demonstration. They are not presented as a production-ready backend application.

## Topics covered

| Assignments | Topics |
|---|---|
| 01–04 | JDK/JRE/JVM, variables, methods, constructors, overloading, wrapper classes |
| 05–08 | Collections, strings, inner and anonymous classes, inheritance, interfaces, `super` |
| 09–12 | `final`, abstract classes, interfaces, packages |
| 13–16 | File handling, exception handling, custom exceptions |
| 17–18 | Swing GUI basics and event handling |
| 19–22 | JDBC, database queries, CRUD operations, `DriverManager`, `PreparedStatement`, `ResultSet` |
| 23–24 | Swing with JDBC and multithreading (`Thread`, `Runnable`) |
| 25–27 | Socket programming, chat applications, and Java RMI |
| 28–30 | JSP fundamentals and JSP-based CRUD exercises |

See the assignment folders for the individual exercise source files and any included output examples.

## Tech stack

- **Language:** Java
- **Version:** Java 17 or later is the intended baseline for the core exercises; older APIs such as applets are included only as historical learning material and may not run on modern JDKs.
- **Database exercises:** H2 database (the repository includes `h2.jar`)
- **GUI exercises:** Java Swing
- **Web exercises:** JSP basics

## Prerequisites

1. Install a JDK and verify the installation:
   ```bash
   java -version
   javac -version
   ```
2. Clone the repository:
   ```bash
   git clone https://github.com/Bryan-manhe/Java_Assignments.git
   cd Java_Assignments
   ```
3. Open the folder for the assignment you want to run. Check the source file's class name before compiling.

## Compile and run a basic exercise

From an assignment directory, use the filename and class name that match the exercise:

```bash
javac Exercise1.java
java Exercise1
```

Some exercises use different filenames, packages, external dependencies, a GUI, a database, or a server. Follow the requirements of that specific exercise rather than assuming every file runs with the same command.

### JDBC exercises

The repository includes an H2 JAR. On Windows, a typical classpath command is:

```powershell
javac -cp "../h2.jar;." Exercise1.java
java -cp "../h2.jar;." Exercise1
```

On macOS or Linux, use a colon (`:`) instead of a semicolon (`;`) in the classpath:

```bash
javac -cp "../h2.jar:." Exercise1.java
java -cp "../h2.jar:." Exercise1
```

Run database exercises from the directory and with the class name specified by that exercise. GUI programs need a graphical desktop; networking and JSP/RMI examples may require additional setup.

## Repository structure

```text
Java_Assignments/
├── Assignment01/
├── Assignment02/
├── ...
├── Assignment30/
├── h2.jar
└── README.md
```

The diagram is a high-level overview; individual folders may contain additional source files and output examples.

## Current scope and next steps

This repository documents Java learning exercises. It does **not** claim to be a Spring Boot REST API or a production backend service. Planned portfolio improvements include adding reproducible setup instructions per exercise, automated compile checks where compatible, and unit tests for suitable exercises.

## Author

Bryan Manhenge
