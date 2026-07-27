<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Exercise 1 - Employee Form</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background-color: #f8f9fa; }
        .container { max-width: 500px; margin: auto; padding: 25px; border: 2px solid #333; border-radius: 8px; background-color: white; }
        h1 { color: #1a5276; text-align: center; }
        label { font-weight: bold; display: block; margin-top: 12px; }
        input[type="text"], input[type="number"], select { width: 100%; padding: 8px; margin-top: 5px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        input[type="submit"] { background-color: #1a5276; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; margin-top: 15px; width: 100%; }
        input[type="submit"]:hover { background-color: #154360; }
        .back-link { text-align: center; margin-top: 15px; }
        .back-link a { color: #2e86c1; }
        .footer { text-align: center; margin-top: 20px; color: #7f8c8d; font-size: 0.9em; }
    </style>
</head>
<body>
    <div class="container">
        <%
            String idParam = request.getParameter("id");
            String name = "", department = "", designation = "";
            double salary = 0;
            boolean isEdit = false;

            if (idParam != null && !idParam.isEmpty()) {
                isEdit = true;
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                try {
                    Class.forName("org.h2.Driver");
                    conn = DriverManager.getConnection(
                        "jdbc:h2:~/empcruddb;MODE=MySQL;DB_CLOSE_DELAY=-1", "sa", "");
                    ps = conn.prepareStatement("SELECT * FROM employees WHERE emp_id=?");
                    ps.setInt(1, Integer.parseInt(idParam));
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        name = rs.getString("name");
                        department = rs.getString("department");
                        designation = rs.getString("designation");
                        salary = rs.getDouble("salary");
                    }
                } catch (Exception e) {
                    out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
                } finally {
                    if (rs != null) try { rs.close(); } catch (SQLException e) {}
                    if (ps != null) try { ps.close(); } catch (SQLException e) {}
                    if (conn != null) try { conn.close(); } catch (SQLException e) {}
                }
            }
        %>

        <h1><%= isEdit ? "Edit Employee" : "Add New Employee" %></h1>

        <form action="Exercise1_Action.jsp" method="post">
            <% if (isEdit) { %>
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="<%= idParam %>">
            <% } else { %>
                <input type="hidden" name="action" value="add">
            <% } %>

            <label for="name">Employee Name:</label>
            <input type="text" id="name" name="name" value="<%= name %>" required>

            <label for="department">Department:</label>
            <select id="department" name="department">
                <option value="IT" <%= department.equals("IT") ? "selected" : "" %>>IT</option>
                <option value="HR" <%= department.equals("HR") ? "selected" : "" %>>HR</option>
                <option value="Finance" <%= department.equals("Finance") ? "selected" : "" %>>Finance</option>
                <option value="Marketing" <%= department.equals("Marketing") ? "selected" : "" %>>Marketing</option>
                <option value="Engineering" <%= department.equals("Engineering") ? "selected" : "" %>>Engineering</option>
            </select>

            <label for="designation">Designation:</label>
            <input type="text" id="designation" name="designation" value="<%= designation %>" required>

            <label for="salary">Salary:</label>
            <input type="number" id="salary" name="salary" step="0.01" value="<%= isEdit ? salary : "" %>" required>

            <input type="submit" value="<%= isEdit ? "Update Employee" : "Add Employee" %>">
        </form>

        <div class="back-link"><a href="Exercise1_List.jsp">&larr; Back to Employee List</a></div>
        <div class="footer">&copy; 2025 Employee CRUD - Exercise 1</div>
    </div>
</body>
</html>
