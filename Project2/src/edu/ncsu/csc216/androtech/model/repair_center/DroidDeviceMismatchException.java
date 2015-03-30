package edu.ncsu.csc216.androtech.model.repair_center;

/**
 * Exception class that is thrown when a device is attemptting to assign itself
 * to a droid of the incorrect type
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (March 19, 2015)
 */
@SuppressWarnings("serial")
public class DroidDeviceMismatchException extends Exception {

	/**
	 * Generic constructor method for the exception
	 */
	public DroidDeviceMismatchException() {
		super();
	}

	/**
	 * Specific Constructor method for the exception with a string message as a
	 * parameter
	 * 
	 * @param message
	 *            String with the error message
	 */
	public DroidDeviceMismatchException(String message) {
		super(message);
	}

}
