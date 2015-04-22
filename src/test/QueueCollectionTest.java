package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import Model.PersonQueue;
import Model.QueueCollection;
import Model.Queue;
import Model.Person;
import Model.Customer;

public class QueueCollectionTest {
	
	private QueueCollection queues;
	private Queue queue;
	private Queue queue1;
	
	@Before
	public void setUp() {
		queues = new QueueCollection();
		queue = new PersonQueue();
		queue1 = new PersonQueue();
	}
	
	@Test
	public void testGetQueues() {
		queues.addQueue(queue);
		queues.addQueue(queue1);
		ArrayList<Queue> expected = new ArrayList<Queue>(Arrays.asList(queue, queue1));
		ArrayList<Queue> actual = queues.getQueues();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testShowShortestQueue() {
		queues.addQueue(queue);
		queues.addQueue(queue1);
		for (int i = 0; i < 4; i++) {
			Person person = new Customer();
			queue.addPerson(person);
		}
		for (int i = 0; i < 8; i++) {
			Person person = new Customer();
			queue1.addPerson(person);
		}
		assertSame(queue, queues.showShortestQueue());
	}
	
	@Test
	public void testAddQueue() {
		queues.addQueue(queue);
		assertEquals(1, queues.getQueues().size());
	}
}
