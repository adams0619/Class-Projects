/**
 * 
 */
package edu.ncsu.csc216.carrental.model.state;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.carrental.model.management.NuxCarRental;

/**
 * JUnit test cases for OutForRepair
 * 
 * @author Noah Unger (ngunger@ncsu.edu)
 *
 */
public class OutForRepairTest {
	/**
	 * Available object for testing purposes
	 */
	OutForRepair tester = null;
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
		tester = new OutForRepair();
		mgr = new NuxCarRental();
	}

	/**
	 * Test method for the constructor
	 * {@link edu.ncsu.csc216.carrental.model.state.OutForRepair#OutForRepair()}
	 * .
	 */
	@Test
	public void testOutForRepair() {
		assertTrue(tester != null);
	}

	/**
	 * Test method for renting a car
	 * {@link edu.ncsu.csc216.carrental.model.state.OutForRepair#rentCar(edu.ncsu.csc216.carrental.model.state.RentalStateManager)}
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
	 * {@link edu.ncsu.csc216.carrental.model.state.OutForRepair#returnCar(edu.ncsu.csc216.carrental.model.state.RentalStateManager)}
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
	 * {@link edu.ncsu.csc216.carrental.model.state.OutForRepair#reportProblem(edu.ncsu.csc216.carrental.model.state.RentalStateManager)}
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
	 * {@link edu.ncsu.csc216.carrental.model.state.OutForRepair#detailDone(edu.ncsu.csc216.carrental.model.state.RentalStateManager)}
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
	 * {@link edu.ncsu.csc216.carrental.model.state.OutForRepair#repairDone(edu.ncsu.csc216.carrental.model.state.RentalStateManager)}
	 * .
	 */
	@Test
	public void testRepairDone() {
		tester.repairDone(mgr);
		assertTrue(tester != null);
	}

	/**
	 * Test method for returning state info
	 * {@link edu.ncsu.csc216.carrental.model.state.OutForRepair#rentalInfo()}.
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
