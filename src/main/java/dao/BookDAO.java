package dao;

import java.util.List;
import entity.Book;

public interface BookDAO {
    List<Book> getAllBooks();
    Book getBookById(int id);
    List<Book> getAvailableBooks();  // Add this method to get available books
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(int id);
    void updateBookAvailability(int bookId, boolean available);
}
