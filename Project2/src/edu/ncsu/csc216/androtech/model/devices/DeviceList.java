package edu.ncsu.csc216.androtech.model.devices;

import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.androtech.model.util.SimpleIterator;

/**
 * Class representing a linked list of devices awaiting service from the repair
 * center
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (March 24, 2015)
 *
 */
public class DeviceList {
	/**
	 * Private reference to the beginning (or current position) of the linked
	 * list 
	 */
	private Node head;

	/**
	 * Constructor method for device list that accepts scanner as a parameter
	 * and uses the information found in the scanner to populate the list
	 * 
	 * @param scan
	 *            scanner containing the passed text file.
	 */
	public DeviceList(Scanner scan) {
		String kind = "";
		int tier = -1;
		String serial = "";
		String name = "";
		if (scan == null) {
			head = null;
		} else {
			while (scan.hasNextLine()) {
				try {
					kind = scan.next();
					tier = scan.nextInt();
					serial = scan.next();
					name = scan.nextLine();
					if (kind.equalsIgnoreCase("c")) {
						this.add(new ComDevice(serial, name, tier));
					} else if (kind.equalsIgnoreCase("V")) {
						this.add(new VRDevice(serial, name, tier));
					}
				} catch (Exception e) {
					if (scan.hasNextLine())
						scan.nextLine();
				}
			}
		}
	}

	/**
	 * Constructor method for device list that accepts no parameters and creates
	 * an empty device list
	 */
	public DeviceList() {
		head = null;
	}

	/**
	 * Method used to determine the first point of the list as well as return a
	 * cursor that can be used to easily traverse the linked list
	 * 
	 * @return SimpleIterator with type Device
	 */
	public SimpleIterator<Device> iterator() {
		Cursor cursor = new Cursor();
		return cursor;
	}

	/**
	 * Remove method that accepts a filter and positon as a parameter, using
	 * these parameters it determines where in a list the item to be removed is
	 * and removes it
	 * 
	 * @param filter
	 *            filter used to match only certain devices based on their name
	 * @param position
	 *            index indicating where the device is in the filtered list.
	 * @return the device being removed.
	 */
	public Device remove(String filter, int position) {
		Node trailingNode;
		Node removedNode;
		try {
			trailingNode = findTrailingNode(filter, position);
		} catch (NoSuchElementException e) {
			return null;
		}
		if (trailingNode == null) {
			removedNode = head;
			head = head.next;
		} else {
			removedNode = trailingNode.next;
			trailingNode.next = trailingNode.next.next;
		}
		return removedNode.device;
	}

	/**
	 * Method used to find the trailing (last) node for the specific filtered
	 * list by using the position and the filter The trailing node is returned
	 * 
	 * @param filter
	 *            filter used to match only certain devices based on their name
	 * @param position
	 *            index along the filtered list
	 * @return the trailing node from the linked list based on the filtered list
	 *         and position
	 */
	private Node findTrailingNode(String filter, int position) {
		Node current = head;
		Node trailingNode = null;
		int devMeetsFilter = -1;
		while (current != null && devMeetsFilter < position) {
			if (current.device.meetsFilter(filter)) {
				devMeetsFilter++;
			}
			if (devMeetsFilter < position) {
				trailingNode = current;
				current = current.next;
			}
		}
		if (devMeetsFilter == position) {
			return trailingNode;
		} else {
			throw new NoSuchElementException();
		}
	}

	/**
	 * This method adds the specified device to the linked list, regardless of
	 * the insertion point, and instead based on it's tier and the tier of the
	 * devices it will be added next to
	 * 
	 * @param deviceToAdd
	 *            the device being added to the linked list
	 */
	public void add(Device deviceToAdd) {
		int size = 0;
		Node current = head;
		while (current != null) {
			current = current.next;
			size++;
		}
		current = head;
		// add to the beginning
		if (head == null) {
			head = new Node(deviceToAdd, head);
			// add after the beginning
		} else if (deviceToAdd != null
				&& head.device.compareToTier(deviceToAdd) == -1) {
			head = new Node(deviceToAdd, head);
		} else {
			// add anywhere else
			int i = 0;
			Node prev = null;
			current = head;
			while (i < size && current != null
					&& (current.device.compareToTier(deviceToAdd) != -1)) {
				prev = current;
				current = current.next;
				i++;
			}
			prev.next = new Node(deviceToAdd, prev.next);
		}
	}

	/**
	 * Method which uses the specified filter to find all devices that match the
	 * prefix and creates a string showing only these devices, called
	 * filteredList.
	 * 
	 * @param filter
	 *            the filter string used to sort the enter list
	 * @return filteredList the string of the devices that match the filter.
	 */
	public String filteredList(String filter) {
		Node current = head;
		String filteredList = "";
		while (current != null) {
			if (current.device.meetsFilter(filter.trim())) {
				filteredList += current.device.toString() + "\n";
			} else if (filter.equals("")) {
				filteredList += current.device.toString() + "\n";
			}
			current = current.next;
		}
		return filteredList;
	}

	/**
	 * Private inner class representing a node for our linked list
	 * 
	 * @author Adams Ombonga (amombong)
	 * @version 1.0 (March 19, 2015)
	 *
	 */
	private class Node {
		/**
		 * Device reference for the Node class
		 */
		public Device device;

		/**
		 * Reference to the next node in the linked list
		 */
		private Node next;

		/**
		 * Constructor method for Node which accepts a DEvice and node as a
		 * parameter. This method is used to place the object (device) onto the
		 * linked list
		 * 
		 * @param device
		 *            device object containing device data
		 * @param node
		 *            position in the linked list that will reference this
		 *            Device
		 */
		public Node(Device device, Node node) {
			this.device = device;
			this.next = node;

		}
	}

	/**
	 * Private inner class representing a cursor for our linked list. This is
	 * used to traverse the linked list by implementing the SimpleIterator
	 * methods.
	 * 
	 * @author Adams Ombonga (amombong)
	 * @version 1.0 (March 19, 2015)
	 *
	 */
	private class Cursor implements SimpleIterator<Device> {

		/**
		 * Save a reference to the head of the linked list as the variable
		 * cursor
		 */
		private Node cursor;

		/**
		 * Private constructor method used to re reference t
		 */
		private Cursor() {
			cursor = head;
		}

		/**
		 * Method used to determine if another element is available in the
		 * linked list,
		 * 
		 * @return true if another element exists, false otherwise
		 */
		@Override
		public boolean hasNext() {
			// Check to see if the linked list is empty
			return cursor != null;
		}

		/**
		 * This method is used to move to the next element in the linked list,
		 * but first checks to see if there is another element available in the
		 * linked list
		 * 
		 * @return the next available element or null if no element is available
		 */
		@Override
		public Device next() throws NoSuchElementException {
			// Before moving to the next element in the linked list check to see
			// if there is another element available
			if (hasNext()) {
				Device data = cursor.device;
				cursor = cursor.next;
				return data;
			} else {
				throw new NoSuchElementException();
			}
		}
	}
}
