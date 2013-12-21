package tests.businesslogic;

import static org.easymock.EasyMock.*;
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
import businesslogic.PayCardController;
import config.BusinessLogicTesting;
import domain.Route;
import domain.Ticket;
import domain.Train;
@Category(BusinessLogicTesting.class)
public class PayCardControllerTests {


	// test object
	PayCardController payCardController;
	
	// mocked objects
	DatabaseTemplate databaseTemplate;

	@Before
	public void before() {
		databaseTemplate = createMock(DatabaseTemplate.class);
		
		payCardController = new PayCardController(databaseTemplate);
	}
	
	@Test
	public void testSuccess() throws SQLException {
		String cnp = "1";
		int pin = 11;

		expect(databaseTemplate.checkCard(eq(cnp), eq(pin))).andReturn(true);
		replay(databaseTemplate);
		boolean _true = payCardController.checkCard(cnp, pin);
		verify(databaseTemplate);
		Assert.assertTrue(_true);
	}
	
	@Test
	public void testFail() throws SQLException {
		String cnp = "1";
		int pin = 11;

		expect(databaseTemplate.checkCard(eq(cnp), eq(pin))).andReturn(false);
		replay(databaseTemplate);
		boolean _true = payCardController.checkCard(cnp, pin);
		verify(databaseTemplate);
		Assert.assertFalse(_true);
	}
}