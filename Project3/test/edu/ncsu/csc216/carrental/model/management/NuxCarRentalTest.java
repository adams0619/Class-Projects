/**
 * 
 */
package edu.ncsu.csc216.carrental.model.management;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.carrental.model.Car;
import edu.ncsu.csc216.carrental.model.Customer;

/**
 * JUnit test cases for NuxCarRental
 * 
 * @author Noah Unger (ngunger@ncsu.edu)
 *
 */
public class NuxCarRentalTest {
	/**
	 * testNux for testing purposes
	 */
	private NuxCarRental testNux;

	/**
	 * setUp before test cases
	 * 
	 * @throws java.lang.Exception
	 *             general exception can be thrown if a problem is encountered
	 *             during the construction of objects
	 */
	@Before
	public void setUp() throws Exception {
		testNux = new NuxCarRental();
	}

	/**
	 * Test method for NuxCar rental constructor
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#NuxCarRental()}
	 * .
	 * 
	 */
	@Test
	public void testNuxCarRental() {
		testNux = new NuxCarRental(
				new Scanner(
						"Ford,Explorer,Red,F0186,A \n Dodge,Dart,Blue,D1480,R,Hank Peters,23-4850\n Buick,Encore,White,B2267,A\n Ford,Mustang,Black,F4682,D\n Kia,Soul,Green,K0031,S\n Chevrolet,Lightning,White,C0026,R,June Ashe,06-9021\n Andro,Sparkle,Silver,A2011,A\n Cadillac,STX4,Black,G0092,R,Han Solo,40-1341\n Chevrolet,Lightning,Pink,C2041,R,Jeanette Kirk,12-5018"));
		assertTrue(testNux != null);
	}

	/**
	 * Test method for NuxCar Rental constructor with a scanner file
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#NuxCarRental(java.util.Scanner)}
	 * .
	 */
	@Test
	public void testNuxCarRentalScanner() {
		testNux = new NuxCarRental(
				new Scanner(
						"Ford,Explorer,Red,F0186,A \n Dodge,Dart,Blue,D1480,R,Hank Peters,23-4850\n Buick,Encore,White,B2267,A\n Ford,Mustang,Black,F4682,D\n Kia,Soul,Green,K0031,S\n Chevrolet,Lightning,White,C0026,R,June Ashe,06-9021\n Andro,Sparkle,Silver,A2011,A\n Cadillac,STX4,Black,G0092,R,Han Solo,40-1341\n Chevrolet,Lightning,Pink,C2041,R,Jeanette Kirk,12-5018"));
		assertTrue(testNux != null);
	}

	/**
	 * Test method for renting a car in NuxCarRental
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#rentCar()}
	 * .
	 */
	@Test
	public void testRentCar() {
		// Test renting of car
		// Expected should be true
		// Actual is call to hasRentedCars
		Customer testCustomer = new Customer("Test", "Customer", "12-3456");
		Car testCar = new Car("A1234", "Toyota", "Camry", "Red");
		testNux.addCar(testCar);
		testNux.addCustomer(testCustomer);
		testNux.rentCar();
		assertTrue(testNux.hasRentedCars());
	}

	/**
	 * Test method for returning a car in NuxCarRental
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#returnCar()}
	 * .
	 */
	@Test
	public void testReturnCar() {
		// Test renting of car
		// Expected should be false
		// Actual is call to hasRentedCars
		Customer testCustomer = new Customer("Test", "Customer", "12-3456");
		Car testCar = new Car("A1234", "Toyota", "Camry", "Red");
		testNux.addCar(testCar);
		testNux.addCustomer(testCustomer);
		testNux.rentCar();
		testNux.returnCar();
		assertFalse(testNux.hasRentedCars());
	}

	/**
	 * Test method for returning a car with a problem in the NuxCarRental
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#reportProblem()}
	 * .
	 */
	@Test
	public void testReportProblem() {
		// Test renting of car
		// Expected should be true
		// Actual is call to hasRepairingCars
		Customer testCustomer = new Customer("Test", "Customer", "12-3456");
		Car testCar = new Car("A1234", "Toyota", "Camry", "Red");
		testNux.addCar(testCar);
		testNux.addCustomer(testCustomer);
		testNux.rentCar();
		testNux.reportProblem();
		assertTrue(testNux.hasRepairingCars());
	}

