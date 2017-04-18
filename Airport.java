package airport;

import java.util.ArrayList;

/**
 * The Airport class contains a list of flights, stored in an ArrayList, and 
 * performs various actions on the flights or passengers in the flight.
 */
public class Airport {

	// store multiple flights in an ArrayList
	private ArrayList<Flight> flightList;

	/**
	 * Constructor which only initializes the ArrayList containing the flights
	 * but does not add any flights to it.
	 */
	public Airport() {
		flightList = new ArrayList<Flight>();

	}

	/**
	 * Adds a flight with a given flight number and destination to the airport.
	 * @param flightNumber
	 * @param destination
	 * @return an airport with the flight added or the unmodified one if the
	 * flight was already added or the airport already had 20 flights in it
	 */
	public Airport addFlight(int flightNumber, String destination) {
		// first check if there is enough space in the airport to be able to
		//add a flight (max 20)
		if (flightList.size() < 20) { 
			for (Flight f : flightList) {
				//iterate through the list of flights, and if a flight with the
				//specified number is already there, return the Airport
				if (f.getFlightNumber() == flightNumber) {
					return this;
				}
			}
			//add a flight to the flight list if it meets the conditions
			flightList.add(new Flight(flightNumber, destination));
		}
		//finally return the airport
		return this;
	}

	/**
	 * Return the number of flights in the Airport object
	 * @return the number of flights
	 */
	public int numFlights() {
		//the number of flights is the number of Flight objects in the 
		//ArrayList
		return flightList.size();
	}

	/**
	 * Add a passenger with the given name to a specific flight.
	 * @param flightNumber
	 * @param passengerName
	 * @return
	 */
	public boolean addPassengerReservation(int flightNumber,
			String passengerName) {
		for (Flight f : flightList) {
			//iterate until the correct flight is reached
			if (f.getFlightNumber() == flightNumber) {
				if (f.getReservationList().size() < 100) {
					//only add a reservation if there are less than 100
					//reservations
					f.getReservationList().add(
							new PassengerReservation(passengerName,
									flightNumber));
					return true;//successfully added
				}
			}
		}

		return false; //the reservation was not added
	}

	/**
	 * Returns the number of passengers on the flight by finding the size of
	 * the ArrayList that stores them
	 * @param flightNumber
	 * @return the number of passengers on the flight
	 */
	public int numPassengersOnFlight(int flightNumber) {
		for (Flight f: flightList){
			//iterate through the flight list
			if (f.getFlightNumber() == flightNumber){
				//if you are on the correct flight number, return the number of
				//passengers
				return f.getReservationList().size();
			}
			//if the flight is not found, return -1
			return -1;
		}
		return -1;
	}

	/**
	 * Return the number of passengers on the flight with the specified name.
	 * @param flightNumber the flight we are looking on
	 * @param name the name we are looking for
	 * @return the number of passengers with that name
	 */
	public int numPassengersOnFlight(int flightNumber, String name) {
		int count = 0;
		for (Flight f: flightList){
			//iterate through until you are on the correct flight
			if (f.getFlightNumber() == flightNumber){
				for (PassengerReservation pr : f.getReservationList()){
					//iterate through the passenger list and search for that 
					//name
					if (pr.getPassengerName().equals(name)){
						count++;
						//increment for every instance of that name
					}

				}
				//that passenger is not on the flight, so return 0
				return count;
			} 
		}
		//there is no flight with that flight number, so return -1
		return -1;
	}

	/**
	 * Cancels the flight given a specific flight number
	 * @param flightNumber the flight to cancel
	 * @return true if cancelled successfully and false if not
	 */
	public boolean cancelFlight(int flightNumber) {
		for (Flight f : flightList){
			//iterate through the flight list until the correct flight is 
			//reached
			if (f.getFlightNumber() == flightNumber){
				f.getReservationList().clear();//empty the passenger list by
				//clearing that ArrayList
				flightList.remove(f); //remove the flight from the list of all
				//flights
				return true;// to say that this cancellation completed 
				//successfully
			}
		}
		return false;// if the flight provided was not found
	}

	/**
	 * Cancels a passenger reservation when provided with the flight that person
	 * is on and the name of the passenger.
	 * @param flightNumber the flight to look on
	 * @param passengerName the passenger to remove
	 * @return true if completed successfully, false if not
	 */
	public boolean cancelPassengerReservation(int flightNumber,
			String passengerName) {
		for (Flight f : flightList){
			//iterate through the list of flights until the correct flight is 
			//reached
			if (f.getFlightNumber() == flightNumber){
				ArrayList<PassengerReservation> r = f.getReservationList();
				for (PassengerReservation pr : r){
					//iterate through the passenger list until the correct
					//passenger is reached
					if (pr.getPassengerName().equals(passengerName)){
						r.remove(pr);//remove the passenger and return true
						return true;
					}
				}
			}
		}
		//return false if the passenger was not on the flight or the flight
		//was not at that airport
		return false;
	}

	/**
	 * Returns the number of flights going to a certain destination
	 * @param city 
	 * @return the number of flights
	 */
	public int numFlightsGoingToCity(String city) {
		int count = 0;
		for (Flight f: flightList){
			//iterate through the list of flights until the correct on is 
			//reached
			if (f.getDestination().equals(city)){
				count++;
				//increment when the city is found
			}
		}
		return count;
	}

	/**
	 * Return the number of passengers going to a certain city by finding all
	 * flights that are going there and adding all of the people from each 
	 * flight 
	 * @param city
	 * @return the number of people going to that city
	 */
	public int numPassengersGoingToCity(String city) {
		int count = 0;
		for (Flight f: flightList){
			//iterate through the list of flights and if it matches the input
			//city, add the number of people on that flight
			if (f.getDestination().equals(city)){
				count += f.getReservationList().size();

			}
			//if no flights are going to that city
			count = -1;
		}
		return count;
	}

	/**
	 * Returns the list of flights
	 * @return 
	 */
	public ArrayList<Flight> getFlightList() {
		return flightList;
	}

	/**
	 * Set the flight list
	 * @param flightList
	 */
	public void setFlightList(ArrayList<Flight> flightList) {
		this.flightList = flightList;
	}

}// end class