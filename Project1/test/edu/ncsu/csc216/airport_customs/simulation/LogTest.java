/**
 * 
 */
package edu.ncsu.csc216.airport_customs.simulation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.airport_customs.arriving_passengers.ArrivingAirplanes;
import edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger;
import edu.ncsu.csc216.airport_customs.arriving_passengers.Resident;
import edu.ncsu.csc216.airport_customs.queues.CustomsDesk;

/**
 * JUnit test class used to test the methods from the Log class of our airport
 * simulator.
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (February 19, 2015)
 */
public class LogTest {
	/** Array representing a group of Customs Desks */
	private CustomsDesk[] desks;

	/** Instance of log class */
	private Log log;

	/** Array of a seven known passengers */
	private Passenger[] passengers;

	/**
	 * Set up method used to help create objects for testing each method in
	 * Simulator class
	 * 
	 * @throws Exception
	 *             if there are errors while setting up unit test
	 *
	 */
	@Before
	public void setUp() throws Exception {
		// Log used by customs desk to log simulator info
		log = new Log();

		// Create seven known passengers
		ArrivingAirplanes.resetFactory();
		passengers = new Passenger[7];
		for (int i = 0; i < passengers.length; i++) {
			passengers[i] = ArrivingAirplanes.generatePassenger();
		}

		// Create/Instantiate Customs desks array
		desks = new CustomsDesk[3];
		for (int i = 0; i < desks.length; i++) {
			desks[i] = new CustomsDesk(log);
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.simulation.Log#Log()}.
	 * 
	 * Method used to test the constructor for the log class
	 */
	@Test
	public void testLog() {
		Log newLog = new Log();
		// Create a new resident passenger and log their information
		Resident p = new Resident(10, 35);
		newLog.logItem(p);
		// Assert that only one passenger has been logged to show that the log
		// was constructed properly
		assertEquals(1, newLog.getNumCompleted());

		// Create a customs desk with the log to show it was constructed
		// properly
		CustomsDesk d1 = new CustomsDesk(newLog);

		// Assert the customs desk was created properly
		assertNotNull(d1);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.simulation.Log#getNumCompleted()}.
	 * 
 	 * Method used to test the getNumCompleted method for the log class
	 */
	@Test
	public void testGetNumCompleted() {
		// Create a single customs desk and 3 visitor passengers
		log = new Log();
		CustomsDesk myDesk = new CustomsDesk(log);
		Resident one = new Resident(13, 209);
		Resident two = new Resident(5, 200);
		Resident three = new Resident(25, 275);

		// Add 3 passengers to the customs desk
		myDesk.addToLine(one);
		myDesk.addToLine(two);
		myDesk.addToLine(three);

		// Process all three passengers
		myDesk.processNext();
		myDesk.processNext();
		myDesk.processNext();

		// Assert that 3 passengers have been logged yet
		assertEquals(3, log.getNumCompleted());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.simulation.Log#logItem(edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger)}
	 * 
	 * Method used to test the log item method for the log class
	 * 
	 */
	@Test
	public void testLogItem() {
		// Place 7 known passengers into their corresponding customs desks lines
		for (int i = 0; i < passengers.length; i++) {
			passengers[i].getInLine(desks);
		}

		// Assert that no one has been processed yet
		assertEquals(0, log.getNumCompleted());

		// Assert that no one has been processed yet
		assertEquals(0.0, log.averageWaitTime(), 0.001);

		// Assert that no one has been processed yet
		assertEquals(0.0, log.averageProcessTime(), 0.001);

		// Process first passenger in the desk with index 0
		desks[0].processNext();

		// Assert that one passenger has been processed in desk with index 0
		assertEquals(1, log.getNumCompleted());

		// Assert that the average wait time is 0 since only one passenger has
		// been processed
		assertEquals(0, log.averageWaitTime(), 0.001);

		// Process remaining passengers in desk with index 0
		for (int i = 0; i < 2; i++) {
			desks[0].processNext();
		}

		// Assert that one passenger has been processed in desk with index 0
		assertEquals(3, log.getNumCompleted());
		// Create a single customs desk and 3 visitor passengers
		log = new Log();
		CustomsDesk myDesk = new CustomsDesk(log);
		Resident one = new Resident(13, 209);
		Resident two = new Resident(5, 200);
		Resident three = new Resident(25, 275);

		// Add 3 passengers to the customs desk
		myDesk.addToLine(one);
		myDesk.addToLine(two);
		myDesk.addToLine(three);

		// Process all three passengers
		myDesk.processNext();
		myDesk.processNext();
		myDesk.processNext();

		// Assert the average wait time to be
		assertEquals(3.411, log.averageWaitTime(), 0.001);

		// Assert the average wait time to be
		assertEquals(3.80, log.averageProcessTime(), 0.001);

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.simulation.Log#averageWaitTime()}.
	 * 
	 * Method used to test the average wait time method for the log class
	 */
	@Test
	public void testAverageWaitTime() {
		// Create a simulator using 7 known passengers and 3 desks
		ArrivingAirplanes.resetFactory();
		Simulator customSim = new Simulator(3, 7);
		for (int i = 0; i < 14; i++) {
			customSim.step();
		}
		// Assert that the average wait time is 3.059 with an error of .001
		assertEquals(3.05, customSim.averageWaitTime(), .01);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.simulation.Log#averageProcessTime()}
	 * .
	 * 
	 * Method used to test the average process time method for the log class
	 */
	@Test
	public void testAverageProcessTime() {
		// Create a simulator using 7 known passengers and 3 desks
		ArrivingAirplanes.resetFactory();
		Simulator customSim = new Simulator(3, 7);
		for (int i = 0; i < 14; i++) {
			customSim.step();
		}
		// Assert that the average wait time is 3.059 with an error of .001
		assertEquals(4.521, customSim.averageProcessTime(), .001);
	}

}
