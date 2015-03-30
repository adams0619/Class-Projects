package edu.ncsu.csc216.androtech.model.util;

import edu.ncsu.csc216.androtech.model.devices.Device;

/**
 * Interface used to traverse the list for the next available devices or
 * determine if there are more available devices
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (March, 15 ,2015)
 *
 * @param <Device>
 *            the devices parameters from the linked list used within the
 *            iterator
 */
@SuppressWarnings("hiding")
public interface SimpleIterator<Device> {

	/**
	 * Method used to determine if another element in the linked list is
	 * available
	 * 
	 * @return true if another device exists in the list, false otherwise
	 */
	public boolean hasNext();

	/**
	 * Method used to get the next available element in the linked list
	 * 
	 * @return the next element in the linked list
	 */
	public Device next();
}
