package server;

import database.Migrator;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class TCPServer {
	public static void main(String[] args) {
		
		try (Connection connection = getConnection()){
			Class.forName("com.mysql.jdbc.Driver").getDeclaredConstructor().newInstance();
			System.out.println("ALL IS OK");
			
			Statement statement = connection.createStatement();
			Migrator migrator = new Migrator(statement);
			
			new Executor().execute();
			
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public static Connection getConnection() throws SQLException, IOException {
		Properties properties = new Properties();
		try (InputStream in = Files.newInputStream(Paths.get("src\\main\\java\\database\\properties.txt"))){
			properties.load(in);
		}
		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		
		return DriverManager.getConnection(url, username, password);
	}
}
