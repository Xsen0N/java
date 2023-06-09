import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

@WebServlet(name = "CurrentTimeServlet", value = "/CurrentTimeServlet")
public class CurrentTimeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Current Time:</h2>");
        out.println("<p>" + new Date() + "</p>");
        out.println("<h2>Request Information:</h2>");
        out.println("<p>Protocol: " + request.getProtocol() + "</p>");
        out.println("<p>Client IP Address: " + request.getRemoteAddr() + "</p>");
        out.println("<p>Client Host Name: " + request.getRemoteHost() + "</p>");
        out.println("<p>HTTP Method: " + request.getMethod() + "</p>");
        out.println("<p>Request URL: " + request.getRequestURL() + "</p>");
        out.println("<h2>Request Headers:</h2>");
        out.println("<ul>");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            out.println("<li>" + headerName + ": " + request.getHeader(headerName) + "</li>");
        }
        out.println("</ul>");
        out.println("</body></html>");
    }

}
