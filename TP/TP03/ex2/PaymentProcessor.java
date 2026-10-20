import java.util.*;

// OCP VIOLATION: PaymentProcessor class needs modification for new payment types
public class PaymentProcessor {
    
    public boolean processPayment(String paymentType, double amount, Map<String, String> details) {
        // Violates OCP - need to modify this method for each new payment type
        if (paymentType.equals("CREDIT_CARD")) {
            return processCreditCard(amount, details);
        } else if (paymentType.equals("PAYPAL")) {
            return processPayPal(amount, details);
        } else if (paymentType.equals("BANK_TRANSFER")) {
            return processBankTransfer(amount, details);
        }
        // Adding new payment methods requires modifying this method
        throw new UnsupportedOperationException("Payment type not supported: " + paymentType);
    }
    
    private boolean processCreditCard(double amount, Map<String, String> details) {
        String cardNumber = details.get("cardNumber");
        String expiryDate = details.get("expiryDate");
        String cvv = details.get("cvv");
        
        // Credit card processing logic
        if (cardNumber == null || cardNumber.length() != 16) {
            return false;
        }
        
        // Simulate API call
        System.out.println("Processing credit card payment of $" + amount);
        return true;
    }
    
    private boolean processPayPal(double amount, Map<String, String> details) {
        String email = details.get("email");
        String password = details.get("password");
        
        // PayPal processing logic
        if (email == null || !email.contains("@")) {
            return false;
        }
        
        System.out.println("Processing PayPal payment of $" + amount);
        return true;
    }
    
    private boolean processBankTransfer(double amount, Map<String, String> details) {
        String accountNumber = details.get("accountNumber");
        String routingNumber = details.get("routingNumber");
        
        // Bank transfer processing logic
        if (accountNumber == null || accountNumber.length() < 8) {
            return false;
        }
        
        System.out.println("Processing bank transfer of $" + amount);
        return true;
    }
}

// ISP VIOLATION: Interface too broad, forces unnecessary implementations
interface PaymentOperations {
    boolean processPayment(double amount, Map<String, String> details);
    boolean refundPayment(String transactionId, double amount);
    boolean validatePaymentDetails(Map<String, String> details);
    String generatePaymentToken(Map<String, String> details);
    boolean supports3DSecure();  // Not all payment methods need this
    boolean supportsInstallments();  // Not all payment methods need this
    List<String> getSupportedCurrencies();  // Not all need this
    double getProcessingFee(double amount);
    boolean requiresPhysicalCard();  // Only credit cards need this
    boolean supportsRecurringPayments();  // Not all need this
}

// LSP VIOLATION: CryptoCurrencyPayment breaks substitutability
class CreditCardPayment implements PaymentOperations {
    @Override
    public boolean processPayment(double amount, Map<String, String> details) {
        return amount > 0 && amount <= 10000;  // Credit cards have limits
    }
    
    @Override
    public boolean refundPayment(String transactionId, double amount) {
        return true;  // Credit cards support refunds
    }
    
    @Override
    public boolean validatePaymentDetails(Map<String, String> details) {
        return details.containsKey("cardNumber") && details.containsKey("cvv");
    }
    
    @Override
    public String generatePaymentToken(Map<String, String> details) {
        return "cc_" + System.currentTimeMillis();
    }
    
    @Override
    public boolean supports3DSecure() { return true; }
    
    @Override
    public boolean supportsInstallments() { return true; }
    
    @Override
    public List<String> getSupportedCurrencies() {
        return Arrays.asList("USD", "EUR", "GBP");
    }
    
    @Override
    public double getProcessingFee(double amount) { return amount * 0.029; }
    
    @Override
    public boolean requiresPhysicalCard() { return true; }
    
    @Override
    public boolean supportsRecurringPayments() { return true; }
}

// LSP VIOLATION: This class violates LSP by throwing exceptions for inherited behavior
class CryptoCurrencyPayment implements PaymentOperations {
    @Override
    public boolean processPayment(double amount, Map<String, String> details) {
        // Violates LSP - completely different behavior than expected
        if (amount < 50) {
            throw new IllegalArgumentException("Minimum crypto payment is $50");
        }
        return true;
    }
    
    @Override
    public boolean refundPayment(String transactionId, double amount) {
        // Violates LSP - crypto payments typically can't be refunded
        throw new UnsupportedOperationException("Crypto payments cannot be refunded");
    }
    
    @Override
    public boolean validatePaymentDetails(Map<String, String> details) {
        return details.containsKey("walletAddress");
    }
    
    @Override
    public String generatePaymentToken(Map<String, String> details) {
        return "crypto_" + System.currentTimeMillis();
    }
    
    // ISP violation - forced to implement methods that don't make sense for crypto
    @Override
    public boolean supports3DSecure() { 
        throw new UnsupportedOperationException("3D Secure not applicable for crypto");
    }
    
    @Override
    public boolean supportsInstallments() { 
        throw new UnsupportedOperationException("Installments not supported for crypto");
    }
    
    @Override
    public List<String> getSupportedCurrencies() {
        return Arrays.asList("BTC", "ETH", "LTC");
    }
    
    @Override
    public double getProcessingFee(double amount) { return 5.0; }  // Flat fee
    
    @Override
    public boolean requiresPhysicalCard() { 
        throw new UnsupportedOperationException("Crypto doesn't use physical cards");
    }
    
    @Override
    public boolean supportsRecurringPayments() { return false; }
}