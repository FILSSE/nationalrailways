package tests.businesslogic;

import static org.easymock.EasyMock.anyString;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.sql.SQLException;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import services.DatabaseTemplate;
import businesslogic.BookingController;
import config.BusinessLogicTesting;
import domain.Route;
import domain.Ticket;
import domain.Train;
@Category(BusinessLogicTesting.class)
public class BookingControllerTests {


	// test object
	BookingController bookingController;
	
	// mocked objects
	DatabaseTemplate databaseTemplate;
	Ticket ticket;
	Route route;
	Train train;
	@Before
	public void before() {
		databaseTemplate = createMock(DatabaseTemplate.class);
		ticket = createMock(Ticket.class);
		route = createNiceMock(Route.class);
		train = createNiceMock(Train.class);
		
		bookingController = new BookingController(databaseTemplate);
	}
	
	@Test
	public void testSuccess() throws SQLException {
		int available = 2;

		expect(databaseTemplate.getInt(anyString())).andReturn(available);
		databaseTemplate.runSql(anyString());
		EasyMock.expectLastCall().once();
		expect(ticket.getRoute()).andReturn(route).anyTimes();
		expect(route.getTrain()).andReturn(train).anyTimes();
		replay(databaseTemplate, ticket, route, train);
		int after = bookingController.updateRouteTicket(ticket);
		verify(databaseTemplate, ticket, route);
		Assert.assertEquals(available-1, after);
	}
	
	@Test
	public void testFail() throws SQLException {

		expect(databaseTemplate.getInt(anyString())).andThrow(new SQLException());
		expect(ticket.getRoute()).andReturn(route).anyTimes();
		expect(route.getTrain()).andReturn(train).anyTimes();
		replay(databaseTemplate, ticket, route, train);
		int after = bookingController.updateRouteTicket(ticket);
		verify(databaseTemplate);
		Assert.assertEquals(-1, after);
	}
}