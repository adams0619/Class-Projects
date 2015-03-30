package edu.ncsu.csc216.androtech.model.management;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.androtech.model.devices.ComDevice;
import edu.ncsu.csc216.androtech.model.devices.Device;
import edu.ncsu.csc216.androtech.model.devices.VRDevice;

/**
 * Method used to test the ServiceManager class for our program through various
 * testing strategies
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (March 25, 2015)
 *
 */
public class ServiceManagerTest {

	/**
	 * Private reference to a repair center used for JUnit testing
	 */
	private ServiceManager manager, secManager;

	/**
	 * Private array of devices
	 */
	private Device[] devices = new Device[5];

	/**
	 * Set up method used to create objects for testing the service manager
	 * class
	 * 
	 * @throws java.lang.Exception
	 *             general exception can be thrown if a problem is encountered
	 *             during the construction of objects
	 */
	@Before
	public void setUp() throws Exception {
		manager = new ServiceManager();

		ComDevice one = new ComDevice("123456", "John Doe", 1);
		VRDevice two = new VRDevice("78910", "Jane Doe", 3);
		ComDevice three = new ComDevice("123456", "Janet Walsh", 3);
		VRDevice four = new VRDevice("M231Q", "Dan Gaben", 2);
		VRDevice five = new VRDevice("QW123W", "Quinn Tucker", 2);

		String s = "V 3  M78!91$0 Jerry Purp";
		Scanner scan = new Scanner(s);
		secManager = new ServiceManager(scan);

		devices[0] = one;
		devices[1] = two;
		devices[2] = three;
		devices[3] = four;
		devices[4] = five;

		manager.putOnWaitingList(devices[0]);
		manager.putOnWaitingList(devices[1]);
		manager.putOnWaitingList(devices[2]);
		manager.putOnWaitingList(devices[3]);
		manager.putOnWaitingList(devices[4]);
	}

	/**
	 * * Test method for the service manager constructor without a parameter
	 * passed
	 * 
	 * {@link edu.ncsu.csc216.androtech.model.management.ServiceManager#ServiceManager()}
	 * .
	 */
	@Test
	public void testServiceManager() {
		// Assert that an empty service manager starts with 5 ordered unassigned
		// droids
		assertEquals("05V: UNASSIGNED" + "\n" + "03V: UNASSIGNED" + "\n"
				+ "02E: UNASSIGNED" + "\n" + "01C: UNASSIGNED" + "\n"
				+ "04C: UNASSIGNED" + "\n", manager.printDroids());
	}

	/**
	 * Test method for the service manager constructor with a scanner passed as
	 * a parameter
	 * 
	 * {@link edu.ncsu.csc216.androtech.model.management.ServiceManager#ServiceManager(java.util.Scanner)}
	 * .
	 */
	@Test
	public void testServiceManagerScanner() {
		// Assert that an service manager starts with 5 ordered unassigned
		// droids
		assertEquals("05V: UNASSIGNED" + "\n" + "03V: UNASSIGNED" + "\n"
				+ "02E: UNASSIGNED" + "\n" + "01C: UNASSIGNED" + "\n"
				+ "04C: UNASSIGNED" + "\n", manager.printDroids());

		assertEquals("V Platinum  M78!91$0 Jerry Purp" + "\n",
				secManager.printWaitList(""));
	}

	/**
	 * Test method for the assign droid method of the service manager class; it
	 * is responsible for assigning the droids in the repair center to a
	 * correspod1ing device
	 * 
	 * {@link edu.ncsu.csc216.androtech.model.management.ServiceManager#assignDroids()}
	 * .
	 */
	@Test
	public void testAssignDroids() {
		// Assign the droids in the service manager devices from the waiting
		// list (should have 3
		manager.assignDroids();

		// Assert that the devices in the array was assigned to a proper droid
		assertEquals(devices[1].toString(), manager.releaseFromService(0)
				.toString());
		assertEquals(devices[2].toString(), manager.releaseFromService(4)
				.toString());
		assertEquals(devices[3].toString(), manager.releaseFromService(1)
				.toString());
		assertEquals(devices[4].toString(), manager.releaseFromService(2)
				.toString());
		assertEquals(devices[0].toString(), manager.releaseFromService(3)
				.toString());

		// Add new droid
		manager.addNewDroid();
		// Assert this droid is unassigned
		assertEquals(null, manager.releaseFromService(1));
	}

