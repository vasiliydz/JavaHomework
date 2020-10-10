package main.ru.vasiliydz.javahomework.hw3;

public class BonusAccount extends DebitCard {
	private final double bonusPercent;

	public BonusAccount(long id, TransactionManager transactionManager, double bonusPercent) {
		super(id, transactionManager);
		if (bonusPercent < 0 || bonusPercent > 1) {
			throw new IllegalArgumentException("BonusPercent should be between 0 and 1");
		}
		this.bonusPercent = bonusPercent;
	}

	@Override
	public boolean withdraw(double amount, Account beneficiary) {
		boolean ret = super.withdraw(amount, beneficiary);
		super.addCash(amount * bonusPercent);
		return ret;
	}
}
