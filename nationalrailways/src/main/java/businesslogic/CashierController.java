/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author AndreiM
 */
public class CashierController {
    
    
    public  boolean checkCustomer(String cnp){
 //       System.out.println(username+" "+password);
        String url="jdbc:derby://localhost:1527/NationalRailways";
        try{
            Connection con=DriverManager.getConnection(url,"andrei","andrei");
            Statement instr=con.createStatement();
            String sql="SELECT * FROM app.customer l WHERE cnp='"+cnp+"'";
            ResultSet rs=instr.executeQuery(sql);
            if(rs.next()==false)
                return false;
            rs.close();
            instr.close();
            con.close();
        }
        catch(Exception e){
            System.out.println("Exception:"+e.getMessage());
            return false;
        }
        return true;
    }
    
}
