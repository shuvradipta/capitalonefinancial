package in.capitalonefinancial.admin.jdbc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class JDBCConnectorDAO {
	
	protected String dbUrl = "";
	
	protected String database = "";
	
	protected String username = "";
	
	protected String password = "";
	
	
	
	public JDBCConnectorDAO(String dbUrl, String database, String username, String password) {
		super();
		this.dbUrl = dbUrl;
		this.database = database;
		this.username = username;
		this.password = password;
	}

	public JDBCConnectorDAO(InputStream is) {
		super();
		this.initializeDatabaseParameters(is);
	}



	private void initializeDatabaseParameters(InputStream filePath){
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(filePath));
		try {
			String rawCredential = bufferedReader.readLine();
			String[] rawCredentialSplit = rawCredential.split("@");
			for (int i = 0; i < rawCredentialSplit.length; i++) {
				System.out.println(rawCredentialSplit[i]);
			}
		} catch (IOException e) {
			System.out.println("IOException while reading");
		}finally {
			try {
				bufferedReader.close();
			} catch (IOException e) {
				System.out.println("IOException while closing");
			}
		}
	}

}
