package main.ru.vasiliydz.javahomework.hw4;

import main.ru.vasiliydz.javahomework.hw3.*;
import main.ru.vasiliydz.javahomework.hw4.report.*;
import org.apache.poi.ss.formula.functions.T;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Main {

	public static void main(String[] args) throws IOException {
		TransactionManager transactionManager = new TransactionManager();
		Account someAccount = generateRealAccount(transactionManager, 100, 50);
		AnalyticsManager manager = new AnalyticsManager(transactionManager);
		List<Transaction> transactions = new ArrayList<>(manager.topTenExpensivePurchases(someAccount));
		System.out.println(transactions.size());
		// пишем отчёт по аналитике
		TableReporter tableReporter = new CsvTableReporter();
		ReportGenerator<Transaction> generator = new TableReportGenerator<>(Transaction.class, tableReporter);
		Report report = generator.generate(transactions);
		report.writeTo(new FileOutputStream("report.csv"));
	}

	// метод для имитации работы аккаунтов
	// возвращает некоторый аккаунт с некоторой историей
	private static Account generateRealAccount(TransactionManager transactionManager,
											   int numOfAccounts, int maxMoney) {
		List<Account> accounts = new ArrayList<>();
		for (int i = 0; i < numOfAccounts; i++) {
			Account account = new DebitCard(i+1, transactionManager);
			// каждому дадим денег
			Transaction transaction = transactionManager.createTransactionWithTechnicalOriginator(
					1+2*numOfAccounts*maxMoney, account);
			transactionManager.executeTransaction(transaction);
			accounts.add(account);
		}
		Random random = new Random();
		// каждый с каждым взаимодействует
		for (int i = 0; i < numOfAccounts; i++) {
			for (int j = 0; j < numOfAccounts; j++) {
				if (j != i) {
					Transaction transaction = transactionManager.createTransaction(
							1+random.nextInt(maxMoney), accounts.get(i), accounts.get(j));
					transactionManager.executeTransaction(transaction);
				}
			}
		}
		int retInd = random.nextInt(numOfAccounts);
		return accounts.get(retInd);
	}
}
