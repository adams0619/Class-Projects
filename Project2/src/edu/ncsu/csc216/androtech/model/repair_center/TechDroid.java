package edu.ncsu.csc216.androtech.model.repair_center;

import edu.ncsu.csc216.androtech.model.devices.Device;

/**
 * Abstract class representing the methods/data members of a tech-droid, which
 * is use to repair communication or virtual reality devices
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (
 *
 */
public abstract class TechDroid {

	/** Boolean used to determine if the droid has been assigned */
	private boolean assigned;

	/** ID used to identify specific droids */
	private String droidID;

	/** Variable representing the next number for a droid */
	private static int nextNumber;

	/** Representation of the device that is used by the tech droid */
	public Device myDevice;

	/** Method used to start the droid numbering at 01 */
	public static void startDroidNumberingAt01() {
		nextNumber = 1;
	} 

	/**
	 * Constructor used to create a service droid. The specific droid is created
	 * based on the suffix as well as the corresponding number. The next number
	 * is incremented
	 * 
	 * @param suffix
	 *            determines which kind of droid will be created, either a
	 *            VRDroid, a ComDroid, or ExpertDroid
	 */
	public TechDroid(String suffix) {
		// If a device is available then assign this droid to null and set the
		// assigned variable to false
		myDevice = null;
		assigned = false;
		// Start the droid numbering at 1
		if (nextNumber >= 10) {
			droidID = "" + nextNumber;
		} else {
			droidID = "0" + nextNumber;
		}
		// Increment the next number for the next droid to be created
		nextNumber++;
		// Tack on droid type to droid ID
		droidID += suffix;
		
	}

	/**
	 * Getter method used to get the full droid ID for this particular droid
	 * 
	 * @return the droidID string representation of the droid
	 */
	public String getDroidID() {
		return droidID;
	}

	/**
	 * Method used to determine if the droid is currently assigned a device for
	 * repair
	 * 
	 * @return true/false if the current droid is assigned a device
	 */
	public boolean isAssigned() {
		return assigned;
	}

	/**
	 * Method used to release a device that has been repaired by a droid
	 * 
	 * @return the device that was released
	 */
	public Device release() {
		// Set this droids assigned status to false due to a lack of repairable
		// device
		this.assigned = false;
		Device release = myDevice;
		myDevice = null;
		return release;
	}

	/**
	 * Method used to assign the next device to an appropriate service droid
	 * 
	 * @param device
	 *            the device to be assigned
	 * @throws DroidBusyException
	 *             thrown if the droid is busy but someone is attempting to
	 *             assign it a device
	 * @throws DroidDeviceMismatchException
	 *             thrown if an incorrect device is attempted to be assigned to
	 *             this droid
	 */
	public void assign(Device device) throws DroidBusyException,
			DroidDeviceMismatchException {
		if (!this.assigned) {
			myDevice = device;
			assigned = true;
		} else {
			throw new DroidBusyException();
		}

	}

	/**
	 * Method used to return the droid information as a string representation
	 * 
	 * @return string with droid information
	 */
	@Override
	public String toString() {
		if (myDevice == null) {
			return droidID + ": " + "UNASSIGNED";
		} else {
			return droidID + ": " + myDevice.getSerialNum() + " "
					+ myDevice.getName();
		}	
	}
}
