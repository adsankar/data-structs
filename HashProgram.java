import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Hash Program
 * @author Aleksander Sankar
 * Analysis of Algorithms Pd. 9
 */
public class HashProgram {

	private static Component frame;

	/**
	 * This program reads a file containing names of students with a corresponding ID.
	 * The names and IDs are stored into Record objects and are inserted into a hash table.
	 * The user can then search for a student by entering the student's ID.
	 * @param args not used
	 */
	public static void main(String[] args) {

		ArrayList<Record> r = new ArrayList<Record>();
		r = readRecords();
		ListNode[] l = makeHashTable(r);
		printTable(l);

		int o = JOptionPane.showConfirmDialog(
				frame,
				"Do you want to search for a person? (click no or X to quit)",
				"Search?",
				JOptionPane.YES_NO_OPTION);

		if (o==-1 || o==1) System.exit(0);//ends program if user clicks no or x on the dialog box

		int c =0;
		while (c==0){

			String t = (String)JOptionPane.showInputDialog(
					frame,
					"Enter the ID of the student you want to search for:",
					"Enter ID",
					JOptionPane.PLAIN_MESSAGE,
					null,
					null,
					"");

			int f = 0;
			if (t==null ||t.equals("")){
				JOptionPane.showMessageDialog(
						frame,
						"You did not enter anything!",
						"No Input",
						JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
			else f=Integer.parseInt(t);

			JOptionPane.showMessageDialog(frame,
					findPerson(f, l),
					"Search Result",
					JOptionPane.PLAIN_MESSAGE);

			c = JOptionPane.showConfirmDialog(
					frame,
					"Do you want to search for another person? (click no or X to quit)" +
					"",
					"Search Again?",
					JOptionPane.YES_NO_OPTION);
			if (c==1 || c==-1) System.exit(0);//ends program if user clicks no or x on the dialog box

		}//end while

	}//end main

	/**
	 * This method prints out all of the elements of the ListNode, separated by tabs.
	 * @param head the ListNode to be printed
	 */
	public static void printNodes(ListNode head){
		if (head != null){
			ListNode t= head;
			while (t !=null){
				System.out.print(t.getValue()+"\t\t");
				t = t.getNext();
			}//end while

		}//end if
	}// end printNodes method

	/**
	 * This method searches through the hash table and finds the student by ID number.
	 * @param id, inputted by the user
	 * @param l, the hash table in the form of an array of ListNodes
	 * @return a string containing the results of the query
	 */
	public static String findPerson(int id, ListNode[] l){
		int numSteps = 1;
		int temp = id%23;
		if (l[temp]==null){
			return id+" was not found.";
		}//end if

		else{
			ListNode t = l[temp];

			if (t.getNext()==null && ((Record)t.getValue()).getID() == id){
				return t.getValue().toString()+" was found after "+numSteps+" step(s).";
			}//end if

			while (t.getNext()!= null){

				if (((Record)t.getValue()).getID() == id){
					return t.getValue().toString()+" was found after "+numSteps+" step(s).";
				}//end if
				else {
					numSteps++;
					t=t.getNext();
					if (t.getNext()==null && ((Record)t.getValue()).getID() == id){
						return t.getValue().toString()+" was found after "+numSteps+" step(s).";
					}//end if
				}//end else
			}//end while

			return id+" was not found.";
		}//end else

	}//end findPerson method

	/**
	 * Prints the hash table with indicies.
	 * @param l the ListNode array to be printed
	 */
	public static void printTable(ListNode[] l){
		for (int i=0; i<l.length; i++){
			ListNode t = l[i];
			System.out.print(i+"\t");
			printNodes(t);		
			System.out.println();;
		}//end for

	}//end printTable method


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

	/**
	 * Makes the hash table from the Record objects. It is stored as an array of ListNodes.
	 * The collisions are solved by chaining and the number of collisions are displayed.
	 * @param r the set of Records to be inserted
	 * @return a ListNode array representing the hash table
	 */
	public static ListNode[] makeHashTable(ArrayList<Record> r){
		ListNode[] l = new ListNode[23];
		int numCollisions = 0;

		for (int i=0; i<r.size(); i++){
			int target = hash(r.get(i));
			ListNode temp = l[target];

			if (temp == null){
				//no collision;
				l[target] = new ListNode(r.get(i),l[target]);
			}//end if

			if(temp !=null){ 
				//collision here
				numCollisions++;
				l[target] = new ListNode(r.get(i),l[target]);
			}//end if

		}//end for

		//show the number of collisions
		JOptionPane.showMessageDialog(frame,
				"Number of collisions: "+numCollisions,
				"Hash Report",
				JOptionPane.PLAIN_MESSAGE);

		return l;
	}//end makeHashTable method

	/**
	 * The hash function (mod 23), used for building the hash table.
	 * @param r the Record used
	 * @return and int representing the hash index
	 */
	public static int hash(Record r){
		return r.getID()%23;
	}//end hash method

}//end class