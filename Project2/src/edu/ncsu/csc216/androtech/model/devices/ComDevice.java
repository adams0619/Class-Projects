package edu.ncsu.csc216.androtech.model.devices;

/**
 * Class representing a communication device which is a member of the device
 * class
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (March 15, 2015)
 * 
 */
public class ComDevice extends Device {

	/**
	 * Constructor method used to create a communication device
	 * 
	 * @param serialNum
	 *            the serial number tied to this device
	 * @param name
	 *            represents the name of the device
	 * @param tierIndex
	 *            service level index for this particular device
	 * @throws BadDeviceInformationException
	 *             exception thrown if the information for creating the device
	 *             is incorrect
	 */
	public ComDevice(String serialNum, String name, int tierIndex)
			throws BadDeviceInformationException {
		super(serialNum, name, tierIndex);
	}

	/**
	 * To String method used to return a string representation of the
	 * communication device
	 * 
	 * @return String representation of the VRDevice's information
	 */
	@Override
	public String toString() {
		String s =  String.format("C %-10s" + getSerialNum() + " " + getName(), CUSTOMER_TIER[getTier()]);
		return s;
	}

}
