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
		// Empty constructor do nothing b/c stacks/queues have already be initialized
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
		
			try {
				//Create a car and place it into it's corresponding list
				if (status != null) {
					if (status.equalsIgnoreCase("R")) {
						if (first != null && last != null) {
							Customer c = new Customer(first, last, id);
							RentalState r = new Rented(c);
							Car newcar = new Car(fleet, make, model, color);
							newcar.setState(r);
							if (findDuplicates(newcar)) {
								continue;
							}
							rented.add(newcar);
						}	
					} else if (status.equalsIgnoreCase("A")) {
						RentalState a = new Available();
						Car newcar = new Car(fleet, make, model, color);
						newcar.setState(a);
						if (findDuplicates(newcar)) {
							continue;
						}
						available.push(newcar);
					} else if (status.equalsIgnoreCase("D")) {
						RentalState d = new OutForDetail();
						Car newcar = new Car(fleet, make, model, color);
						newcar.setState(d);
						if (findDuplicates(newcar)) {
							continue;
						}
						detailShop.add(newcar);
					} else if (status.equalsIgnoreCase("S")) {
						RentalState s = new OutForRepair();
						Car newcar = new Car(fleet, make, model, color);
						newcar.setState(s);
						if (findDuplicates(newcar)) {
							continue;
						}
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
		available.peek().getState().rentCar(this);
	}
	
	/**
	 * Method used to process the return of a car with no problems.
	 */
	@Override
	public void returnCar() {
		//Set parameter to false b/c no problems
		rented.peek().getState().returnCar(this);
	}

	/**
	 * Method used to process the return of a car with problems.
	 */
	@Override
	public void reportProblem() {
		//Set parameter to true b/c problems were reported
		rented.peek().getState().reportProblem(this);
	}

	/**
	 * Method used to remove the first car from the detail queue and place it
	 * onto the available stack because detailing is complete
	 */
	@Override
	public void completeDetailing() {
		detailShop.peek().getState().detailDone(this);
	}

	/**
	 * Method used to remove the first car from the repair queue and place it
	 * onto the detailing queue because repairs have been completed
	 */
	@Override
	public void completeRepairs() {
		repairShop.peek().getState().repairDone(this);
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
	 * rented.
	 * 
	 * @return String of cars which are on a rental
	 */
	@Override
	public String rentedCars() {
		String rentedString = "";
		SimpleQueue<Car> temp = new Queue<>();
		while (!rented.isEmpty()) {
			Car dCar = rented.remove();
			rentedString += dCar.toString() + " " + dCar.getState().rentalInfo() + "\n";
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
			detailShop.add(temp.remove());
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
			customerString += current.toString() + "\n";
			temp.add(current);
		}
		while (!temp.isEmpty()) {
			customers.add(temp.remove());
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
	 * queue. 
	 * 
	 * @param c
	 *            Customer that you are attempting to add
	 * @return true if customer was added, false otherwise
	 */
	@Override
	public boolean addCustomer(Customer c) {
		//No customer means no need to add customer
		if (null == c) {
			return false;
		}
		Queue<Customer> tempCustomer = new Queue<Customer>();
		boolean addCustomer = true;
		while (!customers.isEmpty()) {
			if (customers.peek().equals(c))
				addCustomer = false;
			tempCustomer.add(customers.remove());
		}
		while (!tempCustomer.isEmpty())
			customers.add(tempCustomer.remove());
		Queue<Car> tempCar = new Queue<Car>();
		while (!rented.isEmpty()) {
			Rented r = (Rented) rented.peek().getState();
			if (r.getCustomer().equals(c))
				addCustomer = false;
			tempCar.add(rented.remove());
		}
		while (!tempCar.isEmpty())
			rented.add(tempCar.remove());
		if (addCustomer)
			customers.add(c);
		return addCustomer;
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
				if (findDuplicates(c)) {
					return false;
				} else {
					RentalState avail = new Available();
					c.setState(avail);
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
	public void processNewCar(String id, String make, String model, String color) {
		// Possible use try/catch for creating new cars
		try {
			Car newCar = new Car(id, make, model, color);
			addCar(newCar);	
		} catch (InvalidIDException ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Private inner method used to determine if the
	 * 
	 * @param c
	 *            the car containing the information to be compared
	 * @return true if a duplicate is found, false otherwise
	 */
	private boolean findDuplicates(Car c) {
		boolean dontAdd = false;
		SimpleStack<Car> temp = new Stack<>();
		SimpleQueue<Car> tempRented = new Queue<>();
		SimpleQueue<Car> tempRepair = new Queue<>();
		SimpleQueue<Car> tempDetail = new Queue<>();

		///CHECK AVAILABLE STACK
		while (!available.isEmpty()) {
			// Pop cars from the stack
			Car availCar = available.pop();
			if (availCar.getFleetNum().equals(c.getFleetNum())) {
				dontAdd = true;
			}
			// Put last items from available stack as first items into this
			// stack
			temp.push(availCar);
		}
		while (!temp.isEmpty()) {
			// Pop items from the temp stack (last item, was the first time in
			// previous stack
			tempRented.add(temp.pop());
			available.push(tempRented.remove());
		}
		//CHECK RENTED QUEUE
		while (!rented.isEmpty()) {
			Car availCar = rented.remove();
			if (availCar.getFleetNum().equals(c.getFleetNum())) {
				dontAdd = true;
			}
			tempRented.add(availCar);
		}
		while (!tempRented.isEmpty()) {
			rented.add((Car) tempRented.remove());
		}
		//CHECK REPAIR SHOP QUEUE
		while (!repairShop.isEmpty()) {
			Car availCar = repairShop.remove();
			if (availCar.getFleetNum().equals(c.getFleetNum())) {
				dontAdd = true;
			}
			tempRepair.add(availCar);
		}
		while (!tempRepair.isEmpty()) {
			repairShop.add((Car) tempRepair.remove());
		}
		//CHECK DETAIL SHOP QUEUE
		while (!detailShop.isEmpty()) {
			Car availCar = detailShop.remove();
			if (availCar.getFleetNum().equals(c.getFleetNum())) {
				dontAdd = true;
			}
			tempDetail.add(availCar);
		}
		while (!tempDetail.isEmpty()) {
			detailShop.add((Car) tempDetail.remove());
		}
		return dontAdd;
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
		String printFile = "";
		//CHECK AVAILABLE STACK
		while (!available.isEmpty()) {
			// Pop cars from the stack
			Car car = available.pop();
			printFile += car.getMake() + ",";
			printFile += car.getModel() + ",";
			printFile += car.getColor() + ",";
			printFile += car.getFleetNum() + ",";
			printFile += "A" + "\n";
		}
		//CHECK RENTED QUEUE
		while (!rented.isEmpty()) {
			// Pop cars from the stack
			Car car = rented.remove();
			printFile += car.getMake() + ",";
			printFile += car.getModel() + ",";
			printFile += car.getColor() + ",";
			printFile += car.getFleetNum() + ",";
			printFile += "R" + ",";
			Rented state = (Rented) car.getState();
			printFile += state.getCustomer().getFirstName() + " " + state.getCustomer().getLastName() + ",";
			printFile += state.getCustomer().getId() + "\n";
		}
		//CHECK REPAIR SHOP QUEUE
		while (!repairShop.isEmpty()) {
			Car car = repairShop.remove();
			printFile += car.getMake() + ",";
			printFile += car.getModel() + ",";
			printFile += car.getColor() + ",";
			printFile += car.getFleetNum() + ",";
			printFile += "S" + "\n";
		}
		//CHECK DETAIL SHOP QUEUE
		while (!detailShop.isEmpty()) {
			Car car = detailShop.remove();
			printFile += car.getMake() + ",";
			printFile += car.getModel() + ",";
			printFile += car.getColor() + ",";
			printFile += car.getFleetNum() + ",";
			printFile += "D" + "\n";
		}
		if (writer != null) {
			writer.write(printFile);
			writer.close();
		} else {
			throw new IOException();
		}
	}

}