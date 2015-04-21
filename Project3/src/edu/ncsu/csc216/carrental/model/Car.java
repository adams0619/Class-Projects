package edu.ncsu.csc216.carrental.model;

import edu.ncsu.csc216.carrental.model.state.RentalState;

/**
 * Class representing the car in our car rental system program.
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (April 13, 2015)
 */
public class Car {

	/** Variable representing a cars fleet number */
	private String fleetNum;

	/** Variable representing a cars particular make */
	private String make;

	/** Variable representing a cars particular model */
	private String model;

	/** Variable representing a cars color */
	private String color;

	/**
	 * Variable representing a cars current state within our program (i.e.
	 * Rented, Available, etc.)
	 */
	private RentalState state;

	/**
	 * Constructor method used to create a car object with the passed parameters
	 * 
	 * @param fleetNum
	 *            String representing this cars fleet number
	 * @param make
	 *            String representing this cars make
	 * @param model
	 *            String representing this cars model
	 * @param color
	 *            String representing this cars color
	 * @throws InvalidIDException
	 *             if the fleetNumber does not match the corresponding pattern
	 */
	public Car(String fleetNum, String make, String model, String color) throws InvalidIDException {
		// Throw exception if the fleet number is invalid
		String match = "\\b([A-Z]\\d{4})\\b";
		if (fleetNum.matches(match)) {
			this.fleetNum = fleetNum;
			this.make = make;
			this.model = model;
			this.color = color;
		} else {
			throw new InvalidIDException("Invalid car parameters");
		}
	}

	/**
	 * Getter method used to get this cars fleet number
	 * 
	 * @return the fleetNum
	 */
	public String getFleetNum() {
		return fleetNum;
	}

	/**
	 * Getter method used to to get this cars make
	 * 
	 * @return the make
	 */
	public String getMake() {
		return make;
	}

	/**
	 * Getter method used to get this cars model type
	 * 
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Getter method used to get this cars color
	 * 
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * Getter method used to get this cars current state
	 * 
	 * @return the current state
	 */
	public RentalState getState() {
		return state;
	}

	/**
	 * Setter method used to set the state for this car
	 * 
	 * @param newState
	 *            the state that this car will be set to
	 */
	public void setState(RentalState newState) {
		state = newState;
	}

	/**
	 * Method used to return a string representation of our car object with all
	 * pertinent information
	 * 
	 * @return String representation of this Car
	 */
	@Override
	public String toString() {
		return fleetNum + ":  " + make + " " + model + " (" + color + ")";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fleetNum == null) ? 0 : fleetNum.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (fleetNum == null) {
			if (other.fleetNum != null)
				return false;
		} else if (!fleetNum.equals(other.fleetNum))
			return false;
		return true;
	}

}
