package ru.vasiliydz.javahomework;

public class Customer {
    private final String name;
    private final String lastName;
    private Account account;

    public Customer(String name, String lastName, Account account) {
        if (namesAreNull(name, lastName)) {
            throw new IllegalArgumentException("Name and Last Name should not be empty");
        }
        this.name = name;
        this.lastName = lastName;
        this.account = account;
    }

    public Customer(String name, String lastName) {
        this(name, lastName, null);
    }

    /**
     * Opens account for a customer (creates Account and sets it to field "account").
     * Customer can't have greater than one opened account.
     *
     * @param accountId id of the account
     * @return true if account hasn't already created, otherwise returns false and prints "Customer fullName() already has the active account"
     */
    public boolean openAccount(long accountId) {
        if (account != null) {
            System.out.println("Customer " + fullName() + " already has the active account");
            return false;
        }
        account = new Account(accountId);
        return true;
    }

    /**
     * Closes account. Sets account to null.
     *
     * @return false if account is already null and prints "Customer fullName() has no active account to close", otherwise sets account to null and returns true
     */
    public boolean closeAccount() {
        if (account == null) {
            System.out.println("Customer " + fullName() + " has no active account to close");
            return false;
        }
        account = null;
        return true;
    }

    /**
     * Formatted full name of the customer
     *
     * @return concatenated form of name and lastName, e.g. "John Goodman"
     */
    public String fullName() {
        return name + " " + lastName;
    }

    /**
     * Delegates withdraw to Account class
     *
     * @param amount amount of money to withdraw
     * @return false if account is null and prints "Customer fullName() has no active account", otherwise returns the result of Account's withdraw method
     */
    public boolean withdrawFromCurrentAccount(double amount) {
        if (account == null) {
            System.out.println("Customer " + fullName() + " has no active account");
            return false;
        }
        return account.withdraw(amount);
    }

    /**
     * Delegates adding money to Account class
     *
     * @param amount amount of money to add
     * @return false if account is null and prints "Customer fullName() has no active account", otherwise returns the result of Account's add method
     */
    public boolean addMoneyToCurrentAccount(double amount) {
        if (account == null) {
            System.out.println("Customer " + fullName() + " has no active account");
            return false;
        }
        return account.add(amount);
    }

    private boolean namesAreNull(String name, String lastName) {
        return name == null || lastName == null;
    }
}