	/**
	 * Test method for the remove method of the service manager class. This
	 * method calls the device list's remove method
	 *
	 * {@link edu.ncsu.csc216.androtech.model.management.ServiceManager#remove(java.lang.String, int)}
	 * .
	 */
	@Test
	public void testRemove() {
		// Assert that no device is at the position and filter provided
		assertEquals(null, manager.remove("A", 04));

		// remove everyone from the wait list
		manager.remove("", 0);
		manager.remove("", 0);
		manager.remove("", 0);
		manager.remove("", 0);
		manager.remove("", 0);

		// Assert that there is nothing the wait list
		assertEquals("", manager.printWaitList(""));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.androtech.model.management.ServiceManager#putOnWaitingList(java.lang.String, java.lang.String, java.lang.String, int)}
	 * .
	 */
	@Test
	public void testPutOnWaitingListStringStringStringInt() {
		// remove everyone from the wait list
		manager.remove("", 0);
		manager.remove("", 0);
		manager.remove("", 0);
		manager.remove("", 0);
		manager.remove("", 0);
		
		// Place single device back on wait list
		manager.putOnWaitingList("C", devices[0].getSerialNum(), devices[0].getName(), devices[0].getTier());
		
		// Assert that there is nothing the wait list
		assertEquals("C Silver    123456 John Doe" + "\n", manager.printWaitList(""));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.androtech.model.management.ServiceManager#putOnWaitingList(edu.ncsu.csc216.androtech.model.devices.Prioritizable)}
	 * .
	 */
	@Test
	public void testPutOnWaitingListPrioritizable() {
		// Assert that the wait list is full
		assertEquals("V Platinum  78910 Jane Doe" + "\n"
				+ "C Platinum  123456 Janet Walsh" + "\n"
				+ "V Gold      M231Q Dan Gaben" + "\n"
				+ "V Gold      QW123W Quinn Tucker" + "\n"
				+ "C Silver    123456 John Doe" + "\n",
				manager.printWaitList(""));

		// remove everyone from the wait list
		manager.remove("", 0);
		manager.remove("", 0);
		manager.remove("", 0);
		manager.remove("", 0);
		manager.remove("", 0);
		// Assert that there is nothing the wait list
		assertEquals("", manager.printWaitList(""));

		// Place every device back on wait list in order
		manager.putOnWaitingList(devices[0]);
		manager.putOnWaitingList(devices[1]);
		manager.putOnWaitingList(devices[2]);
		manager.putOnWaitingList(devices[3]);
		manager.putOnWaitingList(devices[4]);

		// Assert that the wait list is full
		assertEquals("V Platinum  78910 Jane Doe" + "\n"
				+ "C Platinum  123456 Janet Walsh" + "\n"
				+ "V Gold      M231Q Dan Gaben" + "\n"
				+ "V Gold      QW123W Quinn Tucker" + "\n"
				+ "C Silver    123456 John Doe" + "\n",
				manager.printWaitList(""));
	}

	/**
	 * Test method for the release from service method in the service manager
	 * class; it is responsible for releasing a device from a tech droid after
	 * it has been repaired, the released device is what is returned
	 * 
	 * {@link edu.ncsu.csc216.androtech.model.management.ServiceManager#releaseFromService(int)}
	 * .
	 */
	@Test
	public void testReleaseFromService() {
		// Assign the droids in the service manager devices from the waiting
		// list (should have 3
		manager.assignDroids();

		// Assert that the devices in the array was assigned to a proper droid
		assertEquals(devices[1].toString(), manager.releaseFromService(0)
				.toString());
		assertEquals(devices[2].toString(), manager.releaseFromService(4)
				.toString());
		assertEquals(devices[3].toString(), manager.releaseFromService(1)
				.toString());
		assertEquals(devices[4].toString(), manager.releaseFromService(2)
				.toString());
		assertEquals(devices[0].toString(), manager.releaseFromService(3)
				.toString());

		// Add new droid
		manager.addNewDroid();
		// Assert this droid is unassigned
		assertEquals(null, manager.releaseFromService(1));
	}

	/**
	 * Test method for the add droid method in the service manager class; it is
	 * responsible for adding a new tech droid to the repair center
	 * 
	 * {@link edu.ncsu.csc216.androtech.model.management.ServiceManager#addNewDroid()}
	 * .
	 */
	@Test
	public void testAddNewDroid() {
		// Assert that an empty service manager starts with 5 ordered unassigned
		// droids
		assertEquals("05V: UNASSIGNED" + "\n" + "03V: UNASSIGNED" + "\n"
				+ "02E: UNASSIGNED" + "\n" + "01C: UNASSIGNED" + "\n"
				+ "04C: UNASSIGNED" + "\n", manager.printDroids());

		// Add new droid to list (Should be COM droid)
		manager.addNewDroid();

		// Assert that an empty service manager starts with 5 ordered unassigned
		// droids
		assertEquals("05V: UNASSIGNED" + "\n" + "03V: UNASSIGNED" + "\n"
				+ "02E: UNASSIGNED" + "\n" + "01C: UNASSIGNED" + "\n"
				+ "04C: UNASSIGNED" + "\n" + "06C: UNASSIGNED" + "\n",
				manager.printDroids());

		// Add new droid to list (Should be COM droid)
		manager.addNewDroid();

		// Add new droid to list (Should be COM droid)
		manager.addNewDroid();

		// Assert that an empty service manager starts with 5 ordered unassigned
		// droids
		assertEquals("05V: UNASSIGNED" + "\n" + "03V: UNASSIGNED" + "\n"
				+ "02E: UNASSIGNED" + "\n" + "01C: UNASSIGNED" + "\n"
				+ "04C: UNASSIGNED" + "\n" + "06C: UNASSIGNED" + "\n"
				+ "07C: UNASSIGNED" + "\n" + "08C: UNASSIGNED" + "\n",
				manager.printDroids());

	}

	/**
	 * Test method for the print wait list method in the service manager class;
	 * this is responsible for returning the list of devices that meets the
	 * filter
	 * 
	 * {@link edu.ncsu.csc216.androtech.model.management.ServiceManager#printWaitList(java.lang.String)}
	 * .
	 */
	@Test
	public void testPrintWaitList() {
		// Assert that the wait list is full
		assertEquals("V Platinum  78910 Jane Doe" + "\n"
				+ "C Platinum  123456 Janet Walsh" + "\n"
				+ "V Gold      M231Q Dan Gaben" + "\n"
				+ "V Gold      QW123W Quinn Tucker" + "\n"
				+ "C Silver    123456 John Doe" + "\n",
				manager.printWaitList(""));

		// remove everyone from the wait list
		manager.remove("", 0);
		manager.remove("", 0);
		manager.remove("", 0);
		manager.remove("", 0);
		manager.remove("", 0);

		// Assert that there is nothing the wait list
		assertEquals("", manager.printWaitList(""));
	}

	/**
	 * Test method for printing the droids currently in the repair center as
	 * created by the serice manager
	 * {@link edu.ncsu.csc216.androtech.model.management.ServiceManager#printDroids()}
	 * .
	 */
	@Test
	public void testPrintDroids() {
		// Assert that the service manager starts with 5 ordered unassigned
		// droids
		assertEquals("05V: UNASSIGNED" + "\n" + "03V: UNASSIGNED" + "\n"
				+ "02E: UNASSIGNED" + "\n" + "01C: UNASSIGNED" + "\n"
				+ "04C: UNASSIGNED" + "\n", manager.printDroids());

	}
}
