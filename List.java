package list;

import java.lang.Iterable;
import java.util.Iterator;
import java.util.Comparator;
import java.lang.IndexOutOfBoundsException;
import java.util.NoSuchElementException;

/**
 * This program represents a Linked List and has several methods expanding on
 * the functionality of it. The list is made up of a series of nodes and
 * references to other nodes
 * 
 * @author Aleksander Sankar
 */
public class List<T extends Comparable<T>> implements Iterable<T> {

	// TODO sub list

	/**
	 * This class stores information about an individual node object
	 */
	private class Node<D extends Comparable<D>> {
		private D data;
		private Node<D> next;

		// constructor for a new node given the data and a reference to the
		// next node
		public Node(D newData, Node<D> newNext) {
			data = newData;
			next = newNext;
		}

	}// end of Node<T> class

	// this is the first node in the list
	private Node<T> head;

	/**
	 * Constructor which sets the head to null, effectively creating a blank
	 * list.
	 */
	public List() {
		head = null;
	}

	/**
	 * This constructor copies the contents of the list in the parameter and
	 * adds all of them to a list, making a deep copy
	 * 
	 * @param otherList
	 *            the list we are copying
	 */
	public List(List<T> otherList) {
		// if the other list is empty, the new list will be too
		if (otherList == null)
			head = null;
		else {
			head = otherList.head;
			Node<T> t = head;
			Node<T> copy = otherList.head;
			// go through the list and set the corresponding nodes to be equal
			while (copy != null) {
				t.data = copy.data;
				t = t.next;
				copy = copy.next;// finally increment and move the pointer to
				// the next node
			}
		}

	}

	/**
	 * This method inserts a new element into the list while keeping it sorted
	 * 
	 * @param newElt
	 *            the element we are adding
	 */
	public void insertInSortedOrder(T newElt) {
		if (head == null)// make a new node with the data as the only node
			head = new Node<T>(newElt, null);
		else if (head.next == null) {
			// if there is only one node, add the new node before head if
			// smaller or after if larger
			if (newElt.compareTo(head.data) >= 0) {
				head.next = new Node<T>(newElt, null);
			} else
				head = new Node<T>(newElt, head);
		} else {
			// step through the list and see if the data is larger than the
			// current node
			Node<T> prev = null;
			Node<T> curr = head;// start at the beginning of the list
			while (curr != null && !(curr.data.compareTo(newElt) > 0)) {
				// move through the list until one of the nodes is larger than
				// the new data
				prev = curr;
				curr = curr.next;
			}
			if (prev == null)// if the new element is the smallest
				head = new Node<T>(newElt, head);
			else
				prev.next = new Node<T>(newElt, curr); // general case
		}
	}

	/**
	 * Find the size of the list by counting through all of the elements
	 * 
	 * @return the size of the list
	 */
	public int size() {
		int count = 0;
		Node<T> t = head; // start at the beginning of the list
		if (head != null) {
			while (t != null) {
				// if there is another node left, increment the counter and
				// point to the next node
				count++;
				t = t.next;
			}
		}
		return count; // size of zero if the list is empty

	}

	/**
	 * Return the element at the given index
	 * 
	 * @param index
	 *            the index we are looking for
	 * @return the data of the nth node
	 * @throws IndexOutOfBoundsException
	 *             if the index is invalid
	 */
	public T getElementAtIndex(int index) throws IndexOutOfBoundsException {
		Node<T> t = head;// start at the beginning of the list

		// if the list is empty or the index is negative, the index will
		// be invalid
		if (t == null || index < 0)
			throw new IndexOutOfBoundsException();

		for (int i = 0; i < index; i++) {
			if (t != null) {
				t = t.next;
				// step through the list the specified number of times
			} else
				throw new IndexOutOfBoundsException();// reach here if the
			// index provided is too large;
		}
		return t.data; // return the element at that point in the list
	}

	/**
	 * Determine if the specified element is in the list and if it is, return it
	 * 
	 * @param element
	 * @return
	 */
	public T contains(T element) {
		if (head != null) {
			Node<T> t = head; // start at the beginning of the list
			while (t != null) {
				if (t.data.compareTo(element) == 0) {
					return t.data; // found the correct element, so return it
				} else {// we have not reached the target, so increment the
					// counter and point to the next node
					t = t.next;
				}
			}

		}
		return null;
	}

	/**
	 * This method creates a string representation of the list, and this is used
	 * as a visual aid in debugging
	 *
	 * @return a string containing the information stored in the list
	 */
	public String toString() {
		String s = "";
		if (head != null) {
			Node<T> t = head; // start at the beginning of the list
			while (t != null) {
				s += t.data;// add the data from that node to the string
				if (t.next != null) {// if we are not at the end of the list,
					// print a space so that the data points are separated
					s += " ";
				}
				t = t.next; // point to the next node
			}
		}
		return s;
	}

