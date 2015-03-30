package edu.ncsu.csc216.androtech.model.repair_center;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.androtech.model.devices.VRDevice;

/**
 * Method used to test the TechDroid class for our program through various
 * strategies
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (March 25, 2015)
 *
 */
public class TechDroidTest { 

	/**
	 * Private reference to a repair center
	 */
	private RepairCenter center;

	/**
	 * Private reference to a VR device
	 */
	private VRDevice two;
	
	/**
	 * Private reference to a VR device
	 */
	private VRDevice three;

	/**
	 * Set up method used to create objects for testing this method
	 * 
	 * @throws java.lang.Exception
	 *             general exception can be thrown if a problem is encountered
	 *             during the construction of objects
	 */
	@Before
	public void setUp() throws Exception {
		// Create entirely new repair center
		center = new RepairCenter();
		// Create your one of your devices
		two = new VRDevice("69!1!8", "Justin Franks", 3);
		three = new VRDevice("195864", "Darren Russel", 2);
	}

	/**
	 * Test method for starting the droid numbering at 1 rather then 0
	 * {@link edu.ncsu.csc216.androtech.model.repair_center.TechDroid#startDroidNumberingAt01()}
	 * .
	 */
	@Test
	public void testStartDroidNumberingAt01() {
		// Assert that none of the intial droids have a droid ID that starts
		// with 00
		assertEquals("05V: UNASSIGNED" + "\n" + "03V: UNASSIGNED" + "\n"
				+ "02E: UNASSIGNED" + "\n" + "01C: UNASSIGNED" + "\n"
				+ "04C: UNASSIGNED" + "\n", center.printDroids());
	}

	/**
	 * Test method for the constructor of a tech droid, this method is
	 * responsible for creating the 3 different types of tech droids (E, V, C)
	 * 
	 * {@link edu.ncsu.csc216.androtech.model.repair_center.TechDroid#TechDroid(java.lang.String)}
	 * .
	 */
	@Test
	public void testTechDroid() {
		// Assert that ComDroid constructor passes the proper suffice to be a
		// new ComDroid
		TechDroid a = new ComDroid();
		assertTrue(a instanceof TechDroid);
		assertTrue(a instanceof ComDroid);

		// Assert that VRDroid constructor passes the proper suffice to be a new
		// VRDroid
		TechDroid b = new VRDroid();
		assertTrue(b instanceof TechDroid);
		assertTrue(b instanceof VRDroid);

		// Assert that ExpertDroid constructor passes the proper suffice to be a
		// new ExpertDroid
		TechDroid c = new ExpertDroid();
		assertTrue(c instanceof TechDroid);
		assertTrue(c instanceof ExpertDroid);
	}

	/**
	 * Test method for getting the full droid id for a particular droid which is
	 * a TechDroid
	 * 
	 * {@link edu.ncsu.csc216.androtech.model.repair_center.TechDroid#getDroidID()}
	 * .
	 */
	@Test
	public void testGetDroidID() {
		// Assert that none of the initial droids have a droid ID that starts
		assertEquals("05V: UNASSIGNED" + "\n" + "03V: UNASSIGNED" + "\n"
				+ "02E: UNASSIGNED" + "\n" + "01C: UNASSIGNED" + "\n"
				+ "04C: UNASSIGNED" + "\n", center.printDroids());

		// Create ComDroid and attempt to assign an invalid device to it
		TechDroid a = new ComDroid();
		try {
			a.assign(two);
			fail(); // never run this line
		} catch (DroidBusyException e) {
			assertEquals("06C", a.getDroidID());
		} catch (DroidDeviceMismatchException e) {
			assertEquals("06C", a.getDroidID());
		}

	}

	/**
	 * Test method for determining if the droid is currently assigned a device
	 * for repair
	 * 
	 * {@link edu.ncsu.csc216.androtech.model.repair_center.TechDroid#isAssigned()}
	 * .
	 */
	@Test
	public void testIsAssigned() {
		// Create VRDroid and assign it a VR Device
		TechDroid a = new VRDroid();
		try {
			a.assign(two);
			assertTrue(a.isAssigned());
		} catch (DroidBusyException e) {
			assertFalse(a.isAssigned());
		} catch (DroidDeviceMismatchException e) {
			assertFalse(a.isAssigned());
		}
	}

	/**
	 * Test method for testing release method in the TechDroid class which is
	 * responsible for releasing a device that has been repaired by a droid
	 * 
	 * {@link edu.ncsu.csc216.androtech.model.repair_center.TechDroid#release()}
	 * .
	 */
	@Test
	public void testRelease() {
		// Create VRDroid and assign it a VR Device
		TechDroid a = new VRDroid();
		try {
			a.assign(two);
			assertTrue(a.isAssigned());
		} catch (DroidBusyException e) {
			assertFalse(a.isAssigned());
		} catch (DroidDeviceMismatchException e) {
			assertFalse(a.isAssigned());
		}
		// Assert this droid has a assigned ID
		assertEquals("06V: 69!1!8 Justin Franks", a.toString());
		// Release device from droid
		a.release();
		assertEquals("06V: UNASSIGNED", a.toString());
	}

	/**
	 * Test method for testing the assign method which is responsible for
	 * assigning the next device to an appropriate service droid
	 * 
	 * {@link edu.ncsu.csc216.androtech.model.repair_center.TechDroid#assign(edu.ncsu.csc216.androtech.model.devices.Device)}
	 * .
	 */
	@Test
	public void testAssign() {
		// Create VRDroid and assign it a VR Device
		TechDroid a = new VRDroid();
		try {
			a.assign(two);
			assertTrue(a.isAssigned());
		} catch (DroidBusyException e) {
			assertFalse(a.isAssigned());
		} catch (DroidDeviceMismatchException e) {
			assertFalse(a.isAssigned());
		}

		try {
			a.assign(three);
			assertTrue(a.isAssigned());
		} catch (DroidBusyException e1) {
			assertTrue(a.isAssigned());
		} catch (DroidDeviceMismatchException e1) {
			assertFalse(a.isAssigned());
		}
		
		// Create ComDroid and attempt to assign an invalid device to it
		TechDroid b = new ComDroid();
		try {
			b.assign(two);
			assertTrue(b.isAssigned());
		} catch (DroidBusyException e) {
			assertFalse(b.isAssigned());
		} catch (DroidDeviceMismatchException e) {
			assertFalse(b.isAssigned());
		}
	}

	/**
	 * Test method for testing the toString method of the tech droid class which
	 * is responsible for giving you a string representation of the droids id
	 * and current assignment status
	 * 
	 * {@link edu.ncsu.csc216.androtech.model.repair_center.TechDroid#toString()}
	 * .
	 */
	@Test
	public void testToString() {

		//Assert this is the id for the 1st initial droid
		assertEquals("05V: UNASSIGNED", center.getDroidAt(0).toString());
		
		//Assert this is the id for the 5th initial droid
		assertEquals("04C: UNASSIGNED", center.getDroidAt(4).toString());
		
	}

}
