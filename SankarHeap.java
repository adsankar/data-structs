import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Binary Search Tree/Heap Program
 * @author Aleksander Sankar
 * Analysis of Algorithms Pd. 9
 */
public class SankarHeap {

	/**
	 * This program uses the TreeNode class to make binary search tree. It is then converted into a heap and
	 * several operations are done on the heap.
	 * @param args not used
	 */
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("In order traversal: ");
		TreeNode a = readAndInsertData();
		inOrderPrint(a);
		System.out.println();
		System.out.println("What number would you like to delete?");
		int in = sc.nextInt();
		a= remove3(new Integer(in),a); //fix here
		System.out.println("In order traversal:");
		inOrderPrint(a);
		System.out.println();
		System.out.println("Post order traversal:");
		postOrderPrint(a);
		PrintWriter outputStream =null;
		try{
			outputStream = new PrintWriter(new FileOutputStream("newNumbers.txt"));
		}
		catch(FileNotFoundException e) {
			System.out.println("No file here!");
			System.exit(0);
		}

		writeToFilePostOrder(a, outputStream);
		outputStream.close();
		System.out.println();
		System.out.println("Written to file:");

		//	printHeap(readtoHeap());
		//	System.out.println();

		ArrayList<Integer> heap= new ArrayList<Integer>();
		readtoHeap(heap);
		buildHeap(heap, heap.size());
		printHeap(heap);
		System.out.println();
		System.out.println("<Delete a number>");
		deleteFromHeap(heap, heap.size()-1);
		System.out.println("new heap:");
		//	buildHeap(heap, heap.size());
		printHeap(heap);
		System.out.println();

		System.out.println("What number would you like to insert?");
		int n = sc.nextInt();
		insertToHeap(n, heap);
		buildHeap(heap, heap.size());
		System.out.println("new heap");
		printHeap(heap);

