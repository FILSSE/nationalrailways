package integration;

import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import nationalrailways.MyController;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import services.DatabaseTemplate;
import businesslogic.ApplicationContext;
import businesslogic.BookingController;
import domain.Route;
import domain.Ticket;
import domain.Train;

public class IntegrationTests {
	private final static Logger log = Logger.getLogger("IntegrationTests");
	public final static int CAPACITY = 300;

	// test object
	MyController myController;
	
	Ticket ticket;
	Route route;
	Train train;
	@Before
	public void before() {
		myController = new MyController();
	}
	
	@Test
	public void testRegisterSuccess() throws SQLException {
		String firstName = "Mihaita";
		String lastName = "Tinta";
		String CNP = "11122222222";
		String series ="SZ99999";
		String number = "12231";
		String IBAN = "RO33ING2832832";
		int pin = 2222;
		MyController.context.getRegisterController().addNewCustomer(firstName, lastName, CNP, series, number, IBAN, pin);
		boolean _true = MyController.context.getCashierController().checkCustomer(CNP);
		Assert.assertTrue(_true);
		MyController.context.getDatabaseTemplate().deleteCustomer(CNP);
		

	}
	
	@Test
	public void testCleanCustomers() throws SQLException {
		MyController.context.getDatabaseTemplate().deleteAllCustomer();
	}
	
	@Test
	public void testCapacity() throws SQLException {
		String firstName = "Mihaita";
		String lastName = "Tinta";
		String CNP = "11122222222";
		String series ="SZ99999";
		String number = "12231";
		String IBAN = "RO33ING2832832";
		int pin = 2222;
		System.out.println( "testCapacity - start");
		for (int i = 0;i<CAPACITY;i++) {
			System.out.println( "testCapacity - " + i);
			MyController.context.getRegisterController().addNewCustomer(firstName, lastName, CNP + i, series, number, IBAN, pin);
			boolean _true = MyController.context.getCashierController().checkCustomer( CNP + i);
			Assert.assertTrue(_true);
		}
		System.out.println( "testCapacity - stop");
		System.out.println( "testCapacity - clean start");
		for (int i = 0;i<CAPACITY;i++) {
			System.out.println( "testCapacity - clean " + i);
			MyController.context.getDatabaseTemplate().deleteCustomer(CNP + i);
		}
		System.out.println( "testCapacity - clean stop");
		

	}
	
	@Test
	public void testLoginAdminSuccess() throws SQLException {
		boolean _true = MyController.context.getLoginController().checkLogin("admin", "admin");
		Assert.assertTrue(_true);
		

	}
	
	@Test
	public void testLoginCashierSuccess() throws SQLException {
		boolean _true = MyController.context.getLoginController().checkLogin("cashier", "cashier");
		Assert.assertTrue(_true);
		

	}
	
	@Test
	public void testGetRoutes() throws SQLException {
		MyController.context.getModifyRouteController().getRouteList();
	}
	
	@Test
	public void testUpdateRoute() throws SQLException {
		String departureTime = "20:18:00";
		String arrivalTime = "22:22:00";
		String departureStation = "Constanta";
		String arrivalStation = "Bucharest";
		String trainType = "rapid";
		int seats = 200;
		int idTrain = 92329;
		int distance = 300;
		MyController.context.getModifyRouteController().updateRoute(departureTime, arrivalTime, distance, departureStation, arrivalStation, trainType, seats, idTrain);

		MyController.context.getDatabaseTemplate().deleteRoute(idTrain);
	}
	
	@Test
	public void testUpdateRouteFail() throws SQLException {
		boolean _true = MyController.context.getBookingController().updateRouteTickets();
		// TODO some null exception
		Assert.assertFalse(_true);
		

	}
}