package edu.ncsu.csc216.carrental.model;

/**
 * Class representing the customer in our car rental system program.
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (April 13, 2015)
 */
public class Customer {

	/** Variable representing a customers first number */
	private String firstName;

	/** Variable representing a customers last number */
	private String lastName;

	/** Variable representing a customers identification number */
	private String id;

	/**
	 * Constructor method used to create a customer object with the passed
	 * parameters
	 * 
	 * @param firstName
	 *            String representing this customers first name
	 * @param lastName
	 *            String representing this customers last name
	 * @param id
	 *            String representing this customers identification number
	 * @throws InvalidIDException
	 *             if identification number is invalid
	 * @throws IllegalArgumentException
	 *             if name parameters for this customer are invalid
	 */
	public Customer(String firstName, String lastName, String id) {
		// Throw exception if the fleet number is invalid
		String idMatch = "\\b\\d{2}-\\d{4}";
		String nameMatch = "^([A-Za-z'-])+\\b";
		if (!id.matches(idMatch)) {
			throw new InvalidIDException("Invalid customer ID");
		}
		if (!firstName.matches(nameMatch) || !lastName.matches(nameMatch)) {
			throw new IllegalArgumentException("Invalid name");
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
	}

	/**
	 * Getter method used to to get this customer first name
	 * 
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Getter method used to to get this customer last name
	 * 
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Getter method used to to get this customer identification number
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Method used to return a string representation of our customer object with
	 * all pertinent information
	 * 
	 * @return String representation of the customer
	 */
	@Override
	public String toString() {
		return "" + id + ":  " + firstName + " " + lastName;
	}

	/**
	 * Method used to create a unique hashCode for this object
	 * 
	 * @return integer representing this objects hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result - prime;
	}

	/**
	 * Equals method used to test if the object that is passed equals the object
	 * it is being compared to by comparing the id's of each object
	 * 
	 * @param obj
	 *            Object to be compared
	 * @return true if objects are equal, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return this.getId().equals(other.getId());
	}

}
