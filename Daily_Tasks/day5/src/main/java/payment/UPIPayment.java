package payment;

public class UPIPayment extends Payment implements Refundable {

    public UPIPayment(double amount) {
        super(amount);
    }

    @Override
    public void pay() {
        System.out.println("UPI payment successful: " + amount);
    }

    @Override
    public void refund(double amount) {
        System.out.println("UPI refund processed: " + amount);
    }
}