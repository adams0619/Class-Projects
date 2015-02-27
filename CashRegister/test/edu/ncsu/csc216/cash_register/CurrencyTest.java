/**
 * 
 */
package edu.ncsu.csc216.cash_register;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit Test class, used to test to the Currency method in the CashRegister
 * Program
 * 
 * @author Adams Ombonga (amombong) [Modified from CSC 216 Staff Code]
 * @version 1.1 (January 30, 2015)
 */
public class CurrencyTest {

	/** Currency object with the value of a penny */
	private Currency penny;
	/** Currency object with the value of a dollar */
	private Currency dollar;
	/** Currency object with the value of 5 dollars */
	private Currency five;
	/** Currency object with the value of 10 dollars */
	private Currency ten;

	/**
	 * Sets up the CurrencyTest by creating 4 representative Currency objects:
	 * one with the value of a penny, one with the value of a dollar, one with
	 * the value of 5 dollars, and the other with the value of 10 dollar.
	 */
	@Before
	public void setUp() throws Exception {
		penny = new Currency(CurrencyCollection.PENNY_VALUE,
				CurrencyCollection.PENNY_NAME, 10);
		dollar = new Currency(CurrencyCollection.ONE_VALUE,
				CurrencyCollection.ONE_NAME, 10);
		five = new Currency(CurrencyCollection.FIVE_VALUE,
				CurrencyCollection.FIVE_NAME, 5);
		ten = new Currency(CurrencyCollection.TEN_VALUE,
				CurrencyCollection.TEN_NAME, 25);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.Currency#getValue()}
	 * .
	 *
	 * The test determines if the value returned from the Currency object is the
	 * same as what the value was initialized to.
	 */
	@Test
	public void testGetValue() {
		// The PENNY_VALUE constant is our expected value
		// The actual value is the call to the getValue() method
		assertEquals(CurrencyCollection.PENNY_VALUE, penny.getValue());

		// The ONE_VALUE constant is our expected value
		// The actual value is the call to the getValue method
		assertEquals(CurrencyCollection.ONE_VALUE, dollar.getValue());

		// The FIVE_VALUE constant is our expected value
		// The actual value is the call to the getValue method
		assertEquals(CurrencyCollection.FIVE_VALUE, five.getValue());

		// The TEN_VALUE constant is our expected value
		// The actual value is the call to the getValue method
		assertEquals(CurrencyCollection.TEN_VALUE, ten.getValue());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.Currency#getName()}.
	 * 
	 * The test determines if the named returned from the Currency object is the
	 * same as what the value was initialized to
	 */
	@Test
	public void testGetName() {
		// The PENNY_NAME constant is our expected value
		// The actual value is the call to the getName() method
		assertEquals(CurrencyCollection.PENNY_NAME, penny.getName());

		// The ONE_NAME constant is our expected value
		// The actual value is the call to the getName() method
		assertEquals(CurrencyCollection.ONE_NAME, dollar.getName());

		// The FIVE_NAME constant is our expected value
		// The actual value is the call to the getName() method
		assertEquals(CurrencyCollection.FIVE_NAME, five.getName());

		// The TEN_NAME constant is our expected value
		// The actual value is the call to the getName() method
		assertEquals(CurrencyCollection.TEN_NAME, ten.getName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.Currency#getCount()}
	 * .
	 * 
	 * The test determines if the count for the Currency object is the same as
	 * what the value was initialized to
	 */
	@Test
	public void testGetCount() {
		// Ensure that Penny was initialized to a count of 10
		// The actual value is the call to the getName() method
		assertEquals(10, penny.getCount());

		// 10 is our expected value for the count
		// The actual value is the call to the getName() method
		assertEquals(10, dollar.getCount());

		// 5 is our expected value for the count
		// The actual value is the call to the getName() method
		assertEquals(5, five.getCount());

		// 25 is our expected value for the count
		// The actual value is the call to the getName() method
		assertEquals(25, ten.getCount());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.cash_register.Currency#modifyCount(int)}.
	 * 
	 * The test uses the getCount() method to understand how the count is
	 * changed when using the modifyCount() method
	 */
	@Test
	public void testModifyCount() {
		// Ensure we're starting with 10 pennies
		// We can't assume that the getCount() method has been tested at this
		// point
		// so this is our sanity check that we can use the getCount() method to
		// evaluate modifyCount() 10 is our expected value and penny.getCount()
		// is
		// our actual value
		assertEquals(10, penny.getCount());

		// Increase the count of pennies by 3
		penny.modifyCount(3);

		// Check that the count changed
		assertEquals(13, penny.getCount());

		// Decrease the count of pennies by 5
		penny.modifyCount(-5);

		// Check that the count changed
		assertEquals(8, penny.getCount());

		try {
			// Decrease the count of pennies by 9
			penny.modifyCount(-9);
			fail(); // We should never reach this point, if we do, the test
					// fails
		} catch (IllegalArgumentException e) {
			// Check that the count did NOT change
			assertEquals(8, penny.getCount());
		}
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.Currency#hashCode()}
	 * Create a new object to test whether hashCodes for the objects are equal
	 */
	@Test
	public void testHashCode() {
		// Create a new penny object that has the same state as our
		// field named penny
		Currency penny2 = new Currency(CurrencyCollection.PENNY_VALUE,
				CurrencyCollection.PENNY_NAME, 10);

		// Assert that both of these objects have the same has code.
		// When using assertTrue, the expected value is true. The actual
		// value is the result of the argument.
		assertTrue(penny.hashCode() == penny2.hashCode());

		// Assert that the penny and dollar objects have different
		// hashcodes.
		assertTrue(penny.hashCode() != dollar.hashCode());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.cash_register.Currency#equals(java.lang.Object)}.
	 * Create a new object to test whether the objects are equal
	 */
	@Test
	public void testEqualsObject() {
		// Create a new penny object that has the same state as our
		// field named penny
		Currency penny2 = new Currency(CurrencyCollection.PENNY_VALUE,
				CurrencyCollection.PENNY_NAME, 10);

		// Assert that both of these objects are the same using the
		// equals method.
		// When using assertTrue, the expected value is true. The actual
		// value is the result of the argument.
		assertTrue(penny.equals(penny2));

		// Assert that the penny and dollar objects are not the same.
		// When using assertFalse, the expected value is false. The actual
		// value is the result of the argument.
		assertFalse(penny.equals(dollar));
	}

}
