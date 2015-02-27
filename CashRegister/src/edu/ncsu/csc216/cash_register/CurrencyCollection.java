package edu.ncsu.csc216.cash_register;

import java.util.Arrays;

/**
 * A collection of currency for US dollars
 * 
 * @author SarahHeckman [Modified by Adams Ombonga (amombong)]
 * @version 1.1 (January 30, 2015)
 */
public class CurrencyCollection {

	/** Penny name */
	public static final String PENNY_NAME = "Penny";
	/** Nickel name */
	public static final String NICKEL_NAME = "Nickel";
	/** Dime name */
	public static final String DIME_NAME = "Dime";
	/** Quarter name */
	public static final String QUARTER_NAME = "Quarter";
	/** One Dollar name */
	public static final String ONE_NAME = "One Dollar";
	/** Five Dollars name */
	public static final String FIVE_NAME = "Five Dollars";
	/** Ten Dollars name */
	public static final String TEN_NAME = "Ten Dollars";
	/** Twenty Dollars name */
	public static final String TWENTY_NAME = "Twenty Dollars";
	
	/** Penny value */
	public static final int PENNY_VALUE = 1;
	/** Nickel value */
	public static final int NICKEL_VALUE = 5;	
	/** Dime value */
	public static final int DIME_VALUE = 10;
	/** Quarter value */
	public static final int QUARTER_VALUE = 25;
	/** One Dollar value */
	public static final int ONE_VALUE = 100;
	/** Five Dollars value */
	public static final int FIVE_VALUE = 500;
	/** Ten Dollars value */
	public static final int TEN_VALUE = 1000;
	/** Twenty Dollars value */
	public static final int TWENTY_VALUE = 2000;
	
	/** Initial count of currency in the collection */
	private static final int INITIAL_COUNT = 0;
	/** Number of currency slots in the collection */
	public static final int NUM_SLOTS = 8;
	
	/** Array storing all currency in the collection */
	private Currency[] currency = new Currency[NUM_SLOTS];

	/** 
	 * Constructs the currency collection with default values
	 */
	public CurrencyCollection() {
		this(INITIAL_COUNT);
	}
	
	/**
	 * Constructs the currency collection with a default
	 * number of bill/coins as provided by the parameter
	 * @param initialCount the initial number of each bills/coins 
	 * in the currency collection
	 */
	public CurrencyCollection(int initialCount) {
		currency[0] = new Currency(PENNY_VALUE, PENNY_NAME, initialCount);
		currency[1] = new Currency(NICKEL_VALUE, NICKEL_NAME, initialCount);
		currency[2] = new Currency(DIME_VALUE, DIME_NAME, initialCount);
		currency[3] = new Currency(QUARTER_VALUE, QUARTER_NAME, initialCount);
		currency[4] = new Currency(ONE_VALUE, ONE_NAME, initialCount);
		currency[5] = new Currency(FIVE_VALUE, FIVE_NAME, initialCount);
		currency[6] = new Currency(TEN_VALUE, TEN_NAME, initialCount);
		currency[7] = new Currency(TWENTY_VALUE, TWENTY_NAME, initialCount);
	}
	
	/**
	 * Returns the Currency at the given index.
	 * @param idx index in currency collection
	 * @return Currency at given index.
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 */
	public Currency getCurrencyAtIdx(int idx) {
		if (idx < 0 || idx >= NUM_SLOTS) {
			throw new IndexOutOfBoundsException();
		}
		return currency[idx];
	}
	
	/**
	 * Adds the number of a denomination to the currency slot that matches
	 * the given value.  If the count is negative or the value doesn't match
	 * a value in the CurrencyCollection, an IllegalArgumentException is 
	 * thrown.
	 * @param value the monetary value to increase
	 * @param count the count of denomination
	 * @throws IllegalArgumentException if the value is invalid.
	 */
	public void modifyDenomination(int value, int count) {
		boolean valueNotValid = false;
		// Check to ensure that the value is within the currency collection
		for (int i = 0; i < currency.length; i++) {
			if (value == currency[i].getValue()) {
				valueNotValid = false;
				break; //Exit loop if value is within currency collection
			} else {
				valueNotValid = true;
			}
		}	
		if (valueNotValid && count < 0) {
				throw new IllegalArgumentException(); //invalid value
		}	
		
		boolean added = false;
		for (int i = 0; i < NUM_SLOTS; i++) {
			if (currency[i].getValue() == value) {
				currency[i].modifyCount(count);
				added = true;
				break;
			}
		}
		if (!added) {
			throw new IllegalArgumentException(); //invalid value
		}
	}
	
	/**
	 * Adds the currency in the payment to the current CurrencyCollection.
	 * @param deposit amount to add
	 */
	public void depositCurrencyCollection(CurrencyCollection deposit) {
		for (int i = 0; i < NUM_SLOTS; i++) {
			currency[i].modifyCount(deposit.getCurrencyAtIdx(i).getCount());
		}
	}
	
	/**
	 * Returns a CurrencyCollection containing the bills and coins that make
	 * up the refunded amount if available in the current CurrencyCollection.
	 * An IllegalArgumentException is thrown if there is not exact change.
	 * @param refund amount to refund
	 * @return CurrencyCollection with the refunded currency
	 * @throws IllegalArgumentException if there is not exact change in the current
	 * CurrencyCollection.
	 */
	public CurrencyCollection refundByAmount(int refund) {
		int amountLeft = refund;
		CurrencyCollection refundCollection = new CurrencyCollection();
		for (int i = NUM_SLOTS - 1; i >= 0; i--) {
			Currency c = currency[i];
			if (amountLeft < c.getValue()) {
				continue; //no need to check this denomination
			} 
			int numDenom = amountLeft / c.getValue(); //max possible bills/coins of this denomination
			int min = Math.min(c.getCount(), numDenom); //how many bills/coins can we move
			refundCollection.modifyDenomination(c.getValue(), min); //add all that we can to refundCollection
			c.modifyCount(-min); //reduce current Currency by refunded amount, use negative number for coins/bills
			amountLeft -= c.getValue() * min; //modify the amount left
		}
		if (amountLeft != 0) {
			//put refunded currency back into the current currency collection
			depositCurrencyCollection(refundCollection);
			throw new IllegalArgumentException();
		}
		return refundCollection;
	}

	/**
	 * Returns the currency collection
	 * @return the currency collection
	 */
	public Currency[] getCurrencyCollection() {
		return currency;
	}

	/**
	 * Returns the balance.  Since we are representing physical 
	 * whole coins, we're storing the amount as an int.  Divide by
	 * 100.00 to see the double version of the currency collection.
	 * @return the balance.
	 */
	public int getBalance() {
		int balance = 0;
		for (int i = 0; i < currency.length; i++) {
			balance += currency[i].getCount() * currency[i].getValue();
		}
		return balance;
	}

	/**
	 * Returns the hash code for the current object
	 * @return the hash code for the current object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(currency);
		return result;
	}

	/**
	 * Returns true if the given object is the same as the current object.
	 * @param obj object to compare with current object
	 * @return true if the given object is the same as the current object
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurrencyCollection other = (CurrencyCollection) obj;
		if (!Arrays.equals(currency, other.currency))
			return false;
		return true;
	}
	
	
}