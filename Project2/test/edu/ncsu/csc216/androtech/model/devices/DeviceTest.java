package edu.ncsu.csc216.androtech.model.devices;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
/**
 * Method used to test the DeviceList class for our program through various
 * testing strategies
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (March 25, 2015)
 *
 */
public class DeviceTest {
	/** Private COM Devices use for testing */
	ComDevice one;

	/** Private COM Devices use for testing */
	VRDevice two;

	/**
	 * Set up method used to help create objects for testing each method in
	 * device class
	 * 
	 * @throws java.lang.Exception
	 *             general exception can be thrown if a problem is encountered
	 *             during the construction of objects
	 */
	@Before
	public void setUp() throws Exception {
		one = new ComDevice("123456", "John Doe", 1);
		two = new VRDevice("78910", "Jane Doe", 3);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.androtech.model.devices.Device#Device(java.lang.String, java.lang.String, int)}
	 * .
	 */
	@Test
	public void testDevice() {
		// Assert that the name for this device is Jane Doe
		assertTrue(two.getName().equalsIgnoreCase("Jane Doe"));
		// Assert this tier to be platinum
		assertEquals(3, two.getTier());

		// Assert that the name for this device is John Doe
		assertTrue(one.getName().equalsIgnoreCase("John Doe"));
		// Assert this tier to be Silver
		assertEquals(1, one.getTier());
	}

	/**
	 * Test method for meetFilter method in the program.
	 * {@link edu.ncsu.csc216.androtech.model.devices.Device#meetsFilter(java.lang.String)}
	 * .
	 */
	@Test
	public void testMeetsFilter() {
		// Test both devices meet a generic filter
		assertTrue(two.meetsFilter("J"));
		assertTrue(one.meetsFilter("J"));

		// assert only one device meets specifc filter
		assertFalse(one.meetsFilter("Ja"));
		assertTrue(two.meetsFilter("Ja"));
	}

	/**
	 * Test method for getting a devices serial number
	 * {@link edu.ncsu.csc216.androtech.model.devices.Device#getSerialNum()}.
	 */
	@Test
	public void testGetSerialNum() {
		// Assert this serial number to be 123456
		assertEquals(123456, Integer.parseInt(one.getSerialNum()));

		// Assert this serial number to be 78910
		assertEquals(78910, Integer.parseInt(two.getSerialNum()));
	}

	/**
	 * Test method for get name for the method
	 * {@link edu.ncsu.csc216.androtech.model.devices.Device#getName()}.
	 */
	@Test
	public void testGetName() {
		// Assert that the name for this device is Jane Doe
		assertTrue(two.getName().equalsIgnoreCase("Jane Doe"));

		// Assert that the name for this device is John Doe
		assertTrue(one.getName().equalsIgnoreCase("John Doe"));
	}

	/**
	 * Test method for getting the tier for each device;r
	 * {@link edu.ncsu.csc216.androtech.model.devices.Device#getTier()}.
	 */
	@Test
	public void testGetTier() {
		// Assert this tier to be platinum
		assertEquals(3, two.getTier());

		// Assert this tier to be Silver
		assertEquals(1, one.getTier());
	}

	/**
	 * Test method for determine which kind of droid is which.
	 * {@link edu.ncsu.csc216.androtech.model.devices.Device#toString()}.
	 */
	@Test
	public void testToString() {
		String a = one.toString();
		String b = two.toString();

		// Assert this is a Com droid
		assertEquals(true, a.startsWith("C"));
		// Assert this is a VR droid
		assertEquals(true, b.startsWith("V"));

	}

	/**
	 * Test method for comparing the tier of two devices
	 * {@link edu.ncsu.csc216.androtech.model.devices.Device#compareToTier(edu.ncsu.csc216.androtech.model.devices.Prioritizable)}
	 * .
	 */
	@Test
	public void testCompareToTier() {
		// Tier for device 1 is greater then device 2
		assertEquals(-1, one.compareToTier(two));
		// Tier for device 1 is greater then device 2
		assertEquals(1, two.compareToTier(one));
	}

}
