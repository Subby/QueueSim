package model;

import java.util.ArrayList;

/**
 * Represents a collection of many Servers.
 * @author Denver Fernandes
 * @author Ben Lawton 
 */

public class ServerCollection {
	
	//A collection of the servers in the simulation 
	private ArrayList<Server> servers;
	
	public ServerCollection() {
		servers = new ArrayList<Server>();
	}
	
	/**
	 * Returns all available Servers.
	 * @return the available Servers
	 */
	public ArrayList<Server> showAvailableServers() {
		ArrayList<Server> localList = new ArrayList<Server>();
		for(Server currentServer : servers) {
			if(currentServer.showAvailability()) {
				localList.add(currentServer);
			}
		}
		return localList;
	}
	
	/**
	 * Increments timeServed for all Servers with an allocated Person(Customer)
	 */
	public void serveCustomers() {
		if (servers.size() > 0) {
			for (Server server : servers) {
				if (server.isFree() == false) {
					server.serveCustomer();
				}
			}
		}
	}
	
	/**
	 * Deallocates customers from servers if their serve time (time it takes to serve them) has been reached 
	 */
	public void finishWithCustomers() {
		if (servers.size() > 0) {
			for (Server server : servers) {
				if (server.showAvailability() == false) {
					Person currentCustomer = server.getCurrentCustomer();
					if (currentCustomer.getServeTime() <= server.getTimeSpentServing()) {
						server.finishWithCustomer();
					}
				}
			}
		}
	}
	/**
	 * Returns all servers in the collection 
	 * @return an ArrayList holding the servers in the collection 
	 */
	public ArrayList<Server> getServers() {
		return servers;
	}

	/**
	 * Adds a server to the collection
	 * @param the Server to be added  
	 */
	public void addServer(Server server) {
		this.servers.add(server);
	}
}
