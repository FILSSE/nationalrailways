package services;

import java.sql.SQLException;

public interface DatabaseTemplate {

	public  boolean checkLogin(String username,String password);
	public  boolean checkCard(String cnp,int pin);
	public  void runSql(String sql) throws SQLException;
	public int getInt(String sql) throws SQLException;
	public void deleteCustomer(String cnp)throws SQLException;
	public void deleteAllCustomer()throws SQLException;
	public void deleteRoute(int trainId)throws SQLException;
}
