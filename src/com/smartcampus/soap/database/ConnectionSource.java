package com.smartcampus.soap.database;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.naming.Context;
import javax.naming.InitialContext;

public class ConnectionSource {

	private static Connection connection = null;
	private static Context context=null;
	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://fausocialmedia.c4tfnnnnkrdn.us-east-1.rds.amazonaws.com/FauDB";

	private static final String USER = "prakash";
	private static final String PASS = "prakash123";
	
	public static Connection getConnection(){
	if(connection!=null)
		return connection;
		
		try{
			if(context==null){
				context=new InitialContext();
			}
			Class.forName(JDBC_DRIVER).newInstance();
			connection = DriverManager.getConnection(DB_URL, USER, PASS);
		}catch(Exception e){
			e.printStackTrace();
		}
		return connection;
	}
}
