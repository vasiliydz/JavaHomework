package main.ru.vasiliydz.javahomework.hw2;

import java.time.LocalDateTime;

public class TechnicalAccount extends Account {
	public TechnicalAccount(long id, TransactionManager transactionManager) {
		super(id, transactionManager);
	}

	// This account always has money
	@Override
	public double balanceOn(LocalDateTime time) {
		return Double.POSITIVE_INFINITY;
	}
}
