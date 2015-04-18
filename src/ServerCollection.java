

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
			if(currentServer.isFree()) {
				localList.add(currentServer);
			}
		}
		return localList;
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
