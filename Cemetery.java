import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import java.util.TreeMap;

/**
 * Cemetery Class
 * @author Aleksander Sankar
 * Analysis of Algorithms Pd. 9
 */
public class Cemetery{
	//TODO docs

	TreeMap<Date, Tombstone> sorted = new TreeMap<Date, Tombstone>();

	/**
	 * Constructor for a Cemetery object which reads from the file.
	 */
	public Cemetery(){
		String line = "";
		int count = 0;
		double ageSum = 0.0;
		int numPeople=0;
		Date low=new Date(1, 7, 1814, 0);
		Date high=new Date(1, 12, 1852, 0);
		try{ Scanner reader = new Scanner (new FileReader("cemetery.txt"));
		while(reader.hasNext()){
			while(count<5){
				reader.nextLine();
				count++;
			}//end while			
			Tombstone t = new Tombstone();
			line = reader.nextLine();
			t.setName(line.substring(0, 25));		
			//TODO fix the snag
			Date d = new Date(Integer.parseInt(line.substring(25, 27)),
					convertMonth(line.substring(28,31)),
					Integer.parseInt(line.substring(32, 36)),
					count);
			t.setBurialDate(d);

			t.setAge(line.substring(37, 42));
			t.setAddress(line.substring(42));
			if((t.getAddress().contains("Little Carter Lane"))){
				if(t.getBurialDate().compareTo(low)>=0 && t.getBurialDate().compareTo(high)<=0){
					numPeople++;
					ageSum += convertAge(t.getAge());
				}//end if
			}//end if
			sorted.put(d, t);
			count++;
			
		}//end while

		}//end try
		catch(FileNotFoundException e){
			System.out.println("File not found!");
		}//end catch
		count-=4;//accounts for the 4 lines that don't have Tombstone data

		System.out.println("Number of people buried in Fish Street Cemetery: "+count);
		System.out.println();
		double averageAge = ageSum / numPeople;
		System.out.println(numPeople+" people live on some part of Little Carter Lane.");
		System.out.println("Average age on Little Carter Lane: " + averageAge + " years.");
		System.out.println();
	}//end constructor

	/**
	 * Setter for the TreeMap.
	 * @param t
	 */
	public void setMap(TreeMap<Date, Tombstone> t){
		sorted=t;
	}//end setMap

	/**
	 * Getter for the TreeMap.
	 * @return the TreeMap
	 */
	public TreeMap<Date, Tombstone> getMap(){
		return sorted;
	}//end getMap

	/**
	 * Converts the string representing a month to an integer.
	 * @param month
	 * @return a number representing the month
	 */
	public int convertMonth(String month){
		int monthNum = -1;
		if(month.equals("Jan")) monthNum = 1;
		if(month.equals("Feb")) monthNum = 2;
		if(month.equals("Mar")) monthNum = 3;
		if(month.equals("Apr")) monthNum = 4;
		if(month.equals("May")) monthNum = 5;
		if(month.equals("Jun")) monthNum = 6;
		if(month.equals("Jul")) monthNum = 7;
		if(month.equals("Aug")) monthNum = 8;
		if(month.equals("Sep")) monthNum = 9;
		if(month.equals("Oct")) monthNum = 10;
		if(month.equals("Nov")) monthNum = 11;
		if(month.equals("Dec")) monthNum = 12;

		return monthNum;
	}//end convertMonth
	
	/**
	 * Converts the integer representing the month to a string.
	 * @param monthNum
	 * @return a string representing the month
	 */
	public String convertMonth(int monthNum){
		String month = "";
		if(monthNum == 1) month = "Jan";
		if(monthNum == 2) month = "Feb";
		if(monthNum == 3) month = "Mar";
		if(monthNum == 4) month = "Apr";
		if(monthNum == 5) month = "May";
		if(monthNum == 6) month = "Jun";
		if(monthNum == 7) month = "Jul";
		if(monthNum == 8) month = "Aug";
		if(monthNum == 9) month = "Sep";
		if(monthNum == 10) month = "Oct";
		if(monthNum == 11) month = "Nov";
		if(monthNum == 12) month = "Dec";
		return month;
	}

	/**
	 * Converts the string representing the age to a double.
	 * @param a
	 * @return a double representing the age
	 */
	public double convertAge(String a){
		a = a.trim();
		double ageNum = 0;
		int midpoint=0;
		if (!(a.contains(".") || a.contains("d") || a.contains("w"))){
			return Double.parseDouble(a);
		}//end if
		for(int i=0; i<a.length(); i++){
			switch (a.charAt(i)){
			case 'w':{
				midpoint = i;
				String weekNumStr = a.substring(0, midpoint);
				int weekNum = Integer.parseInt(weekNumStr);
				return (weekNum * 7) / 365.0;
			}//end case
			case 'd':{
				midpoint = i;
				String dayNumStr = a.substring(0, midpoint);
				int dayNum = Integer.parseInt(dayNumStr);
				return dayNum / 365.0;

			}//end case
			case '.':{
				midpoint = i;
				String yearNumStr = a.substring(0, midpoint);
				String monthNumStr = a.substring(midpoint+1);
				int yearNum = Integer.parseInt(yearNumStr);
				int monthNum = Integer.parseInt(monthNumStr);
				return yearNum + (monthNum * 30.5)/365.0;
			}//end case

			}//end switch

		}//end for loop
		return ageNum;

	}//end convertAge method
	
	/**
	 * Prints the Tombstone data (name and burial date) in order by burial date.
	 */
	public void print(){
		Set<Date> nameSet = sorted.keySet();
		Iterator<Date> itr = nameSet.iterator();
		Date d=new Date();
		System.out.println("People in cemetery (sorted by burial date): ");
		while(itr.hasNext()){
			d = itr.next();
			System.out.print("Name: " + ((Tombstone)sorted.get(d)).getName());
			if(d.getDay()>=10)//for spacing and alignment
				System.out.println("Burial Date: " + convertMonth(d.getMonth()) + " " + d.getDay() + ", " + d.getYear()+" "+d.getCount());
			if(d.getDay()<10)
				System.out.println("Burial Date: " + convertMonth(d.getMonth()) + "  " + d.getDay() + ", " + d.getYear()+" "+d.getCount());
		}//end while loop
	}//end print method

}//end class