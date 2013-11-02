package tests.businesslogic;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import businesslogic.BookingController;
import config.BusinessLogicTesting;
@Category(BusinessLogicTesting.class)
public class BookingControllerTests {

	BookingController bookingController;
	
	@Before
	public void before() {
		bookingController = new BookingController();
	}
	
	@Test
	public void testTicket() {
		// TODO test methods..why are so many static used? no objects with specific states? :(

		bookingController = new BookingController();
	}
}
