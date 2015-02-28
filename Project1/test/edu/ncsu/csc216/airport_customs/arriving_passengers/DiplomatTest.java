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
 * JUnit test case used to test the Diplomat class of our airport simulator.
 * Through this class the abstract methods in passengers are also tested
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (February 18, 2015) 
 */
public class DiplomatTest {

	/** Array representing a group of Customs Desks */
	private CustomsDesk[] desks;

	/** Instance of a Resident passenger */
	private Diplomat one;

	/** Instance of a Resident passenger */
	private Diplomat two;

	/** Instance of a Resident passenger */
	private Diplomat three;

	/** Instance of a Resident passenger */
	private Diplomat four;

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
		one = new Diplomat(13, 30);
		two = new Diplomat(5, 59);
		three = new Diplomat(25, 90);
		four = new Diplomat(20, 60);

		// Create lighter light blue color for Residents
		color = new Color(153, 255, 153);

		// Create/Instantiate Customs desks array
		desks = new CustomsDesk[5];
		for (int i = 0; i < desks.length; i++) {
			desks[i] = new CustomsDesk(log);
		}

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.arriving_passengers.Diplomat#Diplomat(int, int)}
	 * .
	 */
	@Test
	public void testDiplomat() {
		// Create instance of fifth passenger
		Diplomat five = null;

		try {
			// Try creating a passenger with invalid arrival time parameters
			five = new Diplomat(-1, 25);
			fail(); // This line should never be reached
		} catch (IllegalArgumentException e) {
			// Assert this object was not created and is still null
			assertNull(five);
		}
		
		try {
			// Try creating a passenger with invalid process time parameters
			five = new Diplomat(5, -5);
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
		assertEquals(30, one.getProcessTime());

		// Assert the process time for passenger two is 200
		assertEquals(59, two.getProcessTime());

		// Assert the process time for passenger three is 275
		assertEquals(90, three.getProcessTime());

		// Assert the process time for passenger four is 209
		assertEquals(60, four.getProcessTime());

		// Assert that the passenger is not waiting to be processed
		assertEquals(false, one.isWaitingInCustomsLine());

		// Assert that the passenger is not in customs desk so its line index is
		// -1
		assertEquals(-1, one.getLineIndex());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.arriving_passengers.Diplomat#getInLine(edu.ncsu.csc216.airport_customs.queues.CustomsDesk[])}
	 * .
	 */
	@Test
	public void testGetInLine() {
		// Tell passenger one to get in a line
		one.getInLine(desks);
		// Assert that passenger one is in the middle (diplomat) desks
		assertEquals(true, desks[2].hasNext());

		// Tell passenger two to get in a line
		two.getInLine(desks);
		// Assert that passenger two is in the middle (diplomat) desks
		assertEquals(true, desks[2].hasNext());
		
		//Assert the wait time for passenger 1 is 0
		assertEquals(0, one.getWaitTime());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.arriving_passengers.Diplomat#getColor()}
	 * .
	 * 
	 * Test method used to test the color setter/getter for the diplomat
	 * passenger
	 */
	@Test
	public void testGetColor() {
		// Check to see if the halfway point for the process time is correctly
		// calculated
		// Assert that the halfway point for the process time is 210
		assertEquals(60,
				((Diplomat.MIN_PROCESS_TIME + Diplomat.MAX_PROCESS_TIME) / 2));

		// Assert that the color for Resident one is light green
		assertEquals(color, one.getColor());

		// Assert that the color for Resident two is light green
		assertEquals(color, two.getColor());

		// Assert that the color for Resident three is solid green
		assertEquals(Color.GREEN, three.getColor());

		// Assert that the color for Resident four is solid green
		assertEquals(Color.GREEN, four.getColor());
	}

}
