/**
 * 
 */
package edu.ncsu.csc216.cash_register;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test class,used to test the CurrencyCollection Class in the
 * CashRegister program
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.1 (January 30, 2015)
 */
public class CurrencyCollectionTest {
	/**
	 * Object containing a collection of the currency
	 */
	private CurrencyCollection paymentCurrency;

	/**
	 * Object representing the various types cash used for transactions
	 */
	private CurrencyCollection register;

	/**
	 * Object representing the various types cash used for transactions
	 */
	private CurrencyCollection copyRegister;

	/**
	 * Object representing the various types cash used for transactions
	 */
	private CurrencyCollection emptyRegister;

	/** Array storing all currency in the collection */
	private Currency[] currency = new Currency[CurrencyCollection.NUM_SLOTS];

	/**
	 * setUp method used to set up currency collection for the register and
	 * another collection for the payment.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Collection of currency in payment collection
		paymentCurrency = new CurrencyCollection(10);
		// Register for the Currency Collection
		register = new CurrencyCollection(10);

		// Create a two new register objects. One has the same amount of
		// currency as the original register and the other has a different
		// no currency
		copyRegister = new CurrencyCollection(10);
		emptyRegister = new CurrencyCollection();

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.cash_register.CurrencyCollection#getCurrencyAtIdx(int)}
	 * 
	 * This test determines if their is a currency object within the index of
	 * the register currency collection. If there is not currency object or the
	 * index is out of bounds then throw an IndexOutOfBounds Exception
	 */
	@Test
	public void testGetCurrencyAtIdx() {

		// Use a loop to test all the currency items in their respective
		// indexes
		for (int i = 0; i < CurrencyCollection.NUM_SLOTS; i++) {
			assertEquals(register.getCurrencyAtIdx(i),
					paymentCurrency.getCurrencyAtIdx(i));
		}

		try {
			// Test for a currency that is not within the collection index
			// Assert that the two currencies in the index are the same
			assertEquals(register.getCurrencyAtIdx(7),
					paymentCurrency.getCurrencyAtIdx(8));
			fail(); // This line should never run
		} catch (IndexOutOfBoundsException e) {
			// Test to make sure that currency at index was not changed
			assertEquals(register.getCurrencyAtIdx(7),
					paymentCurrency.getCurrencyAtIdx(7));
		}

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.cash_register.CurrencyCollection#modifyDenomination(int, int)}
	 * 
	 * Test modify Denomination method and determine whether the count for the
	 * corresponding collections are equal.
	 */
	@Test
	public void testModifyDenomination() {

		// Increase denomination count by 2
		paymentCurrency.modifyDenomination(CurrencyCollection.TEN_VALUE, 2);
		// Assert that the count for the $10 is not the same for the
		// register currency collection or the payment currency collection
		assertNotEquals(register.getCurrencyAtIdx(6).getCount(),
				paymentCurrency.getCurrencyAtIdx(6).getCount());

		try {
			// Try modifying the denomination count to a negative number
			paymentCurrency.modifyDenomination(CurrencyCollection.PENNY_VALUE,
					-12);
			fail(); // This line should never run
		} catch (IllegalArgumentException e) {
			// Assert that the count was not changed
			assertEquals(register.getCurrencyAtIdx(0).getCount(),
					paymentCurrency.getCurrencyAtIdx(0).getCount());
		}

		try {
			// Try modifying the denomination count for an invalid value
			paymentCurrency.modifyDenomination(2, 2);
			fail(); // This line should never run
		} catch (IllegalArgumentException e) {
			// Assert that the count was not changed
			assertEquals(register.getCurrencyAtIdx(0).getCount(),
					paymentCurrency.getCurrencyAtIdx(0).getCount());
		}

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.cash_register.CurrencyCollection#depositCurrencyCollection(edu.ncsu.csc216.cash_register.CurrencyCollection)}
	 * 
	 * This test determines whether the currency collection was deposited to the
	 * register.
	 */
	@Test
	public void testDepositCurrencyCollection() {
		// Create collection of currency with total of 3641
		CurrencyCollection custPayment = new CurrencyCollection(1);
		// Deposit customer payment to register currency collection
		register.depositCurrencyCollection(custPayment);

		// Assert that the new balance of currency collection is 40051
		assertEquals(40051, register.getBalance());

		// Assert that the new balance is not equal to the initial balance
		// (36410)
		assertNotEquals(36410, register.getBalance());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.cash_register.CurrencyCollection#refundByAmount(int)}
	 * 
	 * This test method determines refund amount and whether or not a refund can
	 * be placed based on the current amount of currency in the register's
	 * collection
	 */
	@Test
	public void testRefundByAmount() {
		// Create collection with 40051 in the currency collection
		CurrencyCollection custPayment = new CurrencyCollection(11);

		try {
			// Try to refund more money than what's in the register
			register.refundByAmount(custPayment.getBalance());
			fail(); // This line should never run
		} catch (IllegalArgumentException e) {
			// Assert that the register currency balance is unchanged
			assertEquals(36410, register.getBalance());
		}

		// Refund the entire register collection
		register.refundByAmount(register.getBalance());
		// Assert that the register balance is now 0
		assertEquals(0, register.getBalance());

		// Assert that the register balance does not equal 36410
		assertNotEquals(36410, register.getBalance());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.cash_register.CurrencyCollection#getCurrencyCollection()}
	 * 
	 * Test the currency collection and determine if it is equal to the default
	 * initialized currency collection.
	 */
	@Test
	public void testGetCurrencyCollection() {
		// Create array of currency/initialize them
		int initialCount = 10;
		currency[0] = new Currency(CurrencyCollection.PENNY_VALUE,
				CurrencyCollection.PENNY_NAME, initialCount);
		currency[1] = new Currency(CurrencyCollection.NICKEL_VALUE,
				CurrencyCollection.NICKEL_NAME, initialCount);
		currency[2] = new Currency(CurrencyCollection.DIME_VALUE,
				CurrencyCollection.DIME_NAME, initialCount);
		currency[3] = new Currency(CurrencyCollection.QUARTER_VALUE,
				CurrencyCollection.QUARTER_NAME, initialCount);
		currency[4] = new Currency(CurrencyCollection.ONE_VALUE,
				CurrencyCollection.ONE_NAME, initialCount);
		currency[5] = new Currency(CurrencyCollection.FIVE_VALUE,
				CurrencyCollection.FIVE_NAME, initialCount);
		currency[6] = new Currency(CurrencyCollection.TEN_VALUE,
				CurrencyCollection.TEN_NAME, initialCount);
		currency[7] = new Currency(CurrencyCollection.TWENTY_VALUE,
				CurrencyCollection.TWENTY_NAME, initialCount);
		// Check each currency at the given index to determine if they are in
		// fact the default initialized values
		for (int i = 0; i < currency.length; i++) {
			// Assert the currency at the given index is equal to the same
			// currency from the currency array created above
			assertEquals(currency[i],
					paymentCurrency.getCurrencyCollection()[i]);
		}
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.cash_register.CurrencyCollection#getBalance()}.
	 * 
	 * Test balance of Currency Collection before and after modifying the count
	 * for the currency within the collection
	 */
	@Test
	public void testGetBalance() {
		// Ensure that we start with a currency collection that has a count of
		// 10 for each bill/coin giving a total of $364.10
		assertEquals(36410, paymentCurrency.getBalance());

		// Modify the currency count for the $10 and $20 bills
		paymentCurrency.modifyDenomination(CurrencyCollection.TEN_VALUE, 1);
		paymentCurrency.modifyDenomination(CurrencyCollection.TWENTY_VALUE, 2);

		// Assert that the new balance is equal to 41410
		assertEquals(41410, paymentCurrency.getBalance());

		// Assert that the new balance does not equal the initial balance
		assertNotEquals(36410, paymentCurrency.getBalance());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.cash_register.Currency#hashCode()}
	 * Test whether hashCodes for the register objects are equal
	 */
	@Test
	public void testHashCode() {
		// Assert that both of these objects have the same hashcode.
		// When using assertTrue, the expected value is true. The actual
		// value is the result of the argument.
		assertTrue(register.hashCode() == copyRegister.hashCode());

		// Assert that the full register and empty register objects have
		// different
		// hashcodes.
		assertTrue(register.hashCode() != emptyRegister.hashCode());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.cash_register.Currency#equals(java.lang.Object)}.
	 * Test whether the different register objects are equal
	 */
	@Test
	public void testEqualsObject() {
		// Assert that both of these objects are the same using the
		// equals method.
		// When using assertTrue, the expected value is true. The actual
		// value is the result of the argument.
		assertTrue(register.equals(copyRegister));

		// Assert that the two register objects have different currency values.
		// When using assertFalse, the expected value is false. The actual
		// value is the result of the argument.
		assertFalse(register.equals(emptyRegister));
	}

}
