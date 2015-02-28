/**
 * 
 */
package edu.ncsu.csc216.airport_customs.queues;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.airport_customs.arriving_passengers.ArrivingAirplanes;
import edu.ncsu.csc216.airport_customs.arriving_passengers.Diplomat;
import edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger;
import edu.ncsu.csc216.airport_customs.arriving_passengers.Resident;
import edu.ncsu.csc216.airport_customs.simulation.Log;

/**
 * JUnit test case for the CustsomsDesl Hall
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (February 16, 2015)
 */
public class CustomsDeskTest {

	/** Array representing a group of Customs Desks */
	private CustomsDesk[] desk;

	/** Array of a seven known passengers */
	private Passenger[] passengers;

	/** Instance of log class */
	private Log log;

	/**
	 * Set up method used to help create objects that can be used before testing
	 * each method
	 * 
	 * @throws java.lang.Exception
	 *             general exception can be thrown if a problem is encountered
	 *             during the construction of objects
	 */
	@Before
	public void setUp() throws Exception {
		// Log used by customs desk to log simulator info
		log = new Log();

		// Create seven known passengers
		passengers = new Passenger[7];
		ArrivingAirplanes.resetFactory();
		for (int i = 0; i < passengers.length; i++) {
			passengers[i] = ArrivingAirplanes.generatePassenger();
		}

		// Create/Instantiate Customs desks array
		desk = new CustomsDesk[3];
		for (int i = 0; i < desk.length; i++) {
			desk[i] = new CustomsDesk(log);
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.queues.CustomsDesk#CustomsDesk(edu.ncsu.csc216.airport_customs.simulation.Log)}
	 * .
	 * 
	 * Method used to test the constructor for the customs desk class
	 */
	@Test
	public void testCustomsDesk() {
		// Create new customs desk
		CustomsDesk myDesk = new CustomsDesk(log);

		// Assert that this customs desk is empty
		assertEquals(false, myDesk.hasNext());

		// Assert that this customs desk has a size of 0
		assertEquals(0, myDesk.size());

		// Assert that the next depart time is Integer.MAX_VALUE
		assertEquals(Integer.MAX_VALUE, myDesk.departTimeNext());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.queues.CustomsDesk#addToLine(edu.ncsu.csc216.airport_customs.arriving_passengers.Passenger)}
	 * .
	 * 
	 * Method used to test the add to line method for the customs desk class
	 */
	@Test
	public void testAddToLine() {
		// Create new customs desk
		CustomsDesk myDesk = new CustomsDesk(log);

		// Create 3 new passengers
		Resident one = new Resident(13, 299);
		Resident two = new Resident(5, 200);
		Diplomat three = new Diplomat(25, 90);

		// Assert that no passengers have been added to the customs desk
		assertEquals(0, myDesk.size());

		// Add 3 passengers to the customs desk
		myDesk.addToLine(one);

		// Assert that one passengers has been added to the line
		assertEquals(1, myDesk.size());

		myDesk.addToLine(two);
		myDesk.addToLine(three);

		// Assert that all three passengers have been added to the line
		assertEquals(3, myDesk.size());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.queues.CustomsDesk#hasNext()}.
	 * 
	 * Method used to test the hasNext method for the customs desk class
	 */
	@Test
	public void testHasNext() {
		// Add one of the seven passengers to a customs desk line
		passengers[0].getInLine(desk);

		// Assert that the first passenger entered the visitor line
		assertEquals(true, desk[2].hasNext());

		// Add one of the seven passengers to a customs desk line
		passengers[1].getInLine(desk);

		// Assert that the next passenger entered the diplomat line
		assertEquals(true, desk[2].hasNext());

		// Add one of the seven passengers to a customs desk line
		passengers[2].getInLine(desk);

		// Assert that the next passenger entered the resident line
		assertEquals(true, desk[2].hasNext());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.queues.CustomsDesk#processNext()}.
	 * 
	 * Method used to test the process next method for the customs desk class
	 */
	@Test
	public void testProcessNext() {
		// Add 7 known passengers to a customs desk
		for (int i = 0; i < passengers.length; i++) {
			passengers[i].getInLine(desk);
		}

		// Assert that three passengers have been added to the resident line
		assertEquals(3, desk[0].size());

		// Assert that one passengers has been added to the diplomat line
		assertEquals(1, desk[1].size());

		// Assert that three passengers have been added to the visitor line
		assertEquals(3, desk[2].size());

		// Process all three passengers in the resident line
		desk[0].processNext();
		desk[0].processNext();
		desk[0].processNext();

		// Assert that the resident line is empty
		assertEquals(0, desk[0].size());

		// Assert that 3 passengers have been logged
		assertEquals(3, log.getNumCompleted());

		// Process all three passengers in the visitor line
		desk[2].processNext();
		desk[2].processNext();
		desk[2].processNext();

		// Assert that the visitor line is empty
		assertEquals(0, desk[2].size());

		// Assert that 6 passengers have been logged
		assertEquals(6, log.getNumCompleted());

		// Process all the passenger in the diplomat line
		desk[1].processNext();

		// Assert that the diplomat line is empty
		assertEquals(0, desk[1].size());

		// Assert that 7 passengers have been logged
		assertEquals(7, log.getNumCompleted());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.queues.CustomsDesk#departTimeNext()}
	 * .
	 * 
	 * Method used to test the depart time next method for the customs desk
	 * class
	 */
	@Test
	public void testDepartTimeNext() {
		for (int i = 0; i < desk.length; i++) {
			// Assert that the next depart time is Integer.MAX_VALUE because all
			// the desks are empty
			assertEquals(Integer.MAX_VALUE, desk[i].departTimeNext());
		}

		// Add all seven passengers to a customs desk line
		for (int i = 0; i < passengers.length; i++) {
			passengers[i].getInLine(desk);
		}

		// Assert that depart time next for the first passenger in the first
		// customs desk is 199 --> 2nd residents arrival time + process time
		assertEquals(199, desk[0].departTimeNext());

		// Assert that depart time next for the first passenger in the first
		// customs desk is 201 --> 1st residents arrival time + process time
		assertEquals(201, desk[1].departTimeNext());

		// Assert that depart time next for the first passenger in the first
		// customs desk is 201 --> 1st visitors arrival time + process time
		assertEquals(215, desk[2].departTimeNext());

		// Process the passenger in the diplomat line
		desk[1].processNext();

		// Assert that the next depart time is Integer.MAX_VALUE because the
		// diplomat line is now empty
		assertEquals(Integer.MAX_VALUE, desk[1].departTimeNext());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.airport_customs.queues.CustomsDesk#size()}.
	 * 
	 * Method used to the the size method for the customs desk class
	 */
	@Test
	public void testSize() {
		// Assert that no passengers has been added to the resident line
		assertEquals(0, desk[0].size());

		// Assert that no passengers has been added to the diplomat line
		assertEquals(0, desk[1].size());

		// Assert that no passengers has been added to the visitor line
		assertEquals(0, desk[2].size());

		// Add 7 known passengers to a customs desk
		for (int i = 0; i < passengers.length; i++) {
			passengers[i].getInLine(desk);
		}

		// Assert that three passengers have been added to the resident line
		assertEquals(3, desk[0].size());

		// Assert that one passengers has been added to the diplomat line
		assertEquals(1, desk[1].size());

		// Assert that three passengers have been added to the visitor line
		assertEquals(3, desk[2].size());

	}

}
