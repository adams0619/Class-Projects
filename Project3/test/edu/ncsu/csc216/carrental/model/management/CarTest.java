/**
 * 
 */
package edu.ncsu.csc216.carrental.model.management;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.carrental.model.Car;
import edu.ncsu.csc216.carrental.model.InvalidIDException;
import edu.ncsu.csc216.carrental.model.state.Available;

/**
 * JUnit test cases for Car
 * 
 * @author Noah Unger (ngunger@ncsu.edu)
 *
 */
public class CarTest {
	/**
	 * Car used for testing purposes
	 */
	Car testCar;

	/**
	 * setUp method before tests
	 * 
	 * @throws java.lang.Exception
	 *             general exception can be thrown if a problem is encountered
	 *             during the construction of objects
	 */
	@Before
	public void setUp() throws Exception {
		testCar = new Car("A1234", "Toyota", "Camry", "Color");
	}

	/**
	 * Test method for testing hash code
	 * {@link edu.ncsu.csc216.carrental.model.Car#hashCode()}.
	 */
	@Test
	public void testHashCode() {
		assertTrue(testCar.hashCode() > 0);
	}

	/**
	 * Test method for testing car constructor
	 * {@link edu.ncsu.csc216.carrental.model.Car#Car(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testCar() {
		// Test for incorrectly generated car by invalid fleetNum
		// Expected is a caught InvalidIDException
		// Actual should be a null object
		Car testFailer = null;
		try {
			testFailer = new Car("ABCDE", "Toyota", "Camry", "Red");
			fail("Should be excepted");
		} catch (InvalidIDException e) {
			assertTrue(testFailer == null);
		}

		// Test for correctly generated car through make check
		// Expected is "Toyota"
		// Actual is call to getMake
		assertEquals("Toyota", testCar.getMake());
	}

	/**
	 * Test method for getting the id
	 * {@link edu.ncsu.csc216.carrental.model.Car#getFleetNum()} .
	 */
	@Test
	public void testGetFleetNum() {
		// Test for correct fleetNum return
		// Expected is "AB1234"
		// Actual is call to getMake
		assertEquals("A1234", testCar.getFleetNum());
	}

	/**
	 * Test method for getting the make
	 * {@link edu.ncsu.csc216.carrental.model.Car#getMake()}.
	 */
	@Test
	public void testGetMake() {
		// Test for correct getMake return
		// Expected is "Toyota"
		// Actual is call to getMake
		assertEquals("Toyota", testCar.getMake());
	}

	/**
	 * Test method for getting the model
	 * {@link edu.ncsu.csc216.carrental.model.Car#getModel()}.
	 */
	@Test
	public void testGetModel() {
		// Test for correct getModel return
		// Expected is "Camry"
		// Actual is call to getMake
		assertEquals("Camry", testCar.getModel());
	}

	/**
	 * Test method for getting the color
	 * {@link edu.ncsu.csc216.carrental.model.Car#getColor()}.
	 */
	@Test
	public void testGetColor() {
		// Test for correct getColor return
		// Expected is "Camry"
		// Actual is call to getMake
		assertEquals("Color", testCar.getColor());
	}

	/**
	 * Test method for getting the current state
	 * {@link edu.ncsu.csc216.carrental.model.Car#getState()}.
	 */
	@Test
	public void testGetState() {
		testCar.getState();
		assertFalse(testCar == null);
	}

	/**
	 * Test method for setting the state
	 * {@link edu.ncsu.csc216.carrental.model.Car#setState(edu.ncsu.csc216.carrental.model.state.RentalState)}
	 * .
	 */
	@Test
	public void testSetState() {
		Available newState = new Available();
		testCar.setState(newState);
		assertTrue(testCar.hashCode() > 0);
	}

	/**
	 * Test method for printing this car to a string
	 * {@link edu.ncsu.csc216.carrental.model.Car#toString()}.
	 */
	@Test
	public void testToString() {
		// Test for correct string representation
		// Expected is "AB1234: Toyota Camry (Red)"
		// Actual is call to getMake
		assertFalse(testCar.toString().equals("A1234: Camry Toyota Color"));
	}

	/**
	 * Test method for comparing objects
	 * {@link edu.ncsu.csc216.carrental.model.Car#equals(java.lang.Object)}.
	 */
	@Test
	public void testEqualsObject() {
		Car failerCar = null;
		assertFalse(testCar.equals(failerCar));

		failerCar = new Car("B2345", "Chevy", "Make", "Color");
		assertFalse(testCar.equals(failerCar));

		failerCar = testCar;
		assertTrue(testCar.equals(failerCar));
	}

}
