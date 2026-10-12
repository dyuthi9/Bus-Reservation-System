package com.brs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	public static Connection getConnection() {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/brs";
		String username = "root";
		String passward = "root";
		
		Connection connection=null;
		
		try {
			Class.forName(driver);
			
			connection=DriverManager.getConnection(url, username, passward);
			
			connection.setAutoCommit(false);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
