package eg.edu.alexu.csd.filestructure.avl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary implements IDictionary {

	private MyAVL<String> myDictionary = new MyAVL<String>();
	@Override
	public void load(File file) {
		// TODO Auto-generated method stub
		try (BufferedReader br = new BufferedReader(new FileReader(file)))
		{

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
		if(myDictionary.search(word))
		{
			return false;
		}
		myDictionary.insert(word);
		return true;
	}

	@Override
	public boolean exists(String word) {
		// TODO Auto-generated method stub
		if(myDictionary.search(word))
		{
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(String word) {
		// TODO Auto-generated method stub
		return myDictionary.delete(word);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return myDictionary.countNodes(myDictionary.getTree());
	}

	@Override
	public int height() {
		// TODO Auto-generated method stub
		return myDictionary.height();
	}

}
