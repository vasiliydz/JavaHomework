package main.ru.vasiliydz.javahomework.hw4.report;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public class ExcelTableReporter implements TableReporter {
	@Override
	public ExcelReport makeReport(Table table) {
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet();
		addHeaderToSheet(sheet, table.getColumnNames());
		for (Table.Row row : table) {
			addRowToSheet(sheet, row);
		}
		return new ExcelReport(workbook);
	}

	private void addHeaderToSheet(HSSFSheet sheet, List<String> columnNames) {
		HSSFRow hssfRow = sheet.createRow(0);
		for (int i = 0; i < columnNames.size(); i++) {
			HSSFCell cell = hssfRow.createCell(i);
			cell.setCellValue(columnNames.get(i));
		}
	}

	private void addRowToSheet(HSSFSheet sheet, Table.Row row) {
		HSSFRow hssfRow = sheet.createRow(sheet.getLastRowNum() + 1);
		int cellIndex = 0;
		for (String value : row) {
			HSSFCell cell = hssfRow.createCell(cellIndex);
			cell.setCellValue(value);
			cellIndex++;
		}
	}
}
