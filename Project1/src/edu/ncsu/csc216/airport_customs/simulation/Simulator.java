package edu.ncsu.csc216.airport_customs.simulation;

import java.awt.Color;

import edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger;
import edu.ncsu.csc216.airport_customs.queues.ArrivalHall;
import edu.ncsu.csc216.airport_customs.queues.CustomsDesk;

/**
 * This class represents the Simulator for our airport simulator program, it is
 * responsible for overseeing the simulation. This is done by first creating the
 * arrival hall and customs desks from the user entered information, next the
 * simulator steps through the simulation and determines which passenger
 * leaves/enters the arrival hall and customs desk (respectively) by calling
 * upon the EventCalender. All of the passenger information is then logged when
 * the passenger is being processed in the customs desk.
 * 
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (February 16, 2015)
 *
 */
public class Simulator {

	/**
	 * Variable representing the minimum number of customs desks allowed
	 */
	private static final int MIN_NUM_DESKS = 3;

	/**
	 * Variable representing the maximum number of customs desks allowed
	 */
	private static final int MAX_NUM_DESKS = 17;

	/**
	 * Variable representing the number of customs desks entered by user
	 */
	private int numCustomsDesks;

	/**
	 * Variable representing the number of passengers entered by user
	 */
	private int numPassengers;

	/**
	 * Variable representing the number of steps taken by each passenger in the
	 * simulator (this should be 2 because they enter/leave the arrival hall and
	 * then enter/leave the customs desks
	 */
	private int stepsTaken;

	/** Instance of Log class */
	private Log myLog = new Log();

	/** Instance of Arrival Hall */
	private ArrivalHall arrivingPassengers;

	/** Instance of the current passenger */
	private Passenger currentPassenger;

	/** Instance of Event Calendar */
	private EventCalendar myCalendar;

	/** Instance of CustomsDesk array */
	private CustomsDesk[] customsArea;

	/**
	 * Constructor method used to create a simulator using the number of user
	 * entered customs desks and passengers
	 * 
	 * @param numCustomsDesks
	 *            number of user entered customs desks
	 * @param numPassengers
	 *            number of user entered passengers
	 * @throws IllegalArgumentException
	 *             if number of passengers is not greater than 0
	 */
	public Simulator(int numCustomsDesks, int numPassengers) {
		super();

		if (numPassengers >= 1) {
			this.numPassengers = numPassengers;
		} else {
			throw new IllegalArgumentException(
					"There must be at least one passenger.");
		}

		if (numCustomsDesks >= MIN_NUM_DESKS
				&& numCustomsDesks <= MAX_NUM_DESKS) {
			this.numCustomsDesks = numCustomsDesks;
			customsArea = new CustomsDesk[numCustomsDesks];
		} else {
			throw new IllegalArgumentException(
					"Number of customs desks must be between 3 and 17.");
		}
		
		// Call the setup method to instantiate objects with the user entered
		// information
		setUp();

	}

	/**
	 * Method used to set up instance/constructors and other variables for the
	 * arrival halls class
	 */
	private void setUp() {
		int i = 0;
		while (i < numCustomsDesks) {
			customsArea[i] = new CustomsDesk(myLog);
			i++;
		}
		
		// Initialize/finish creating arrival hall
		arrivingPassengers = new ArrivalHall(numPassengers, customsArea);

		// Initialize event calendar
		myCalendar = new EventCalendar(customsArea, arrivingPassengers);
	}

	/**
	 * Getter method used to determine the total number of steps taken in the
	 * simulation so far
	 * 
	 * @return number of steps taken in the simulation
	 */
	public int getStepsTaken() {
		return stepsTaken;
	}

	/**
	 * Method used to return the total number of steps in the simulator
	 * 
	 * @return totalStepsTaken total number of steps taken
	 */
	public int totalNumberOfSteps() {
		return 2 * numPassengers;
	}

	/**
	 * Step method used to step through the simulation and increment the number
	 * of steps taken accordingly
	 */
	public void step() {
		currentPassenger = null;
		currentPassenger = myCalendar.nextToBeProcessed().processNext();
		stepsTaken++;
	}

	/**
	 * Determines if there are more steps in the simulation
	 * 
	 * @return boolean true if steps is greater than 0, false otherwise
	 */
	public boolean moreSteps() {
		return (stepsTaken < totalNumberOfSteps());
	}

	/**
	 * Method to determine the customs desk index of the current passenger,
	 * return -1 if there is no current passenger
	 * 
	 * @return index of the customs desk line for the current passenger
	 */
	public int getCurrentIndex() {
		if (currentPassenger == null) {
			return Passenger.INITIAL_CUSTOMS_LINE_INDEX;
		} else {
			return currentPassenger.getLineIndex();
		}
	}

	/**
	 * Return the current color of the passenger at the current index
	 * 
	 * @return Color of the current passengers, null if there is no
	 *         currentPassenger
	 */
	public Color getCurrentPassengerColor() {
		if (currentPassenger == null) {
			return null;
		} else {
			return currentPassenger.getColor();
		}
	}

	/**
	 * Method used to determine if the passenger has been processed through the
	 * customs desk
	 * 
	 * @return true if passenger was processed, false otherwise
	 */
	public boolean passengerClearedCustoms() {
		if (currentPassenger == null) {
			return false;
		}
		return !currentPassenger.isWaitingInCustomsLine();
	}

	/**
	 * Method used to calculate the average wait time from the log class
	 * 
	 * @return double representing the average wait time
	 */
	public double averageWaitTime() {
		return myLog.averageWaitTime();
	}

	/**
	 * Method used to calculate the process wait time from the log class
	 * 
	 * @return double representing the process wait time
	 */
	public double averageProcessTime() {
		return myLog.averageProcessTime();
	}
}
