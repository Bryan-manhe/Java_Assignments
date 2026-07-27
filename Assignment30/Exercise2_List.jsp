<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Exercise 2 - Patient Records</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background-color: #f8f9fa; }
        .container { max-width: 1000px; margin: auto; padding: 20px; border: 2px solid #333; border-radius: 8px; background-color: white; }
        h1 { color: #922b21; text-align: center; }
        .add-btn { display: inline-block; background-color: #28a745; color: white; padding: 10px 18px; text-decoration: none; border-radius: 4px; margin-bottom: 15px; }
        .add-btn:hover { background-color: #218838; }
        table { width: 100%; border-collapse: collapse; }
        th, td { border: 1px solid #ddd; padding: 10px; text-align: left; }
        th { background-color: #922b21; color: white; }
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
        <h1>Patient Record Management</h1>

        <%
            String msg = request.getParameter("msg");
            if (msg != null) {
                if (msg.equals("added"))   { out.println("<div class='msg success'>Patient added successfully!</div>"); }
                if (msg.equals("updated")) { out.println("<div class='msg success'>Patient record updated successfully!</div>"); }
                if (msg.equals("deleted")) { out.println("<div class='msg success'>Patient record deleted successfully!</div>"); }
                if (msg.equals("error"))   { out.println("<div class='msg error'>Operation failed. Please try again.</div>"); }
            }
        %>

        <a href="Exercise2_Form.jsp" class="add-btn">+ Add New Patient</a>

        <table>
            <tr><th>Patient ID</th><th>Name</th><th>Age</th><th>Gender</th><th>Disease</th><th>Admission Date</th><th>Actions</th></tr>

            <%
                Connection conn = null;
                Statement stmt = null;
                ResultSet rs = null;
                try {
                    Class.forName("org.h2.Driver");
                    conn = DriverManager.getConnection(
                        "jdbc:h2:~/patientcruddb;MODE=MySQL;DB_CLOSE_DELAY=-1", "sa", "");

                    stmt = conn.createStatement();
                    stmt.executeUpdate("CREATE TABLE IF NOT EXISTS patients ("
                        + "patient_id INT PRIMARY KEY AUTO_INCREMENT, "
                        + "name VARCHAR(100), age INT, gender VARCHAR(10), "
                        + "disease VARCHAR(100), admission_date VARCHAR(20))");

                    rs = stmt.executeQuery("SELECT * FROM patients ORDER BY patient_id");

                    boolean hasData = false;
                    while (rs.next()) {
                        hasData = true;
                        int pid = rs.getInt("patient_id");
            %>
            <tr>
                <td><%= pid %></td>
                <td><%= rs.getString("name") %></td>
                <td><%= rs.getInt("age") %></td>
                <td><%= rs.getString("gender") %></td>
                <td><%= rs.getString("disease") %></td>
                <td><%= rs.getString("admission_date") %></td>
                <td>
                    <a href="Exercise2_Form.jsp?id=<%= pid %>" class="action-link edit">Edit</a>
                    <a href="Exercise2_Action.jsp?action=delete&id=<%= pid %>"
                       class="action-link delete"
                       onclick="return confirm('Delete patient #<%= pid %>?')">Delete</a>
                </td>
            </tr>
            <%
                    }
                    if (!hasData) {
                        out.println("<tr><td colspan='7' style='text-align:center; color:#888;'>No patients found. Click 'Add New Patient' to add one.</td></tr>");
                    }
                } catch (Exception e) {
                    out.println("<tr><td colspan='7' style='color:red;'>Error: " + e.getMessage() + "</td></tr>");
                } finally {
                    if (rs != null) try { rs.close(); } catch (SQLException e) {}
                    if (stmt != null) try { stmt.close(); } catch (SQLException e) {}
                    if (conn != null) try { conn.close(); } catch (SQLException e) {}
                }
            %>
        </table>

        <div class="footer">&copy; 2025 Patient Record Management - Exercise 2</div>
    </div>
</body>
</html>
