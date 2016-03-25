package eg.edu.alexu.csd.filestructure.sort;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Heap <T extends Comparable<T>>  implements IHeap<T> {
	private ArrayList<INode> tree ;
	//private int size = 0;
	 public Heap() {
	        tree = new ArrayList<>();
	    }

	private class Node<T extends Comparable<T>> implements INode<T>{

		private T value = null;
		private int index ;
		public Node (int index)
		{
			this.index=index;
			
		}
		
		@Override
		public INode getLeftChild() {
			// TODO Auto-generated method stub
			if( 2*index+1 < size()-1)
			{
				return tree.get(2*index+1);
			}
			return null;
		}

		@Override
		public INode getRightChild() {
			// TODO Auto-generated method stub
			if( 2*index+2 < size()-1)
			{
				return tree.get(2*index+2);
			}
			return null;
		}

		@Override
		public INode getParent() {
			// TODO Auto-generated method stub
			if(index!= 0){
			return tree.get((int) Math.floor(index/2));
			}
			return null;
		}

		@Override
		public T getValue() {
			// TODO Auto-generated method stub
			return value;
		}

		@Override
		public void setValue(T value) {
			// TODO Auto-generated method stub
			this.value=value;
		}
	}

	@Override
	public INode<T> getRoot() {
		// TODO Auto-generated method stub
		if(tree.size()==0)
		{
			return null;
		}
		return  tree.get(0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return tree.size();
	}

	@Override
	public void heapify(INode<T> node) {
		// TODO Auto-generated method stub
		INode l,r,largest ;
		
		
		l = node.getLeftChild();
		r = node.getRightChild();
		if (l != null && l.getValue().compareTo(node.getValue())>0)
		{
		largest = l;
		}
		else
		{
		largest = node;
		}
		if (r != null && r.getValue().compareTo(node.getValue())>0)
		{
		largest = r;
		}
		if (largest.getValue().compareTo(node.getValue())!=0)
		{
		swap(node, largest);
		heapify(largest);
		}
		
	}

	@Override
	public T extract() {
		// TODO Auto-generated method stub
		if(tree.size()==0)
		{
			return null;
		}
		T root = getRoot().getValue();
		tree.remove(0);
		heapify(getRoot());
		return root ;
	}

	@Override
	public void insert(T element) {
	    if(element != null)
	    {
        if (size()==0)
        {
        	INode node = new Node(size());
        	node.setValue(element);
        	tree.add(node);
        	
        }
        else{
		INode node = new Node(size());
		node.setValue(element);
		tree.add(node);
		bubbleUp(node);
        }
	    }
	}

	public ArrayList<INode> getTree() {
		return tree;
	}

	@Override
	public void build(Collection<T> unordered) {
		// TODO Auto-generated method stub
		for (Iterator iterator = unordered.iterator(); iterator.hasNext();) {
	        T type = (T) iterator.next();
	        insert(type);

	    }
		for (int i = size()/2; i>1;i--){
		heapify(tree.get(i));
		}
	}
	public void swap(INode<T> less,INode<T> larger)
	{
	    T value = less.getValue();
	    less.setValue(larger.getValue());
	    larger.setValue(value);
	}

	private void bubbleUp(INode<T> node) {
        INode parent = node.getParent();
        while (parent.getValue().compareTo(node.getValue()) <0) {
            swap(parent, node);
            node = parent ;
            parent = node.getParent();
        }
    }

	

}
