package main.ru.vasiliydz.javahomework.hw3;

import java.time.LocalDateTime;

/**
 * The record of allocating the amount to the account
 * Amount can be either positive or negative depending on originator or beneficiary
 */
public class Entry {
	private final Account account;
	private final Transaction transaction;
	private final double amount;
	private final LocalDateTime time;

	public Entry(Account account, Transaction transaction, double amount, LocalDateTime time) {
		this.account = account;
		this.transaction = transaction;
		this.amount = amount;
		this.time = time;
	}

	LocalDateTime getTime() {
		return time;
	}

	Transaction getTransaction() {
		return transaction;
	}

	double getAmount() {
		return amount;
	}
}
