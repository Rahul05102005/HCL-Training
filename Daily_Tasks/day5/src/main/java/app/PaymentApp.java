package app;

import payment.CardPayment;
import payment.CashPayment;
import payment.Payment;
import payment.Refundable;
import payment.UPIPayment;

public class PaymentApp {

    public static void main(String[] args) {

        Payment[] payments = {
            new CardPayment(1500),
            new UPIPayment(750),
            new CashPayment(500)
        };

        for (Payment payment : payments) {
            payment.pay();
        }

        Payment card = new CardPayment(2000);
        card.pay(2500);

        Refundable refundable = new CardPayment(1500);
        refundable.refund(500);
    }
}