<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Exercise 1 - Employee Records</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background-color: #f8f9fa; }
        .container { max-width: 900px; margin: auto; padding: 20px; border: 2px solid #333; border-radius: 8px; background-color: white; }
        h1 { color: #1a5276; text-align: center; }
        .add-btn { display: inline-block; background-color: #28a745; color: white; padding: 10px 18px; text-decoration: none; border-radius: 4px; margin-bottom: 15px; }
        .add-btn:hover { background-color: #218838; }
        table { width: 100%; border-collapse: collapse; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #1a5276; color: white; }
        tr:nth-child(even) { background-color: #f2f2f2; }
        .action-link { text-decoration: none; padding: 4px 10px; border-radius: 3px; color: white; font-size: 0.85em; }
        .edit { background-color: #f39c12; }
        .delete { background-color: #e74c3c; }
        .action-link:hover { opacity: 0.8; }
        .msg { padding: 10px; border-radius: 4px; margin-bottom: 15px; text-align: center; }
        .success { background-color: #d4edda; color: #155724; border: 1px solid #c3e6cb; }
        .error { background-color: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; }
        .footer { text-align: center; margin-top: 20px; color: #7f8c8d; font-size: 0.9em; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Employee Management</h1>

        <%-- Display status messages --%>
        <%
            String msg = request.getParameter("msg");
            if (msg != null) {
                if (msg.equals("added"))    { out.println("<div class='msg success'>Employee added successfully!</div>"); }
                if (msg.equals("updated"))  { out.println("<div class='msg success'>Employee updated successfully!</div>"); }
                if (msg.equals("deleted"))  { out.println("<div class='msg success'>Employee deleted successfully!</div>"); }
                if (msg.equals("error"))    { out.println("<div class='msg error'>Operation failed. Please try again.</div>"); }
            }
        %>

        <a href="Exercise1_Form.jsp" class="add-btn">+ Add New Employee</a>

        <table>
            <tr><th>ID</th><th>Name</th><th>Department</th><th>Designation</th><th>Salary</th><th>Actions</th></tr>

            <%
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                try {
                    Class.forName("org.h2.Driver");
                    conn = DriverManager.getConnection(
                        "jdbc:h2:~/empcruddb;MODE=MySQL;DB_CLOSE_DELAY=-1", "sa", "");

                    stmt = conn.createStatement();
                    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS employees ("
                        + "emp_id INT PRIMARY KEY AUTO_INCREMENT, "
                        + "name VARCHAR(100), department VARCHAR(50), "
                        + "designation VARCHAR(50), salary DOUBLE)");

                    rs = stmt.executeQuery("SELECT * FROM employees ORDER BY emp_id");

                    boolean hasData = false;
                    while (rs.next()) {
                        hasData = true;
                        int id = rs.getInt("emp_id");
            %>
            <tr>
                <td><%= id %></td>
                <td><%= rs.getString("name") %></td>
                <td><%= rs.getString("department") %></td>
                <td><%= rs.getString("designation") %></td>
                <td>$<%= String.format("%.2f", rs.getDouble("salary")) %></td>
                <td>
                    <a href="Exercise1_Form.jsp?id=<%= id %>" class="action-link edit">Edit</a>
                    <a href="Exercise1_Action.jsp?action=delete&id=<%= id %>"
                       class="action-link delete"
                       onclick="return confirm('Delete employee #<%= id %>?')">Delete</a>
                </td>
            </tr>
            <%
                    }
                    if (!hasData) {
                        out.println("<tr><td colspan='6' style='text-align:center; color:#888;'>No employees found. Click 'Add New Employee' to add one.</td></tr>");
                    }
                } catch (Exception e) {
                    out.println("<tr><td colspan='6' style='color:red;'>Error: " + e.getMessage() + "</td></tr>");
                } finally {
                    if (rs != null) try { rs.close(); } catch (SQLException e) {}
                    if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
                    if (conn != null) try { conn.close(); } catch (SQLException e) {}
                }
            %>
        </table>

        <div class="footer">&copy; 2025 Employee CRUD - Exercise 1</div>
    </div>
</body>
</html>