	/**
	 * Test method for detailing a car in NuxCarRental
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#completeDetailing()}
	 * .
	 */
	@Test
	public void testCompleteDetailing() {
		// Test renting of car
		// Expected should be true
		// Actual is call to hasAvailableCars
		Customer testCustomer = new Customer("Test", "Customer", "12-3456");
		Car testCar = new Car("A1234", "Toyota", "Camry", "Red");
		testNux.addCar(testCar);
		testNux.addCustomer(testCustomer);
		testNux.rentCar();
		testNux.returnCar();
		testNux.completeDetailing();
		assertTrue(testNux.hasAvailableCars());
	}

	/**
	 * Test method for completing a car repairs
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#completeRepairs()}
	 * .
	 */
	@Test
	public void testCompleteRepairs() {
		// Test renting of car
		// Expected should be true
		// Actual is call to hasDetailingCars
		Customer testCustomer = new Customer("Test", "Customer", "12-3456");
		Car testCar = new Car("A1234", "Toyota", "Camry", "Red");
		testNux.addCar(testCar);
		testNux.addCustomer(testCustomer);
		testNux.rentCar();
		testNux.reportProblem();
		testNux.completeRepairs();
		assertFalse(testNux.hasRepairingCars());
	}

	/**
	 * Test method for printing the cars available for rent
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#availableCars()}
	 * .
	 */
	@Test
	public void testAvailableCars() {
		// Test string of empty available line
		// Expected is ""
		// Actual is call to availableCars
		assertEquals("", testNux.availableCars());

		Car testCar = new Car("A1234", "Toyota", "Camry", "Red");
		testNux.addCar(testCar);
		assertEquals(testNux.availableCars(), testCar.toString() + "\n");
	}

	/**
	 * Test method for printing the cars currently rented
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#rentedCars()}
	 * .
	 */
	@Test
	public void testRentedCars() {
		// Test string of empty rent line
		// Expected is ""
		// Actual is call to rentedCars
		assertEquals("", testNux.rentedCars());

		Car testCar = new Car("A1234", "Toyota", "Camry", "Red");
		testNux.addCar(testCar);
		testNux.addCustomer(new Customer("Test", "Customer", "12-3456"));
		testNux.rentCar();
		assertEquals(testNux.rentedCars(), testCar.toString() + " (T Customer)"
				+ "\n");
	}

	/**
	 * Test method for printing the cars out for detailing
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#detailingCars()}
	 * .
	 */
	@Test
	public void testDetailingCars() {
		// Test string of empty detailing line
		// Expected is ""
		// Actual is call to detailingCars
		assertEquals("", testNux.detailingCars());

		Car testCar = new Car("A1234", "Toyota", "Camry", "Red");
		testNux.addCar(testCar);
		testNux.addCustomer(new Customer("Test", "Customer", "12-3456"));
		testNux.rentCar();
		testNux.returnCar();
		assertEquals(testNux.detailingCars(), testCar.toString() + "\n");
	}

	/**
	 * Test method for printing the cars being repaired
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#repairingCars()}
	 * .
	 */
	@Test
	public void testRepairingCars() {
		// Test string of empty repairing line
		// Expected is ""
		// Actual is call to repairingCars
		assertEquals("", testNux.repairingCars());

		Car testCar = new Car("A1234", "Toyota", "Camry", "Red");
		testNux.addCar(testCar);
		testNux.addCustomer(new Customer("Test", "Customer", "12-3456"));
		testNux.rentCar();
		testNux.reportProblem();
		assertEquals(testNux.repairingCars(), testCar.toString() + "\n");
	}

	/**
	 * Test method for printing the customers available to rent a car
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#customersWaiting()}
	 * .
	 */
	@Test
	public void testCustomersWaiting() {
		// Test string of empty waiting line
		// Expected is ""
		// Actual is call to customersWaiting
		assertEquals("", testNux.customersWaiting());

		Customer testCustomer = new Customer("Test", "Customer", "12-3456");
		Car testCar = new Car("A1234", "Toyota", "Camry", "Red");
		testNux.addCar(testCar);
		testNux.addCustomer(testCustomer);
		assertEquals(testNux.customersWaiting(), testCustomer.toString() + "\n");
	}

