<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
    String action = request.getParameter("action");
    Connection conn = null;
    PreparedStatement ps = null;
    String redirect = "Exercise1_List.jsp?msg=";

    try {
        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection(
            "jdbc:h2:~/empcruddb;MODE=MySQL;DB_CLOSE_DELAY=-1", "sa", "");

        if ("add".equals(action)) {
            String name = request.getParameter("name");
            String dept = request.getParameter("department");
            String desig = request.getParameter("designation");
            double salary = Double.parseDouble(request.getParameter("salary"));

            ps = conn.prepareStatement(
                "INSERT INTO employees (name, department, designation, salary) VALUES (?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, dept);
            ps.setString(3, desig);
            ps.setDouble(4, salary);
            ps.executeUpdate();
            redirect += "added";

        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String dept = request.getParameter("department");
            String desig = request.getParameter("designation");
            double salary = Double.parseDouble(request.getParameter("salary"));

            ps = conn.prepareStatement(
                "UPDATE employees SET name=?, department=?, designation=?, salary=? WHERE emp_id=?");
            ps.setString(1, name);
            ps.setString(2, dept);
            ps.setString(3, desig);
            ps.setDouble(4, salary);
            ps.setInt(5, id);
            ps.executeUpdate();
            redirect += "updated";

        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            ps = conn.prepareStatement("DELETE FROM employees WHERE emp_id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
            redirect += "deleted";
        }

    } catch (Exception e) {
        redirect += "error";
    } finally {
        if (ps != null) try { ps.close(); } catch (SQLException e) {}
        if (conn != null) try { conn.close(); } catch (SQLException e) {}
    }

    response.sendRedirect(redirect);
%>
