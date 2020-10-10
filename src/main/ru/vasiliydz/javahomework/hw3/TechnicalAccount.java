package main.ru.vasiliydz.javahomework.hw3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

public class TechnicalAccount implements Account {
	private final long id;
	private final TransactionManager transactionManager;

	public TechnicalAccount(long id, TransactionManager transactionManager) {
		this.id = id;
		this.transactionManager = transactionManager;
	}

	// This account always has money
	@Override
	public double balanceOn(LocalDateTime time) {
		return Double.POSITIVE_INFINITY;
	}

	@Override
	public void addEntry(Entry entry) {
		// do nothing
	}

	@Override
	public Collection<Entry> allHistoryToDate(LocalDate date) {
		// nothing
		return new ArrayList<>();
	}
}
