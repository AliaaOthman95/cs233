package eg.edu.alexu.csd.filestructure.hash;

import java.util.ArrayList;

public class Linear<K, V> implements IHash<K, V>, IHashLinearProbing {

	private int capacity = 1200;
	private int col = 0, size = 0;
	private Pair<K, V>[] hashTable = new Pair[capacity];
	private ArrayList<K> keys = new ArrayList<K>();

	@Override
	public void put(K key, V value) {
		if (size == capacity)
			rehash(capacity * 2);
		int hashIndex = key.hashCode() % capacity;
		while (hashTable[hashIndex] != null
				&& hashTable[hashIndex].getKey() != key) {
			col++;
			hashIndex = (hashIndex + 1) % capacity;
		}
		hashTable[hashIndex] = new Pair<K, V>(key, value);
		size++;
	}

	private void rehash(int cap) {
		Pair<K, V>[] hashTable2 = hashTable;
		capacity = cap;
		// System.arraycopy(hashTable, 0, hashTable2, 0, hashTable.length);

		hashTable = new Pair[capacity];
		for (int i = 0; i < hashTable2.length; i++) {
			put(hashTable2[i].getKey(), hashTable2[i].getValue());
		}

		// capacity = cap ;
		// Pair<K, V>[] hashTable2 = new Pair[capacity];
		//
		// for (int i = 0; i < hashTable.length; i++) {
		// put(hashTable[i].getKey(), hashTable[i].getValue());
		// }
		//
		// hashTable = hashTable2 ;
	}

	@Override
	public String get(K key) {
		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] != null && hashTable[i].getKey().equals(key))
				return (String) hashTable[i].getValue();
		}
		return null;
	}

	@Override
	public void delete(K key) {
		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] != null && hashTable[i].getKey().equals(key)) {
				hashTable[i] = null;
				size--;
			}
		}

	}

	@Override
	public boolean contains(K key) {
		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] != null && hashTable[i].getKey().equals(key))
				return true;
		}
		return false;
	}

	@Override
	public boolean isEmpty() {
		int counter = 0;
		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] != null) {
				counter++;
				break;
			}
		}
		if (counter == 0)
			return true;
		return false;
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public int capacity() {

		return capacity;
	}

	@Override
	public int collisions() {

		return col;
	}

	@Override
	public Iterable<K> keys() {

		return keys;
	}

}
