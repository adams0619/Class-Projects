package edu.ncsu.csc216.cash_register;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * JUnit Test class, used to test to the CashRegister method in the CashRegister
 * Program
 * 
 * @author Adams Ombonga (amombong) [Modified from CSC 216 Staff Code]
 * @version 1.1 (January 30, 2015)
 */
public class CashRegisterTest {

	/**
	 * Object representing a collection of the currency in a cash drawer
	 */
	private CurrencyCollection cashDrawer;

	/**
	 * Object representing the cash register used for transactions
	 */
	private CashRegister register;

	/** Price of baby-doll t-shirt */
	private static final int BABYDOLL_PRICE = 2150;
	/** Price of pencil */
	private static final int PENCIL_PRICE = 136;

	/**
	 * Sets up the CashRegisterTest by creating an objects object representing
	 * the cashDrawer with a total of 10 pieces of each currency. Also creates
	 * the cash register used for processing refunds and purchases.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		cashDrawer = new CurrencyCollection(10);

		register = new CashRegister();
	}

	/**
	 * Test the current balance of the cash register after it's initialized.
	 */
	@Test
	public void testGetCurrentBalance() {
		// Assert that the currency collection cash drawer contains a total of
		// $364.10 or 10 objects of each currency
		assertEquals(36410, cashDrawer.getBalance());

		// Assert that the currency collection cash drawer does not contain more
		// or less than the $364.10 or 10 objects of each currency
		assertNotEquals(30000, cashDrawer.getBalance());

		assertNotEquals(46410, cashDrawer.getBalance());
	}

	/**
	 * This test creates a collection representing a customers payment. Using
	 * this collection we test the processPurchase method; if the item price is
	 * greater than the amount payed throw an exception.
	 */
	@Test
	public void testProcessPurchase() {
		// Create a Currency Collection containing a single 10 dollar bill,
		// representing the customers payment
		CurrencyCollection payment = new CurrencyCollection();
		payment.modifyDenomination(CurrencyCollection.TEN_VALUE, 1);

		try {
			// Try making a purchase where the item price is greater than the
			// payed amount
			register.processPurchase(BABYDOLL_PRICE, payment);
			fail(); // This line should not be run
		} catch (IllegalArgumentException e) {
			// Check to make sure that no money was removed from the cash drawer
			assertEquals(36410, cashDrawer.getBalance());
		}
		// Purchase a pencil using 10 dollars & check refund amount
		assertEquals(864, register.processPurchase(PENCIL_PRICE, payment)
				.getBalance());
	}

	/**
	 * Test uses the processRefund method in order to process the amount of
	 * money refunded to the customer
	 */
	@Test
	public void testProcessRefund() {
		int refundAmount = 1000;
		// Refund a total of $10.00
		assertEquals(1000, register.processRefund(refundAmount).getBalance());

		// Refund a less than $10.00
		assertNotEquals(100, register.processRefund(refundAmount).getBalance());
	}

}
