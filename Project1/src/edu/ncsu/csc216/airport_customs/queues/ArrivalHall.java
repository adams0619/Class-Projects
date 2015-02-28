package edu.ncsu.csc216.airport_customs.queues;

import edu.ncsu.csc216.airport_customs.arriving_passengers.ArrivingAirplanes;
import edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger;

/**
 * Class representing the arrival hall in our airport customs simulator. This
 * class is responsible for housing the passengers once they have de-planed from
 * ArrivingAirplanes and helping passengers pass from the arrival hall to their
 * respective customs desks
 * 
 * @author Adams Ombonga (amombong)
 * @version 2.0 (February 16, 2015)
 */
public class ArrivalHall implements TransitSystem {

	/** Instance of Customs Desks */
	private CustomsDesk[] customsArea;

	/**
	 * Instance of PassengerQueue representing the waiting line for the
	 * passengers in the arrival hall
	 */
	private PassengerQueue incomingPassengers = new PassengerQueue();

	/**
	 * Constructor method for Arrival hall that accepts a number of passengers
	 * and an array of customs desks as parameters. Using these parameters
	 * passengers are generated and placed in a waiting line queue for the
	 * arrival hall
	 * 
	 * @param numPassengers
	 *            number of passengers in the simulation (user-entered)
	 * @param desks
	 *            array of various passengers customs desks (user-entered)
	 */
	public ArrivalHall(int numPassengers, CustomsDesk[] desks) {
		this.customsArea = desks;
		int passengersLeftToGen = numPassengers;
		
		// While loop used to generate and add passengers to arrival hall waiting
		// line
		while (passengersLeftToGen > 0) {
			Passenger newPass = ArrivingAirplanes.generatePassenger();
			incomingPassengers.add(newPass);
			// Decrement the number of passengers to generate	
			passengersLeftToGen--;

		}
	}

	/**
	 * Method used to determine the remaining amount of passengers in the queue
	 * 
	 * @return the remaining size of the passengers queue
	 */
	public int size() {
		return incomingPassengers.size();
	}

	/**
	 * Method used to determine if there a passengers to still be processed
	 * 
	 * @return true if the queue still has passengers
	 */
	public boolean hasNext() {
		return !incomingPassengers.isEmpty();
	}

	/**
	 * Method used to send the passenger in the front of the line the signal to
	 * get in a customs desk line. That passenger is the removed from the
	 * arrival hall queue
	 * 
	 * @return passenger at the front of the queue who is to be processed
	 */
	public Passenger processNext() {
		Passenger p = incomingPassengers.remove();
		p.getInLine(customsArea);
		return p;
	}

	/**
	 * Method used to determine when the front passenger in the queue will leave
	 * the arrival hall. Return Integer.MAX_VALUE if the line is empty
	 * 
	 * @return when the time when current passenger will leave the arrival hall,
	 *         return Integer.MAX_VALUE if the line is empty
	 */
	public int departTimeNext() {
		if (incomingPassengers.isEmpty()) {
			return Integer.MAX_VALUE;
		} else {
			// When this passenger will finish processing & leave the arrival
			// hall
			return incomingPassengers.front().getArrivalTime();
		}
	}

}
