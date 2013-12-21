/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;


import domain.CreditCard;
import domain.Customer;
import domain.Discount;
import domain.IDCard;
import domain.Route;
import domain.Seat;
import domain.Station;
import domain.StationList;
import domain.Ticket;
import domain.Train;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import services.DatabaseTemplate;

/**
 *
 * @author AndreiM
 */
public class BookingController {
    
    private ArrayList<Route> myRouteList=new ArrayList<Route>();;
    private ArrayList<Ticket> listTicket;
    private Ticket myTicket;
    private ArrayList<Ticket> myTickets;

	private final DatabaseTemplate databaseTemplate;
	
	public BookingController(DatabaseTemplate databaseTemplate) {
		this.databaseTemplate = databaseTemplate;
	}
	
    public void setSelectedTicket(int indexSelected){
        myTicket=listTicket.get(indexSelected);
        myTicket.computeCost();
    }
    
    public void setSelectedTickets(int index1,int index2){
        myTickets=new ArrayList<Ticket>();
        listTicket.get(index1).computeCost();
        listTicket.get(index2).computeCost();
        myTickets.add(listTicket.get(index1));
        myTickets.add(listTicket.get(index2));
    }
    
    public int updateRouteTicket(Ticket myTicket) {
        try{
            String sql1="SELECT availableseats FROM app.route l WHERE idtrain="
            		+myTicket.getRoute().getTrain().getId()
            		+" AND departuretime='"
            		+myTicket.getRoute().getDepartureTime()+"'";
            
            int available=databaseTemplate.getInt(sql1);
			available=available-1;
            String sql2="UPDATE app.route SET availableseats="+available+" WHERE idtrain="+myTicket.getRoute().getTrain().getId()+" AND departuretime='"+myTicket.getRoute().getDepartureTime()+"'";
            databaseTemplate.runSql(sql2);
            return available;
        } catch(SQLException e){
            System.out.println("Exception:"+e.getMessage());
            return -1;
        }  
    }
    
    public  boolean updateRouteTickets(){
        String url="jdbc:derby://localhost:1527/NationalRailways";
        try{
            Connection con=DriverManager.getConnection(url,"andrei","andrei");
            Statement instr=con.createStatement();
            String sql11="SELECT availableseats FROM app.route l WHERE idtrain="+myTickets.get(0).getRoute().getTrain().getId()+" AND departuretime='"+myTickets.get(0).getRoute().getDepartureTime()+"'";
            ResultSet rs1=instr.executeQuery(sql11);
            rs1.next();
            int available1=Integer.parseInt(rs1.getString(1));
            available1=available1-1;
            rs1.close();
            String sql12="UPDATE app.route SET availableseats="+available1+" WHERE idtrain="+myTickets.get(0).getRoute().getTrain().getId()+" AND departuretime='"+myTickets.get(0).getRoute().getDepartureTime()+"'";
            instr.executeUpdate(sql12);
            
            String sql21="SELECT availableseats FROM app.route l WHERE idtrain="+myTickets.get(1).getRoute().getTrain().getId()+" AND departuretime='"+myTickets.get(1).getRoute().getDepartureTime()+"'";
            ResultSet rs2=instr.executeQuery(sql21);
            rs2.next();
            int available2=Integer.parseInt(rs2.getString(1));
            available2=available2-1;
            rs2.close();
            String sql22="UPDATE app.route SET availableseats="+available2+" WHERE idtrain="+myTickets.get(1).getRoute().getTrain().getId()+" AND departuretime='"+myTickets.get(1).getRoute().getDepartureTime()+"'";
            instr.executeUpdate(sql22);
     //       String sql2="UPDATE app.route SET departuretime='"+departureTime+"', arrivaltime = '"+arrivalTime+"', distance = "+distance+", departurestation = '"+departureStation+"', arrivalstation = '"+arrivalStation+"', traintype = '"+trainType+"', seats="+seats+" WHERE idtrain="+idTrain;
            instr.close();
            con.close();
            return true;
        }
        catch(Exception e){
            System.out.println("Exception:"+e.getMessage());
            return false;
        }  
    }
    
    public  ArrayList<Ticket> createTickets(String selectedClass,String placement,String direction,int maxPrice,Discount discount,Customer customer){
        listTicket=new ArrayList<Ticket>();
        ArrayList<Route> removeRoutes=new ArrayList<Route>();
        for(Route r:myRouteList){
    //        System.out.println(maxPrice);
            int number;
            while(true){
                Random myRandom=new Random();   
       //         System.out.println(r.getTrain().getNoSeats());
                number=myRandom.nextInt(r.getTrain().getNoSeats());
                if(number!=0){
                    break;
                }
                }
            Seat mySeat=new Seat(number,selectedClass);
            mySeat.setPlacement(placement);
            mySeat.setDirection(direction);
            Ticket myTicketDesired=new Ticket(r,mySeat);
            myTicketDesired.setDiscount(discount);
            if(customer!=null){
                myTicketDesired.setClient(customer);
            } 
            myTicketDesired.computeCost();
    //        myTicketDesired.setPrice(myTicketDesired.getPrice()*discount);
            if(myTicketDesired.getPrice()<maxPrice){
    //            System.out.println(maxPrice);
                listTicket.add(myTicketDesired);
            }
            else{
                removeRoutes.add(r);
            }
        }
        myRouteList.removeAll(removeRoutes);
        return listTicket;
    }
     
