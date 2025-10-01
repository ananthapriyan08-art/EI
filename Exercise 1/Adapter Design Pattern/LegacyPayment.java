// Legacy Code
class LegacyPaymentSystem {
    public void doTransaction(String account, double amount) {
        System.out.println("Legacy transaction for account " + account + " of amount $" + amount);
    }
}

// Target Interface
interface PaymentProcessor {
    void processPayment(String account, double amount);
}

// Adapter
class PaymentAdapter implements PaymentProcessor {
    private LegacyPaymentSystem legacySystem;

    public PaymentAdapter(LegacyPaymentSystem legacySystem) {
        this.legacySystem = legacySystem;
    }

    public void processPayment(String account, double amount) {
        legacySystem.doTransaction(account, amount);
    }
}

// Client Code
public class LegacyPayment {
    public static void main(String[] args) {
        LegacyPaymentSystem legacy = new LegacyPaymentSystem();
        PaymentProcessor processor = new PaymentAdapter(legacy);
        processor.processPayment("123456", 250.0);
    }
}
