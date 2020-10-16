package main.ru.vasiliydz.javahomework.hw4.report;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableReportGenerator<T> implements ReportGenerator<T> {
	private final Class<T> classT;
	private final TableReporter reportMaker;
	private final ObjectToMapTranslator<T> entriesTranslator;
	private final Map<String, String> aliases;
	

	public TableReportGenerator(Class<T> classT, TableReporter reportMaker) {
		this.classT = classT;
		this.reportMaker = reportMaker;
		entriesTranslator = new ObjectToMapTranslator<>(classT);
		aliases = new HashMap<>();
	}

	public TableReportGenerator(Class<T> classT, TableReporter reportMaker,
								List<String> fieldNames) {
		this.classT = classT;
		this.reportMaker = reportMaker;
		entriesTranslator = new ObjectToMapTranslator<>(classT, fieldNames);
		aliases = new HashMap<>();
	}

	public TableReportGenerator<T> addAlias(String fieldName, String alias) {
		if (!entriesTranslator.hasField(fieldName)) {
			throw new IllegalArgumentException("This report generator does not contain " +
					"field " + fieldName);
		}
		Map<String, String> newAliases = new HashMap<>(aliases);
		newAliases.put(fieldName, alias);
		return new TableReportGenerator<>(this, newAliases);
	}

	private TableReportGenerator(TableReportGenerator<T> old,
								 Map<String, String> aliases) {
		this.classT = old.classT;
		this.reportMaker = old.reportMaker;
		this.entriesTranslator = old.entriesTranslator;
		this.aliases = aliases;
	}

	@Override
	public Report generate(List<T> entities) {
		return reportMaker.makeReport(generateTable(entities));
	}

	private Table generateTable(List<T> entities) {
		TableBuilder builder = new TableBuilder(entriesTranslator.getFieldNames(), aliases);
		for (T entity : entities) {
			Map<String, String> values = entriesTranslator.translate(entity);
			builder.addRow(values);
		}
		return builder.build();
	}

}
