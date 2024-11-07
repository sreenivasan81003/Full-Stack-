import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/ApprovalServlet")
public class ApprovalServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int requestId = Integer.parseInt(request.getParameter("requestId"));
        String action = request.getParameter("action");
        String status = action.equals("approve") ? "Approved" : "Rejected";

        try (Connection conn = DatabaseConnection.initializeDatabase()) {
            PreparedStatement stmt = conn.prepareStatement("UPDATE requests SET status = ? WHERE id = ?");
            stmt.setString(1, status);
            stmt.setInt(2, requestId);
            stmt.executeUpdate();
            response.sendRedirect("pendingRequests.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
