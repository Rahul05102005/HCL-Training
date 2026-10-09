package strategy;

public class CardStrategy implements PaymentStrategy {

    @Override
    public void execute(double amount) {
        System.out.println("Processing card payment: " + amount);
    }
}