	/**
	 * Test method for determining if the available stack of cars is empty
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#hasAvailableCars()}
	 * .
	 */
	@Test
	public void testHasAvailableCars() {
		// Test empty available line
		// Expected is false
		// Actual is call to hasAvailableCars
		assertFalse(testNux.hasAvailableCars());
	}

	/**
	 * Test method for determining if the rented queue of cars is empty
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#hasRentedCars()}
	 * .
	 */
	@Test
	public void testHasRentedCars() {
		// Test empty rented line
		// Expected is false
		// Actual is call to hasRentedCars
		assertFalse(testNux.hasRentedCars());
	}

	/**
	 * Test method for determining if the detailed queue of cars is empty
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#hasDetailingCars()}
	 * .
	 */
	@Test
	public void testHasDetailingCars() {
		// Test empty detailing line
		// Expected is false
		// Actual is call to hasDetailingCars
		assertFalse(testNux.hasDetailingCars());
	}

	/**
	 * Test method for determining if the repair queue of cars is empty
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#hasRepairingCars()}
	 * .
	 */
	@Test
	public void testHasRepairingCars() {
		// Test empty repairing line
		// Expected is false
		// Actual is call to hasRepairingCars
		assertFalse(testNux.hasRepairingCars());
	}

	/**
	 * Test method for determining if the customer queue is empty
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#hasWaitingCustomers()}
	 * .
	 */
	@Test
	public void testHasWaitingCustomers() {
		// Test empty waiting line
		// Expected is false
		// Actual is call to hasWaitingCustomers
		assertFalse(testNux.hasWaitingCustomers());

		Customer testCustomer = new Customer("Test", "Customer", "12-3456");
		testNux.addCustomer(testCustomer);
		assertTrue(testNux.hasWaitingCustomers());
	}

	/**
	 * Test method for adding a customer
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#addCustomer(edu.ncsu.csc216.carrental.model.Customer)}
	 * .
	 */
	@Test
	public void testAddCustomer() {
		// Test adding of null customer
		// Expected is false
		// Actual is call to addCar
		Customer testCustomer = null;
		assertFalse(testNux.addCustomer(testCustomer));

		// Test adding of acceptable customer
		// Expected is true
		// Actual is call to addCar
		testCustomer = new Customer("Test", "Customer", "12-3456");
		assertTrue(testNux.addCustomer(testCustomer));
	}

	/**
	 * Test method for adding a car
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#addCar(edu.ncsu.csc216.carrental.model.Car)}
	 * .
	 */
	@Test
	public void testAddCar() {
		// Test adding of null car
		// Expected should be false
		// Actual is call to addCar
		Car testCar = null;
		assertFalse(testNux.addCar(testCar));

		// Test adding of acceptable car
		// Expected should be true
		// Actual is call to addCar
		Customer testCustomer = new Customer("Test", "Customer", "12-3456");
		Customer testCustomer2 = new Customer("Tes", "Custome", "13-3456");
		Customer testCustomer3 = new Customer("Te", "Custom", "22-3456");
		testCar = new Car("A1234", "Toyota", "Camry", "Red");
		Car testCar2 = new Car("B2345", "Jeep", "Rover", "Blue");
		Car testCar3 = new Car("C2345", "Jeep", "Rover", "Blue");
		Car testCar4 = new Car("D2345", "Jeep", "Rover", "Blue");
		testNux.addCustomer(testCustomer);
		testNux.addCustomer(testCustomer2);
		testNux.addCustomer(testCustomer3);
		testNux.addCar(testCar);
		Car testCar5 = testCar2;
		testNux.addCar(testCar2);
		testNux.addCar(testCar3);
		testNux.addCar(testCar4);
		testNux.rentCar();
		testNux.rentCar();
		testNux.rentCar();
		testNux.returnCar();
		testNux.reportProblem();
		assertFalse(testNux.addCar(testCar5));

	}

