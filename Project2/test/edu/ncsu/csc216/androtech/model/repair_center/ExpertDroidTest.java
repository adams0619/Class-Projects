package edu.ncsu.csc216.androtech.model.repair_center;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Method used to test the ExpertDroid class for our program through various
 * strategies
 * 
 * @author Adams Ombonga (amombong)
 * @version 1.0 (March 25, 2015)
 *
 */
public class ExpertDroidTest {
	
	/** Private COM Devices use for testing */
	private ExpertDroid one;

	/**
	 * Set up method used to create objects for testing this class
	 * 
	 * @throws java.lang.Exception
	 *             general exception can be thrown if a problem is encountered
	 *             during the construction of objects
	 */
	@Before
	public void setUp() throws Exception {
		one = new ExpertDroid();
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.androtech.model.repair_center.ExpertDroid#ExpertDroid()}.
	 */
	@Test
	public void testExpertDroid() {
		// Assert this is a Expert droid
		assertTrue(one instanceof ExpertDroid);
	}

}
