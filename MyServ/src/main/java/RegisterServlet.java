import DB.RequestManager;
import DB.ConnectorDb;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        try {
            ConnectorDb connectorDb = new ConnectorDb();
            var kek = connectorDb.getConnection("Users");
            RequestManager requestManager = new RequestManager(kek);
            requestManager.addUser(username, password, role);
            getServletContext().getRequestDispatcher("/LoginPass.jsp").forward(request, response);

            requestManager.close();
        } catch (SQLException e) {
            e.printStackTrace();
            e.getErrorCode();
        }

    }
}
