package main.ru.vasiliydz.javahomework.hw3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleEntitiesStorage<K, V> implements BankEntitiesStorage<K, V> {
	private final Map<K, V> storage = new HashMap<>();
	private final KeyExtractor<? extends K, ? super V> keyExtractor;

	public SimpleEntitiesStorage(KeyExtractor<? extends K, ? super V> keyExtractor) {
		this.keyExtractor = keyExtractor;
	}

	@Override
	public void save(V entity) {
		K key = keyExtractor.extract(entity);
		storage.put(key, entity);
	}

	@Override
	public void saveAll(List<? extends V> entities) {
		for (V entity : entities) {
			save(entity);
		}
	}

	@Override
	public V findByKey(K key) {
		return storage.get(key);
	}

	@Override
	public List<V> findAll() {
		return new ArrayList<>(storage.values());
	}

	@Override
	public void deleteByKey(K key) {
		storage.remove(key);
	}

	@Override
	public void deleteAll(List<? extends V> entities) {
		for (V entity : entities) {
			K key = keyExtractor.extract(entity);
			deleteByKey(key);
		}
	}
}
