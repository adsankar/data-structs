package airport;

/**
 * This file stores the information for each passenger reservation. Passenger 
 * reservations consist of the passenger's name (a string) and the flight
 * he/she is on, marked by a number.
 */
public class PassengerReservation {

	//the name of the passenger
	private String passengerName;
	//the number (like an ID) of the flight
	private int flightNumber;

	/**
	 * Constructor which creates a new PassengerReservation with the given name
	 * and flight.
	 * @param name
	 * @param flight
	 */
	public PassengerReservation(String name, int flight){
		this.passengerName = name;
		this.flightNumber = flight;
	}

	/**
	 * Returns the name of the passenger
	 * @return passengerName
	 */
	public String getPassengerName() {
		return passengerName;
	}

	/**
	 * Sets the name of the passenger
	 * @param passengerName
	 */
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
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

}//end class