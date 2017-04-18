import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Radix Sort Program
 * @author Aleksander Sankar
 * Analysis of Algorithms Pd. 9
 */
public class RadixSort {

	/**
	 * This program reads in a list of integers from a file and then sorts them using the radix algorithm
	 * @param args not used
	 */
	public static void main(String[] args) {

		ArrayList<Integer> list = new ArrayList<Integer>();
		readAndInsertData(list);
		System.out.println("Unsorted List:");
		printList(list);
		sort(list);

	}

	/**
	 * Reads the data from a text file and inserts it into the ArrayList.
	 * @param DataArray
	 */
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
	 * Prints the data with the for-each loop and a new line afterwards
	 * @param list
	 */
	public static void printList(ArrayList<Integer> list){
		for (Integer i: list)
			System.out.print(i+ " ");
		System.out.println();
	}//end printList method

	/**
	 * Sorts the ArrayList using the Radix Sort alogrithm
	 * @param x, the unsorted list
	 * @return x, a sorted list
	 */
	public static ArrayList<Integer> sort(ArrayList<Integer> x){
		//initialize the set of buckets
		ArrayList<ArrayList<Integer>> nums = new ArrayList<ArrayList<Integer>>();

		//initializations of buckets
		for (int i=0; i<10; i++){
			nums.add(i, new ArrayList<Integer>());
		}//end for loop

		for (int k=0; k<loopTimes(x); k++){
			for (int i=0; i<10; i++){
				for (int j=0; j<x.size(); j++){
					//convert to string and locate correct digit
					String convert = x.get(j).toString();
					if (convert.length()-1-k<0) convert = convert.substring(0,convert.length()-k);
					else convert = convert.substring(convert.length()-1-k,convert.length()-k);
					if (convert.equals("")) convert ="0";
					if (Integer.parseInt(convert)== i) nums.get(i).add(x.get(j));//find which bucket to add to
				}//end for loop
			}//end for loop

			//add buckets together and clear after each
			x.clear(); //empty the list
			for (int m=0; m<10; m++){
				x.addAll(nums.get(m));
				nums.get(m).clear();
			}//end for loop
			System.out.println("Pass #" + (k+1));
			printList(x);

		}//end for loop
		return x;
	}//end sort method

	/**
	 * Finds the number of times to loop during the sort
	 * It is based off of the number of digits of the largest number
	 * @param x, the list used
	 * @return the number of times to loop (an integer)
	 */
	public static int loopTimes(ArrayList<Integer> x){
		int big = Integer.MIN_VALUE;
		for (int i=0; i<x.size(); i++){
			if (x.get(i)> big) big = x.get(i);
		}
		return Integer.toString(big).length();
	}//end loopTimes method

}//end RadixSort class
