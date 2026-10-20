import java.sql.*;
import java.time.LocalDate;

public class LibraryRepository {
    private Book b;

    public LibraryRepository(Book b){
        this.b = b;
    }

    private String getUserEmail(String userId) {
        // More database operations mixed in
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "user", "pass");
            String sql = "SELECT email FROM users WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("email");
            }
            conn.close();
        } catch (SQLException e) {
            System.err.println("User lookup error: " + e.getMessage());
        }
        return "";
    }

    public boolean updateBookState(LocalDate dueDate, String userId){
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "user", "pass");
            String sql = "UPDATE books SET is_available = false, due_date = ?, borrower_id = ? WHERE isbn = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(dueDate));
            stmt.setString(2, userId);
            stmt.setString(3, b.getIsbn());
            stmt.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            return false;
        }
        return true;
    }

    public void updateFine(double  fine){
        try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/library", "user", "pass");
                String sql = "INSERT INTO fines (isbn, user_id, amount, date_created) VALUES (?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, b.getIsbn());
                stmt.setString(2, getCurrentBorrower());
                stmt.setDouble(3, fine);
                stmt.setDate(4, Date.valueOf(LocalDate.now()));
                stmt.executeUpdate();
                conn.close();
            } catch (SQLException e) {
                System.err.println("Fine calculation error: " + e.getMessage());
            }
            
    }

    private String getCurrentBorrower() {
        // Yet more database operations
        // Implementation omitted for brevity
        return "";
    }
}