/**
 * 
 */
package edu.ncsu.csc216.carrental.model.management;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.carrental.model.Car;
import edu.ncsu.csc216.carrental.model.Customer;
import edu.ncsu.csc216.carrental.model.InvalidIDException;

/**
 * JUnit test cases for Customer
 * 
 * @author Noah Unger (ngunger@ncsu.edu)
 *
 */
public class CustomerTest {
	Customer testCustomer;

	/**
	 * setUp method before tests
	 * 
	 * @throws java.lang.Exception
	 *             general exception can be thrown if a problem is encountered
	 *             during the construction of objects
	 */
	@Before
	public void setUp() throws Exception {
		testCustomer = new Customer("Test", "Customer", "12-3456");
	}

	/**
	 * Test method for testing hash code
	 * {@link edu.ncsu.csc216.carrental.model.Customer#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		assertTrue(testCustomer.hashCode() > 0);
	}

	/**
	 * Test method for testing customer constructor
	 * {@link edu.ncsu.csc216.carrental.model.Customer#Customer(java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testCustomer() {
		// Test generated customer by comparing first name
		// Expected is "Test"
		// Actual is call to getFirstName()
		assertEquals("Test", testCustomer.getFirstName());

		// Test a failed customer generated due to ID
		// Expected is a caught InvalidIDException
		// Actual should be a null object after failed initialization
		testCustomer = null;
		try {
			testCustomer = new Customer("Test", "Customer", "AB4j");
			fail("Should be excepted");
		} catch (InvalidIDException e) {
			assertTrue(testCustomer == null);
		}

		// Test a failed customer generated due to improper naming
		// Expected is a caught IllegalArgumentException
		// Actual should be a null object after failed initialization
		testCustomer = null;
		try {
			testCustomer = new Customer("Test123", "Customer", "12-3456");
			fail("Should be excepted");
		} catch (IllegalArgumentException e) {
			assertTrue(testCustomer == null);
		}
	}

	/**
	 * Test method for getting the first name
	 * {@link edu.ncsu.csc216.carrental.model.Customer#getFirstName()}.
	 */
	@Test
	public void testGetFirstName() {
		// Test a customer's first name
		// Expected is "Test"
		// Actual is call to getFirstName
		assertEquals("Test", testCustomer.getFirstName());
	}

	/**
	 * Test method for getting the last name
	 * {@link edu.ncsu.csc216.carrental.model.Customer#getLastName()}.
	 */
	@Test
	public void testGetLastName() {
		// Test a customer's last name
		// Expected is "Customer"
		// Actual is call to getFirstName
		assertEquals("Customer", testCustomer.getLastName());
	}

	/**
	 * Test method for getting the id
	 * {@link edu.ncsu.csc216.carrental.model.Customer#getId()}.
	 */
	@Test
	public void testGetId() {
		// Test a customer's ID
		// Expected is "12-3456"
		// Actual is call to getFirstName
		assertEquals("12-3456", testCustomer.getId());
	}

	/**
	 * Test method for printing this custmer to a string
	 * {@link edu.ncsu.csc216.carrental.model.Customer#toString()}.
	 */
	@Test
	public void testToString() {
		// Test a customer's string representation
		// Expected is "12-3456: Test Customer"
		// Actual is call to toString
		assertFalse(testCustomer.toString().equals("Test Customer 12-3456"));
	}

	/**
	 * Test method for comparing objects
	 * {@link edu.ncsu.csc216.carrental.model.Customer#equals(java.lang.Object)}
	 * .
	 */
	@Test
	public void testEqualsObject() {
		Customer failerCustomer = null;
		assertFalse(testCustomer.equals(failerCustomer));

		failerCustomer = new Customer("Failer", "Customer", "23-4567");
		assertFalse(testCustomer.equals(failerCustomer));

		failerCustomer = testCustomer;
		assertTrue(failerCustomer.equals(testCustomer));

		Car failerCar = new Car("B2345", "Chevy", "Make", "Color");
		assertFalse(testCustomer.equals(failerCar));

	}

}
