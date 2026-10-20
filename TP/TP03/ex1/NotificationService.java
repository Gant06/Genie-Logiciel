import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class NotificationService {
    public  void sendConfirmationEmail(String userId, Book b) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            
            Session session = Session.getInstance(props);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("library@example.com"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(getUserEmail(userId)));
            message.setSubject("Book Checkout Confirmation");
            message.setText("You have successfully checked out: " + b.getTitle());
            
            Transport.send(message);
        } catch (MessagingException e) {
            System.err.println("Email error: " + e.getMessage());
        }
    }
}