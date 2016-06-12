package eg.edu.alexu.csd.filestructure.hash;

import java.util.ArrayList;

public class Chaining<K, V> implements IHash<K, V>, IHashChaining {

	private int size = 1200;
	private int col =0;
	private ArrayList<Pair<K, V>> hashTable[] = new ArrayList[size];
	private ArrayList<K> keys = new ArrayList<K>();

	@Override
	public void put(K key, V value) {
		int hashIndex = key.hashCode() % size;
		boolean found = false;
		if (hashTable[hashIndex] == null) {
			ArrayList<Pair<K, V>> chain = new ArrayList<>();
			chain.add(new Pair<K, V>(key, value));
			hashTable[hashIndex] = chain;
			keys.add(key);
		} else {
			col++;
			ArrayList<Pair<K, V>> entry = hashTable[hashIndex];
			for (Pair<K, V> element : entry) {

				if (element.getKey().equals(key)) {

					element.setValue(value);
					found = true;
					break;
				}
			}
			if (!found) {
				entry.add(new Pair<K, V>(key, value));
				keys.add(key);
			}

		}

	}

	@Override
	public String get(K key) {
		int hashIndex = key.hashCode();
		if (hashTable[hashIndex] != null) {
			ArrayList<Pair<K, V>> entry = hashTable[hashIndex];
			for (Pair<K, V> element : entry) {

				if (element.getKey().equals(key)) {

					return (String) element.getValue();
				}
			}
		}
		return null;
	}

	@Override
	public void delete(K key) {
		int hashIndex = key.hashCode();
		if (hashTable[hashIndex] != null) {
			keys.remove(key);
			ArrayList<Pair<K, V>> entry = hashTable[hashIndex];
			if (entry.size() == 1) {
				hashTable[hashIndex] = null;

			} else {
				for (Pair<K, V> element : entry) {

					if (element.getKey().equals(key)) {

						entry.remove(element);
					}
				}
			}
		}

	}

	@Override
	public boolean contains(K key) {

		if (get(key) != null)
			return true;
		return false;
	}

	@Override
	public boolean isEmpty() {

		int counter = 0;
		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] != null) {
				counter++;
			}
		}
		if (counter == 0)
			return true;
		return false;
	}

	@Override
	public int size() {

		int counter = 0;
		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] != null) {
				counter++;
			}
		}
		return counter;
	}

	@Override
	public int capacity() {

		return size;
	}

	@Override
	public int collisions() {
	
		return col;
	}

	@Override
	public Iterable keys() {
	
		return keys;
	}

}
