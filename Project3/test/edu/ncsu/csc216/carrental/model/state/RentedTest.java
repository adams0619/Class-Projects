/**
 * 
 */
package edu.ncsu.csc216.carrental.model.state;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.carrental.model.Customer;
import edu.ncsu.csc216.carrental.model.management.NuxCarRental;

/**
 * JUnit tests for Rented
 * 
 * @author Noah Unger (ngunger@ncsu.edu)
 *
 */
public class RentedTest {
	/**
	 * Available object for testing purposes
	 */
	private Rented tester = null;
	/**
	 * NuxCarRental for testing purposes
	 */
	private NuxCarRental mgr;

	/**
	 * setUp method before tests
	 * 
	 * @throws java.lang.Exception
	 *             general exception can be thrown if a problem is encountered
	 *             during the construction of objects
	 */
	@Before
	public void setUp() throws Exception {
		tester = new Rented(new Customer("Testing", "Customer", "12-3456"));
		mgr = new NuxCarRental();
	}

	/**
	 * Test method for the construcor
	 * {@link edu.ncsu.csc216.carrental.model.state.Rented#Rented(edu.ncsu.csc216.carrental.model.Customer)}
	 * .
	 */
	@Test
	public void testRented() {
		tester = new Rented(new Customer("Testing", "Customer", "12-3456"));
		assertTrue(tester != null);
	}

	/**
	 * Test method for renting a car
	 * {@link edu.ncsu.csc216.carrental.model.state.Rented#rentCar(edu.ncsu.csc216.carrental.model.state.RentalStateManager)}
	 * .
	 */
	@Test
	public void testRentCar() {
		try {
			tester.rentCar(mgr);
			fail("Should be excepted");
		} catch (IllegalStateException e) {
			assertFalse(tester == null);
		}
	}

	/**
	 * Test method for returning a car
	 * {@link edu.ncsu.csc216.carrental.model.state.Rented#returnCar(edu.ncsu.csc216.carrental.model.state.RentalStateManager)}
	 * .
	 */
	@Test
	public void testReturnCar() {
		tester.returnCar(mgr);
		assertTrue(tester != null);
	}

	/**
	 * Test method for reporting a problem
	 * {@link edu.ncsu.csc216.carrental.model.state.Rented#reportProblem(edu.ncsu.csc216.carrental.model.state.RentalStateManager)}
	 * .
	 */
	@Test
	public void testReportProblem() {
		tester.reportProblem(mgr);
		assertTrue(tester != null);
	}

	/**
	 * Test method for detailing
	 * {@link edu.ncsu.csc216.carrental.model.state.Rented#detailDone(edu.ncsu.csc216.carrental.model.state.RentalStateManager)}
	 * .
	 */
	@Test
	public void testDetailDone() {
		try {
			tester.detailDone(mgr);
			fail("Should be excepted");
		} catch (IllegalStateException e) {
			assertFalse(tester == null);
		}
	}

	/**
	 * Test method for completing repairs
	 * {@link edu.ncsu.csc216.carrental.model.state.Rented#repairDone(edu.ncsu.csc216.carrental.model.state.RentalStateManager)}
	 * .
	 */
	@Test
	public void testRepairDone() {
		try {
			tester.repairDone(mgr);
			fail("Should be excepted");
		} catch (IllegalStateException e) {
			assertFalse(tester == null);
		}
	}

	/**
	 * Test method for returning state info
	 * {@link edu.ncsu.csc216.carrental.model.state.Rented#rentalInfo()}.
	 */
	@Test
	public void testRentalInfo() {
		assertEquals("(" + "Testing".substring(0, 1) + " " + "Customer" + ")",
				tester.rentalInfo());
	}

	/**
	 * Test method for getting the customer
	 * {@link edu.ncsu.csc216.carrental.model.state.Rented#getCustomer()}.
	 */
	@Test
	public void testGetCustomer() {
		assertEquals(tester.getCustomer(), new Customer("Testing", "Customer",
				"12-3456"));
	}

}
