import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FineCalculator{
    private LibraryRepository db;
    private Book b;

    public FineCalculator(Book b, LibraryRepository db){
        this.b = b;
        this.db = db;
    }

    public double calculateLateFee(LocalDate dueDate) {
        if (dueDate != null && LocalDate.now().isAfter(dueDate)) {
            long daysLate = ChronoUnit.DAYS.between(dueDate, LocalDate.now());
            double fine = daysLate * 0.50; // $0.50 per day
            
            db.updateFine(fine);
            
            return fine;
        }  
        return 0.0;
    }
}