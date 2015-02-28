package edu.ncsu.csc216.airport_customs.simulation;

import edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger;

/**
 * Log class used to log variables and statistical information about our
 * program. In this instance the log class is responsible for logging the total
 * number of completed passengers as well as providing the average wait time and
 * process time for the passengers.
 * 
 * @author Adams Ombonga (amombong) version 1.0
 */
public class Log {

	/**
	 * Variable representing the total number of processed passengers
	 */
	private int numCompleted;

	/**
	 * Variable representing the total amount of waiting time for passengers
	 */
	private int totalWaitTime;

	/**
	 * Variable representing the total amount of processing time for passengers
	 */
	private int totalProcessTime;

	/**
	 * Constructor method used to create a log
	 */
	public Log() {
		// Empty log constructor
	}

	/**
	 * Method used to return the number of processed passengers
	 * 
	 * @return numCompleted number of completed passengers
	 */
	public int getNumCompleted() {
		return numCompleted;
	}

	/**
	 * Method used to log the wait/process time for each passenger As well as
	 * logging the total number of passengers
	 * 
	 * @param p
	 *            passenger object passed to the log
	 */
	public void logItem(Passenger p) {
		totalWaitTime += p.getWaitTime();
		totalProcessTime += p.getProcessTime();
		numCompleted++;
	}

	/**
	 * Method used to compute the average wait time
	 * 
	 * @return average double representing the average wait time or 0 if the
	 *         number completed is too small
	 */
	public double averageWaitTime() {
		double average;
		double calc;
		if ((numCompleted > 0)) {
			calc  = (double) totalWaitTime / (double) numCompleted;
			average = calc / (double) 60;
		} else
			average = 0.0;
		return average;
	}

	/**
	 * Method used to compute the average wait time
	 * 
	 * @return average double representing the average process time or 0 if the
	 *         number completed is too small
	 */
	public double averageProcessTime() {
		double average;
		if ((numCompleted > 0))
			average = (((double) totalProcessTime / 60) / (double) numCompleted);
		else
			average = 0.0;
		return average;
	}

}