    public  String checkIntermediateStation(String departureStation,String arrivalStation ){
        String intermediateStation=null;
        String url="jdbc:derby://localhost:1527/NationalRailways";
        myRouteList=null;
        try{
            Connection con=DriverManager.getConnection(url,"andrei","andrei");
            Statement instr=con.createStatement();
            String sql="SELECT arrivalstation FROM app.route l WHERE departurestation='"+departureStation+"'";
            ResultSet rs=instr.executeQuery(sql);
 /*           if(rs.next()==false)
                return false; */
            myRouteList=new ArrayList<Route>();
            while(rs.next()){
                
                String intermediate=rs.getString(1);
                String sqlinterm="SELECT * FROM app.route l WHERE departurestation='"+intermediate+"' AND arrivalstation='"+arrivalStation+"'";
      //          System.out.println(sqlinterm);
                Statement instrInterm=con.createStatement();
                ResultSet rsinterm=instrInterm.executeQuery(sqlinterm);
                if(rsinterm.next()==true){
           //         System.out.println(intermediate);
                    intermediateStation=intermediate;
                    break;
                }
                rsinterm.close();
                instrInterm.close();
            }        
            rs.close();
            instr.close();
            con.close();
            return intermediateStation;
        }
        catch(Exception e){
            System.out.println("Exception:"+e.getMessage());
            return null;
        }  
    }
    
    public  Customer getCustomer(String cnp){
        String url="jdbc:derby://localhost:1527/NationalRailways";
        Customer ticketCustomer=null;
        try{
            Connection con=DriverManager.getConnection(url,"andrei","andrei");
            Statement instr=con.createStatement();
            String sql="SELECT * FROM app.customer l WHERE cnp='"+cnp+"'";
            ResultSet rs=instr.executeQuery(sql);
 /*           if(rs.next()==false)
                return false; */
            while(rs.next()){
                String firstName=rs.getString(1);
                String lastName=rs.getString(2);
                String series=rs.getString(4);
                int number=Integer.parseInt(rs.getString(5));
                String IBAN=rs.getString(6);
                int pin=Integer.parseInt(rs.getString(7));
                IDCard idc=new IDCard(cnp,series,number);
                CreditCard cc=new CreditCard(IBAN,pin);
                ticketCustomer=new Customer(firstName,lastName,cc,idc);              
            } 
            rs.close();
            instr.close();
            con.close();
            return ticketCustomer;
        }
        catch(Exception e){
            System.out.println("Exception:"+e.getMessage());
            return null;
        } 
    }
    
    public  ArrayList<Route> checkTrain(String departureStation,String arrivalStation,String desiredType,int depLow,int depHigh,int arrivLow,int arrivHigh){
        String url="jdbc:derby://localhost:1527/NationalRailways";
        myRouteList=null;
        try{
            Connection con=DriverManager.getConnection(url,"andrei","andrei");
            Statement instr=con.createStatement();
            String sql="SELECT * FROM app.route l WHERE departurestation='"+departureStation+"' AND arrivalstation='"+arrivalStation+"'";
            ResultSet rs=instr.executeQuery(sql);
 /*           if(rs.next()==false)
                return false; */
            myRouteList=new ArrayList<Route>();
            while(rs.next()){
                String departureTime=rs.getString(1);
                String arrivalTime=rs.getString(2);
                int departureHour=Integer.parseInt(departureTime.split(":")[0]);
                int arrivalHour= Integer.parseInt(arrivalTime.split(":")[0]);
                if((depLow<=departureHour && departureHour<=depHigh && arrivLow<=arrivalHour && arrivalHour<=arrivHigh)!=true){
          //          System.out.println(departureHour+"|"+arrivalHour);
          //          System.out.println(depLow+"|"+depHigh+"|"+arrivLow+"|"+arrivHigh);
                    continue;
                }
                int distance=Integer.parseInt(rs.getString(3));
                String trainType=rs.getString(6);
                if(desiredType.compareTo(trainType)!=0 && desiredType.compareTo("Choose:")!=0){
                    continue;
                }              
                int seats=Integer.parseInt(rs.getString(7));
                int idTrain=Integer.parseInt(rs.getString(8));
                int availableSeats=Integer.parseInt(rs.getString(9));
                if(availableSeats==0){
                    continue;
                }
                Train myTrain=new Train(idTrain,trainType,seats);
                Station departureSt=new Station(departureStation);
                Station arrivalSt=new Station(arrivalStation);
                StationList myListStation=new StationList();
                myListStation.addStation(departureSt);
                myListStation.addStation(arrivalSt);
                Route myRoute=new Route(departureTime,arrivalTime,distance,myTrain,myListStation);
                myRouteList.add(myRoute);
            } 
            for(Route r:myRouteList){
      //          System.out.println(r.toString());
            }
            if(myRouteList.isEmpty()){
                return null;
            }
            rs.close();
            instr.close();
            con.close();
            return myRouteList;
        }
        catch(Exception e){
            System.out.println("Exception:"+e.getMessage());
            return null;
        }  
    }

    public  ArrayList<Ticket> getListTicket() {
        return listTicket;
    }

    public  void setListTicket(ArrayList<Ticket> listTicket) {
        this.listTicket = listTicket;
    }

    public  ArrayList<Route> getMyRouteList() {
        return myRouteList;
    }

    public  void setMyRouteList(ArrayList<Route> myRouteList) {
        this.myRouteList = myRouteList;
    }

    public  Ticket getMyTicket() {
        return myTicket;
    }

    public  void setMyTicket(Ticket myTicket) {
        this.myTicket = myTicket;
    }

    public  ArrayList<Ticket> getMyTickets() {
        return myTickets;
    }

    public  void setMyTickets(ArrayList<Ticket> myTickets) {
        this.myTickets = myTickets;
    }
    
    
}
