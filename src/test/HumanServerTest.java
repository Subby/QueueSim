/**
 * Tests the HumanServer class. 
 * Getter & Setter tests are combined in some cases where the tests
 * where the same to DRY out the code. 
 * @author Ben Lawton
 */

package test;

import static org.junit.Assert.assertEquals;

import model.Customer;
import model.HumanServer;
import model.Person;
import model.PersonQueue;
import model.Queue;
import model.Server;

import org.junit.Before;
import org.junit.Test;

public class HumanServerTest {
	
	private Server server;
	private Person customer;
	private Person customer1;
	private Queue queue;
	private Queue queue1;
	
	@Before 
	public void setUp() {
		server = new HumanServer();
		customer = new Customer();
		customer1 = new Customer();
		queue = new PersonQueue();
		queue1 = new PersonQueue();
	}
	
	@Test
	public void testFinishWithCustomer() {
		server.setCurrentCustomer(customer);
		server.finishWithCustomer();
		assertEquals(null, server.getCurrentCustomer());
		assertEquals(true, server.showAvailability());
		assertEquals(0, server.getTimeSpentServing());
	}
	
	@Test
	public void testServeCustomer() {
		server.setCurrentCustomer(customer);
		server.serveCustomer();
		assertEquals(1, server.getTimeSpentServing());
	}
	
	@Test
	public void testShowAvailability() {
		server.setFree(true);
		server.setCurrentCustomer(null);
		assertEquals(true, server.showAvailability());
		server.setFree(false);
		server.setCurrentCustomer(customer);
		assertEquals(false, server.showAvailability());
	}

	@Test
	public void testCurrentCustomer() {
		server.setCurrentCustomer(customer1);
		assertEquals(customer1, server.getCurrentCustomer());
		server.setCurrentCustomer(customer);
		assertEquals(customer, server.getCurrentCustomer());
	}
	
	@Test
	public void testIsFree() {
		server.setFree(false);
		assertEquals(false, server.isFree());
		server.setFree(true);
		assertEquals(true, server.isFree());
	}
	
	@Test
	public void testGetTimeSpentServing() {
		server.setCurrentCustomer(customer);
		server.serveCustomer();
		server.serveCustomer();
		assertEquals(2, server.getTimeSpentServing());
	}
	
	@Test
	public void testAllocatedQueue() {
		server.setAllocatedQueue(queue);
		assertEquals(queue, server.getAllocatedQueue());
		server.setAllocatedQueue(queue1);
		assertEquals(queue1, server.getAllocatedQueue());
	}
}


