<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Exercise 1 - Student Info Display</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        .container { max-width: 700px; margin: auto; padding: 20px; border: 2px solid #333; border-radius: 8px; }
        h1 { color: #1a5276; text-align: center; }
        h2 { color: #2e86c1; border-bottom: 1px solid #ccc; padding-bottom: 5px; }
        table { width: 100%; border-collapse: collapse; margin: 15px 0; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #2e86c1; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        .info { background-color: #eaf2f8; padding: 15px; border-radius: 5px; margin: 15px 0; }
        .footer { text-align: center; margin-top: 20px; color: #7f8c8d; font-size: 0.9em; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Student Information Display</h1>

        <%-- Declaration: defines a method --%>
        <%!
            String getGrade(int marks) {
                if (marks >= 90) return "A+";
                else if (marks >= 80) return "A";
                else if (marks >= 70) return "B";
                else if (marks >= 60) return "C";
                else return "D";
            }
        %>

        <div class="info">
            <strong>Current Date & Time:</strong>
            <%= new java.util.Date().toString() %>
        </div>

        <h2>Student Details (Using JSP Expressions)</h2>
        <%
            String name = "Alice Johnson";
            int age = 20;
            String course = "Computer Science";
            int semester = 4;
            double gpa = 8.75;
        %>
        <table>
            <tr><th>Field</th><th>Value</th></tr>
            <tr><td>Name</td><td><%= name %></td></tr>
            <tr><td>Age</td><td><%= age %></td></tr>
            <tr><td>Course</td><td><%= course %></td></tr>
            <tr><td>Semester</td><td><%= semester %></td></tr>
            <tr><td>GPA</td><td><%= gpa %></td></tr>
        </table>

        <h2>Student Records (Using Scriptlet Loop)</h2>
        <table>
            <tr><th>Student ID</th><th>Name</th><th>Department</th><th>Marks</th><th>Grade</th></tr>
            <%
                String[][] students = {
                    {"S1001", "John Smith",   "Computer Science", "85"},
                    {"S1002", "Jane Doe",     "Mathematics",      "92"},
                    {"S1003", "Bob Wilson",   "Physics",          "78"},
                    {"S1004", "Alice Brown",  "Computer Science", "95"},
                    {"S1005", "Tom Harris",   "Electronics",      "70"}
                };
                for (String[] student : students) {
                    int marks = Integer.parseInt(student[3]);
            %>
            <tr>
                <td><%= student[0] %></td>
                <td><%= student[1] %></td>
                <td><%= student[2] %></td>
                <td><%= student[3] %></td>
                <td><%= getGrade(marks) %></td>
            </tr>
            <% } %>
        </table>

        <div class="footer">
            &copy; 2025 JSP Student Info Display - Exercise 1
        </div>
    </div>
</body>
</html>
