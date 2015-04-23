package edu.ncsu.csc216.carrental.model.state;

/**
 * Class representing the available state for a car
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (April 16, 2015)
 */
public class Available implements RentalState {

	/**
	 * Empty constructor for the Available state class of a car
	 */
	public Available() {
		// Empty constructor
	}

	/**
	 * Method used to rent a car to a customer if it is currently in the
	 * available state.
	 * 
	 * @param mgr
	 *            Reference to the car rental manager which is used to process
	 *            the car through different states
	 * @throws IllegalStateException
	 *             if this method cannot be called in this current state
	 */
	@Override
	public void rentCar(RentalStateManager mgr) {
		// Use manager to rent a car
		if (mgr != null) {
			mgr.processRental();
		} else {
			throw new IllegalStateException();
		}
	}

	/**
	 * Method used to return a car with no problems once a rental is complete
	 * 
	 * @param mgr
	 *            Reference to the car rental manager which is used to process
	 *            the car through different states
	 * @throws IllegalStateException
	 *             if this method cannot be called in this current state
	 */
	@Override
	public void returnCar(RentalStateManager mgr) {
		// Car cannot be returned if its currently available
		throw new IllegalStateException();
	}

	/**
	 * Method used to return a car with a problem once a rental is complete
	 * 
	 * @param mgr
	 *            Reference to the car rental manager which is used to process
	 *            the car through different states
	 * @throws IllegalStateException
	 *             if this method cannot be called in this current state
	 */
	@Override
	public void reportProblem(RentalStateManager mgr) {
		// Car cannot be returned with problem if its currently available
		throw new IllegalStateException();
	}

	/**
	 * Method used to send a car to the detail shop after the rental is complete
	 * or after it has been repaired
	 * 
	 * @param mgr
	 *            Reference to the car rental manager which is used to process
	 *            the car through different states
	 * @throws IllegalStateException
	 *             if this method cannot be called in this current state
	 */
	@Override
	public void detailDone(RentalStateManager mgr) {
		// Car cannot be detailed if its currently available
		throw new IllegalStateException();
	}

	/**
	 * Method used to repair a car if a problem was reported when the rental was
	 * completed
	 * 
	 * @param mgr
	 *            Reference to the car rental manager which is used to process
	 *            the car through different states
	 * @throws IllegalStateException
	 *             if this method cannot be called in this current state
	 */
	@Override
	public void repairDone(RentalStateManager mgr) {
		// Car cannot be repaired if its currently available
		throw new IllegalStateException();

	}

	/**
	 * Method used to return rental information about the cars rental
	 * 
	 * @throws IllegalStateException
	 *             if this method cannot be called in this current state
	 * @return String representing the info about the rented car
	 */
	@Override
	public String rentalInfo() {
		throw new IllegalStateException();
	}

}
