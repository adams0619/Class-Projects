/**
 * 
 */
package edu.ncsu.csc216.carrental.util;

import static org.junit.Assert.*;

import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.carrental.model.Car;

/**
 * JUnit test cases for Stack class
 * 
 * @author Noah Unger (ngunger@ncsu.edu)
 * @author Adams Ombonga (amombong)
 *
 */
public class StackTest {
	/**
	 * Car testCar used for testing purposes
	 */
	private Car testCar;

	/**
	 * setUp before tests
	 * 
	 * @throws java.lang.Exception
	 *             general exception can be thrown if a problem is encountered
	 *             during the construction of objects
	 */
	@Before
	public void setUp() throws Exception {
		testCar = new Car("A1234", "Toyota", "Camry", "Red");
	}

	/**
	 * Test method for constructor
	 * {@link edu.ncsu.csc216.carrental.util.Stack#Stack()}.
	 */
	@Test
	public void testStack() {
		// Test for construction of an empty stack
		// Expected is null
		// testStack.peek()
		Stack<Car> testStack = new Stack<Car>();
		assertTrue(testStack.isEmpty());
	}

	/**
	 * Test method for is empty
	 * {@link edu.ncsu.csc216.carrental.util.Stack#isEmpty()}.
	 */
	@Test
	public void testIsEmpty() {
		// Test for construction of an empty stack
		// Expected is null
		// Actual is testStack.peek()
		Stack<Car> testStack = new Stack<Car>();
		assertTrue(testStack.isEmpty());
	}

	/**
	 * Test method for peek{@link edu.ncsu.csc216.carrental.util.Stack#peek()}.
	 */
	@Test
	public void testPeek() {
		// Test for peek of empty stack
		// Expected is exception
		// Exception should be caught and stack should be empty
		Stack<Car> testStack = new Stack<Car>();
		try {
			testStack.peek();
			fail("Should be excepted");
		} catch (EmptyStackException e) {
			assertTrue(testStack.isEmpty());
		}

		// Test for peek of stack with an element
		// Expected is testCar
		// Actual is testStack.peek()
		testStack.push(testCar);
		assertEquals(testCar, testStack.peek());

	}

	/**
	 * Test method for popping from stack
	 * {@link edu.ncsu.csc216.carrental.util.Stack#pop()}.
	 */
	@Test
	public void testPop() {
		// Test for pop of empty stack
		// Expected is exception
		// Exception should be caught and stack should be empty
		Stack<Car> testStack = new Stack<Car>();
		try {
			testStack.pop();
			fail("Should be excepted");
		} catch (EmptyStackException e) {
			assertTrue(testStack.isEmpty());
		}

		// Test for pop of stack with an element
		// Expected is testCar
		// Actual is testStack.pop()
		testStack.push(testCar);
		assertEquals(testCar, testStack.pop());
	}

	/**
	 * Test method for pusing to stack
	 * {@link edu.ncsu.csc216.carrental.util.Stack#push(java.lang.Object)}.
	 */
	@Test
	public void testPush() {
		// Test for push of stack with an element
		// Expected is testCar
		// Actual is testStack.peek()
		Stack<Car> testStack = new Stack<Car>();
		testStack.push(testCar);
		assertEquals(testCar, testStack.peek());
	}

}
