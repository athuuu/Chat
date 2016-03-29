package com.bng.chat.server.BD;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.ResourceBundle;

import com.bng.chat.server.ServerChat;

public class ConnexionBD {
public static Connection connection;
	
	
	public static Connection getConnection()
	{	
    	try {
        
    		
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/tchat","root","root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection ;
		
	}

}
