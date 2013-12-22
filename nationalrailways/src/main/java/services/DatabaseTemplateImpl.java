package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTemplateImpl implements DatabaseTemplate{
    private final static String url="jdbc:derby://localhost:1527/NationalRailways;create=true";
    private Connection con;

    public DatabaseTemplateImpl() throws SQLException {
    	con=DriverManager.getConnection(url,"andrei","andrei");
    }
    
    public  boolean checkCard(String cnp,int pin) {
       try{
           Statement instr=con.createStatement();
           String sql="SELECT * FROM app.customer l WHERE cnp='"+cnp+"' AND pin="+pin;
      //     System.out.println(sql);
           ResultSet rs=instr.executeQuery(sql);
           if(rs.next()==false)
               return false;
           rs.close();
           instr.close();
       }
       catch(Exception e){
           System.out.println("Exception:"+e.getMessage());
           return false;
       }
       return true;
    }
	public  boolean checkLogin(String username,String password) {

		 //       System.out.println(username+" "+password);
		        try{
		            Statement instr=con.createStatement();
		            String sql="SELECT * FROM app.login l WHERE username='"+username+"' AND password='"+password+"'";
		            ResultSet rs=instr.executeQuery(sql);
		            if(rs.next()==false)
		                return false;
		/*            while(rs.next()){
		                System.out.println(rs.getString(1));
		            } */
		            

//		            Statement s = null;
		            /* Creating a statement object that we can use for running various
		             * SQL statements commands against the database.*/
//		            s = con.createStatement();
//		            s.execute("create table APP.LOGIN(USERNAME varchar(40), PASSWORD varchar(40))");
//		            s.execute("create table APP.CUSTOMER(FIRSTNAME varchar(40), LASTNAME varchar(40), CNP varchar(40), SERIES varchar(40), NUMBER varchar(40), IBAN varchar(40), PIN int)");
//		            s.execute("create table APP.ROUTE(DEPARTURETIME varchar(40), ARRIVALTIME varchar(40), DISTANCE int, DEPARTURESTATION varchar(40), " +
//		           		" ARRIVALSTATION varchar(40), TRAINTYPE varchar(40), SEATS int, IDTRAIN int, AVAILABLESEATS int)");
//		            s.execute("INSERT INTO APP.LOGIN (USERNAME, PASSWORD)  VALUES ('admin', 'admin') ");
//		            s.execute("INSERT INTO APP.LOGIN (USERNAME, PASSWORD)  VALUES ('cashier', 'cashier')");
//		            s.execute("INSERT INTO APP.CUSTOMER (FIRSTNAME, LASTNAME, CNP, SERIES, IBAN, PIN)  VALUES ('Marius', 'Popescu', '1870912349920', 'RZ', 'ROBRDE892053458', 1234)");
		//
		//
//		            s.execute("INSERT INTO APP.ROUTE (DEPARTURETIME, ARRIVALTIME, DISTANCE, DEPARTURESTATION, ARRIVALSTATION, TRAINTYPE, SEATS, IDTRAIN, AVAILABLESEATS) VALUES ('07:00', '10:00', 150, 'Bucuresti', 'Brasov', 'InterCity', 120, 89234, 120)");
//		       s.execute(" INSERT INTO APP.ROUTE (DEPARTURETIME, ARRIVALTIME, DISTANCE, DEPARTURESTATION, ARRIVALSTATION, TRAINTYPE, SEATS, IDTRAIN, AVAILABLESEATS) VALUES ('08:10', '12:30', 150, 'Bucuresti', 'Brasov', 'Personal', 200, 89123, 200)");
//		       s.execute(" INSERT INTO APP.ROUTE (DEPARTURETIME, ARRIVALTIME, DISTANCE, DEPARTURESTATION, ARRIVALSTATION, TRAINTYPE, SEATS, IDTRAIN, AVAILABLESEATS) VALUES ('08:15', '12:45', 150, 'Bucuresti', 'Brasov', 'Accelerat', 150, 89652, 150)");
//		       s.execute(" INSERT INTO APP.ROUTE (DEPARTURETIME, ARRIVALTIME, DISTANCE, DEPARTURESTATION, ARRIVALSTATION, TRAINTYPE, SEATS, IDTRAIN, AVAILABLESEATS) VALUES ('13:30', '14:15', 50, 'Brasov', 'Sighisoara', 'Accelerat', 50, 89233, 50)");
//		       s.execute(" INSERT INTO APP.ROUTE (DEPARTURETIME, ARRIVALTIME, DISTANCE, DEPARTURESTATION, ARRIVALSTATION, TRAINTYPE, SEATS, IDTRAIN, AVAILABLESEATS)  VALUES ('13:45', '14:30', 50, 'Brasov', 'Sighisoara', 'Accelerat', 50, 89444, 50)");
					rs.close();
		            instr.close();
		        } catch(Exception e){
		            System.out.println("Exception:"+e.getMessage());
		            return false;
		        }
		        return true;
	}
	
	@Override
	public  void runSql(String sql) throws SQLException {
		Statement instr=con.createStatement();
		try {
			instr.executeUpdate(sql);
		} finally {
			instr.close();
		}
	}
	@Override
	public int getInt(String sql) throws SQLException {
		int available = 0;
		Statement instr=con.createStatement();
		try { 
			ResultSet rs=instr.executeQuery(sql);
			try {rs.next();
			available=Integer.parseInt(rs.getString(1));
			} finally { 
				rs.close();
			}
		} finally { 
			instr.close();
		}
		return available;
	}

	@Override
	protected void finalize() throws Throwable {
		if (con!=null)
			try {
				con.close();
		} catch(SQLException e){
			
		}
		super.finalize();
	}
	
	@Override
	public void deleteAllCustomer() throws SQLException {
		Statement instr=con.createStatement();
		try {
            String sql="delete FROM app.customer l WHERE 1=1";
			instr.executeUpdate(sql);
		} finally {
			instr.close();
		}
		
	}
	@Override
	public void deleteCustomer(String cnp) throws SQLException {
		Statement instr=con.createStatement();
		try {
            String sql="delete FROM app.customer l WHERE cnp='"+cnp+"'";
			instr.executeUpdate(sql);
		} finally {
			instr.close();
		}
		
	}
	
	@Override
	public void deleteRoute(int trainId) throws SQLException {
		Statement instr=con.createStatement();
		try {
            String sql="delete FROM  APP.ROUTE l WHERE IDTRAIN="+trainId+"";
			instr.executeUpdate(sql);
		} finally {
			instr.close();
		}
		
	}
}
