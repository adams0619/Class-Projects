package edu.ncsu.csc216.androtech.model.repair_center;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.androtech.model.devices.ComDevice;
import edu.ncsu.csc216.androtech.model.devices.Device;

/**
 * Method used to test the RepairCenter class for our program through various
 * strategies
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (March 25, 2015)
 *
 */
public class RepairCenterTest {

	/**
	 * Private reference to a repair center
	 */
	private RepairCenter center;

	/**
	 * Private reference to a COM device
	 */
	private ComDevice one;

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
		one = new ComDevice("529%36", "Aaron Bradely", 3);
	}

	/**
	 * Test method for repair center constructor, which is responsible for
	 * creating repair center (ArrayList) that is used for housing the various
	 * tech droids
	 * 
	 * {@link edu.ncsu.csc216.androtech.model.repair_center.RepairCenter#RepairCenter()}
	 * .
	 */
	@Test
	public void testRepairCenter() {
		// Assert that a repair center starts with 5 order droids that are all
		// unassigned
		assertEquals("05V: UNASSIGNED" + "\n" + "03V: UNASSIGNED" + "\n"
				+ "02E: UNASSIGNED" + "\n" + "01C: UNASSIGNED" + "\n"
				+ "04C: UNASSIGNED" + "\n", center.printDroids());
	}

	/**
	 * Test method for add tech droid method of the repair center, which is
	 * responsible for adding a tech droid to the repair center array list
	 * 
	 * {@link edu.ncsu.csc216.androtech.model.repair_center.RepairCenter#addTechDroid()}
	 * .
	 */
	@Test
	public void testAddTechDroid() {
		// Add a new tech droid to the 5 initial droids that the repair center
		// starts with
		center.addTechDroid();
		// Assert there are now 6 droids
		assertEquals(6, center.totalNumberOfDroids());
		// Assert that the new droid is Com droid
		assertTrue(center.getDroidAt(5) instanceof ComDroid);
		// Add more droids to the repair center
		center.addTechDroid();
		center.addTechDroid();
		center.addTechDroid();
		// Assert that there are now 9 droids
		assertEquals(9, center.totalNumberOfDroids());
		// Assert that the last added droid was a VRDroid
		assertTrue(center.getDroidAt(8) instanceof ComDroid);
	}

	/**
	 * Test method for number of available droids for the repair center which is
	 * responsible for determining how many droids are available for servicing
	 * different types of devices
	 * 
	 * {@link edu.ncsu.csc216.androtech.model.repair_center.RepairCenter#numberOfAvailableDroids()}
	 * .
	 */
	@Test
	public void testNumberOfAvailableDroids() {
		// Assert there are 5 initial droids
		assertEquals(5, center.numberOfAvailableDroids());
		// Add a new tech droid to the 5 initial droids that the repair center
		// starts with
		center.addTechDroid();
		// Assert there are now 6 droids available for servicing
		assertEquals(6, center.numberOfAvailableDroids());
		// Assert that the new droid is Com droid
		assertTrue(center.getDroidAt(5) instanceof ComDroid);
		// Add more droids to the repair center
		center.addTechDroid();
		center.addTechDroid();
		center.addTechDroid();
		// Assert that there are now 9 droids available for servicing
		assertEquals(9, center.numberOfAvailableDroids());
	}

	/**
	 * Test method for get droid at method for the repair center which is
	 * responsible for returning the droid at the specific index.
	 * 
	 * {@link edu.ncsu.csc216.androtech.model.repair_center.RepairCenter#getDroidAt(int)}
	 * .
	 */
	@Test
	public void testGetDroidAt() {
		// Assert which initial droid is at which position
		assertEquals("05V: UNASSIGNED", center.getDroidAt(0).toString());
		assertEquals("03V: UNASSIGNED", center.getDroidAt(1).toString());
		assertEquals("02E: UNASSIGNED", center.getDroidAt(2).toString());
		assertEquals("01C: UNASSIGNED", center.getDroidAt(3).toString());
		assertEquals("04C: UNASSIGNED", center.getDroidAt(4).toString());

		// Add more droids to the repair center (15)
		for (int i = 0; i <= 5; i++) {
			center.addTechDroid();
			center.addTechDroid();
			center.addTechDroid();
		}

		// Assert the toString for the droid at the specific position
		assertEquals("13C: UNASSIGNED", center.getDroidAt(15).toString());
		
		
	}

	/**
	 * Test method for total number of available droids for the repair center
	 * which is responsible for determining how many droids are available for
	 * servicing different types of devices
	 * {@link edu.ncsu.csc216.androtech.model.repair_center.RepairCenter#totalNumberOfDroids()}
	 * .
	 */
	@Test
	public void testTotalNumberOfDroids() {
		// Assert there are 5 initial droids
		assertEquals(5, center.totalNumberOfDroids());
		// Add a new tech droid to the 5 initial droids that the repair center
		// starts with
		center.addTechDroid();
		// Assert there are now 6 droids
		assertEquals(6, center.totalNumberOfDroids());
		// Assert that the new droid is Com droid
		assertTrue(center.getDroidAt(5) instanceof ComDroid);
		// Add more droids to the repair center
		center.addTechDroid();
		center.addTechDroid();
		center.addTechDroid();
		// Assert that there are now 9 droids
		assertEquals(9, center.totalNumberOfDroids());
	}

	/**
	 * Test method for release method or the repair center which is responsible
	 * for releasing a device from a tech droid after it has been repaired, the
	 * released device is what is returned
	 * 
	 * {@link edu.ncsu.csc216.androtech.model.repair_center.RepairCenter#release(int)}
	 * .
	 */
	@Test
	public void testRelease() {
		// Attempt to assign a device to a improper droid
		try {
			center.getDroidAt(01).assign(one);
			fail();
		} catch (Exception e) {
			assertFalse(center.getDroidAt(01).isAssigned());
		}
		// Assert that no device was assigned to the droid because it's release
		// method returns null
		assertEquals(null, center.getDroidAt(01).release());
		
		//Assign proper device to droid
				
		// Assign false device
		Device d = center.release(1);
		assertEquals(null, d);
	}

	/**
	 * Test method for print droids for the repair center which is responsible
	 * for printing the list of droids available in the repair center
	 * 
	 * {@link edu.ncsu.csc216.androtech.model.repair_center.RepairCenter#printDroids()}
	 * .
	 */
	@Test
	public void testPrintDroids() {
		// Assert the first initial five droids for the repair center
		// Assert that a repair center starts with 5 order droids that are all
		// unassigned
		assertEquals("05V: UNASSIGNED" + "\n" + "03V: UNASSIGNED" + "\n"
				+ "02E: UNASSIGNED" + "\n" + "01C: UNASSIGNED" + "\n"
				+ "04C: UNASSIGNED" + "\n", center.printDroids());
	}

}
