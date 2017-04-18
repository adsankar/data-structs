/**
 * Record Class
 * @author Aleksander Sankar
 * Analysis of Algorithms Pd. 9
 */
public class Record {
	
	//fields for ID number and name
	private int IDnumber;
	private String name;

	/**
	 * Getter for the ID number
	 * @return the id of the Record
	 */
	public int getID() {
		return IDnumber;
	}//end getID method
	
	/**
	 * Getter for the name
	 * @return the name of the Record
	 */
	public String getName() {
		return name;
	}//end getName method

	@Override
	/**
	 * Converts the Record to a string representation (the name)
	 */
	public String toString() {
		return getName();
	}//end toString method


	/**
	 * Constructor for a Record object
	 * @param newName the name of the new Record
	 * @param newId the ID of the new Record
	 */
	public Record (String newName, int newId){
		name = newName;
		IDnumber = newId;
	}//end constructor
	
}//end Record class