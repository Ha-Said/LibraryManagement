package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import entity.Book;
import util.DBConnection;




public class BookDAOImpl implements BookDAO {
    private Connection connection;
    public BookDAOImpl() throws SQLException {
        this.connection = DBConnection.getConnection();
    }
    // Constructor with dependency injection for Connection
    public BookDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";  // Assuming you have a table called 'books' with appropriate columns
        try (PreparedStatement ps = connection.prepareStatement(query)) {
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
    public Book getBookById(int id) {
        Book book = null;
        String query = "SELECT * FROM books WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                book = new Book(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getBoolean("available"),
                    rs.getInt("price"),
                    rs.getInt("pages")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public void addBook(Book book) {
        String query = "INSERT INTO books (title, author, available, price, pages) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setBoolean(3, book.isAvailable());
            ps.setInt(4, book.getPrice());
            ps.setInt(5, book.getPages());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void updateBookAvailability(int bookId, boolean available) {
        String query = "UPDATE books SET available = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setBoolean(1, available);
            ps.setInt(2, bookId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void updateBook(Book book) {
        String query = "UPDATE books SET title = ?, author = ?, available = ?, price = ?, pages = ? WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setBoolean(3, book.isAvailable());
            ps.setInt(4, book.getPrice());
            ps.setInt(5, book.getPages());
            ps.setInt(6, book.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBook(int id) {
        String query = "DELETE FROM books WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        String query = "SELECT * FROM books WHERE available = TRUE"; 
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                availableBooks.add(new Book(
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
        return availableBooks;
    }
}
