package main.ru.vasiliydz.javahomework.hw4.report;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelReport implements Report {
	private final HSSFWorkbook workbook;

	ExcelReport(HSSFWorkbook workbook) {
		this.workbook = workbook;
	}

	@Override
	public byte[] asBytes() {
		return workbook.getBytes();
	}
}
