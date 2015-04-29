/**
 * Class that generates customers based on their probability of arrival
 * follows the Factory pattern 
 * @author Ben Lawton
 */
package model;

import java.util.Random;

public final class PersonFactory {
	
	private Random rand;
	
	public PersonFactory() {
		rand = new Random(42);
	}
	
	/**
	 * (Possibly) Generates a Person if their range matches the number generated. 
	 * Models the following probabilities of generation...
	 * Customer: 0.07
	 * ComplainingCustomer: 0.07 
	 * ShortOfTimeCustomer: 0.05 
	 * @return the appropriate object if generated, otherwise null 
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