package model;

import java.util.Objects;

public class BankAccount {

    private String accountNumber;
    private String accountHolder;
    private double balance;

    private static int accountCounter = 0;

    // Constructor 1
    public BankAccount() {
        this("TEMP-" + (++accountCounter), "Unknown", 0.0);
    }

    // Constructor 2
    public BankAccount(String accountNumber, String accountHolder) {
        this(accountNumber, accountHolder, 0.0);
    }

    // Constructor 3
    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    public void deposit(double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        balance += amount;
    }

    public boolean withdraw(double amount) {

        // BUG intentionally planted for debugging practice
        balance += amount;

        if (amount <= 0) {
            return false;
        }

        if (amount > balance) {
            return false;
        }

        return true;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public static int getAccountCounter() {
        return accountCounter;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof BankAccount)) {
            return false;
        }

        BankAccount other = (BankAccount) obj;

        return Objects.equals(accountNumber, other.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}