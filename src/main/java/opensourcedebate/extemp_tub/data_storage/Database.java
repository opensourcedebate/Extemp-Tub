package opensourcedebate.extemp_tub.data_storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

public class Database {
	private Date Date = new Date();
	
	public Database(String databaseName) {
		String createThreadsTable = "CREATE TABLE Threads (thread_id VARCHAR(10) NOT NULL, title VARCHAR(100) NOT NULL, created TIMESTAMP NOT NULL, subreddit VARCHAR(15) NOT NULL, url VARCHAR(100) NOT NULL, author VARCHAR(50) NOT NULL, status VARCHAR(10) NOT NULL, PRIMARY KEY (thread_id))";
		Connection connection = null;
		Statement statement = null;
		
		try {
			String databasePath = "jdbc:derby:" + databaseName + ";create=true";
			connection = DriverManager.getConnection(databasePath);
			
			statement = connection.createStatement();
			statement.executeUpdate(createThreadsTable);
			if (connection != null) {
				System.out.println(new Timestamp(Date.getTime()) + " Database: " + databaseName + ": Created.");
				statement.close();
				connection.close();
            }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
		}
	}
	
	public void insert() {
		
	}
	
	public void update() {
		
	}
	
	public void query() {
		
	}
}
