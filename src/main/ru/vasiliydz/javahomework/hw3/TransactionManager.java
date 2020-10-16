package main.ru.vasiliydz.javahomework.hw3;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * Manages all transactions within the application
 */
public class TransactionManager {
	private final HashMap<Account, Collection<Transaction>> transactionsByAccount;
	private long nextTransactionId;
	private final TechnicalAccount technicalAccount;


	public TransactionManager() {
		transactionsByAccount = new HashMap<>();
		nextTransactionId = 1;
		technicalAccount = new TechnicalAccount(0, this);
	}

	/**
	 * Creates and stores transactions
	 * @param amount
	 * @param originator
	 * @param beneficiary
	 * @return created Transaction
	 */
	public Transaction createTransaction(double amount,
										 Account originator,
										 Account beneficiary) {
		Transaction transaction =  new Transaction(generateTransactionId(), amount, originator, beneficiary);
		putTransactionToStorage(transaction);
		return transaction;
	}

	public Transaction createTransactionWithTechnicalBeneficiary(double amount, Account account) {
		return createTransaction(amount, account, technicalAccount);
	}

	public Transaction createTransactionWithTechnicalOriginator(double amount, Account account) {
		return createTransaction(amount, technicalAccount, account);
	}

	public Collection<Transaction> findAllTransactionsByAccount(Account account) {
		return new ArrayList<>(transactionsByAccount.get(account));
	}


	public void rollbackTransaction(Transaction transaction) {
		Transaction newTransaction = transaction.rollback(LocalDateTime.now());
		putTransactionToStorage(newTransaction);
	}

	public void executeTransaction(Transaction transaction) {
		Transaction newTransaction = transaction.execute(LocalDateTime.now());
		putTransactionToStorage(newTransaction);
	}

	private long generateTransactionId() {
		nextTransactionId++;
		return nextTransactionId - 1;
	}

	private void putTransactionToStorage(Transaction transaction) {
		putTransactionToStorageByAccount(transaction, transaction.getOriginator());
		putTransactionToStorageByAccount(transaction, transaction.getBeneficiary());
	}

	private void putTransactionToStorageByAccount(Transaction transaction, Account account) {
		if (!transactionsByAccount.containsKey(account)) {
			transactionsByAccount.put(account, new ArrayList<>());
		}
		transactionsByAccount.get(account).add(transaction);
	}


}
