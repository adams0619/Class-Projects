package edu.ncsu.csc216.androtech.model.devices;

/**
 * Abstract class representing a device in the android tech repair center
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (March 29, 2015)
 */
public abstract class Device implements Prioritizable {

	/** Variable representing a device's serial number */
	private String serialNum;

	/** Variable representing a device's name (type) */
	private String name;

	/** Variable representing a device's tier plan */
	private int tierIndex;

	/** Variable representing various customer tiers? */
	public static final String[] CUSTOMER_TIER = { "None", "Silver", "Gold",
			"Platinum" };

	/**
	 * Constructor method for a device using the passed parameters
	 * 
	 * @param serialNum
	 *            serial number for this device
	 * @param name
	 *            owner of this device
	 * @param tierIndex
	 *            index representing the tier status of this device
	 * @throws BadDeviceInformationException
	 *             exception thrown if the information for creating the device
	 *             is incorrect
	 */
	public Device(String serialNum, String name, int tierIndex)
			throws BadDeviceInformationException {
		this.serialNum = serialNum;
		this.name = name;
		this.tierIndex = tierIndex;

		validateName();
		validateSerialNum();
		validateTier();
	}

	/**
	 * Method used to determine if this device meets the user inputed filter for
	 * the name
	 * 
	 * @param filter
	 *            user input filter for the name of the device
	 * @return true or false if the device meets the filter
	 */
	public boolean meetsFilter(String filter) {
		if (filter == null || filter.equals("")
				|| name.toLowerCase().startsWith(filter.toLowerCase().trim())) {
			return true;
		}
		return false;
	}

	/**
	 * Getter method used to get the devices serial number
	 * 
	 * @return the serialNum
	 */
	public String getSerialNum() {
		return serialNum;
	}

	/**
	 * Getter method used to get the devices name
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter method used to get the devices tier
	 * 
	 * @return the tierIndex
	 */
	public int getTier() {
		return tierIndex;
	}

	/**
	 * Ensures that the selected service tier is valid.
	 */
	public void validateTier() {
		if (this.getTier() <= 0) {
			tierIndex = 0;
		} else if (getTier() >= 3) {
			tierIndex = 3;
		}
	}

	/**
	 * Ensures that the name entered by the user is valid.
	 * 
	 * @throws BadDeviceInformationException
	 *             thrown if the name is null or consists of only whitespaces
	 */
	public void validateName() throws BadDeviceInformationException {
		if (name == null || name.matches("^\\s*$")) {
			// Throw exception
			throw new BadDeviceInformationException(
					"Owner name cannot be blank");
		} else {
			name = name.trim();
		}
	}

	/**
	 * Ensures that the serial number entered by the user is valid.
	 * 
	 * @throws BadDeviceInformationException
	 *             thrown if the name is null or consists of only whitespacesF
	 */
	public void validateSerialNum() throws BadDeviceInformationException {
		if (serialNum == null || serialNum.matches("^\\s*$")) {
			// Throw exception
			throw new BadDeviceInformationException(
					"Serial number cannot be blank");
		} else {
			serialNum = serialNum.trim();
		}
	}

	/**
	 * Method used to create a String representation of the device
	 * 
	 * @return String containing device information
	 */
	@Override
	public String toString() {
		// Return the device represented as a string
		if (this instanceof VRDevice) {
			String s = String.format("V %-10s" + getSerialNum() + " "
					+ getName(), CUSTOMER_TIER[getTier()]);
			return s.trim();
		} else {
			String s = String.format("C %-10s" + getSerialNum() + " "
					+ getName(), CUSTOMER_TIER[getTier()]);
			return s.trim();
		}
	}

	/**
	 * Method used to determine if the device being added is of higher or lower
	 * tier then the device before it
	 * 
	 * @param priority
	 *            Object representing the device to be compared against
	 * @return zero if the two items match or a negative number if they don't
	 *         match
	 */
	public int compareToTier(Prioritizable priority) {
		if (priority.getTier() == this.tierIndex) {
			return 0;
			// Negative Number (lower tier)
		} else if (priority.getTier() > this.tierIndex) {
			// Positive number (higher tier)
			return -1;
		} else
			return 1;
	}

}
