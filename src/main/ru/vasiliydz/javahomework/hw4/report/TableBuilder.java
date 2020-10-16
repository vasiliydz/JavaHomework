package main.ru.vasiliydz.javahomework.hw4.report;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TableBuilder {
	private final List<String> columnNames;
	private final Map<String, String> columnAliases;
	private final List<Table.Row> rows;

	public TableBuilder(List<String> columnNames, Map<String, String> columnAliases) {
		this.columnNames = new ArrayList<>(columnNames);
		this.columnAliases = columnAliases;
		rows = new ArrayList<>();
	}

	public void addRow(Map<String, String> rowValuesMap) {
		List<String> rowValues = new ArrayList<>();
		for (String columnName : columnNames) {
			verifyMapHasValueForColumn(rowValuesMap, columnName);
			rowValues.add(rowValuesMap.get(columnName));
		}
		rows.add(new Table.Row(rowValues));
	}

	public Table build() {
		return new Table(getColumnAliases(), rows);
	}

	private String getColumnAlias(String columnName) {
		verifyTableHasColumn(columnName);
		return columnAliases.getOrDefault(columnName, columnName);
	}

	private List<String> getColumnAliases() {
		List<String> aliases = new ArrayList<>();
		for (String columnName : columnNames) {
			aliases.add(getColumnAlias(columnName));
		}
		return aliases;
	}

	private void verifyTableHasColumn(String columnName) {
		if (!columnNames.contains(columnName)) {
			throw new IllegalArgumentException("Table does not have a column " + columnName);
		}
	}

	private void verifyMapHasValueForColumn(Map<String, String> valuesMap, String columnName) {
		if (!valuesMap.containsKey(columnName)) {
			throw new IllegalArgumentException("RowValuesMap does not have a value for " +
					"column " + columnName);
		}
	}

}
