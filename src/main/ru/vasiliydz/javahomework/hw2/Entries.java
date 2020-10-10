package main.ru.vasiliydz.javahomework.hw2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.TreeMap;
import java.util.Comparator;

/**
 * Collection of entries for the account. Use it to save and get history of payments
 */
public class Entries {
	private final TreeMap<Key, Entry> entryMap;

	Entries() {
		entryMap = new TreeMap<>();
	}

	void addEntry(Entry entry) {
		entryMap.put(new Key(entry), entry);
	}

	Collection<Entry> from(LocalDate date) {
		Key fromKey = new Key(date.atStartOfDay(), Long.MIN_VALUE);
		return entryMap.tailMap(fromKey).values();
	}

	Collection<Entry> toTime(LocalDateTime time) {
		Key toKey = new Key(time, Long.MAX_VALUE);
		return entryMap.headMap(toKey).values();
	}

	Collection<Entry> betweenDates(LocalDate from, LocalDate to) {
		Key fromKey = new Key(from.atStartOfDay(), Long.MIN_VALUE);
		Key toKey = new Key(to.plusDays(1).atStartOfDay(), Long.MAX_VALUE);
		return entryMap.subMap(fromKey, toKey).values();
	}

	Entry last() {
		return entryMap.lastEntry().getValue();
	}

	private static class Key implements Comparable<Key> {
		private final LocalDateTime time;
		private final long transactionId;

		private static final Comparator<Key> comparator = Comparator.comparing(Key::getTime)
				.thenComparing(Key::getTransactionId);

		Key(LocalDateTime time, long transactionId) {
			this.time = time;
			this.transactionId = transactionId;
		}

		Key(Entry entry) {
			this(entry.getTime(), entry.getTransaction().getId());
		}

		@Override
		public int compareTo(Key otherKey) {
			return comparator.compare(this, otherKey);
		}

		private LocalDateTime getTime() {
			return time;
		}

		private long getTransactionId() {
			return transactionId;
		}
	}
}
