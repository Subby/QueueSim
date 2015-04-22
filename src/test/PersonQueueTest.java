package test;

import java.util.Arrays;
import java.util.LinkedList;

import model.ComplainingCustomer;
import model.Customer;
import model.Person;
import model.PersonQueue;
import model.Queue;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class PersonQueueTest {
	
	private Queue queue;
	private Person customer;
	private Person customer1;
	
	@Before
	public void setUp() {
		queue = new PersonQueue();
		customer = new Customer();
		customer = new ComplainingCustomer();
	}
	
	@Test
	public void testAddPerson() {
		queue.addPerson(customer);
		assertEquals(1, queue.getLength());
	}
	
	@Test
	public void testGetQueue() {
		queue.addPerson(customer);
		queue.addPerson(customer1);
		LinkedList<Person> actual = queue.getQueue();
		LinkedList<Person> expected = new LinkedList<Person>(Arrays.asList(customer, customer1));
		assertEquals(expected, actual);
	}
	
	@Test
	public void testRemovePerson() {
		queue.addPerson(customer1);
		assertEquals(1, queue.getLength());
		queue.removePerson(customer1);
		assertEquals(0, queue.getLength());
	}
	
	@Test
	public void testGetLength() {
		assertEquals(0, queue.getLength());
		queue.addPerson(customer);
		queue.addPerson(customer1);
		assertEquals(2, queue.getLength());
	}
	
	@Test
	public void testRemoveHeadOfQueue() {
		queue.addPerson(customer);
		queue.addPerson(customer1);
		queue.removeHeadOfQueue();
		assertEquals(1, queue.getLength());
		Person newHeadOfQueue = customer1;
		assertSame(newHeadOfQueue, queue.getHeadOfQueue());
	}
	
	@Test 
	public void testGetHeadOfQueue() {
		queue.addPerson(customer1);
		assertSame(customer1, queue.getHeadOfQueue());
	}

}
