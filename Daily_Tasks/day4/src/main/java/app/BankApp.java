package app;

import model.BankAccount;
import service.BankService;

public class BankApp {

    public static void main(String[] args) {

        BankAccount account =
                new BankAccount("ACC1001", "Rahul", 5000.0);

        BankService service = new BankService();

        System.out.println("Account Holder: " + account.getAccountHolder());
        System.out.println("Initial Balance: " + account.getBalance());

        service.processWithdrawal(account, 1000.0);

        System.out.println("Final Balance: " + account.getBalance());
    }
}