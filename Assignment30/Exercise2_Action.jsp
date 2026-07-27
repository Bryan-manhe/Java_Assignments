<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
    String action = request.getParameter("action");
    Connection conn = null;
    PreparedStatement ps = null;
    String redirect = "Exercise2_List.jsp?msg=";

    try {
        Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection(
            "jdbc:h2:~/patientcruddb;MODE=MySQL;DB_CLOSE_DELAY=-1", "sa", "");

        if ("add".equals(action)) {
            String name = request.getParameter("name");
            int age = Integer.parseInt(request.getParameter("age"));
            String gender = request.getParameter("gender");
            String disease = request.getParameter("disease");
            String admissionDate = request.getParameter("admissionDate");

            ps = conn.prepareStatement(
                "INSERT INTO patients (name, age, gender, disease, admission_date) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.setString(4, disease);
            ps.setString(5, admissionDate);
            ps.executeUpdate();
            redirect += "added";

        } else if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            int age = Integer.parseInt(request.getParameter("age"));
            String gender = request.getParameter("gender");
            String disease = request.getParameter("disease");
            String admissionDate = request.getParameter("admissionDate");

            ps = conn.prepareStatement(
                "UPDATE patients SET name=?, age=?, gender=?, disease=?, admission_date=? WHERE patient_id=?");
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, gender);
            ps.setString(4, disease);
            ps.setString(5, admissionDate);
            ps.setInt(6, id);
            ps.executeUpdate();
            redirect += "updated";

        } else if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            ps = conn.prepareStatement("DELETE FROM patients WHERE patient_id=?");
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
