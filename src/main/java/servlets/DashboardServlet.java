package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Member;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Check if the user is logged in
        if (session != null && session.getAttribute("loggedInMember") != null) {
            request.getRequestDispatcher("/jsp/dashboard.jsp").forward(request, response);
        } else {
        	 System.out.println("WOOPSIE");
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Check if the user is logged in
        if (session != null && session.getAttribute("loggedInMember") != null) {
            String action = request.getParameter("action");

            if ("manageLoans".equals(action)) {
                // Redirect to the manage loans page
                response.sendRedirect(request.getContextPath() + "/manageLoans");
            } else {
                // Handle other actions if needed, redirect to dashboard as default
                response.sendRedirect(request.getContextPath() + "/dashboard");
            }
        } else {
            // If not logged in, redirect to login page
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
