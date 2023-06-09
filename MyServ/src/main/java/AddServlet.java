import DB.RequestManager;
import DB.ConnectorDb;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddServlet", value = "/AddServlet")
public class AddServlet extends HttpServlet {
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int grad = Integer.parseInt(req.getParameter("AVRGRADE"));
        int ID = Integer.parseInt(req.getParameter("Id"));
        String name = req.getParameter("NAME");
        int skips = Integer.parseInt(req.getParameter("SKIPS"));
        ConnectorDb connectorDb = new ConnectorDb();
        try {
            RequestManager requestManager = new RequestManager(connectorDb.getConnection("Users"));
            requestManager.addSubject(grad, ID, name, skips);
            getServletContext().getRequestDispatcher("../webapp/welcome.jsp").forward(req, resp);
            requestManager.close();
        } catch (SQLException e) {
            e.printStackTrace();
            getServletContext().getRequestDispatcher("../webapp/error.jsp").forward(req, resp);
            e.printStackTrace();
        }

    }

}
