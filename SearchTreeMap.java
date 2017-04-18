package tree;

import java.util.NoSuchElementException;
import java.util.Collection;

@SuppressWarnings("unchecked")
/**
 * This file contains the TreeMap and its methods
 * @author Aleksander Sankar

 */
public class SearchTreeMap<K extends Comparable<K>, V extends Comparable<V>> {
	// Tree that holds all of the information
	Tree<K, V> root = EmptyTree.getInstance();

	/**
	 * Add a value to the map by invoking the Tree's add method
	 * 
	 * @param key
	 *            the key we are adding
	 * @param value
	 *            the value we are adding
	 */
	public void put(K key, V value) {
		root = root.add(key, value);
	}

	/**
	 * Return the value for the specified key in the map by invoking the Tree's
	 * lookup method
	 * 
	 * @param key
	 *            the key to look for
	 * @return the value associated with that key
	 */
	public V get(K key) {
		return root.lookup(key);
	}

	/**
	 * Return the size of the map by invoking the Tree's size method
	 * 
	 * @return the size of the map
	 */
	public int size() {
		return root.size();
	}

	/**
	 * Removes the node containing the specified key by invoking the Tree's
	 * delete method
	 * 
	 * @param key
	 *            the key that we want to remove
	 */
	public void remove(K key) {
		root = root.delete(key);
	}

	/**
	 * Returns the minimum value of the map by invoking the Tree's min method
	 * 
	 * @return the minimum value of the map
	 * @throws NoSuchElementException
	 *             if the map is empty
	 */
	public K firstKey() throws NoSuchElementException {
		try {
			return root.min();

		} catch (EmptyTreeException e1) {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Returns the maximum value of the map by invoking the Tree's max method
	 * 
	 * @return the maximum value of the map
	 * @throws NoSuchElementException
	 *             if the map is empty
	 */
	public K lastKey() throws NoSuchElementException {
		try {
			return root.max();

		} catch (EmptyTreeException e1) {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Returns a collection of the keys of the map by invoking the Tree's
	 * keyCollection method
	 * 
	 * @return a collection containing all of the keys in the map
	 */
	public Collection<K> keys() {
		return root.keyCollection();
	}

}
