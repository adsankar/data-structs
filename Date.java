/**
 * Date Class
 * @author Aleksander Sankar
 * Analysis of Algorithms Pd. 9
 */
public class Date implements Comparable<Object>{
	//TODO docs
	/**
	 * Fields
	 */
	private int day;
	private int month;
	private int year;
	private int count;

	/**
	 * The default constructor, sets all of the variables to 0
	 */
	public Date(){
		day=0;
		month=0;
		year=0;
		count=0;
	}//end constructor

	/**
	 * The constructor which sets the variables to the values of the parameters.
	 * @param setDay
	 * @param setMonth
	 * @param setYear
	 * @param newNum
	 */
	public Date(int setDay, int setMonth, int setYear, int newNum){
		day=setDay;
		month=setMonth;
		year=setYear;
		count=newNum;
	}//end constructor

	/**
	 * Sets the value of the day with the parameter given.
	 * @param newDay
	 */
	public void setDay(int newDay){
		day=newDay;
	}//end setDay

	/**
	 * Sets the value of the month with the parameter given.
	 * @param newMonth
	 */
	public void setMonth(int newMonth){
		month=newMonth;
	}//end setMonth

	/**
	 * Sets the value of the year with the parameter given.
	 * @param newYear
	 */
	public void setYear(int newYear){
		year=newYear;
	}//end setYear

	/**
	 * Sets the value of the count with the parameter given.
	 * @param newNum
	 */
	public void setCount(int newNum){
		count=newNum;
	}

	/**
	 * Returns the day of the Date object.
	 * @return the day
	 */
	public int getDay(){
		return day;
	}//end getDay

	/**
	 * Returns the month of the Date object.
	 * @return the month
	 */
	public int getMonth(){
		return month;
	}//end getMonth

	/**
	 * Returns the year of the Date object.
	 * @return the year
	 */
	public int getYear(){
		return year;
	}//end getYear

	/**
	 * Returns the repeat count of the Date object.
	 * @return repeat
	 */
	public int getCount(){
		return count;
	}//end getNum

	/**
	 * Compares the two Date objects and shows which date is farther ahead in time.
	 * @param d the Date being compared to
	 * @return 
	 */
	public int compareTo(Object o){	
		Date d = (Date)o;
		if(year==d.getYear() && month==d.getMonth() && day==d.getDay() && count>d.getCount())
			return 1;
		if(year==d.getYear() && month==d.getMonth() && day==d.getDay())
			return 0;
		if(year>d.getYear())
			return 1;
		if(year==d.getYear() && month>d.getMonth())
			return 1;
		if(year==d.getYear() && month==d.getMonth() && day>d.getDay())
			return 1;
		return -1;
	}//end compareTo

}//end class