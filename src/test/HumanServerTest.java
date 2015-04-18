package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import Model.Customer;
import Model.HumanServer;
import Model.Person;
import Model.Server;

public class HumanServerTest {
	
	private Server server;
	private Person customer;
	private Person customer1;
	
	@Before 
	public void setUp() {
		server = new HumanServer();
		customer = new Customer();
		customer1 = new Customer();
	}
	
	@Test
	public void testFinishWithCustomer() {
		server.setCurrentCustomer(customer);
		server.finishWithCustomer();
		assertEquals(null, server.getCurrentCustomer());
		assertEquals(true, server.isFree());
		assertEquals(0, server.getTimeSpentServing());
	}
	
	@Test
	public void testServeCustomer() {
		server.setCurrentCustomer(customer);
		server.serveCustomer();
		assertEquals(1, server.getTimeSpentServing());
	}
}
