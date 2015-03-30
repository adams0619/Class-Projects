package edu.ncsu.csc216.androtech.model.repair_center;

/**
 * Exception class that is thrown when a device is currently servicing a droid,
 * but another device has attempted to assign to it
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (March 19, 2015)
 */
@SuppressWarnings("serial")
public class DroidBusyException extends Exception {

	/**
	 * Generic constructor method for the exception
	 */
	public DroidBusyException() {
		super();
	}

	/**
	 * Specific Constructor method for the exception with a string message as a parameter
	 * 
	 * @param message String with the error message
	 */
	public DroidBusyException(String message) {
		super(message);
	}

}