		heapSort(heap, heap.size());
		System.out.println("\nSorted:");
		printHeap(heap);

	}

	public static TreeNode remove3( Integer x, TreeNode t ){
		if( t == null )
			return t;   // Item not found; do nothing
		//int compareResult = x.compareTo( t.element );
		if( (Integer)t.getValue()<x)
			t.setLeft(remove3( x, t.getLeft()));
		else if( (Integer)t.getValue()>x )
			t.setRight(remove3( x, t.getRight()));
		else if( t.getLeft() != null && t.getRight() != null ){ // 2 children
			t.setValue(findMin(t.getRight()).getValue());
			t.setRight(remove3((Integer)t.getValue(), t.getRight()));
		}
		else /*if(t.getLeft() !=null) t=t.getLeft();
		else t=t.getRight();*/
			t = ( t.getLeft() != null ) ? t.getLeft() : t.getRight();
		return t;
	}

	/**
	 * Reads the data from a text file and inserts it into the binary search tree
	 * @return t the new binary search tree
	 */
	public static TreeNode readAndInsertData() {
		TreeNode t = null;
		//TreeNode root = null;
		Scanner reader = null;
		try {
			reader = new Scanner(new FileReader("numbers.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		while (reader.hasNext()) {

			int a = reader.nextInt();
			t = insert(t, a);
		}// end while loop
		return t;
	}// end readAndInsertData method

	/**
	 * Sorts the heap using the heap sort algorithm
	 * @param h the heap to be sorted
	 * @param size the size of the heap
	 */
	public static void heapSort (ArrayList<Integer> h, int size) {		
		buildHeap(h, size);
		for (int i=size-1; i>0; i--) {
			h.add(h.get(h.size()-1));
			deleteFromHeap(h, i);
		}//end for
	}//end heapSort method

	/**
	 * Reads the data from a text file and inserts it into the binary search tree
	 * @return t the new binary search tree
	 */
	public static void readtoHeap(ArrayList<Integer> heap) {
		//= new ArrayList<Integer>();
		//TreeNode root = null;
		Scanner r = null;
		try {
			r = new Scanner(new FileReader("newNumbers.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		}
		while (r.hasNext()){
			heap.add(r.nextInt());
		}// end while loop
		//return heap;
	}// end readToHeap method

	/**
	 * This method goes through a post-order traversal of the binary search tree and writes the numbers
	 * to a file
	 * @param t the TreeNode being traversed
	 * @param outputStream the PrintWriter that writes to the file
	 */
	public static void writeToFilePostOrder(TreeNode t, PrintWriter outputStream){
		if (t!=null){
			writeToFilePostOrder(t.getLeft(), outputStream);
			writeToFilePostOrder(t.getRight(), outputStream);
			outputStream.println((Integer)t.getValue());
		}//end if
	}//end writeToFilePostOrder method

	/**
	 * Prints the elements of the TreeNode through an in-order traversal, recursively
	 * @param t the TreeNode that is traversed
	 */
	public static void inOrderPrint(TreeNode t) {
		if (t != null) {
			inOrderPrint(t.getLeft());   
			System.out.print(t.getValue() + " ");
			inOrderPrint(t.getRight());
		}// end if
	}// end inOrderPrint method

	/**
	 * Prints the elements of the TreeNode through a post-order traversal, recursively
	 * @param t
	 */
	public static void postOrderPrint(TreeNode t) {
		if (t != null) {
			postOrderPrint(t.getLeft());
			postOrderPrint(t.getRight());
			System.out.print(t.getValue() + " ");
		}// end if
	}// end preOrderPrint method

	/**
	 * Traverses the binary search tree and goes to the node with the specified value
	 * @param t the tree traversed
	 * @param x the integer to be found
	 * @return the TreeNode with the specified value
	 */
	public static TreeNode gotoNode(TreeNode t, Integer x){
		if (t==null){
			return null;
		}
		if ((Integer) t.getValue()<x){
			return gotoNode(t.getRight(),x);
		}
		else if ((Integer)t.getValue()>x){
			return gotoNode(t.getLeft(),x);
		}
		else return t;//found the node
	}//end gotoNode method

	/**
	 * Insert the number into the binary search tree
	 * @param t the TreeNode that the value goes in to
	 * @param x the value of the new TreeNode
	 * @return a TreeNode including the new value
	 */
	public static TreeNode insert(TreeNode t, int x) {
		if (t == null) {
			t = new TreeNode(x);
			return t;
		}//end if
		TreeNode temp = t;
		while (temp != null) {

			if (new Integer(x) < (Integer) temp.getValue()) {
				//left side
				if (temp.getLeft() == null) {
					temp.setLeft(new TreeNode(x));
					return t;
				} else temp = temp.getLeft();

			} else {
				//right side
				if (temp.getRight() == null) {
					temp.setRight(new TreeNode(x));
					return t;
				} else temp = temp.getRight();
			}//end else
		}//end while
		return t;
	}//end insert method

	/**
	 * The heap down process
	 * @param h the heap used
	 * @param size the size of the heap
	 */
	public static void heapDown (ArrayList<Integer> h, int size) {
		int n = 0;
		while (2*n+1<size) {

			int biggest = getLarger(h, n, size);
			int r = h.get(n);
			int max = h.get(biggest);

			if (r>=max) break;

			h.set(n, max);
			h.set(biggest, r);
			n = biggest;
		}//end while
	}//end heapDown

	/**
	 * Method that finds the larger of the two elements in a heap
	 * @param h the heap used
	 * @param a the first element
	 * @param b the second element
	 * @return the larger of the two elements
	 */
	public static int getLarger(ArrayList<Integer> h, int a, int b){
		int left = 2*a+1;
		int right = 2*a+2;
		if (right < b && h.get(right)>h.get(left)) {
			return right;
		} else return left;

	}//end getLarger method

	/**
	 * Heap up process
	 * @param h the heap used
	 * @param a the reference point
	 */
	public static void heapUp (ArrayList<Integer> h, int a) {
		while (a > 0) {
			int parent =  (a-1)/2;
			int b = h.get(a);
			int p = h.get(parent);
			if (p>=b) break;
			// swapping
			h.set(a, p);
			h.set(parent, b);
			a = parent;
		}//end while
	}//end heapUp method

	/**
	 * Finds the parent of the specified TreeNode
	 * @param t the TreeNode used
	 * @return the parent of that TreeNode
	 */
	public static TreeNode getParent(TreeNode t){
		Integer a = (Integer) t.getValue();
		TreeNode temp = t;
		while ((Integer)t.getValue()<a){
			temp = t;
			t = t.getLeft();
		}//end while
		while ((Integer)t.getValue()>a){
			temp = t;
			t=t.getRight();
		}//end while
		return temp;
	}//end getParent

	public static ArrayList<Integer> makeList(TreeNode t, ArrayList<Integer> l){
		if (t!= null){
			makeList(t.getLeft(),l);
			l.add((Integer) t.getValue());
			makeList(t.getRight(),l);
		}//end if
		return l;
	}

	public static TreeNode delete(Integer x) {
		TreeNode t = null;
		ArrayList<Integer> a = new ArrayList<Integer>();
		makeList(t,a);
		for (Iterator<Integer> i = a.iterator(); i.hasNext();){
			if ((Integer)i.next()==x){
				i.remove();
			}//end if
		}//end for loop
		TreeNode t2 = null;
		for (int i=0; i<a.size(); i++){
			insert(t2,a.get(i));
		}
		return t;
	}

	public static void switchNodes( TreeNode a, TreeNode b ) {
		@SuppressWarnings("unused")
		TreeNode temp = null;
		if( getParent(a) == null ) {
			temp = b;
		} else if( a == getParent(a).getLeft() ) {
			getParent(a).getLeft().setValue(b);
		} else {
			getParent(a).getLeft().setValue(b);
		}

		if( b != null ) {
			getParent(b).setValue(getParent(a));
		}
	}

	public static void remove2(TreeNode t, int x) {
		TreeNode r = t;
		t =gotoNode(t, x);
		if (t == null) {
			return;
		}
		if ((Integer)t.getValue() == x) {

			if ((t.getLeft() == null) && (t.getRight() == null)) {
				// leaf node
				t = null;
				return;
			}

			if ((t.getLeft() != null) && (t.getRight() != null)) {
				// node with two children
				t.setValue(lowOne(t.getRight()));
				return;
			}

			// either left child or right child
			if (t.getLeft() != null) {
				r.setLeft(t.getLeft());
				t = null;
				return;
			}

			if (t.getRight() != null) {
				r.setRight(t.getRight());
				t = null;
				return;
			}
		}
		r = t;
		if ((Integer)t.getValue() > x) {
			remove2(t.getLeft(), x);
		} else {
			remove2(t.getRight(), x);
		}
	}

	public static int lowOne(TreeNode t) {
		if (t.getLeft() == null) {
			int x = (Integer)t.getValue();
			t = null;
			return x;
		}
		return lowOne(t.getLeft());
	}

	public static void delete( TreeNode t, int x ) {

		if( t == null ) {
			return;
		}

		else if ( x == (Integer)t.getValue()) {

			if( t.getLeft() == null ) {
				switchNodes(t, t.getRight() ); 
			} 

			else if( t.getRight() == null ) {
				switchNodes( t, t.getLeft() );
			} 

			else {
				TreeNode minNode = t.getRight();

				while( minNode.getLeft() != null ) {
					minNode = minNode.getLeft();
				}

				if( getParent(minNode) != t ) {
					switchNodes( minNode, minNode.getRight() );
					minNode.setRight ( t.getRight());
					getParent(minNode.getRight()).setValue(minNode);
				}

				switchNodes( t, minNode );
				minNode.setLeft(t.getLeft());
				getParent(minNode.getLeft()).setLeft(minNode);//.left.parent = minNode;
			}
		} 

		else if( x < (Integer)t.getValue()) {
			delete( t.getLeft(), x );
		} 

		else {
			delete( t.getRight(), x );
		}
	}

	/**
	 * Finds the minimum value in the TreeNode recursively
	 * @param t the TreeNode used
	 * @return the TreeNode with the smallest value
	 */
	public static TreeNode findMin(TreeNode t){
		if( t == null )
			return null;
		else if( t.getLeft() == null )
			return t;
		return findMin( t.getLeft() );
	}//end findMin method

	/**
	 * Deletes the TreeNode containing the minimum value
	 * @param t the TreeNode used
	 * @return the smallest TreeNode
	 */
	public static TreeNode delMin(TreeNode t){
		if( t == null )
			return null;
		else if( t.getLeft() != null ){
			t.setLeft(delMin(t.getLeft()));
			return t;
		}//end if
		else return t.getRight();
	}//end delMin method

	/**
	 * Build the heap
	 * @param h the heap used
	 * @param n the reference point
	 */
	public static void buildHeap (ArrayList<Integer> h, int n) {
		for (int i=1; i<n; i++) {
			heapUp (h, i);
		}//end for
	}//end buildHeap method

	/**
	 * Inserts a number into the heap and heaps up
	 * @param x the number to be added
	 * @param h the heap used
	 */
	public static void insertToHeap(int x, ArrayList<Integer> h) {
		h.add(x);
		heapUp(h,0);
	}//end insertToHeap

	/**
	 * Swaps the two elements within the heap
	 * @param h the heap used
	 * @param a the first element
	 * @param b the second element 
	 */
	public static void swap (ArrayList<Integer> h, int a, int b) {
		if (h.size()>0){
			int temp = h.get(a);
			h.set(a, h.get(b));
			h.set(b,temp);
		}
	}//end swap method

	/**
	 * Deletes the value from the heap
	 * @param h the heap used
	 * @param count
	 */
	public static void deleteFromHeap(ArrayList<Integer> h, int count) {
		swap (h, 0, count);
		h.remove(h.size()-1);
		ArrayList<Integer> h2 =h;
		heapDown(h2, count);
	}//end deleteFromHeap


	/**
	 * Prints the heap using a for-each loop
	 * @param h the heap to be printed
	 */
	public static void printHeap(ArrayList<Integer> h) {
		for (Integer i: h)
			System.out.print(i+ " ");
		System.out.println();
	}//end printHeap


}//end class