package edu.ncsu.csc216.androtech.model.repair_center;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.androtech.model.devices.ComDevice;
import edu.ncsu.csc216.androtech.model.devices.VRDevice;

/**
 * Method used to test the V class for our program through various
 * testing strategies
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (March 25, 2015)
 *
 */
public class VRDroidTest {

	/** Private COM Devices use for testing */
	private VRDroid one;

	/** Private COM Devices use for testing */
	private ComDevice c;
	
	/** Private COM Devices use for testing */
	private VRDevice a, b;

	/**
	 * Set up method used to create objects for testing this method
	 * 
	 * @throws java.lang.Exception
	 *             general exception can be thrown if a problem is encountered
	 *             during the construction of objects
	 */
	@Before
	public void setUp() throws Exception {
		one = new VRDroid();
		a = new VRDevice("12345", "Bob Barch", 2);
		b = new VRDevice("15645", "Barb Bakch", 3);
		c = new ComDevice("78910", "Jane Doe", 3);
	}

	/**
	 * Test method for assigning this droid to a proper deviceTest method for 
	 * {@link edu.ncsu.csc216.androtech.model.repair_center.VRDroid#assign(edu.ncsu.csc216.androtech.model.devices.Device)}.
	 */
	@Test
	public void testAssign() {
		//Assert this is assigned
		try {
			one.assign(a);
			assertTrue(one.isAssigned());
		} catch (DroidBusyException e) {
			assertFalse(one.isAssigned());
		} catch (DroidDeviceMismatchException e) {
			assertFalse(one.isAssigned());
		}
		//Assert this is unassigned b/c droid busy
		try {
			one.assign(b);
			assertTrue(one.isAssigned());
		} catch (DroidBusyException e) {
			assertTrue(one.isAssigned());
		} catch (DroidDeviceMismatchException e) {
			assertFalse(one.isAssigned());
		}
		
		//Release device
		one.release();
		//Assert this is unassigned b/c device mismatch 
		try {
			one.assign(c);
			assertTrue(one.isAssigned());
		} catch (DroidBusyException e) {
			assertFalse(one.isAssigned());
		} catch (DroidDeviceMismatchException e) {
			assertFalse(one.isAssigned());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.androtech.model.repair_center.VRDroid#VRDroid()}.
	 */
	@Test
	public void testVRDroid() {
		VRDroid two = new VRDroid();
		// Assert this is a VR droid
		assertTrue(two instanceof VRDroid);
	}

}
