package model;
/**
 * Class that handles the generation of customers based on
 * their probability of being generated
 * @author Ben Lawton
 */

import java.util.Random;


public final class PersonFactory {
	
	private Random rand;
	
	public PersonFactory() {
		rand = new Random(42);
	}
	
	/**
	 * Generates a new person if the probability matches their likelihood of appearing
	 * ComplainingCustomer has a 0.07 probability of being generated 
	 * Customer has a 0.07 probability of being generated
	 * ShortOfTime has a 0.05 probability of being generated
	 * @return a Person object if generated 
	 */
	public Person generatePerson() {
		double probability = rand.nextDouble();
		Person person = null;
		
		if (probability <= 0.07) {
			person = new ComplainingCustomer();
		}
		else if (probability > 0.07 && probability <= 0.14) {
			person = new Customer();
		}
		else if (probability > 0.14 && probability < 0.20) {
			person = new ShortOfTimeCustomer();
		}
		return person;
	}
}