	/**
	 * This method returns the first index of the element specified in the
	 * parameter
	 * 
	 * @param element
	 *            the element we are looking for
	 * @return the index of that element
	 */
	public int indexOf(T element) {
		int index = 0;

		if (head != null) {
			Node<T> t = head; // start at the beginning of the list
			while (t != null) {
				if (t.data.compareTo(element) == 0) {
					return index; // return the position if we have reached the
					// target
				} else {// we have not reached the target, so increment the
					// counter and point to the next node
					t = t.next;
					index++;
				}
			}
			index = -1;// the specified element is not in the list
		}

		return index;
	}

	/**
	 * Finds the last occurrence of the element in the parameter.
	 * 
	 * @param element
	 *            the element we are looking for
	 * @return the index of that element
	 */
	public int lastIndexOf(T element) {
		int index = 0;

		if (head != null) {
			Node<T> t = head; // start at the beginning of the list
			while (t != null) {
				if (t.data.compareTo(element) == 0) {// found one instance of
					// the element we are looking for
					while (t.next != null) {
						if (t.next.data.compareTo(t.data) == 0) {
							t = t.next;// if there is another occurrence of
							// that element, go until you reach the last one
							index++;
						} else
							return index;
					}
					return index; // return the position if we have reached the
					// target
				} else {// we have not reached the target, so increment the
					// counter and point to the next node
					t = t.next;
					index++;
				}
			}
			index = -1;// the specified element is not in the list
		}

		return index;
	}

	/**
	 * Removes the node that contains the element in the parameter
	 * 
	 * @param element
	 *            the element we are looking for
	 * @return true if removed successfully, and false if not
	 */
	public boolean remove(T element) {
		if (head != null) {
			Node<T> t = head; // start at the beginning of the list
			Node<T> prev = null;
			while (t != null) {
				if (t.data.compareTo(element) == 0) {// found the element
					if (prev == null) {// if the element is the first one in the
						// list, set the head to jump over the first node
						head = t.next;
						return true;
					} else {
						prev.next = t.next;// general case
						return true;
					}
				} else {// we have not reached the target, so point to the next
					// node
					prev = t;
					t = t.next;
				}
			}
			return false;// the specified element is not in the list
		}
		return false;// the list is empty so we cannot remove anything
	}

	/**
	 * This method goes through the list and removes the node at the specified
	 * index
	 * 
	 * @param index
	 * @throws IndexOutOfBoundsException
	 *             if the index is invalid
	 */
	public void removeElementAtIndex(int index)
			throws IndexOutOfBoundsException {
		Node<T> t = head;// start at the beginning of the list
		Node<T> prev = null;
		// if the list is empty or the index is negative, the index will
		// be invalid
		if (t == null || index < 0)
			throw new IndexOutOfBoundsException();

		for (int i = 0; i < index; i++) {// step a specific number of times
			// in order to get to the right index
			if (t != null) {
				prev = t;
				t = t.next;
			} else
				throw new IndexOutOfBoundsException();// reach here if the
			// index provided is too large;
		}
		if (prev == null) {// case if we are trying to remove the first node
			head = t.next;

		} else {
			prev.next = t.next;// general case
		}

	}

	/**
	 * Clears the list by setting the head to null
	 */
	public void clear() {
		head = null;
	}

	/**
	 * This method creates and returns a new list which contains the data from
	 * the current list within the specified indicies, without modifying the
	 * current list.
	 * 
	 * @param fromIndex
	 *            the starting index
	 * @param toIndex
	 *            the ending index
	 * @return a sublist of the current list
	 * @throws IndexOutOfBoundsException
	 *             if one or both of the indicies is invalid
	 */
	public List<T> subList(int fromIndex, int toIndex)
			throws IndexOutOfBoundsException {
		if (fromIndex > toIndex || fromIndex < 0 || toIndex < 0) {
			throw new IndexOutOfBoundsException();
		}
		List<T> newList = new List<T>();// create the new list
		Node<T> t = head;// start at the beginning of the list

		for (int i = 0; i < fromIndex; i++) {
			if (t != null) {
				t = t.next;
				// step through the list the specified number of times
			}
		}
		newList.head = new Node<T>(t.data, null);// creates the first node in
		// the new list
		Node<T> last = t;// we will increment this to find the end of the
							// sublist
		Node<T> t2 = newList.head;// this marks the beginning of the new list
		for (int i = 0; i < toIndex - fromIndex; i++) {
			if (last != null) {
				last = last.next;
				t2.next = new Node<T>(last.data, null);// add a new node at the
				// end of the new list
				t2 = t2.next;
				// step through the list the specified number of times
			}
		}
		return newList;
	}

