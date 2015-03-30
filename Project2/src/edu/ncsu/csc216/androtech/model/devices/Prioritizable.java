package edu.ncsu.csc216.androtech.model.devices;

/**
 * Public interface used to determine the priority of a device in the waiting
 * list based on its tier.
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (March 19, 2015)
 *
 */
public interface Prioritizable {

	/**
	 * Gets the tier of the selected device.
	 * 
	 * @return integer representing this device's tier
	 */
	public int getTier();

	/**
	 * Method used to determine if the device being added is of higher or lower
	 * tier then the device before it
	 * 
	 * @param priority
	 *            Object representing the device to be compared against
	 * @return zero if the two items match or a negative number if they don't
	 *         match
	 */
	public int compareToTier(Prioritizable priority);

}
