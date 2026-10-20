// SRP VIOLATION: Book class handles too many responsibilities
public class Book {
    private String title;
    private String author;
    private String isbn;
    
    // Constructor
    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }
    
    // Book information methods
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }   
    
}