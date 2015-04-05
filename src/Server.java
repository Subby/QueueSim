

/**
 * An abstract class to represent a Server.
 * @author Denver
 *
 */
public abstract class Server {
	/**
	 * The current customer the server is serving.
	 */
	private Customer currentCustomer;
	/**
	 * A flag to check whether the server is free.
	 */
	private boolean isFree;
	
	public Server() {
		currentCustomer = null;
		isFree = true;
	}
	
	/**
	 * Sets the {@link #currentCustomer} and {@link #isFree()} variables to their default value.
	 * This indicates that the server is free.
	 */
	public void finishWithCustomer() {
		currentCustomer = null;
		isFree = true;
	}
	
	/**
	 * Servers the customer.
	 * @param customer the customer to serve
	 * @return whether the operation is successful
	 */
	public boolean serveCustomer(Customer customer) {
		if(currentCustomer != null) {
			return false;
		}
		if(!isFree) {
			return false;
		}
		return true;
	}
	
	/**
	 * Shows the availability of the server.
	 * @return whether the server is free or not
	 */
	public boolean showAvailability() {
		return ((isFree) && (currentCustomer == null));
	}
	
	/**
	 * Gets the current customer being server.
	 * @return the current customer being server
	 */
	public Customer getCurrentCustomer() {
		return currentCustomer;
	}
	
	/**
	 * Sets the current customer being served.
	 * @param currentCustomer the customer being served.
	 */
	public void setCurrentCustomer(Customer currentCustomer) {
		this.currentCustomer = currentCustomer;
	}
	
	/**
	 * Checks whether the server is free or not.
	 * @return whether the server is free or not
	 */
	public boolean isFree() {
		return isFree;
	}
	
	/**
	 * Sets the flag to indicate whether the server is free or not.
	 * @param isFree whether the server is free or not
	 */
	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

}
