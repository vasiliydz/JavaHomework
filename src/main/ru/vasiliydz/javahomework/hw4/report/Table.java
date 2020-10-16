package main.ru.vasiliydz.javahomework.hw4.report;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Table implements Iterable<Table.Row> {
	private final List<String> columnNames;
	private final List<Row> rows;

	Table(List<String> columnNames, List<Row> rows) {
		this.columnNames = columnNames;
		this.rows = rows;
	}

	public List<String> getColumnNames() {
		return new ArrayList<>(columnNames);
	}

	@Override
	public Iterator<Row> iterator() {
		return rows.iterator();
	}

	public static class Row implements Iterable<String> {
		private final List<String> values;

		Row(List<String> values) {
			this.values = values;
		}

		@Override
		public Iterator<String> iterator() {
			return values.iterator();
		}
	}

}
