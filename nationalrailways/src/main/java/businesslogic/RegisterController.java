/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
/**
 *
 * @author AndreiM
 */
public class RegisterController {
    
        public  boolean addNewCustomer(String firstName, String lastName,String CNP,String series, String number, String IBAN, int pin){
        String url="jdbc:derby://localhost:1527/NationalRailways";
        try{
            Connection con=DriverManager.getConnection(url,"andrei","andrei");
            Statement instr=con.createStatement();
            String sql="INSERT INTO app.customer (FIRSTNAME, LASTNAME, CNP , SERIES , NUMBER , IBAN , PIN )" +
            		" VALUES('"+firstName+"', '"+lastName+"', '"+CNP+"', '"+series+"', '"+number+"', '"+IBAN+"', "+pin+" )";
            instr.executeUpdate(sql);
            instr.close();
            con.close();
        }
        catch(Exception e){
            System.out.println("Exception:"+e);
            return false;
        }
        return true;
    }
    
}
