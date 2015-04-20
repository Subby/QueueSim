package Model;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestPersonQueue {
	
	private Queue queue;
	private Customer customer; 
	
	@Before
	public void setUp() {
		queue = new PersonQueue();
		customer =  new Customer();
	}
	
	@Test
	public void testAddPerson(Person person) {
		LinkedList<Person> actual = new LinkedList<Person>(Arrays.asList(customer));
		LinkedList<Person> expected = queue.getQueue();
		assertEquals(true, actual.equals(expected));	
	}
	
	@Test
	public void testGetQueue() {
		
	}


}
