package entity;
public class Book {
    private int id;
    private String title;
    private String author;
    private boolean available;
    private int price;
    private int pages;
	public int getId() {
		return id;
	}
	public Book(int id, String title, String author, boolean available, int price, int pages) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.available = available;
		this.price = price;
		this.pages = pages;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
   
}
