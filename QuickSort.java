import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * QuickSort Program
 * @author Aleksander Sankar
 * Analysis of Algorithms Pd. 9
 */
public class QuickSort {

	/**
	 * This program reads a list of names (strings) from a file and inputs them into an ArrayList and prints it
	 * The names are then sorted using the quick sort algorithm and the list is printed again.
	 * @param args not used
	 */
	public static void main(String [] args){
		//TODO fix sort method more
		ArrayList<String> list = readAndInsertData();
		printList(list);
		ArrayList<String> sortedList = sort(list,0,list.size()-1);
		System.out.println("*SORT*");
		printList(sortedList);

	}//end main 

	/**
	 * Reads the data from a text file and inserts it into the ArrayList.
	 * @param DataArray
	 */
	public static ArrayList<String> readAndInsertData(){
		ArrayList<String> DataArray = new ArrayList<String>();

		Scanner reader = null; 
		try{reader = new Scanner(new FileReader("names.txt"));
		}
		catch(FileNotFoundException e){
			System.out.println("File not found!");
		}
		while (reader.hasNext()){
			DataArray.add(reader.nextLine());
		}//end while loop
		return DataArray;
	}//end readAndInsertData method


	/**
	 * Prints the data with the for-each loop and a new line afterwards
	 * @param list
	 */
	public static void printList(ArrayList<String> list){
		for (String i: list)
			System.out.println(i+ " ");
		System.out.println();
	}//end printList method

	/**
	 * This method sorts the strings from the array list by the quick sort algorithm
	 * @param x the list to sort
	 * @param a the left endpoint
	 * @param b the right endpoint
	 * @return the sorted array list
	 */
	public static ArrayList<String> sort(ArrayList<String> x, int a, int b){
		int low = a;
		int high = b;
		String pivot;
		if (b > a) {
			pivot = x.get(low);
			while (low <= high) {
				while ((low < b) && (x.get(low).compareToIgnoreCase(pivot) < 0)){
					low++;
				}//end while

				while ((high > a) && (x.get(high).compareToIgnoreCase(pivot) > 0)){
					high--;
				}//end while

				if (low <= high) {
					String t = x.get(high);
					x.set(high, x.get(low));
					x.set(low, t); 
					low++;
					high--;
				}//end if
			}//end while

			if (a < high){
				sort(x, a, high);
			}//end if

			if (low < b){
				sort(x, low, b);
			}//end if
		}
		return x;
	}//end sort method

}//end class QuickSort
