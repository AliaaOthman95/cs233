package eg.edu.alexu.csd.filestructure.avl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary implements IDictionary {

	private AVLTree<String> myDictionary = new AVLTree<String>();
	private int size = 0;

	@Override
	public void load(File file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				myDictionary.insert(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean insert(String word) {
		if (myDictionary.search(word)) {
			return false;
		}
		myDictionary.insert(word);
		this.size++;
		return true;
	}

	@Override
	public boolean exists(String word) {
		if (myDictionary.search(word)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String word) {
		this.size--;
		return myDictionary.delete(word);
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public int height() {
		return myDictionary.height();
	}

}
