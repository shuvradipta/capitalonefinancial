package in.capitalonefinancial.admin.jdbc;

import java.io.InputStream;

public class UploadJDBCConnectorDAO extends JDBCConnectorDAO {

	public UploadJDBCConnectorDAO(InputStream is) {
		super(is);
	}
	
	public UploadJDBCConnectorDAO(String dbUrl, String database, String username, String password) {
		super(dbUrl, database, username, password);
	}
		
}
