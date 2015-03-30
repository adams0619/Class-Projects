package edu.ncsu.csc216.androtech.model.management;

import java.util.Scanner;

import edu.ncsu.csc216.androtech.model.devices.*;
import edu.ncsu.csc216.androtech.model.repair_center.*;
import edu.ncsu.csc216.androtech.model.util.SimpleIterator;

/**
 * Class representing the manager for out android repair center, this class is
 * responsible for creating the arrays of droids and list of devices as well as
 * managing them concurrently
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (March 20, 2015)
 */
public class ServiceManager implements Manager {
	/**
	 * Private reference to the linked list containing the devices
	 */
	private DeviceList devicesWaiting;

	/**
	 * Private reference to the repair center and the array list containing the
	 * droids
	 */
	private RepairCenter androTech;

	// Call empty constructor if no file is found
	// Create empty device list and initial 5 droids
	/**
	 * Service manager constructed called when no text file is found containing
	 * device information
	 */
	public ServiceManager() {
		// Create an empty list of devices
		devicesWaiting = new DeviceList();
		androTech = new RepairCenter();
	}

	/**
	 * Constructor method for service manager that accepts a scanner containing
	 * information as a parameter. This scanner is passed and used to creates a
	 * device list and a repair center is also created
	 * 
	 * @param scan
	 *            scanner containing the passed text file information
	 */
	public ServiceManager(Scanner scan) {
		// Use file to create list of devices
		try {
			devicesWaiting = new DeviceList(scan);
		} catch (Exception e) {
			devicesWaiting = new DeviceList();
		}
		androTech = new RepairCenter();
	}

	/**
	 * Method used to assign the droids in the repair center a correspod1ing
	 * device
	 * 
	 */
	public void assignDroids() {
		try {
			// get the first device from the devices linked list
			SimpleIterator<Device> deviceIter = devicesWaiting.iterator();
			// Traverse the array of repair center droids and assign the device
			// to a corresponding droid
			while (devicesWaiting.iterator().hasNext()) {
				// get the next device from the devices linked list
				Device device = deviceIter.next();
				findDroids(device);
			}
		} catch (Exception e) {
			// Don't assign anything there was an error somewhere
		}
	}

	/**
	 * Helper method used to help determine if a droid is available for the
	 * selected device, if no droids is available then
	 * 
	 * @param device
	 *            device to be serviced which is looking for a droid to service
	 *            it
	 * @return true if there is a droid available for this device, false
	 *         otherwise
	 */
	private boolean findDroids(Device device) {
		boolean isVrDev = false;
		int comPositionStart = androTech.totalNumberOfDroids() - 1;
		if (device instanceof VRDevice) {
			isVrDev = true;
		}
		if (isVrDev) {
			for (int i = 0; i <= androTech.numberOfAvailableDroids() + 1; i++) {
				// If the device is an instance of the VR or COM device then
				// assign
				// the device to that droid
				try {
					androTech.getDroidAt(i).assign(device);
					remove(device.getName(), 0);
					return true;
				} catch (DroidBusyException | DroidDeviceMismatchException e) {
					// Skip this line and continue looking for a droid to
					// process
					// the device
					continue;
				}
			}
		} else {
			for (int i = comPositionStart; i >= 0; i--) {
				try {
					androTech.getDroidAt(i).assign(device);
					remove(device.getName(), 0);
					return true;
				} catch (DroidBusyException | DroidDeviceMismatchException e) {
					// Skip this line and continue looking for a droid to
					// process the device
					continue;
				}
			}
		}
		return false;
	}

	/**
	 * Remove the selected device that meets the filter and is at the specific
	 * position;
	 * 
	 * @param filter
	 *            filtered list of devices that contains the device to be
	 *            removed
	 * @param position
	 *            index of the device to be removed
	 * @return the removed device as a Prioritizable interface
	 */
	public Prioritizable remove(String filter, int position) {
		return devicesWaiting.remove(filter, position);
	}

	/**
	 * Method used to place a device onto the devices awaiting service list
	 * 
	 * @param type
	 *            corresponds to the type of device being added
	 * @param serialNum
	 *            refers to the serial number for this device
	 * @param name
	 *            the owners name of this device
	 * @param tier
	 *            index that relates to this devices service tier
	 */
	@Override
	public void putOnWaitingList(String type, String serialNum, String name,
			int tier) {
		try {
			if (type.startsWith("V") || type.startsWith("v")) {
				VRDevice device = new VRDevice(serialNum, name, tier);
				// Add this device to the list
				devicesWaiting.add(device);
			} else {
				ComDevice device = new ComDevice(serialNum, name, tier);
				// Add this device to the list
				devicesWaiting.add(device);
			}
		} catch (BadDeviceInformationException e) {
			// Don't create new device b/c bad information
		}
	}

	/**
	 * Method used to place device onto waiting list by extracting the passed
	 * parameters
	 * 
	 * @param passDev
	 *            passed device from
	 */
	@Override
	public void putOnWaitingList(Prioritizable passDev) {
		// Cast passed parameter to device and extract device information
		Device newDevice = (Device) passDev;
		String type = "";
		if (newDevice instanceof VRDevice) {
			type = "VRDevice";
		} else {
			type = "ComDevice";
		}
		// Place device onto waiting list
		putOnWaitingList(type, newDevice.getSerialNum(), newDevice.getName(),
				newDevice.getTier());
	}

	/**
	 * Method used to release a device from a tech droid after it has been
	 * repaired, the released device is what is returned
	 * 
	 * @param droid
	 *            index of the droid which has the device to be released
	 * @return the released device as type Prioritizable
	 */
	@Override
	public Prioritizable releaseFromService(int droid) {
		return androTech.release(droid);
	}

	/**
	 * Method used to add a new tech droid to the repair center
	 * 
	 */
	@Override
	public void addNewDroid() {
		androTech.addTechDroid();
	}

	/**
	 * Method used to return the list of devices that meets the filter
	 * 
	 * @param filter
	 *            filter used when creating filtered waiting list
	 * @return String of devices that meet the required filter
	 */
	@Override
	public String printWaitList(String filter) {
		return devicesWaiting.filteredList(filter);
	}

	/**
	 * Method used to return the list of droids in the repair center
	 * 
	 * @return String of droids that are in the repair center
	 */
	@Override
	public String printDroids() {
		return androTech.printDroids();
	}
}
