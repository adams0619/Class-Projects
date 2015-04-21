package edu.ncsu.csc216.carrental.model.management;

import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

import edu.ncsu.csc216.carrental.model.Car;
import edu.ncsu.csc216.carrental.model.Customer;
import edu.ncsu.csc216.carrental.model.InvalidIDException;
import edu.ncsu.csc216.carrental.model.state.Available;
import edu.ncsu.csc216.carrental.model.state.OutForDetail;
import edu.ncsu.csc216.carrental.model.state.OutForRepair;
import edu.ncsu.csc216.carrental.model.state.RentalState;
import edu.ncsu.csc216.carrental.model.state.RentalStateManager;
import edu.ncsu.csc216.carrental.model.state.Rented;
import edu.ncsu.csc216.carrental.util.Queue;
import edu.ncsu.csc216.carrental.util.SimpleQueue;
import edu.ncsu.csc216.carrental.util.SimpleStack;
import edu.ncsu.csc216.carrental.util.Stack;

/**
 * Class representing the manager for our car rental program. This class is
 * responsible for translating UI interaction into program actions
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (April 14, 2015)
 */
public class NuxCarRental implements RentalLocation, RentalStateManager {

	/** Variable representing a queue of customers in our program */
	private SimpleQueue<Customer> customers = new Queue<Customer>();

	/** Variable representing a queue of Cars in the detail shop for our program */
	private SimpleQueue<Car> detailShop = new Queue<Car>();

	/** Variable representing a queue of Cars in the repair shop for our program */
	private SimpleQueue<Car> repairShop = new Queue<Car>();

	/**
	 * Variable representing a queue of Cars available for rental in our program
	 */
	private SimpleStack<Car> available = new Stack<Car>();

	/** Variable representing a queue of Cars in the repair shop for our program */
	private SimpleQueue<Car> rented = new Queue<Car>();

	/**
	 * Constructor method for this class that is called if no text file is found
	 */
	public NuxCarRental() {
		// Empty constructor do nothing b/c stacks/queues have already be intitialized
	}

	/**
	 * Constructor method for this class that is called when a text file is
	 * found. The passed scanner parameter contains the loaded text file that
	 * will be traversed and used to create car objects for our car rental
	 * programs inventory
	 * 
	 * @param scan
	 *            Scanner used to traverse text file.
	 */
	public NuxCarRental(Scanner scan) {
		// Create strings for car variables
		String fleet = null, make = null, model = null, color = null, status = null, name, first = null, last = null, id = null;
		// Use while loop to traverse the text file and create car objects
		while (scan.hasNextLine()) {
			String line = scan.nextLine();
			String[] data = line.split(",");
			if (data.length < 5) {
				continue;
			}
			make = data[0];
			model = data[1];
			color = data[2];
			fleet = data[3];
			status = data[4];
			//Extra customer info (if available)
			if (data.length > 6) {
				name = data[5];
				first = name.substring(0, name.indexOf(" "));
				last = name.substring(name.indexOf(" ") + 1);
				id = data[6];
			}
			
			//TODO Check for Duplicates everywhere you add a car!
			
			try {
				//Create a car and place it into it's corresponding list
				if (status != null) {
					if (status.equalsIgnoreCase("R")) {
						if (first != null && last != null) {
							Customer c = new Customer(first, last, id);
							RentalState r = new Rented(c);
							Car newcar = new Car(fleet, make, model, color);
							newcar.setState(r);
							rented.add(newcar);
						}	
					} else if (status.equalsIgnoreCase("A")) {
						RentalState a = new Available();
						Car newcar = new Car(fleet, make, model, color);
						newcar.setState(a);
						available.push(newcar);
					} else if (status.equalsIgnoreCase("D")) {
						RentalState d = new OutForDetail();
						Car newcar = new Car(fleet, make, model, color);
						newcar.setState(d);
						detailShop.add(newcar);
					} else if (status.equalsIgnoreCase("S")) {
						RentalState s = new OutForRepair();
						Car newcar = new Car(fleet, make, model, color);
						newcar.setState(s);
						repairShop.add(newcar);
					}
 				} 
			} catch (InvalidIDException e) {
				e.printStackTrace();
				System.out.println("Invalid ID, Car discarded");
				continue;
			} 
		}
	}

