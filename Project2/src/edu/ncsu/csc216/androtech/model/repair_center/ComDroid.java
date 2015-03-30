package edu.ncsu.csc216.androtech.model.repair_center;

import edu.ncsu.csc216.androtech.model.devices.ComDevice;
import edu.ncsu.csc216.androtech.model.devices.Device;

/**
 * Class used to represent a service droid of type communication, which is a
 * member of the abstract TechDroids class
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (March 24, 2015)
 *
 */
public class ComDroid extends TechDroid {

	/**
	 * Constructor method used to create a service droid used for servicing
	 * communication devices
	 *
	 */
	public ComDroid() {
		super("C");
	}

	/**
	 * Method used to assign a device to this device. This method overrides its
	 * parent method
	 * 
	 * @param device Device to be assigned to this droid
	 * @throws DroidBusyException
	 *             thrown if the droid is busy but someone is attempting to
	 *             assign it a device
	 * @throws DroidDeviceMismatchException
	 *             thrown if an incorrect device is attempted to be assigned to
	 *             this droid
	 * 
	 */
	@Override
	public void assign(Device device) throws DroidBusyException,
			DroidDeviceMismatchException {
		if (this.isAssigned()) {
			// Throw DroidBusyException
			throw new DroidBusyException();
		} else {
			if (device instanceof ComDevice) { 
				// Assign device to this droid
				super.assign(device);
				myDevice = device;
			} else {
				// Throw device mismatch exception
				throw new DroidDeviceMismatchException();
			}
		}
	}

}
