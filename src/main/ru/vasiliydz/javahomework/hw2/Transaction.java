package main.ru.vasiliydz.javahomework.hw2;

import java.time.LocalDateTime;

public class Transaction {
	private final long id;
	private final double amount;
	private final Account originator;
	private final Account beneficiary;
	private final boolean executed;
	private final boolean rolledBack;

	Transaction(long id, double amount, Account originator, Account beneficiary) {
		verifyAmountGreaterThanZero(amount);
		this.id = id;
		this.amount = amount;
		this.originator = originator;
		this.beneficiary = beneficiary;
		this.executed = false;
		this.rolledBack = false;
	}

	/**
	 * Adding entries to both accounts
	 * @throws IllegalStateException when was already executed
	 */
	public Transaction execute(LocalDateTime time) {
		verifyNotExecuted();
		verifyNotRolledBack();
		verifyOriginatorHasEnoughMoney(time);
		Transaction newTransaction = new Transaction(this, true, rolledBack);
		originator.addEntry(new Entry(originator, newTransaction, -amount, time));
		beneficiary.addEntry(new Entry(beneficiary, newTransaction, amount, time));
		return newTransaction;
	}

	/**
	 * Removes all entries of current transaction from originator and beneficiary
	 * @throws IllegalStateException when was already rolled back
	 */
	public Transaction rollback(LocalDateTime time) {
		verifyExecuted();
		verifyNotRolledBack();
		Transaction newTransaction = new Transaction(this, executed, true);
		originator.addEntry(new Entry(originator, newTransaction, amount, time));
		beneficiary.addEntry(new Entry(beneficiary, newTransaction, -amount, time));
		return newTransaction;
	}

	long getId() {
		return id;
	}

	Account getOriginator() {
		return originator;
	}

	Account getBeneficiary() {
		return beneficiary;
	}

	double getAmount() {
		return amount;
	}

	private Transaction(Transaction transaction, boolean executed, boolean rolledBack) {
		id = transaction.id;
		amount = transaction.amount;
		originator = transaction.originator;
		beneficiary = transaction.beneficiary;
		this.executed = executed;
		this.rolledBack = rolledBack;
	}

	private void verifyExecuted() {
		if (!executed) {
			throw new IllegalStateException("Transaction " + id + " is not executed yet");
		}
	}

	private void verifyNotExecuted() {
		if (executed) {
			throw new IllegalStateException("Transaction " + id + " is already executed");
		}
	}

	private void verifyNotRolledBack() {
		if (rolledBack) {
			throw new IllegalStateException("Transaction " + id + " is already rolled back");
		}
	}

	private void verifyOriginatorHasEnoughMoney(LocalDateTime time) {
		double balance = originator.balanceOn(time);
		if (balance < amount) {
			throw new TransactionException();
		}
	}

	private void verifyAmountGreaterThanZero(double amount) {
		if (amount <= 0) {
			throw new TransactionException("Amount should not be zero or less");
		}
	}
}