	/**
	 * Method used to rent the first available car to the first available customer
	 */
	@Override
	public void rentCar() {
		processRental();
	}
	
	/**
	 * Method used to process the return of a car with no problems.
	 */
	@Override
	public void returnCar() {
		//Set parameter to false b/c no problems
		processReturn(false);
	}

	/**
	 * Method used to process the return of a car with problems.
	 */
	@Override
	public void reportProblem() {
		//Set parameter to true b/c problems were reported
		processReturn(true);
	}

	/**
	 * Method used to remove the first car from the detail queue and place it
	 * onto the available stack because detailing is complete
	 */
	@Override
	public void completeDetailing() {
		processDetailed();
	}

	/**
	 * Method used to remove the first car from the repair queue and place it
	 * onto the detailing queue because repairs have been completed
	 */
	@Override
	public void completeRepairs() {
		processRepaired();
	}

	/**
	 * Method used to return a String containing the current cars that are
	 * available for rental
	 *
	 * @return String of cars which are ready for rental
	 */
	@Override
	public String availableCars() {
		// TODO Complete the logic for this method
		String availableString = "";
		SimpleStack<Car> temp = new Stack<>();
		SimpleStack<Car> temp2 = new Stack<>();
		// Pop items from stack and place them into temporary stack (will be in
		// reverse order) --> Last In, First Out
		while (!available.isEmpty()) {
			// Pop first item
			Car availCar = available.pop();
			availableString += availCar.toString() + "\n";
			// Put last items from available stack as first items into this
			// stack
			temp.push(availCar);
		}
		while (!temp.isEmpty()) {
			// Pop items from the temp stack (last item, was the first time in
			// previous stack
			temp2.push(temp.pop());
			available.push(temp2.pop());
		}
		return availableString;
	}

	/**
	 * Method used to return a String containing the current cars that are being
	 * rented
	 * 
	 * @return String of cars which are on a rental
	 */
	@Override
	public String rentedCars() {
		String rentedString = "";
		SimpleQueue<Car> temp = new Queue<>();
		while (!rented.isEmpty()) {
			Car dCar = rented.remove();
			rentedString += dCar.toString() + "\n";
			temp.add(dCar);
		}
		while (!temp.isEmpty()) {
			rented.add((Car) temp.remove());
		}
		return rentedString;
	}

	/**
	 * Method used to return a String containing the current cars waiting to be
	 * detailed
	 * 
	 * @return String of cars waiting to be detailed
	 */
	@Override
	public String detailingCars() {
		String detailString = "";
		SimpleQueue<Car> temp = new Queue<>();
		while (!detailShop.isEmpty()) {
			Car dCar = detailShop.remove();
			detailString += dCar.toString() + "\n";
			temp.add(dCar);
		}
		while (!temp.isEmpty()) {
			detailShop.add((Car) temp.remove());
		}
		return detailString;
	}

	/**
	 * Method used to return a String containing the current cars waiting to be
	 * repaired
	 * 
	 * @return String of cars waiting to be repaired
	 */
	@Override
	public String repairingCars() {
		String repairString = "";
		SimpleQueue<Car> temp = new Queue<>();
		while (!repairShop.isEmpty()) {
			Car dCar = repairShop.remove();
			repairString += dCar.toString() + "\n";
			temp.add(dCar);
		}
		while (!temp.isEmpty()) {
			repairShop.add((Car) temp.remove());
		}
		return repairString;
	}

	/**
	 * Method used to return a String containing the current customers waiting
	 * to rent a car
	 * 
	 * @return String of customers waiting to rent a car
	 */
	@Override
	public String customersWaiting() {
		String customerString = "";
		SimpleQueue<Customer> temp = new Queue<>();
		while (!customers.isEmpty()) {
			Customer current = customers.remove();
			customerString = current.toString() + "\n";
			temp.add(current);
		}
		while (!temp.isEmpty()) {
			customers.add((Customer) temp.remove());
		}
		return customerString;
	}

	/**
	 * Method used to determine if the available queue of cars is empty or not
	 * 
	 * @return true if this stack is empty, false otherwise
	 */
	@Override
	public boolean hasAvailableCars() {
		return !available.isEmpty();
	}

