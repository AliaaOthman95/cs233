package eg.edu.alexu.csd.filestructure.avl;

@SuppressWarnings("unchecked")
public class AVLTree<T extends Comparable<T>> implements IAVLTree<T> {
	private static final int ALLOWED_IMBALANCE = 1;
	private Node<T> root;

	public AVLTree() {
		this.root = null;
	}

	public class Node<A extends Comparable<T>> implements INode<T> {
		private INode<T> left, right;
		private T value;
		private int height;

		Node(T value) {
			this(value, null, null);
		}

		Node(T element, INode<T> lt, INode<T> rt) {
			value = element;
			left = lt;
			right = rt;
			height = 0;
		}

		@Override
		public INode<T> getLeftChild() {
			return left;
		}

		@Override
		public INode<T> getRightChild() {
			return right;
		}

		@Override
		public T getValue() {

			return value;
		}

		@Override
		public void setValue(T value) {
			this.value = value;
		}

		public void setLeft(INode<T> left) {
			this.left = left;
		}

		public void setRight(INode<T> right) {
			this.right = right;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

	}

	@Override
	public void insert(T key) {
		if (key == null) {
			return;
		}
		root = (Node<T>) insert(key, root);
	}

	public INode<T> insert(T key, Node<T> root) {

		if (root == null) {
			root = new Node<T>(key);
		} else {
			int compareResult = key.compareTo(root.getValue());
			if (compareResult < 0) {

				root.setLeft(insert(key, (Node<T>) root.getLeftChild()));
			} else if (compareResult >= 0) {
				root.setRight(insert(key, (Node<T>) root.getRightChild()));
			}
		}
		return balance(root);

	}

	public Node<T> balance(Node<T> t) {

		if (t == null)
			return t;
		if (height((Node<T>) t.getLeftChild()) - height((Node<T>) t.getRightChild()) > ALLOWED_IMBALANCE)
			if (height((Node<T>) t.getLeftChild().getLeftChild()) >= height(
					(Node<T>) t.getLeftChild().getRightChild())) {
				t = rotateWithLeftChild(t);
			} else {
				t = doubleWithLeftChild(t);
			}
		else if (height((Node<T>) t.getRightChild()) - height((Node<T>) t.getLeftChild()) > ALLOWED_IMBALANCE) {
			if (height((Node<T>) t.getRightChild().getRightChild()) >= height(
					(Node<T>) t.getRightChild().getLeftChild())) {
				t = rotateWithRightChild(t);
			} else {
				t = doubleWithRightChild(t);
			}
		}
		t.setHeight(Math.max(height((Node<T>) t.getLeftChild()), height((Node<T>) t.getRightChild())) + 1);
		return t;

	}

	/*
	 * Rotate binary tree node with left child. For AVL trees, this is a single
	 * rotation for case 1. Update heights, then return new root.
	 */

	private Node<T> rotateWithLeftChild(Node<T> k2) {

		Node<T> k1 = (Node<T>) k2.getLeftChild();
		k2.setLeft(k1.getRightChild());
		k1.setRight(k2);
		k2.setHeight(Math.max(height((Node<T>) k2.getLeftChild()), height((Node<T>) k2.getRightChild())) + 1);
		k1.setHeight(Math.max(height((Node<T>) k1.getLeftChild()), k2.getHeight()) + 1);
		return k1;

	}

	private Node<T> rotateWithRightChild(Node<T> k1) {
		Node<T> k2 = (Node<T>) k1.getRightChild();
		k1.setRight(k2.left);
		k2.setLeft(k1);
		k1.setHeight(Math.max(height((Node<T>) k1.getLeftChild()), height((Node<T>) k1.getRightChild())) + 1);
		k2.setHeight(Math.max(height((Node<T>) k2.getRightChild()), k1.getHeight()) + 1);
		return k2;
	}

	private Node<T> doubleWithLeftChild(Node<T> k3) {
		k3.setLeft(rotateWithRightChild((Node<T>) k3.getLeftChild()));
		return rotateWithLeftChild(k3);
	}

	private Node<T> doubleWithRightChild(Node<T> k1) {
		k1.setRight(rotateWithLeftChild((Node<T>) k1.getRightChild()));
		return rotateWithRightChild(k1);
	}

	private int height(Node<T> node) {
		return node == null ? -1 : node.getHeight();
	}

	@Override
	public boolean delete(T key) {
		int before = countNodes(root);
		root = remove(key, root);
		int after = countNodes(root);
		if (after - before == 0) {
			return false;
		}
		return true;
	}

	private Node<T> remove(T key, Node<T> t) {
		if (t == null) {
			return t; // Item not found; do nothing
		}
		int compareResult = key.compareTo((T) t.getValue());

		if (compareResult < 0) {
			t.setLeft(remove(key, (Node<T>) t.getLeftChild()));
		} else if (compareResult > 0) {
			t.setRight(remove(key, (Node<T>) t.getRightChild()));
		} else if (t.left != null && t.right != null) // Two children
		{
			t.setValue(findMin(t.getRightChild()).getValue());
			t.right = remove((T) t.getValue(), (Node<T>) t.getRightChild());
		} else {
			t = (t.getLeftChild() != null) ? (Node<T>) t.getLeftChild() : (Node<T>) t.getRightChild();
		}
		return balance(t);
	}

	public INode<T> findMin(INode<T> root) {
		if (root == null) {
			return null;
		} else if (root.getLeftChild() == null) {
			return root;
		}
		return findMin(root.getLeftChild());
	}

	@Override
	public boolean search(T key) {

		if (key == null) {
			return false;
		}
		return search(key, root);
	}

	public boolean search(T val, INode<T> r) {
		boolean found = false;
		while ((r != null) && !found) {
			T rvalue = (T) r.getValue();
			if (val.compareTo(rvalue) < 0) {
				r = (Node<T>) r.getLeftChild();
			} else if (val.compareTo(rvalue) > 0) {
				r = (Node<T>) r.getRightChild();
			} else {
				found = true;
				break;
			}
			found = search(val, r);
		}
		return found;
	}

	public int countNodes(INode<T> r) {
		if (r == null)
			return 0;
		else {
			int l = 1;
			l += countNodes(r.getLeftChild());
			l += countNodes(r.getRightChild());
			return l;
		}
	}

	@Override
	public int height() {
		return height(root) + 1;

	}

	@Override
	public INode<T> getTree() {
		return root;
	}

}