	/**
	 * Test method for processing a rental
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#processRental()}
	 * .
	 */
	@Test
	public void testProcessRental() {
		// Test renting of car
		// Expected should be true
		// Actual is call to hasRentedCars
		Customer testCustomer = new Customer("Test", "Customer", "12-3456");
		Car testCar = new Car("A1234", "Toyota", "Camry", "Red");
		testNux.addCar(testCar);
		testNux.addCustomer(testCustomer);
		testNux.rentCar();
		assertTrue(testNux.hasRentedCars());
	}

	/**
	 * Test method for processing a return
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#processReturn(boolean)}
	 * .
	 */
	@Test
	public void testProcessReturn() {
		// Test returning of car
		// Expected should be true
		// Actual is call to hasReturningCars
		Customer testCustomer = new Customer("Test", "Customer", "12-3456");
		Car testCar = new Car("A1234", "Toyota", "Camry", "Red");
		testNux.addCar(testCar);
		testNux.addCustomer(testCustomer);
		testNux.rentCar();
		testNux.returnCar();
		assertTrue(testNux.hasDetailingCars());
	}

	/**
	 * Test method for processing a detailing
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#processDetailed()}
	 * .
	 */
	@Test
	public void testProcessDetailed() {
		// Test detailing of car
		// Expected should be true
		// Actual is call to hasDetailingCars
		Customer testCustomer = new Customer("Test", "Customer", "12-3456");
		Car testCar = new Car("A1234", "Toyota", "Camry", "Red");
		testNux.addCar(testCar);
		testNux.addCustomer(testCustomer);
		testNux.rentCar();
		testNux.returnCar();
		testNux.completeDetailing();
		assertTrue(testNux.hasAvailableCars());
	}

	/**
	 * Test method for processing a repair
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#processRepaired()}
	 * .
	 */
	@Test
	public void testProcessRepaired() {
		// Test returning of car
		// Expected should be true
		// Actual is call to hasAvailableCars
		Customer testCustomer = new Customer("Test", "Customer", "12-3456");
		Car testCar = new Car("A1234", "Toyota", "Camry", "Red");
		testNux.addCar(testCar);
		testNux.addCustomer(testCustomer);
		testNux.rentCar();
		testNux.reportProblem();
		testNux.processRepaired();
		assertFalse(testNux.hasRepairingCars());
	}

	/**
	 * Test method for processing a a new car to be added
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#processNewCar(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testProcessNewCar() {
		// Test renting of car
		// Expected should be true
		// Actual is call to hasAvailableCars
		testNux.processNewCar("A1234", "Toyota", "Camry", "Red");
		assertTrue(testNux.hasAvailableCars());
	}

	/**
	 * Test method for saving the inventory of the program when it id exited
	 * {@link edu.ncsu.csc216.carrental.model.management.NuxCarRental#writeData(java.io.Writer)}
	 * .
	 */
	@Test
	public void testWriteData() {
		Customer testCustomer = new Customer("Test", "Customer", "12-3456");
		Customer testCustomer2 = new Customer("Tes", "Custome", "13-3456");
		Customer testCustomer3 = new Customer("Te", "Custom", "22-3456");
		Car testCar = new Car("A1234", "Toyota", "Camry", "Red");
		Car testCar2 = new Car("B2345", "Jeep", "Rover", "Blue");
		Car testCar3 = new Car("C2345", "Jeep", "Rover", "Blue");
		Car testCar4 = new Car("D2345", "Jeep", "Rover", "Blue");
		testNux.addCustomer(testCustomer);
		testNux.addCustomer(testCustomer2);
		testNux.addCustomer(testCustomer3);
		testNux.addCar(testCar);
		testNux.addCar(testCar2);
		testNux.addCar(testCar3);
		testNux.addCar(testCar4);
		testNux.rentCar();
		testNux.rentCar();
		testNux.rentCar();
		testNux.returnCar();
		testNux.reportProblem();
		try {
			testNux.writeData(new PrintWriter("end.txt"));
		} catch (IOException e) {
			assertFalse(testNux == null);
		}

	}

}
