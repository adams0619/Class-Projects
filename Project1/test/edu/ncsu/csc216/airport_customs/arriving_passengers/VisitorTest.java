/**
 * 
 */
package edu.ncsu.csc216.airport_customs.arriving_passengers;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.airport_customs.queues.CustomsDesk;
import edu.ncsu.csc216.airport_customs.simulation.Log;

/**
 * JUnit test case used to test the Visitor class of our airport simulator.
 * Through this class the abstract methods in passengers are also tested
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (February 18, 2015)
 */
public class VisitorTest {

	/** Array representing a group of Customs Desks */
	private CustomsDesk[] desks;

	/** Instance of log class */
	private Log log;

	/** Private instance of a color for the visitor passenger */
	private Color color;

	/** Instance of a Resident passenger */
	private Visitor one;

	/** Instance of a Resident passenger */
	private Visitor two;

	/** Instance of a Resident passenger */
	private Visitor three;

	/** Instance of a Resident passenger */
	private Visitor four;

	/**
	 * Set up method used to help create objects for testing each method in
	 * Visitor class
	 * 
	 * @throws Exception
	 *             general exception can be thrown if a problem is encountered
	 *             during the construction of objects
	 */
	@Before
	public void setUp() throws Exception {
		// Log used by customs desk to log simulator info
		log = new Log();

		// Create lighter light blue color for Residents
		color = new Color(255, 153, 153);

		// Create/Instantiate Customs desks array
		desks = new CustomsDesk[5];
		for (int i = 0; i < desks.length; i++) {
			desks[i] = new CustomsDesk(log);
		}

		// Instantiate all four passengers
		one = new Visitor(13, 209);
		two = new Visitor(5, 200);
		three = new Visitor(25, 390);
		four = new Visitor(20, 480);

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.arriving_passengers.Visitor#Visitor(int, int)}
	 * .
	 * 
	 * Test method used to test the constructor method for our specific
	 * passengers
	 * 
	 * @throws IllegalArgumentException
	 *             if the passed parameters to the constructor are invalid
	 */
	@Test
	public void testVisitor() {
		// Create instance of fifth passenger
		Visitor five = null;

		try {
			// Try creating a passenger with invalid arrival time parameters
			five = new Visitor(-2, 25);
			fail(); // This line should never be reached
		} catch (IllegalArgumentException e) {
			// Assert this object was not created and is still null
			assertNull(five);
		}

		try {
			// Try creating a passenger with invalid process time parameters
			five = new Visitor(15, -25);
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
		assertEquals(390, three.getProcessTime());

		// Assert the process time for passenger four is 209
		assertEquals(480, four.getProcessTime());

		// Assert that the passenger is not waiting to be processed
		assertEquals(false, one.isWaitingInCustomsLine());

		// Assert that the passenger is not in customs desk so its line index is
		// -1
		assertEquals(-1, one.getLineIndex());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.arriving_passengers.Visitor#getInLine(edu.ncsu.csc216.airport_customs.queues.CustomsDesk[])}
	 * .
	 * 
	 * Test method used to test the getInLine for the visitor passenger
	 */
	@Test
	public void testGetInLine() {
		// Tell passenger one to get in a line
		one.getInLine(desks);
		// Assert that is it true that passenger one is in desk with index of
		// middle + 1
		assertEquals(true, desks[3].hasNext());

		// Assert this passenger is waiting in a customs desk line
		assertEquals(true, one.isWaitingInCustomsLine());

		two.getInLine(desks);
		// Assert that passenger two is in desk with the index 0 and that the
		// size for this customs desk is 1
		assertEquals(true, desks[4].hasNext());

		// Assert that desk 3 has a size of
		assertEquals(1, desks[3].size());

		three.getInLine(desks);
		// Assert this passenger is waiting in a customs desk line with index 3
		assertEquals(3, three.getLineIndex());

		// Assert the wait time for passenger 3 is 197
		assertEquals(197, three.getWaitTime());

		// Create array of 7 known passengers
		Passenger[] pass = new Passenger[7];
		ArrivingAirplanes.resetFactory();
		for (int i = 0; i < pass.length; i++) {
			pass[i] = ArrivingAirplanes.generatePassenger();
		}
		// Create 11 customs desks
		CustomsDesk[] cd = new CustomsDesk[11];
		for (int i = 0; i < cd.length; i++) {
			cd[i] = new CustomsDesk(log);
		}
		// Place each passenger in a customs desk line
		for (int i = 0; i < pass.length; i++) {
			pass[i].getInLine(cd);
		}
		// Assert that this resident goes to diplomat line with index 5
		assertEquals(5, pass[1].getLineIndex());

		// Assert that the first visitor goes to line 6 (one greater than
		// diplomat index)
		assertEquals(6, pass[0].getLineIndex());

		// Assert that this visitor goes to line 7
		assertEquals(7, pass[3].getLineIndex());

		// Assert that this visitor goes to line 8
		assertEquals(8, pass[5].getLineIndex());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.arriving_passengers.Visitor#getColor()}
	 * . Test method used to test the color setter/getter for the visitor
	 * passenger
	 */
	@Test
	public void testGetColor() {
		assertEquals(color, color);
		// Check to see if the halfway point for the process time is correctly
		// calculated
		// Assert that the halfway point for the process time is 390
		assertEquals(390,
				((Visitor.MIN_PROCESS_TIME + Visitor.MAX_PROCESS_TIME) / 2));

		// Assert that the color for this visitor one is light blue
		assertEquals(color, one.getColor());

		// Assert that the color for this visitor two is light blue
		assertEquals(color, two.getColor());

		// Assert that the color for this visitor three is solid blue
		assertEquals(Color.RED, three.getColor());

		// Assert that the color for this visitor four is solid blue
		assertEquals(Color.RED, four.getColor());

	}

}
