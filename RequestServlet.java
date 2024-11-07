package com.yourpackage;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RequestServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String softwareId = request.getParameter("softwareId");
        String accessType = request.getParameter("accessType");
        String reason = request.getParameter("reason");

        try (Connection con = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO requests (user_id, software_id, access_type, reason, status) VALUES ((SELECT id FROM users WHERE username = ?), ?, ?, ?, 'Pending')";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setInt(2, Integer.parseInt(softwareId));
                stmt.setString(3, accessType);
                stmt.setString(4, reason);
                stmt.executeUpdate();
            }

            response.sendRedirect("requestAccess.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}

