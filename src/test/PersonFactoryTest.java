package test;

import static org.junit.Assert.*;

import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import Model.Customer;
import Model.Person;
import Model.PersonFactory;
import Model.PersonQueue;

public class PersonFactoryTest {
	
	private PersonFactory factory;
	private PersonQueue queue;
	
	@Before
	public void setUp() {
		factory = new PersonFactory();
		queue = new PersonQueue();
	}
	
	@Test
	/**
	 * TODO: The test below is a poor way of testing randomness; I couldn't think of anything else
	 * apart from just leaving the test for this and trusting the libraries to do their thing. 
	 * What do you guys think?
	 */
	//public void testGeneratePerson() {
		//for (int i = 0; i < 5; i++) {
			//Person person = factory.generatePerson();
			//if (person != null) {
				//queue.addPerson(person);
			//}
		//}
		//assertTrue(1 <= queue.getLength());
	//}

}
