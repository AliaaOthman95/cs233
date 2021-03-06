package eg.edu.alexu.csd.filestructure.hash;

import java.util.ArrayList;

public class Double<K, V> implements IHash<K, V>, IHashDouble {
	private static final double MAX_LOAD_FACTOR = 0.75;
	private int capacity = 1200;
	private int col = 0, size = 0;
	private Pair<K, V>[] hashTable = new Pair[capacity];
	private ArrayList<K> keys = new ArrayList<K>();

	@Override
	public void put(K key, V value) {
////		if (size == capacity) {
////			col += capacity + 1;
////			rehash();
//		}
		int hashIndex = key.hashCode() % capacity;
		int h = hashIndex;
		int i = 0;
		while (hashTable[hashIndex] != null
				&& hashTable[hashIndex].getKey() != key) {
			i++;
			col++;
			hashIndex = (h + i * hash((Integer) key)) % capacity;

			if (i == capacity) {
				col++;
				rehash();
				put(key, value);
				return;

			}
		}
		hashTable[hashIndex] = new Pair<K, V>(key, value);
		size++;
	}

	public int hash(int k) {
		int fun = 1193 - k % 1193;
		return fun;

	}

	private void rehash() {
		size = 0;
		Pair<K, V>[] temp = new Pair[capacity];
		for (int i = 0; i < capacity; i++) {
			temp[i] = hashTable[i];
		}
		int cap = capacity;
		capacity *= 2;
		hashTable = new Pair[capacity];
		for (int i = 0; i < capacity / 2; i++) {
			if (temp[i] != null)
				put(temp[i].getKey(), temp[i].getValue());
		}
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

		for (int i = 0; i < hashTable.length; i++) {
			if (hashTable[i] != null)
				keys.add(hashTable[i].getKey());
		}
		return keys;
	}

}
