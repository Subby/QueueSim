package test;

import org.junit.Before;
import org.junit.Test;

import Model.ComplainingCustomer;
import Model.Person;
import static org.junit.Assert.*;

public class ComplainingCustomerTest {
	
	private Person customer;
	
	@Before
	public void setUp() {
		customer = new ComplainingCustomer();
	}
	
	@Test
	public void testIncrementTimeSpentQueueing() {
		((ComplainingCustomer) customer).incrementTimeSpentQueueing();
		((ComplainingCustomer) customer).incrementTimeSpentQueueing();
		assertEquals(2, ((ComplainingCustomer) customer).getTimeSpentQueueing());	
	}
	
	@Test
	public void testGetTimeSpentQueueing() {
		for (int i = 0; i < 4; i++) {
			((ComplainingCustomer) customer).incrementTimeSpentQueueing();
		}		
		assertEquals(4, ((ComplainingCustomer) customer).getTimeSpentQueueing());
	}
	
	@Test
	public void testQueuedForTooLong() {
		for (int i = 0; i <59; i++) {
			((ComplainingCustomer) customer).incrementTimeSpentQueueing();
		}
		assertEquals(true, ((ComplainingCustomer) customer).queuedForTooLong());

	}
	
	@Test
	public void testDoubleServedTime() {
		for (int i = 0; i <59; i++) {
			((ComplainingCustomer) customer).incrementTimeSpentQueueing();
		}
		int serveTimeDoubled = customer.getServeTime() * 2;
		((ComplainingCustomer) customer).doubleServeTime();
		assertEquals(serveTimeDoubled, customer.getServeTime());
	}
	
	@Test
	public void testGetPatienceLimit() {
		//ComplainingCustomer class has a static, final PATIENCE_LIMIT value of 48
		assertEquals(48, ((ComplainingCustomer) customer).getPatienceLimit());
	}
}