	/**
	 * Removes a set of nodes from one list contained in another list
	 * 
	 * @param otherList
	 *            the list we are removing from the current list
	 * @return true if completed successfully, false if not
	 */
	public boolean removeOtherList(List<T> otherList) {
		Node<T> t = otherList.head;
		Node<T> t2 = head;
		Node<T> prev = null;

		if (t != null) {
			if (t2 != null) {// check that the other list contains something
				while (t != null && t2.data.compareTo(t.data) != 0) {
					// search for the first occurrence of matching data values
					prev = t;// keep track of the node just before the values
					// are the same
					t = t.next;

				}
				while (t2 != null && t != null) {
					for (int i = 0; i < otherList.size(); i++) {
						// check that the entire sublist is in the list
						if (t.data.compareTo(t2.data) != 0) {
							return false;
						}
						t2 = t2.next;
						prev = prev.next;
						t = t.next;
					}
				}
				prev.next = t2.next;// delete all members of the sublist
				return true;
			}
		} else if (t == null)// removal of no elements from the current list
			return true;

		return false;
	}

	/**
	 * Constructor for an iterator that can iterate over the linked list. This
	 * implementation uses an anonymous inner class
	 * 
	 * @return a new iterator
	 */
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Node<T> curr = head;
			private Node<T> prev = null;

			@Override
			/**
			 * Determines if there is another element to be iterated
			 */
			public boolean hasNext() {
				if (curr != null)
					return true;
				else
					return false;
			}

			@Override
			/**
			 * Return the next element in the list
			 */
			public T next() throws NoSuchElementException {
				if (hasNext()) {
					Node<T> t = curr;
					prev = curr;
					curr = curr.next;
					return t.data;
				} else
					throw new NoSuchElementException();// if there are
				// no elements left
			}

			@Override
			/**
			 * Removes that last node that was returned by next()
			 */
			public void remove() throws IllegalStateException {
				if (prev != null)
					List.this.remove(prev.data);
				else
					throw new IllegalStateException();// if the list is empty
			}

		};
	}

	/**
	 * Constructor of a comparator object which compares lists based on their
	 * lengths. This is implemented using anonymous inner class
	 * 
	 * @return a new comparator
	 */
	public Comparator<List<T>> lengthComparator() {
		return new Comparator<List<T>>() {

			@Override
			/**
			 * Returns a positive number if the first list is larger than the
			 * second, a negative if the second is longer than the first, and
			 * zero if they are the same length
			 */
			public int compare(List<T> o1, List<T> o2) {
				return o1.size() - o2.size();
			}
		};
	}

	/**
	 * Constructor for a comparator which compares lists based on their
	 * contents. It is implemented using an anonymous inner class
	 * 
	 * @return
	 */
	public Comparator<List<T>> orderComparator() {
		return new Comparator<List<T>>() {

			@Override
			/**
			 * Compares the lists by going through their elements and returns a
			 * positive number if the first list is larger than the
			 * second, a negative if the second is larger than the first, and
			 * zero if they are the same list
			 */
			public int compare(List<T> l1, List<T> l2) {
				Node<T> head1 = l1.head;
				Node<T> head2 = l2.head;
				Node<T> t1 = head1;
				Node<T> t2 = head2;

				if (l1.size() > l2.size()) {// check which one is longer so that
					// we do not step out of bounds in one list

					while (t2 != null) {// start from the head
						if (t2.data.compareTo(t1.data) > 0)
							return -1;
						if (t2.data.compareTo(t1.data) < 0)
							return 1;
						// no conclusion can be drawn from the nodes yet, so
						// step to the next nodes
						t2 = t2.next;
						t1 = t1.next;
					}
					// if the contents are the same but the lengths are not,
					// return the larger one
					return (l1.size() - l2.size());
				}
				if (l1.size() <= l2.size()) {
					while (t1 != null) {
						if (t2.data.compareTo(t1.data) > 0)
							return -1;
						if (t2.data.compareTo(t1.data) < 0)
							return 1;
						t2 = t2.next;
						t1 = t1.next;
						// no conclusion can be drawn from the nodes yet, so
						// step to the next nodes
					}
					if (t2 == null && t1 == null)
						return 0;// if we have reached here, the lists are
					// exactly the same in terms of contents
					return (l1.size() - l2.size());
				}
				return 0;
			}

		};
	}

}// end class