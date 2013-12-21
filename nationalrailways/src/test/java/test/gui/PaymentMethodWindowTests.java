package test.gui;

import gui.PaymentMethodWindow;
import gui.PrintTicketWindow;
import gui.RegisterWindow;

import org.junit.Test;

public class PaymentMethodWindowTests {

	@Test
	public void testWindow() {
		new PaymentMethodWindow("status", "cnp", "realCnp");

	}
}
