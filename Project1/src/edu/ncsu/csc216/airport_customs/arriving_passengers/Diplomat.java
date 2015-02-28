package edu.ncsu.csc216.airport_customs.arriving_passengers;

import java.awt.Color;

import edu.ncsu.csc216.airport_customs.queues.CustomsDesk;

/**
 * Class representing a passenger of type Diplomat, this class extends the abstract Passenger class and inherits it's methods
 * 
 * @author Adams Ombonga
 * @version 1.0
 */
public class Diplomat extends Passenger {

	/**
	 * Variable representing the specific color of the diplomat passenger
	 */
	private Color color = new Color(153, 255, 153);

	/**
	 * Variable representing the minimum processing time for the passenger
	 */
	public static final int MIN_PROCESS_TIME = 30;

	/**
	 * Variable representing the maximum processing time for the passenger
	 */
	public static final int MAX_PROCESS_TIME = 90;

	/**
	 * Constructor method used to create a visitor passenger, this constructors
	 * calls the constructor if its parents class and also sets the passengers color
	 * 
	 * @param arrivalTime
	 *            the arrival time of the passenger
	 * @param processTime
	 *            the processing time for this passenger
	 */
	public Diplomat(int arrivalTime, int processTime) {
		super(arrivalTime, processTime);
		setLineIndex(INITIAL_CUSTOMS_LINE_INDEX);

		// Set color based on process time
		// If the process time is less than half the allowed process time then
		// the passengers color is light green otherwise the passengers color is
		// solid green
		if (processTime < ((MIN_PROCESS_TIME + MAX_PROCESS_TIME) / 2)) {
			color = new Color(153, 255, 153);
		} else {
			color = Color.GREEN;
		}
		
		// Make sure this passenger does not start in a waiting line
		this.removeFromWaitingLine();
	}

	/**
	 * Method used to place passenger in their specific line.
	 * 
	 * @param desks
	 *            CustomsDesk Array containing the different customs desk
	 *            available.
	 */
	public void getInLine(CustomsDesk[] desks) {
		int myLine = desks.length / 2;
		setLineIndex(myLine);
		desks[myLine].addToLine(this);
	}

	/**
	 * Getter method used to get the color for this type of passenger
	 * 
	 * @return the color for this passenger
	 */
	public Color getColor() {
		return color;
	}

}
