import java.time.LocalDate;

public class CheckoutService{
    private boolean isAvailable;
    private LocalDate dueDate;
    private NotificationService Not;
    private FineCalculator F;
    private LibraryRepository db;

    public CheckoutService(Book b, LibraryRepository db){
        this.db = db;
        this.F = new FineCalculator(b, db);
    }

    public boolean checkOut(String userId) {
        if (!isAvailable) {
            System.out.println("Book is not available");
            return false;
        }
        isAvailable = false;
        dueDate = LocalDate.now().plusDays(14);
        
        boolean ok = db.updateBookState(dueDate, userId);
        
        if(!ok){
            return false;
        }

        // Email notification (violates SRP)
        Not.sendConfirmationEmail(userId, b);
        
        // Fine calculation (violates SRP)
        F.calculateLateFee(dueDate);
        
        return true;
    }
}