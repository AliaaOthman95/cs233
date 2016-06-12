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
			rehash();
		int hashIndex = key.hashCode() % capacity;
		while (hashTable[hashIndex] != null
				&& hashTable[hashIndex].getKey() != key) {
			col++;
			hashIndex = (hashIndex + 1) % capacity;
		}
		hashTable[hashIndex] = new Pair<K, V>(key, value);
		size++;
	}

	private void rehash() {
		// Pair<K, V>[] hashTable2 = hashTable;
		// //hashTable2 = java.util.Arrays.copyOf(hashTable,hashTable.length);
		// capacity = capacity*2;
		// // System.arraycopy(hashTable, 0, hashTable2, 0, hashTable.length);
		//
		// hashTable = new Pair[capacity];
		// for (int i = 0; i < hashTable2.length; i++) {
		// if(hashTable2[i]!= null)
		// {
		// put(hashTable2[i].getKey(), hashTable2[i].getValue());
		// }
		// }

		capacity = capacity * 2;
		Pair<K, V>[] hashTable2 = new Pair[capacity];

		for (int i = 0; i < hashTable.length; i++) {
			put(hashTable[i].getKey(), hashTable[i].getValue());
		}

		// hashTable = hashTable2 ;
		hashTable = java.util.Arrays.copyOf(hashTable2, hashTable2.length);
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
