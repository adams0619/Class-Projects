package edu.ncsu.csc216.airport_customs.arriving_passengers;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.airport_customs.queues.CustomsDesk;
import edu.ncsu.csc216.airport_customs.simulation.Log;

/**
 * JUnit test case used to test the Resident class of our airport simulator.
 * Through this class the abstract methods in passengers are also tested
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (February 18, 2015)
 */
public class ResidentTest {

	/** Array representing a group of Customs Desks */
	private CustomsDesk[] desks;

	/** Instance of a Resident passenger */
	private Resident one;

	/** Instance of a Resident passenger */
	private Resident two;

	/** Instance of a Resident passenger */
	private Resident three;

	/** Instance of a Resident passenger */
	private Resident four;

	/** Instance of log class */
	private Log log;

	/** Private instance of a color for the resident passenger */
	private Color color;

	/**
	 * Set up method used to help create objects for testing each method in
	 * Resident class
	 * 
	 * @throws Exception
	 *             general exception can be thrown if a problem is encountered
	 *             during the construction of objects
	 */
	@Before
	public void setUp() throws Exception {
		// Log used by customs desk to log simulator info
		log = new Log();

		// Instantiate all four passengers
		one = new Resident(13, 209);
		two = new Resident(5, 200);
		three = new Resident(25, 275);
		four = new Resident(20, 210);

		// Create lighter light blue color for Residents
		color = new Color(153, 153, 255);

		// Create/Instantiate Customs desks array
		desks = new CustomsDesk[5];
		for (int i = 0; i < desks.length; i++) {
			desks[i] = new CustomsDesk(log);
		}

	}

	/**
	 * Test method used to test the constructor method for our specific
	 * passengers
	 * 
	 * @throws IllegalArgumentException
	 *             if the passed parameters to the constructor are invalid
	 */
	@Test
	public void testResident() {
		// Create instance of fifth passenger
		Resident five = null;

		try {
			// Try creating a passenger with invalid arrival time parameters
			five = new Resident(-2, 25);
			fail(); // This line should never be reached
		} catch (IllegalArgumentException e) {
			// Assert this object was not created and is still null
			assertNull(five);
		}

		try {
			// Try creating a passenger with invalid process time parameters
			five = new Resident(15, -25);
			fail(); // This line should never be reached
		} catch (IllegalArgumentException e) {
			// Assert this object was not created and is still null
			assertNull(five);
		}

		// Assert the arrival time for passenger one is 13
		assertEquals(13, one.getArrivalTime());

		// Assert the arrival time for passenger two is 5
		assertEquals(5, two.getArrivalTime());

		// Assert the arrival time for passenger three is 25
		assertEquals(25, three.getArrivalTime());

		// Assert the arrival time for passenger four is 20
		assertEquals(20, four.getArrivalTime());

		// Assert the process time for passenger one is 209
		assertEquals(209, one.getProcessTime());

		// Assert the process time for passenger two is 200
		assertEquals(200, two.getProcessTime());

		// Assert the process time for passenger three is 275
		assertEquals(275, three.getProcessTime());

		// Assert the process time for passenger four is 209
		assertEquals(210, four.getProcessTime());

		// Assert that the passenger is not waiting to be processed
		assertEquals(false, one.isWaitingInCustomsLine());

		// Assert that the passenger is not in customs desk so its line index is
		// -1
		assertEquals(-1, one.getLineIndex());
	}

	/**
	 * Test method used to test the getInLine for the resident passenger
	 */
	@Test
	public void testGetInLine() {

		// Tell passenger one to get in a line
		one.getInLine(desks);
		// Assert that is it true that passenger one is in the middle (diplomat)
		// desks
		assertEquals(true, desks[2].hasNext());

		// Assert this passenger is waiting in a customs desk line
		assertEquals(true, one.isWaitingInCustomsLine());

		two.getInLine(desks);
		// Assert that passenger two is in desk with the index 0 and that the
		// size for this customs desk is 1
		assertEquals(true, desks[0].hasNext());
		assertEquals(1, desks[0].size());

		// Assert this passenger is waiting in a customs desk line
		assertEquals(true, two.isWaitingInCustomsLine());

		three.getInLine(desks);
		// Assert that passenger three is in desk with the index 1
		assertEquals(true, desks[1].hasNext());

		// Assert this passenger is waiting in a customs desk line
		assertEquals(1, three.getLineIndex());

		four.getInLine(desks);
		// Assert that this passenger is in desk 0 which has a size of
		// 2
		assertEquals(2, desks[0].size());

		// Assert this passenger is waiting in a customs desk line with index 0
		assertEquals(0, four.getLineIndex());

		//Assert the wait time for passenger 4 is 185
		assertEquals(185, four.getWaitTime());
		
		// Clear out this customs desks line
		desks[2] = new CustomsDesk(log);

		// Assert that the middle (diplomat) customs desks has no passengers
		assertEquals(false, desks[2].hasNext());

		// Create 3 residents and check to see that
		Resident[] res = new Resident[3];
		for (int i = 0; i < res.length; i++) {
			res[i] = new Resident(i + 2, i * 2);
		}

		CustomsDesk[] cd = new CustomsDesk[3];
		for (int i = 0; i < cd.length; i++) {
			cd[i] = new CustomsDesk(log);
		}

		for (int i = 0; i < cd.length; i++) {
			res[i].getInLine(cd);
		}
	}

	/**
	 * Test method used to test the color setter/getter for the resident
	 * passenger
	 */
	@Test
	public void testGetColor() {
		// Check to see if the halfway point for the process time is correctly
		// calculated
		// Assert that the halfway point for the process time is 210
		assertEquals(210,
				((Resident.MIN_PROCESS_TIME + Resident.MAX_PROCESS_TIME) / 2));

		// Assert that the color for Resident one is light blue
		assertEquals(color, one.getColor());

		// Assert that the color for Resident two is light blue
		assertEquals(color, two.getColor());

		// Assert that the color for Resident three is solid blue
		assertEquals(Color.BLUE, three.getColor());

		// Assert that the color for Resident four is solid blue
		assertEquals(Color.BLUE, four.getColor());
	}

}
