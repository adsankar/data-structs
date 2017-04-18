/**
 * Tombstone Class
 * @author Aleksander Sankar
 * Analysis of Algorthms Pd. 9
 */
public class Tombstone{

	//fields
	private String name;
	private Date burialDate;
	private String age;
	private String address;

	/**
	 * Default constructor which sets all of the variables to null.
	 */
	public Tombstone(){
		name=null;
		burialDate=null;
		age=null;
		address=null;
	}//end constructor

	/**
	 * Constructor which sets the variables to the parameters.
	 * @param newName
	 * @param newBurialDate
	 * @param newAge
	 * @param newAddress
	 */
	public Tombstone(String newName, Date newBurialDate, String newAge, String newAddress){
		name=newName;
		burialDate=newBurialDate;
		age=newAge;
		address=newAddress;
	}//end constructor

	/**
	 * Setter for the name.
	 * @param newName
	 */
	public void setName(String newName){
		name=newName;
	}//end setName

	/**
	 * Setter for the burial date.
	 * @param newBurialDate
	 */
	public void setBurialDate(Date newBurialDate){
		burialDate=newBurialDate;
	}//end setBurialDate

	/**
	 * Setter for the age.
	 * @param newAge
	 */
	public void setAge(String newAge){
		age=newAge;
	}//end setAge

	/**
	 * Setter for the address.
	 * @param newAddress
	 */
	public void setAddress(String newAddress){
		address=newAddress;
	}//end setAdress

	/**
	 * Getter for the name.
	 * @return the name
	 */
	public String getName(){
		return name;
	}//end getName

	/**
	 * Getter for the burial date.
	 * @return the burial date
	 */
	public Date getBurialDate(){
		return burialDate;
	}//end getBurialDate

	/**
	 * Getter for the age.
	 * @return the age
	 */
	public String getAge(){
		return age;
	}//end getAge

	/**
	 * Getter for the address.
	 * @return the address
	 */
	public String getAddress(){
		return address;
	}//end getAddress

}//end class