package edu.ncsu.csc216.airport_customs.arriving_passengers;

import java.awt.Color;

import edu.ncsu.csc216.airport_customs.queues.CustomsDesk;

/**
 * This is an abstract class that defines the general type of passenger and the
 * methods they should implement
 * 
 * @author Adams Ombonga
 * @version 1.0 (February 2, 2015)
 */
public abstract class Passenger {
	/**
	 * Variable representing customs line index showing that no passengers are
	 * in a processing line yet
	 */
	public static final int INITIAL_CUSTOMS_LINE_INDEX = -1;
	/**
	 * Variable representing the arrival time of the passenger
	 */
	private int arrivalTime;
	/**
	 * Variable representing the wait time of the passenger
	 */
	private int waitTime;
	/**
	 * Variable representing the process time of the passenger
	 */
	private int processTime;

	/**
	 * Variable representing the specific line the passenger is assigned to
	 */
	private int lineIndex;

	/**
	 * Variable representing whether the passenger is currently
	 * processing/waiting
	 */
	private boolean waitingProcessing;

	/**
	 * Constructor method used to create passengers
	 * 
	 * @param arrivalTime
	 *            arrival time of the passenger
	 * @param processTime
	 *            processing time for the passenger
	 * @throws IllegalArgumentException
	 *             throws exception if the arrival time or the process time is
	 *             less than or equal 0
	 */
	public Passenger(int arrivalTime, int processTime) {
		if (arrivalTime < 0 || processTime < 0) {
				throw new IllegalArgumentException();
		} else {
			this.arrivalTime = arrivalTime;
			this.processTime = processTime;
			this.waitingProcessing = false;
		}
	}

	/**
	 * Method used to determine if a passenger is waiting in a customs line to
	 * be processed
	 * 
	 * @return boolean if the passenger is currently waiting in a customs line
	 */
	public boolean isWaitingInCustomsLine() {
		return waitingProcessing;
	}

	/**
	 * Method used to remove a passenger from the waiting line by setting the
	 * flag for waitingProcesing to false
	 */
	public void removeFromWaitingLine() {
		waitingProcessing = false;
	}

	/**
	 * Getter method used to get the arrival time for the passenger
	 * 
	 * @return arrivalTime, the arrival time of the passenger
	 */
	public int getArrivalTime() {
		// TODO Finish method logic in child classes
		return arrivalTime;
	}

	/**
	 * Setter method used to set the wait time for the passenger
	 * 
	 * @param waitTime
	 *            the waitTime to set
	 */
	public void setWaitTime(int waitTime) {
		// TODO Finish method logic in child classes
		this.waitTime = waitTime;
	}

	/**
	 * Getter method used to get the wait time of the passenger
	 * 
	 * @return the waitTime
	 */
	public int getWaitTime() {
		return waitTime;
	}

	/**
	 * Getter method used to get the processing time of the passenger
	 * 
	 * @return the processTime
	 */
	public int getProcessTime() {
		return processTime;
	}

	/**
	 * Getter method used to determine the line index of the passenger
	 * 
	 * @return the lineIndex
	 */
	public int getLineIndex() {
		return lineIndex;
	}

	/**
	 * Setter method used to set the line index for the passenger in their
	 * corresponding customs desks
	 * 
	 * @param lineIndex
	 *            the customs desk lineIndex to set for the corresponding
	 *            passengers
	 */
	protected void setLineIndex(int lineIndex) {
		this.lineIndex = lineIndex;
		this.waitingProcessing = true;
	}

	/**
	 * Method which accepts an array of customs desk as a parameter.Using this
	 * array this method determines the specific customs desk (line) that the
	 * passenger will go to.
	 * 
	 * @param desk
	 *            array of customs desk for diplomat, visitor, and resident
	 *            passengers
	 */
	public abstract void getInLine(CustomsDesk[] desk);

	/**
	 * Getter method used to return the color of the specific passenger
	 * 
	 * @return Color for the specific passenger
	 */
	public abstract Color getColor();
}
