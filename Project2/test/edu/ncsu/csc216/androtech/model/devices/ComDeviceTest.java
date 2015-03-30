package edu.ncsu.csc216.androtech.model.devices;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Method used to test the ComDevice class for our program
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (March 25, 2015)
 *
 */
public class ComDeviceTest {

	/** Private COM Devices use for testing */
	private ComDevice one;

	/**
	 * Set up method used to create COM device used for testing the COM device
	 * class
	 * 
	 * @throws Exception
	 *             general exception can be thrown if a problem is encountered
	 *             during the construction of objects
	 */
	@Before
	public void setUp() throws Exception {
		// Create a single COM device used
		one = new ComDevice("123456", "John Doe", 1);
	}

	/**
	 * Method used to test the toString method for the ComDevice
	 */
	@Test
	public void testToString() {
		// Assert that the beginning of this Device's ID is the device type C
		assertTrue(one.toString().startsWith("C"));
	}

	/**
	 * Method used to test the constructor method for the ComDevice
	 */
	@Test
	public void testComDevice() {
		// Assert that the name for this device is John Doe
		assertTrue(one.getName().equalsIgnoreCase("John Doe"));

		// Assert that the tier for this device is Silver
		assertTrue(one.getTier() == 1);
	}

}
