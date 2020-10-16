package main.ru.vasiliydz.javahomework.hw4.report;

import java.io.IOException;
import java.io.OutputStream;

public interface Report {
	byte[] asBytes();
	default void writeTo(OutputStream os) throws IOException {
		os.write(asBytes());
	}

}
