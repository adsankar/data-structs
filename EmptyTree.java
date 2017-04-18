package tree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SuppressWarnings({ "unchecked", "rawtypes" })
/**
 * This file contains the EmptyTree and its methods
 * @author Aleksander Sankar

 */
public final class EmptyTree<K extends Comparable<K>, V> implements Tree<K, V> {

	/**
	 * Return the one and only instance of the EmptyTree object
	 * 
	 * @return an empty tree
	 */
	public static EmptyTree getInstance() {
		return new EmptyTree();
	}

	/**
	 * This method adds a key/value pair to the tree and returns a new tree
	 * containing it
	 */
	public NonEmptyTree<K, V> add(K key, V value) {
		if (key == null || value == null) {
			throw new NullPointerException();
		}
		NonEmptyTree n = new NonEmptyTree<>();// create a non-empty tree
		n.value = value;// set the value and left and right leaves
		n.key = key;
		n.left = n.right = getInstance();
		n.root = n;
		return n;
	}

	/**
	 * Returns the size of the empty tree, which is defined to be 0
	 */
	public int size() {
		return 0;
	}

	/**
	 * Retrieves the value stored with the specified key, but since the tree is
	 * empty, null is returned
	 */
	public V lookup(K key) {
		if (key == null)
			throw new NullPointerException();
		return null;
	}

	/**
	 * Returns the number of leaves in the tree which is defined to be 0
	 */
	public int numLeaves() {
		return 0;
	}

	/**
	 * Returns a subtree of the current tree, but since it is empty it just
	 * returns an empty tree
	 */
	public Tree<K, V> subTree(K fromKey, K toKey) {
		if (fromKey == null || toKey == null)
			throw new NullPointerException();
		return getInstance();
	}

	/**
	 * Returns a list containing the path to the root, but since the tree is
	 * empty, the list returned is also empty
	 */
	public List<K> pathToRoot(K key) {
		if (key == null)
			throw new NullPointerException();
		return new ArrayList<K>();
	}

	/**
	 * Returns a list containing all of the keys of the tree, but since the tree
	 * is empty, the list returned is also empty
	 */
	public Collection<K> keyCollection() {
		return new ArrayList<K>();
	}

	/**
	 * Returns the key for maximum value of the tree, but since it is empty, it
	 * just throws an exception
	 */
	public K max() throws EmptyTreeException {
		throw new EmptyTreeException();
	}

	/**
	 * Returns the key for minimum value of the tree, but since it is empty, it
	 * just throws an exception
	 */
	public K min() throws EmptyTreeException {
		throw new EmptyTreeException();
	}

	/**
	 * Deletes a node from the current tree, but since it is empty, it just
	 * returns an empty tree
	 */
	public Tree<K, V> delete(K key) {
		if (key == null)
			throw new NullPointerException();
		return getInstance();
	}

	/**
	 * Returns the string representation for the tree, but since it is empty, it
	 * just returns a blank string
	 */
	public String toString() {
		return "";
	}

}
