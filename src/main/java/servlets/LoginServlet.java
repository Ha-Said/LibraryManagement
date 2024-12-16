package servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.MemberDAO;
import dao.MemberDAOImpl;
import entity.Member;

@WebServlet("/") // Maps this servlet to the root URL
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MemberDAO memberDAO;

    @Override
    public void init() throws ServletException {
        try {
            memberDAO = new MemberDAOImpl();
        } catch (SQLException e) {
            throw new ServletException("Failed to initialize MemberDAO", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Member member = memberDAO.validateLogin(username, password);

        if (member != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loggedInMember", member);

            // Redirect to DashboardServlet with context path
            response.sendRedirect(request.getContextPath() + "/dashboard");
        } else {
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }
}
