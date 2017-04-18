import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class HeapSort {
	//TODO refactor a lot and document and edit

	/**
	 * 
	 * @param h
	 * @param i
	 * @param j
	 */
	public static void swap (ArrayList<Integer> h, int i, int j) {
		int temp = h.get(i);
		h.set(i, h.get(j));
		h.set(j,temp);
	}

	/**
	 * 
	 * @param h
	 * @param count
	 */
	public static void deleteFromHeap(ArrayList<Integer> h, int count) {
		swap (h, 0, count);
		h.remove(h.size()-1);
		ArrayList<Integer> h2 =h;
		heapDown(h2, count);
	}//end deleteFromHeap

	/**
	 * 
	 * @param h
	 * @param size
	 */
	public static void heapSort (ArrayList<Integer> h, int size) {		
		buildHeap(h, size);
		for (int i=size-1; i>0; i--) {
			h.add(h.get(h.size()-1));
			deleteFromHeap(h, i);
		}//end for
	}

	public static void readAndInsertData(ArrayList<Integer> DataArray){
		Scanner reader = null; 
		try{reader = new Scanner(new FileReader("numbers.txt"));
		}
		catch(FileNotFoundException e){
			System.out.println("File not found!");
		}
		while (reader.hasNext()){
			DataArray.add(reader.nextInt());
		}//end while loop
	}//end readAndInsertData method

	/**
	 * 
	 * @param h
	 * @param n
	 */
	public static void buildHeap (ArrayList<Integer> h, int n) {
		for (int i=1; i<n; i++) {
			heapUp (h, i);
		}
	}

	public static void printData(ArrayList<Integer> list){
		for (Integer i: list)
			System.out.print(i+ " ");
		 System.out.println();
	}//end printData method

	/**
	 * 
	 * @param h
	 * @param a
	 * @param size
	 * @return
	 */
	public static int getLarger(ArrayList<Integer> h, int a, int size){
		int left = 2*a+1;
		int right = 2*a+2;
		if (right < size && h.get(right)>h.get(left)) {
			return right;
		} else return left;

	}

	/**
	 * 
	 * @param h
	 * @param size
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
		}
	}//end heapDown

	/**
	 * 
	 * @param h
	 * @param a
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
		}
	}
	
	public static void insertToHeap(int x, ArrayList<Integer> h) {
		h.add(x);
		heapUp(h,0);
	}//end insertToHeap
	
	public static void main(String [] args){
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> heap = new ArrayList<Integer>();
		readAndInsertData(heap);
		buildHeap(heap, heap.size());
		printData(heap);
		System.out.println("<Delete a number>");
		deleteFromHeap(heap, heap.size()-1);
		System.out.println("new heap:");
		printData(heap);
		
		System.out.println("What number would you like to insert?");
		int n = sc.nextInt();
		insertToHeap(n, heap);
		System.out.println("new heap");
		printData(heap);
		
		heapSort(heap, heap.size());
		System.out.println("\nSorted:");
		printData(heap);
		
		
	}

}//end class HeapSort