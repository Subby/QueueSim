package test;

import org.junit.Before;
import org.junit.Test;

import Model.Person;
import Model.Customer;
import static org.junit.Assert.*;

public class CustomerTest {

	private Person customer;
	
	@Before
	public void setUp() {
		customer = new Customer();
	}
	
	@Test
	public void testSetServeTime() {
		assertTrue(18 <= customer.getServeTime() && 30 >= customer.getServeTime());
	}
	
	@Test
	public void testChangeServeTime() {
		((Customer) customer).setServeTime(55);
		assertEquals(55, customer.getServeTime());
	}
	
	@Test
	public void getServeTime() {
		((Customer) customer).setServeTime(35);
		assertEquals(35, customer.getServeTime());
		
	}
}
