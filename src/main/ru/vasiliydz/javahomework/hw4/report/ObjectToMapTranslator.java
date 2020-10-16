package main.ru.vasiliydz.javahomework.hw4.report;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectToMapTranslator<T> {
	private final Class<T> classT;
	private final List<String> fieldNames;

	ObjectToMapTranslator(Class<T> classT) {
		this.classT = classT;
		fieldNames = createDefaultFieldNames();
	}

	ObjectToMapTranslator(Class<T> classT, List<String> newFieldNames) {
		this.classT = classT;
		verifyFieldNames(newFieldNames);
		this.fieldNames = newFieldNames;
	}

	List<String> getFieldNames() {
		return fieldNames;
	}

	boolean hasField(String fieldName) {
		return fieldNames.contains(fieldName);
	}

	Map<String, String> translate(T obj) {
		Map<String, String> map = new HashMap<>();
		try {
			for (String fieldName : fieldNames) {
				Field field = classT.getDeclaredField(fieldName);
				field.setAccessible(true);
				Object fieldValue = field.get(obj);
				map.put(fieldName, fieldValue.toString());
			}
			return map;
		} catch (NoSuchFieldException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private List<String> createDefaultFieldNames() {
		List<String> fieldNames = new ArrayList<>();
		for (Field field : classT.getDeclaredFields()) {
			fieldNames.add(field.getName());
		}
		return fieldNames;
	}

	private void verifyFieldNames(List<String> fieldNames) {
		for (String fieldName : fieldNames) {
			try {
				classT.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				throw new IllegalArgumentException("Class " + classT.getName() + " does not " +
						"have a field " + fieldName);
			}
		}
	}
}
