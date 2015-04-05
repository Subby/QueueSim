

import java.util.ArrayList;
/**
 * Represents a collection of many Servers.
 * @author Denver Fernandes
 *
 */
public class ServerCollection {
	
	/**
	 * All the different servers in this.
	 */
	private ArrayList<Server> servers = new ArrayList<Server>();
	
	/**
	 * The instance.
	 */
	private static ServerCollection instance = null;
	
	protected ServerCollection() {
		//To allow only a single point of access, the constructor is protected.
	}
	
	/**
	 * The object creation point.
	 * @return the instance
	 */
	public static ServerCollection getInstance() {
		if(instance == null) {
			instance = new ServerCollection();
		}
		return instance;
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
	 * Sets the servers in this collection.
	 * @param servers the servers in this colleciton
	 */
	public void setServers(ArrayList<Server> servers) {
		this.servers = servers;
	}
}
