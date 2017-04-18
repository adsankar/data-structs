package tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("unchecked")
/**
 * This file contains the NonEmptyTree and its methods
 * @author Aleksander Sankar

 */
public class NonEmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	// Fields that store the values of the root tree and its two leaves
	// the leaves are of type Tree<K, V> because they could be empty or filled
	V value;
	K key;
	Tree<K, V> left;
	Tree<K, V> right;
	NonEmptyTree<K, V> root;

	/**
	 * This method inserts a key/value pair into the current tree and returns
	 * it. It will always return a non-empty tree
	 * 
	 * @param K
	 *            key the key of the node we are adding
	 * @param V
	 *            value the value of the node we are adding
	 * @return a tree with that element added
	 */
	public NonEmptyTree<K, V> add(K key, V value) {
		if (key == null || value == null) {
			throw new NullPointerException();
		}// if either is null, we cannot add it to the tree
		if (this.key.compareTo(key) == 0) {
			this.value = value;// replace the current value with the new value
			// if the keys are the same
		} else {
			if (key.compareTo(this.key) < 0) {
				// add to the left and recur if the key is smaller than the
				// current key
				this.left = this.left.add(key, value);
			} else {
				// add to the right and recur if the key is larger than the
				// current key
				this.right = this.right.add(key, value);
			}
		}
		return this;

	}

	/**
	 * Finds the size of the tree through recursion
	 */
	public int size() {
		return 1 + left.size() + right.size();
	}

	/**
	 * Returns the value of the tree with the associated key
	 * 
	 * @return the value paired with key
	 */
	public V lookup(K key) {
		if (key == null)
			throw new NullPointerException();
		if (key.compareTo(this.key) == 0)// (base case)
			return this.value;// we have found the value, so return it
		else {
			if (key.compareTo(this.key) < 0)
				// recur and search the left half of the tree
				return this.left.lookup(key);
			if (key.compareTo(this.key) > 0)
				// recur and search the right half of the tree
				return this.right.lookup(key);
		}

		return null;// if the key is not in the tree
	}

	/**
	 * Returns the number of leaves of a Tree
	 */
	public int numLeaves() {

		if (this.left == this.right) {// if the tree is a leaf itself
			return 1;
		} else {// recur down and count the leaves
			return this.left.numLeaves() + this.right.numLeaves();

		}
	}

	/**
	 * Returns a subtree of the original tree that has nodes starting from
	 * fromKey to toKey
	 */
	public Tree<K, V> subTree(K fromKey, K toKey) {
		if (fromKey == null || toKey == null)
			throw new NullPointerException();

		NonEmptyTree<K, V> n = new NonEmptyTree<>();
		if (this.key.compareTo(toKey) > 0) {
			return this.left.subTree(fromKey, toKey);
		} else if (this.key.compareTo(fromKey) < 0) {
			return this.right.subTree(fromKey, toKey);
		} else {

			n.left = this.left.subTree(fromKey, toKey);
			n.key = this.key;
			n.right = this.right.subTree(fromKey, toKey);
		}
		return n;
	}

	/**
	 * Returns a list that shows the path from the given key back to the root.
	 * The tree is traversed and the elements are placed in the list in reverse
	 * order.
	 */
	public List<K> pathToRoot(K key) {
		if (key == null)
			throw new NullPointerException();
		ArrayList<K> x = new ArrayList<K>();
		if (lookup(key) != null) {
			// normal traversal of the list and then add each element to the
			// list and finally recur
			if (key.compareTo(this.key) < 0) {
				x.add(0, this.key);
				x.addAll(0, this.left.pathToRoot(key));
			}
			if (key.compareTo(this.key) == 0) {
				x.add(0, this.key);
			}
			if (key.compareTo(this.key) > 0) {
				x.add(0, this.key);
				x.addAll(0, this.right.pathToRoot(key));
			}
		}
		return x;// if the key is not in the Tree
	}

	/**
	 * Returns a collection (in this case an ArrayList) containing all of the
	 * elements of the tree.
	 */
	public Collection<K> keyCollection() {
		ArrayList<K> x = new ArrayList<K>();

		// recursive in-order traversal
		x.addAll(this.left.keyCollection());
		x.add(this.key);
		x.addAll(this.right.keyCollection());

		return x;
	}

	/**
	 * Returns the key for the maximum value in the tree
	 */
	public K max() throws EmptyTreeException {
		try {
			return this.right.max();
		} catch (EmptyTreeException e) {
			return this.key;
		}
	}

	/**
	 * Returns the key for the minimum value in the tree
	 */
	public K min() throws EmptyTreeException {
		try {
			return this.left.min();
		} catch (EmptyTreeException e) {
			return this.key;
		}

	}

	/**
	 * This method deletes the node associated with the given key from the
	 * current tree and then returns it
	 */
	public Tree<K, V> delete(K key) {
		if (key.compareTo(this.key) > 0) {
			this.right = this.right.delete(key);
			// recur to the right if greater
			if (this.right.delete(key).numLeaves() == 0) {
				this.right = EmptyTree.getInstance();
			}
		} else if (key.compareTo(this.key) < 0) {
			// recur to the right if smaller
			this.left = this.left.delete(key);
		} else {
			try {
				// set the value of the current node to the largest of the left
				// nodes and then delete that node
				this.value = this.lookup(this.left.max());
				this.key = this.left.max();
				this.left = this.left.delete(this.key);
			} catch (EmptyTreeException e1) {
				try {
					// set the value of the current node to the smallest of the
					// right nodes and then delete that node
					this.value = this.lookup(this.right.min());
					this.key = this.right.min();
					this.right = this.right.delete(this.key);
				} catch (EmptyTreeException e2) {
					return EmptyTree.getInstance();

				}
			}
		}

		return this;// return the modified tree
	}

	/**
	 * Returns a string containing all of the key/value pairs in the tree,
	 * sorted by their keys
	 */
	public String toString() {
		String s = "";

		// in order traversal of calling toString
		s += this.left.toString();
		if (!this.left.toString().equals(""))
			s += " ";
		s += this.key + "/" + this.value;
		// add a space if the next value is not the largest one
		if (!this.right.toString().equals(""))
			s += " ";
		s += this.right.toString();

		return s;

	}

}// end class
