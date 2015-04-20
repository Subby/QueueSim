package test;

import org.junit.Before;
import org.junit.Test;
import Model.Person;
import Model.ShortOfTimeCustomer;
import static org.junit.Assert.*;

public class ShortOfTimeCustomerTest {
	
	private Person customer;
	
	@Before 
	public void setUp() {
		customer = new ShortOfTimeCustomer();
	}
	
	@Test
	public void testIncrementTimeSpentQueueing() {
		for (int i = 0; i < 3; i++) {
			((ShortOfTimeCustomer) customer).incrementTimeSpentQueueing();
		}
		assertEquals(3, ((ShortOfTimeCustomer) customer).getTimeSpentQueueing());
	}
	
	@Test
	public void testGetTimeSpentQueueing() {
		for (int i = 0; i < 2; i++) {
			((ShortOfTimeCustomer) customer).incrementTimeSpentQueueing();
		}
		assertEquals(2, ((ShortOfTimeCustomer) customer).getTimeSpentQueueing());
	}
	
	@Test 
	public void testQueuedForTooLong() {
		for (int i = 0; i < 61; i++) {
			((ShortOfTimeCustomer) customer).incrementTimeSpentQueueing();
		}
		assertTrue(((ShortOfTimeCustomer) customer).queuedForTooLong());
	}
	
	@Test
	public void testGetPatienceLimit() {
		//PATIENCE_LIMIT is a final, static class variable set to 60
		assertEquals(60, ((ShortOfTimeCustomer) customer).getPatienceLimit());
	}
}
