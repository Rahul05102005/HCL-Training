package payment;

public abstract class Payment {

    protected double amount;

    public Payment(double amount) {
        this.amount = amount;
    }

    public abstract void pay();

    public void pay(double amount) {
        this.amount = amount;
        pay();
    }

    public double getAmount() {
        return amount;
    }
}