package main.ru.vasiliydz.javahomework.hw2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;


public class AnalyticsManager {
	private final TransactionManager transactionManager;

	public AnalyticsManager(TransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}

	public Account mostFrequentBeneficiaryOfAccount(Account account) {
		Collection<Entry> history = account.allHistoryToDate(LocalDate.now());
		Map<Account, Integer> beneficiaryCounter = new HashMap<>();
		Account maxAccount = null;
		int maxNum = 0;
		for (Entry entry : history) {
			Account beneficiary = entry.getTransaction().getBeneficiary();
			int value = beneficiaryCounter.getOrDefault(beneficiary, 0) + 1;
			beneficiaryCounter.put(account, value);
			if (value > maxNum) {
				maxNum = value;
				maxAccount = beneficiary;
			}
		}
		return maxAccount;
	}

	public Collection<Transaction> topTenExpensivePurchases(Account account) {
		Collection<Entry> history = account.allHistoryToDate(LocalDate.now());
		ArrayList<Transaction> transactions = new ArrayList<>();
		for (Entry entry : history) {
			transactions.add(entry.getTransaction());
		}
		transactions.sort(Comparator.comparingDouble(Transaction::getAmount));
		int startInd = Integer.max(transactions.size() - 10, 0);
		Collection<Transaction> ret = new ArrayList<>();
		for (int i = startInd; i < transactions.size(); i++) {
			ret.add(transactions.get(i));
		}
		return ret;
	}
}