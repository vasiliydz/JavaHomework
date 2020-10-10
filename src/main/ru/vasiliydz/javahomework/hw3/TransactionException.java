package main.ru.vasiliydz.javahomework.hw3;

public class TransactionException extends RuntimeException {
	public TransactionException() {
		super();
	}
	public TransactionException(String message) {
		super(message);
	}
}