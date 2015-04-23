package edu.ncsu.csc216.carrental.util;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.carrental.model.Car;

/**
 * Test class used to test the Queue class in our car rental program
 * 
 * @author Adams Ombonga (amombong)
 * @author Noah Unger (ngunger@ncsu.edu)
 * @version 1.0 (April 19, 2015)
 *
 */
public class QueueTest {

	/** Private reference to a Queue used for testing */
	private SimpleQueue<Car> q;

	/**
	 * Set up method used to create queues and other objects for testing various
	 * methods
	 * 
	 * @throws Exception
	 *             general exception can be thrown if a problem is encountered
	 *             during the construction of objects
	 */
	@Before
	public void setUp() throws Exception {
		q = new Queue<>();
	}

	/**
	 * Test method used to test queue
	 */
	@Test
	public void testQueue() {
		// Assert that this is in fact a queue object
		assertTrue(q instanceof Queue<?>);
	}

	/**
	 * Test cases for add method
	 */
	@Test
	public void testAdd() {
		Car testCar = null;
		q.add(testCar);
		q.add(testCar);
		assertFalse(q.isEmpty());
	}

	/**
	 * Test cases for remove method
	 */
	@Test
	public void testRemove() {
		try {
			q.remove();
			fail("Should be excepted");
		} catch (NoSuchElementException e) {
			assertTrue(q.isEmpty());
		}

		Car testCar = null;
		q.add(testCar);
		assertEquals(testCar, q.remove());
	}

	/**
	 * Test cases for peek method
	 */
	@Test
	public void testPeek() {
		try {
			q.peek();
			fail("Should be excepted");
		} catch (NoSuchElementException e) {
			assertTrue(q.isEmpty());
		}

		Car testCar = null;
		q.add(testCar);
		assertEquals(testCar, q.peek());
	}

	/**
	 * Test cases for isEmpty method
	 */
	@Test
	public void testIsEmpty() {
		assertTrue(q.isEmpty());

		Car testCar = null;
		q.add(testCar);
		assertFalse(q.isEmpty());
	}

}
