package main.ru.vasiliydz.javahomework.hw2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class Account {
    private final long id;
    private final TransactionManager transactionManager;
    private final Entries entries;

    public Account(long id, TransactionManager transactionManager) {
        this.id = id;
        this.transactionManager = transactionManager;
        this.entries = new Entries();
    }

    /**
     * Withdraws money from account. <b>Should use TransactionManager to manage transactions</b>
     *
     * @param amount amount of money to withdraw
     * @return true
     * if amount &gt 0 and (currentBalance - amount) &ge 0,
     * otherwise returns false
     */
    public boolean withdraw(double amount, Account beneficiary) {
        try {
            Transaction transaction = transactionManager
                    .createTransaction(amount, this, beneficiary);
            transactionManager.executeTransaction(transaction);
        } catch (TransactionException e) {
            return false;
        }
        return true;
    }

    /**
     * Withdraws cash money from account. <b>Should use TransactionManager to manage transactions</b>
     *
     * @param amount amount of money to withdraw
     * @return true
     * if amount &gt 0 and (currentBalance - amount) &ge 0,
     * otherwise returns false
     */
    public boolean withdrawCash(double amount) {
        try {
            Transaction transaction = transactionManager
                    .createTransactionWithTechnicalBeneficiary(amount, this);
            transactionManager.executeTransaction(transaction);
        } catch (TransactionException e) {
            return false;
        }
        return true;
    }

    /**
     * Adds cash money to account. <b>Should use TransactionManager to manage transactions</b>
     *
     * @param amount amount of money to add
     * @return true
     * if amount &gt 0,
     * otherwise returns false
     */
    public boolean addCash(double amount) {
        try {
            Transaction transaction = transactionManager
                    .createTransactionWithTechnicalOriginator(amount, this);
            transactionManager.executeTransaction(transaction);
        } catch (TransactionException e) {
            return false;
        }
        return true;
    }

    public Collection<Entry> history(LocalDate from, LocalDate to) {
        return entries.betweenDates(from, to);
    }

    public Collection<Entry> allHistoryToDate(LocalDate date) {
        return entries.toTime(date.plusDays(1).atStartOfDay());
    }

    /**
     * Calculates balance on the accounting entries basis
     * @param time
     * @return balance
     */
    public double balanceOn(LocalDateTime time) {
        Collection<Entry> history = entries.toTime(time);
        double balance = 0;
        for (Entry entry : history) {
            balance += entry.getAmount();
        }
        return balance;
    }

    /**
     * Finds the last transaction of the account and rollbacks it
     */
    public void rollbackLastTransaction() {
        Transaction lastTransaction = entries.last().getTransaction();
        transactionManager.rollbackTransaction(lastTransaction);
    }

    void addEntry(Entry entry) {
        entries.addEntry(entry);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}