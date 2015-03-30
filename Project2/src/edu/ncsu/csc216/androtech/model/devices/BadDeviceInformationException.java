package edu.ncsu.csc216.androtech.model.devices;

/**
 * Exception class that is thrown when a device is passed conflicting or
 * incorrect parameters
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (March 19, 2015)
 *
 */
@SuppressWarnings("serial")
public class BadDeviceInformationException extends Exception {

	/**
	 * Generic constructor method for the exception
	 */
	public BadDeviceInformationException() {
		super();
	}
	
	/**
	 * Specific Constructor method for the exception with a string message as a parameter
	 * 
	 * @param message String with the error message
	 */
	public BadDeviceInformationException(String message) {
		super(message);
	}
	
}
