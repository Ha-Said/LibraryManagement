package servlets;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDAO;
import dao.BookDAOImpl;
import dao.LoanDAO;
import dao.LoanDAOImpl;
import entity.Book;
import entity.Member;

@WebServlet("/manageLoans")
public class ManageLoansServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookDAO bookDAO;
    private LoanDAO loanDAO;

    @Override
    public void init() throws ServletException {
        try {
            bookDAO = new BookDAOImpl(); // assuming you pass a DB connection here in real case
            loanDAO = new LoanDAOImpl(); // same for loanDAO
        } catch (Exception e) {
            throw new ServletException("Failed to initialize DAOs", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("loggedInMember") != null) {
            Member loggedInMember = (Member) session.getAttribute("loggedInMember");

            // Fetch borrowed books and available books
            List<Book> borrowedBooks = loanDAO.getBorrowedBooksByMember(loggedInMember.getId());
            List<Book> availableBooks = bookDAO.getAvailableBooks();

            // Set attributes for the JSP
            request.setAttribute("borrowedBooks", borrowedBooks);
            request.setAttribute("availableBooks", availableBooks);

            // Forward to the manage loans page
            request.getRequestDispatcher("/jsp/manageLoans.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Check if the user is logged in
        if (session != null && session.getAttribute("loggedInMember") != null) {
            Member loggedInMember = (Member) session.getAttribute("loggedInMember");

            String action = request.getParameter("action");
            if (action != null) {
                if ("loan".equals(action)) {
                    // Loan a book
                    int bookId = Integer.parseInt(request.getParameter("bookId"));
                    loanDAO.createLoan(loggedInMember.getId(), bookId);
                    response.sendRedirect(request.getContextPath() + "/manageLoans");
                } else if ("return".equals(action)) {
                    // Return a book
                    int loanId = Integer.parseInt(request.getParameter("loanId"));
                    
                    // Delete the loan record
                    loanDAO.deleteLoan(loanId);
                    
                    // Update the book's availability
                    int bookId = Integer.parseInt(request.getParameter("bookId"));
                    bookDAO.updateBookAvailability(bookId, true); // Assuming 'true' means the book is now available

                    response.sendRedirect(request.getContextPath() + "/manageLoans");
                }
            } else {
                // In case of no action, simply reload the page
                response.sendRedirect(request.getContextPath() + "/manageLoans");
            }
        } else {
            // If not logged in, redirect to login page
            response.sendRedirect(request.getContextPath() + "/");
        }
    }
}
