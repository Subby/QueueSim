package model;


import java.util.ArrayList;
/**
 * Represents a collection of many Servers.
 * @author Denver Fernandes
 * @author Ben Lawton 
 */
public class ServerCollection {
	
	//A collection of all of the different servers.
	private ArrayList<Server> servers;
	
	public ServerCollection() {
		servers = new ArrayList<Server>();
	}
	
	/**
	 * Returns all the available servers.
	 * @return the available servers
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
	 * Increments timeServed for all Servers with an allocated Person
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
	 * Finishes with the Servers' customers if their serving time has been reached
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
	 * Gets all the servers in this collection.
	 * @return the servers in this collection
	 */
	public ArrayList<Server> getServers() {
		return servers;
	}

	/**
	 * Adds a server to the collection 
	 */
	public void addServer(Server server) {
		this.servers.add(server);
	}
}
