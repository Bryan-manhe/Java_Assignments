<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Successful</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; background-color: #f8f9fa; }
        .container { max-width: 600px; margin: auto; padding: 25px; border: 2px solid #28a745; border-radius: 8px; background-color: white; }
        h1 { color: #28a745; text-align: center; }
        .success-icon { text-align: center; font-size: 60px; color: #28a745; }
        table { width: 100%; border-collapse: collapse; margin: 15px 0; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #28a745; color: white; width: 35%; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        .back-link { text-align: center; margin-top: 20px; }
        .back-link a { color: #2e86c1; text-decoration: none; }
        .back-link a:hover { text-decoration: underline; }
        .footer { text-align: center; margin-top: 20px; color: #7f8c8d; font-size: 0.9em; }
    </style>
</head>
<body>
    <div class="container">
        <div class="success-icon">&#10004;</div>
        <h1>Registration Successful!</h1>
        <p style="text-align:center; color:#555;">Thank you for registering. Your details are shown below.</p>

        <%
            // Retrieve form data using request.getParameter()
            String name   = request.getParameter("name");
            String email  = request.getParameter("email");
            String phone  = request.getParameter("phone");
            String course = request.getParameter("course");
            String gender = request.getParameter("gender");
            String address = request.getParameter("address");

            // Handle checkboxes (multiple values)
            String[] hobbies = request.getParameterValues("hobbies");
            String hobbiesStr = "None selected";
            if (hobbies != null) {
                hobbiesStr = String.join(", ", hobbies);
            }
        %>

        <%-- Check if form was actually submitted --%>
        <% if (name == null || name.trim().isEmpty()) { %>
            <p style="color: red; text-align: center;">
                No registration data received. Please go back and submit the form.
            </p>
            <div class="back-link">
                <a href="Exercise2_Form.jsp">&larr; Go to Registration Form</a>
            </div>
        <% } else { %>
            <table>
                <tr><th>Field</th><th>Value</th></tr>
                <tr><td>Full Name</td><td><%= name %></td></tr>
                <tr><td>Email</td><td><%= email %></td></tr>
                <tr><td>Phone</td><td><%= (phone != null && !phone.isEmpty()) ? phone : "Not provided" %></td></tr>
                <tr><td>Course</td><td><%= course %></td></tr>
                <tr><td>Gender</td><td><%= (gender != null) ? gender : "Not specified" %></td></tr>
                <tr><td>Hobbies</td><td><%= hobbiesStr %></td></tr>
                <tr><td>Address</td><td><%= (address != null && !address.isEmpty()) ? address.replace("\n", "<br>") : "Not provided" %></td></tr>
            </table>

            <h2>Data Submitted Via</h2>
            <p><strong>Method:</strong> <%= request.getMethod() %></p>
            <p><strong>Server:</strong> <%= request.getServerName() %>:<%= request.getServerPort() %></p>
            <p><strong>Remote Address:</strong> <%= request.getRemoteAddr() %></p>

            <div class="back-link">
                <a href="Exercise2_Form.jsp">&larr; Register Another Student</a>
            </div>
        <% } %>

        <div class="footer">
            &copy; 2025 JSP Registration Result - Exercise 2
        </div>
    </div>
</body>
</html>
