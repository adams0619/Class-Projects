package edu.ncsu.csc216.androtech.model.devices;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.androtech.model.util.SimpleIterator;

/**
 * Method used to test the DeviceList class for our program through various
 * testing strategies
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (March 25, 2015)
 *
 */
public class DeviceListTest {

	/** Private COM Devices used for testing */
	private ComDevice one, three;

	/** Private COM Devices used for testing */
	private VRDevice two;

	/**
	 * Private reference to the Scanner
	 */
	private Scanner sc;

	/**
	 * Private reference to a device list used throughout this testing program
	 */
	private DeviceList commonlist;

	/**
	 * Set up method used to create objects for testing this method
	 * 
	 * @throws java.lang.Exception
	 *             general exception can be thrown if a problem is encountered
	 *             during the construction of objects
	 */
	@Before
	public void setUp() throws Exception {
		try {
			one = new ComDevice("123456", "John Doe", 1);
			two = new VRDevice("78910", "Jane Doe", 3);
			three = new ComDevice("MQ@1234", "Jurry Park", 0);
		} catch (BadDeviceInformationException e) {
			assertEquals(1, one.getTier());
			assertEquals(3, two.getTier());
			assertEquals(1, three.getTier());
			assertEquals("123456", one.getSerialNum());
			assertEquals("78910", two.getSerialNum());
			assertEquals("1234", three.getSerialNum());
			assertEquals("John Doe", one.getName());
			assertEquals("Jane Doe", two.getName());
			assertEquals("Jurry Park", three.getName());
		}
		String s = "C 1    123456 John Doe" + "\n";
		s += "V 3  78910 Jane Doe";
		sc = new Scanner(s);

		// Construct new device list with parameters
		commonlist = new DeviceList();
	}

	/**
	 * Test method for the device list constructor with a scanner passed as a
	 * parameter
	 * 
	 * {@link edu.ncsu.csc216.androtech.model.devices.DeviceList#DeviceList(java.util.Scanner)}
	 * .
	 */
	@Test
	public void testDeviceListScanner() {
		// Construct new device list with parameters
		DeviceList newdevice = new DeviceList(sc);
		// Assert that a device has been added to the device list
		assertEquals(true, newdevice.iterator().hasNext());

		// Assert that the first device in the list is the VR device
		assertEquals(two.toString(), newdevice.iterator().next().toString());

		// Assert that another device has been added to the device list
		assertEquals(true, newdevice.iterator().hasNext());

		// Construct new device list with null scanner
		sc = null;
		DeviceList nullscan = new DeviceList(sc);
		try {
			nullscan.iterator().next();
		} catch (NoSuchElementException e) {
			e.getStackTrace();
			// Assert that there is nothing in this device list
			assertEquals("", nullscan.filteredList(""));
		}
	}

	/**
	 * Test method for the device list constructor with no parameters passed
	 * 
	 * {@link edu.ncsu.csc216.androtech.model.devices.DeviceList#DeviceList()}.
	 */
	@Test
	public void testDeviceList() {
		// Construct new device list with no parameters
		DeviceList newList = new DeviceList();

		// Assert that no device is in this list
		assertEquals(false, newList.iterator().hasNext());
	}

	/**
	 * Test method used to test the iterator method in the device list class.
	 * This method is used to easily go through the list and also determine if
	 * another item can be found in the list
	 * 
	 * {@link edu.ncsu.csc216.androtech.model.devices.DeviceList#iterator()}.
	 */
	@Test
	public void testIterator() {
		// Construct new device list with parameters
		DeviceList newList = new DeviceList();

		// Construct new device list with parameters
		DeviceList newdevice = new DeviceList(sc);

		// Assert that a device is in this list b/c it was constructed with
		// parameters
		assertEquals(true, newdevice.iterator().hasNext());

		// Assert that no device is in this list
		assertEquals(false, newList.iterator().hasNext());

		// Assert that the first device is a VR Device
		assertEquals(two.toString(), newdevice.iterator().next().toString());
	}

	/**
	 * Test method for the add method in the device list class. This method is
	 * responsible for adding items in the list regardless of their insertion
	 * point
	 * 
	 * 
	 * {@link edu.ncsu.csc216.androtech.model.devices.DeviceList#add(edu.ncsu.csc216.androtech.model.devices.Device)}
	 * .
	 */
	@Test
	public void testAdd() {
		// Create simple iterator
		SimpleIterator<Device> iterator = commonlist.iterator();

		// Assert that no devices are in the list b/c it's empty
		assertEquals(false, iterator.hasNext());

		// Add device to the common list @ the beginning of the list
		commonlist.add(one);

		// Add device to the common list to the front of the existing list
		commonlist.add(two);

		// Add device to the common list to the front of the existing list
		commonlist.add(three);

		// Create simple iterator
		SimpleIterator<Device> newIterator = commonlist.iterator();
		// Assert that one device is in the list
		assertEquals(true, newIterator.hasNext());

		// Assert that the first device is the VR device
		assertEquals(two, newIterator.next());

		// Assert that the second device is the COM device
		assertEquals(one, newIterator.next());

	}

	/**
	 * Test method for the remove method in the device list class. This method
	 * is responsible for removing items in the list regardless of their
	 * insertion point
	 * 
	 * {@link edu.ncsu.csc216.androtech.model.devices.DeviceList#remove(java.lang.String, int)}
	 * .
	 */
	@Test
	public void testRemove() {
		// Add device to the common list @ the beginning of the list
		commonlist.add(one);

		// Add device to the common list to the front of the existing list
		commonlist.add(two);

		// Create simple iterator
		SimpleIterator<Device> iterator = commonlist.iterator();

		// Assert that one device is in the list
		assertEquals(true, iterator.hasNext());

		// Remove the second device in this device list
		commonlist.remove("", 1);

		// Assert that one device is in the list
		assertEquals(true, iterator.hasNext());

		// Remove the first device in this device list
		// Assert that the removed device is the VR Device
		assertEquals(two, commonlist.remove("", 0));

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.androtech.model.devices.DeviceList#filteredList(java.lang.String)}
	 * .
	 */
	@Test
	public void testFilteredList() {
		// Add device to the common list @ the beginning of the list
		commonlist.add(one);

		// Add device to the common list to the front of the existing list
		commonlist.add(two);

		// Assert that a filter list with filter "Jo" returns Com Device
		assertEquals("C Silver    123456 John Doe" + "\n",
				commonlist.filteredList("Jo"));

		// Assert that a filter list with filter "Ja" returns VR Device
		assertEquals("V Platinum  78910 Jane Doe" + "\n",
				commonlist.filteredList("Ja"));

	}

}
