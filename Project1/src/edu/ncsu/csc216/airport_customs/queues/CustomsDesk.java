package edu.ncsu.csc216.airport_customs.queues;

import edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger;
import edu.ncsu.csc216.airport_customs.simulation.Log;

/**
 * This class represents the customs desks in our airport simulator. It is
 * responsible for being the final place passengers go before finally being
 * processed and leaving the airport (simulator). This class implements the
 * TransitSystem interface to help fulfill these responsibilities and also
 * contains a PassengerQueue line where the passengers wait before being
 * processed by their respective customs desk.
 * 
 * @author Adams Ombonga
 * @version 1.0
 *
 */
public class CustomsDesk implements TransitSystem {

	/**
	 * Variable representing when a customs desk is available
	 */
	private int timeWhenAvailable;

	/**
	 * Instance of passenger queue
	 */
	private PassengerQueue line;

	/**
	 * Instance of log class used for logging passenger information
	 */
	private Log log;

	/**
	 * Constructor method used to create a customs desk and instantiate the
	 * customs desk line (queue)
	 * 
	 * @param log
	 *            used to hold passenger information throughout simulation
	 */
	public CustomsDesk(Log log) {
		this.log = log;
		line = new PassengerQueue();
	}

	/**
	 * This method is responsible for add a passenger to the end of the selected
	 * passengers customs desk line. Once done the passengers wait time is
	 * calculated as well as the time when the line will next be available
	 * (empty). Lastly the condition for waitingProcessing is set to true
	 * because the passenger is now waiting in line to be processed.
	 * 
	 * @param nextPass
	 *            passenger parameter containing the next passenger passed to
	 *            this customs desk line
	 */
	public void addToLine(Passenger nextPass) {

		// If the passenger arrives when the line is available then they
		// don't have to wait and the wait time can be set to 0. This line
		// should be available once the passenger has finished processing
		if (nextPass.getArrivalTime() >= timeWhenAvailable) {
			nextPass.setWaitTime(0);
			timeWhenAvailable = nextPass.getProcessTime()
					+ nextPass.getArrivalTime();

			// Add passenger to end of customs line
			line.add(nextPass);
		} else {
			// If the passengers arrives when the line is still unavailable then
			// subtract the arrival time of the passenger from the time when the
			// line will be available in order to get the time the passenger
			// must wait in order for the line to be available. This line should
			// be available once the passenger has finished waiting and finished
			// processing
			nextPass.setWaitTime(timeWhenAvailable - nextPass.getArrivalTime());
			timeWhenAvailable += nextPass.getProcessTime();

			// Add passenger to end of customs line
			line.add(nextPass);

		}
	}

	/**
	 * Method used to determine if there are still passengers to be processed
	 * 
	 * @return true if the line still has passengers, false otherwise
	 */
	@Override
	public boolean hasNext() {
		return !line.isEmpty();
	}

	/**
	 * Method used to log the information for the passenger in the front of the
	 * line. Once this is completed that passenger is removed from the line and
	 * moves on with their processing.
	 * 
	 * @return the removed passenger to be processed.
	 */
	@Override
	public Passenger processNext() {
		// Remove the passenger from queue and save the instance of the
		// passenger to p
		Passenger p = line.remove();
		
		// Log the info for the passenger at the beginning of the line
		log.logItem(p);

		// Remove this passengers from its waiting line
		p.removeFromWaitingLine();

		// Remove this passenger from the beginning of the customs line desks
		// line
		return p;
	}

	/**
	 * Method used to determine when the front passenger in the queue will leave
	 * the customs desk line. Return the Integer.MAX_VALUE if the line is empty
	 * 
	 * @return time when current passenger will leave the customs desk line,
	 *         return Integer.MAX_VALUE if the line is empty
	 */
	@Override
	public int departTimeNext() {
		// Is the current customs desk line empty?
		if (line.isEmpty()) {
			return Integer.MAX_VALUE;
		} else {
			// When this passenger will finish processing & leave the simulation
			return (line.front().getProcessTime() + line.front().getWaitTime() + line
					.front().getArrivalTime());
		}
	}

	/**
	 * Method used to find the remaining number of passengers waiting in line
	 * 
	 * @return number of passengers still waiting in line
	 */
	@Override
	public int size() {
		return line.size();
	}

}
