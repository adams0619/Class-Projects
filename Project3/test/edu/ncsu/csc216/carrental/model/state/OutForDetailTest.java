/**
 * 
 */
package edu.ncsu.csc216.carrental.model.state;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.carrental.model.management.NuxCarRental;

/**
 * JUnit test cases for OutForDetail
 * 
 * @author Noah Unger (ngunger@ncsu.edu)
 *
 */
public class OutForDetailTest {
	/**
	 * Available object for testing purposes
	 */
	OutForDetail tester = null;
	/**
	 * NuxCarRental for testing purposes
	 */
	private NuxCarRental mgr;

	/**
	 * setUP method before tests
	 * 
	 * @throws java.lang.Exception
	 *             general exception can be thrown if a problem is encountered
	 *             during the construction of objects
	 */
	@Before
	public void setUp() throws Exception {
		tester = new OutForDetail();
		mgr = new NuxCarRental();
	}

	/**
	 * Test method for constructor
	 * {@link edu.ncsu.csc216.carrental.model.state.OutForDetail#OutForDetail()}
	 * .
	 */
	@Test
	public void testOutForDetail() {
		assertTrue(tester != null);
	}

	/**
	 * Test method for renting a car
	 * {@link edu.ncsu.csc216.carrental.model.state.OutForDetail#rentCar(edu.ncsu.csc216.carrental.model.state.RentalStateManager)}
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
	 * {@link edu.ncsu.csc216.carrental.model.state.OutForDetail#returnCar(edu.ncsu.csc216.carrental.model.state.RentalStateManager)}
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
	 * {@link edu.ncsu.csc216.carrental.model.state.OutForDetail#reportProblem(edu.ncsu.csc216.carrental.model.state.RentalStateManager)}
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
	 * {@link edu.ncsu.csc216.carrental.model.state.OutForDetail#detailDone(edu.ncsu.csc216.carrental.model.state.RentalStateManager)}
	 * .
	 */
	@Test
	public void testDetailDone() {
		tester.detailDone(mgr);
		assertTrue(tester != null);
	}

	/**
	 * Test method for completing repairs
	 * {@link edu.ncsu.csc216.carrental.model.state.OutForDetail#repairDone(edu.ncsu.csc216.carrental.model.state.RentalStateManager)}
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
	 * {@link edu.ncsu.csc216.carrental.model.state.OutForDetail#rentalInfo()}.
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
