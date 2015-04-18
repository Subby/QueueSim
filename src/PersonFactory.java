/**
 * Class that handles the generation of customers based on
 * their probability of being generated
 * @author Ben Lawton
 */

import java.util.Random;


public final class PersonFactory {
	
	public PersonFactory() {}
	
	private int generateRandomNumber() {
		Random rand = new Random(42);
		return rand.nextInt(101);
	}
	
	/**
	 * Generates a new person if the probability matches their likelihood of appearing
	 * ComplainingCustomer has a 0.07 probability of being generated 
	 * Customer has a 0.07 probability of being generated
	 * ShortOfTime has a 0.05 probability of being generated
	 * @return a Person object if generated 
	 */
	public Person generatePerson() {
		int probability = generateRandomNumber();
		Person person = null;
		
		if (probability <= 7) {
			person = new ComplainingCustomer();
		}
		else if (probability > 7 && probability <= 14) {
			person = new Customer();
		}
		else if (probability > 14 && probability < 20) {
			person = new ShortOfTimeCustomer();
		}
		return person;
	}
}