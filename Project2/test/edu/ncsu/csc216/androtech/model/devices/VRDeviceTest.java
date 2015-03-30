package edu.ncsu.csc216.androtech.model.devices;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Method used to test the VRDevice class for our program
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (March 25, 2015)
 *
 */
public class VRDeviceTest {

	/** Private COM Devices use for testing */
	private VRDevice one;

	/**
	 * Set up method used to create objects for testing this method
	 * 
	 * @throws java.lang.Exception
	 *             general exception can be thrown if a problem is encountered
	 *             during the construction of objects
	 */
	@Before
	public void setUp() throws Exception {
		one = new VRDevice("78910", "Jane Doe", 3);
	}

	/**
	 * Method used to test the toString method for the VRDevice
	 */
	@Test
	public void testToString() {
		// Assert that the beginning of this Device's ID is the device type V
		assertTrue(one.toString().startsWith("V"));
		// Assert the droid id contains the proper serial number
		assertTrue(one.toString().contains("78910"));
	}

	/**
	 * Method used to test the constructor method for the VRDevice
	 */
	@Test
	public void testComDevice() {
		// Assert that the name for this device is Jane Doe
		assertTrue(one.getName().equalsIgnoreCase("Jane Doe"));
		// Assert this tier to be platinum
		assertTrue(one.getTier() == 3);
	}

}
