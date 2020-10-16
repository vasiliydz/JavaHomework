package main.ru.vasiliydz.javahomework.hw3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


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
			Transaction transaction = entry.getTransaction();
			if (transaction.getOriginator() == account) {
				transactions.add(entry.getTransaction());
			}
		}
		transactions.sort(Comparator.comparingDouble(Transaction::getAmount));
		int startInd = Integer.max(transactions.size() - 10, 0);
		Collection<Transaction> ret = new ArrayList<>();
		for (int i = startInd; i < transactions.size(); i++) {
			ret.add(transactions.get(i));
		}
		return ret;
	}

	public double overallBalanceOfAccounts(List<? extends Account> accounts) {
		LocalDateTime now = LocalDateTime.now();
		double sum = 0;
		for (Account account : accounts) {
			sum += account.balanceOn(now);
		}
		return sum;
	}

	public <K> Set<K> uniqueKeysOf(List<? extends Account> accounts,
								   KeyExtractor<? extends K, ? super Account> extractor) {
		Set<K> retSet = new HashSet<>();
		for (Account account : accounts) {
			retSet.add(extractor.extract(account));
		}
		return retSet;
	}

	public List<Account> accountsRangeFrom(
			List<? extends Account> accounts, Account minAccount, Comparator<? super Account> comparator) {
		List<Account> retList = new ArrayList<>();
		for (Account account : accounts) {
			if (comparator.compare(account, minAccount) >= 0) {
				retList.add(account);
			}
		}
		return retList;
	}
}