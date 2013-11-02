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
public class NewRouteController {
    
    public static boolean addNewRoute(String departureTime, String arrivalTime, int distance, String departureStation, String arrivalStation, String trainType,int seats,int idTrain){
        String url="jdbc:derby://localhost:1527/NationalRailways";
        try{
            Connection con=DriverManager.getConnection(url,"andrei","andrei");
            Statement instr=con.createStatement();
            String sql="INSERT INTO app.route VALUES('"+departureTime+"', '"+arrivalTime+"', "+distance+", '"+departureStation+"', '"+arrivalStation+"', '"+trainType+"', "+seats+","+idTrain+","+seats+" )";
            instr.executeUpdate(sql);
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
