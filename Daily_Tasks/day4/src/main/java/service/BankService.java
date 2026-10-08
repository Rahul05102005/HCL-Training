package service;

import model.BankAccount;

public class BankService {

    public void processWithdrawal(BankAccount account, double amount) {

        boolean success = account.withdraw(amount);

        if (success) {
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Withdrawal failed.");
        }

        System.out.println("Balance: " + account.getBalance());
    }
}