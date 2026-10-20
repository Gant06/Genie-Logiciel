import java.util.Properties;
import java.util.UUID;
import javax.mail.*;
import javax.mail.internet.*;

// DRY VIOLATION: Repeated notification logic across different channels
// DIP VIOLATION: High-level modules depend on low-level concrete classes

public class EmailNotificationService {
    
    public boolean sendWelcomeNotification(String userEmail, String userName) {
        // DRY VIOLATION: Repeated validation logic
        if (userEmail == null || userEmail.trim().isEmpty()) {
            System.err.println("Invalid email address");
            return false;
        }
        if (!userEmail.contains("@") || !userEmail.contains(".")) {
            System.err.println("Email format is invalid");
            return false;
        }
        if (userName == null || userName.trim().isEmpty()) {
            System.err.println("Username cannot be empty");
            return false;
        }
        
        // DRY VIOLATION: Repeated message formatting
        String subject = "Welcome to Our Platform!";
        String message = "Hi " + userName + ",\\n\\n" +
                        "Welcome to our amazing platform! We're excited to have you on board.\\n" +
                        "Best regards,\\n" +
                        "The Team";
                        
        // DIP VIOLATION: Direct dependency on concrete email implementation
        return sendEmail(userEmail, subject, message);
    }
    
    public boolean sendPasswordResetNotification(String userEmail, String userName, String resetToken) {
        // DRY VIOLATION: Same validation logic repeated
        if (userEmail == null || userEmail.trim().isEmpty()) {
            System.err.println("Invalid email address");
            return false;
        }
        if (!userEmail.contains("@") || !userEmail.contains(".")) {
            System.err.println("Email format is invalid");
            return false;
        }
        if (userName == null || userName.trim().isEmpty()) {
            System.err.println("Username cannot be empty");
            return false;
        }
        
        // DRY VIOLATION: Similar message formatting
        String subject = "Password Reset Request";
        String message = "Hi " + userName + ",\\n\\n" +
                        "You requested a password reset. Click the link below:\\n" +
                        "https://oursite.com/reset?token=" + resetToken + "\\n\\n" +
                        "Best regards,\\n" +
                        "The Team";
        
        return sendEmail(userEmail, subject, message);
    }
    
    // DIP VIOLATION: Direct implementation dependency
    private boolean sendEmail(String email, String subject, String message) {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            
            Session session = Session.getInstance(props);
            MimeMessage emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress("noreply@company.com"));
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            emailMessage.setSubject(subject);
            emailMessage.setText(message);
            
            Transport.send(emailMessage);
            return true;
            
        } catch (MessagingException e) {
            System.err.println("Failed to send email: " + e.getMessage());
            return false;
        }
    }
}

public class SMSNotificationService {
    
    public boolean sendWelcomeNotification(String phoneNumber, String userName) {
        // DRY VIOLATION: Same validation logic repeated again
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            System.err.println("Invalid phone number");
            return false;
        }
        if (!phoneNumber.matches("\\\\+?[1-9]\\\\d{1,14}")) {
            System.err.println("Phone number format is invalid");
            return false;
        }
        if (userName == null || userName.trim().isEmpty()) {
            System.err.println("Username cannot be empty");
            return false;
        }
        
        // DRY VIOLATION: Similar message formatting
        String message = "Hi " + userName + ", welcome to our platform! We're excited to have you!";
        
        return sendSMS(phoneNumber, message);
    }
    
    public boolean sendPasswordResetNotification(String phoneNumber, String userName, String resetToken) {
        // DRY VIOLATION: Same validation logic repeated
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            System.err.println("Invalid phone number");
            return false;
        }
        if (!phoneNumber.matches("\\\\+?[1-9]\\\\d{1,14}")) {
            System.err.println("Phone number format is invalid");
            return false;
        }
        if (userName == null || userName.trim().isEmpty()) {
            System.err.println("Username cannot be empty");
            return false;
        }
        
        // DRY VIOLATION: Similar message formatting
        String message = "Hi " + userName + ", reset your password: https://oursite.com/reset?token=" + resetToken;
        
        return sendSMS(phoneNumber, message);
    }
    
    // DIP VIOLATION: Direct implementation dependency
    private boolean sendSMS(String phoneNumber, String message) {
        try {
            // Simulate SMS API call
            System.out.println("Sending SMS to " + phoneNumber + ": " + message);
            return true;
        } catch (Exception e) {
            System.err.println("Failed to send SMS: " + e.getMessage());
            return false;
        }
    }
}

public class PushNotificationService {
    
    public boolean sendWelcomeNotification(String deviceToken, String userName) {
        // DRY VIOLATION: Same validation logic again
        if (deviceToken == null || deviceToken.trim().isEmpty()) {
            System.err.println("Invalid device token");
            return false;
        }
        if (deviceToken.length() < 32) {
            System.err.println("Device token format is invalid");
            return false;
        }
        if (userName == null || userName.trim().isEmpty()) {
            System.err.println("Username cannot be empty");
            return false;
        }
        
        // DRY VIOLATION: Similar message formatting
        String title = "Welcome!";
        String message = "Hi " + userName + ", welcome to our platform!";
        
        return sendPushNotification(deviceToken, title, message);
    }
    
    // DIP VIOLATION: Direct implementation dependency
    private boolean sendPushNotification(String deviceToken, String title, String message) {
        try {
            System.out.println("Sending push notification to " + deviceToken + ": " + title + " - " + message);
            return true;
        } catch (Exception e) {
            System.err.println("Failed to send push notification: " + e.getMessage());
            return false;
        }
    }
}

// DIP VIOLATION: UserService depends on concrete notification services
public class UserService {
    
    // DIP VIOLATION: Direct dependencies on concrete classes
    private EmailNotificationService emailService = new EmailNotificationService();
    private SMSNotificationService smsService = new SMSNotificationService();
    private PushNotificationService pushService = new PushNotificationService();
    
    public boolean registerUser(String email, String phone, String deviceToken, String userName) {
        // User registration logic...
        
        // DIP VIOLATION: Calling concrete implementations directly
        boolean emailSent = emailService.sendWelcomeNotification(email, userName);
        boolean smsSent = smsService.sendWelcomeNotification(phone, userName);
        boolean pushSent = pushService.sendWelcomeNotification(deviceToken, userName);
        
        return emailSent && smsSent && pushSent;
    }
    
    public boolean initiatePasswordReset(String email, String phone, String userName) {
        String resetToken = generateResetToken();
        
        // DIP VIOLATION: Calling concrete implementations directly
        boolean emailSent = emailService.sendPasswordResetNotification(email, userName, resetToken);
        boolean smsSent = smsService.sendPasswordResetNotification(phone, userName, resetToken);
        
        return emailSent && smsSent;
    }
    
    private String generateResetToken() {
        return UUID.randomUUID().toString();
    }
}