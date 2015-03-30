package edu.ncsu.csc216.androtech.model.repair_center;

import edu.ncsu.csc216.androtech.model.devices.Device;
import edu.ncsu.csc216.androtech.model.devices.VRDevice;

/**
 * Class used to represent a service droid of type virtual reality, which is a
 * member of the abstract TechDroids class
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (March 19, 2015)
 * 
 */
public class VRDroid extends TechDroid {

	/**
	 * Constructor method used to create a service droid used for servicing
	 * virtual reality devices
	 * 
	 */
	public VRDroid() {
		super("V");
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
	 */
	@Override
	public void assign(Device device) throws DroidBusyException, DroidDeviceMismatchException {
		if (this.isAssigned()) {
			// Throw DroidBusyException
			throw new DroidBusyException();
		} else {
			if (device instanceof VRDevice) {
				// Assign device to this droid
				super.assign(myDevice);
				myDevice = device;
			} else {
				// Throw device mismatch exception
				throw new DroidDeviceMismatchException();
			}
		}
	}
	
	
}
