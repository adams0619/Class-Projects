/**
 * 
 */
package edu.ncsu.csc216.airport_customs.queues;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.airport_customs.arriving_passengers.ArrivingAirplanes;
import edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger;
import edu.ncsu.csc216.airport_customs.simulation.Log;

/**
 * JUnit test class used to unit test the methods from the Arrival Hall class
 * from the airport simulator program
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (February 16, 2015)
 *
 */
public class ArrivalHallTest {
	/** Array representing a group of Passengers */
	private Passenger[] passengers;

	/** Array representing a group of Customs Desks */
	private CustomsDesk[] desks;

	/** Variable representing the Arrival Hall */
	private ArrivalHall arrivalHall;
	
	/**
	 * Set up method used to help create objects for testing each method in
	 * Arrival Hall
	 * 
	 * @throws java.lang.Exception
	 *             general exception can be thrown if a problem is encountered
	 *             during the construction of objects
	 */
	@Before
	public void setUp() throws Exception {
		//Create a log used by the customs desk/arrival Hall
		Log log = new Log();

		// Generate passengers using a seed of 10
		passengers = new Passenger[10];
		for (int i = 0; i < 10; i++) {
			ArrivingAirplanes.resetFactory();
			passengers[i] = ArrivingAirplanes.generatePassenger();
		}

		desks = new CustomsDesk[5];
		for (int i = 0; i < desks.length; i++) {
			desks[i] = new CustomsDesk(log);
		}

		arrivalHall = new ArrivalHall(passengers.length, desks);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.queues.ArrivalHall#ArrivalHall()}
	 * 
	 * 
	 * Test method used to test the constructor method used for creating the
	 * Arrival Hall.
	 * 
	 */
	@Test
	public void testArrivalHall() {
		// Create a new array if passengers and generate 10 passengers using a
		// random seed of 10
		Passenger newPassengers[] = new Passenger[10];
		for (int i = 0; i < newPassengers.length; i++) {
			ArrivingAirplanes.resetFactory();
			newPassengers[i] = ArrivingAirplanes.generatePassenger();
		}
		// Create new array of customs desks
		Log newLog = new Log();
		CustomsDesk[] newDesks = new CustomsDesk[5];
		for (int i = 0; i < newDesks.length; i++) {
			newDesks[i] = new CustomsDesk(newLog);
		}
		// Create second arrival hall
		ArrivalHall secondArrivalHall = new ArrivalHall(newPassengers.length,
				newDesks);

		// Assert this second arrival hall is the same as the first arrival hall
		// by comparing the departure times for each upcoming passenger
		for (int i = 0; i < passengers.length; i++) {
			assertEquals(arrivalHall.departTimeNext(),
					secondArrivalHall.departTimeNext());
		}

		// Assert that the first 

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.queues.ArrivalHall#size()}
	 * 
	 * Test method used to test the size or number of passengers still in a
	 * queue
	 * 
	 */
	@Test
	public void testSize() {
		// Assert the queue for arrival hall has 10 passengers
		assertEquals(10, arrivalHall.size());

		// Process 1 passenger
		arrivalHall.processNext();
		// Assert the line has now has passengers
		assertEquals(9, arrivalHall.size());

		// Process the remaining passengers
		for (int i = 0; i < passengers.length - 1; i++) {
			arrivalHall.processNext();
		}

		// Assert the line for arrival hall does not have 10 passengers
		assertNotEquals(10, arrivalHall.size());

		// Assert the line for the arrival has not passengers
		assertEquals(0, arrivalHall.size());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.queues.ArrivalHall#hasNext()}.
	 * 
	 * Test method used to determine if the queue for the arrival hall is empty
	 * or not
	 */
	@Test
	public void testHasNext() {
		// Assert the queue is still empty
		assertEquals(true, arrivalHall.hasNext());

		// Remove one passenger
		arrivalHall.processNext();
		// Assert the line or queue for the arrival hall is still not empty
		assertEquals(true, arrivalHall.hasNext());

		// Process the remaining passengers in the arrival hall queue
		for (int i = 0; i < passengers.length - 1; i++) {
			arrivalHall.processNext();
		}
		// Assert the line if now empty, all the passengers have been processed
		assertEquals(false, arrivalHall.hasNext());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.queues.ArrivalHall#processNext()}.
	 * 
	 * Test method used to test if the next passenger is processed by comparing
	 * the known processed passengers value to those provided by the method
	 */
	@Test
	public void testProcessNext() {
		// Process one passenger/test size & log items
		// Save the processed passenger to
		Passenger frontPass = arrivalHall.processNext();

		// Assert there are 9 passengers after 1 was previously processed
		assertEquals(9, arrivalHall.size());
		// Assert the previously processed passenger does not equal the next
		// processed passenger
		assertNotEquals(frontPass, arrivalHall.processNext());

		// Assert there are now 8 passengers after 2 were previously processed
		assertEquals(8, arrivalHall.size());
		// Process remaining passengers
		for (int i = 0; i < 8; i++) {
			arrivalHall.processNext();
		}

		// Assert the arrival hall has no passengers since they have all been
		// processed
		assertEquals(0, arrivalHall.size());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.queues.ArrivalHall#departTimeNext()}
	 * .
	 * 
	 * Test method used to test the depart time next method by comparing the
	 * departure times of processed passengers to their known values
	 */
	@Test
	public void testDepartTimeNext() {
		// Create new arrival hall with 0 passengers
		ArrivalHall newArrivalHall = new ArrivalHall(0, desks);

		// Create new arrival hall with 0 passengers
		ArrivalHall copyArrival = arrivalHall;
		
		// Create new arrival hall with 0 passengers
		ArrivingAirplanes.resetFactory();
		ArrivalHall myArrivalHall = new ArrivalHall(7, desks);

		// Assert that the nextDepartTime is Integer.MAX since this arrival hall
		// has no passengers
		assertEquals(Integer.MAX_VALUE, newArrivalHall.departTimeNext());

		// Assert the first passengers arrival time is the next departure time
		assertEquals(copyArrival.departTimeNext(), arrivalHall.departTimeNext());

		// Assert the arrival time for this passenger is 11
		Passenger p2 = myArrivalHall.processNext();
		assertEquals(11, p2.getArrivalTime());

		// fail("Not yet implemented");

	}

}
