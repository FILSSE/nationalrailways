/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import domain.Route;
import domain.RouteList;
import domain.Station;
import domain.StationList;
import domain.Train;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author AndreiM
 */
public class ModifyRouteController {
    
    private static RouteList myRouteList;
    
    public  RouteList getRouteList(){
        myRouteList=new RouteList();
        RouteList rl=new RouteList();
        String url="jdbc:derby://localhost:1527/NationalRailways";
        try{
            Connection con=DriverManager.getConnection(url,"andrei","andrei");
            Statement instr=con.createStatement();
            String sql="SELECT * FROM app.route ";
            ResultSet rs=instr.executeQuery(sql);         
            while(rs.next()){
                String departureTime=rs.getString(1);
                String arrivalTime=rs.getString(2);
                int distance=Integer.parseInt(rs.getString(3));
                String departureStation=rs.getString(4);
                String arrivalStation=rs.getString(5);
                String trainType=rs.getString(6);      
                int seats=Integer.parseInt(rs.getString(7));
                int idTrain=Integer.parseInt(rs.getString(8));
                Train myTrain=new Train(idTrain,trainType,seats);
                Station departureSt=new Station(departureStation);
                Station arrivalSt=new Station(arrivalStation);
                StationList myListStation=new StationList();
                myListStation.addStation(departureSt);
                myListStation.addStation(arrivalSt);
                Route myRoute=new Route(departureTime,arrivalTime,distance,myTrain,myListStation);
                rl.addRoute(myRoute);
            } 
            rs.close();
            instr.close();
            con.close();
        }
        catch(Exception e){
            System.out.println("Exception:"+e.getMessage());
            return null;
        }
        myRouteList=rl;
        return rl;
    }

    public  boolean updateRoute(String departureTime,String arrivalTime,int distance,String departureStation,String arrivalStation,String trainType,int seats,int idTrain){
        String url="jdbc:derby://localhost:1527/NationalRailways";
        try{
            Connection con=DriverManager.getConnection(url,"andrei","andrei");
            Statement instr=con.createStatement();
            String sql="UPDATE app.route SET departuretime='"+departureTime+"', arrivaltime = '"+arrivalTime+"', distance = "+distance+", departurestation = '"+departureStation+"', arrivalstation = '"+arrivalStation+"', traintype = '"+trainType+"', seats="+seats+" WHERE idtrain="+idTrain;
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
    
    public  RouteList getMyRouteList() {
        return myRouteList;
    }

    public  void setMyRouteList(RouteList myRouteList) {
        ModifyRouteController.myRouteList = myRouteList;
    }
    
}
