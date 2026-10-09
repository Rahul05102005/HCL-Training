package strategy;

public class UPIStrategy implements PaymentStrategy {

    @Override
    public void execute(double amount) {
        System.out.println("Processing UPI payment: " + amount);
    }
}