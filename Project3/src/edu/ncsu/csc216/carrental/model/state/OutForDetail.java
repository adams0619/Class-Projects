package edu.ncsu.csc216.carrental.model.state;

/**
 * Class representing the detail state for a car
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (April 16, 2015)
 */
public class OutForDetail implements RentalState {

	/**
	 * Empty constructor for the detailing state class of a car
	 */
	public OutForDetail() {
		// Empty Constructor
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
	public void rentCar(RentalStateManager mgr) throws IllegalStateException {
		// Car cannot be rented if its currently out for detail
		throw new IllegalStateException();
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
	public void returnCar(RentalStateManager mgr) throws IllegalStateException {
		// Car cannot be returned if its currently out for detail
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
	public void reportProblem(RentalStateManager mgr)
			throws IllegalStateException {
		// Car cannot be returned with problem if its currently out for detail
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
	public void detailDone(RentalStateManager mgr) throws IllegalStateException {
		// Finish detailing this car
		mgr.processDetailed();
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
	public void repairDone(RentalStateManager mgr) throws IllegalStateException {
		// Car cannot be repaired if its currently out for detail
		throw new IllegalStateException();
	}

	/**
	 * Method used to return rental information about the cars rental
	 * 
	 * @throws IllegalStateException
	 *             if this method cannot be called in this current state
	 */
	@Override
	public String rentalInfo() throws IllegalStateException {
		throw new IllegalStateException();
	}
}
