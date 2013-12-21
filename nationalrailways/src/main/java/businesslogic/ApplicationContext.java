package businesslogic;

import java.sql.SQLException;

import javax.management.RuntimeErrorException;

import services.DatabaseTemplate;
import services.DatabaseTemplateImpl;

public class ApplicationContext {
	private final DatabaseTemplate databaseTemplate;
	private final BookingController bookingController;
	private final CashierController cashierController;
	private final LoginController loginController;
	private final ModifyRouteController modifyRouteController;
	private final NewRouteController newRouteController;
	private final PayCardController payCardController;
	private final RegisterController registerController;
	
	
	public ApplicationContext(){
		try {
			databaseTemplate = new DatabaseTemplateImpl();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		bookingController = new BookingController(databaseTemplate);
		cashierController = new CashierController();
		loginController = new LoginController(databaseTemplate);
		modifyRouteController = new ModifyRouteController();
		newRouteController = new NewRouteController();
		payCardController = new PayCardController(databaseTemplate);
		registerController = new RegisterController();
	}


	public BookingController getBookingController() {
		return bookingController;
	}


	public CashierController getCashierController() {
		return cashierController;
	}


	public LoginController getLoginController() {
		return loginController;
	}


	public ModifyRouteController getModifyRouteController() {
		return modifyRouteController;
	}


	public NewRouteController getNewRouteController() {
		return newRouteController;
	}


	public PayCardController getPayCardController() {
		return payCardController;
	}


	public RegisterController getRegisterController() {
		return registerController;
	}


	public DatabaseTemplate getDatabaseTemplate() {
		return databaseTemplate;
	}
	
	
}
