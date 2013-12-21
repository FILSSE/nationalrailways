package tests.businesslogic;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import services.DatabaseTemplate;
import businesslogic.LoginController;
import config.BusinessLogicTesting;
@Category(BusinessLogicTesting.class)
public class LoginControllerTests {

	// test object
	LoginController loginController;
	
	// mocked objects
	DatabaseTemplate databaseTemplate;
	@Before
	public void before() {
		databaseTemplate = createMock(DatabaseTemplate.class);
		
		loginController = new LoginController(databaseTemplate);
	}
	
	@Test
	public void testLoginSuccess() {
		String username = "us";
		String pass = "pass123";

		expect(databaseTemplate.checkLogin(eq(username), eq(pass))).andReturn(true);
		replay(databaseTemplate);
		boolean trueR = loginController.checkLogin(username, pass);
		verify(databaseTemplate);
		Assert.assertTrue(trueR);
	}
	
	@Test
	public void testLoginFail() {
		String username = "us";
		String pass = "pass123";

		expect(databaseTemplate.checkLogin(eq(username), eq(pass))).andReturn(false);
		replay(databaseTemplate);
		boolean trueR = loginController.checkLogin(username, pass);
		verify(databaseTemplate);
		Assert.assertFalse(trueR);
	}
	
	@Test(expected=RuntimeException.class)
	public void testLoginError() {
		String username = "us";
		String pass = null;

		expect(databaseTemplate.checkLogin(eq(username), eq(pass)))
		.andThrow(new RuntimeException());
		replay(databaseTemplate);
		loginController.checkLogin(username, pass);
		Assert.fail();
	}
}
