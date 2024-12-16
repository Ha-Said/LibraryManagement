package dao;
import java.util.List;

import entity.Book;
import entity.Loan;

public interface LoanDAO {
    void addLoan(Loan loan);                 
    Loan getLoanById(int id);                
    List<Loan> getLoansByMemberId(int memberId); 
    List<Loan> getAllLoans();                 
    void updateLoan(Loan loan);              
    void deleteLoan(int id);            
    List<Book> getBorrowedBooksByMember(int memberId);
    void createLoan(int memberId, int bookId);
    void returnLoan(int bookId, int memberId);

}
