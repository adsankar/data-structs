import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * HashMap and TreeMap Program
 * @author Aleksander Sankar
 * Analysis of Algorithms Pd. 9
 */
public class HashMapProgram{

	/**
	 * Reads from the file and inserts the Records into both a HashMap and TreeMap.
	 * Prints out the names with IDs and then prompts the user for a student to search
	 * for and finally prints an appropriate message.
	 * @param args not used
	 */
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		ArrayList<Record> r = readRecords();
		TreeMap<String,Integer> t = insertToTreeMap(r);
		HashMap<Integer,String> h = insertToHashMap(r);
		printList(t);
		printNames(h);

		//searching part
		int id =1;
		while (id !=0){
			
			System.out.println("Enter the ID of the student you want to search for. (0 to quit)");
			id = sc.nextInt();
			if (id!=0){
				search(id,h);
			}//end if

		}//end while		
		System.out.println("Program ended.");

	}//end main

	/**
	 * Searches the HashMap for the student using his/her ID and prints a message displaying the result.
	 * @param id the ID being used in the Search
	 * @param h the HashMap being traversed
	 */
	public static void search(int id, HashMap<Integer,String> h){
		if (h.containsKey(id)){
			System.out.println("Name: "+h.get(id)+ "   ID: "+id);
		}//end if
		else System.out.println(id+" was not found.");
	}//end search method

	/**
	 * Prints the names and IDs of all of the students from the HashMap using a for each loop.
	 * @param h the HashMap being traversed
	 */
	public static void printNames(HashMap<Integer,String> h){
		Set<Integer> s = h.keySet();
		for (Integer i: s){
			System.out.println("Name: "+h.get(i)+ "   ID: "+i);
		}//end for
		System.out.println();
	}//end printNames method

	/**
	 * Prints the names of the students from the TreeMap using an iterator.
	 * @param t the TreeMap being traversed
	 */
	public static void printList(TreeMap<String,Integer> t){
		Set<String> s = t.keySet();
		Iterator<String> i = s.iterator();
		while (i.hasNext()){
			System.out.println(i.next());
		}//end while
	}//end printList method

	/**
	 * Inserts all of the Records into a HashMap with the ID as the key.
	 * @param r the ArrayList containing all of the Records
	 * @return h HashMap containing all of the Records
	 */
	public static HashMap<Integer,String> insertToHashMap(ArrayList<Record> r){
		HashMap<Integer,String> h = new HashMap<Integer, String>();
		for (int i=0; i<r.size(); i++){
			h.put(r.get(i).getID(), r.get(i).getName() );
		}//end for
		return h;
	}//end insertToHashMap method

	/**
	 * Inserts all of the Records into a TreeMap with the name as the key.
	 * @param r the ArrayList containing all of the Records
	 * @return t TreeMap containing all of the Records
	 */
	public static TreeMap<String,Integer> insertToTreeMap(ArrayList<Record> r){
		TreeMap<String,Integer> t = new TreeMap<String, Integer>();
		for (int i=0; i<r.size(); i++){
			t.put(r.get(i).getName(), r.get(i).getID());
		}//end for
		return t;
	}//end insertToTreeMap method

	/**
	 * Reads the file and inserts the information into Record objects.
	 * The Records are stored in an ArrayList for further manipulation.
	 * @return an ArrayList of the Records made using the file
	 */
	public static ArrayList<Record> readRecords(){
		ArrayList<Record> r = new ArrayList<Record>();
		Scanner reader = null; 
		try{reader = new Scanner(new FileReader("HashData.txt"));
		}//end try statement
		catch(FileNotFoundException e){
			System.out.println("File not found!");
		}//end catch statement
		while (reader.hasNext()){
			r.add(new Record(reader.nextLine(), Integer.parseInt(reader.nextLine())));
		}//end while loop
		return r;
	}//end readData method

}//end class
