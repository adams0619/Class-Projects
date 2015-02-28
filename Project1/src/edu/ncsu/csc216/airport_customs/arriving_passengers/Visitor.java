package edu.ncsu.csc216.airport_customs.arriving_passengers;

import java.awt.Color;

import edu.ncsu.csc216.airport_customs.queues.CustomsDesk;

/**
 * Class representing a passenger of type Visitor, this class extends the abstract Passenger class and inherits it's methods
 * 
 * @author Adams Ombonga
 * @version 1.0
 */
public class Visitor extends Passenger {

	/**
	 * Variable representing the specific color of the diplomat passenger
	 */
	private Color color;

	/**
	 * Variable representing the minimum processing time for the passenger
	 */
	public static final int MIN_PROCESS_TIME = 180;

	/**
	 * Variable representing the maximum processing time for the passenger
	 */
	public static final int MAX_PROCESS_TIME = 600;

	/**
	 * Variable representing this passengers most recent customs line index
	 */
	private static int mostRecentIndex;

	/**
	 * Constructor method used to create a visitor passenger, this constructors
	 * calls the constructor if its parents class and also sets the passengers color
	 * 
	 * @param arrivalTime
	 *            the arrival time of the passenger
	 * @param processTime
	 *            the processing time for this passenger
	 */
	public Visitor(int arrivalTime, int processTime) {
		super(arrivalTime, processTime);
		setLineIndex(INITIAL_CUSTOMS_LINE_INDEX);

		// Set color based on process time
		// If the process time is less than half the allowed process time then
		// the passengers color is pink otherwise the passengers color is
		// solid red
		if (processTime < ((MIN_PROCESS_TIME + MAX_PROCESS_TIME) / 2)) {
			color = new Color(255, 153, 153);
		} else {
			color = Color.RED;
		}
		
		// Make sure this passenger does not start in a waiting line
		this.removeFromWaitingLine();
	}

	/**
	 * Method used to place passenger in specific line
	 * 
	 * @param desks
	 *            , the customs desk array which will be used to delegate the
	 *            line the passenger will go to
	 */
	public void getInLine(CustomsDesk[] desks) {
		// Call pickLine method to choose a desks line index
		setLineIndex(pickLine(desks));
	}

	/**
	 * Getter/setter method used to get the color for this type of passenger and
	 * also set the passengers color
	 * 
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Method used to place passenger in specific line. If the diplomat desk
	 * line is empty this passenger will prefer to enter that line rather then
	 * its own
	 * 
	 * @param desks
	 *            , the customs desk array which will be used to delegate the
	 *            line the passenger will go to
	 * @return integer representing the chosen line index
	 */
	private int pickLine(CustomsDesk[] desks) {
		
		int diplomatIndex = desks.length / 2;
		// Check that the line indexes never equals/falls below the index for
		// the diplomat index or exceeds the maximum number of available desks
		if (mostRecentIndex < diplomatIndex) {
			mostRecentIndex = diplomatIndex + 1;
			desks[mostRecentIndex].addToLine(this);
			return mostRecentIndex;
		} else {//if (mostRecentIndex > diplomatIndex) {
			mostRecentIndex++;
			if (mostRecentIndex >= desks.length) {
				mostRecentIndex = diplomatIndex + 1;
				desks[mostRecentIndex].addToLine(this);
				return mostRecentIndex;
			}
			desks[mostRecentIndex].addToLine(this);
			return mostRecentIndex;
		} 
	}
}
