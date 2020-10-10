package main.ru.vasiliydz.javahomework.hw3;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public interface Account {
	double balanceOn(LocalDateTime time);
	void addEntry(Entry entry);
	Collection<Entry> allHistoryToDate(LocalDate date);
}