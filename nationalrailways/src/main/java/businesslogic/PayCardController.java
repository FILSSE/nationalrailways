/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import services.DatabaseTemplate;
/**
 *
 * @author AndreiM
 */
public class PayCardController {
    
	private final DatabaseTemplate databaseTemplate;
	public PayCardController(DatabaseTemplate databaseTemplate) {
		this.databaseTemplate = databaseTemplate;
	}
    public  boolean checkCard(String cnp,int pin){
    	return this.databaseTemplate.checkCard(cnp, pin);
    }
    
}
