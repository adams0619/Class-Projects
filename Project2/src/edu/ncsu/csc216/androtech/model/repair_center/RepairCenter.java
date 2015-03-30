package edu.ncsu.csc216.androtech.model.repair_center;

import java.util.Random;

import edu.ncsu.csc216.androtech.model.devices.Device;

/**
 * Class representing an array list of tech droids that will be used to repair
 * the various devices.
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (March 19, 2015)
 *
 */
public class RepairCenter {

	/** Variable representing the maximum number of droids */
	private static final int MAX_DROIDS = 30;

	/**
	 * Variable representing the initial number of droids, if no text file is
	 * found
	 */
	private static final int DEFAULT_SIZE = 5;

	/** Variable representing the size of the droids list */
	private int size;

	/** Representation of the tech droids array list */
	private TechDroid[] droids;

	/**
	 * Constructor method used to create repair center (ArrayList) used for
	 * housing the various tech droids
	 */
	public RepairCenter() {
		// Always start with a list of 5 specific
		//create a list with 30 spaces for tech droids
		TechDroid.startDroidNumberingAt01();
		droids = new TechDroid[MAX_DROIDS];
		initDroids(DEFAULT_SIZE);
		size = 5;

	}

	/**
	 * Method used to create the five basic droids if n
	 * 
	 * @param intialNumDroids
	 *            number of initial droids in repair center
	 */
	private void initDroids(int intialNumDroids) {
		// If the list does not equal 5 then do nothing, if the list is 5 then
		// create the initial first 5 droids used by the repair center
		TechDroid one = new ComDroid();
		TechDroid two = new ExpertDroid(); 
		TechDroid three = new VRDroid();
		TechDroid four = new ComDroid();
		TechDroid five = new VRDroid();

		droids[0] = five;
		droids[1] = three;
		droids[2] = two;
		droids[3] = one;
		droids[4] = four;
	}

	/**
	 * Method use to add a tech droid to the repair center array list, if there
	 * are already 30 droids in the repair center then no new droids is added
	 */
	public void addTechDroid() {
		double vR = ((size + 1) * .3) / size;
		// double actualPercentage = 0.0;
		Random randomNumber = new Random();
		int rand = randomNumber.nextInt(10);
		// Gives you the percent of random capable droids needed
		double ranVar = (double) rand / 10;
		double minVrNeeded = .6;
		// Count the number of VR droids
		int numVr = 0;
		int numVRrAndEx = 0;
		for (int i = 0; i < size; i++) {
			if (droids[i].toString().endsWith("V")) {
				numVr++;
			}
			if (droids[i] instanceof ExpertDroid
					|| droids[i] instanceof VRDroid) {
				numVRrAndEx++;
			}
		}

		if (!(size >= MAX_DROIDS)) {
			double actualPercentage = (double) numVRrAndEx / (double) size;
			if (actualPercentage >= vR) {
				droids[size] = new ComDroid();
				size++;
			} else {
				// Create VR or Expert droid , but move the list over by one
				// each time
				if (ranVar < minVrNeeded) {
					for (int i = size + numVr; i >= numVr; i--) {
						if (i < MAX_DROIDS - 1) {
							droids[i + 1] = droids[i];
						}
					}
					droids[numVr] = new VRDroid();
					size++;
				} else {
					for (int i = size + numVRrAndEx; i >= numVRrAndEx; i--) {
						if (i < MAX_DROIDS - 1) {
							droids[i + 1] = droids[i];
						}
					}
					droids[numVRrAndEx] = new ExpertDroid();
					size++;

				}
			}
		} 
	}

	/**
	 * Method used to determine how many droids are available for servicing
	 * different types of devices.
	 * 
	 * @return number of available droids
	 */
	public int numberOfAvailableDroids() {
		int available = 0;
		for (int i = 0; i < droids.length; i++) {
			if (droids[i] != null && !(droids[i].isAssigned())) {
				available++;
			}
		}
		return available;
	}

	/**
	 * Returns the droid at the specific index.
	 * 
	 * @param index
	 *            the index for the specified droid
	 * @return the droid at the specified index
	 */
	public TechDroid getDroidAt(int index) {
		return droids[index];
	}

	/**
	 * Method used to return the total number of droids in the repair center
	 * array list
	 * 
	 * @return total number of droids (size)
	 */
	public int totalNumberOfDroids() {
		return size;
	}

	/**
	 * Method used to release a device from a tech droid after it has been
	 * repaired, the released device is what is returned
	 * 
	 * @param index
	 *            index of the droid which has the device to be released
	 * @return the released device
	 */
	public Device release(int index) {
		// Remove the device from the techDroid since it's now repaired.
		Device repDevice = droids[index].release();
		return repDevice;
	}

	/**
	 * Method used to print the list of droids available in the repair center
	 * 
	 * @return String representing all the droids in the repair center
	 */
	public String printDroids() {
		// Create a string for the list of droids
		String s = "";
		for (int i = 0; i < size; i++) {
			s += droids[i].toString() + "\n";
		}
		return s;
	}

}
