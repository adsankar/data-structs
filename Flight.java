package airport;

import java.util.ArrayList;

/**
 * The Flight class defines the flight object which stores information about
 * the flight number and the destination. It also contains an ArrayList which
 * holds all of the passenger reservations. Getters and setters for all of the 
 * fields are included.
 */
public class Flight {

	//an int to store the number of the flight, similar to an ID
	private int flightNumber;
	//the destination of each flight is a city and the city name is stored as a
	//string
	private String destination;
	//a list which holds all of the information pertaining to the passengers
	private ArrayList<PassengerReservation> reservationList;

	/**
	 * Constructs a new Flight and sets the flight number and destination. It
	 * also initializes the passenger reservation list but it is empty at first.
	 * @param flight the number of the flight
	 * @param destination the destination city's name
	 */
	public Flight(int flight, String destination){
		this.flightNumber = flight;
		this.destination = destination;
		reservationList = new ArrayList<PassengerReservation>();
	}

	/**
	 * Returns the number of the flight
	 * @return flightNumber
	 */
	public int getFlightNumber() {
		return flightNumber;
	}

	/**
	 * Sets the number of the flight
	 * @param flightNumber
	 */
	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}


	/**
	 * Returns the destination of the flight
	 * @return destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * Sets the destination of the flight
	 * @param destination
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * Returns the current flight's reservation list
	 * @return reservation list
	 */
	public ArrayList<PassengerReservation> getReservationList() {
		return reservationList;
	}

	/**
	 * Sets the reservation list of the flight
	 * @param reservationList
	 */
	public void setReservationList(ArrayList<PassengerReservation> reservationList) {
		this.reservationList = reservationList;
	}

}//end class