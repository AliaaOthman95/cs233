package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Sort<T extends Comparable<T>> implements ISort<T> {
	private Heap bHeap = new Heap();

	@Override
	public IHeap heapSort(ArrayList unordered) {
		// TODO Auto-generated method stub
		int size = unordered.size();
		bHeap.build(unordered);
		for (int i = size; i > 2; i--) {
			size--;
			bHeap.swap((INode)bHeap.getTree().get(1),(INode)bHeap.getTree().get(i));
			bHeap.heapify((INode)bHeap.getTree().get(1));
		}
		return bHeap;
	}

	@Override
	public void sortSlow(java.util.ArrayList<T> unordered) {
		// TODO Auto-generated method stub
		boolean swapped = true;
		while (swapped) {
			swapped = false;
			for (int i = 1; i < unordered.size() - 1; i++) {

				if (unordered.get(i - 1).compareTo(unordered.get(i)) > 0) {
					Collections.swap(unordered, i, i - 1);

					swapped = true;
				}
			}

		}

	}

	@Override
	public void sortFast(java.util.ArrayList<T> unordered) {
		
		int p =1;
		int r = unordered.size();
		quickSort(unordered, p, r);
	}

	public void swap(INode<T> less, INode<T> larger) {
		T value = less.getValue();
		less.setValue(larger.getValue());
		larger.setValue(value);
	}

	public void quickSort (java.util.ArrayList<T> unordered , int p ,int r )
	{
		int q;
		if (p < r)
		{
		q = partition(unordered, p, r);
		quickSort(unordered, p, q);
		quickSort(unordered, q+1, r);
		}

	}

	public int partition( java.util.ArrayList<T>unordered, int p, int r) {
		// TODO Auto-generated method stub
		
		 T x = unordered.get(r); 
		int i = p-1 ;
		for (int j = p; j < r-1 ; j++) {
			if ( unordered.get(r).compareTo(x) <= 0)
			{
				i = i+1;
				Collections.swap(unordered, i, j);
			}
		}
		Collections.swap(unordered, i+1 , r);
		
		
	return i+1 ;
	}
}
