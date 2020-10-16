package main.ru.vasiliydz.javahomework.hw4.report;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.nio.charset.Charset;

public class CsvTableReporter implements TableReporter {
	private final CSVFormat format;
	private final Charset charset;

	public CsvTableReporter() {
		this.format = CSVFormat.DEFAULT;
		this.charset = Charset.defaultCharset();
	}

	public CsvTableReporter withDelimiter(char delimiter) {
		return new CsvTableReporter(this, delimiter);
	}

	public CsvTableReporter withCharset(Charset charset) {
		return new CsvTableReporter(this, charset);
	}

	private CsvTableReporter(CsvTableReporter old, char delimiter) {
		this.format = old.format.withDelimiter(delimiter);
		this.charset = old.charset;
	}

	private CsvTableReporter(CsvTableReporter old, Charset charset) {
		this.format = old.format;
		this.charset = charset;
	}

	@Override
	public CsvReport makeReport(Table table) {
		StringBuilder data = new StringBuilder();
		try (CSVPrinter csvPrinter = new CSVPrinter(data, format)) {
			csvPrinter.printRecord(table.getColumnNames());
			for (Table.Row row : table) {
				csvPrinter.printRecord(row);
			}
			return new CsvReport(data.toString(), charset);
		} catch (IOException e) {
			throw new RuntimeException("Something has gone wrong with writing CSV-String");
		}
	}
}