	/**
	 * Method used to determine if the rented queue of cars is empty or not
	 * 
	 * @return true if this queue is empty, false otherwise
	 */
	@Override
	public boolean hasRentedCars() {
		return !rented.isEmpty();
	}

	/**
	 * Method used to determine if the detail queue for detailing cars is empty
	 * or not
	 * 
	 * @return true if this queue is empty, false otherwise
	 */
	@Override
	public boolean hasDetailingCars() {
		return !detailShop.isEmpty();
	}

	/**
	 * Method used to determine if the repair queue for repairing cars is empty
	 * or not
	 * 
	 * @return true if this queue is empty, false otherwise
	 */
	@Override
	public boolean hasRepairingCars() {
		return !repairShop.isEmpty();
	}

	/**
	 * Method used to determine if the customer queue for waiting customers is
	 * empty or not
	 * 
	 * @return true if this queue is empty, false otherwise
	 */
	@Override
	public boolean hasWaitingCustomers() {
		return !customers.isEmpty();
	}

	/**
	 * Method used to complete UI interaction and add customers to the customer
	 * queue
	 */
	@Override
	public boolean addCustomer(Customer c) {
		//TODO Find where to throw error for dialog message
		//TODO Check for Duplicates
		//Check to determine if customer parameters are valid
		if (c != null) {
			//Verify the name is a string w/out #'s or special characters 
			if (c.getFirstName().matches("^([A-Za-z])+\\b") ||
				c.getLastName().matches("^([A-Za-z])+\\b")) {
				// Used to check for duplicates
				boolean needToAdd = true;
				SimpleQueue<Customer> temp = new Queue<>();
				//Last-in First out.
				while (!customers.isEmpty()) {
					Customer tempC = customers.remove();
					if (c.getId().contentEquals(tempC.getId())) {
						needToAdd = false;
					}
					temp.add(tempC);
				}
				//Last-In, First out
				while (!temp.isEmpty()) {
					//Re-add to the customers queue 
					customers.add(temp.remove());
				}
				if (needToAdd) {
					customers.add(c);
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * Method used to complete UI interaction and add a car to the available
	 * stack of cars that can be rented
	 * 
	 * @param c
	 *            the car to be added to the available stack of cars
	 * @return true if the car was added to the available stack, false otherwise
	 */
	@Override
	public boolean addCar(Car c) {
		//TODO Find where to throw error for dialog message
		if (c != null) {
			if (c.getFleetNum().matches("\\b([A-Z]\\d{4})\\b")) {
				SimpleStack<Car> temp = new Stack<>();
				// Used to check for duplicates
				boolean dontAdd = false;
				// Pop items from stack and place them into temp stack (will be in
				// reverse order) --> Last In, First Out
				while (!available.isEmpty()) {
					// Pop first item
					Car availCar = available.pop();
					// Put last items from available stack as first items into this
					// stack
					if (c.getFleetNum().equals(availCar.getFleetNum())) {
						dontAdd = true;
					}
					temp.push(availCar);
				}
				while (!temp.isEmpty()) {
					// Pop items from the temporary stack (last item, was the first time in
					// previous stack
					available.push(temp.pop());
				}
				if (dontAdd) {
					return false;
				} else {
					available.push(c);
					return true;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/**
	 * Method used to rent the next available car to the first waiting customer
	 * by removing the first customer and assigning it to the state of the first
	 * removed car
	 */
	@Override
	public void processRental() {
		// Remove the first car front available and place it into the
		// rented list
		if (!available.isEmpty() && !customers.isEmpty()) {
			Car removed = available.pop();
			Customer renter = customers.remove();
			RentalState rent = new Rented(renter);
			removed.setState(rent);
			rented.add(removed);
		}
	}
	
	/**
	 * Method used to process the return of the first car in the rented list of
	 * cars. This method depends upon whether a problem was reported or not. If
	 * a problem is reported the car is sent to the repair shop otherwise it
	 * goes to the detail shop
	 * 
	 * @param problem
	 *            boolean used to determine if the rented car was returned with
	 *            a problem
	 */
	@Override
	public void processReturn(boolean problem) {
		if (!rented.isEmpty()) {
			if (problem) {
				//Remove car from the rented list and place into repair shop
				Car removed = rented.remove();
				RentalState repairs = new OutForRepair();
				removed.setState(repairs);
				//Move the car to the repair shop
				repairShop.add(removed);
			} else {
				//Remove car from the rented list and place into detail shop
				Car removed = rented.remove();
				RentalState detail = new OutForDetail();
				removed.setState(detail);
				//Move this car to the detail shop
				detailShop.add(removed);
			}
		}
	}

	/**
	 * Method used to process the first car in the detail shop in order to make
	 * it available. The car is removed from the detail shop queue and placed
	 * into the available stack.
	 */
	@Override
	public void processDetailed() {
		// Remove this car from the detail queue into the available stack
		if (!detailShop.isEmpty()) {
			Car removed = detailShop.remove();
			RentalState avail = new Available();
			removed.setState(avail);
			available.push(removed);
		}
	}
	
	/**
	 * Method used to process the first car that was out for repair and send it
	 * for detailing. The car is removed from the the repair ship queue and
	 * placed into the detail shop queue
	 */
	@Override
	public void processRepaired() {
		// Remove this car from the repair shop and into the detailing
		// queue
		if (!repairShop.isEmpty()) {
			Car removed = repairShop.remove();
			RentalState detail = new OutForDetail();
			removed.setState(detail);
			detailShop.add(removed);
		}	
	}

	/**
	 * Method used to complete UI interaction of adding a new car to the
	 * available stack of cars, making it available for renting. If this car's
	 * fleet number matches another car which is already in the available stack
	 * then it is not added
	 * 
	 * @param id
	 *            the fleet number for the new car
	 * @param make
	 *            the make for this new car
	 * @param model
	 *            the model for this new car
	 * @param color
	 *            the color of this new car
	 * 
	 * @throws InvalidIDException
	 *             thrown if the fleet number for the new car is invalid
	 */
	@Override
	public void processNewCar(String id, String make, String model, String color)
			throws InvalidIDException {
		boolean dontAdd = false;
		SimpleStack<Car> temp = new Stack<>();
		SimpleStack<Car> temp2 = new Stack<>();
		// Pop items from stack and place them into temp stack (will be in
		// reverse order) --> Last In, First Out
		while (!available.isEmpty()) {
			// Pop cars from the stack
			Car availCar = available.pop();
			if (availCar.getFleetNum().equals(id)) {
				dontAdd = true;
			}
			// Put last items from available stack as first items into this
			// stack
			temp.push(availCar);
		}
		while (!temp.isEmpty()) {
			// Pop items from the temp stack (last item, was the first time in
			// previous stack
			temp2.push(temp.pop());
			available.push(temp2.pop());
		}
		if (dontAdd)
			throw new InvalidIDException("Duplicate ID for Car");
		// Possible use try/catch for creating new cars
		try {
			Car newCar = new Car(id, make, model, color);
			RentalState avail = new Available();
			newCar.setState(avail);
			addCar(newCar);	
		} catch (InvalidIDException ex) {
			ex.printStackTrace();
			throw new InvalidIDException("Invlaid ID for Car");
		}
	}

	// If program terminated save file into CSV or text file with current
	// inventory
	/**
	 * Method used to save the current inventory from our Car Inventory into a
	 * user specified file that is comma separated for each car parameter from
	 * the inventory
	 * 
	 * @param writer
	 *            used to receive the information and write it to a specified
	 *            file
	 * @throws IOException
	 *             thrown if an error occurs while creating/writing the output file.
	 */
	@Override
	public void writeData(Writer writer) throws IOException {
		// TODO Complete method logic
//		try { 
            //Whatever the file path is. 
//            File filename = new File(//USER INPUT FILE NAME);
//            FileOutputStream output = new FileOutputStream(filename);
//            OutputStreamWriter osw = new OutputStreamWriter(output);    
//            Writer w = new BufferedWriter(osw);
//            w.write("POTATO!!!"); //What you are writing
//            w.close();
//        } catch (IOException e) {
//            System.err.println("Problem writing to the file statsTest.txt");
//        } 

	}

}