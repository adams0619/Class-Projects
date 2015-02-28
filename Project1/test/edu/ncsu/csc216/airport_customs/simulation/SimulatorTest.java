/**
 * 
 */
package edu.ncsu.csc216.airport_customs.simulation;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.airport_customs.arriving_passengers.ArrivingAirplanes;
import edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger;
import edu.ncsu.csc216.airport_customs.queues.CustomsDesk;

/**
 * JUnit test class used to test the methods from the Simulator class of our
 * airport simulator.
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (February 19, 2015)
 */
public class SimulatorTest {

	/** Array representing a group of Customs Desks */
	private CustomsDesk[] desks;

	/** Instance of log class */
	private Log log;

	/** Array of a seven known passengers */
	private Passenger[] passengers;

	/** Instance of a simulator */
	private Simulator simulator;

	/**
	 * Set up method used to help create objects for testing each method in
	 * Simulator class
	 * 
	 * @throws Exception
	 *             general exception can be thrown if a problem is encountered
	 *             during the construction of objects
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
		desks = new CustomsDesk[5];
		for (int i = 0; i < desks.length; i++) {
			desks[i] = new CustomsDesk(log);
		}

		// Create a simulator using 7 passengers and the desks array
		simulator = new Simulator(desks.length, passengers.length);

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.simulation.Simulator#Simulator(int, int)}
	 * .
	 * 
	 * Method used to test the constructor for the simulator class
	 * 
	 * @throws IllegalArgumentException
	 *             if the number of passengers or desks is invalid
	 */
	@Test
	public void testSimulator() {
		// Create a simulator using 10 passengers and 5 desks
		Simulator customSim = new Simulator(5, 10);

		// Assert that the total number of steps for this simulator is 20
		assertEquals(20, customSim.totalNumberOfSteps());

		// Assert that no steps have been taken so there are more steps
		assertEquals(true, customSim.moreSteps());

		try {
			// Try creating a simulator with an invalid number of passengers
			customSim = null;
			customSim = new Simulator(4, 0);
			fail(); // This line should not run
		} catch (IllegalArgumentException e) {
			// Assert that the customSim simulator is is null, case
			// IllegalArgumentException
			assertNull(customSim);
		}

		try {
			// Try creating a simulator with an invalid number of customs desks
			customSim = null;
			customSim = new Simulator(1, 15);
			fail(); // This line should not run
		} catch (IllegalArgumentException e) {
			// Assert that the customSim simulator is is null, case
			// IllegalArgumentException
			assertNull(customSim);
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.simulation.Simulator#getStepsTaken()}
	 * .
	 * 
	 * Test method used to determine how many steps have been take so far in the
	 * simulation
	 */
	@Test
	public void testGetStepsTaken() {
		// Assert that the number of steps taken is the number of passengers
		// (half the total # of steps)s
		for (int i = 1; i <= (passengers.length / 2); i++) {
			simulator.step();
			assertEquals(i, simulator.getStepsTaken());
		}
		// Assert that the total number of steps taken is the number of
		// passengers
		// divided by 2
		assertEquals(3, simulator.getStepsTaken());

		// Complete running the remainder of the steps
		for (int i = 1; i <= ((passengers.length / 2) + 1); i++) {
			simulator.step();
		}
		// Assert that the total number of steps taken is the number of
		// passengers
		assertEquals(7, simulator.getStepsTaken());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.simulation.Simulator#totalNumberOfSteps()}
	 * .
	 * 
	 * Test method used to determine how many steps have been take in total for
	 * the duration of the simulation
	 */
	@Test
	public void testTotalNumberOfSteps() {
		// Assert that the number of steps taken is the number of passengers
		// (half the total # of steps)s
		simulator = new Simulator(3, 7);
		for (int i = 1; i < (passengers.length * 2); i++) {
			simulator.step();
		}

		// Assert that the total number of steps taken is the number of
		// passengers multiplied by 2
		assertEquals((passengers.length * 2), simulator.totalNumberOfSteps());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.simulation.Simulator#step()}.
	 * 
	 * Test method used to determine how steps have been take in total for the
	 * duration of the simulation
	 */
	@Test
	public void testStep() {
		// Simulate a single step in the simulator
		simulator.step();

		// Assert that only one step has been taken
		assertEquals(1, simulator.getStepsTaken());

		// Assert false that this passenger hasn't cleared customs and is not in
		// a customs desk
		assertEquals(false, simulator.passengerClearedCustoms());

		// Simulate a 2 more steps in the simulator
		simulator.step();
		simulator.step();

		// Assert false that this passenger hasn't cleared customs and is not in
		// a customs desk
		assertEquals(false, simulator.passengerClearedCustoms());

		// Assert that 3 steps have been taken
		assertEquals(3, simulator.getStepsTaken());

		// Complete running the remainder of the steps until you reach the
		// passengers length
		for (int i = 1; i <= ((passengers.length / 2) + 1); i++) {
			simulator.step();
		}

		// Assert true that there are still more steps to go through in the
		// simulation
		assertEquals(true, simulator.moreSteps());

		// Assert that the total number of steps taken so far is the number of
		// passengers
		assertEquals(7, simulator.getStepsTaken());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.simulation.Simulator#moreSteps()}.
	 * 
	 * Test method used to determine if there are still how steps have been take
	 * in total for the duration of the simulation
	 */
	@Test
	public void testMoreSteps() {
		// Create a simulator using 7 passengers and the desks array
		simulator = new Simulator(desks.length, passengers.length);
		
		// Simulate a single step in the simulator
		simulator.step();

		// Assert true that there are still more steps to go through in the
		// simulation
		assertEquals(true, simulator.moreSteps());

		// Simulate a 4 more steps in the simulator
		simulator.step();
		simulator.step();
		simulator.step();
		simulator.step();

		// Assert true that there are still more steps to go through in the
		// simulation
		assertEquals(true, simulator.moreSteps());

		// Complete running the remained of the steps
		for (int i = 1; i <= (passengers.length + 2); i++) {
			simulator.step();
		}

		// Assert true that there are still more steps to go through in the
		// simulation
		assertEquals(false, simulator.moreSteps());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.simulation.Simulator#getCurrentIndex()}
	 * .
	 * 
	 * Method used to determine the line index for the current passenger in the
	 * simulation
	 */
	@Test
	public void testGetCurrentIndex() {
		// Create simulator with our 7 known passengers
		ArrivingAirplanes.resetFactory();
		simulator = new Simulator(3, 7);

		// Assert that no steps have been taken so the line index is -1
		assertEquals(-1, simulator.getCurrentIndex());

		// Step through the simulator once
		simulator.step();
		// Assert that the line index for the first passenger is 2
		assertEquals(2, simulator.getCurrentIndex());

		// Step through the simulator twice
		simulator.step();

		// Assert that the line index for the second passenger is 1
		assertEquals(1, simulator.getCurrentIndex());

		// Step through the simulator once
		simulator.step();

		// Assert that the line index for the first passenger is 0
		assertEquals(0, simulator.getCurrentIndex());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.simulation.Simulator#getCurrentPassengerColor()}
	 * .
	 * 
	 * Method used to test the getCurrentPassengerColor method in the simulator
	 * class
	 */
	@Test
	public void testGetCurrentPassengerColor() {
		// Create simulator with our 7 known passengers
		ArrivingAirplanes.resetFactory();
		simulator = new Simulator(3, 7);

		// Assert that the color is null since no passengers has been processed
		assertEquals(null, simulator.getCurrentPassengerColor());

		Color resColor = new Color(153, 153, 255); // Blue
		Color visColor = new Color(255, 153, 153); // Red

		// Step through the simulation once
		simulator.step();

		// Assert the first passengers color is pink
		assertEquals(visColor, simulator.getCurrentPassengerColor());

		// Step through the simulation twice
		simulator.step();
		simulator.step();

		// Assert the first passengers color is light blue
		assertEquals(resColor, simulator.getCurrentPassengerColor());

		// Step through the simulation three times
		simulator.step();
		simulator.step();
		simulator.step();

		// Assert the first passengers color is RED
		assertEquals(Color.RED, simulator.getCurrentPassengerColor());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.simulation.Simulator#passengerClearedCustoms()}
	 * .
	 */
	@Test
	public void testPassengerClearedCustoms() {
		// fail("Not yet implemented");
		// Create Simulator with 2 passengers and 3 customs desk
		Simulator customSim = new Simulator(3, 2);
		// Assert that there is no current passenger so the passengers cleared
		// customs flag should be false
		assertFalse(customSim.passengerClearedCustoms());

		// Step through the simulation three times
		customSim.step();
		customSim.step();
		customSim.step();
		// Assert that the first passenger has cleared customs
		assertEquals(true, customSim.passengerClearedCustoms());

		// Step through simulation
		customSim.step();

		// Assert that the second passenger has cleared customs
		assertEquals(true, customSim.passengerClearedCustoms());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.simulation.Simulator#averageWaitTime()}
	 * 
	 * 
	 * Method used to test the average wait time method from the simulator
	 * class
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
		assertEquals(3.059, customSim.averageWaitTime(), .001);

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.simulation.Simulator#averageProcessTime()}
	 *
	 * 
	 * Method used to test the average process time method from the simulator
	 * class
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
