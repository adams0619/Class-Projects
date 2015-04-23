package edu.ncsu.csc216.carrental.model.state;

import edu.ncsu.csc216.carrental.model.Customer;

/**
 * Class representing the rental state of a car in our car rental program
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (April 14, 2015)
 */
public class Rented implements RentalState {

	/** Private reference to a customer */
	private Customer customer;

	/**
	 * Constructor method used for this state class
	 * 
	 * @param c
	 *            the car customer to be passed/referenced by this state class
	 */
	public Rented(Customer c) {
		this.customer = c;
	}

	/**
	 * Method used to rent a car to a customer if it is currently in the
	 * available state.
	 * 
	 * @throws IllegalStateException
	 *             if this method cannot be called in this current state
	 * @param mgr RentalStateManager for change             
	 */
	@Override
	public void rentCar(RentalStateManager mgr) {
		// Car cannot be rented if its currently rented
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
	public void returnCar(RentalStateManager mgr) {
		// Return this car with a problem
		if (mgr != null)
			mgr.processReturn(false);
		else
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
		// Return this car with a problem
		mgr.processReturn(true);
	}

	/**
	 * Method used to send a car to the detail shop after the rental is complete
	 * or after it has been repaired
	 * 
	 * 
	 * @param mgr
	 *            Reference to the car rental manager which is used to process
	 *            the car through different states
	 * @throws IllegalStateException
	 *             if this method cannot be called in this current state
	 */
	@Override
	public void detailDone(RentalStateManager mgr) {
		// Car cannot be detailed if its currently rented
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
		// Car cannot be repaired if its currently rented
		throw new IllegalStateException();
	}

	/**
	 * Method used to return rental information about the cars rental
	 * 
	 * @throws IllegalStateException
	 *             if this method cannot be called in this current state
	 * @return String of rental info            
	 */
	@Override
	public String rentalInfo() {
		return "(" + customer.getFirstName().substring(0, 1) + " "
			   + customer.getLastName() + ")";
	}

	/**
	 * Return the customer that is renting this car
	 * 
	 * @return the customer renting the current car
	 */
	public Customer getCustomer() {
		return customer;
	}

}