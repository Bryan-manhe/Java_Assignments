<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Exercise 2 - Patient Form</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 30px; background-color: #f8f9fa; }
        .container { max-width: 500px; margin: auto; padding: 25px; border: 2px solid #333; border-radius: 8px; background-color: white; }
        h1 { color: #922b21; text-align: center; }
        label { font-weight: bold; display: block; margin-top: 12px; }
        input[type="text"], input[type="number"], select { width: 100%; padding: 8px; margin-top: 5px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
        input[type="submit"] { background-color: #922b21; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; margin-top: 15px; width: 100%; }
        input[type="submit"]:hover { background-color: #7b241c; }
        .back-link { text-align: center; margin-top: 15px; }
        .back-link a { color: #2e86c1; }
        .footer { text-align: center; margin-top: 20px; color: #7f8c8d; font-size: 0.9em; }
        .radio-group { margin-top: 5px; }
        .radio-group label { display: inline; font-weight: normal; margin-right: 15px; }
        .radio-group input { margin-right: 3px; }
    </style>
</head>
<body>
    <div class="container">
        <%
            String idParam = request.getParameter("id");
            String name = "", gender = "", disease = "", admissionDate = "";
            int age = 0;
            boolean isEdit = false;

            if (idParam != null && !idParam.isEmpty()) {
                isEdit = true;
                Connection conn = null;
                PreparedStatement ps = null;
                ResultSet rs = null;
                try {
                    Class.forName("org.h2.Driver");
                    conn = DriverManager.getConnection(
                        "jdbc:h2:~/patientcruddb;MODE=MySQL;DB_CLOSE_DELAY=-1", "sa", "");
                    ps = conn.prepareStatement("SELECT * FROM patients WHERE patient_id=?");
                    ps.setInt(1, Integer.parseInt(idParam));
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        name = rs.getString("name");
                        age = rs.getInt("age");
                        gender = rs.getString("gender");
                        disease = rs.getString("disease");
                        admissionDate = rs.getString("admission_date");
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

        <h1><%= isEdit ? "Edit Patient" : "Add New Patient" %></h1>

        <form action="Exercise2_Action.jsp" method="post">
            <% if (isEdit) { %>
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="<%= idParam %>">
            <% } else { %>
                <input type="hidden" name="action" value="add">
            <% } %>

            <label for="name">Patient Name:</label>
            <input type="text" id="name" name="name" value="<%= name %>" required>

            <label for="age">Age:</label>
            <input type="number" id="age" name="age" min="0" max="150" value="<%= isEdit ? age : "" %>" required>

            <label>Gender:</label>
            <div class="radio-group">
                <input type="radio" id="male" name="gender" value="Male" <%= gender.equals("Male") ? "checked" : "" %>>
                <label for="male">Male</label>
                <input type="radio" id="female" name="gender" value="Female" <%= gender.equals("Female") ? "checked" : "" %>>
                <label for="female">Female</label>
            </div>

            <label for="disease">Disease/Diagnosis:</label>
            <input type="text" id="disease" name="disease" value="<%= disease %>" required>

            <label for="admissionDate">Admission Date:</label>
            <input type="text" id="admissionDate" name="admissionDate"
                   value="<%= isEdit ? admissionDate : new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()) %>" required>

            <input type="submit" value="<%= isEdit ? "Update Patient" : "Add Patient" %>">
        </form>

        <div class="back-link"><a href="Exercise2_List.jsp">&larr; Back to Patient Records</a></div>
        <div class="footer">&copy; 2025 Patient Record Management - Exercise 2</div>
    </div>
</body>
</html>
