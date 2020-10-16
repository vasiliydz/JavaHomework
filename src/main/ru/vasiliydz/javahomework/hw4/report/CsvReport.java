package main.ru.vasiliydz.javahomework.hw4.report;

import java.nio.charset.Charset;

public class CsvReport implements Report {
	private final String data;
	private final Charset charset;

	CsvReport(String data, Charset charset) {
		this.data = data;
		this.charset = charset;
	}

	@Override
	public byte[] asBytes() {
		return data.getBytes(charset);
	}
}
