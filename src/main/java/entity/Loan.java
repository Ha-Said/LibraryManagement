package entity;
import java.util.Date;

public class Loan {
    private int id;
    private int memberId;
    private int bookId;
    private Date loanDate;
    private Date returnDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMemberId() {
		return memberId;
	}
	public Loan(int id, int memberId, int bookId, Date loanDate, Date returnDate) {
		super();
		this.id = id;
		this.memberId = memberId;
		this.bookId = bookId;
		this.loanDate = loanDate;
		this.returnDate = returnDate;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public Date getLoanDate() {
		return loanDate;
	}
	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
    
    

}
