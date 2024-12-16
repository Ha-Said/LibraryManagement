package servlets;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDAO;
import dao.BookDAOImpl;
import entity.Book;

@WebServlet("/ViewBooks")
public class ViewBooksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookDAO bookDAO;

    @Override
    public void init() throws ServletException {
        try {
			bookDAO = new BookDAOImpl();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("loggedInMember") == null) {
            response.sendRedirect("login.jsp"); // Redirect to login if not logged in
            return;
        }

        try {
            List<Book> books = bookDAO.getAllBooks();
            request.setAttribute("books", books);

            // Forward to the viewBooks.jsp page
            request.getRequestDispatcher("/jsp/viewBooks.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Redirect to an error page if there's an issue
        }
    }
}
