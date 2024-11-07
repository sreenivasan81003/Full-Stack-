import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;

@WebServlet("/SoftwareServlet")
public class SoftwareServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String softwareName = request.getParameter("name");
        String description = request.getParameter("description");
        String accessLevels = request.getParameter("accessLevels");

        try (Connection conn = DatabaseConnection.initializeDatabase()) {
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO software (name, description, access_levels) VALUES (?, ?, ?)");
            stmt.setString(1, softwareName);
            stmt.setString(2, description);
            stmt.setString(3, accessLevels);
            stmt.executeUpdate();
            response.sendRedirect("createSoftware.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
