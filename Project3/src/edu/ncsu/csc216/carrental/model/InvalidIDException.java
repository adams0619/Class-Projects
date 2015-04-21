package edu.ncsu.csc216.carrental.model;

/**
 * Exception class that is thrown when a device is passed conflicting or
 * incorrect ID parameters
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (April 13, 2015)
 *
 */
@SuppressWarnings("serial")
public class InvalidIDException extends RuntimeException{
	
	/**
	 * Generic constructor method for the exception
	 */
	public InvalidIDException() {
		super();
	}
	
	/**
	 * Specific Constructor method for the exception with a string message as a parameter
	 * 
	 * @param message String with the error message
	 */
	public InvalidIDException(String message) {
		super(message);
	}
	
}
	