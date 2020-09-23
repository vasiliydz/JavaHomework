package ru.vasiliydz.javahomework;

public class Account {
    private final long id;
    private double balance;

    public Account(long id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public Account(long id) {
        this(id, 0);
    }

    /**
     * Withdraws money from account balance
     *
     * @param amount amount of money to withdraw
     * @return true
     * if amount &gt 0 and (balance - amount) &gt 0,
     * otherwise returns false
     */
    public boolean withdraw(double amount) {
        if (canWithdraw(amount)) {
            balance -= amount;
            return true;
        }
        return false;
    }

    /**
     * Adds money to account balance
     *
     * @param amount amount of money to add on account
     * @return true if amount &gt 0, otherwise returns false
     */
    public boolean add(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    private boolean canWithdraw(double amount) {
        return amount > 0
                && (balance - amount >= 0);
    }
}