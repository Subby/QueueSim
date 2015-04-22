package test;

import java.util.ArrayList;
import java.util.Arrays;

import Model.HumanServer;
import Model.Server;
import Model.ServerCollection;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import Model.Customer;

public class ServerCollectionTest {
	
	private ServerCollection servers;
	private Server server;
	private Server server1;
	private Customer customer;
	
	@Before
	public void setUp() {
		servers = new ServerCollection();
		server = new HumanServer();
		server1 = new HumanServer();
		customer = new Customer();
		
	}
	
	@Test
	public void testShowAvailableServers() {
		servers.addServer(server);
		servers.addServer(server1);
		server1.setFree(false);
		assertSame(server, servers.showAvailableServers().get(0));
	}
	
	@Test 
	public void testServeCustomers() {
		servers.addServer(server);
		servers.addServer(server1);
		server.setCurrentCustomer(customer);
		for (int i = 0; i < 3; i++) {
			servers.serveCustomers();
		}
		assertEquals(3, server.getTimeSpentServing());
		assertEquals(0, server1.getTimeSpentServing());
	}
	
	@Test
	public void testFinishWithCustomers() {
		servers.addServer(server);
		server.setCurrentCustomer(customer);
		assertEquals(0, servers.showAvailableServers().size());
		int customerServeTime = customer.getServeTime();
		for (int i = 0; i < customerServeTime; i++) {
			servers.serveCustomers();
		}
		servers.finishWithCustomers();
		assertSame(server, servers.showAvailableServers().get(0));
	}
	
	@Test
	public void testGetServers() {
		servers.addServer(server);
		servers.addServer(server1);
		ArrayList<Server> expected = new ArrayList<Server>(Arrays.asList(server, server1));
		ArrayList<Server> actual = servers.getServers();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testAddServer() {
		assertEquals(0, servers.getServers().size());
		servers.addServer(server);
		assertEquals(1, servers.getServers().size());
	}
	
	
}
