package in.capitalonefinancial.admin.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class InsertRecordDAO extends JDBCConnectorDAO {

	public InsertRecordDAO(String dbUrl, String database, String username, String password) {
		super(dbUrl, database, username, password);
	}
	
	public InsertRecordDAO(InputStream is) {
		super(is);
	}
	
	public int insertRecord(HashMap<String, String> record, String dbTableName, ArrayList<String> dbColumnOrder) {
		System.out.println("InsertRecordDAO.insertRecord() - dbColumnOrder :: " + dbColumnOrder);
		System.out.println("InsertRecordDAO.insertRecord() - record ::" + record);
		int rowsUpdated = 0;
		String paramQuery = "";
		for(int i=0; i< dbColumnOrder.size(); i++) {
			String columnName = dbColumnOrder.get(i);
			paramQuery = paramQuery.concat("'" + record.get(columnName) + "',");
			System.out.println("InsertRecordDAO.insertRecords() - paramQuery :: " + paramQuery);
		}
		paramQuery = paramQuery.substring(0, paramQuery.length()-1);
		System.out.println("InsertRecordDAO.insertRecords() - paramQuery :: " + paramQuery);
		
		// This will load the MySQL driver, each DB has its own driver
	    try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    // Setup the connection with the DB
	    try {
			Connection connect = DriverManager
			      .getConnection("jdbc:mysql://localhost/capitalonedb?"
			          + "user=root&password=root");
			// Statements allow to issue SQL queries to the database
		    PreparedStatement statement = connect.prepareStatement("insert into " + dbTableName + " values (" + paramQuery + ");");
		    //System.out.println("InsertRecordDAO.insertRecords() - statement :: " + statement.toString());
		    rowsUpdated = statement.executeUpdate();
		    connect.close();
		} catch (SQLException e) {
			System.out.println("InsertRecordDAO.insertRecord() - SQLException" + e.getMessage());
			rowsUpdated = 0;
		}
		return rowsUpdated;
	}

}
