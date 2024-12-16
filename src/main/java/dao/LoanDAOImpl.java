package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Book;
import entity.Loan;
import util.DBConnection; // Assuming DBConnection utility exists for connection setup

public class LoanDAOImpl implements LoanDAO {
    private Connection connection;

    // No-argument constructor
    public LoanDAOImpl() {
        try {
            this.connection = DBConnection.getConnection(); // Fetch connection from utility
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize database connection", e);
        }
    }

    // Constructor with Connection parameter (optional, for flexibility)
    public LoanDAOImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void createLoan(int memberId, int bookId) {
        String query = "INSERT INTO loans (member_id, book_id, loan_date, return_date) VALUES (?, ?, CURRENT_DATE, NULL)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, memberId);
            ps.setInt(2, bookId);
            ps.executeUpdate();
            
            // Update the book's availability to false
            String updateBookQuery = "UPDATE books SET available = false WHERE id = ?";
            try (PreparedStatement psUpdate = connection.prepareStatement(updateBookQuery)) {
                psUpdate.setInt(1, bookId);
                psUpdate.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void returnLoan(int bookId, int memberId) {
        String query = "UPDATE loans SET return_date = CURRENT_DATE WHERE book_id = ? AND member_id = ? AND return_date IS NULL";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, bookId);
            ps.setInt(2, memberId);
            ps.executeUpdate();
            
            // Update the book's availability to true
            String updateBookQuery = "UPDATE books SET available = true WHERE id = ?";
            try (PreparedStatement psUpdate = connection.prepareStatement(updateBookQuery)) {
                psUpdate.setInt(1, bookId);
                psUpdate.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public List<Book> getBorrowedBooksByMember(int memberId) {
        List<Book> books = new ArrayList<>();
        String query = "SELECT b.id, b.title, b.author, b.available, b.price, b.pages FROM books b " +
                       "JOIN loans l ON b.id = l.book_id " +
                       "WHERE l.member_id = ? AND l.return_date IS NULL";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, memberId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                books.add(new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getBoolean("available"),
                    rs.getInt("price"),
                    rs.getInt("pages")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public void addLoan(Loan loan) {
        String query = "INSERT INTO loans (member_id, book_id, loan_date, return_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, loan.getMemberId());
            ps.setInt(2, loan.getBookId());
            ps.setDate(3, new java.sql.Date(loan.getLoanDate().getTime()));
            ps.setDate(4, new java.sql.Date(loan.getReturnDate().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Loan getLoanById(int id) {
        String query = "SELECT * FROM loans WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Loan(
                    rs.getInt("id"),
                    rs.getInt("member_id"),
                    rs.getInt("book_id"),
                    rs.getDate("loan_date"),
                    rs.getDate("return_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Loan> getLoansByMemberId(int memberId) {
        List<Loan> loans = new ArrayList<>();
        String query = "SELECT * FROM loans WHERE member_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, memberId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                loans.add(new Loan(
                    rs.getInt("id"),
                    rs.getInt("member_id"),
                    rs.getInt("book_id"),
                    rs.getDate("loan_date"),
                    rs.getDate("return_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }

    @Override
    public List<Loan> getAllLoans() {
        List<Loan> loans = new ArrayList<>();
        String query = "SELECT * FROM loans";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                loans.add(new Loan(
                    rs.getInt("id"),
                    rs.getInt("member_id"),
                    rs.getInt("book_id"),
                    rs.getDate("loan_date"),
                    rs.getDate("return_date")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loans;
    }

    @Override
    public void updateLoan(Loan loan) {
        String query = "UPDATE loans SET member_id = ?, book_id = ?, loan_date = ?, return_date = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, loan.getMemberId());
            ps.setInt(2, loan.getBookId());
            ps.setDate(3, new java.sql.Date(loan.getLoanDate().getTime()));
            ps.setDate(4, new java.sql.Date(loan.getReturnDate().getTime()));
            ps.setInt(5, loan.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLoan(int id) {
        String query = "DELETE FROM loans WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
