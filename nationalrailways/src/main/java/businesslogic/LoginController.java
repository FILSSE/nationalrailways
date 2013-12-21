/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import services.DatabaseTemplate;

/**
 *
 * @author AndreiM
 */
public class LoginController {
	private final DatabaseTemplate databaseTemplate;
	
	public LoginController(DatabaseTemplate databaseTemplate) {
		this.databaseTemplate = databaseTemplate;
	}
	
    public boolean checkLogin(String username,String password){
    	
    	return databaseTemplate.checkLogin(username, password);
    }
    
}
