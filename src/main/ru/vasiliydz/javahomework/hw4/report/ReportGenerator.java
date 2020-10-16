package main.ru.vasiliydz.javahomework.hw4.report;

import java.util.List;

public interface ReportGenerator<T> {
	Report generate(List<T> entities);
}
