/**
 * 
 */
package edu.ncsu.csc216.carrental.model.state;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.carrental.model.management.NuxCarRental;

/**
 * JUnit test cases for Available class
 * 
 * @author Noah Unger (ngunger@ncsu.edu)
 *
 */
public class AvailableTest {
	/**
	 * Available object for testing purposes
	 */
	Available tester = null;
	/**
	 * NuxCarRental for testing purposes
	 */
	private NuxCarRental mgr;

	/**
	 * setUp method occurs before tests
	 * 
	 * @throws java.lang.Exception
	 *             general exception can be thrown if a problem is encountered
	 *             during the construction of objects
	 */
	@Before
	public void setUp() throws Exception {
		tester = new Available();
		mgr = new NuxCarRental();
	}

	/**
	 * Test method for constructor
	 * {@link edu.ncsu.csc216.carrental.model.state.Available#Available()}.
	 */
	@Test
	public void testAvailable() {
		assertTrue(tester != null);
	}

	/**
	 * Test method for renting a car
	 * {@link edu.ncsu.csc216.carrental.model.state.Available#rentCar(edu.ncsu.csc216.carrental.model.state.RentalStateManager)}
	 * .
	 */
	@Test
	public void testRentCar() {
		tester.rentCar(mgr);
		assertTrue(tester != null);
	}

	/**
	 * Test method for returning a car
	 * {@link edu.ncsu.csc216.carrental.model.state.Available#returnCar(edu.ncsu.csc216.carrental.model.state.RentalStateManager)}
	 * .
	 */
	@Test
	public void testReturnCar() {
		try {
			tester.returnCar(mgr);
			fail("Should be excepted");
		} catch (IllegalStateException e) {
			assertFalse(tester == null);
		}
	}

	/**
	 * Test method for reporting a problem
	 * {@link edu.ncsu.csc216.carrental.model.state.Available#reportProblem(edu.ncsu.csc216.carrental.model.state.RentalStateManager)}
	 * .
	 */
	@Test
	public void testReportProblem() {
		try {
			tester.reportProblem(mgr);
			fail("Should be excepted");
		} catch (IllegalStateException e) {
			assertFalse(tester == null);
		}
	}

	/**
	 * Test method for detailing
	 * {@link edu.ncsu.csc216.carrental.model.state.Available#detailDone(edu.ncsu.csc216.carrental.model.state.RentalStateManager)}
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
	 * {@link edu.ncsu.csc216.carrental.model.state.Available#repairDone(edu.ncsu.csc216.carrental.model.state.RentalStateManager)}
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
	 * {@link edu.ncsu.csc216.carrental.model.state.Available#rentalInfo()}.
	 */
	@Test
	public void testRentalInfo() {
		try {
			tester.rentalInfo();
			fail("Should be excepted");
		} catch (IllegalStateException e) {
			assertFalse(tester == null);
		}
	}

}
