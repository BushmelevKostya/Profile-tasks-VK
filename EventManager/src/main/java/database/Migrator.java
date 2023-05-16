package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Migrator {
	Statement statement;
	
	public Migrator(Statement statement) {
		this.statement = statement;
	}
	
	public void createTable() throws SQLException {
		String query = "CREATE TABLE events (Id SERIAL UNIQUE, name VARCHAR(255) NOT NULL, user_ip INT NOT NULL, user_state VARCHAR(255) NOT NULL)";
		statement.execute(query);
	}
	
	public void select() throws SQLException{
		String query = "SELECT * FROM events";
		ResultSet result = statement.executeQuery(query);
		while (result.next()) {
			int id = result.getInt(1);
			String name = result.getString(2);
			int ip = result.getInt(3);
			String status = result.getString(4);
			System.out.printf("%d | %s | %d | %s \n", id, name, ip, status);
		}
	}
	
	public void insert() throws SQLException {
		String query = "INSERT INTO events(name, user_ip, user_state) " +
		 "VALUES ('open', 1234567891, 1), ('open', 1234567892, 0), ('save', 1234567893, 3)";
		int rows = statement.executeUpdate(query);
		System.out.printf("Added %d rows", rows);
	}
}
