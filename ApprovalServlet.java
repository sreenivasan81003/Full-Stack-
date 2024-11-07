package com.yourpackage;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ApprovalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        String action = request.getParameter("action");

        try (Connection con = DatabaseConnection.getConnection()) {
            String status = "Approved".equals(action) ? "Approved" : "Rejected";
            String query = "UPDATE requests SET status = ? WHERE id = ?";
            try (PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setString(1, status);
                stmt.setInt(2, requestId);
                stmt.executeUpdate();
            }

            response.sendRedirect("pendingRequests.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}

